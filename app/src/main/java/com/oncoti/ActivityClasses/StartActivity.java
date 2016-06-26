package com.oncoti.ActivityClasses;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.oncoti.Fragments.SigninFragment;
import com.oncoti.Fragments.SignupFragment;
import com.oncoti.Fragments.StartScreenFragment;
import com.oncoti.R;
import com.parse.Parse;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;

public class StartActivity extends FragmentActivity {

    private FragmentManager fm;
    private FragmentTransaction ft;
    private SharedPreferences sharedPreferences;
    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        res=getResources();
        sharedPreferences=getSharedPreferences(res.getString(R.string.loginSharedPrefere),0);
        //checkUserStatus();
        initFragment();
        updateUserData();
    }

    private void initFragment() {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        //ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

        StartScreenFragment startScreenFragment = new StartScreenFragment();
        ft.replace(R.id.start_fragholder, startScreenFragment);
        ft.commit();

    }

    private void checkUserStatus() {

            ParseUser currentUser = ParseUser.getCurrentUser();
            if (currentUser != null) {
                // Send logged in users to Welcome.class
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                // Send user to LoginSignupActivity.class
                initFragment();
                finish();
            }
    }

    private void updateUserData(){
        ParseUser currentUser = ParseUser.getCurrentUser();
        currentUser.fetchInBackground();
    }
    public void openFragment(int fragNo) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

        switch (fragNo) {
            case 0:
                SigninFragment signinFragment = new SigninFragment();
                ft.replace(R.id.start_fragholder, signinFragment);
                ft.addToBackStack(null);
                ft.commit();

                break;
            case 1:
                SignupFragment signupFragment = new SignupFragment();
                ft.replace(R.id.start_fragholder, signupFragment);
                ft.addToBackStack(null);
                ft.commit();

                break;
        }

    }


}
