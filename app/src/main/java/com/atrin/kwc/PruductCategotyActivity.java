package com.atrin.kwc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.atrin.jsonclass.DataStorage;
import com.atrin.jsonclass.ProductGroups;
import com.atrin.jsonclass.Project;
import com.atrin.task.AtrinTask;
import com.atrin.task.OnTaskCompleted;
import com.squareup.picasso.Picasso;

/**
 * Created by SE7EN on 06/08/2016.
 */
public class PruductCategotyActivity extends MainActivity implements OnTaskCompleted {


    private View projectView;
    String[] projectsName;
    String[] projectsPicture;
    String[] mainNumber;

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainLinearLayout.setTag(PruductCategotyActivity.class);


        title_imageView.setVisibility(View.INVISIBLE);
        DataStorage.category = getIntent().getStringExtra("category");
//        if(global_language.equals("FA")){
//        Toast.makeText(getApplication(), getIntent().getStringExtra("name"), Toast.LENGTH_SHORT).show();
            title_textView.setText(getString(getIntent().getIntExtra("name",0)));
//        }else {
//            title_textView.setText(R.string.project_EN);
//        }

        LayoutInflater inflater = getLayoutInflater();
        projectView = inflater.inflate(R.layout.activity_product_group, mainLinearLayout, true);
        new AtrinTask(PruductCategotyActivity.this, 8, tf ,this, true, global_language).execute();
        gridView = (GridView) projectView.findViewById(R.id.projectsGridview);

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
                    Intent intent = new Intent(PruductCategotyActivity.this, ProductListActivity.class);
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
