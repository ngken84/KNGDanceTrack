package joker.persona.ngrocken.kngdancetrack.danceview.fragments;

import android.app.Activity;
import android.content.Intent;
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
import joker.persona.ngrocken.kngdancetrack.database.DanceObjectDBTasks;
import joker.persona.ngrocken.kngdancetrack.model.Dance;
import joker.persona.ngrocken.kngdancetrack.model.Move;
import joker.persona.ngrocken.kngdancetrack.util.DanceConsumer;
import joker.persona.ngrocken.kngdancetrack.util.StringUtils;

public class CreateMoveFragment extends Fragment implements View.OnClickListener {

    private Button createMoveBtn;
    private EditText nameEdtTxt;
    private EditText descEdtTxt;
    private RatingBar rtgBar;

    private Dance dance = null;

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

    public void setDance(Dance dance) {
        this.dance = dance;
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
        if(dance == null) {
            nameEdtTxt.setError("NO DANCE AVAILABLE");
            return;
        }

        String moveName = nameEdtTxt.getText().toString();
        if(StringUtils.isEmpty(moveName)) {
            nameEdtTxt.setError("Please enter a name");
            return;
        }

        String description = descEdtTxt.getText().toString();
        int rating = rtgBar.getNumStars();

        Move move = new Move(0L, moveName, dance.getId(), dance.getName(), new Date(), "");

        final Fragment fragment = this;
        DanceObjectDBTasks.insertDanceMove(getContext(), move, new DanceConsumer<Long>() {
            @Override
            public void consume(Long aLong) {
                Intent intent = new Intent();
                intent.putExtra("id", aLong);
                fragment.getActivity().setResult(Activity.RESULT_OK, intent);
                fragment.getActivity().finish();
            }

            @Override
            public void handleError() {

            }
        });
    }
}
