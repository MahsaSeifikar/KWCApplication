package com.atrin.kwc;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.atrin.jsonclass.DataStorage;

/**
 * Created by SE7EN on 20/09/2016.
 */
public class OrderManagment extends AppCompatActivity {

    private Button orderList, makeList;
    private Typeface tf;
    private String language;
    private String vnum;
    private TextView toolBar, tittle;
    public static TextView textview_numberOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tf = Typeface.createFromAsset(getAssets(), "fonts/IRANSansMobile.ttf");

        setContentView(R.layout.activity_managment);
        TextView title = (TextView) findViewById(R.id.titleImake_order);
        title.setText(getIntent().getStringExtra("tittle"));
        title.setTypeface(tf);
        language = getIntent().getStringExtra("language");


        toolBar = (TextView) findViewById(R.id.toolbarLeftIcon);
        toolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(OrderManagment.this, ListOrderdActivity.class);
                intent.putExtra("language", language);
                intent.putExtra("tittle", DataStorage.logInInformation.get(0).getVName()+" | "+
                        DataStorage.logInInformation.get(0).getUserType());

                startActivity(intent);
                finish();

            }
        });
        Typeface tfaw = Typeface.createFromAsset(getAssets(), "fonts/fontawesome.ttf");

        TextView back = (TextView) findViewById(R.id.back);
        back.setTypeface(tfaw);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        textview_numberOrder = (TextView) findViewById(R.id.textNumOrder);
        textview_numberOrder.setTypeface(tf);
        if(DataStorage.rowNumber != 0){
            textview_numberOrder.setText(DataStorage.rowNumber+" ");
        }
        orderList = (Button) findViewById(R.id.list_order);
        makeList = (Button) findViewById(R.id.make_order);
        orderList.setTypeface(tf);
        makeList.setTypeface(tf);

        orderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderManagment.this, ListOrderdActivity.class);
                intent.putExtra("language", language);
                intent.putExtra("tittle", DataStorage.logInInformation.get(0).getVName()+" | "+
                        DataStorage.logInInformation.get(0).getUserType());

                startActivity(intent);
            }
        });
        makeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderManagment.this, MakeOrderActivity.class);
                intent.putExtra("language", language);
                intent.putExtra("tittle", DataStorage.logInInformation.get(0).getVName()+" | "+
                        DataStorage.logInInformation.get(0).getUserType());
                startActivity(intent);
            }
        });
    }
}
