package com.oncoti.ActivityClasses;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.oncoti.Fragments.AddNewProductFragment;
import com.oncoti.Fragments.AddNewVisitFragment;
import com.oncoti.Fragments.ProductListFragment;
import com.oncoti.R;

public class AddNewVisitAcitivty extends AppCompatActivity implements View.OnClickListener {

    private FragmentManager fm;
    private FragmentTransaction ft;
    private Toolbar mToolbar;
    private TextView newvisitTitle;
    private Button sumbitBtn;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_visit);

        initToolbar();
        initFragment();

    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.newvisit_toolbar);
        newvisitTitle = (TextView) mToolbar.findViewById(R.id.back_toolbar_titletv);
        sumbitBtn = (Button) mToolbar.findViewById(R.id.back_toolbar_publishbtn);
        backBtn = (ImageButton) mToolbar.findViewById(R.id.back_toolbar_menubtn);
        setSupportActionBar(mToolbar);

        newvisitTitle.setText(getResources().getString(R.string.newvisit));
        backBtn.setOnClickListener(this);
        sumbitBtn.setOnClickListener(this);

    }

    private void initFragment() {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        AddNewVisitFragment addNewVisitFragment = new AddNewVisitFragment();
        ft.replace(R.id.newvisit_fragholder, addNewVisitFragment,"newvisit");
        ft.commit();
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.back_toolbar_menubtn:
                finish();
                break;
            case R.id.back_toolbar_publishbtn:
                AddNewVisitFragment addNewVisitFragment = (AddNewVisitFragment) getSupportFragmentManager().findFragmentByTag("newvisit");

                if (addNewVisitFragment != null && addNewVisitFragment.isVisible()) {
                    addNewVisitFragment.submitVisit();
                }
                break;

        }

    }
}
