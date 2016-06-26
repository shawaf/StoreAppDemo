package com.oncoti.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oncoti.Adapters.CategoryListAdapter;
import com.oncoti.Adapters.ProductsStaggGridAdapter;
import com.oncoti.ItemDecoration.SpacesItemDecoration;
import com.oncoti.Models.CategoryModel;
import com.oncoti.Models.ProductModel;
import com.oncoti.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shawaf on 9/11/2015.
 */
public class SearchByCategoryFragment extends Fragment {
    private View v;
    private RecyclerView categoriesGridView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.searchbycat_frag, container, false);

        initCategoryList();

        return v;
    }

    private void initCategoryList() {
        categoriesGridView=(RecyclerView)v.findViewById(R.id.categories_grid);
        categoriesGridView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        CategoryListAdapter adapter = new CategoryListAdapter(getActivity(),generateCatModels());
        categoriesGridView.setAdapter(adapter);
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        categoriesGridView.addItemDecoration(decoration);
    }

    private List<CategoryModel> generateCatModels() {
        List<CategoryModel> categoryModels=new ArrayList<>();

        categoryModels.add(new CategoryModel(0,"Food"));
        categoryModels.add(new CategoryModel(0,"Clothes"));
        categoryModels.add(new CategoryModel(0,"Electronics"));
        categoryModels.add(new CategoryModel(0,"Gifts"));
        categoryModels.add(new CategoryModel(0,"Cosmetics"));
        categoryModels.add(new CategoryModel(0,"Perfumes"));


        return categoryModels;
    }
}
