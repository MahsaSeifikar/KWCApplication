package com.atrin.kwc;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Display;
import android.widget.FrameLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.atrin.jsonclass.DataStorage;

/**
 * Created by aida on 2/8/16.
 */

public class MainActivity extends Activity {

    protected DrawerLayout mDrawerLayout;
    protected ActionBarDrawerToggle drawerToggle;
    protected ListView mDrawerList;
    protected LinearLayout mainLinearLayout;
    protected Typeface tfIcon;
    protected Typeface tf, tn;
    protected Typeface fontawesome;

    protected float rippleSpeed;
    private  SideBarAdapter adapter;
    protected static String global_language;
    private FrameLayout frameLayout;
    protected TextView title_textView;
    protected ImageView title_imageView;
    private boolean productMenu, companyMenu;
    private ImageView navigation;
    private String[] textSlideBar;
    private String[] SlideBar;
    public ImageView toolBarIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main_home);
        navigation = (ImageView) findViewById(R.id.navigation);
        productMenu = companyMenu = false;
        tfIcon = Typeface.createFromAsset(getAssets(), "fonts/Kwc Icons.ttf");
        tf = Typeface.createFromAsset(getAssets(), "fonts/IRANSansMobile.ttf");
        tn = Typeface.createFromAsset(getAssets(), "fonts/BNazanin.ttf");
        fontawesome = Typeface.createFromAsset(getAssets(), "fonts/fontawesome.ttf");

        title_textView = (TextView) findViewById(R.id.title_textview);
        title_textView.setTypeface(tf);
        title_imageView = (ImageView) findViewById(R.id.tittle_imageview);

        global_language = getIntent().getStringExtra("language");
        mainLinearLayout = (LinearLayout) findViewById(R.id.mainLinearLayout);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.navdrawer);
        frameLayout = (FrameLayout) findViewById(R.id.framelayout);


        toolBarIcon = (ImageView) findViewById(R.id.toolbarLeftIcon);
        toolBarIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mainLinearLayout.getTag() != HomeActivity.class) {

                        Intent intent =new Intent(MainActivity.this, HomeActivity.class);
                        intent.putExtra("language", global_language);
                        startActivity(intent);
                    }
                }
            });


        toolBarIcon.setImageDrawable(getResources().getDrawable(R.drawable.home));
        if(global_language.equals("EN")){
                adapter = new SideBarAdapter(this, R.layout.sidebar_list_item,
                        getResources().getStringArray(R.array.sideBarItemTitlesEn),
                        getResources().getStringArray(R.array.sideBarItemIcons), tf, tfIcon);
        }else{
                adapter = new SideBarAdapter(this, R.layout.sidebar_list_item,
                        getResources().getStringArray(R.array.sideBarItemTitlesFA),
                        getResources().getStringArray(R.array.sideBarItemIcons), tf, tfIcon);
        }
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: //Company
                        if ( adapter.getCount() < 10 ) {
                            if(global_language.equals("EN")) {
                                    adapter = new SideBarAdapter(MainActivity.this, R.layout.sidebar_list_item,
                                            getResources().getStringArray(R.array.sideBarItemTitlesCompanyEn),
                                            getResources().getStringArray(R.array.sideBarItemIconsCompayny), tf, tfIcon);

                            }else{
                                    adapter = new SideBarAdapter(MainActivity.this, R.layout.sidebar_list_item,
                                            getResources().getStringArray(R.array.sideBarItemTitlesCompanyFA),
                                            getResources().getStringArray(R.array.sideBarItemIconsCompayny), tf, tfIcon);
                            }
                            companyMenu = true;
                        } else if(adapter.getCount() <17 && adapter.getCount() >= 10 && !companyMenu) {
                            if(global_language.equals("EN")) {
                                    adapter = new SideBarAdapter(MainActivity.this, R.layout.sidebar_list_item,
                                            getResources().getStringArray(R.array.sideBarItemTitlesProductCompanyEn),
                                            getResources().getStringArray(R.array.sideBarItemIconsProductCompany), tf, tfIcon);
                            }else{
                                        adapter = new SideBarAdapter(MainActivity.this, R.layout.sidebar_list_item,
                                                getResources().getStringArray(R.array.sideBarItemTitlesProductCompanyFA),
                                                getResources().getStringArray(R.array.sideBarItemIconsProductCompany), tf, tfIcon);
                            }
                            companyMenu = true;
                        } else{
                            companyMenu = false;
                            if(!productMenu) {
                                if (global_language.equals("EN")) {
                                        adapter = new SideBarAdapter(MainActivity.this, R.layout.sidebar_list_item,
                                                getResources().getStringArray(R.array.sideBarItemTitlesEn),
                                                getResources().getStringArray(R.array.sideBarItemIcons), tf, tfIcon);

                                } else {
                                            adapter = new SideBarAdapter(MainActivity.this, R.layout.sidebar_list_item,
                                                    getResources().getStringArray(R.array.sideBarItemTitlesFA),
                                                    getResources().getStringArray(R.array.sideBarItemIcons), tf, tfIcon);

                                }
                            }else{
                                if(global_language.equals("EN")) {
                                        adapter = new SideBarAdapter(MainActivity.this, R.layout.sidebar_list_item,
                                                getResources().getStringArray(R.array.sideBarItemTitlesProductEn),
                                                getResources().getStringArray(R.array.sideBarItemIconsProduct), tf, tfIcon);

                                }else{
                                        adapter = new SideBarAdapter(MainActivity.this, R.layout.sidebar_list_item,
                                                    getResources().getStringArray(R.array.sideBarItemTitlesProductFA),
                                                    getResources().getStringArray(R.array.sideBarItemIconsProduct), tf, tfIcon);
                                  }
                            }
                        }
                        adapter.notifyDataSetChanged();
                        mDrawerList.setAdapter(adapter);
                        break;

                    case 1:
                        if ( adapter.getCount() < 10 ) { // Product
                            if(global_language.equals("EN")) {
                                  adapter = new SideBarAdapter(MainActivity.this, R.layout.sidebar_list_item,
                                            getResources().getStringArray(R.array.sideBarItemTitlesProductEn),
                                            getResources().getStringArray(R.array.sideBarItemIconsProduct), tf, tfIcon);
                             }else{
                                       adapter = new SideBarAdapter(MainActivity.this, R.layout.sidebar_list_item,
                                                getResources().getStringArray(R.array.sideBarItemTitlesProductFA),
                                                getResources().getStringArray(R.array.sideBarItemIconsProduct), tf, tfIcon);
                            }
                            productMenu = true;
                        }else if(companyMenu) { // About Us
                            if ( mainLinearLayout.getTag() != AboutUsActivity.class ){
                                Intent intent = new Intent(MainActivity.this , AboutUsActivity.class);
                                intent.putExtra("language", global_language);
                                startActivity(intent);
                            }
                        }else{
                            productMenu = false;
                            if(global_language.equals("EN")) {
                                        adapter = new SideBarAdapter(MainActivity.this, R.layout.sidebar_list_item,
                                            getResources().getStringArray(R.array.sideBarItemTitlesEn),
                                            getResources().getStringArray(R.array.sideBarItemIcons), tf, tfIcon);
                            }else{
                                        adapter = new SideBarAdapter(MainActivity.this, R.layout.sidebar_list_item,
                                                getResources().getStringArray(R.array.sideBarItemTitlesFA),
                                                getResources().getStringArray(R.array.sideBarItemIcons), tf, tfIcon);
                            }
                        }

                        adapter.notifyDataSetChanged();
                        mDrawerList.setAdapter(adapter);
                        break;

                    case 2 :
                        if ( adapter.getCount() < 10 ) { // Projects
                            Intent intent = new Intent(MainActivity.this, ProjectActivity.class);
                            intent.putExtra("language", global_language);
                            startActivity(intent);
                        }
                        else if(companyMenu){ // Philosophy
                            if ( mainLinearLayout.getTag() != PhilosophyActivity.class ){
                                Intent intent = new Intent(MainActivity.this, PhilosophyActivity.class);
                                intent.putExtra("language", global_language);
                                startActivity(intent);
                            }

                        }else if(productMenu){ // Kitchen
                            Intent intent = new Intent(MainActivity.this, PruductCategotyActivity.class);
                            intent.putExtra("language", global_language);
                            intent.putExtra("category", "kitchen");
                            if(global_language.equals("FA")) {
                                intent.putExtra("name", R.string.Kitchen_FA);
                            }else {
                                intent.putExtra("name", R.string.Kitchen_EN);
                            }
                            startActivity(intent);
                        }
                        mDrawerLayout.closeDrawer(frameLayout);
                        break;
                    case 3 :
                        if ( adapter.getCount() < 10 ) { // Sales Network
                            Intent intent = new Intent(MainActivity.this, SaleNetworkActivity.class);
                            intent.putExtra("language", global_language);
                            startActivity(intent);
                        }
                        else if(companyMenu) { // Awards
                            if ( mainLinearLayout.getTag() != AwardsActivity.class ) {
                                Intent intent = new Intent(MainActivity.this, AwardsActivity.class);
                                intent.putExtra("language", global_language);
                                startActivity(intent);
                            }
                        }else if(productMenu){ //BathRoom
                            Intent intent = new Intent(MainActivity.this, PruductCategotyActivity.class);
                            intent.putExtra("language", global_language);
                            intent.putExtra("category", "bathroom");
                            if(global_language.equals("FA"))
                                intent.putExtra("name", R.string.Bathroom_FA);
                            else
                                intent.putExtra("name", R.string.Bathroom_EN);
                            startActivity(intent);
                        }

                        mDrawerLayout.closeDrawer(frameLayout);
                        break;

                    case 4 :
                        if ( adapter.getCount() < 10 ) { // After Sale Services
                            Intent intent = new Intent(MainActivity.this, AfterSalesActivity.class);
                            intent.putExtra("language", global_language);
                            startActivity(intent);
                        }
                        else if(companyMenu){ // Certificates
                            if ( mainLinearLayout.getTag() != CertificatesActivity.class ) {
                                Intent intent = new Intent(MainActivity.this, CertificatesActivity.class);
                                intent.putExtra("language", global_language);
                                startActivity(intent);
                            }
                        }else if(productMenu){ //Intelligent
                            Intent intent = new Intent(MainActivity.this, PruductCategotyActivity.class);
                            intent.putExtra("language", global_language);
                            intent.putExtra("category", "intelligent");
                            if(global_language.equals("FA"))
                                intent.putExtra("name", R.string.Intelligent_FA);
                            else
                                intent.putExtra("name", R.string.Intelligent_EN);
                            startActivity(intent);
                        }
                        mDrawerLayout.closeDrawer(frameLayout);
                        break;

                    case 5:
                        if ( adapter.getCount() < 10 ) { // News and Events
                            Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                            intent.putExtra("language", global_language);
                            startActivity(intent);
                        }else if(companyMenu && !productMenu){
                            productMenu = true;
                            if(global_language.equals("EN")) {
                                    adapter = new SideBarAdapter(MainActivity.this, R.layout.sidebar_list_item,
                                            getResources().getStringArray(R.array.sideBarItemTitlesProductCompanyEn),
                                            getResources().getStringArray(R.array.sideBarItemIconsProductCompany), tf, tfIcon);
                            }else{
                                        adapter = new SideBarAdapter(MainActivity.this, R.layout.sidebar_list_item,
                                                getResources().getStringArray(R.array.sideBarItemTitlesProductCompanyFA),
                                                getResources().getStringArray(R.array.sideBarItemIconsProductCompany), tf, tfIcon);
                            }


                            adapter.notifyDataSetChanged();
                            mDrawerList.setAdapter(adapter);
                        }else if(!companyMenu){ //concealed
                            Intent intent = new Intent(MainActivity.this, PruductCategotyActivity.class);
                            intent.putExtra("language", global_language);
                            intent.putExtra("category", "concealed");
                            if(global_language.equals("FA"))
                                intent.putExtra("name", R.string.Concealed_FA);
                            else
                                intent.putExtra("name", R.string.Concealed_EN);
                            startActivity(intent);
                            mDrawerLayout.closeDrawer(frameLayout);
                        }else{
                            productMenu = false;
                            if (global_language.equals("EN")) {
                                adapter = new SideBarAdapter(MainActivity.this, R.layout.sidebar_list_item,
                                            getResources().getStringArray(R.array.sideBarItemTitlesCompanyEn),
                                            getResources().getStringArray(R.array.sideBarItemIconsCompayny), tf, tfIcon);

                            } else {
                                        adapter = new SideBarAdapter(MainActivity.this, R.layout.sidebar_list_item,
                                                getResources().getStringArray(R.array.sideBarItemTitlesCompanyFA),
                                                getResources().getStringArray(R.array.sideBarItemIconsCompayny), tf, tfIcon);
                            }

                            adapter.notifyDataSetChanged();
                            mDrawerList.setAdapter(adapter);

                        }
                        break;

                    case 6 :
                        if ( adapter.getCount() < 10 ) { // Newsletter
                            Intent intent = new Intent(MainActivity.this, NewLetterActivity.class);
                            intent.putExtra("language", global_language);
                            startActivity(intent);
                        }else if(companyMenu && !productMenu){ // Project
                            Intent intent = new Intent(MainActivity.this, ProjectActivity.class);
                            intent.putExtra("language", global_language);
                            startActivity(intent);
                        }else if(!companyMenu){ // multiColor
                            Intent intent = new Intent(MainActivity.this, PruductCategotyActivity.class);
                            intent.putExtra("language", global_language);
                            intent.putExtra("category", "multi_color");
                            if(global_language.equals("FA"))
                                intent.putExtra("name", R.string.MultiColor_FA);
                            else
                                intent.putExtra("name", R.string.MultiColor_EN);
                            startActivity(intent);
                        }else{ // Kitchen
                            Intent intent = new Intent(MainActivity.this, PruductCategotyActivity.class);
                            intent.putExtra("language", global_language);
                            intent.putExtra("category", "kitchen");
                            if(global_language.equals("FA"))
                                intent.putExtra("name", R.string.Kitchen_FA);
                            else
                                intent.putExtra("name", R.string.Kitchen_EN);
                            startActivity(intent);
                        }
                        mDrawerLayout.closeDrawer(frameLayout);
                        break;

                    case 7 :
                        if(adapter.getCount() < 10){ //Contact Us
                            Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                            intent.putExtra("language", global_language);
                            startActivity(intent);
                        }else if(companyMenu && !productMenu){ // Sale Network
                            Intent intent = new Intent(MainActivity.this, SaleNetworkActivity.class);
                            intent.putExtra("language", global_language);
                            startActivity(intent);
                        }else if(!companyMenu){ //Accesorri
                            Intent intent = new Intent(MainActivity.this, AccessoriActivity.class);
                            intent.putExtra("language", global_language);
                            if(global_language.equals("FA"))
                                intent.putExtra("name", R.string.accessori_FA);
                            else
                                intent.putExtra("name", R.string.accessori_EN);
                            startActivity(intent);
                        }else{ // Bathroom
                            Intent intent = new Intent(MainActivity.this, PruductCategotyActivity.class);
                            intent.putExtra("language", global_language);
                            intent.putExtra("category", "bathroom");
                            if(global_language.equals("FA"))
                                intent.putExtra("name", R.string.Bathroom_FA);
                            else
                                intent.putExtra("name", R.string.Bathroom_EN);
                            startActivity(intent);
                        }
                        mDrawerLayout.closeDrawer(frameLayout);
                        break;

                    case 8 :

                        if(companyMenu && !productMenu){// After Sale Services
                            Intent intent = new Intent(MainActivity.this, AfterSalesActivity.class);
                            intent.putExtra("language", global_language);
                            startActivity(intent);
                        }else if(companyMenu && productMenu){ //Inteligance
                            Intent intent = new Intent(MainActivity.this, PruductCategotyActivity.class);
                            intent.putExtra("language", global_language);
                            intent.putExtra("category", "inteligance");
                            if(global_language.equals("FA"))
                                intent.putExtra("name", R.string.Intelligent_FA);
                            else
                                intent.putExtra("name", R.string.Intelligent_EN);
                            startActivity(intent);
                        }else if(!companyMenu && productMenu){ //project
                            Intent intent = new Intent(MainActivity.this, ProjectActivity.class);
                            intent.putExtra("language", global_language);
                            startActivity(intent);
                        }else{//Login
                            if(!DataStorage.log_in) {
                                Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                                intent.putExtra("language", global_language);
                                startActivity(intent);

                            }else{
                                Intent intent = new Intent(MainActivity.this, OrderManagment.class);
                                intent.putExtra("language", global_language);
                                intent.putExtra("tittle", DataStorage.logInInformation.get(0).getVName()+" | "+
                                        DataStorage.logInInformation.get(0).getUserType());
                                startActivity(intent);
                            }
                        }
                        mDrawerLayout.closeDrawer(frameLayout);
                        break;

                    case 9 :
                        if(companyMenu && !productMenu){// New and Event
                            Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                            intent.putExtra("language", global_language);
                            startActivity(intent);

                        }else if(companyMenu && productMenu){ //Concealed
                            Intent intent = new Intent(MainActivity.this, PruductCategotyActivity.class);
                            intent.putExtra("language", global_language);
                            intent.putExtra("category", "concealed");
                            if(global_language.equals("FA"))
                                intent.putExtra("name", R.string.Concealed_FA);
                            else
                                intent.putExtra("name", R.string.Concealed_EN);
                            startActivity(intent);
                        }else if(!companyMenu && productMenu){ //SaleNetwork
                            Intent intent = new Intent(MainActivity.this, SaleNetworkActivity.class);
                            intent.putExtra("language", global_language);
                            startActivity(intent);
                        }
                        mDrawerLayout.closeDrawer(frameLayout);
                        break;

                    case 10 :

                        if(companyMenu && !productMenu){// Newletter
                            Intent intent = new Intent(MainActivity.this, NewLetterActivity.class);
                            intent.putExtra("language", global_language);
                            startActivity(intent);
                        }else if(companyMenu && productMenu){ //MultiColor
                            Intent intent = new Intent(MainActivity.this, PruductCategotyActivity.class);
                            intent.putExtra("language", global_language);
                            intent.putExtra("category", "multi_color");
                            if(global_language.equals("FA"))
                                intent.putExtra("name", R.string.MultiColor_FA);
                            else
                                intent.putExtra("name", R.string.MultiColor_EN);
                            startActivity(intent);
                        }else if(!companyMenu && productMenu){ //After sale Service
                            Intent intent = new Intent(MainActivity.this, AfterSalesActivity.class);
                            intent.putExtra("language", global_language);
                            startActivity(intent);
                        }
                        mDrawerLayout.closeDrawer(frameLayout);
                        break;
                    case 11 :

                        if(companyMenu && !productMenu){// Contactus
                            Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                            intent.putExtra("language", global_language);
                            startActivity(intent);
                        }else if(companyMenu && productMenu){ //Accessorie
                            Intent intent = new Intent(MainActivity.this, AccessoriActivity.class);
                            intent.putExtra("language", global_language);
                            if(global_language.equals("FA"))
                                intent.putExtra("name", R.string.accessori_FA);
                            else
                                intent.putExtra("name", R.string.accessori_EN);
                            startActivity(intent);
                        }else if(!companyMenu && productMenu){ //News and Event
                            Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                            intent.putExtra("language", global_language);
                            startActivity(intent);

                        }
                        mDrawerLayout.closeDrawer(frameLayout);
                        break;

                    case 12 :

                        if(companyMenu && productMenu){ //Projects
                            Intent intent = new Intent(MainActivity.this, ProjectActivity.class);
                            intent.putExtra("language", global_language);
                            startActivity(intent);

                        }else if(!companyMenu && productMenu){ //Newletter
                            Intent intent = new Intent(MainActivity.this, NewLetterActivity.class);
                            intent.putExtra("language", global_language);
                            startActivity(intent);
                        }else if(companyMenu && !productMenu){ //Login
                            if(!DataStorage.log_in) {
                                Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                                intent.putExtra("language", global_language);
                                startActivity(intent);
                            }else{
                                Intent intent = new Intent(MainActivity.this, OrderManagment.class);
                                intent.putExtra("language", global_language);
                                intent.putExtra("tittle", DataStorage.logInInformation.get(0).getVName()+" | "+
                                        DataStorage.logInInformation.get(0).getUserType());
                                startActivity(intent);
                            }
                        }
                        mDrawerLayout.closeDrawer(frameLayout);
                        break;

                    case 13 :

                        if(companyMenu && productMenu){ //Sale and network

                        }else if(!companyMenu && productMenu){ //Contact us
                            Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                            intent.putExtra("language", global_language);
                            startActivity(intent);
                        }
                        mDrawerLayout.closeDrawer(frameLayout);
                        break;
                    case 14 :

                        if(companyMenu && productMenu){ //Technical Services

                        }
                        else if(!companyMenu && productMenu){ //Login
                            if(!DataStorage.log_in) {
                                Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                                intent.putExtra("language", global_language);
                                startActivity(intent);
                            }else{
                                Intent intent = new Intent(MainActivity.this, OrderManagment.class);
                                intent.putExtra("language", global_language);
                                intent.putExtra("tittle", DataStorage.logInInformation.get(0).getVName()+" | "+
                                        DataStorage.logInInformation.get(0).getUserType());
                                startActivity(intent);
                            }
                        }
                        mDrawerLayout.closeDrawer(frameLayout);
                        break;

                    case 15 :

                        if(companyMenu && productMenu){ //New and Event
                            Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                            intent.putExtra("language", global_language);
                            startActivity(intent);

                        }
                        mDrawerLayout.closeDrawer(frameLayout);
                        break;
                    case 16 :

                        if(companyMenu && productMenu){ //Newsletter
                            Intent intent = new Intent(MainActivity.this, NewLetterActivity.class);
                            intent.putExtra("language", global_language);
                            startActivity(intent);
                        }
                        mDrawerLayout.closeDrawer(frameLayout);
                        break;

                    case 17 :
                        if(companyMenu && productMenu){ //Contact us
                            Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                            intent.putExtra("language", global_language);
                            startActivity(intent);
                        }
                        mDrawerLayout.closeDrawer(frameLayout);
                        break;

                    case 18 :
                        if(companyMenu && productMenu){ //Login
                            if(!DataStorage.log_in) {
                                Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                                intent.putExtra("language", global_language);
                                startActivity(intent);
                            }else{
                                Intent intent = new Intent(MainActivity.this, OrderManagment.class);
                                intent.putExtra("language", global_language);
                                intent.putExtra("tittle", DataStorage.logInInformation.get(0).getVName()+" | "+
                                        DataStorage.logInInformation.get(0).getUserType());
                                startActivity(intent);
                            }
                        }
                        mDrawerLayout.closeDrawer(frameLayout);
                        break;


                    default:
                        mDrawerLayout.closeDrawer(frameLayout);
                }
            }
        });


        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        }; // Drawer Toggle Object Made

        mDrawerLayout.setDrawerListener(drawerToggle);
        navigation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerOpen(frameLayout)) {
                    mDrawerLayout.closeDrawer(frameLayout);
                } else {
                    mDrawerLayout.openDrawer(frameLayout);
                }
            }
        });

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        rippleSpeed = size.x / 16;
        final TextView la = (TextView) findViewById(R.id.la);

        if(global_language.equals("FA")){
            la.setText("EN");
        }else{
            la.setText("FA");
        }
        la.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(global_language.equals("FA")){
                    global_language = "EN";
                    la.setText("FA");

                }else{
                    global_language = "FA";
                    la.setText("FA");

                }

                getIntent().putExtra("language", global_language);
                startActivity(getIntent());
                finish();


            }
        });

        TextView youtube = (TextView) findViewById(R.id.youtube);
        youtube.setTypeface(fontawesome);
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                intent.putExtra("language", global_language);
                startActivity(intent);
            }
        });


        TextView insta = (TextView) findViewById(R.id.insta);
        insta.setTypeface(tfIcon);
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("http://instagram.com/_u/KWCIRAN");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://instagram.com/KWCIRAN")));
                }
            }
        });


        TextView ggplus = (TextView) findViewById(R.id.ggplus);
        ggplus.setTypeface(tfIcon);
        ggplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/113395744161205684637"));
                if (browserIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(browserIntent);
                }
            }
        });



        TextView telegram = (TextView) findViewById(R.id.telegram);
        telegram.setTypeface(tfIcon);
        telegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://telegram.me/kwciran_bot"));
                if (browserIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(browserIntent);
                }
            }
        });
    }

}
