package com.oncoti.ActivityClasses;

import android.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.oncoti.Fragments.ChatFragment;
import com.oncoti.R;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener{

    private FragmentManager fm;
    private android.support.v4.app.FragmentTransaction ft;
    private Toolbar mToolbar;
    private TextView mainTitle;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initFragment();
        initToolbar();
        setTitle();
    }
    public void setTitle(){
        String chatUserName=getIntent().getExtras().getString("chat_user");
        mainTitle.setText(chatUserName);

    }
    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.chat_toolbar);
        mainTitle = (TextView) mToolbar.findViewById(R.id.chat_toolbar_titletv);
        backBtn = (ImageButton) mToolbar.findViewById(R.id.chat_toolbar_backbtn);
        setSupportActionBar(mToolbar);

        backBtn.setOnClickListener(this);

    }

    private void initFragment() {
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();

        ChatFragment chatFragment=new ChatFragment();
        ft.replace(R.id.chat_fragholder,chatFragment);
        ft.commit();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.chat_toolbar_backbtn:
                finish();
                break;
        }
    }
}
