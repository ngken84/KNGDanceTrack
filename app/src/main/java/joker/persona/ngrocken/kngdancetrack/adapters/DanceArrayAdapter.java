package joker.persona.ngrocken.kngdancetrack.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import joker.persona.ngrocken.kngdancetrack.model.Dance;

public class DanceArrayAdapter extends ArrayAdapter<Dance> {


    public DanceArrayAdapter(@NonNull Context context, int resource, @NonNull List<Dance> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            convertView = View.inflate(getContext(), android.R.layout.simple_list_item_1, null);
        }
        TextView view = convertView.findViewById(android.R.id.text1);
        view.setText(getItem(position).getName());
        return convertView;
    }
}
