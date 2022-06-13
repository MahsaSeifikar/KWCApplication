package com.atrin.kwc;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.atrin.jsonclass.DataStorage;

/**
 * Created by Mahsa on 11/07/2016.
 */
public class SettingActivity extends MainActivity {

    private View settingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainLinearLayout.setTag(SettingActivity.class);

        title_textView.setVisibility(View.INVISIBLE);

        LayoutInflater inflater = getLayoutInflater();
        settingView = inflater.inflate(R.layout.activity_setting, mainLinearLayout, true);

        TextView update = (TextView) settingView.findViewById(R.id.update);
        TextView call = (TextView) settingView.findViewById(R.id.call);
        TextView version = (TextView) settingView.findViewById(R.id.version);
        TextView logout = (TextView) settingView.findViewById(R.id.logout);

        update.setTypeface(tf);
        call.setTypeface(tf);
        version.setTypeface(tf);
        logout.setTypeface(tf);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Dialog dialog = new Dialog(SettingActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.language_dialog);

                TextView message = (TextView) dialog.findViewById(R.id.question);
                message.setTypeface(tf);

                TextView fButton = (TextView) dialog.findViewById(R.id.yes);
                fButton.setTypeface(tf);
                fButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toolBarIcon.setImageDrawable(getResources().getDrawable(R.drawable.home));

                        DataStorage.log_in = false;
                        dialog.dismiss();
                        finish();
                        startActivity(getIntent());
                    }
                });
                final TextView aButton = (TextView) dialog.findViewById(R.id.No);
                aButton.setTypeface(tf);
                aButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }



}
