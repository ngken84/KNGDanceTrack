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

import java.util.Date;

import joker.persona.ngrocken.kngdancetrack.R;
import joker.persona.ngrocken.kngdancetrack.model.Move;
import joker.persona.ngrocken.kngdancetrack.util.StringUtils;

public class CreateMoveFragment extends Fragment implements View.OnClickListener {

    private Button createMoveBtn;
    private EditText nameEdtTxt;
    private EditText descEdtTxt;
    private RatingBar rtgBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_dance_move, null);
        createMoveBtn = view.findViewById(R.id.fcdm_create_btn);
        nameEdtTxt = view.findViewById(R.id.fcdm_name_edit);
        descEdtTxt = view.findViewById(R.id.fcdm_description_edit);
        rtgBar = view.findViewById(R.id.fcdm_rating);

        createMoveBtn.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fcdm_create_btn:
                createMove();
                break;
        }
    }

    private void createMove() {
        String moveName = nameEdtTxt.getText().toString();
        if(StringUtils.isEmpty(moveName)) {
            nameEdtTxt.setError("Please enter a name");
            return;
        }

        String description = descEdtTxt.getText().toString();
        int rating = rtgBar.getNumStars();

        Move newMove = new Move("", moveName,"Test Dance", new Date(), description);
        newMove.setRating(rating);
    }
}
