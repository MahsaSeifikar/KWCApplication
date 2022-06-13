package com.atrin.kwc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.atrin.jsonclass.Award;
import com.atrin.jsonclass.DataStorage;

import com.atrin.task.AtrinTask;
import com.atrin.task.OnTaskCompleted;
import com.squareup.picasso.Picasso;

/**
 * Created by aida on 2/27/16.
 * modify by mahsa on 7/5/16
 */
public class AwardsActivity extends MainActivity implements OnTaskCompleted {

    private View awardsView;
    private String[] awardtitle;
    private String[] awards;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainLinearLayout.setTag(AwardsActivity.class);


        title_imageView.setVisibility(View.INVISIBLE);
        if(global_language.equals("FA")){
            title_textView.setText(R.string.Award_FA);
        }else {
            title_textView.setText(R.string.Award_EN);
        }
        LayoutInflater inflater = getLayoutInflater();
        awardsView = inflater.inflate(R.layout.activity_awards, mainLinearLayout, true);

        new AtrinTask(AwardsActivity.this, 2, tf, this, true, global_language).execute();

        gridView = (GridView) awardsView.findViewById(R.id.awardGridview);
    }


    public void onTaskCompleted(boolean result){

        if(!result){
            finish();
        }
        if( DataStorage.awardsCollection != null &&!DataStorage.awardsCollection.isEmpty()) {

            int i = 0;
            awards = new String[DataStorage.awardsCollection.size()];
            awardtitle = new String[DataStorage.awardsCollection.size()];

            for(Award p : DataStorage.awardsCollection){

                awardtitle[i] = p.getTitle().replace("</br>","").replace("<br>","").replace("</br/>","");

                awards[i] = p.getPicUrlB();
                i++;
            }
            gridView.setAdapter(new AwardAdapter(getApplicationContext(), awards, awardtitle));
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(AwardsActivity.this, AwardDetailActivity.class);
                    intent.putExtra("pic", awards[position]);
                    intent.putExtra("title", awardtitle[position]);
                    intent.putExtra("language", global_language);
                    startActivity(intent);
                }
            });
        }
    }

    private final class AwardAdapter extends BaseAdapter {
        private final String[] projectsUrl;
        private final LayoutInflater mInflater;
        private final String[] awardTitle;
        private Context context;

        public AwardAdapter(Context context, String[] url, String[] at) {
            mInflater = LayoutInflater.from(context);
            projectsUrl = url;
            this.context = context;
            awardTitle = at;
        }

        @Override
        public int getCount() {
            return projectsUrl.length;
        }

        @Override
        public String getItem(int i) {
            return projectsUrl[i];
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
                v = mInflater.inflate(R.layout.item_award, viewGroup, false);
                v.setTag(R.id.AwardItemImageView, v.findViewById(R.id.AwardItemImageView));

            }

            img = (ImageView) v.getTag(R.id.AwardItemImageView);
            TextView title = (TextView) v.findViewById(R.id.awardItemTextView);
            title.setTypeface(tf);
            if(global_language.equals("FA"))
                title.setText(R.string.more_info_fa);
            else
                title.setText(R.string.more_info_en);

            Picasso.with(context).load(projectsUrl[i].split(",")[0]).
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

