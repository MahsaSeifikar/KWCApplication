package com.atrin.kwc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by aida on 5/29/16.
 */
public class PhilosophyActivity extends MainActivity {
    private View philosophyView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainLinearLayout.setTag(PhilosophyActivity.class);

        title_imageView.setVisibility(View.INVISIBLE);
        if(global_language.equals("FA")){
            title_textView.setText(R.string.Philosophy_FA);
        }else {
            title_textView.setText(R.string.Philosophy_EN);
        }
        LayoutInflater inflater = getLayoutInflater();
        philosophyView = inflater.inflate(R.layout.activity_philosophy, mainLinearLayout, true);

        TextView title = (TextView) philosophyView.findViewById(R.id.philosophyTextViewTitle1);
        TextView desc = (TextView) philosophyView.findViewById(R.id.philosophyTextViewDesc1);
        if(global_language.equals("EN")){
            title.setText(R.string.philosophyTitle1EN);
            desc.setText(R.string.philosophyText1EN);
        }else{
            title.setText(R.string.philosophyTitle1FA);
            desc.setText(R.string.philosophyText1FA);
        }
        setTypefaceForAllViews();
        setImages();
    }

    private void setTypefaceForAllViews() {
        ((TextView) philosophyView.findViewById(R.id.philosophyTextViewTitle1)).setTypeface(tf);
        ((TextView) philosophyView.findViewById(R.id.philosophyTextViewDesc1)).setTypeface(tf);
    }

    private void setImages() {
        ImageView img1 = (ImageView) findViewById(R.id.philosophyImageView1);
        Picasso.with(PhilosophyActivity.this).load(getString(R.string.philosophyImgUrl1)).
                placeholder(R.drawable.holder).into(img1);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
