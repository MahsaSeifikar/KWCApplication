package com.atrin.kwc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.atrin.jsonclass.DataStorage;
import com.atrin.jsonclass.News;
import com.atrin.jsonclass.Project;
import com.atrin.task.AtrinTask;
import com.atrin.task.OnTaskCompleted;
import com.squareup.picasso.Picasso;

/**
 * Created by Mahsa on 6/10/2016.
 */
public class NewsActivity extends MainActivity implements OnTaskCompleted {

    private View newsView;
    String[] newstitle;
    String[] newsdate;
    String[] newsPicture;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainLinearLayout.setTag(NewsActivity.class);


        title_imageView.setVisibility(View.INVISIBLE);
        if(global_language.equals("FA")){
            title_textView.setText(R.string.News_FA);
        }else {
            title_textView.setText(R.string.News_EN);
        }
        LayoutInflater inflater = getLayoutInflater();
        newsView = inflater.inflate(R.layout.activity_news, mainLinearLayout, true);

        new AtrinTask(NewsActivity.this, 1, tf, this, true, global_language).execute();
        listView = (ListView) findViewById(R.id.listview_newsactivity);

    }

    public void onTaskCompleted(boolean result) {

        if (result && !DataStorage.newsCollection.isEmpty()) {

            int i = 0;
            newsdate = new String[DataStorage.newsCollection.size()];
            newstitle = new String[DataStorage.newsCollection.size()];
            newsPicture = new String[DataStorage.newsCollection.size()];

            for (News p : DataStorage.newsCollection) {
                newstitle[i] = p.getTitle().replace("<fontsize=2>", "").replace("<font size=2>", "").replace("</font>","");
                newsdate[i] = p.getDate();
                newsPicture[i] = p.getPicUrlB();
                i++;

            }
            listView.setAdapter(new NewsAdapter(this, newsPicture, newstitle, newsdate));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(NewsActivity.this, NewsDetailActivity.class);
                    intent.putExtra("number", position);
                    intent.putExtra("language",global_language);
                    startActivity(intent);
                }
            });
        }
    }


    final class NewsAdapter extends BaseAdapter {

        private final String[] newsUrl;
        private final String[] news_tittle;
        private final String[] news_date;

        private final LayoutInflater mInflater;

        private Context context;

        public NewsAdapter(Context context, String[] url, String[] title, String[] date) {
            mInflater = LayoutInflater.from(context);
            this.context = context;
            newsUrl = url;
            news_tittle = title;
            news_date = date;
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
                v = mInflater.inflate(R.layout.item_news, viewGroup, false);
                v.setTag(R.id.newsItemImageView, v.findViewById(R.id.newsItemImageView));

            }

            TextView title = (TextView) v.findViewById(R.id.titleItemTextView);
            news_tittle[i].replace("</font>"," ");
            news_tittle[i].replace("<fontsize=2>"," ");
            title.setText(news_tittle[i].split("<b>")[1].split("</b>")[0]);
            title.setTypeface(tf);

            TextView textView = (TextView) v.findViewById(R.id.dateItemTextView);
            textView.setText(news_date[i]);
            textView.setTypeface(tf);

            img = (ImageView) v.getTag(R.id.newsItemImageView);

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


