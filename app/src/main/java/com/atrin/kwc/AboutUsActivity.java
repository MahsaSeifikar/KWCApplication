package com.atrin.kwc;

import android.graphics.Paint;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.atrin.jsonclass.DataStorage;
import com.atrin.jsonclass.Newsletter;
import com.atrin.task.AtrinTask;
import com.atrin.task.OnTaskCompleted;
import com.atrin.tool.JustifiedTextView;
import com.squareup.picasso.Picasso;

/**
 * Created by aida on 5/28/16.
 */
public class AboutUsActivity extends MainActivity implements OnTaskCompleted {

    private View aboutUsView;
    TextView t1, t2;
    TextView d1,d2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainLinearLayout.setTag(AboutUsActivity.class);

        title_imageView.setVisibility(View.INVISIBLE);

        LayoutInflater inflater = getLayoutInflater();
        aboutUsView = inflater.inflate(R.layout.activity_about_us, mainLinearLayout, true);


        if(global_language.equals("FA")){
            title_textView.setText(R.string.About_us_FA);
            t1 = (TextView) aboutUsView.findViewById(R.id.aboutUsTextViewTitle1);
            t2 = (TextView) aboutUsView.findViewById(R.id.aboutUsTextViewTitle2);
            d1 = (TextView) aboutUsView.findViewById(R.id.aboutUsTextViewDesc1);
            d2 = (TextView) aboutUsView.findViewById(R.id.aboutUsTextViewDesc2);

        }else {
            title_textView.setText(R.string.About_us_EN);
            t2 = (TextView) aboutUsView.findViewById(R.id.aboutUsTextViewTitle1);
            t1 = (TextView) aboutUsView.findViewById(R.id.aboutUsTextViewTitle2);
            d2 = (TextView) aboutUsView.findViewById(R.id.aboutUsTextViewDesc1);
            d1 = (TextView) aboutUsView.findViewById(R.id.aboutUsTextViewDesc2);

        }


        new AtrinTask(AboutUsActivity.this, 11, tf, this, true, global_language).execute();

    }


    public void onTaskCompleted(boolean result) {

        if (result && !DataStorage.companyCollection.isEmpty()) {

            t2.setText(DataStorage.companyCollection.get(0).getTitle());
            t1.setText(DataStorage.companyCollection.get(1).getTitle());
            d2.setText(DataStorage.companyCollection.get(0).getDescription());
            d1.setText(DataStorage.companyCollection.get(1).getDescription());

            setTypefaceForAllViews();
            setImages();
        }
    }
    private void setTypefaceForAllViews() {
        ((TextView) aboutUsView.findViewById(R.id.aboutUsTextViewTitle1)).setTypeface(tf);
        ((TextView) aboutUsView.findViewById(R.id.aboutUsTextViewTitle2)).setTypeface(tf);
        ((TextView) aboutUsView.findViewById(R.id.aboutUsTextViewDesc1)).setTypeface(tf);
        ((TextView) aboutUsView.findViewById(R.id.aboutUsTextViewDesc2)).setTypeface(tf);
    }

    private void setImages() {

        ImageView img1, img2;
        if(global_language.equals("FA")){

            img2 = (ImageView) findViewById(R.id.aboutUsImageView1);
            img1 = (ImageView) findViewById(R.id.aboutUsImageView2);
        }else{

            img1 = (ImageView) findViewById(R.id.aboutUsImageView1);
            img2 = (ImageView) findViewById(R.id.aboutUsImageView2);
        }
        Picasso.with(AboutUsActivity.this).load(DataStorage.companyCollection.get(0).getPicUrl()).
                placeholder(R.drawable.holder).into(img1);

        Picasso.with(AboutUsActivity.this).load(DataStorage.companyCollection.get(1).getPicUrl()).
                placeholder(R.drawable.holder).into(img2);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
