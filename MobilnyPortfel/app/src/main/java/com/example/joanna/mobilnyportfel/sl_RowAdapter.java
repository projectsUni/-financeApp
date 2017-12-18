package com.example.joanna.mobilnyportfel;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Łukasz on 27.11.2017.
 */

public class sl_RowAdapter extends ArrayAdapter <productRow> {
    Context context;
    int layoutResourceId;
    ArrayList<productRow> data = null;

    public sl_RowAdapter(Context context, int layoutResourceId, ArrayList<productRow> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        productHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new productHolder();
            holder.prName = (TextView)row.findViewById(R.id.productName);
            holder.zlote = (TextView)row.findViewById(R.id.zl);
            holder.grosze = (TextView)row.findViewById(R.id.grosz);

            row.setTag(holder);
        }
        else {
            holder = (productHolder) row.getTag();
        }

        productRow object = data.get(position);
        float fullPrice = object.priceZL + (float)object.priceGR / 100;

        holder.prName.setText(object.prName);
        holder.zlote.setText(Float.toString(fullPrice));
        //holder.grosze.setText(Integer.toString(object.priceGR));


        return row;
    }

    static class productHolder {
        TextView prName;
        TextView zlote;
        TextView grosze;
    }
}
