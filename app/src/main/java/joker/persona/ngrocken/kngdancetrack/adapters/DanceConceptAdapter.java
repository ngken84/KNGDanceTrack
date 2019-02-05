package joker.persona.ngrocken.kngdancetrack.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import joker.persona.ngrocken.kngdancetrack.R;
import joker.persona.ngrocken.kngdancetrack.model.DanceConcept;

public class DanceConceptAdapter extends ArrayAdapter<DanceConcept> {

    public DanceConceptAdapter(Context context, List<DanceConcept> objects) {
        super(context, R.layout.listview_dance_concept, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = View.inflate(getContext(), R.layout.listview_dance_concept, null);
        }
        TextView nametv = convertView.findViewById(R.id.lvdc_name);
        nametv.setText(getItem(position).getName());

        return convertView;
    }
}
