package com.oncoti.Fragments;

import android.app.Activity;
import android.content.Intent;
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

import com.oncoti.ActivityClasses.MainActivity;
import com.oncoti.ActivityClasses.AddNewHeadlineActivity;
import com.oncoti.Adapters.HeadlinesListAdapter;
import com.oncoti.ItemDecoration.SpacesItemDecoration;
import com.oncoti.Models.HeadlineModel;
import com.oncoti.R;
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
 * Created by Shawaf on 9/3/2015.
 */
public class HeadlinesFragment extends Fragment {

    private View v;
    private MainActivity mainActivity;
    private RecyclerView headlinesLV;
    private FloatingActionButton fab_newHeadline;
    private ArrayList<HeadlineModel> headlineModels;
    private ParseUser parseUser;
    private LinearLayout headlineListProgLay;
    private TextView noheadlineTV;
    private ProgressBar headlinelistProgPB;
    private String userUsername;
    private String ownerImage, headlineImage;
    private String  postText;
    private Date postTime;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.headlines_frag, container, false);

        initUI();
        parseUser = ParseUser.getCurrentUser();
        loadHeadlines();
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
        mainActivity.setMainTitleandSelection("HeadLines", 3);
    }

    private void initUI() {
        headlineListProgLay = (LinearLayout) v.findViewById(R.id.headlineslist_progress_lay);
        noheadlineTV = (TextView) v.findViewById(R.id.headlineslist_noprod_tv);
        headlinelistProgPB = (ProgressBar) v.findViewById(R.id.headlineslist_pb);
        fab_newHeadline = (FloatingActionButton) v.findViewById(R.id.headlines_fabbtn);
        headlinesLV = (RecyclerView) v.findViewById(R.id.headlines_lv);
        headlinesLV.setLayoutManager(new LinearLayoutManager(getActivity()));


        fab_newHeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newHeadlineIntent = new Intent(getActivity(), AddNewHeadlineActivity.class);
                startActivity(newHeadlineIntent);

            }
        });

    }



    private List<HeadlineModel> loadHeadlines() {
        headlineModels = new ArrayList<>();

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
        ParseQuery<ParseObject> query_product = ParseQuery.getQuery("Headlines");
        query_product.whereMatchesQuery("owner", query);
        query_product.include("owner");
        query_product.orderByDescending("createdAt");
        query_product.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> headlinsList, ParseException e) {

                if (e == null) {
                    for (ParseObject headline : headlinsList) {
                        String headlineId = headline.getObjectId();
                        ParseObject userHeadline = headline.getParseUser("owner");
                        userUsername = userHeadline.getString("username");
                        ownerImage = getImageUrl(userHeadline, "avatar");
                        postTime = headline.getCreatedAt();
                        headlineImage = getImageUrl(headline, "picture");
                        postText = headline.get("message").toString();
                        headlineModels.add(new HeadlineModel(userUsername, ownerImage, headlineImage, postText, postTime, "0", "0"));
                    }
                    fillHeadlineList(headlineModels);
                } else {
                    Log.e("Result", e.getMessage());
                    Toast.makeText(getActivity(), "Error : " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });
        return headlineModels;
    }

    private void fillHeadlineList(ArrayList<HeadlineModel> headlineModels) {
        if (headlineModels.size() != 0) {
            headlineListProgLay.setVisibility(View.GONE);
            HeadlinesListAdapter adapter = new HeadlinesListAdapter(getActivity(), headlineModels);
            headlinesLV.setAdapter(adapter);
            SpacesItemDecoration decoration = new SpacesItemDecoration(16);
            headlinesLV.addItemDecoration(decoration);

        } else {
            headlineListProgLay.setVisibility(View.VISIBLE);
            headlinelistProgPB.setVisibility(View.GONE);
            noheadlineTV.setVisibility(View.VISIBLE);
        }
    }

    private String getImageUrl(ParseObject userProd, String key) {

        ParseFile pict1File = (ParseFile) userProd.get(key);
        if (pict1File != null) {
            return pict1File.getUrl();
        }
        return null;
    }
}
