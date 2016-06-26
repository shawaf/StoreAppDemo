package com.oncoti.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.etsy.android.grid.util.DynamicHeightImageView;
import com.oncoti.Models.CategoryModel;
import com.oncoti.R;

import java.util.List;

/**
 * Created by Shawaf on 9/11/2015.
 */
public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryGridView>{
    private Activity activity;
    private List<CategoryModel> categoryModels;
    public CategoryListAdapter(Activity activity,List<CategoryModel> categoryModels){
        this.activity=activity;
        this.categoryModels=categoryModels;
    }
    @Override
    public CategoryGridView onCreateViewHolder(ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.categories_grid_item, viewGroup, false);
        CategoryGridView categoryGridView = new CategoryGridView(layoutView);
        return categoryGridView;
    }

    @Override
    public void onBindViewHolder(CategoryGridView holder, int position) {
        holder.catName.setText(categoryModels.get(position).getCatName());
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    class CategoryGridView extends RecyclerView.ViewHolder {

        TextView catName;
        ImageView catImage;

        public CategoryGridView(View itemView) {
            super(itemView);

            catImage = (ImageView) itemView
                    .findViewById(R.id.category_image);
            catName = (TextView) itemView
                    .findViewById(R.id.category_name);
        }
    }
}
