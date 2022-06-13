package com.atrin.kwc;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.atrin.jsonclass.DataStorage;
import com.atrin.jsonclass.News;
import com.atrin.jsonclass.Newsletter;
import com.atrin.task.AtrinTask;
import com.atrin.task.OnTaskCompleted;
import com.squareup.picasso.Picasso;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by SE7EN on 31/07/2016.
 */
public class NewLetterActivity extends MainActivity implements OnTaskCompleted {

    private View newsView;
    String[] newstitle;
    String[] newsdate;
    String[] newsPicture;
    String[] newsFile;
    String[] newsText;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainLinearLayout.setTag(NewLetterActivity.class);


        title_imageView.setVisibility(View.INVISIBLE);
        if(global_language.equals("FA")){
            title_textView.setText(R.string.Newletter_Fa);
        }else {
            title_textView.setText(R.string.Newletter_EN);
        }
        LayoutInflater inflater = getLayoutInflater();
        newsView = inflater.inflate(R.layout.activity_newletter, mainLinearLayout, true);

        new AtrinTask(NewLetterActivity.this, 7, tf, this, true, global_language).execute();
        listView = (ListView) findViewById(R.id.listview_newsactivity);

    }

    public void onTaskCompleted(boolean result) {

        if (result && !DataStorage.newsLeterCollection.isEmpty()) {

            int i = 0;
            newsdate = new String[DataStorage.newsLeterCollection.size()];
            newstitle = new String[DataStorage.newsLeterCollection.size()];
            newsText = new String[DataStorage.newsLeterCollection.size()];
            newsPicture = new String[DataStorage.newsLeterCollection.size()];
            newsFile = new String[DataStorage.newsLeterCollection.size()];

            for (Newsletter p : DataStorage.newsLeterCollection) {
                newstitle[i] = p.getTitle().replace("<fontsize=2>", "").replace("</font>","");
                newsdate[i] = p.getDate();
                newsPicture[i] = p.getPicUrl();
                newsFile[i] = p.getFile();
                newsText[i] = p.getText();
                i++;
            }
            listView.setAdapter(new NewsAdapter(this, newsPicture, newstitle, newsdate,  newsText,newsFile));
//            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Intent intent = new Intent(NewLetterActivity.this, NewsDetailActivity.class);
//                    intent.putExtra("number", position);
//                    intent.putExtra("language",global_language);
//                    startActivity(intent);
//                }
//            });
        }
    }


    final class NewsAdapter extends BaseAdapter {

        private final String[] newsUrl;
        private final String[] news_tittle;
        private final String[] news_date;
        private  String[] news_text;
        private final String[] news_file;

        private final LayoutInflater mInflater;

        private Context context;

        public NewsAdapter(Context context, String[] url, String[] title, String[] date, String[] text
                , String[] file) {
            mInflater = LayoutInflater.from(context);
            this.context = context;
            newsUrl = url;
            news_tittle = title;
            news_date = date;
            news_text = text;
            news_file = file;
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
        public View getView(final int i, View view, ViewGroup viewGroup) {
            View v = view;
            ImageView img;
            if (v == null) {
                if(global_language.equals("EN")) {
                    v = mInflater.inflate(R.layout.item_newsletter, viewGroup, false);
                }else{
                    v = mInflater.inflate(R.layout.item_newsletter_fa, viewGroup, false);
                }
                    v.setTag(R.id.newsItemImageView, v.findViewById(R.id.newsItemImageView));

            }

            TextView title = (TextView) v.findViewById(R.id.titleItemTextView);
            news_tittle[i].replace("</font>","");
            news_tittle[i].replace("<fontsize=2>","");
            title.setText(news_tittle[i]);
            title.setTypeface(tf);

            TextView textView = (TextView) v.findViewById(R.id.dateItemTextView);
            textView.setText(news_text[i]);
            textView.setTypeface(tf);


            TextView dl = (TextView) v.findViewById(R.id.download_textview);
            dl.setTypeface(fontawesome);
            Button linearLayout = (Button) v.findViewById(R.id.download_newsletter);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    try {
//                        URL u = new URL(news_file[i]);
//                        HttpURLConnection c = (HttpURLConnection) u.openConnection();
//                        c.setRequestMethod("GET");
//                        c.setDoOutput(true);
//                        c.connect();
//                        FileOutputStream f = new FileOutputStream(new File(Environment.getExternalStorageDirectory() + "/" + "file.pdf"));
//
//
//                        InputStream in = c.getInputStream();
//
//                        byte[] buffer = new byte[1024];
//                        int len1 = 0;
//                        while ((len1 = in.read(buffer)) > 0) {
//                            f.write(buffer, 0, len1);
//                        }
//                        f.close();
//                    }catch (MalformedURLException mue) {
//                        Toast.makeText(context, "malformed", Toast.LENGTH_LONG).show();
//
////                        Log.e("SYNC getUpdate", "malformed url error", mue);
//                    } catch (IOException ioe) {
//                        Toast.makeText(context, "io error", Toast.LENGTH_LONG).show();
//
////                        Log.e("SYNC getUpdate", "io error", ioe);
//                    } catch (SecurityException se) {
//                        Toast.makeText(context, "security error", Toast.LENGTH_LONG).show();
//
////                        Log.e("SYNC getUpdate", "security error", se);
//                    }
//                    try {
//                        URL u = new URL(newsUrl[i]);
//                        InputStream is = u.openStream();
//
//                        DataInputStream dis = new DataInputStream(is);
//
//                        byte[] buffer = new byte[1024];
//                        int length;
//
//                        FileOutputStream fos = new FileOutputStream(new File(Environment.getExternalStorageDirectory() + "/" + "file.pdf"));
//                        while ((length = dis.read(buffer))>0) {
//                            fos.write(buffer, 0, length);
//                        }
//
//                    } catch (MalformedURLException mue) {
//
////                        Log.e("SYNC getUpdate", "malformed url error", mue);
//                    } catch (IOException ioe) {
////                        Log.e("SYNC getUpdate", "io error", ioe);
//                    } catch (SecurityException se) {
//
////                        Log.e("SYNC getUpdate", "security error", se);
//                    }
//                }
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(news_file[i]));
                    startActivity(browserIntent);
                }});

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


