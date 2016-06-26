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

import com.oncoti.Fragments.AboutUserFragment;
import com.oncoti.Fragments.ProfileFragment;
import com.oncoti.R;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView profileTitle;
    private ImageButton backBtn, optionBtn, settingsBtn, chatBtn;
    private LinearLayout backBtnLay, optionBtnLay, settingsBtnLay, chatBtnLay;
    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initToolbar();
        initFragment();

    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.profile_toolbar);
        profileTitle = (TextView) mToolbar.findViewById(R.id.profile_toolbar_titletv);
        backBtn = (ImageButton) mToolbar.findViewById(R.id.profile_toolbar_backbtn);
        optionBtn = (ImageButton) mToolbar.findViewById(R.id.profile_toolbar_optionsbtn);
        settingsBtn = (ImageButton) mToolbar.findViewById(R.id.profile_toolbar_settingbtn);
        chatBtn = (ImageButton) mToolbar.findViewById(R.id.profile_toolbar_chatbtn);
        optionBtnLay = (LinearLayout) mToolbar.findViewById(R.id.profile_toolbar_optionslay);
        settingsBtnLay = (LinearLayout) mToolbar.findViewById(R.id.profile_toolbar_settingslay);
        chatBtnLay = (LinearLayout) mToolbar.findViewById(R.id.profile_toolbar_chatslay);

        setSupportActionBar(mToolbar);

        optionBtn.setOnClickListener(this);
        settingsBtn.setOnClickListener(this);
        chatBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
        optionBtnLay.setOnClickListener(this);
        settingsBtnLay.setOnClickListener(this);
        chatBtnLay.setOnClickListener(this);

    }

    private void initFragment() {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        ProfileFragment profileFragment = new ProfileFragment();
        ft.replace(R.id.profile_fragholder, profileFragment, "profile");
        ft.commit();

    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
            overridePendingTransition(R.anim.stop_animation, R.anim.right_slide_out);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_toolbar_settingbtn:
                startSettingsActivity();
                break;
            case R.id.profile_toolbar_backbtn:
                finish();
                overridePendingTransition(R.anim.stop_animation, R.anim.right_slide_out);
                break;
            case R.id.profile_toolbar_chatbtn:
                startChatActivity();
                break;
            case R.id.profile_toolbar_optionsbtn:
                openAboutUserFragment();
                break;
            case R.id.profile_toolbar_chatslay:
                startChatActivity();
                break;
            case R.id.profile_toolbar_optionslay:
                openAboutUserFragment();
                break;
            case R.id.profile_toolbar_settingslay:
                startSettingsActivity();
                break;
        }
    }

    private void openAboutUserFragment() {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

        AboutUserFragment aboutUserFragment = new AboutUserFragment();
        ft.replace(R.id.profile_fragholder, aboutUserFragment, "about");
        ft.addToBackStack(null);
        ft.commit();

    }

    private void startChatActivity() {
        Intent chatIntent = new Intent(ProfileActivity.this, ChatActivity.class);
        chatIntent.putExtra("chat_user", "Carl Pamlr");
        startActivity(chatIntent);
    }

    private void startSettingsActivity() {
        Intent settIntent = new Intent(ProfileActivity.this, SettingsActivity.class);
        startActivity(settIntent);
    }
}
