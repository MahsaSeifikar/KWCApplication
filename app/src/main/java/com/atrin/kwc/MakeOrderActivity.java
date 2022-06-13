package com.atrin.kwc;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
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
import java.util.ArrayList;

/**
 * Created by SE7EN on 10/10/2016.
 */
public class MakeOrderActivity extends AppCompatActivity implements OnTaskCompleted {

//    private View makeOrder;
    private TextView categoriTxt, productTittleTxt, productGroupTxt,
            productNumberTxt, productPriceTxt, productPriceWebTxt;
    private Spinner categoriSpinner, productTittleSpinner, productGroupSppiner;
    private int titlePosition=0, GroupPosition =0;
    private CustomSpinnerAdapter emptyadapter;
    private String[] emptyArray;
    private Button addToListButton, listOrderButton;

    private GridView gridView;
    private String productName;
    private EditText enterProductNumber;
    private String[] categoriArray;
    Typeface tf;
    String language;
    private String vnum;
    private TextView toolBar, numOrder_TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // remove focus
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        tf = Typeface.createFromAsset(getAssets(), "fonts/IRANSansMobile.ttf");

        setContentView(R.layout.activity_makeorder);
        TextView title = (TextView) findViewById(R.id.titleImake_order);
        title.setText(getResources().getString(R.string.make_order_fa));
        title.setTypeface(tf);

        enterProductNumber = (EditText) findViewById(R.id.product_number_enter_textview);
        categoriTxt = (TextView) findViewById(R.id.categori_textview);
        productGroupTxt = (TextView) findViewById(R.id.product_group_textview);
        productTittleTxt = (TextView) findViewById(R.id.product_title_textview);
        productPriceTxt = (TextView)  findViewById(R.id.product_price_textview);
        productNumberTxt = (TextView)  findViewById(R.id.product_number_textview);
        productPriceWebTxt = (TextView)  findViewById(R.id.product_price_web_textview);

