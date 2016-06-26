package com.oncoti.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oncoti.Adapters.KnocksStaggGridAdapter;
import com.oncoti.Adapters.ShurasListAdapter;
import com.oncoti.ItemDecoration.SpacesItemDecoration;
import com.oncoti.Models.ShuraModel;
import com.oncoti.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shawaf on 9/4/2015.
 */
public class MyShurasFragment extends Fragment {

    private View v;
    private RecyclerView myShurasList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.myshuras_frag,container,false);

        initLV();
        return v;
    }
    private void initLV() {
        myShurasList=(RecyclerView)v.findViewById(R.id.myshuras_list);
        myShurasList.setLayoutManager(new LinearLayoutManager(getActivity()));

        ShurasListAdapter adapter = new ShurasListAdapter(getActivity(),generateShurasModel());
        myShurasList.setAdapter(adapter);
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        myShurasList.addItemDecoration(decoration);
    }
    private List<ShuraModel> generateShurasModel(){
        List<ShuraModel> shuraModels=new ArrayList<>();
        shuraModels.add(new ShuraModel(null,"Shawaf","Abaya With White and Black Colors and red bibon was added","1500","5","34"));
        shuraModels.add(new ShuraModel(null,"Client","Suite","3000","5","27"));
        shuraModels.add(new ShuraModel(null,"Shushi dubai","Hp Pavilion Laptop","2500","5","10"));
        shuraModels.add(new ShuraModel(null,"MMMM","Samsung Note5","300","5","55"));
        shuraModels.add(new ShuraModel(null,"Max","Abaya With White and Black Colors and red bibon was added","500","5","34"));
        shuraModels.add(new ShuraModel(null,"Joun","Mac Laptop","5000","5","54"));
        return shuraModels;
    }

}
