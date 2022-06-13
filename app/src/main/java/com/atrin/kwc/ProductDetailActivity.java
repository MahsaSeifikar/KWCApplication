package com.atrin.kwc;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.atrin.jsonclass.DataStorage;
import com.atrin.jsonclass.ProductDetails;
import com.atrin.task.AtrinTask;
import com.atrin.task.OnTaskCompleted;
import com.atrin.tool.ColorAdapter;
import com.squareup.picasso.Picasso;

/**
 * Created by SE7EN on 06/08/2016.
 */
public class ProductDetailActivity extends AppCompatActivity implements OnTaskCompleted {

    private TextView price;
    private TextView title;
    private TextView codemahsol;
    private TextView despriction;
    private ImageView detailImage;
    private GridView horizontalListView;
    private String global_language;
    ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/IRANSansMobile.ttf");
        Typeface tfaw = Typeface.createFromAsset(getAssets(), "fonts/fontawesome.ttf");
        global_language = getIntent().getStringExtra("language");

        if(global_language.equals("EN")){
            setContentView(R.layout.activity_product_detail);
        }else{
            setContentView(R.layout.activity_product_detail_fa);
        }
        ImageView toolBarIcon = (ImageView) findViewById(R.id.toolbarLeftIcon);
//        if(DataStorage.log_in) {
//            toolBarIcon.setImageDrawable(getResources().getDrawable(R.drawable.shop));
//        }else {
            toolBarIcon.setImageDrawable(getResources().getDrawable(R.drawable.home));
            toolBarIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(ProductDetailActivity.this, HomeActivity.class);
                    i.putExtra("language", global_language);
                    startActivity(i);
                    finish();
                }
            });

//        }
        TextView back = (TextView) findViewById(R.id.back_product_detail);
        back.setTypeface(tfaw);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        TextView title_textView = (TextView) findViewById(R.id.titlenews);
        title_textView.setTypeface(tf);

        title_textView.setText(DataStorage.productGroupTitle);



        price = (TextView) findViewById(R.id.priceTextView);
        price.setTypeface(tf);
        title = (TextView) findViewById(R.id.title);
        title.setTypeface(tf);
        codemahsol = (TextView) findViewById(R.id.codemahsolTextView);
        codemahsol.setTypeface(tf);
        despriction = (TextView) findViewById(R.id.tozihatTextView);
        despriction.setTypeface(tf);
        ((TextView) findViewById(R.id.codemahsol)).setTypeface(tf);
        ((TextView) findViewById(R.id.price)).setTypeface(tf);
        ((TextView) findViewById(R.id.tozihat)).setTypeface(tf);
        ((TextView) findViewById(R.id.rangbandi)).setTypeface(tf);

        detailImage = (ImageView) findViewById(R.id.datilImageView1);
        horizontalListView = (GridView) findViewById(R.id.colors);


//        scrollView = (ScrollView)findViewById(R.id.scrol);

        ScrollView view = (ScrollView)findViewById(R.id.scrol);
        view.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.requestFocusFromTouch();
                return false;
            }
        });
        new AtrinTask(ProductDetailActivity.this, 10, tf ,this, true, global_language).execute();

    }

    @Override
    public void onTaskCompleted(boolean result) {
        if(!result){
            finish();
        }
        if( DataStorage.productDetailsCollection != null &&!DataStorage.productDetailsCollection.isEmpty()) {

            ProductDetails details = DataStorage.productDetailsCollection.get(0);
            price.setText(details.getPrice());
            title.setText(details.getTitle());
            codemahsol.setText(details.getCode());

            String out = "";
            for (String s : details.getText().split("<br/>")) {
                out += s + "\n";
            }
            despriction.setText(out);
            String[] c = details.getColor().split(",");
//            String ss = "";
//            for(String s:details.getPower().split(",")){
//                ss += s+"\n";
//            }
//            Toast.makeText(getApplicationContext(), details.getColor(), Toast.LENGTH_LONG).show();

            if (!c[0].isEmpty()){
                horizontalListView.setAdapter(new ColorAdapter(ProductDetailActivity.this, c));
            }
            Log.e("URL LAnai", details.getPicUrl());
            Picasso.with(ProductDetailActivity.this).load(details.getPicUrl()).
                    placeholder(R.drawable.holder).into(detailImage);

//            scrollView.scrollTo(detailImage.getScrollX(),detailImage.getScrollY());

        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
