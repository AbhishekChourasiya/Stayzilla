package com.example.computers.verify;

/**
 * Created by Aman on 1/11/2015.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    Context context;
    List<RowItem> rowItems;

    CustomAdapter(Context context, List<RowItem> rowItems) {
        this.context = context;
        this.rowItems = rowItems;
    }

    @Override
    public int getCount() {
        return rowItems.size();
    }


    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }

    /* private view holder class */
    private class ViewHolder {
        ImageView profile_pic;
        TextView member_name;
        TextView status;
        TextView contactType;
        TextView id_rating;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
       // if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();

            holder.member_name = (TextView) convertView
                    .findViewById(R.id.iddpname);
            holder.profile_pic = (ImageView) convertView
                    .findViewById(R.id.profile_pic);
            holder.id_rating = (TextView) convertView
                    .findViewById(R.id.idrating);
            holder.status = (TextView) convertView.findViewById(R.id.idloc);
            holder.contactType = (TextView) convertView
                    .findViewById(R.id.idprice);

            RowItem row_pos = rowItems.get(position);
           // int p=rowItems.get(position).getPosition();

            holder.profile_pic.setImageBitmap(row_pos.getProfile_pic_id());
            holder.member_name.setText(row_pos.getDisplayName());
            holder.status.setText(row_pos.getAddress());
            holder.contactType.setText(row_pos.getPrice());
            holder.id_rating.setText(row_pos.getStarRating());
            convertView.setTag(holder);
      //  } else {
       //     holder = (ViewHolder) convertView.getTag();
       // }

        return convertView;
    }

}
