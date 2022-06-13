package com.atrin.kwc;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.atrin.jsonclass.AfterSaleList;
import com.atrin.jsonclass.AfterSaleOstan;
import com.atrin.jsonclass.DataStorage;
import com.atrin.task.AtrinTask;
import com.atrin.task.OnTaskCompleted;

/**
 * Created by Mahsa on 22/07/2016.
 */

public class AfterSalesActivity extends  MainActivity implements OnTaskCompleted{

    private View afterSaleview;
    private  String[] ostanName;
    private Spinner spinner;
    private boolean first = false;
    private ListView list;
    private String[] shopName;
    private String[] Tel;
    private String[] shahr;
    private String[] mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainLinearLayout.setTag(AfterSalesActivity.class);



        LayoutInflater inflater = getLayoutInflater();
        if(global_language.equals("FA")){
            afterSaleview = inflater.inflate(R.layout.activity_aftersale_fa, mainLinearLayout, true);
        }else {
            afterSaleview = inflater.inflate(R.layout.activity_aftersale, mainLinearLayout, true);
        }


        list = (ListView) afterSaleview.findViewById(R.id.list_froshgah);
        spinner = (Spinner) afterSaleview.findViewById(R.id.list_ostan);
        TextView ostan = (TextView) afterSaleview.findViewById(R.id.ostan);
        TextView choose_ostan = (TextView) afterSaleview.findViewById(R.id.choose_ostan);
        choose_ostan.setTypeface(tf);
        ostan.setTypeface(tf);

        title_imageView.setVisibility(View.INVISIBLE);
        if(global_language.equals("FA")){
            title_textView.setText(R.string.afterSale_FA);
            ostan.setText(R.string.Ostan_FA);
            choose_ostan.setText(R.string.choose_ostan_textview_fa);

        }else {
            title_textView.setText(R.string.afterSale_EN);
            ostan.setText(R.string.Ostan_EN);
            choose_ostan.setText(R.string.choose_ostan_textview_en);
        }
        title_textView.setTypeface(tf);
        new AtrinTask(AfterSalesActivity.this, 5, tf ,this, true, global_language).execute();

    }

    @Override
    public void onTaskCompleted(boolean result) {

        if(!result){
            finish();
        }
        if(DataStorage.afterSalesListCollection != null &&!DataStorage.afterSalesListCollection.isEmpty()){
            int i = 0;
            shopName = new String[DataStorage.afterSalesListCollection.size()];
            Tel = new String[DataStorage.afterSalesListCollection.size()];
            shahr = new String[DataStorage.afterSalesListCollection.size()];
            mobile = new String[DataStorage.afterSalesListCollection.size()];

            for(AfterSaleList p : DataStorage.afterSalesListCollection){
                shopName[i] = p.getName();
                Tel[i] = p.getTel();
                shahr[i] = p.getShahr();
                mobile[i] = p.getMobile();
                i++;
            }

            list.setAdapter(new AfterSaleAdapter(this, shopName, mobile, Tel, shahr));
        }
        if( !first && DataStorage.afterSalesOstanCollection != null &&!DataStorage.afterSalesOstanCollection.isEmpty()) {

            first = true;
            int i = 0;
            ostanName = new String[DataStorage.afterSalesOstanCollection.size()];

            for(AfterSaleOstan p : DataStorage.afterSalesOstanCollection){
                ostanName[i] = p.getOstanName();
                i++;
            }
            CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, ostanName);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setAdapter(adapter);
            spinner.setPrompt(ostanName[0]);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    DataStorage.afterSaleNameOstan = ostanName[position];
                    call();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    DataStorage.afterSaleNameOstan = "";

                }
            });
        }
    }

    private void call(){
        new AtrinTask(AfterSalesActivity.this, 6, tf ,this, true, global_language).execute();
    }


    private final class AfterSaleAdapter extends BaseAdapter {


        private  String[] mobile;
        private  String[] shopName;
        private  LayoutInflater mInflater;
        private  String[] Tel;
        private  String[] shahr;
        private Context context;

        public AfterSaleAdapter(Context context, String[] name, String[] mobile, String[] tel,  String[] shahr) {
            mInflater = LayoutInflater.from(context);

            this.context = context;
            shopName = name;
            this.mobile = mobile;
            this.Tel = tel;
            this.shahr = shahr;
        }

        @Override
        public int getCount() {
            return shopName.length;
        }

        @Override
        public String getItem(int i) {
            return shopName[i];
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = view;
//            ImageView img;
            if (v == null) {
                if(global_language.equals("FA")){
                    v = mInflater.inflate(R.layout.item_after_fa, viewGroup, false);
                }else{
                    v = mInflater.inflate(R.layout.item_aftersale_en, viewGroup, false);
                }
//                v.setTag(R.id.aftersaleItemImageView, v.findViewById(R.id.aftersaleItemImageView));

            }

//            img = (ImageView) v.getTag(R.id.aftersaleItemImageView);

//            TextView circle = (TextView) v.findViewById(R.id.circle);
//            circle.setTypeface(fontawesome);

            TextView mobileee = (TextView) v.findViewById(R.id.mobile);
            mobileee.setTypeface(tf);

            TextView telll = (TextView) v.findViewById(R.id.tel);
            telll.setTypeface(tf);

            TextView tel = (TextView) v.findViewById(R.id.TelTextView);
            tel.setText(Tel[i]);
            tel.setTypeface(tf);

            TextView mobileTextview = (TextView) v.findViewById(R.id.mobileTextView);
            mobileTextview.setText(mobile[i]);
            mobileTextview.setTypeface(tf);

            TextView addressTextview = (TextView) v.findViewById(R.id.shahrTextView);
            addressTextview.setText(shahr[i]);
            addressTextview.setTypeface(tf);

            TextView shopTextView = (TextView) v.findViewById(R.id.shopNameTextView);
            shopTextView.setText(shopName[i]);
            shopTextView.setTypeface(tf);

//            Toast.makeText(context, picsUrl[i], Toast.LENGTH_SHORT).show();
//            Picasso.with(context).load(picsUrl[i]).
//                    placeholder(R.drawable.holder).into(img);


            return v;
        }
    }


