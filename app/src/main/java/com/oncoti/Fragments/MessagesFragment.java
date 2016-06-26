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

import com.oncoti.ActivityClasses.MainActivity;
import com.oncoti.Adapters.MessagesListAdapter;
import com.oncoti.Adapters.NotificationListAdapter;
import com.oncoti.Models.MessagesModel;
import com.oncoti.Models.NotificationModel;
import com.oncoti.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shawaf on 9/5/2015.
 */
public class MessagesFragment extends Fragment {

    private View v;
    private MainActivity mainActivity;
    private RecyclerView messagesLv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.msg_frag,container,false);
        initUI();

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity) activity;
    }

    @Override
    public void onResume() {
        super.onResume();
        mainActivity.setMainTitleandSelection(getActivity().getResources().getString(R.string.messages), 8);
    }

    private void initUI() {

        messagesLv = (RecyclerView) v.findViewById(R.id.messages_lv);
        messagesLv.setLayoutManager(new LinearLayoutManager(getActivity()));
        messagesLv.setAdapter(new MessagesListAdapter(getActivity(), generateMessageModels()));

//        ListItemDecoration decoration = new ListItemDecoration(2);
//        headlinesLV.addItemDecoration(decoration);


    }

    private List<MessagesModel> generateMessageModels(){
        List<MessagesModel> messagesModels=new ArrayList<>();
        messagesModels.add(new MessagesModel(null, "Shawaf", "Welecome To chat", "2 h"));
        messagesModels.add(new MessagesModel(null, "Shushi dubai", "How Are You ?", "10 h"));
        messagesModels.add(new MessagesModel(null, "Elite", "Did you like my last Product ?", "15 h"));
        messagesModels.add(new MessagesModel(null, "Joun", "contact Me when you are online ", "22 h"));
        messagesModels.add(new MessagesModel(null, "Jack warner", "where are you from", "1 d"));
        messagesModels.add(new MessagesModel(null, "Sara", "you product is ready for shipping", "3 d"));
        messagesModels.add(new MessagesModel(null, "Shawaf", "Contact us on 9405039504", "10 d"));
        messagesModels.add(new MessagesModel(null, "Shushi dubai", "Hi My friend !", "11 d"));

        return messagesModels;
    }

}
