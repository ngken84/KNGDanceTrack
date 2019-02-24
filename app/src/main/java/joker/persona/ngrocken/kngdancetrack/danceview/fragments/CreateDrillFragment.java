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
import android.widget.RatingBar;

import joker.persona.ngrocken.kngdancetrack.R;

public class CreateDrillFragment extends Fragment implements View.OnClickListener {

    private EditText nameEdit;
    private EditText descEdit;
    private EditText durationEdit;
    private RatingBar ratingBar;;
    private Button createDrillBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_drill, null);

        nameEdit = view.findViewById(R.id.fcdl_drillname_edit);
        descEdit = view.findViewById(R.id.fcdl_drilldesc_edit);
        ratingBar = view.findViewById(R.id.fcdl_importance);
        durationEdit = view.findViewById(R.id.fcdl_drill_duration);

        createDrillBtn = view.findViewById(R.id.fcdl_createdrill_btn);
        createDrillBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.fcdl_createdrill_btn:

                break;
        }
    }
}
