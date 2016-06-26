package com.oncoti.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oncoti.ActivityClasses.MainActivity;
import com.oncoti.Adapters.ViewPagerAdapter;
import com.oncoti.R;
import com.oncoti.SlidingTab.SlidingTabLayout;

/**
 * Created by Shawaf on 9/4/2015.
 */
public class ShurasFragment extends Fragment {

    private View v;
    private MainActivity mainActivity;
    private CharSequence[] Shurastitles;
    private ViewPagerAdapter adapter;
    private ViewPager pager;
    private SlidingTabLayout tabs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.shuras_frag,container,false);

        initUI();
        return v;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity=(MainActivity)activity;
    }

    @Override
    public void onResume() {
        super.onResume();
        mainActivity.setMainTitleandSelection("Shuras", 5);
    }

    private void initUI() {

        Shurastitles = new CharSequence[]{getResources().getString(R.string.myshuras_tab), getResources().getString(R.string.ourshuras_tab)};
        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new ViewPagerAdapter(getChildFragmentManager(), Shurastitles, 2);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) v.findViewById(R.id.shuras_pager);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1) {
                    //fab.setVisibility(View.GONE);
                } else if (position == 0) {
                    //fab.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) v.findViewById(R.id.shuras_tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
    }


}
