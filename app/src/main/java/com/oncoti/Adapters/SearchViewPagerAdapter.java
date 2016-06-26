package com.oncoti.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.oncoti.Fragments.StoriesFragment;
import com.oncoti.Fragments.UsersFragment;

/**
 * Created by Shawaf on 9/11/2015.
 */
public class SearchViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public SearchViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if (Titles[position].equals("PRODUCTS")) // if the position is 0 we are returning the First tab
        {
            UsersFragment tab1 = new UsersFragment();
            return tab1;
        } else if (Titles[position].equals("USERS"))             // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            UsersFragment tab2 = new UsersFragment();
            return tab2;
        } else if (Titles[position].equals("STORIES")) {
            StoriesFragment tab3 = new StoriesFragment();
            return tab3;
        } else {
            return null;
        }
    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }

}