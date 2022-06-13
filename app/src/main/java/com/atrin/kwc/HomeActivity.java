package com.atrin.kwc;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by aida on 2/27/16.
 */
public class HomeActivity extends MainActivity {

    private View homeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mainLinearLayout.setTag(HomeActivity.class);

        title_textView.setVisibility(View.INVISIBLE);

        LayoutInflater inflater = getLayoutInflater();
        homeView = inflater.inflate(R.layout.activity_home, mainLinearLayout, true);

        String[] titles;
        if(global_language.equals("FA")){
            titles = getResources().getStringArray(R.array.homeItemsTitlesFa);
        }else{
            titles = getResources().getStringArray(R.array.homeItemsTitlesEn);

        }

        ((TextView) homeView.findViewById(R.id.homeActivityKitchenTextView)).setText(titles[0]);
        ((TextView) homeView.findViewById(R.id.homeActivityKitchenTextView)).setTypeface(tf);
//        ((TextView) homeView.findViewById(R.id.homeActivityKitchenTextViewArrow)).setTypeface(tfIcon);
        ((ImageView) homeView.findViewById(R.id.homeActivityKitchenImageView)).setImageResource(R.drawable.main1);

        ((TextView) homeView.findViewById(R.id.homeActivityBathroomTextView)).setText(titles[1]);
        ((TextView) homeView.findViewById(R.id.homeActivityBathroomTextView)).setTypeface(tf);
//        ((TextView) homeView.findViewById(R.id.homeActivityBathroomTextViewArrow)).setTypeface(tfIcon);
        ((ImageView) homeView.findViewById(R.id.homeActivityBathroomImageView)).setImageResource(R.drawable.main2);

        ((TextView) homeView.findViewById(R.id.homeActivityIntelligentTextView)).setText(titles[2]);
        ((TextView) homeView.findViewById(R.id.homeActivityIntelligentTextView)).setTypeface(tf);
//        ((TextView) homeView.findViewById(R.id.homeActivityIntelligentTextViewArrow)).setTypeface(tfIcon);
        ((ImageView) homeView.findViewById(R.id.homeActivityIntelligentImageView)).setImageResource(R.drawable.main3);

        ((TextView) homeView.findViewById(R.id.homeActivityConcealedTextView)).setText(titles[3]);
        ((TextView) homeView.findViewById(R.id.homeActivityConcealedTextView)).setTypeface(tf);
//        ((TextView) homeView.findViewById(R.id.homeActivityIntelligentTextViewArrow)).setTypeface(tfIcon);
        ((ImageView) homeView.findViewById(R.id.homeActivityConcealedImageView)).setImageResource(R.drawable.main4);

        ((TextView) homeView.findViewById(R.id.homeActivityMultiColorTextView)).setText(titles[4]);
        ((TextView) homeView.findViewById(R.id.homeActivityMultiColorTextView)).setTypeface(tf);
//        ((TextView) homeView.findViewById(R.id.homeActivityIntelligentTextViewArrow)).setTypeface(tfIcon);
        ((ImageView) homeView.findViewById(R.id.homeActivityMultiColorImageView)).setImageResource(R.drawable.main5);

        ((TextView) homeView.findViewById(R.id.homeActivityAccessoriesTextView)).setText(titles[5]);
        ((TextView) homeView.findViewById(R.id.homeActivityAccessoriesTextView)).setTypeface(tf);
//        ((TextView) homeView.findViewById(R.id.homeActivityAccessoriesTextViewArrow)).setTypeface(tfIcon);
        ((ImageView) homeView.findViewById(R.id.homeActivityAccessoriesImageView)).setImageResource(R.drawable.main6);

        ((LinearLayout) homeView.findViewById(R.id.homeActivityKitchenLinear)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PruductCategotyActivity.class);
                intent.putExtra("language", global_language);
                intent.putExtra("category", "kitchen");
                if(global_language.equals("FA"))
                    intent.putExtra("name", R.string.Kitchen_FA);
                else
                    intent.putExtra("name", R.string.Kitchen_EN);
                startActivity(intent);
            }
        });


        ((LinearLayout) homeView.findViewById(R.id.homeActivityBathroomLinear)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PruductCategotyActivity.class);
                intent.putExtra("language", global_language);
                intent.putExtra("category", "bathroom");
                if(global_language.equals("FA"))
                    intent.putExtra("name", R.string.Bathroom_FA);
                else
                    intent.putExtra("name", R.string.Bathroom_EN);
                startActivity(intent);
            }
        });

        ((LinearLayout) homeView.findViewById(R.id.homeActivityIntelligentLinear)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PruductCategotyActivity.class);
                intent.putExtra("language", global_language);
                intent.putExtra("category", "intelligent");
                if(global_language.equals("FA"))
                    intent.putExtra("name", R.string.Intelligent_FA);
                else
                    intent.putExtra("name", R.string.Intelligent_EN);
                startActivity(intent);
            }
        });



        ((LinearLayout) homeView.findViewById(R.id.homeActivityAccessoriesLinear)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AccessoriActivity.class);
                intent.putExtra("language", global_language);
                if(global_language.equals("FA"))
                    intent.putExtra("name", R.string.accessori_FA);
                else
                    intent.putExtra("name", R.string.accessori_EN);
                startActivity(intent);
            }
        });

        ((LinearLayout) homeView.findViewById(R.id.homeActivityConcealedLinear)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PruductCategotyActivity.class);
                intent.putExtra("language", global_language);
                intent.putExtra("category", "concealed");
                if(global_language.equals("FA"))
                    intent.putExtra("name", R.string.Concealed_FA);
                else
                    intent.putExtra("name", R.string.Concealed_EN);
                startActivity(intent);
            }
        });

        ((LinearLayout) homeView.findViewById(R.id.homeActivityMultiColorLinear)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PruductCategotyActivity.class);
                intent.putExtra("language", global_language);
                intent.putExtra("category", "multi_color");
                if(global_language.equals("FA"))
                    intent.putExtra("name", R.string.MultiColor_FA);
                else
                    intent.putExtra("name", R.string.MultiColor_EN);
                startActivity(intent);
            }
        });
    }
}
