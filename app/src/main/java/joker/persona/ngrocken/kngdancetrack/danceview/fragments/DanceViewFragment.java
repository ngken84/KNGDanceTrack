package joker.persona.ngrocken.kngdancetrack.danceview.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.util.Consumer;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

import joker.persona.ngrocken.kngdancetrack.R;
import joker.persona.ngrocken.kngdancetrack.adapters.DanceArrayAdapter;
import joker.persona.ngrocken.kngdancetrack.danceview.CreateDanceActivity;
import joker.persona.ngrocken.kngdancetrack.danceview.DanceActivity;
import joker.persona.ngrocken.kngdancetrack.danceview.IndividualDanceActivity;
import joker.persona.ngrocken.kngdancetrack.danceview.MoveActivity;
import joker.persona.ngrocken.kngdancetrack.database.DanceDBTasks;
import joker.persona.ngrocken.kngdancetrack.database.contracts.DanceContract;
import joker.persona.ngrocken.kngdancetrack.model.Dance;
import joker.persona.ngrocken.kngdancetrack.util.DanceConsumer;

public class DanceViewFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Button createDanceButton;
    private ListView danceListView;

    DanceArrayAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dance, null);

        createDanceButton = view.findViewById(R.id.fd_createDanceBtn);
        createDanceButton.setOnClickListener(this);

        danceListView = view.findViewById(R.id.fd_danceListView);

        ProgressBar progressBar = new ProgressBar(getContext());
        progressBar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER));

        progressBar.setIndeterminate(true);
        danceListView.setEmptyView(progressBar);

        danceListView.setOnItemClickListener(this);

        DanceDBTasks.getAllDances(getContext(), new DanceConsumer<List<Dance>>() {
            @Override
            public void consume(List<Dance> dances) {
                mAdapter = new DanceArrayAdapter(getContext(), android.R.layout.simple_list_item_1, dances);
                danceListView.setAdapter(mAdapter);
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
            case R.id.fd_createDanceBtn:
                Intent intent1 = new Intent(getContext(), CreateDanceActivity.class);
                getActivity().startActivityForResult(intent1, CreateDanceActivity.CREATE_DANCE);
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Dance dance = mAdapter.getItem(i);
        Intent intent = new Intent(getActivity(), IndividualDanceActivity.class);
        intent.putExtra("danceId", dance.getId());
        Toast.makeText(getContext(), "ID " + dance.getId(), Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }


    public void addDance(Dance dance) {
        mAdapter.add(dance);
    }


}
