package com.atrin.kwc;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by aida on 5/29/16.
 */
public class CertificatesActivity extends MainActivity {

    private View certificateView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainLinearLayout.setTag(CertificatesActivity.class);


        title_imageView.setVisibility(View.INVISIBLE);
        if(global_language.equals("FA")){
            title_textView.setText(R.string.Certificate_FA);
        }else {
            title_textView.setText(R.string.Certificate_EN);
        }

        LayoutInflater inflater = getLayoutInflater();
        certificateView = inflater.inflate(R.layout.activity_certificates, mainLinearLayout, true);

        String[] certificates = getResources().getStringArray(R.array.certificateUrl);
        GridView gridView = (GridView) certificateView.findViewById(R.id.certificatesGridview);
        gridView.setAdapter(new CertificateAdapter(this, certificates));
    }

    private final class CertificateAdapter extends BaseAdapter {
        private final String[] certificatesUrl;
        private final LayoutInflater mInflater;

        private Context context;

        public CertificateAdapter(Context context, String[] url) {
            mInflater = LayoutInflater.from(context);

            certificatesUrl = url;
            this.context = context;
        }

        @Override
        public int getCount() {
            return certificatesUrl.length;
        }

        @Override
        public String getItem(int i) {
            return certificatesUrl[i];
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
                v = mInflater.inflate(R.layout.item_certificate, viewGroup, false);
                if(i != 9)
                    v.setTag(R.id.certificateItemImageView, v.findViewById(R.id.certificateItemImageView));

            }
            if(i != 9) {
                img = (ImageView) v.getTag(R.id.certificateItemImageView);

                Picasso.with(context).load(certificatesUrl[i]).
                        placeholder(R.drawable.holder).into(img);
            }
            return v;
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
