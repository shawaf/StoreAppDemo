package com.oncoti.ActivityClasses;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.oncoti.Fragments.CommentFragment;
import com.oncoti.Fragments.ProductDetailsFragment;
import com.oncoti.Models.ProductModel;
import com.oncoti.R;

public class ProductDetailsActivity extends AppCompatActivity implements View.OnClickListener{

    private int itemPos;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Toolbar mToolbar;
    private TextView proddetilsTV;
    private ImageButton likeBtn;
    private ImageButton searchBtn;
    private LinearLayout searchLay;
    private ImageButton optionsBtn;
    private  String productModelString ;
    public ProductModel productModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        itemPos=getIntent().getExtras().getInt("item_pos");
        productModelString=  getIntent().getStringExtra("prod_model");
        Gson gson=new Gson();
        productModel= gson.fromJson(productModelString,ProductModel.class);

        Toast.makeText(this, "Item Clicked No : " + itemPos, Toast.LENGTH_LONG).show();

        initToolbar();
        initFragment();
    }

    public void setTitle(String title){
        if(title.equals("Comments")){
            hideToolbarButtons();
            proddetilsTV.setText(title);
        }else {
            showToolbarButtons();
            proddetilsTV.setText("");
        }


    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.prod_detils_toolbar);
        proddetilsTV = (TextView) mToolbar.findViewById(R.id.prod_toolbar_titletv);
        likeBtn = (ImageButton) mToolbar.findViewById(R.id.prod_toolbar_likebtn);
        searchBtn = (ImageButton) mToolbar.findViewById(R.id.prod_toolbar_searchbtn);
        searchLay=(LinearLayout)mToolbar.findViewById(R.id.prod_toolbar_searchlay);
        optionsBtn = (ImageButton) mToolbar.findViewById(R.id.prod_toolbar_optionsbtn);
        setSupportActionBar(mToolbar);


        searchBtn.setOnClickListener(this);
        searchLay.setOnClickListener(this);

    }

    private void initFragment() {
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();

        ProductDetailsFragment productDetailsFragment=new ProductDetailsFragment();
        ft.replace(R.id.prod_details_fragholder, productDetailsFragment);
        ft.commit();

    }

    public void openFragment(int fragNo){
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();
        ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

        switch (fragNo){
            case 0:
                CommentFragment commentFragment=new CommentFragment();
                ft.replace(R.id.prod_details_fragholder,commentFragment);
                ft.addToBackStack(null);
                ft.commit();
                break;

        }
    }


    private void showToolbarButtons(){
        likeBtn.setVisibility(View.VISIBLE);
                searchBtn.setVisibility(View.VISIBLE);
        optionsBtn.setVisibility(View.VISIBLE);
    }
    private void hideToolbarButtons(){
        likeBtn.setVisibility(View.GONE);
                searchBtn.setVisibility(View.GONE);
        optionsBtn.setVisibility(View.GONE);
    }

    private void openSearchActivity(){
        Intent searchIntent=new Intent(ProductDetailsActivity.this,SearchActivity.class);
        startActivity(searchIntent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.prod_toolbar_searchbtn:
                openSearchActivity();
                break;
            case R.id.prod_toolbar_searchlay:
                openSearchActivity();
                break;
        }
    }
}
