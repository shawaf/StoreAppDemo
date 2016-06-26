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
import com.oncoti.Adapters.OrderListAdapter;
import com.oncoti.Models.OrderModel;
import com.oncoti.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shawaf on 9/5/2015.
 */
public class OrderFragment extends Fragment {
    private View v;
    private MainActivity mainActivity;
    private RecyclerView orderLV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.order_frag,container,false);

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
        mainActivity.setMainTitleandSelection(getActivity().getResources().getString(R.string.orders), 9);
    }

    private void initUI() {

        orderLV = (RecyclerView) v.findViewById(R.id.orders_lv);
        orderLV.setLayoutManager(new LinearLayoutManager(getActivity()));
        orderLV.setAdapter(new OrderListAdapter(getActivity(), generateOrderModels()));

//        ListItemDecoration decoration = new ListItemDecoration(2);
//        headlinesLV.addItemDecoration(decoration);


    }

    private List<OrderModel> generateOrderModels(){
        List<OrderModel> orderModels=new ArrayList<>();
        orderModels.add(new OrderModel(null,"Shushi dubai","Abaya with colors Black and White","2 ahours ago","500","Waitting for Acceptance"));
        orderModels.add(new OrderModel(null,"Dubai Shop","A new Hp Pavilon NoteBook","8 ahours ago","1500","Shipped To Customer"));
        orderModels.add(new OrderModel(null,"Shawaf","Laptop MacBook 2015 Last Version","20 ahours ago","3000","Preparing For Ship"));
        orderModels.add(new OrderModel(null,"Best Seller","Italian Suite","1 day ago","800","Waitting for Acceptance"));
        orderModels.add(new OrderModel(null,"Huwawei Shop","Huwaei P8","2 days ago","1500","Waitting for Acceptance"));

        return orderModels;
    }
}
