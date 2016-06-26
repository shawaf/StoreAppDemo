package com.oncoti.ActivityClasses;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.oncoti.Adapters.NavDrawerAdapter;
import com.oncoti.Adapters.ViewPagerAdapter;
import com.oncoti.Fragments.AboutFragment;
import com.oncoti.Fragments.HeadlinesFragment;
import com.oncoti.Fragments.HomeFragment;
import com.oncoti.Fragments.IVisitedFragment;
import com.oncoti.Fragments.MessagesFragment;
import com.oncoti.Fragments.NotificationFragment;
import com.oncoti.Fragments.OrderFragment;
import com.oncoti.Fragments.ShurasFragment;
import com.oncoti.Models.NavDrawerItem_Model;
import com.oncoti.R;
import com.oncoti.SlidingTab.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private Toolbar mToolbar;
    private View toolbrShadow;
    private ImageButton sideMenuBtn;
    private ImageButton searchBtn;
    private LinearLayout searchLay;
    private TextView mainTitle;
    private ViewPager pager;
    private ViewPagerAdapter adapter;
    private SlidingTabLayout tabs;
    private FloatingActionButton fab;

    int Numboftabs = 2;
    private DrawerLayout Drawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView navDrawerLV;
    private View navDrawerListHeader;
    private NavDrawerAdapter mAdapter;
    private List<NavDrawerItem_Model> navDrawerItem_models;
    private CharSequence[] Hometitles;
    private FragmentManager fm;
    private FragmentTransaction ft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initToolbar();
        initFragment();
        initNavDrawer();
    }

    public void setMainTitleandSelection(String title, int selectedItem) {
        if (title.equals("Home") || title.equals("Shuras")) {
            toolbrShadow.setVisibility(View.GONE);
        }
        mainTitle.setText(title);
        navDrawerLV.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        navDrawerLV.setItemChecked(selectedItem, true);

    }

    private void initToolbar() {
        toolbrShadow = findViewById(R.id.toolbar_shadow);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mainTitle = (TextView) mToolbar.findViewById(R.id.main_toolbar_titletv);
        sideMenuBtn = (ImageButton) mToolbar.findViewById(R.id.main_toolbar_menubtn);
        searchBtn=(ImageButton)mToolbar.findViewById(R.id.main_toolbar_searchbtn);
        searchLay=(LinearLayout)mToolbar.findViewById(R.id.main_toolbar_searchlay);
        setSupportActionBar(mToolbar);

        sideMenuBtn.setOnClickListener(this);
        searchLay.setOnClickListener(this);
        searchBtn.setOnClickListener(this);

    }

    private void initFragment() {
        toolbrShadow.setVisibility(View.GONE);
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        ft.replace(R.id.contentholder, homeFragment, "home");
        ft.commit();
    }

    private void initNavDrawer() {
        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer,
                mToolbar, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {

            }

            public void onDrawerOpened(View drawerView) {

            }
        };
        Drawer.setDrawerListener(mDrawerToggle);
        navDrawerLV = (ListView) findViewById(R.id.side_menu_lv);

        navDrawerListHeader = getLayoutInflater().inflate(R.layout.nav_drawer_header, navDrawerLV, false);

        mAdapter = new NavDrawerAdapter(MainActivity.this, fillNavList());
        navDrawerLV.addHeaderView(navDrawerListHeader, null, false);
        navDrawerLV.setAdapter(mAdapter);
        navDrawerLV.setOnItemClickListener(this);

    }

    private List<NavDrawerItem_Model> fillNavList() {
        String[] titles = new String[]{"Home", "Profile", "Headlines", "I Visited", "Shuras", "", "Notifications", "Messages", "Orders", "", "About"};
        int[] icons = new int[]{R.drawable.side_menu_home, R.drawable.side_menu_user,
                R.drawable.side_menu_headline, R.drawable.side_menu_pvisit,
                R.drawable.side_menu_shuras, 0, R.drawable.side_menu_notif,
                R.drawable.side_menu_msg, R.drawable.side_menu_orders, 0, 0};
        int[] counts = new int[]{0, 0, 0, 0, 0, 0, 7, 3, 10, 0, 0};
        NavDrawerItem_Model navDrawerItem_model;
        navDrawerItem_models = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            navDrawerItem_model = new NavDrawerItem_Model(titles[i], icons[i], counts[i]);
            navDrawerItem_models.add(navDrawerItem_model);
        }
        return navDrawerItem_models;

    }


    public void openFragment(String selected) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        switch (selected) {
            case "Home":
                //Check if fragment is visible
                HomeFragment homeVisible = (HomeFragment) getSupportFragmentManager().findFragmentByTag("home");
                if (!(homeVisible != null && homeVisible.isVisible())) {
                    //Clear BackStack
                    fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    toolbrShadow.setVisibility(View.GONE);
                    HomeFragment homeFragment = new HomeFragment();
                    ft.replace(R.id.contentholder, homeFragment, "home");
                    // ft.addToBackStack(null);
                    ft.commit();
                }
                break;
            case "Headlines":
                HeadlinesFragment headlineVisible = (HeadlinesFragment) getSupportFragmentManager().findFragmentByTag("headlines");
                if (!(headlineVisible != null && headlineVisible.isVisible())) {

                    toolbrShadow.setVisibility(View.VISIBLE);
                    HeadlinesFragment headlinesFragment = new HeadlinesFragment();
                    ft.replace(R.id.contentholder, headlinesFragment, "headlines");
                    ft.addToBackStack(null);
                    ft.commit();
                }
                break;
            case "Shuras":
                ShurasFragment shurasVisible = (ShurasFragment) getSupportFragmentManager().findFragmentByTag("shurs");
                if (!(shurasVisible != null && shurasVisible.isVisible())) {

                    toolbrShadow.setVisibility(View.GONE);
                    ShurasFragment shurasFragment = new ShurasFragment();
                    ft.replace(R.id.contentholder, shurasFragment, "shurs");
                    ft.addToBackStack(null);
                    ft.commit();
                }
                break;
            case "I Visited":
                IVisitedFragment ivistedVisible = (IVisitedFragment) getSupportFragmentManager().findFragmentByTag("ivisited");
                if (!(ivistedVisible != null && ivistedVisible.isVisible())) {
                    toolbrShadow.setVisibility(View.VISIBLE);
                    IVisitedFragment iVisitedFragment = new IVisitedFragment();
                    ft.replace(R.id.contentholder, iVisitedFragment, "ivisited");
                    ft.addToBackStack(null);
                    ft.commit();
                }

                break;
            case "Notifications":
                NotificationFragment notifVisibleFrag = (NotificationFragment) getSupportFragmentManager().findFragmentByTag("notif");
                if (!(notifVisibleFrag != null && notifVisibleFrag.isVisible())) {
                    toolbrShadow.setVisibility(View.VISIBLE);
                    NotificationFragment notificationFragment = new NotificationFragment();
                    ft.replace(R.id.contentholder, notificationFragment, "notif");
                    ft.addToBackStack(null);
                    ft.commit();
                }
                break;
            case "Messages":
                MessagesFragment messageVisibleFrag = (MessagesFragment) getSupportFragmentManager().findFragmentByTag("msg");
                if (!(messageVisibleFrag != null && messageVisibleFrag.isVisible())) {
                    toolbrShadow.setVisibility(View.VISIBLE);
                    MessagesFragment messagesFragment = new MessagesFragment();
                    ft.replace(R.id.contentholder, messagesFragment, "msg");
                    ft.addToBackStack(null);
                    ft.commit();
                }
                break;
            case "Orders":
                OrderFragment orderVisibleFragment = (OrderFragment) getSupportFragmentManager().findFragmentByTag("orders");
                if (!(orderVisibleFragment != null && orderVisibleFragment.isVisible())) {
                    toolbrShadow.setVisibility(View.VISIBLE);
                    OrderFragment orderFragment = new OrderFragment();
                    ft.replace(R.id.contentholder, orderFragment, "orders");
                    ft.addToBackStack(null);
                    ft.commit();
                }
                break;
            case "About":
                AboutFragment aboutFragmenttest = (AboutFragment) getSupportFragmentManager().findFragmentByTag("about");
                if (!(aboutFragmenttest != null && aboutFragmenttest.isVisible())) {
                    toolbrShadow.setVisibility(View.VISIBLE);
                    AboutFragment aboutFragment = new AboutFragment();
                    ft.replace(R.id.contentholder, aboutFragment, "about");
                    ft.addToBackStack(null);
                    ft.commit();
                }
                break;
            case "Profile":
                Handler h=new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent profileIntent=new Intent(MainActivity.this,ProfileActivity.class);
                        startActivity(profileIntent);
                        overridePendingTransition(R.anim.right_slide_in, R.anim.stop_animation);
                    }
                },220);
                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Drawer.closeDrawer(Gravity.LEFT);
        String selectedItem = navDrawerItem_models.get(i - 1).getName();
        mainTitle.setText(selectedItem);
        switch (selectedItem) {
            case "Home":
                openFragment("Home");
                break;
            case "Profile":
                openFragment(selectedItem);
                break;
            case "Headlines":
                openFragment(selectedItem);
                break;
            case "Shuras":
                openFragment(selectedItem);
                break;
            case "I Visited":
                openFragment(selectedItem);
                break;
            case "Notifications":
                openFragment(selectedItem);
                break;
            case "Messages":
                openFragment(selectedItem);
                break;
            case "Orders":
                openFragment(selectedItem);
                break;
            case "About":
                openFragment(selectedItem);
                break;
            default:
                openFragment("Home");
                break;


        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_toolbar_menubtn:
                Drawer.openDrawer(Gravity.LEFT);
                break;
            case R.id.main_toolbar_searchlay:
                openSearchActivity();
                break;
            case R.id.main_toolbar_searchbtn:
                openSearchActivity();
                break;

        }
    }

    private void openSearchActivity(){
        Intent searchIntent=new Intent(MainActivity.this,SearchActivity.class);
        startActivity(searchIntent);
    }
    @Override
    public void onBackPressed() {
        if (Drawer.isDrawerOpen(Gravity.LEFT)) {
            Drawer.closeDrawer(Gravity.LEFT);
        } else {
            super.onBackPressed();
        }
    }
}
