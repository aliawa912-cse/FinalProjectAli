package aliawad.finalprojectali.Data;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import aliawad.finalprojectali.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ThingsAdapter extends ArrayAdapter<Thing> {
    public ThingsAdapter(@Nullable Context context) {
        super(context, R.layout.thing_item);
    }

    /**
     * @param position
     * @param convertView
     * @param parent
     * @return
     */

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View vitem = LayoutInflater.from(getContext()).inflate(R.layout.thing_item, parent, false);
        TextView Title = vitem.findViewById(R.id.itmTitle);
        TextView Categrm = vitem.findViewById(R.id.itmcab);
        ImageView Image = vitem.findViewById(R.id.itmimageview);
        TextView Link = vitem.findViewById(R.id.itmLink);
        final Thing thing = getItem(position);


        Title.setText(thing.getTitle());
        Categrm.setText(thing.getTitle());
        Link.setText(thing.getTitle());


        return vitem;
    }






    }






