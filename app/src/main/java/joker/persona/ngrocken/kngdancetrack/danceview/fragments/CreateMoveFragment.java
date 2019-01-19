package joker.persona.ngrocken.kngdancetrack.danceview.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import joker.persona.ngrocken.kngdancetrack.R;

public class CreateMoveFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_dance_move, null);
        System.out.println("DSFLSDJLFK :: " + view.toString());
        return view;
    }
}
