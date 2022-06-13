package com.atrin.kwc;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.atrin.jsonclass.DataStorage;
import com.atrin.task.AtrinTask;
import com.atrin.task.OnTaskCompleted;
import com.squareup.picasso.Picasso;

/**
 * Created by SE7EN on 6/10/2016.
 */
public class NewsDetailActivity extends AppCompatActivity {

    private ListView listView;
    private String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_newsdetail);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/IRANSansMobile.ttf");
        Typeface tfaw = Typeface.createFromAsset(getAssets(), "fonts/fontawesome.ttf");
        language = getIntent().getStringExtra("language");

        ImageView toolBarIcon = (ImageView) findViewById(R.id.toolbarLeftIcon);
//        if(DataStorage.log_in) {
//            toolBarIcon.setImageDrawable(getResources().getDrawable(R.drawable.shop));
//        }else {
            toolBarIcon.setImageDrawable(getResources().getDrawable(R.drawable.home));
            toolBarIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(NewsDetailActivity.this, HomeActivity.class);
                    i.putExtra("language", language);
                    startActivity(i);
                    finish();
                }
            });

//        }

        TextView back = (TextView) findViewById(R.id.back);
        back.setTypeface(tfaw);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        int item = getIntent().getIntExtra("number",0);
        TextView textView = (TextView) findViewById(R.id.titlenews);
        textView.setTypeface(tf);
        if(language.equals("FA")){
            textView.setText(R.string.News_FA);
        }
        String st = DataStorage.newsCollection.get(item).getTitle().replace("<fontsize=2>", "").replace("</font>","").split("<b>")[1]
                .split("</b>")[0];

//        Toast.makeText(getApplicationContext(), "item :"+ item , Toast.LENGTH_SHORT).show();
        TextView title = (TextView)findViewById(R.id.newdetail_title);
        title.setText(st);
        title.setTypeface(tf);

        String info ="";
        for(String s :DataStorage.newsCollection.get(item).getText().split("<br/>") ){
            if(s!="\n")
                info += s + "\n";
        }
        TextView text = (TextView)findViewById(R.id.newdetail_text);
        text.setText(info);
        text.setTypeface(tf);

        ImageView img = (ImageView)findViewById(R.id.newdetailImageview);

        Picasso.with(getApplicationContext()).load(DataStorage.newsCollection.get(item).getPicUrlB()).
                placeholder(R.drawable.holder).into(img);

    }
}
