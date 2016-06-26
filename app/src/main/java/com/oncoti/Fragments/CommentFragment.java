package com.oncoti.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.oncoti.ActivityClasses.MainActivity;
import com.oncoti.ActivityClasses.ProductDetailsActivity;
import com.oncoti.Adapters.CommentsListAdapter;
import com.oncoti.Adapters.HeadlinesListAdapter;
import com.oncoti.Models.CommentModel;
import com.oncoti.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shawaf on 9/11/2015.
 */
public class CommentFragment extends Fragment{
    private View v;
    private ProductDetailsActivity prodDetailsActivity;
    private RecyclerView commentsLV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.comment_frag,container,false);

        initUI();

        return v;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        prodDetailsActivity=(ProductDetailsActivity)activity;
    }

    @Override
    public void onResume() {
        super.onResume();
        prodDetailsActivity.setTitle("Comments");

    }

    private void initUI() {

        commentsLV = (RecyclerView) v.findViewById(R.id.comments_lv);
        commentsLV.setLayoutManager(new LinearLayoutManager(getActivity()));
        commentsLV.setAdapter(new CommentsListAdapter(getActivity(), generlteComments()));
    }

    private List<CommentModel> generlteComments(){
        List<CommentModel> commentModels=new ArrayList<>();
        commentModels.add(new CommentModel(null,"Shawaf","3 days ago","Test Comments To show how the comments list will look like "));
        commentModels.add(new CommentModel(null,"Shawaf","3 days ago","Test Comments To show how the comments list will look like "));
        commentModels.add(new CommentModel(null,"Shawaf","3 days ago","Test Comments To show how the comments list will look like "));
        commentModels.add(new CommentModel(null,"Shawaf","3 days ago","Test Comments To show how the comments list will look like "));
        commentModels.add(new CommentModel(null,"Shawaf","3 days ago","Test Comments To show how the comments list will look like "));


        return commentModels;
    }
}