        language = getIntent().getStringExtra("language");
        enterProductNumber.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_MEDIA_NEXT) {
                    enterProductNumber.clearFocus();

                    return true;
                }
                return false;
            }
        });
        toolBar = (TextView) findViewById(R.id.toolbarLeftIcon);
        toolBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MakeOrderActivity.this, ListOrderdActivity.class);
                intent.putExtra("language", language);
                intent.putExtra("tittle", DataStorage.logInInformation.get(0).getVName()+" | "+
                        DataStorage.logInInformation.get(0).getUserType());

                startActivity(intent);
                finish();

            }
        });
        numOrder_TextView = (TextView) findViewById(R.id.textNumOrder);
        numOrder_TextView.setTypeface(tf);
        if(DataStorage.rowNumber != 0){
            numOrder_TextView.setText(DataStorage.rowNumber+" ");
            OrderManagment.textview_numberOrder.setText(DataStorage.rowNumber+" ");
        }else{
            numOrder_TextView.setText(" ");
            OrderManagment.textview_numberOrder.setText(" ");

        }
        Typeface tfaw = Typeface.createFromAsset(getAssets(), "fonts/fontawesome.ttf");

        TextView back = (TextView) findViewById(R.id.back);
        back.setTypeface(tfaw);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        categoriTxt.setTypeface(tf);
        productGroupTxt.setTypeface(tf);
        productTittleTxt.setTypeface(tf);
        productPriceTxt.setTypeface(tf);
        productNumberTxt.setTypeface(tf);
        productPriceWebTxt.setTypeface(tf);

        categoriSpinner = (Spinner)  findViewById(R.id.categori_spinner);
        productGroupSppiner = (Spinner)  findViewById(R.id.product_group_spinner);
        productTittleSpinner = (Spinner)  findViewById(R.id.product_title_spinner);

        emptyArray = getResources().getStringArray(R.array.empty_makeorder);
        emptyadapter = new CustomSpinnerAdapter(this, emptyArray);

        categoriArray = getResources().getStringArray(R.array.categori_makeorder_fa);
        final CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, categoriArray);
        categoriSpinner.setAdapter(adapter);
        categoriSpinner.setPrompt(categoriArray[0]);
        categoriSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position !=0){
                    enterProductNumber.setText("");
                    DataStorage.category = getResources().getStringArray(R.array.categori_makeorder_en)[position];
                    call(8);
                }
                productName = "";
                productGroupSppiner.setAdapter(emptyadapter);
                productGroupSppiner.setPrompt(emptyArray[0]);
                productPriceWebTxt.setText("");
                productTittleSpinner.setAdapter(emptyadapter);
                productTittleSpinner.setPrompt(emptyArray[0]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        addToListButton = (Button)  findViewById(R.id.addListButton);
        addToListButton.setTypeface(tf);
        listOrderButton = (Button)  findViewById(R.id.listOrderbutton);
        listOrderButton.setTypeface(tf);
        addToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View view = getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

                if(vnum!="" && productName!="" && enterProductNumber.getText().toString() != ""
                        && productPriceWebTxt.getText().toString() !="") {
                    DataStorage.orderedProductVNum.add(vnum);
                    DataStorage.orderedProductNumber.add(enterProductNumber.getText().toString());
                    DataStorage.orderedProductPriceTxt.add(productPriceWebTxt.getText().toString());
                    DataStorage.orderedProductName.add(productName);
                    DataStorage.rowNumber++;

                    if(DataStorage.rowNumber != 0){
                        numOrder_TextView.setText(DataStorage.rowNumber+" ");
                        OrderManagment.textview_numberOrder.setText(DataStorage.rowNumber+" ");
                    }
                    productName = "";
                    vnum = "";
                    enterProductNumber.setCursorVisible(false);
                    categoriSpinner.setAdapter(adapter);
                    categoriSpinner.setPrompt(categoriArray[0]);
                    productGroupSppiner.setAdapter(emptyadapter);
                    productGroupSppiner.setPrompt(emptyArray[0]);
                    productPriceWebTxt.setText("");
                    productTittleSpinner.setAdapter(emptyadapter);
                    productTittleSpinner.setPrompt(emptyArray[0]);
                    enterProductNumber.setText("");
                }
            }
        });

        listOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MakeOrderActivity.this, ListOrderdActivity.class);
                intent.putExtra("language", language);
                intent.putExtra("tittle", DataStorage.logInInformation.get(0).getVName()+" | "+
                        DataStorage.logInInformation.get(0).getUserType());

                startActivity(intent);
                finish();
            }
        });
    }

    /**
     *
     * @param num
     */
    private void call(int num){
        new AtrinTask(MakeOrderActivity.this, num, tf ,this, true, language).execute();
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
        if(GroupPosition==0 && DataStorage.productGroupsCollection != null &&!DataStorage.productGroupsCollection.isEmpty()) {

            int i = 0;

            final String[] productgroupArray = new String[DataStorage.productGroupsCollection.size()+1];
            final String[] mainNumber = new String[DataStorage.productGroupsCollection.size()+1];

            productgroupArray[i] = getResources().getString(R.string.enter_product_group);
            mainNumber[i]="";
            i++;
            for(ProductGroups p : DataStorage.productGroupsCollection){
                productgroupArray[i] = p.getTitle();
                mainNumber[i++] = p.getNumberMain();
            }
            CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, productgroupArray);
            productGroupSppiner.setAdapter(adapter);
            productGroupSppiner.setPrompt(productgroupArray[0]);
            productGroupSppiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

//                    productName = "";
                    GroupPosition = position;
                    if(position !=0){
                        enterProductNumber.setText("");

                        DataStorage.productGroupMainNumber = mainNumber[position];
                        DataStorage.productGroupTitle = productgroupArray[position];
//                        productName += DataStorage.productGroupTitle + "-";
                        call(13);
                        productPriceWebTxt.setText("");
                        productTittleSpinner.setAdapter(emptyadapter);
                        productTittleSpinner.setPrompt(emptyArray[0]);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }

        if(GroupPosition!=0 && DataStorage.productListOrdersCollection != null &&!DataStorage.productListOrdersCollection.isEmpty()) {

            int i = 0;
            final String[] producttitleArray = new String[DataStorage.productListOrdersCollection.size()+1];
            final String[] productPriceArray = new String[DataStorage.productListOrdersCollection.size()+1];
            final String[] productNumberArray = new String[DataStorage.productListOrdersCollection.size()+1];

            producttitleArray[i++] = getResources().getString(R.string.enter_product_title);
            productPriceArray[0]= "";
            for(ProductListOrder p : DataStorage.productListOrdersCollection){
                producttitleArray[i] = p.getTitle();
                productPriceArray[i] = p.getPrice();
                productNumberArray[i] = p.getNumber();
                i++;
            }

            CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, producttitleArray);
            productTittleSpinner.setAdapter(adapter);
            productTittleSpinner.setPrompt(producttitleArray[0]);
            productTittleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    enterProductNumber.setText("");

                    if(position !=0){
                        productPriceWebTxt.setText(productPriceArray[position]);
                        vnum = productNumberArray[position];
                        enterProductNumber.setCursorVisible(true);
                        productName = producttitleArray[position];

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

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
            TextView txt = new TextView(MakeOrderActivity.this);
            txt.setPadding(16, 16, 16, 16);
            txt.setTextSize(18);
            txt.setGravity(Gravity.CENTER_VERTICAL);
            txt.setText(titles[position]);
            txt.setTextColor(Color.parseColor("#000000"));
            return  txt;
        }

        public View getView(int i, View view, ViewGroup viewgroup) {
            TextView txt = new TextView(MakeOrderActivity.this);
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

    private final class MakeOrderAdapter extends BaseAdapter {

        private final ArrayList<String> textList;
        private final LayoutInflater mInflater;
        private Context context;
//        private Typeface tf;

        public MakeOrderAdapter(Context context, ArrayList<String> textList) {
            mInflater = LayoutInflater.from(context);
            this.textList = textList;
            this.context = context;
//            this.tf = tf;
        }
        @Override
        public int getCount() {
            return textList.size();
        }

        @Override
        public String getItem(int i) {
            return textList.get(i);
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
                v = mInflater.inflate(R.layout.item_makeorder, viewGroup, false);
            }

            TextView textView = (TextView) v.findViewById(R.id.cell_textview);
            textView.setText(textList.get(i));

            textView.setTypeface(tf);

            return v;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
