package com.atrin.kwc;

import android.app.Activity;
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
import android.widget.Toast;

import com.atrin.jsonclass.DataStorage;
import com.atrin.jsonclass.ProductGroups;
import com.atrin.task.AtrinTask;
import com.atrin.task.OnTaskCompleted;
import com.atrin.tool.AccAdapter;
import com.atrin.tool.HorizontalListView;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;

/**
 * Created by SE7EN on 07/08/2016.
 */
public class AccessoriActivity extends MainActivity implements OnTaskCompleted {

    private View accView;
    private HorizontalListView listView;
    GridView gridView;
    String[] projectsName;
    String[] projectsPicture;
    String[] mainNumber;
    OnTaskCompleted onTaskCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainLinearLayout.setTag(PruductCategotyActivity.class);


        title_imageView.setVisibility(View.INVISIBLE);
        DataStorage.category = getIntent().getStringExtra("category");
//        if(global_language.equals("FA")){
//        Toast.makeText(getApplication(), getIntent().getStringExtra("name"), Toast.LENGTH_SHORT).show();
        title_textView.setText(getString(getIntent().getIntExtra("name",0)));

        LayoutInflater inflater = getLayoutInflater();
        accView = inflater.inflate(R.layout.activity_accessori, mainLinearLayout, true);
//        listView = (HorizontalListView) accView.findViewById(R.id.accesori_listview);
//        String []a = {"KWC", "Franke", "Oil", "Geesa", "Ideal Standard"};
        final String []cat = {"accessories_kwc", "accessories_franke", "accessories_oli",
                "accessories_geesa", "accessories_idealstandard"};
//        final int []AccessotiFA = {R.string.accessories_kwc_FA, R.string.accessories_franke_FA,
//                R.string.accessories_oli_FA,
//                R.string.accessories_Geesa_FA,R.string.accessories_Ideal_Standard_FA};
//
//        final int []AccessotiEN = {R.string.accessories_kwc_EN, R.string.accessories_franke_EN,
//                R.string.accessories_oli_EN,
//                R.string.accessories_Geesa_EN,R.string.accessories_Ideal_Standard_EN};
//
//        listView.setAdapter(new AccAdapter(getApplicationContext(), a));
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(AccessoriActivity.this, PruductCategotyActivity.class);
//                intent.putExtra("language", global_language);
//                intent.putExtra("category", cat[position]);
//                if(global_language.equals("FA"))
//                    intent.putExtra("name",AccessotiFA[position]);
//                else
//                    intent.putExtra("name",AccessotiEN[position]);
//                startActivity(intent);
//            }
//        });

        gridView = (GridView) findViewById(R.id.projectsGridview);

        DataStorage.category = cat[0];
        onTaskCompleted = this;
        new AtrinTask(AccessoriActivity.this, 8, tf ,this, true, global_language).execute();

        TextView kwc = (TextView) findViewById(R.id.kwc_textview);
        kwc.setTypeface(tf);
        kwc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataStorage.category = cat[0];
                new AtrinTask(AccessoriActivity.this, 8, tf ,onTaskCompleted, true, global_language).execute();

            }
        });

        TextView franke = (TextView) findViewById(R.id.franke_textview);
        franke.setTypeface(tf);
        franke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataStorage.category = cat[1];
                new AtrinTask(AccessoriActivity.this, 8, tf ,onTaskCompleted, true, global_language).execute();

            }
        });

        TextView ooil = (TextView) findViewById(R.id.oil_textview);
        ooil.setTypeface(tf);
        ooil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataStorage.category = cat[2];
                new AtrinTask(AccessoriActivity.this, 8, tf ,onTaskCompleted, true, global_language).execute();

            }
        });
        TextView grs = (TextView) findViewById(R.id.greesal_textview);
        grs.setTypeface(tf);
        grs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataStorage.category = cat[3];
                new AtrinTask(AccessoriActivity.this, 8, tf ,onTaskCompleted, true, global_language).execute();

            }
        });
        TextView idl = (TextView) findViewById(R.id.ideal_textview);
        idl.setTypeface(tf);
        idl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataStorage.category = cat[4];
                new AtrinTask(AccessoriActivity.this, 8, tf ,onTaskCompleted, true, global_language).execute();

            }
        });

    }

    @Override
    public void onTaskCompleted(boolean result) {
        if(!result){
            finish();
        }
        if( DataStorage.productGroupsCollection != null &&!DataStorage.productGroupsCollection.isEmpty()) {

            int i = 0;
            projectsName = new String[DataStorage.productGroupsCollection.size()];
            projectsPicture = new String[DataStorage.productGroupsCollection.size()];
            mainNumber = new String[DataStorage.productGroupsCollection.size()];

            for(ProductGroups p : DataStorage.productGroupsCollection){
                projectsName[i] = p.getTitle();
                projectsPicture[i] = p.getPicUrl();
                mainNumber[i] = p.getNumberMain();
                i++;

            }
            gridView.setAdapter(new ProjectAdapter(this, projectsPicture, projectsName));
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
                    DataStorage.productGroupMainNumber = mainNumber[position];
                    Intent intent = new Intent(AccessoriActivity.this, ProductListActivity.class);
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
        private final LayoutInflater mInflater;

        private Context context;

        public ProjectAdapter(Context context, String[] url, String[] title) {
            mInflater = LayoutInflater.from(context);
            projects_tittle = title;
            projectsUrl = url;
            this.context = context;

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
