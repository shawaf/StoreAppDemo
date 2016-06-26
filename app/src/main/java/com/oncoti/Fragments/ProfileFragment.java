package com.oncoti.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oncoti.Adapters.SearchViewPagerAdapter;
import com.oncoti.R;
import com.oncoti.SlidingTab.SlidingTabLayout;

/**
 * Created by Shawaf on 9/11/2015.
 */
public class ProfileFragment extends Fragment {
    private View v;
    private CharSequence[] profileTabs;
    private SearchViewPagerAdapter adapter;
    private ViewPager pager;
    private SlidingTabLayout tabs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.profile_frag,container,false);

        initUI();
        return v;
    }

    private void initUI() {


        profileTabs = new CharSequence[]{getResources().getString(R.string.products_tab), getResources().getString(R.string.headlines_tab), getResources().getString(R.string.knocks_tab)};
        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new SearchViewPagerAdapter(getChildFragmentManager(), profileTabs, 3);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) v.findViewById(R.id.profile_pager);
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) v.findViewById(R.id.profile_tabs);
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
