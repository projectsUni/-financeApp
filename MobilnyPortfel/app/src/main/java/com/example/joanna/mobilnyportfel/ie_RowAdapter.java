package com.example.joanna.mobilnyportfel;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Łukasz on 13.12.2017.
 */

public class ie_RowAdapter extends ArrayAdapter<productRow> {
    Context context;
    int layoutResourceId;
    ArrayList<productRow> data = null;

    public ie_RowAdapter(Context context, int layoutResourceId, ArrayList<productRow> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ie_RowAdapter.ieHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ie_RowAdapter.ieHolder();
            holder.prName = (TextView)row.findViewById(R.id.productName);
            holder.zlote = (TextView)row.findViewById(R.id.zl);
            holder.grosze = (TextView)row.findViewById(R.id.grosz);
            holder.date = (TextView)row.findViewById(R.id.date);

            row.setTag(holder);
        }
        else {
            holder = (ie_RowAdapter.ieHolder) row.getTag();
        }

        productRow object = data.get(position);
        holder.prName.setText(object.prName);
        holder.zlote.setText(Integer.toString(object.priceZL));
        holder.grosze.setText(Integer.toString(object.priceGR));
        holder.date.setText(object.getDate());

        return row;
    }

    static class ieHolder {
        TextView prName;
        TextView zlote;
        TextView grosze;
        TextView date;
    }
}
