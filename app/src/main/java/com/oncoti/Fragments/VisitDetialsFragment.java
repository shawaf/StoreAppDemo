package com.oncoti.Fragments;

import android.app.Activity;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.oncoti.ActivityClasses.VisitDetailsActivity;
import com.oncoti.Adapters.ImageSlideAdapter;
import com.oncoti.Models.VisitModel;
import com.oncoti.R;
import com.oncoti.Utilites.CirclePageIndicator;
import com.oncoti.Utilites.CommonMethods;

/**
 * Created by Shawaf on 9/11/2015.
 */
public class VisitDetialsFragment extends Fragment {

    private View v;
    private TextView userName, shopName, visitTime, visitDoing, visitLocation, visitCity, detailsPlaceName;
    private ImageView userImage;
    private VisitDetailsActivity visitDetailsActivity;
    private ViewPager mViewPager;
    private CirclePageIndicator mIndicator;
    private boolean stopSliding = false;
    private Handler handler;
    private Runnable animateViewPager;
    private SharedPreferences sharedPreferences;
    private Resources res;
    private String[] sliderText = new String[]{" ", " ", " "};
    int[] sliderImages = new int[]{R.drawable.street, R.drawable.forest, R.drawable.batman};
    private VisitModel visitModel;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.visitdetails_frag, container, false);

        initSlider();
        initUI();
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        visitDetailsActivity = (VisitDetailsActivity) activity;
    }

    @Override
    public void onResume() {
        visitModel = visitDetailsActivity.visitModel;
        mViewPager.setAdapter(new ImageSlideAdapter(
                getActivity(), VisitDetialsFragment.this, sliderText, visitModel.getPlaceImages()));
        fillData(visitModel);

        //run the image viewpager
        mIndicator.setViewPager(mViewPager);
        stopSliding = false;
        runnable(visitModel.getPlaceImages().size());
        handler.postDelayed(animateViewPager,
                10000);
        super.onResume();
    }


    @Override
    public void onPause() {
        if (handler != null) {
            handler.removeCallbacks(animateViewPager);
        }
        super.onPause();
    }

    private void initSlider() {
        mViewPager = (ViewPager) v.findViewById(R.id.visitdetials_viewpager);
        mIndicator = (CirclePageIndicator) v.findViewById(R.id.visitdetials_indicator);
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

    private void initUI() {
        userImage = (ImageView) v.findViewById(R.id.ivisited_userimage);
        userName = (TextView) v.findViewById(R.id.ivisited_username);
        shopName = (TextView) v.findViewById(R.id.ivisit_placename);
        visitTime = (TextView) v.findViewById(R.id.ivisited_time);
        visitDoing = (TextView) v.findViewById(R.id.ivisit_desc);
        detailsPlaceName = (TextView) v.findViewById(R.id.visitedetails_placename);
        visitLocation = (TextView) v.findViewById(R.id.visitdetails_shopaddress);
        visitCity = (TextView) v.findViewById(R.id.visitdetails_city);

    }
    private void fillData(VisitModel visitModel) {
        UrlImageViewHelper.setUrlDrawable(userImage, visitModel.getUserImageUrl(), R.drawable.logo1);
        userName.setText(visitModel.getUserName());
        shopName.setText(visitModel.getPlaceName());
        visitTime.setText(CommonMethods.getDaysBetween(visitModel.getVisitTime())+" days ago");
        visitDoing.setText(visitModel.getVisitDesc());
        detailsPlaceName.setText(visitModel.getPlaceName());
        visitLocation.setText(visitModel.getPlaceLocation());

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


}
