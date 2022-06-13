package com.atrin.kwc;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atrin.task.AtrinTask;

/**
 * Created by SE7EN on 6/10/2016.
 */
public class ContactActivity extends MainActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainLinearLayout.setTag(ContactActivity.class);


        title_imageView.setVisibility(View.INVISIBLE);
        if(global_language.equals("FA")){
            title_textView.setText(R.string.ContactUs_FA);
        }else {
            title_textView.setText(R.string.ContactUs_EN);
        }
        LayoutInflater inflater = getLayoutInflater();
        View newsView ;

        if(global_language.equals("FA")){
            newsView = inflater.inflate(R.layout.activity_contact_fa, mainLinearLayout, true);
        }else{
            newsView = inflater.inflate(R.layout.activity_contact, mainLinearLayout, true);
        }

        Typeface font  = Typeface.createFromAsset(getAssets(), "fonts/fontawesome.ttf");;
        ((TextView) newsView.findViewById(R.id.phone)).setTypeface(font);
        ((TextView) newsView.findViewById(R.id.phone2)).setTypeface(font);
        ((TextView) newsView.findViewById(R.id.home)).setTypeface(font);
        ((TextView) newsView.findViewById(R.id.fax)).setTypeface(font);
        ((TextView) newsView.findViewById(R.id.mail)).setTypeface(font);

        RelativeLayout address = (RelativeLayout) newsView.findViewById(R.id.relativeLayout1);
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=35.747457,51.40223"));
                startActivity(intent);
            }
        });

        RelativeLayout phone = (RelativeLayout) newsView.findViewById(R.id.relativeLayout2);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                String str = "982188211750";
                callIntent.setData(Uri.parse("tel:+" + str.toString().trim()));
                startActivity(callIntent);
            }
        });


        RelativeLayout fax = (RelativeLayout) newsView.findViewById(R.id.relativeLayout3);
        fax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                String str = "982188211738";
                callIntent.setData(Uri.parse("tel:+" + str.toString().trim()));
                startActivity(callIntent);
            }
        });


        RelativeLayout other = (RelativeLayout) newsView.findViewById(R.id.relativeLayout4);
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                String str = "982188212900";
                callIntent.setData(Uri.parse("tel:+" + str.toString().trim()));
                startActivity(callIntent);
            }
        });

        RelativeLayout mail = (RelativeLayout) newsView.findViewById(R.id.relativeLayout5);
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(android.content.Intent.ACTION_SEND);
                email.setType("text/plain");
                Uri uri = Uri.parse("info@kwciran.com");
                email.setData(uri);
                startActivity(Intent.createChooser(email, "Send mail..."));

            }
        });

//        ImageView facebook = (ImageView) newsView.findViewById(R.id.facebook);
//        facebook.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://facebook.com/KWCIRAN"));
//                if (browserIntent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(browserIntent);
//                }
//            }
//        });
//        ImageView youtube = (ImageView) newsView.findViewById(R.id.youtube);
//        youtube.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://youtu.be/5PtES4XtrXw"));
//                if (browserIntent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(browserIntent);
//                }
//            }
//        });
//
//        ImageView insta = (ImageView) newsView.findViewById(R.id.insta);
//        insta.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/KWCIRAN/"));
//                if (browserIntent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(browserIntent);
//                }
//            }
//        });
//
//        ImageView ggplus = (ImageView) newsView.findViewById(R.id.ggplus);
//        ggplus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/113395744161205684637"));
//                if (browserIntent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(browserIntent);
//                }
//            }
//        });
//
//        ImageView linkedin = (ImageView) newsView.findViewById(R.id.linkedin);
//        linkedin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/company/kwc-iran"));
//                if (browserIntent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(browserIntent);
//                }
//            }
//        });
//
//        ImageView aparat = (ImageView) newsView.findViewById(R.id.aparat);
//        aparat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.aparat.com/KWCIRAN"));
//                if (browserIntent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(browserIntent);
//                }
//            }
//        });
//
//        ImageView telegram = (ImageView) newsView.findViewById(R.id.telegram);
//        telegram.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://telegram.me/kwciran"));
//                if (browserIntent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(browserIntent);
//                }
//            }
//        });
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
