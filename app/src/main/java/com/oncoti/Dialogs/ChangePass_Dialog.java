package com.oncoti.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.oncoti.R;
import com.oncoti.Utilites.CommonMethods;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

/**
 * Created by Shawaf on 10/3/2015.
 */
public class ChangePass_Dialog extends Dialog implements View.OnClickListener {
    Activity activity;
    private TextView descTV, errorTV;
    private Button cancelBtn, yesBtn;
    private EditText emailET;
    private ProgressBar sendingProgBar;
    private LinearLayout btnsLayout;

    public ChangePass_Dialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(android.view.Window.FEATURE_NO_TITLE);
        setContentView(R.layout.changepass_customdialog);
        this.setCancelable(true);

        descTV = (TextView) findViewById(R.id.changepass_title);
        cancelBtn = (Button) findViewById(R.id.changepass_nobtn);
        yesBtn = (Button) findViewById(R.id.changepass_yesbtn);
        errorTV = (TextView) findViewById(R.id.changepass_dialogerror);
        emailET = (EditText) findViewById(R.id.changepass_email);
        sendingProgBar = (ProgressBar) findViewById(R.id.changepass_progbar);
        btnsLayout = (LinearLayout) findViewById(R.id.changepass_btnlayout);

        cancelBtn.setOnClickListener(this);
        yesBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.changepass_yesbtn:
                if (emailET.getText().toString().matches("")) {
                    emailET.setError("Please enter your email");
                } else if (CommonMethods.isValidMail(emailET.getText().toString())) {
                    descTV.setText("Sending Email ...");
                    changePassword(emailET.getText().toString());
                } else {
                    emailET.setError("Please enter valid email");
                }

                break;
            case R.id.changepass_nobtn:
                ChangePass_Dialog.this.cancel();
                break;
        }
    }

    private void changePassword(String signedEmail) {
        sendingProgBar.setVisibility(View.VISIBLE);
        btnsLayout.setVisibility(View.GONE);
        ParseUser.requestPasswordResetInBackground(signedEmail, new RequestPasswordResetCallback() {
            public void done(ParseException e) {
                sendingProgBar.setVisibility(View.GONE);
                if (e == null) {
                    Toast.makeText(getContext(), "Check your email", Toast.LENGTH_LONG).show();
                    ChangePass_Dialog.this.dismiss();
                } else {
                    // Something went wrong. Look at the ParseException to see what's up.
                    errorTV.setVisibility(View.VISIBLE);
                    btnsLayout.setVisibility(View.VISIBLE);
                    errorTV.setText(e.getMessage());
                }
            }
        });
    }
}
