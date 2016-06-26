package com.oncoti.Utilites;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Shawaf on 8/15/2015.
 */
public class CommonMethods {


    private static ProgressDialog progressDialog;

    public static void closeKeyboard(Activity context){
        View cview = context.getCurrentFocus();
        if (cview != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(cview.getWindowToken(), 0);
        }
    }
    public static ProgressDialog showProgressDialog(Context context){
        progressDialog=new ProgressDialog(context);
        progressDialog.setTitle("SignUp Process");
        progressDialog.setMessage("Loading ......");

        return progressDialog;
    }

    public static boolean isValidMail(String email2) {
        boolean check;
        Pattern p;
        Matcher m;

        String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        p = Pattern.compile(EMAIL_STRING);

        m = p.matcher(email2);
        check = m.matches();


        return check;
    }

    public static int getDaysBetween(Date postDate)
    {
        Calendar sDate = toCalendar(postDate.getTime());
        Calendar eDate = toCalendar(System.currentTimeMillis());

        // Get the represented date in milliseconds
        long milis1 = sDate.getTimeInMillis();
        long milis2 = eDate.getTimeInMillis();

        // Calculate difference in milliseconds
        long diff = Math.abs(milis2 - milis1);

        return (int)(diff / (24 * 60 * 60 * 1000));
    }

    private static Calendar toCalendar(long timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar;
    }
    }
