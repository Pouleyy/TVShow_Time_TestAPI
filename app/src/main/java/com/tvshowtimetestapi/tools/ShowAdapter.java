package com.tvshowtimetestapi.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tvshowtimetestapi.R;
import com.tvshowtimetestapi.classItems.Show;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kévin on 02/04/2016.
 */
public class ShowAdapter extends ArrayAdapter<Show> {

    public ShowAdapter(Context context, ArrayList<Show> objects) {
        super(context,0, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_show,parent,
                    false);
        }
        ShowViewHolder viewHolder = (ShowViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new ShowViewHolder();
            viewHolder.nameShow = (TextView) convertView.findViewById(R.id.nameShow);
            viewHolder.overview = (TextView) convertView.findViewById(R.id.overview);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.imageShow);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ShowViewHolder) convertView.getTag();
        }

        Show item = getItem(position);
        if (item!= null) {
            // My layout has only one TextView
            // do whatever you want with your string and long
            viewHolder.nameShow.setText(String.format("%s", item.getName()));
            viewHolder.overview.setText(String.format("%s", item.getOverview()));
            Picasso.with(getContext()).load(item.getImage()).fit().into(viewHolder.image);
        }

        return convertView;

    }
    private class ShowViewHolder{
        public TextView nameShow;
        public TextView overview;
        public ImageView image;

    }
}
