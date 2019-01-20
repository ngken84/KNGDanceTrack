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
import joker.persona.ngrocken.kngdancetrack.danceview.CreateDanceActivity;
import joker.persona.ngrocken.kngdancetrack.danceview.DanceActivity;
import joker.persona.ngrocken.kngdancetrack.danceview.MoveActivity;

public class DanceViewFragment extends Fragment implements View.OnClickListener {

    private Button goToMoveButton;
    private Button createDanceButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dance, null);

        goToMoveButton = view.findViewById(R.id.fd_goToMovesBtn);
        goToMoveButton.setOnClickListener(this);

        createDanceButton = view.findViewById(R.id.fd_createDanceBtn);
        createDanceButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fd_goToMovesBtn:
                Intent intent = new Intent(getContext(), MoveActivity.class);
                startActivity(intent);
                break;
            case R.id.fd_createDanceBtn:
                Intent intent1 = new Intent(getContext(), CreateDanceActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
