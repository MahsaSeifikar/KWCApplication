package com.atrin.tool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atrin.kwc.R;
import com.squareup.picasso.Picasso;

/**
 * Created by SE7EN on 07/08/2016.
 */
public class AccAdapter extends ArrayAdapter<String> {

    private LayoutInflater mInflater;
    private Context context;
    private String[] v;

    public AccAdapter(Context context, String[] values) {
        super(context, R.layout.acc_item, values);
        mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = values;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            // Inflate the view since it does not exist
            convertView = mInflater.inflate(R.layout.acc_item, parent, false);

            // Create and save off the holder in the tag so we get quick access to inner fields
            // This must be done for performance reasons
            TextView imageView = (TextView) convertView.findViewById(R.id.acc_item_textview);
            imageView.setText(v[position]);
//            Picasso.with(context).load(v[position]).
//                    placeholder(R.drawable.holder).into(imageView);

        } else {

        }

        // Populate the text

        // Set the color

        return convertView;
    }

}


