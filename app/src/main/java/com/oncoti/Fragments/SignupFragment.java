package com.oncoti.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.oncoti.ActivityClasses.MainActivity;
import com.oncoti.ActivityClasses.StartActivity;
import com.oncoti.R;
import com.oncoti.Utilites.CheckNetworkConnection;
import com.oncoti.Utilites.CommonMethods;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.text.ParseException;

/**
 * Created by Shawaf on 8/23/2015.
 */
public class SignupFragment extends Fragment {

    private View v;
    private StartActivity startActivity;
    private EditText usernameET, passwordEt, emailET,firstNameET,lastNameET;
    private Spinner countrySpinner;
    private Button signupBtn;
    private CoordinatorLayout rootLayout;
    private ProgressBar signProgBar;
    private SharedPreferences sharedPreferences;
    private Resources res;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.signup_frag, container, false);
        res=getActivity().getResources();
        sharedPreferences=getActivity().getSharedPreferences(res.getString(R.string.loginSharedPrefere), 0);
        initUI();
        return v;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        startActivity = (StartActivity) activity;
    }

    private void initUI() {
        firstNameET=(EditText)v.findViewById(R.id.singup_firstname_et);
        lastNameET=(EditText)v.findViewById(R.id.singup_lastname_et);
        usernameET = (EditText) v.findViewById(R.id.singup_username_et);
        passwordEt = (EditText) v.findViewById(R.id.signup_psw_et);
        emailET = (EditText) v.findViewById(R.id.signup_email_et);
        countrySpinner = (Spinner) v.findViewById(R.id.signup_country_spinner);
        rootLayout = (CoordinatorLayout) v.findViewById(R.id.signup_rootLayout);
        signProgBar=(ProgressBar)v.findViewById(R.id.signup_progressBar);
        signupBtn = (Button) v.findViewById(R.id.signup_btn);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CheckNetworkConnection.isConnectionAvailable(getActivity())) {
                    if (usernameET.getText().toString().matches("") || passwordEt.getText().toString().matches("") || emailET.getText().toString().matches("")) {

                        Toast.makeText(getActivity(),"Please fill the signup form",Toast.LENGTH_LONG).show();
                    }else {
                        signProgBar.setVisibility(View.VISIBLE);
                        CommonMethods.closeKeyboard(getActivity());
                        String firstName=firstNameET.getText().toString();
                        String lastName=lastNameET.getText().toString();
                        String userName=usernameET.getText().toString();
                        String password=passwordEt.getText().toString();
                        String email=emailET.getText().toString();
                        String country=countrySpinner.getSelectedItem().toString();
                        //Log
                        ParseUser currentUser = ParseUser.getCurrentUser();
                        if(currentUser != null)
                        currentUser.logOut();
                        // Save new user data into Parse.com Data Storage
//                        ParseObject user = new ParseObject("User");
//                        user.put("username",userName);
//                        user.put("password", password);
//                        user.put("email", email);
//                        user.put("country", country);
                        ParseUser user = new ParseUser();
                        user.setUsername(userName);
                        user.setPassword(password);
                        user.setEmail(email);
                        user.put("country",country);
                        user.put("first_name",firstName);
                        user.put("last_name",lastName);

                        user.signUpInBackground(new SignUpCallback() {
                            @Override
                            public void done(com.parse.ParseException e) {

                                signProgBar.setVisibility(View.GONE);
                                if (e == null) {
                                    // Show a simple Toast message upon successful registration
                                    Toast.makeText(getActivity(),
                                            "Successfully Signed up",
                                            Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getActivity(), MainActivity.class);
                                    startActivity(intent);
                                    getActivity().finish();
                                } else {
                                    Toast.makeText(getActivity(),
                                            e.getMessage(), Toast.LENGTH_LONG)
                                            .show();
                                    Log.e("Signup", "Error : " + e.getMessage());
                                }
                            }
                        });
                    }
                } else {
                    Snackbar.make(rootLayout, " No Internet Connection!", Snackbar.LENGTH_LONG)
                            .setAction("Settings", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    signProgBar.setVisibility(View.VISIBLE);
                                }
                            })
                            .show();
                }
            }
        });
    }
    private void saveLoginState(String state){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(res.getString(R.string.loginStatePrefer),state);
        editor.commit();
    }
}
