package kr.badream.convenience.View;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import kr.badream.convenience.Helper.Define_menu_click;
import kr.badream.convenience.Helper.Helper_server;
import kr.badream.convenience.Helper.LoginHelper;
import kr.badream.convenience.R;

public class Activity_ctgView extends AppCompatActivity implements View.OnClickListener {

    private ImageView ctg_1;
    private ImageView ctg_2;
    private ImageView ctg_3;
    private ImageView ctg_4;
    private ImageView ctg_5;
    private ImageView ctg_6;

    private View drawerView;
    private DrawerLayout dlDrawer;

    private int storeID;

    @Override
    protected void onResume() {
        super.onResume();
        setCustomActionbar();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctgview);


        ctg_1 = (ImageView) findViewById(R.id.ctg_1);
        ctg_2 = (ImageView) findViewById(R.id.ctg_2);
        ctg_3 = (ImageView) findViewById(R.id.ctg_3);
        ctg_4 = (ImageView) findViewById(R.id.ctg_4);
        ctg_5 = (ImageView) findViewById(R.id.ctg_5);
        ctg_6 = (ImageView) findViewById(R.id.ctg_6);

        ctg_1.setOnClickListener(this);
        ctg_2.setOnClickListener(this);
        ctg_3.setOnClickListener(this);
        ctg_4.setOnClickListener(this);
        ctg_5.setOnClickListener(this);
        ctg_6.setOnClickListener(this);
        //TODO ctg_7추가해야함! Please ADD ctg_7 ImageView

        Intent intent = getIntent();
        storeID = intent.getIntExtra("storeID",1);

        setCustomActionbar();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.ctg_1:
                //간편식사 easy
                Helper_server.loadStoreCategoryListWithRetrofit(this, LoginHelper.getUserID(getApplicationContext()), storeID, 3,0);
                break;
            case R.id.ctg_2:
                //즉석식품 insta
                Helper_server.loadStoreCategoryListWithRetrofit(this, LoginHelper.getUserID(getApplicationContext()), storeID, 2,0);
                break;
            case R.id.ctg_3:
                //식품 food
                Helper_server.loadStoreCategoryListWithRetrofit(this, LoginHelper.getUserID(getApplicationContext()), storeID, 6,0);
                break;
            case R.id.ctg_4:
                //아이스크림 ice
                Helper_server.loadStoreCategoryListWithRetrofit(this, LoginHelper.getUserID(getApplicationContext()), storeID, 5,0);
                break;
            case R.id.ctg_5:
                //과자 snack
                Helper_server.loadStoreCategoryListWithRetrofit(this, LoginHelper.getUserID(getApplicationContext()), storeID, 4,0);
                break;
            case R.id.ctg_6:
                //음료 drink
                Helper_server.loadStoreCategoryListWithRetrofit(this, LoginHelper.getUserID(getApplicationContext()), storeID, 1,0);
                break;
            default :
                //TODO 비식품 해줘야함 notFood
                break;
        }

    }


    private void setCustomActionbar(){

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

        //set custom view layout
        View mCustomView = LayoutInflater.from(this).inflate(R.layout.actionbar_main, null);
        actionBar.setCustomView(mCustomView);

        // set no padding both side
        Toolbar parent = (Toolbar) mCustomView.getParent();
        parent.setContentInsetsAbsolute(0, 0);

        // set actionbar backgroung image
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.activity_layout_background));
        TextView act_title = (TextView) findViewById(R.id.actionbar_title);
        act_title.setText("카테고리 선택");

        //set actionbar layout layoutparams
        ActionBar.LayoutParams params = new ActionBar.LayoutParams((ActionBar.LayoutParams.MATCH_PARENT));
        actionBar.setCustomView(mCustomView, params);

        // navigation 으로 동작할 화면
        drawerView = (View) findViewById(R.id.drawer);

        // Drawer layout
        dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Define_menu_click.set_menu_click(getApplicationContext(),dlDrawer,this);

        //actionbar에서 내비 제어할 버튼
        ImageButton btn_menu = (ImageButton) mCustomView.findViewById(R.id.btn_menu);
        btn_menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dlDrawer.openDrawer(drawerView);
            }
        });
    }
}
