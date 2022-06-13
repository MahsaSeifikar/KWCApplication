package com.atrin.kwc;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.ExtractedText;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.atrin.jsonclass.DataStorage;
import com.atrin.jsonclass.ProductGroups;
import com.atrin.jsonclass.ProductListOrder;
import com.atrin.task.AtrinTask;
import com.atrin.task.OnTaskCompleted;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by SE7EN on 12/29/2016.
 */
public class ListOrderdActivity extends AppCompatActivity implements OnTaskCompleted {


    private Button sendListButton, reOrderButton;
    private TableLayout table;
    private ArrayList<String> cells;
    private int sumOrderPrice = 0;
    private TableRow endrow;
    Typeface tf;
    String language;
    Typeface tfaw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        tf = Typeface.createFromAsset(getAssets(), "fonts/IRANSansMobile.ttf");

        setContentView(R.layout.activity_list_orderd);
        TextView title = (TextView) findViewById(R.id.titleImake_order);
        title.setText(getResources().getString(R.string.list_order_fa));
        title.setTypeface(tf);

        language = getIntent().getStringExtra("language");

        findViewById(R.id.toolbarLeftIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ListOrderdActivity.this, HomeActivity.class);
                i.putExtra("language", language);
                startActivity(i);
                finish();
            }
        });

         tfaw = Typeface.createFromAsset(getAssets(), "fonts/fontawesome.ttf");

        TextView back = (TextView) findViewById(R.id.back);
        back.setTypeface(tfaw);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(DataStorage.rowNumber != 0){
                    OrderManagment.textview_numberOrder.setText(DataStorage.rowNumber+" ");
                }else{
                    OrderManagment.textview_numberOrder.setText(" ");

                }
                onBackPressed();
            }
        });
        cells = new ArrayList<>();

        table = (TableLayout)  findViewById(R.id.orderList_table);
        TextView view = (TextView) findViewById(R.id.listOrderd_textview) ;
        view.setTypeface(tf);

        sendListButton = (Button)  findViewById(R.id.sendlistOrderbutton);
        sendListButton.setTypeface(tf);
        reOrderButton = (Button)  findViewById(R.id.reOrderButton);
        reOrderButton.setTypeface(tf);
        sendListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(DataStorage.orderedProductVNum.size() > 0 && DataStorage.orderedProductNumber.size() > 0){
                    String sumVnum = "", dumVcount = "";
                    for(int i= 0 ; i<DataStorage.orderedProductNumber.size(); i++){
                        sumVnum += DataStorage.orderedProductVNum.get(i);
                        dumVcount += DataStorage.orderedProductNumber.get(i);
                        if(i != DataStorage.orderedProductNumber.size()-1){
                            sumVnum += ",";
                            dumVcount += ",";
                        }
                    }
                    DataStorage.product_order_count = dumVcount;
                    DataStorage.product_order_number = sumVnum;

                    call(14);
                }
            }
        });

        reOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ListOrderdActivity.this, MakeOrderActivity.class);
                        intent.putExtra("language",language);
                        intent.putExtra("tittle", DataStorage.logInInformation.get(0).getVName()+" | "+
                                DataStorage.logInInformation.get(0).getUserType());
                        startActivity(intent);
                finish();
            }
        });
        if(!DataStorage.orderedProductName.isEmpty()) {
            sumOrderPrice = 0;
            for (int i = -1; i < DataStorage.orderedProductName.size(); i++) {
                if (i == -1) {
                    cells.add(getResources().getString(R.string.price_totali_fa));
                    cells.add(getResources().getString(R.string.product_number_fa));
                    cells.add(getResources().getString(R.string.price_one_fa));
                    cells.add(getResources().getString(R.string.name_pruduct_fa));
                } else {

                    cells.add((Integer.parseInt(DataStorage.orderedProductNumber.get(i)) *
                            Integer.parseInt(DataStorage.orderedProductPriceTxt.get(i))) + "");
                    cells.add(DataStorage.orderedProductNumber.get(i));
                    cells.add(DataStorage.orderedProductPriceTxt.get(i));
                    cells.add(DataStorage.orderedProductName.get(i));

                    sumOrderPrice += (Integer.parseInt(DataStorage.orderedProductNumber.get(i)) *
                            Integer.parseInt(DataStorage.orderedProductPriceTxt.get(i)));
                }
            }
            createTable(DataStorage.orderedProductName.size()+1, cells);
        }else{
            view.setText(getResources().getString(R.string.text_listorderd));
        }
    }

    /**
     *
     * @param rows
     */
    private void createTable(int rows, ArrayList<String> cells){

        if(endrow != null) {
            table.removeView(endrow);
        }

        int position = 0;
        for (int i = 1; i <= rows+1; i++) {


            // inner for loop
            if (i != rows+1){
                TableRow row = new TableRow(this);
                row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                for (int j = 1; j <= 5; j++) {


                    TextView tv = new TextView(this);
                    tv.setTypeface(tf);

//                    tv.setBackground(getResources().getDrawable(R.drawable.table_cell));
                    tv.setGravity(Gravity.CENTER);
                    tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));


                    if(j==5 ){
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textSizeMedium));
                        tv.setText(getResources().getString(R.string.det));
                        tv.setPadding(2, 2, 2, 2);
                        tv.setTypeface(tfaw);
                        if(i>1){
                            tv.setTextColor(getResources().getColor(R.color.kwc_red));
                            tv.setHint(""+i);
//                            Toast.makeText(getApplication(), " "+ tv.getHint(),Toast.LENGTH_SHORT).show();

                        }else {
                            tv.setTextColor(getResources().getColor(R.color.kwc_gray_background));
                        }
                        final int index = i;

                        tv.setOnClickListener(new View.OnClickListener() {
                            int nn;
                            @Override
                            public void onClick(View v) {
                                //Login
                                TextView s = (TextView) v;
                                nn = Integer.parseInt(s.getHint().toString());
                                nn-=2;
//                                Toast.makeText(getApplication(), " "+ nn,Toast.LENGTH_SHORT).show();
                                DataStorage.orderedProductNumber.remove(nn);
                                DataStorage.orderedProductPriceTxt.remove(nn);
                                DataStorage.orderedProductName.remove(nn);
                                startActivity(getIntent());
                                finish();
                            }
                        });
                    }else {

                        tv.setBackgroundResource(R.drawable.table_cell);
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textSizeMSmall));
                        tv.setText(cells.get(position++));
                        tv.setPadding(1, 1, 1, 1);
                    }
                    row.addView(tv);

                }
                table.addView(row);

            }else{

                endrow = new TableRow(this);
                endrow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                TextView tv = new TextView(this);
                tv.setTypeface(tf);
//                tv.setBackground(getResources().getDrawable(R.drawable.table_cell));

                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textSizeMSmall));

                tv.setGravity(Gravity.CENTER);
                tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv.setBackgroundResource(R.drawable.table_cell);
                tv.setPadding(1, 1, 1, 1);
                tv.setText(sumOrderPrice + "");
                endrow.addView(tv);

                tv = new TextView(this);
                tv.setTypeface(tf);
                tv.setGravity(Gravity.CENTER);
                tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));

                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textSizeMSmall));
                tv.setBackgroundResource(R.drawable.table_cell);
                tv.setPadding(1, 1, 1, 1);
                tv.setText(getResources().getString(R.string.sumation));
                endrow.addView(tv);

                table.addView(endrow);
            }
        }

    }

    /**
     *
     * @param num
     */
    private void call(int num){
        new AtrinTask(ListOrderdActivity.this, num, tf ,this, true, language).execute();
    }

    /**
     *
     * @param result
     */
    @Override
    public void onTaskCompleted(boolean result) {

        if(!result){
            finish();
        }
        if(DataStorage.saveOrderCollection != null &&!DataStorage.saveOrderCollection.isEmpty()) {

            int i = 0;

            final String message =DataStorage.saveOrderCollection.get(0).getMessage();
            final String factorNumber =DataStorage.saveOrderCollection.get(0).getFactorNumber();
            if (message.equals("ok")){
                final Dialog dialog = new Dialog(ListOrderdActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_dactor);

                TextView messageTextView = (TextView) dialog.findViewById(R.id.message_textview);
                TextView factorMessageTextView = (TextView) dialog.findViewById(R.id.factor_message_textview);
                TextView factorNumTextView = (TextView) dialog.findViewById(R.id.factor_number_message_textview);
                messageTextView.setTypeface(tf);
                factorMessageTextView.setTypeface(tf);
                factorNumTextView.setTypeface(tf);
                factorNumTextView.setText(factorNumber);

                Button retButton = (Button) dialog.findViewById(R.id.retun_dialog);
                retButton.setTypeface(tf);
                retButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        table.removeAllViews();
                        DataStorage.rowNumber = 0;
                        DataStorage.orderedProductName.clear();
                        DataStorage.orderedProductNumber.clear();;
                        DataStorage.orderedProductVNum.clear();;
                        DataStorage.orderedProductPriceTxt.clear();;
                        DataStorage.product_order_number = "";
                        DataStorage.product_order_count = "";

                        dialog.dismiss();
                        Intent intent = new Intent(ListOrderdActivity.this, MakeOrderActivity.class);
                        intent.putExtra("language",language);
                        intent.putExtra("tittle", DataStorage.logInInformation.get(0).getVName()+" | "+
                                DataStorage.logInInformation.get(0).getUserType());
                        startActivity(intent);
                        finish();
                    }
                });
                dialog.show();
                sumOrderPrice = 0;
            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}

