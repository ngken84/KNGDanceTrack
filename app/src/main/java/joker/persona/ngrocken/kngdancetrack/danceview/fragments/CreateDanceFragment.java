package joker.persona.ngrocken.kngdancetrack.danceview.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import joker.persona.ngrocken.kngdancetrack.R;

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
                Toast.makeText(getContext(), "DLSKFJSD", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
