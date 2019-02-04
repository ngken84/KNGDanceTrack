package joker.persona.ngrocken.kngdancetrack.danceview.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Consumer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import joker.persona.ngrocken.kngdancetrack.R;
import joker.persona.ngrocken.kngdancetrack.database.DanceDBTasks;
import joker.persona.ngrocken.kngdancetrack.model.Dance;
import joker.persona.ngrocken.kngdancetrack.util.DanceConsumer;
import joker.persona.ngrocken.kngdancetrack.util.StringUtils;

public class CreateDanceFragment extends Fragment implements View.OnClickListener {

    private EditText nameEdit;
    private Spinner categorySpinner;
    private EditText descriptionEdit;
    private Button createDanceButton;

    ArrayAdapter<String> mAdapter;

    public CreateDanceFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_dance, null);

        nameEdit = view.findViewById(R.id.fcd_name_edit);
        categorySpinner = view.findViewById(R.id.fcd_category_spinner);
        descriptionEdit = view.findViewById(R.id.fcd_description_edit);

        createDanceButton = view.findViewById(R.id.fcd_create_dance_btn);
        createDanceButton.setOnClickListener(this);

        mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, android.R.id.text1);
        categorySpinner.setAdapter(mAdapter);

        DanceDBTasks.getCategories(getActivity(), new DanceConsumer<List<String>>() {
            @Override
            public void consume(List<String> strings) {
                mAdapter.addAll(strings);
            }

            @Override
            public void handleError() {

            }
        });


        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fcd_create_dance_btn:
                createDance();
                break;
        }
    }

    public void createDance(){
        boolean hasError = false;

        String name = nameEdit.getText().toString();
        if(StringUtils.isEmpty(name)) {
            hasError = true;
            nameEdit.setError("Please enter a name.");
        }

        String category = categorySpinner.getSelectedItem().toString();
        if(StringUtils.isEmpty(category)) {
            hasError = true;
        }

        if(hasError) {
            return;
        }

        String description = descriptionEdit.getText().toString();

        Dance newDance = new Dance(0, name, category, description, new Date());

        final CreateDanceFragment fragment = this;

        DanceDBTasks.insertDance(getContext(), new DanceConsumer<Long>() {
            @Override
            public void consume(Long aLong) {
                Intent intent = new Intent();
                intent.putExtra("id", aLong);
                fragment.getActivity().setResult(Activity.RESULT_OK, intent);
                fragment.getActivity().finish();
            }

            @Override
            public void handleError() {
                Toast.makeText(getContext(), getError(), Toast.LENGTH_SHORT).show();
            }
        }, newDance);

    }
}
