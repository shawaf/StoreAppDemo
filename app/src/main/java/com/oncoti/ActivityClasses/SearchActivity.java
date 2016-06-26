package com.oncoti.ActivityClasses;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.oncoti.Fragments.ChatFragment;
import com.oncoti.Fragments.SearchByCategoryFragment;
import com.oncoti.Fragments.SearchByNameFragment;
import com.oncoti.R;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar mToolbar;
    private EditText searchET;
    private ImageButton backBtn;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private View shadowView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initToolbar();
        initFragment();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.search_toolbar);
        searchET = (EditText) mToolbar.findViewById(R.id.search_et);
        backBtn = (ImageButton) mToolbar.findViewById(R.id.search_toolbar_backbtn);
        shadowView=findViewById(R.id.search_toolbar_shadow);
        setSupportActionBar(mToolbar);

        backBtn.setOnClickListener(this);
        searchET.setOnClickListener(this);
    }

    private void initFragment() {
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();

        SearchByCategoryFragment searchByCategoryFragment=new SearchByCategoryFragment();
        ft.replace(R.id.search_fragholder, searchByCategoryFragment);
        ft.commit();

    }

    private void openSearchByNameFragment(){
        shadowView.setVisibility(View.GONE);
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();
       // ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

        SearchByNameFragment searchByNameFragment=new SearchByNameFragment();
        ft.replace(R.id.search_fragholder, searchByNameFragment);
        ft.addToBackStack(null);
        ft.commit();
    }


    @Override
    public void onClick(View view) {
     switch (view.getId()){
         case R.id.search_toolbar_backbtn:
            finish();
             break;
         case R.id.search_et:
             openSearchByNameFragment();
             break;
     }
    }
}
