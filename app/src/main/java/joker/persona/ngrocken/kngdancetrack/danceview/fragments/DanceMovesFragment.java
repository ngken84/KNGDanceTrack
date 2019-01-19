package joker.persona.ngrocken.kngdancetrack.danceview.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import joker.persona.ngrocken.kngdancetrack.R;
import joker.persona.ngrocken.kngdancetrack.danceview.CreateMoveActivity;

public class DanceMovesFragment  extends Fragment implements View.OnClickListener {


    private Button createMoveBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dance_moves, null);
        createMoveBtn = view.findViewById(R.id.fdm_createDanceMoveBtn);
        createMoveBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fdm_createDanceMoveBtn:
                Intent intent = new Intent(getContext(), CreateMoveActivity.class);
                startActivity(intent);
                break;
        }
    }
}
