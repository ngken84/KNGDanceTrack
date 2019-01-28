package joker.persona.ngrocken.kngdancetrack.danceview.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Consumer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import joker.persona.ngrocken.kngdancetrack.R;
import joker.persona.ngrocken.kngdancetrack.database.DanceDBTasks;
import joker.persona.ngrocken.kngdancetrack.model.Dance;
import joker.persona.ngrocken.kngdancetrack.util.StringUtils;

public class CreateDanceFragment extends Fragment implements View.OnClickListener {

    private EditText nameEdit;
    private EditText categoryEdit;
    private EditText descriptionEdit;
    private Button createDanceButton;

    public CreateDanceFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_dance, null);

        nameEdit = view.findViewById(R.id.fcd_name_edit);
        categoryEdit = view.findViewById(R.id.fcd_category_edit);
        descriptionEdit = view.findViewById(R.id.fcd_description_edit);

        createDanceButton = view.findViewById(R.id.fcd_create_dance_btn);
        createDanceButton.setOnClickListener(this);

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

        String category = categoryEdit.getText().toString();
        if(StringUtils.isEmpty(category)) {
            hasError = true;
            categoryEdit.setError("Please enter a category.");
        }

        if(hasError) {
            return;
        }

        String description = descriptionEdit.getText().toString();

        Dance newDance = new Dance(0, name, category, description);

        final CreateDanceFragment fragment = this;

        DanceDBTasks.insertDance(getContext(), new Consumer<Long>() {
            @Override
            public void accept(Long aLong) {
                Intent intent = new Intent();
                intent.putExtra("id", aLong);
                fragment.getActivity().setResult(Activity.RESULT_OK, intent);
                fragment.getActivity().finish();
            }
        }, newDance);

    }
}
