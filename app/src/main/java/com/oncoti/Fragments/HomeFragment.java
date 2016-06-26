package com.oncoti.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oncoti.ActivityClasses.AddNewProductActivity;
import com.oncoti.ActivityClasses.MainActivity;
import com.oncoti.Adapters.ViewPagerAdapter;
import com.oncoti.R;
import com.oncoti.SlidingTab.SlidingTabLayout;

/**
 * Created by Shawaf on 9/3/2015.
 */
public class HomeFragment extends Fragment {
    private View v;
    private CharSequence[] Hometitles;
    private ViewPagerAdapter adapter;
    private ViewPager pager;
    private SlidingTabLayout tabs;
    private FloatingActionButton fab;
    private MainActivity mainActivity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.home_frag,container,false);

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
        mainActivity.setMainTitleandSelection("Home",1);
    }

    private void initUI() {
        fab=(FloatingActionButton)v.findViewById(R.id.home_fabbtn);

        Hometitles = new CharSequence[]{getResources().getString(R.string.products_tab), getResources().getString(R.string.knocks_tab)};
        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new ViewPagerAdapter(getChildFragmentManager(), Hometitles, 2);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) v.findViewById(R.id.home_pager);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1) {
                    fab.setVisibility(View.GONE);
                } else if (position == 0) {
                    fab.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) v.findViewById(R.id.home_tabs);
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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addProDIntent=new Intent(mainActivity, AddNewProductActivity.class);
                startActivity(addProDIntent);
            }
        });
    }
}
