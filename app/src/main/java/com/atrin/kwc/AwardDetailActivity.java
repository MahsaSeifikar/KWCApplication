package com.atrin.kwc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.atrin.jsonclass.DataStorage;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mahsa on 6/10/2016.
 */
public class AwardDetailActivity extends AppCompatActivity {

    private View picsView;
    private String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_project_pic);
        language = getIntent().getStringExtra("language");


        String[] awards = getIntent().getStringExtra("pic").split(",");
        Set<String> stringSet = new HashSet<>();
        stringSet.addAll(Arrays.asList(awards));
        awards = new String[stringSet.size()];

        int i = 0;
        for(String s : stringSet){
            awards[i++] = s;
        }


        ImageView toolBarIcon = (ImageView) findViewById(R.id.toolbarLeftIcon);
//        if(DataStorage.log_in) {
//            toolBarIcon.setImageDrawable(getResources().getDrawable(R.drawable.shop));
//        }else {
            toolBarIcon.setImageDrawable(getResources().getDrawable(R.drawable.home));
            toolBarIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(AwardDetailActivity.this, HomeActivity.class);
                    i.putExtra("language", language);
                    startActivity(i);
                    finish();
                }
            });

//        }
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/IRANSansMobile.ttf");
        Typeface tfaw = Typeface.createFromAsset(getAssets(), "fonts/fontawesome.ttf");



        TextView back = (TextView) findViewById(R.id.back);
        back.setTypeface(tfaw);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        TextView title = (TextView) findViewById(R.id.projectSpecificarion);
        title.setTypeface(tf);
        if(language.equals("FA")){
            title.setText(R.string.Award_FA);
        }else{
            title.setText(R.string.Award_EN);
        }

        TextView textView = (TextView) findViewById(R.id.titleproject);
        textView.setTypeface(tf);
        textView.setText(getIntent().getStringExtra("title"));
        ListView listView = (ListView) findViewById(R.id.pic_listview);
        listView.setAdapter(new PicsAdapter(getApplicationContext(), awards));
    }


    final class PicsAdapter extends BaseAdapter {

        private final String[] newsUrl;

        private final LayoutInflater mInflater;

        private Context context;

        public PicsAdapter(Context context, String[] url) {
            mInflater = LayoutInflater.from(context);
            this.context = context;
            newsUrl = url;
        }

        @Override
        public int getCount() {
            return newsUrl.length;
        }

        @Override
        public String getItem(int i) {
            return newsUrl[i];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = view;
            ImageView img;
            if (v == null) {
                v = mInflater.inflate(R.layout.item_pics, viewGroup, false);
                v.setTag(R.id.pic_imageview, v.findViewById(R.id.pic_imageview));

            }

            img = (ImageView) v.getTag(R.id.pic_imageview);

            Picasso.with(context).load(newsUrl[i].split(",")[0]).
                    placeholder(R.drawable.holder).into(img);


            return v;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
