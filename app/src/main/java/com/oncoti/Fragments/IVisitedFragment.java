package com.oncoti.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.oncoti.ActivityClasses.AddNewVisitAcitivty;
import com.oncoti.ActivityClasses.MainActivity;
import com.oncoti.Adapters.IVisitedListAdapter;
import com.oncoti.ItemDecoration.SpacesItemDecoration;
import com.oncoti.Models.VisitModel;
import com.oncoti.R;
import com.oncoti.Utilites.ConnectionDetector;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Shawaf on 9/4/2015.
 */
public class IVisitedFragment extends Fragment {
    private View v;
    private FloatingActionButton ivisitFab;
    private RecyclerView ivisitLV;
    private MainActivity mainActivity;
    private ArrayList<VisitModel> visitModels;
    private ParseUser parseUser;
    private ConnectionDetector cd;
    private LinearLayout visitListProgLay;
    private TextView novisitTV;
    private ProgressBar visitlistProgPB;
    private ArrayList<String> iBitmaps;
    private Bitmap userImage;
    private String curKey;
    private String userUsername;
    private String ownerImage;
    private String storeName, address, doing;
    private Date uploadTime;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.ivisited_frag, container, false);

        parseUser = ParseUser.getCurrentUser();
        cd = new ConnectionDetector(getActivity());
        initUI();
        loadVisits();
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
        mainActivity.setMainTitleandSelection("I Visited", 4);
    }

    private void initUI() {
        ivisitFab = (FloatingActionButton) v.findViewById(R.id.ivisit_fabbtn);
        ivisitLV = (RecyclerView) v.findViewById(R.id.ivisit_lv);
        visitListProgLay = (LinearLayout) v.findViewById(R.id.visitlist_progress_lay);
        novisitTV = (TextView) v.findViewById(R.id.visitlist_noprod_tv);
        visitlistProgPB = (ProgressBar) v.findViewById(R.id.visitlist_pb);
        ivisitLV.setLayoutManager(new LinearLayoutManager(getActivity()));


        ivisitFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addNewVisitIntent = new Intent(getActivity(), AddNewVisitAcitivty.class);
                startActivity(addNewVisitIntent);

            }
        });

    }



    private List<VisitModel> loadVisits() {
        visitModels = new ArrayList<>();

        List<String> arr = new ArrayList<String>();
        if (parseUser.getList("following") != null) {
            Log.e("ProductsFragment", "There's Following peapole");
            arr = parseUser.getList("following");
        }
        arr.add(parseUser.getObjectId());
//        arr.add("7B1ojCQXFD");
        arr.add("XHPFTaWOOI");
        arr.add("5H4MVAGZED");
//        Log.e("ProductsTabFragment", "Array Size : " + parseUser.getList("following").size());
        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
        query.whereContainedIn("objectId", arr);
        ParseQuery<ParseObject> query_product = ParseQuery.getQuery("Ivisited");
        query_product.whereMatchesQuery("owner", query);
        query_product.include("owner");
        query_product.orderByDescending("createdAt");
        query_product.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> visitList, ParseException e) {

                if (e == null) {
                    for (ParseObject visit : visitList) {
                        String productId = visit.getObjectId();
                        ParseObject userProd = visit.getParseUser("owner");
                        userUsername = userProd.getString("username");
                        ownerImage = getOwnerBitmap(userProd, "avatar");
                        uploadTime = visit.getCreatedAt();
                        storeName = visit.get("store_name").toString();
                        address = visit.get("adresss").toString();
                        doing = visit.get("dowing").toString();
                        getBitmaps(visit);

                    }
                    fillVisitList(visitModels);
                } else {
                    Log.e("Result", e.getMessage());
                    Toast.makeText(getActivity(), "Error : " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });
        return visitModels;
    }

    private void fillVisitList(ArrayList<VisitModel> productModels) {

        if (productModels.size() != 0) {
            visitListProgLay.setVisibility(View.GONE);
            IVisitedListAdapter adapter = new IVisitedListAdapter(getActivity(), visitModels);
            ivisitLV.setAdapter(adapter);
            SpacesItemDecoration decoration = new SpacesItemDecoration(16);
            ivisitLV.addItemDecoration(decoration);

        } else {
            visitListProgLay.setVisibility(View.VISIBLE);
            visitlistProgPB.setVisibility(View.GONE);
            novisitTV.setVisibility(View.VISIBLE);
        }
    }

    private void getBitmaps(ParseObject product) {
        iBitmaps = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            curKey = "picture" + i;
            Log.e("ProductsFragment", curKey + " is downloading");
            ParseFile pict1File = (ParseFile) product.get(curKey);
            if (pict1File != null) {

                Log.e("ProductsFragment", curKey + pict1File.getUrl());
                //Bitmap bmp = BitmapFactory.decodeByteArray(pict1File.getData(), 0, pict1File.getData().length);
                iBitmaps.add(pict1File.getUrl());

            }
        }
        visitModels.add(new VisitModel(ownerImage,userUsername,storeName,address,uploadTime,doing,iBitmaps,"0","0"));

    }

    private String getOwnerBitmap(ParseObject userProd, String key) {
        userImage = null;
        ParseFile pict1File = (ParseFile) userProd.get(key);
        if (pict1File != null) {
            return pict1File.getUrl();
        }
        return null;
    }

}
