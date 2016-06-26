package com.oncoti.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oncoti.ActivityClasses.ChatActivity;
import com.oncoti.Adapters.ChatListAdapter;
import com.oncoti.Models.ChatModel;
import com.oncoti.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shawaf on 9/11/2015.
 */
public class ChatFragment  extends Fragment{
    private View v;
    private ChatActivity chatActivity;
    private RecyclerView chatLV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.chat_frag,container,false);
        initUI();

        return v;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
       chatActivity =(ChatActivity)activity;
    }

    @Override
    public void onResume() {
        super.onResume();
        //chatActivity.setTitle("HeadLines");
    }

    private void initUI(){

        chatLV=(RecyclerView)v.findViewById(R.id.chat_lv);
        chatLV.setLayoutManager(new LinearLayoutManager(getActivity()));
        chatLV.setAdapter(new ChatListAdapter(getActivity(), generateChatList()));


    }

    private List<ChatModel> generateChatList() {
        List<ChatModel> chatModels=new ArrayList<>();
        chatModels.add(new ChatModel(false,"Carl Pamr","Hello there !!","1:30"));
        chatModels.add(new ChatModel(true,"Shawaf","Hi there !!","1:34"));
        chatModels.add(new ChatModel(false,"Carl Pamr","How are You ?","1:37"));
        chatModels.add(new ChatModel(true,"Shawaf","I'm Fine thank you and what about you ? ","1:40"));
        chatModels.add(new ChatModel(false,"Carl Pamr","I'm great , i want to ask you about something in the order do you have som freetime to talk ?","1:45"));
        chatModels.add(new ChatModel(true,"Shawaf","yes of course ask what you want.","1:50"));
        chatModels.add(new ChatModel(false,"Carl Pamr",getResources().getString(R.string.test_post),"1:55"));

        return chatModels;
    }


}
