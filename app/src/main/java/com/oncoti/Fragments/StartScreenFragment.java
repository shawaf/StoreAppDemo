package com.oncoti.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.oncoti.ActivityClasses.MainActivity;
import com.oncoti.ActivityClasses.StartActivity;
import com.oncoti.Adapters.ImageSlideAdapter;
import com.oncoti.Adapters.StartScreenImageSlider;
import com.oncoti.R;
import com.oncoti.Utilites.CirclePageIndicator;
import com.parse.ParseUser;

import java.util.ArrayList;

/**
 * Created by Shawaf on 8/23/2015.
 */
public class StartScreenFragment extends Fragment implements View.OnClickListener{
    private View v;
    private StartActivity startActivity;
    private Button signinBtn,creataccBtn;
    private ViewPager mViewPager;
    private CirclePageIndicator mIndicator;
    private boolean stopSliding=false;
    private Handler handler;
    private Runnable animateViewPager;
    private SharedPreferences sharedPreferences;
    private Resources res;
    private String[] sliderText = new String[]{"This a Test Text of Page 1", "This a Test Text of Page 2", "This a Test Text of Page 3"};
    int[] sliderImages = new int[]{R.drawable.street,R.drawable.forest, R.drawable.batman};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.startscreen_frag,container,false);
        res=getActivity().getResources();
        sharedPreferences=getActivity().getSharedPreferences(res.getString(R.string.loginSharedPrefere), 0);
        initUI();
        initSlider();
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        startActivity=(StartActivity)activity;
    }

    @Override
    public void onResume() {

        mViewPager.setAdapter(new StartScreenImageSlider(
                getActivity(),  StartScreenFragment.this,sliderText,sliderImages));

        mIndicator.setViewPager(mViewPager);
        stopSliding = false;
        runnable(3);
        handler.postDelayed(animateViewPager,
                10000);
        super.onResume();
    }


    @Override
    public void onPause() {
        if(handler != null) {
            handler.removeCallbacks(animateViewPager);
        }
        super.onPause();
    }

    private void initUI(){
        signinBtn=(Button)v.findViewById(R.id.signin_btn);
        creataccBtn=(Button)v.findViewById(R.id.creatacc_btn);

        signinBtn.setOnClickListener(this);
        creataccBtn.setOnClickListener(this);
    }

    private void initSlider(){
        mViewPager = (ViewPager) v.findViewById(R.id.view_pager);
        mIndicator = (CirclePageIndicator) v.findViewById(R.id.indicator);
        mIndicator.setOnPageChangeListener(new PageChangeListener());
        mViewPager.setOnPageChangeListener(new PageChangeListener());
        mViewPager.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                switch (event.getAction()) {

                    case MotionEvent.ACTION_CANCEL:
                        break;

                    case MotionEvent.ACTION_UP:
                        // calls when touch release on ViewPager

                        stopSliding = false;
                        runnable(3);
                        handler.postDelayed(animateViewPager,
                                10000);

                        break;

                    case MotionEvent.ACTION_MOVE:
                        // calls when ViewPager touch
                        if (handler != null && stopSliding == false) {
                            stopSliding = true;
                            handler.removeCallbacks(animateViewPager);
                        }
                        break;
                }
                return false;
            }
        });
    }

    public void runnable(final int size) {
        handler = new Handler();
        animateViewPager = new Runnable() {
            public void run() {
                if (!stopSliding) {
                    if (mViewPager.getCurrentItem() == size - 1) {
                        mViewPager.setCurrentItem(0);
                    } else {
                        mViewPager.setCurrentItem(
                                mViewPager.getCurrentItem() + 1, true);
                    }
                    handler.postDelayed(animateViewPager, 5000);
                }
            }
        };
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signin_btn:
                if(sharedPreferences.getString(res.getString(R.string.loginStatePrefer),"notLoginned").equals(res.getString(R.string.loggined))){
                    Intent mainIntent=new Intent(getActivity(), MainActivity.class);
                    startActivity(mainIntent);
                    getActivity().finish();
                }else {
                    startActivity.openFragment(0);
                }
                break;
            case R.id.creatacc_btn:
                startActivity.openFragment(1);
                break;
        }
    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {

            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
        }
    }

    private void checkUserStatus() {

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // Send logged in users to Welcome.class
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        } else {
            // Send user to LoginSignupActivity.class
          //  initFragment();
            getActivity().finish();
        }
    }

}
