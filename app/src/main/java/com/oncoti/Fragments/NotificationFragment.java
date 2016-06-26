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
import com.oncoti.Adapters.NotificationListAdapter;
import com.oncoti.Models.NotificationModel;
import com.oncoti.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shawaf on 9/5/2015.
 */
public class NotificationFragment extends Fragment {
    private View v;
    private MainActivity mainActivity;
    private RecyclerView notifLV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.notification_frag, container, false);
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
        mainActivity.setMainTitleandSelection(getActivity().getResources().getString(R.string.notifictions), 7);
    }

    private void initUI() {

        notifLV = (RecyclerView) v.findViewById(R.id.notif_lv);
        notifLV.setLayoutManager(new LinearLayoutManager(getActivity()));
        notifLV.setAdapter(new NotificationListAdapter(getActivity(), generateNotifiModelList()));

//        ListItemDecoration decoration = new ListItemDecoration(2);
//        headlinesLV.addItemDecoration(decoration);


    }

    private List<NotificationModel> generateNotifiModelList() {

        List<NotificationModel> notificationModels = new ArrayList<>();
        notificationModels.add(new NotificationModel(null, "Shawaf", "commented on your post", "2 h"));
        notificationModels.add(new NotificationModel(null, "Shushi dubai", "followed you", "10 h"));
        notificationModels.add(new NotificationModel(null, "Elite", "like your last post", "15 h"));
        notificationModels.add(new NotificationModel(null, "Joun", "commented on your post", "22 h"));
        notificationModels.add(new NotificationModel(null, "Jack warner", "liked and commented on your post", "1 d"));
        notificationModels.add(new NotificationModel(null, "Sara", "likes your shura post", "3 d"));
        notificationModels.add(new NotificationModel(null, "Shawaf", "commented on your post", "10 d"));
        notificationModels.add(new NotificationModel(null, "Shushi dubai", "commented on your post", "11 d"));

        return notificationModels;
    }
}
