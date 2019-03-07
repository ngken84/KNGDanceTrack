package joker.persona.ngrocken.kngdancetrack.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import joker.persona.ngrocken.kngdancetrack.R;
import joker.persona.ngrocken.kngdancetrack.model.DanceNote;

public class DanceNoteAdapter extends ArrayAdapter<DanceNote> {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");

    public DanceNoteAdapter(@NonNull Context context, @NonNull List<DanceNote> objects) {
        super(context, R.layout.listview_dancenote, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            convertView = View.inflate(getContext(), R.layout.listview_dancenote, null);
        }
        TextView date = convertView.findViewById(R.id.lvdn_dateCreated);
        TextView noteView = convertView.findViewById(R.id.lvdn_note);

        DanceNote note = getItem(position);
        noteView.setText(note.getNote());
        date.setText(dateFormat.format(note.getDateCreated()));

        return convertView;
    }
}
