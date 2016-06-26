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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.oncoti.ActivityClasses.MainActivity;
import com.oncoti.ActivityClasses.StartActivity;
import com.oncoti.Dialogs.ChangePass_Dialog;
import com.oncoti.R;
import com.oncoti.Utilites.CheckNetworkConnection;
import com.oncoti.Utilites.CommonMethods;
import com.oncoti.Utilites.CommonUsedStrings;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by Shawaf on 8/23/2015.
 */
public class SigninFragment extends Fragment implements View.OnClickListener {
    private View v;
    private StartActivity startActivity;
    private Button signInBtn;
    private EditText usernameET,passwordET;
    private CoordinatorLayout rootLayout;
    private ProgressBar signProgBar;
    private TextView signInForgetPass;
    private String usernametxt,passwordtxt;
    private SharedPreferences sharedPreferences;
    private Resources res;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.signin_frag, container, false);
        res=getActivity().getResources();
        sharedPreferences=getActivity().getSharedPreferences(res.getString(R.string.loginSharedPrefere),0);
        initUI();
        return v;
    }

    private void initUI() {
        signInBtn = (Button) v.findViewById(R.id.sign_btn);
        rootLayout = (CoordinatorLayout) v.findViewById(R.id.rootLayout);
        signProgBar=(ProgressBar)v.findViewById(R.id.signin_progressBar);
        usernameET=(EditText)v.findViewById(R.id.signin_username_et);
        passwordET=(EditText)v.findViewById(R.id.signin_psw_et);
        signInForgetPass=(TextView)v.findViewById(R.id.singin_forgetpass);

        signInBtn.setOnClickListener(this);
        signInForgetPass.setOnClickListener(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        startActivity = (StartActivity) activity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.singin_forgetpass:
                ChangePass_Dialog changePass_dialog=new ChangePass_Dialog(getActivity());
                changePass_dialog.show();
                break;
            case R.id.sign_btn:
                if (CheckNetworkConnection.isConnectionAvailable(getActivity())) {
                    if(usernameET.getText().toString().matches("") || passwordET.getText().toString().matches("")){
                        Toast.makeText(getActivity(),"Please fill the fields to login",Toast.LENGTH_LONG).show();
                    } else {
                        CommonMethods.closeKeyboard(getActivity());
                        signProgBar.setVisibility(View.VISIBLE);
                        // Retrieve the text entered from the EditText
                        usernametxt = usernameET.getText().toString();
                        passwordtxt = passwordET.getText().toString();

                        // Send data to Parse.com for verification
                        ParseUser.logInInBackground(usernametxt, passwordtxt,
                                new LogInCallback() {
                                    public void done(ParseUser user, ParseException e) {
                                        signProgBar.setVisibility(View.GONE);
                                        if (user != null) {
                                           saveLoginState(res.getString(R.string.loggined));
                                            // If user exist and authenticated, send user to Welcome.class
                                            Intent intent = new Intent(
                                                    getActivity(),
                                                    MainActivity.class);
                                            startActivity(intent);
                                            Toast.makeText(getActivity(),
                                                    "Successfully Logged in",
                                                    Toast.LENGTH_LONG).show();
                                            getActivity().finish();
                                        } else {
                                            saveLoginState(res.getString(R.string.notloggined));
                                            Toast.makeText(
                                                    getActivity(),
                                                    "No such user exist, please signup",
                                                    Toast.LENGTH_LONG).show();
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
//                Intent mainIntent=new Intent(getActivity(), MainActivity.class);
//                startActivity(mainIntent);
                break;
        }
    }

    private void saveLoginState(String state){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(res.getString(R.string.loginStatePrefer),state);
        editor.commit();
    }
}
