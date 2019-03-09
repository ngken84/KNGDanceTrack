package joker.persona.ngrocken.kngdancetrack.danceview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Date;

import joker.persona.ngrocken.kngdancetrack.R;
import joker.persona.ngrocken.kngdancetrack.danceview.fragments.DanceMoveFragment;
import joker.persona.ngrocken.kngdancetrack.database.DanceObjectDBTasks;
import joker.persona.ngrocken.kngdancetrack.database.contracts.NoteContract;
import joker.persona.ngrocken.kngdancetrack.model.DanceNote;
import joker.persona.ngrocken.kngdancetrack.model.Move;
import joker.persona.ngrocken.kngdancetrack.util.ActivityTemplate;
import joker.persona.ngrocken.kngdancetrack.util.DanceConsumer;

public class MoveActivity extends ActivityTemplate {

    private DanceMoveFragment movesFragment;

    private Move danceMove;

    public static final int RESULT_CREATE_NOTE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dance_moves);

        movesFragment = new DanceMoveFragment();

        long id = getIntent().getLongExtra("id", 0);
        if(id != 0) {
            DanceObjectDBTasks.getDanceMoveById(this, id, new DanceConsumer<Move>() {
                @Override
                public void consume(Move move) {
                    danceMove = move;
                    movesFragment.setMove(danceMove);
                }

                @Override
                public void handleError() {

                }
            });
        }

        showFragment(R.id.dance_moves_fragment_container, movesFragment);



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RESULT_CREATE_NOTE:
                handleCreateNoteResult(resultCode, data);
                break;
        }
    }

    private void handleCreateNoteResult(int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            long id = data.getLongExtra("id", 0L);
            String noteText = data.getStringExtra("note");
            if(noteText != null) {
                DanceNote note = new DanceNote(id, danceMove.getId(),
                        NoteContract.NOTE_TYPE_MOVE, noteText, new Date());
                movesFragment.addNote(note);
            }

        }
    }

}
