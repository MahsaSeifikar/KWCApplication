package com.atrin.kwc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by aida on 5/29/16.
 */
public class AwardsFragment extends Fragment {

    private String awardsUrl;

    // newInstance constructor for creating fragment with arguments
    public static AwardsFragment newInstance(String awardsUrl) {
        AwardsFragment fragmentFirst = new AwardsFragment();
        Bundle args = new Bundle();
        args.putString("awardsUrl", awardsUrl);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        awardsUrl = getArguments().getString("awardsUrl");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_awards, container, false);

        ImageView img = (ImageView) view.findViewById(R.id.awardsFragImageView);
        Picasso.with(getContext()).load(awardsUrl).
                placeholder(R.drawable.holder).into(img);

        return view;
    }
}