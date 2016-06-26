package com.oncoti.Fragments;

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
import com.oncoti.Adapters.SearchViewPagerAdapter;
import com.oncoti.Adapters.ViewPagerAdapter;
import com.oncoti.R;
import com.oncoti.SlidingTab.SlidingTabLayout;

/**
 * Created by Shawaf on 9/11/2015.
 */
public class SearchByNameFragment extends Fragment{
    private View v;
    private CharSequence[] searchTabs;
    private SearchViewPagerAdapter adapter;
    private ViewPager pager;
    private SlidingTabLayout tabs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.searchbyname_frag,container,false);

        initUI();
        return v;
    }

    private void initUI() {


        searchTabs = new CharSequence[]{getResources().getString(R.string.products_tab), getResources().getString(R.string.users_tab), getResources().getString(R.string.stories_tab)};
        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new SearchViewPagerAdapter(getChildFragmentManager(), searchTabs, 3);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) v.findViewById(R.id.search_pager);
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
        tabs = (SlidingTabLayout) v.findViewById(R.id.search_tabs);
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
