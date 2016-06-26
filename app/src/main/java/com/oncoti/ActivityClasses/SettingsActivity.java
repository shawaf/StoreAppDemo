package com.oncoti.ActivityClasses;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.oncoti.Fragments.HomeFragment;
import com.oncoti.Fragments.SettingsFragment;
import com.oncoti.R;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{

    private FragmentManager fm;
    private FragmentTransaction ft;
    private Toolbar mToolbar;
    private TextView mainTitle;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initToolbar();
        initFragment();

    }


    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.settings_toolbar);
        mainTitle = (TextView) mToolbar.findViewById(R.id.chat_toolbar_titletv);
        backBtn = (ImageButton) mToolbar.findViewById(R.id.chat_toolbar_backbtn);
        setSupportActionBar(mToolbar);

        setTitle();
        backBtn.setOnClickListener(this);

    }

    private void initFragment() {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        SettingsFragment settingsFragment = new SettingsFragment();
        ft.replace(R.id.settings_fragholder, settingsFragment, "settings");
        ft.commit();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.chat_toolbar_backbtn:
                finish();
                break;
        }
    }

    private void setTitle(){
        ParseUser user=ParseUser.getCurrentUser();
        mainTitle.setText(user.getUsername());



    }
}