//    public class SpinerAdapter extends ArrayAdapter<String> {
//
//        private Context context;
//        private String[] titles;
//
//        public SpinerAdapter(Context context, int resourceId, String[] titles) {
//            super(context, resourceId, titles);
//            this.titles = titles;
//            this.context = context;
//
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            LayoutInflater inflater = (LayoutInflater) context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View rowView = inflater.inflate(R.layout.item_spiner, parent, false);
//
//            TextView textView = (TextView) rowView.findViewById(R.id.spiner_item);
//            textView.setText(titles[position]);
//            textView.setTypeface(tf);
//
//
//            return rowView;
//        }
//    }



    public class CustomSpinnerAdapter extends BaseAdapter implements SpinnerAdapter {

        private final Context activity;
        private String[] titles;

        public CustomSpinnerAdapter(Context context, String[] titles) {
            this.titles=titles;
            activity = context;
        }



        public int getCount()
        {
            return titles.length;
        }

        public Object getItem(int i)
        {
            return titles[i];
        }

        public long getItemId(int i)
        {
            return (long)i;
        }



        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            TextView txt = new TextView(AfterSalesActivity.this);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(18);
            txt.setGravity(Gravity.CENTER_VERTICAL);
            txt.setText(titles[position]);
            txt.setTextColor(Color.parseColor("#000000"));
            return  txt;
        }

        public View getView(int i, View view, ViewGroup viewgroup) {
            TextView txt = new TextView(AfterSalesActivity.this);
            txt.setGravity(Gravity.CENTER);
            txt.setPadding(5, 5, 5, 5);
            txt.setTextSize(16);

            txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.down_arrow, 0);
            txt.setCompoundDrawablePadding(5);
//            txt.setCompo
            txt.setText(titles[i]);
            txt.setTypeface(tf);
            txt.setTextColor(Color.parseColor("#000000"));
            return  txt;
        }

    }
}

