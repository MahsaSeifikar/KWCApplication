package com.atrin.task;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.atrin.jsonclass.AfterSaleList;
import com.atrin.jsonclass.AfterSaleOstan;
import com.atrin.jsonclass.Company;
import com.atrin.jsonclass.LogIn;
import com.atrin.jsonclass.Newsletter;
import com.atrin.jsonclass.ProductDetails;
import com.atrin.jsonclass.ProductGroups;
import com.atrin.jsonclass.ProductListOrder;
import com.atrin.jsonclass.ProductLists;
import com.atrin.jsonclass.SaleNetworkList;
import com.atrin.jsonclass.SaleNetworkOstan;
import com.atrin.jsonclass.Award;
import com.atrin.jsonclass.DataStorage;
import com.atrin.jsonclass.News;
import com.atrin.jsonclass.Project;
import com.atrin.jsonclass.SaveOrder;
import com.atrin.kwc.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



/**
 * Created by aida on 12/28/15.
 */
public class AtrinTask extends AsyncTask<String, Void, Response> {
    private OnTaskCompleted listener;

    public static final String AtrinURL = "http://www.kwciran.com/webservice/service.asmx/";
    private  Context context;
    boolean isOk = true;
    private int reqType;
    private Typeface ttf;
    private SweetAlertDialog loadingDialog;
    private SweetAlertDialog errorDialog;
    private String errorMsg = null;
    private boolean showLoadingDialog;
    private String language;
    public AtrinTask(Context ctx, int type, Typeface ttf,
                     OnTaskCompleted listener, Boolean showLoadingDialog, String language) {
        context = ctx;
        reqType = type;
        this.ttf = ttf;
        this.listener = listener;
        this.showLoadingDialog = showLoadingDialog;
        this.language = language;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        if ( isNetworkAvailable() ) {

            if ( showLoadingDialog ) {

                loadingDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
                loadingDialog.getProgressHelper().setBarColor(context.getResources().getColor(R.color.kwc_red));
                if(language.equals("EN")){
                    loadingDialog.setContentText(context.getString(R.string.loadingEn));//TitleText(context.getString(R.string.loading));
                }else{
                    loadingDialog.setContentText(context.getString(R.string.loadingFA));//TitleText(context.getString(R.string.loading));

                }

                loadingDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        SweetAlertDialog alertDialog = (SweetAlertDialog) dialog;
                        TextView text = (TextView) alertDialog.findViewById(R.id.content_text);
                        text.setTypeface(ttf);
                        text.setGravity(Gravity.CENTER_HORIZONTAL);
                        alertDialog.findViewById(R.id.title_text).setVisibility(View.GONE);
                    }
                });

                loadingDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        cancel(true);
                    }
                });
                loadingDialog.show();
            }
        }
        else {

            if(language.equals("EN")){
                errorDialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText(context.getString(R.string.errorEn))
                        .setContentText(context.getString(R.string.errorConnectNetEN));
                errorDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        SweetAlertDialog alertDialog = (SweetAlertDialog) dialog;
                        ((Button)alertDialog.findViewById(R.id.confirm_button)).setText(context.getString(R.string.okEn));
                        ((Button)alertDialog.findViewById(R.id.confirm_button)).setTypeface(ttf);
                        ((TextView)alertDialog.findViewById(R.id.title_text)).setTypeface(ttf);
                        ((TextView)alertDialog.findViewById(R.id.content_text)).setTypeface(ttf);
                    }
                });
            }else{
                errorDialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText(context.getString(R.string.errorFA))
                        .setContentText(context.getString(R.string.errorConnectNetFA));
                errorDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        SweetAlertDialog alertDialog = (SweetAlertDialog) dialog;
                        ((Button)alertDialog.findViewById(R.id.confirm_button)).setText(context.getString(R.string.okFA));
                        ((Button)alertDialog.findViewById(R.id.confirm_button)).setTypeface(ttf);
                        ((TextView)alertDialog.findViewById(R.id.title_text)).setTypeface(ttf);
                        ((TextView)alertDialog.findViewById(R.id.content_text)).setTypeface(ttf);
                    }
                });
            }

            errorDialog.show();
            cancel(true);
        }
    }

    @Override
    protected Response doInBackground(String... params) {

        OkHttpClient client = new OkHttpClient();
        RequestBody body;
        String json;
        Request request = null;
        Response response;

        switch (reqType) {


            case 0: //Project
                if(language.equals("EN")) {
                    request = new Request.Builder()
                            .url("http://www.kwciran.com/webservice/service.asmx/Projects_EN?VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                            .build();
                }else{
                    request = new Request.Builder()
                            .url("http://www.kwciran.com/webservice/service.asmx/Projects_FA?VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                            .build();
                }
             break;

            case 1: //News
                if(language.equals("EN")) {
                    request = new Request.Builder()
                            .url("http://www.kwciran.com/webservice/service.asmx/News_EN?VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                            .build();
                }else{
                    request = new Request.Builder()
                            .url("http://www.kwciran.com/webservice/service.asmx/News_FA?VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                            .build();
                }
                break;

            case 2: //Awards
                if(language.equals("EN")) {
                    request = new Request.Builder()
                            .url("http://www.kwciran.com/webservice/service.asmx/Awards_EN?VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                            .build();
                }else{
                    request = new Request.Builder()
                            .url("http://www.kwciran.com/webservice/service.asmx/Awards_FA?VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                            .build();
                }
                break;
            case 3: //saletwork Ostan
                if(language.equals("EN")) {
                    request = new Request.Builder()
                            .url("http://www.kwciran.com/webservice/service.asmx/SalesNetwork_Ostan_EN?VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                            .build();
                }else{
                    request = new Request.Builder()
                            .url("http://www.kwciran.com/webservice/service.asmx/SalesNetwork_Ostan_FA?VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                            .build();
                }
                break;

            case 4: //salenetwork list
                if(language.equals("EN")) {
                    request = new Request.Builder()
                            .url("http://www.kwciran.com/webservice/service.asmx/SalesNetwork_List_EN?OstanName="
                                    +DataStorage.saleNetworkNameOstan+"&VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                            .build();
                }else{
                    request = new Request.Builder()
                            .url("http://www.kwciran.com/webservice/service.asmx/SalesNetwork_List_FA?OstanName="
                                    +DataStorage.saleNetworkNameOstan+"&VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                            .build();
                }
                break;
            case 5: //afterSale Ostan
                if(language.equals("EN")) {
                    request = new Request.Builder()
                            .url("http://www.kwciran.com/webservice/service.asmx/AfterSales_Ostan_EN?VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                            .build();
                }else{
                    request = new Request.Builder()
                            .url("http://www.kwciran.com/webservice/service.asmx/AfterSales_Ostan_FA?VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                            .build();
                }
                break;

            case 6: //afterSale list
                if(language.equals("EN")) {
                    request = new Request.Builder()
                            .url("http://www.kwciran.com/webservice/service.asmx/AfterSales_List_EN?OstanName="
                                    +DataStorage.afterSaleNameOstan+"&VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                            .build();
                }else{
                    request = new Request.Builder()
                            .url("http://www.kwciran.com/webservice/service.asmx/AfterSales_List_FA?OstanName="
                                    +DataStorage.afterSaleNameOstan+"&VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                            .build();
                }
                break;

            case 7: //NewsLetter
                if(language.equals("EN")) {
                    request = new Request.Builder()
                            .url("http://www.kwciran.com/webservice/service.asmx/Newsletter_EN?VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                            .build();
                }else{
                    request = new Request.Builder()
                            .url("http://www.kwciran.com/webservice/service.asmx/Newsletter_FA?&VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                            .build();
                }
                break;

            case 8: //Product Groups
                if(language.equals("EN")) {
                    request = new Request.Builder()
                            .url("http://www.kwciran.com/webservice/service.asmx/Products_Group_EN?VCat="+
                                    DataStorage.category+"&VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                            .build();
                }else{
                    request = new Request.Builder()
                            .url("http://www.kwciran.com/webservice/service.asmx/Products_Group_FA?VCat="+
                                    DataStorage.category+"&VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                            .build();
                }
                break;

            case 9: //Product Lists
                if(language.equals("EN")) {
                    request = new Request.Builder()
                            .url("http://www.kwciran.com/webservice/service.asmx/Products_List_EN?VCat="+
                                    DataStorage.category + " &VGroup="+
                                    DataStorage.productGroupTitle+"&VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                            .build();
                }else{
                    request = new Request.Builder()
                            .url("http://www.kwciran.com/webservice/service.asmx/Products_List_FA?VCat="+
                                    DataStorage.category+ " &VGroup="+
                                    DataStorage.productGroupTitle+"&VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                            .build();
                }
                break;


            case 10: //Product Details

                if(language.equals("EN")) {

                    if(!DataStorage.productListNumber.isEmpty()){

                        request = new Request.Builder()
                                .url("http://www.kwciran.com/webservice/service.asmx/Products_Detail_EN?VNumber="+
                                        DataStorage.productListNumber +"&VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                                .build();
                    }else{
                        request = new Request.Builder()
                                .url("http://www.kwciran.com/webservice/service.asmx/Products_Detail_EN?VNumber="+
                                        DataStorage.productGroupMainNumber +"&VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                                .build();
                    }
                }else{
                    if(!DataStorage.productListNumber.isEmpty()){

                        request = new Request.Builder()
                                .url("http://www.kwciran.com/webservice/service.asmx/Products_Detail_FA?VNumber="+
                                        DataStorage.productListNumber +"&VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                                .build();
                    }else{
                        request = new Request.Builder()
                                .url("http://www.kwciran.com/webservice/service.asmx/Products_Detail_FA?VNumber="+
                                        DataStorage.productGroupMainNumber +"&VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                                .build();
                    }
                }
                break;

            case 11: //Company
                if(language.equals("EN")) {
                    request = new Request.Builder()
                            .url("http://www.kwciran.com/webservice/service.asmx/Company_EN?VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                            .build();
                }else{
                    request = new Request.Builder()
                            .url("http://www.kwciran.com/webservice/service.asmx/Company_FA?&VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                            .build();
                }
                break;

            case 12: //LogIn
                request = new Request.Builder()
                            .url("http://www.kwciran.com/webservice/service.asmx/User_Login?VUsername=" +
                                    DataStorage.userName +
                                    "&VPassword=" +
                                    DataStorage.password +
                                    "&VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                            .build();

                break;

            case 13: //Product Lists Order
                if(language.equals("EN")) {
                    request = new Request.Builder()
                            .url("http://www.kwciran.com/webservice/service.asmx/Products_List_Order_EN?VCat="+
                                    DataStorage.category + " &VGroup="+
                                    DataStorage.productGroupTitle+"&VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                            .build();
                }else{
                    request = new Request.Builder()
                            .url("http://www.kwciran.com/webservice/service.asmx/Products_List_Order_FA?VCat="+
                                    DataStorage.category+ " &VGroup="+
                                    DataStorage.productGroupTitle+"&VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                            .build();
                }
                break;


            case 14: //Product Lists Order
                    request = new Request.Builder()
                            .url("http://www.kwciran.com/webservice/service.asmx/Save_Order?" +
                                    "VUser_Number=" +
                                    DataStorage.log_in_number +
                                    "&VProduct_Numbers=" +
                                    DataStorage.product_order_number +
                                    "&VProduct_Counts=" +
                                    DataStorage.product_order_count +
                                    "&VKey=ndC@yzJ23]18Qw%C2%AB!6V}")
                            .build();
                break;

            default:
                break;
        }



        Gson gson = new Gson();
        try {
                response = client.newCall(request).execute();

            if ( response != null ) {
                if ( response.code() == HttpURLConnection.HTTP_OK ) {
                    if(reqType == 0){
                        Type collectionType = new TypeToken<ArrayList<Project>>(){}.getType();
                        DataStorage.projectCollection =  gson.fromJson(response.body().string(), collectionType);
                    }else if(reqType == 1){
                        Type collectionType = new TypeToken<ArrayList<News>>(){}.getType();
                        DataStorage.newsCollection =  gson.fromJson(response.body().string(), collectionType);
                    }else if(reqType == 2){
                        Type collectionType = new TypeToken<ArrayList<Award>>(){}.getType();
                        DataStorage.awardsCollection =  gson.fromJson(response.body().string(), collectionType);
                    }else if(reqType == 3){
                        Type collectionType = new TypeToken<ArrayList<SaleNetworkOstan>>(){}.getType();
                        DataStorage.salesNetworkOstanCollection =  gson.fromJson(response.body().string(), collectionType);
                    }else if(reqType == 4){
                        Type collectionType = new TypeToken<ArrayList<SaleNetworkList>>(){}.getType();
                        DataStorage.salesNetworkListCollection =  gson.fromJson(response.body().string(), collectionType);
                    }else if(reqType == 5){
                        Type collectionType = new TypeToken<ArrayList<AfterSaleOstan>>(){}.getType();
                        DataStorage.afterSalesOstanCollection =  gson.fromJson(response.body().string(), collectionType);
                    }else if(reqType == 6){
                        Type collectionType = new TypeToken<ArrayList<AfterSaleList>>(){}.getType();
                        DataStorage.afterSalesListCollection =  gson.fromJson(response.body().string(), collectionType);
                    }else if(reqType == 7){
                        Type collectionType = new TypeToken<ArrayList<Newsletter>>(){}.getType();
                        DataStorage.newsLeterCollection =  gson.fromJson(response.body().string(), collectionType);
                    }else if(reqType == 8){
                        Type collectionType = new TypeToken<ArrayList<ProductGroups>>(){}.getType();
                        DataStorage.productGroupsCollection =  gson.fromJson(response.body().string(), collectionType);
                    }else if(reqType == 9){
                        Type collectionType = new TypeToken<ArrayList<ProductLists>>(){}.getType();
                        DataStorage.productListsCollection =  gson.fromJson(response.body().string(), collectionType);
                    }else if(reqType == 10){
                        Type collectionType = new TypeToken<ArrayList<ProductDetails>>(){}.getType();
                        DataStorage.productDetailsCollection =  gson.fromJson(response.body().string(), collectionType);
                    }else if(reqType == 11){
                        Type collectionType = new TypeToken<ArrayList<Company>>(){}.getType();
                        DataStorage.companyCollection =  gson.fromJson(response.body().string(), collectionType);
                    }else if(reqType == 12){
                        Type collectionType = new TypeToken<ArrayList<LogIn>>(){}.getType();
                        DataStorage.logInInformation =  gson.fromJson(response.body().string(), collectionType);
                    }else if(reqType == 13){
                        Type collectionType = new TypeToken<ArrayList<ProductListOrder>>(){}.getType();
                        DataStorage.productListOrdersCollection =  gson.fromJson(response.body().string(), collectionType);
                    }else if(reqType == 14){
                        Type collectionType = new TypeToken<ArrayList<SaveOrder>>(){}.getType();
                        DataStorage.saveOrderCollection =  gson.fromJson(response.body().string(), collectionType);
                    }
                }
                else {
                    switch (response.code()){
                        case HttpURLConnection.HTTP_UNAUTHORIZED:
                            if(language.equals("EN")){
                                errorMsg = context.getString(R.string.error401En);
                            }else{
                                errorMsg = context.getString(R.string.error401FA);
                            }
                            break;
                        case HttpURLConnection.HTTP_NOT_FOUND:

                            if(language.equals("EN")){
                                errorMsg = context.getString(R.string.error404EN);
                            }else{
                                errorMsg = context.getString(R.string.error404FA);
                            }
                            break;
                        case HttpURLConnection.HTTP_INTERNAL_ERROR:

                            if(language.equals("EN")){
                                errorMsg = context.getString(R.string.error500EN);
                            }else{
                                errorMsg = context.getString(R.string.error500FA);
                            }
                            break;
                        default:
                            if(language.equals("EN")){
                                errorMsg = "Code Error "+response.code();
                            }else{
                                errorMsg = "خطای کد  "+response.code();
                            }
                    }
                }
            }
            else {
                if(language.equals("EN")){
                    errorMsg = context.getString(R.string.errorConnectionEN);
                }else{
                    errorMsg = context.getString(R.string.errorConnectionFA);
                }
            }
            if(  errorMsg == null ) {
                if (showLoadingDialog)
                    loadingDialog.dismiss();
            }else{
                if(language.equals("EN")){
                    errorDialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText(context.getString(R.string.errorEn))
                            .setContentText(errorMsg);
                    errorDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                        @Override
                        public void onShow(DialogInterface dialog) {
                            SweetAlertDialog alertDialog = (SweetAlertDialog) dialog;
                            ((Button) alertDialog.findViewById(R.id.confirm_button)).setText(context.getString(R.string.okEn));
                            ((Button) alertDialog.findViewById(R.id.confirm_button)).setTypeface(ttf);
                            ((TextView) alertDialog.findViewById(R.id.title_text)).setTypeface(ttf);
                            ((TextView) alertDialog.findViewById(R.id.content_text)).setTypeface(ttf);
                        }
                    });
                }else{
                    errorDialog = new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText(context.getString(R.string.errorFA))
                            .setContentText(errorMsg);
                    errorDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                        @Override
                        public void onShow(DialogInterface dialog) {
                            SweetAlertDialog alertDialog = (SweetAlertDialog) dialog;
                            ((Button) alertDialog.findViewById(R.id.confirm_button)).setText(context.getString(R.string.okFA));
                            ((Button) alertDialog.findViewById(R.id.confirm_button)).setTypeface(ttf);
                            ((TextView) alertDialog.findViewById(R.id.title_text)).setTypeface(ttf);
                            ((TextView) alertDialog.findViewById(R.id.content_text)).setTypeface(ttf);
                        }
                    });
                }

                errorDialog.show();
                cancel(true);
                isOk = false;
            }


            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }




    @Override
    protected void onPostExecute(Response result) {
        super.onPostExecute(result);
        listener.onTaskCompleted(isOk);
    }

    public void dismissLoadingDialog() {
        if( loadingDialog != null )
            if( loadingDialog.isShowing() )
                loadingDialog.dismiss();
    }

    public String getErrorMsg() {
        return  errorMsg;
    }

    private boolean isNetworkAvailable() {

            ConnectivityManager connectivityManager
                    = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}

