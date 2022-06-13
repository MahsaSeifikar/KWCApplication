package com.atrin.kwc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.atrin.jsonclass.DataStorage;
import com.atrin.jsonclass.ProductGroups;
import com.atrin.jsonclass.ProductLists;
import com.atrin.task.AtrinTask;
import com.atrin.task.OnTaskCompleted;
import com.squareup.picasso.Picasso;

/**
 * Created by SE7EN on 06/08/2016.
 */
public class ProductListActivity  extends AppCompatActivity implements OnTaskCompleted {



    String[] projectsName;
    String[] projectsPicture;
    String[] numbers;
    private String global_language;
    GridView gridView;
    ImageView mainImage;
    Typeface tf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tf = Typeface.createFromAsset(getAssets(), "fonts/IRANSansMobile.ttf");
        Typeface tfaw = Typeface.createFromAsset(getAssets(), "fonts/fontawesome.ttf");
        global_language = getIntent().getStringExtra("language");
        setContentView(R.layout.activity_product_list);

        DataStorage.productGroupTitle = getIntent().getStringExtra("title");
        ImageView toolBarIcon = (ImageView) findViewById(R.id.toolbarLeftIcon);
//        if(DataStorage.log_in) {
//            toolBarIcon.setImageDrawable(getResources().getDrawable(R.drawable.shop));
//        }else {
            toolBarIcon.setImageDrawable(getResources().getDrawable(R.drawable.home));
            toolBarIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(ProductListActivity.this, HomeActivity.class);
                    i.putExtra("language", global_language);
                    startActivity(i);
                    finish();
                }
            });

//        }

        TextView back = (TextView) findViewById(R.id.back_product_list);
        back.setTypeface(tfaw);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        TextView title_textView = (TextView) findViewById(R.id.titlenews);
        title_textView.setTypeface(tf);

//        if(global_language.equals("FA")){
        title_textView.setText(getIntent().getStringExtra("title"));
//        }else {
//            title_textView.setText(R.string.project_EN);
//        }


        new AtrinTask(ProductListActivity.this, 9, tf ,this, true, global_language).execute();
        gridView = (GridView) findViewById(R.id.projectsGridview);
//        mainImage = (ImageView) findViewById(R.id.imageView_product_list);
    }

    @Override
    public void onTaskCompleted(boolean result) {

        if(!result){
            finish();
        }
        if( DataStorage.productListsCollection != null &&!DataStorage.productListsCollection.isEmpty()) {

            int i = 0;
            projectsName = new String[DataStorage.productListsCollection.size()];
            projectsPicture = new String[DataStorage.productListsCollection.size()];
            numbers = new String[DataStorage.productListsCollection.size()];
            for(ProductLists p : DataStorage.productListsCollection){
                projectsName[i] = p.getTitle();
                projectsPicture[i] = p.getPicUrl();
                numbers[i] = p.getNumber();
                i++;
            }

            gridView.setAdapter(new ProjectAdapter(this, projectsPicture, projectsName, numbers));
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    DataStorage.productListNumber = numbers[position];
                    Intent intent = new Intent(ProductListActivity.this, ProductDetailActivity.class);
                    intent.putExtra("title", projectsName[position]);
                    intent.putExtra("title", projectsName[position]);
                    intent.putExtra("language", global_language);
                    startActivity(intent);
                }
            });
        }
    }

    private final class ProjectAdapter extends BaseAdapter {
        private final String[] projectsUrl;
        private final String[] projects_tittle;
        private final String[] numbers;

        private final LayoutInflater mInflater;

        private Context context;

        public ProjectAdapter(Context context, String[] url, String[] title, String[] num) {
            mInflater = LayoutInflater.from(context);
            projects_tittle = title;
            projectsUrl = url;
            this.context = context;
            numbers = num;

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
                v = mInflater.inflate(R.layout.item_prouct_group, viewGroup, false);
                v.setTag(R.id.projectItemImageView, v.findViewById(R.id.projectItemImageView));

            }

            TextView textView = (TextView) v.findViewById(R.id.projectItemTextView);
            textView.setText(projects_tittle[i]);

            textView.setTypeface(tf);
            img = (ImageView) v.getTag(R.id.projectItemImageView);

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

