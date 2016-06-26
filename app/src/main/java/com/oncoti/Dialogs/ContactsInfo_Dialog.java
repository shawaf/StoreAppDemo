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
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

/**
 * Created by Shawaf on 10/4/2015.
 */
public class ContactsInfo_Dialog extends Dialog implements View.OnClickListener {
    Activity activity;
    private TextView descTV, errorTV;
    private Button cancelBtn, saveBtn;
    private EditText phoneET, addressET;
    private ProgressBar sendingProgBar;
    private LinearLayout btnsLayout;

    public ContactsInfo_Dialog(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(android.view.Window.FEATURE_NO_TITLE);
        setContentView(R.layout.contactsinfo_customdialog);
        this.setCancelable(true);

        descTV = (TextView) findViewById(R.id.contactinfo_title);
        cancelBtn = (Button) findViewById(R.id.contactinfo_nobtn);
        saveBtn = (Button) findViewById(R.id.contactinfo_yesbtn);
        errorTV = (TextView) findViewById(R.id.contactinfo_dialogerror);
        phoneET = (EditText) findViewById(R.id.contactinfo_phone);
        addressET = (EditText) findViewById(R.id.contactinfo_address);
        sendingProgBar = (ProgressBar) findViewById(R.id.contactinfo_progbar);
        btnsLayout = (LinearLayout) findViewById(R.id.contactinfo_btnlayout);

        cancelBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);

        ParseUser user = ParseUser.getCurrentUser();
        if (user.get("adress") != null) {
            addressET.setText(user.get("adress").toString());
        } else {
            addressET.setText("null");
        }
        if (user.get("phone") != null) {
            phoneET.setText(user.get("phone").toString());
        } else {
            phoneET.setText("null");
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.contactinfo_yesbtn:
                if (phoneET.getText().toString().matches("")) {
                    phoneET.setError("please enter your phone");
                } else if (addressET.getText().toString().matches("")) {
                    addressET.setError("please enter your address");
                } else {
                    btnsLayout.setVisibility(View.GONE);
                    sendingProgBar.setVisibility(View.VISIBLE);

                    String phone = phoneET.getText().toString();
                    String address = addressET.getText().toString();

                    ParseUser user = ParseUser.getCurrentUser();
                    user.put("phone", phone);
                    user.put("adress", address);
                    user.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e != null) {
                                btnsLayout.setVisibility(View.GONE);
                                sendingProgBar.setVisibility(View.VISIBLE);
                                errorTV.setText(e.getMessage());
                                return;
                            } else {
                                Toast.makeText(activity, "Contacts Info Updated", Toast.LENGTH_LONG).show();
                                ContactsInfo_Dialog.this.dismiss();
                            }
                        }
                    });
                }

                break;
            case R.id.contactinfo_nobtn:
                ContactsInfo_Dialog.this.cancel();
                break;
        }
    }


}
