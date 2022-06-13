package com.atrin.kwc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.atrin.jsonclass.DataStorage;
import com.atrin.task.AtrinTask;
import com.atrin.task.OnTaskCompleted;

import java.util.ArrayList;


/**
 * Created by Mahsa on 9/18/2016.
 */
public class LogInActivity extends MainActivity implements OnTaskCompleted {

    EditText password;
    EditText username;
    Button enter;
    TextView message, forgot, signup;
    View logInView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainLinearLayout.setTag(LogInActivity.class);
//        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
//        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        title_imageView.setVisibility(View.INVISIBLE);

        LayoutInflater inflater = getLayoutInflater();
        logInView = inflater.inflate(R.layout.log_in, mainLinearLayout, true);
        password = (EditText) logInView.findViewById(R.id.pass_word);
        username = (EditText) logInView.findViewById(R.id.user_name);
        enter = (Button) logInView.findViewById(R.id.enter);
        message = (TextView) logInView.findViewById(R.id.log_in_message);
        forgot = (TextView) logInView.findViewById(R.id.forgot);
        signup = (TextView) logInView.findViewById(R.id.sign_up);

        String forgotString, signupString;

        if(global_language.equals("FA")){
            title_textView.setText(R.string.login_FA);
            forgotString = getResources().getString(R.string.forgot_fa);
            signupString = getResources().getString(R.string.signup_fa);
        }else {
            title_textView.setText(R.string.login_EN);
            message.setText(R.string.log_in_message_en);
            username.setHint(R.string.user_name_en);
            password.setHint(R.string.pass_word_en);
            enter.setText(R.string.enter_en);
            forgotString = getResources().getString(R.string.forgot_en);
            signupString = getResources().getString(R.string.signup_en);
        }


        SpannableString content = new SpannableString(forgotString);
        content.setSpan(new UnderlineSpan(), 0, forgotString.length(), 0);
        forgot.setText(content);

        content = new SpannableString(signupString);
        content.setSpan(new UnderlineSpan(), 0, signupString.length(), 0);
        signup.setText(content);

        message.setTypeface(tf);
        username.setTypeface(tf);
        password.setTypeface(tf);
        enter.setTypeface(tf);
        forgot.setTypeface(tf);
        signup.setTypeface(tf);
        username.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_MEDIA_NEXT) {
                    Selection.setSelection((Editable) password.getText(),password.getSelectionStart());
                    username.clearFocus();

//                    password.setLines(1);
                    password.requestFocus();
//                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.showSoftInput(password, InputMethodManager.SHOW_IMPLICIT);
                    return true;
                }
                return false;
            }
        });
        password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    password.clearFocus();
                    enter.performClick();
                    return true;
                }
                return false;
            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataStorage.userName = username.getText().toString();
                DataStorage.password = password.getText().toString();

                call();

            }
        });


    }


    public void onTaskCompleted(boolean result) {

        if (result && !DataStorage.logInInformation.isEmpty()) {
            if(DataStorage.logInInformation.get(0).getMessage().equals("ok")){
                DataStorage.log_in = true;
//                toolBarIcon.setImageDrawable(getResources().getDrawable(R.drawable.shop));


                DataStorage.rowNumber = 0;
                DataStorage.orderedProductName = new ArrayList<>();
                DataStorage.orderedProductVNum = new ArrayList<>();
                DataStorage.orderedProductPriceTxt = new ArrayList<>();
                DataStorage.orderedProductNumber = new ArrayList<>();
                DataStorage.log_in_number = DataStorage.logInInformation.get(0).getUserNumber();
                Intent intent = new Intent(this, OrderManagment.class);
                intent.putExtra("language", global_language);
                DataStorage.nameCustomer = DataStorage.logInInformation.get(0).getVName();
                intent.putExtra("tittle", DataStorage.logInInformation.get(0).getVName()+" | "+
                        DataStorage.logInInformation.get(0).getUserType());
                startActivity(intent);
                finish();
            }else{
                message.setText(DataStorage.logInInformation.get(0).getMessage());
                message.setTextColor(getResources().getColor(R.color.kwc_red));
                password.setText("");
                username.setText("");
            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }



    private void call(){
        new AtrinTask(LogInActivity.this, 12, tf, this, true, global_language).execute();
    }
}
