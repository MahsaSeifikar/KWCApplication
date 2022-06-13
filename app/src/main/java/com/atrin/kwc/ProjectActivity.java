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
import android.widget.TextView;

import com.atrin.jsonclass.DataStorage;
import com.atrin.jsonclass.Project;
import com.atrin.task.AtrinTask;
import com.atrin.task.OnTaskCompleted;
import com.squareup.picasso.Picasso;

/**
 * Created by Mahsa on 6/9/2016.
 */
public class ProjectActivity extends MainActivity  implements OnTaskCompleted{

    private View projectView;
    String[] projectsName;
    String[] projectsPicture;
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainLinearLayout.setTag(ProjectActivity.class);


        title_imageView.setVisibility(View.INVISIBLE);
        if(global_language.equals("FA")){
            title_textView.setText(R.string.project_FA);
        }else {
            title_textView.setText(R.string.project_EN);
        }

        LayoutInflater inflater = getLayoutInflater();
        projectView = inflater.inflate(R.layout.activity_project, mainLinearLayout, true);

        new AtrinTask(ProjectActivity.this, 0, tf ,this, true, global_language).execute();
        gridView = (GridView) projectView.findViewById(R.id.projectsGridview);

    }

    public void onTaskCompleted(boolean result){

        if(!result){
            finish();
        }
        if( DataStorage.projectCollection != null &&!DataStorage.projectCollection.isEmpty()) {

            int i = 0;
            projectsName = new String[DataStorage.projectCollection.size()];
            projectsPicture = new String[DataStorage.projectCollection.size()];

            for(Project p : DataStorage.projectCollection){
                projectsName[i] = p.getTitle();
                projectsPicture[i] = p.getGalleryPix();
                i++;

            }
            gridView.setAdapter(new ProjectAdapter(this, projectsPicture, projectsName));
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent = new Intent(ProjectActivity.this, ProjectPictureActivity.class);
                    intent.putExtra("pic", DataStorage.projectCollection.get(position).getGalleryPix());
                    intent.putExtra("title", DataStorage.projectCollection.get(position).getTitle());
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
                v = mInflater.inflate(R.layout.item_project, viewGroup, false);
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
