package com.oncoti.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oncoti.ActivityClasses.MainActivity;
import com.oncoti.R;

/**
 * Created by Shawaf on 9/11/2015.
 */
public class AboutFragment extends Fragment {
    private View v;
    private MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.about_frag, container, false);

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
        mainActivity.setMainTitleandSelection(getActivity().getResources().getString(R.string.about), 11);
    }

}