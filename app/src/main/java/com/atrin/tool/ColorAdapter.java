package com.atrin.tool;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.atrin.kwc.R;
import com.squareup.picasso.Picasso;

/**
 * Created by SE7EN on 07/08/2016.
 */
public class ColorAdapter extends ArrayAdapter <String>{

    private LayoutInflater mInflater;
    private  Context context;
    private String[] v;

    public ColorAdapter(Context context, String[] values) {
        super(context, R.layout.item_color, values);
        mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = values;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            // Inflate the view since it does not exist
            convertView = mInflater.inflate(R.layout.item_color, parent, false);

            // Create and save off the holder in the tag so we get quick access to inner fields
            // This must be done for performance reasons
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imagecolor);
            v[position] = v[position].replace("color :","");
//            Toast.makeText(context,"color :" + v[position], Toast.LENGTH_SHORT).show();
            Log.e("COLOR", v[position]);
            Picasso.with(context).load(v[position]).
                    placeholder(R.drawable.holder).into(imageView);

        } else {

        }

        // Populate the text

        // Set the color

        return convertView;
    }

}

