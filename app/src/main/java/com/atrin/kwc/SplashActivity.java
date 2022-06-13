package com.atrin.kwc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;


/**
 * Created by aida on 3/7/16.
 */
public class SplashActivity extends Activity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    private String language = "FA";
    private boolean Fa_clicked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        ((Button)findViewById(R.id.fa_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                language = "EN";
                Fa_clicked = true;
                Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                i.putExtra("language", language);
                startActivity(i);
                finish();
            }
        });

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!Fa_clicked) {
                    Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                    i.putExtra("language", language);
                    startActivity(i);
                    finish();
                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
