package com.oncoti.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.oncoti.Fragments.KnocksFragment;
import com.oncoti.Fragments.MyShurasFragment;
import com.oncoti.Fragments.OurShurasFragment;
import com.oncoti.Fragments.ProductsFragment;
import com.oncoti.Fragments.SignupFragment;

/**
 * Created by Edwin on 15/02/2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if (Titles[position].equals("PRODUCTS")) // if the position is 0 we are returning the First tab
        {
            ProductsFragment tab1 = new ProductsFragment();
            return tab1;
        } else if (Titles[position].equals("KNOCKS"))             // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            KnocksFragment tab2 = new KnocksFragment();
            return tab2;
        } else if (Titles[position].equals("MY SHURAS")) {
            MyShurasFragment myShurasFragment = new MyShurasFragment();
            return myShurasFragment;
        } else if (Titles[position].equals("OUR SHURAS")) {
            OurShurasFragment ourShurasFragment = new OurShurasFragment();
            return ourShurasFragment;
        } else {
            SignupFragment signupFragment = new SignupFragment();
            return signupFragment;
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