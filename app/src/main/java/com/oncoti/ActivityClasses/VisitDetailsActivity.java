package com.oncoti.ActivityClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.oncoti.Fragments.ProductDetailsFragment;
import com.oncoti.Fragments.VisitDetialsFragment;
import com.oncoti.Models.ProductModel;
import com.oncoti.Models.VisitModel;
import com.oncoti.R;

public class VisitDetailsActivity extends AppCompatActivity implements View.OnClickListener{

    private FragmentManager fm;
    private FragmentTransaction ft;
    private Toolbar mToolbar;
    private TextView visitdetilsTV;
    private ImageButton likeBtn;
    private ImageButton searchBtn;
    private LinearLayout searchLay;
    private String visitModelString;
    public VisitModel visitModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_details);
        initToolbar();
        initFragment();

        visitModelString=getIntent().getExtras().getString("visit_model");
        Gson gson=new Gson();
        visitModel= gson.fromJson(visitModelString, VisitModel.class);
    }

    public void setTitle(String title) {


    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.visitdetials_toolbar);
        visitdetilsTV = (TextView) mToolbar.findViewById(R.id.visitdetials__titletv);
        likeBtn = (ImageButton) mToolbar.findViewById(R.id.visitdetials_likebtn);
        searchBtn = (ImageButton) mToolbar.findViewById(R.id.visitdetials_searchbtn);
        searchLay=(LinearLayout)mToolbar.findViewById(R.id.visitdetials_searchlay);


        setSupportActionBar(mToolbar);

        searchBtn.setOnClickListener(this);
        searchLay.setOnClickListener(this);


    }

    private void initFragment() {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        VisitDetialsFragment visitDetialsFragment = new VisitDetialsFragment();
        ft.replace(R.id.visitdetails_fragholder, visitDetialsFragment);
        ft.commit();

    }

    public void openFragment(int fragNo) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

        switch (fragNo) {

        }
    }
    private void openSearchActivity(){
        Intent searchIntent=new Intent(VisitDetailsActivity.this,SearchActivity.class);
        startActivity(searchIntent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.visitdetials_searchbtn:
                openSearchActivity();
                break;
            case R.id.visitdetials_searchlay:
                openSearchActivity();
                break;
        }
    }
}
