package joker.persona.ngrocken.kngdancetrack.danceview.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import joker.persona.ngrocken.kngdancetrack.R;

public class IndividualDanceViewFragment extends Fragment {

    private Button createDanceMoveBtn;
    private Button createDrillBtn;
    private Button createTaskBtn;
    private ListView danceListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_individ_dance_view, null);
        return view;
    }
}
