package com.atrin.kwc;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by aida on 2/21/16.
 */
public class SideBarAdapter extends ArrayAdapter<String> {

    private Context context;
    private String[] titles;
    private String [] icons;
    private int resourceId;
    private Typeface tf, tfIcon;

    public SideBarAdapter(Context context, int resourceId, String[] titles, String[] icons, Typeface tf, Typeface tfIcon) {
        super(context, resourceId, titles);
        this.titles = titles;
        this.icons = icons;
        this.context = context;
        this.resourceId = resourceId;
        this.tf = tf;
        this.tfIcon = tfIcon;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(resourceId, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.sidebarItemTextView);
        textView.setText(titles[position]);
        textView.setTypeface(tf);

        TextView textViewIcon = (TextView) rowView.findViewById(R.id.sidebarItemTextViewIcon);
        textViewIcon.setText(icons[position]);
        textViewIcon.setTypeface(tfIcon);

        return rowView;
    }
}
