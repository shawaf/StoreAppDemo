package com.oncoti.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oncoti.R;

/**
 * Created by Shawaf on 9/11/2015.
 */
public class StoriesFragment extends Fragment{
    private View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.stories_frag,container,false);

        return v;
    }
}
