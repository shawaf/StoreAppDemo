package com.oncoti.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.etsy.android.grid.util.DynamicHeightImageView;
import com.google.gson.Gson;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.oncoti.ActivityClasses.ProductDetailsActivity;
import com.oncoti.Models.ProductModel;
import com.oncoti.R;
import com.oncoti.Utilites.CommonMethods;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Shawaf on 9/2/2015.
 */
public class ProductsStaggGridAdapter extends RecyclerView.Adapter<ProductsStaggGridAdapter.StaggeredGridView>  {

    private Activity activity;
    private List<ProductModel> productModels;
    private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();
    private final Random mRandom;
    private int[] images = new int[]{R.drawable.street, R.drawable.forest, R.drawable.batman, R.drawable.street, R.drawable.forest, R.drawable.batman, R.drawable.street, R.drawable.forest, R.drawable.batman, R.drawable.batman};

    public ProductsStaggGridAdapter(Activity activity, List<ProductModel> productModels) {
        this.activity = activity;
        this.productModels = productModels;
        mRandom = new Random();

    }

    @Override
    public StaggeredGridView onCreateViewHolder(ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_grid_item, viewGroup, false);
        StaggeredGridView stagGridView = new StaggeredGridView(layoutView);
        return stagGridView;
    }

    @Override
    public void onBindViewHolder(final StaggeredGridView holder, int position) {
        double positionHeight = getPositionRatio(position);
        holder.imgView.setHeightRatio(positionHeight);
        UrlImageViewHelper.setUrlDrawable(holder.imgView, productModels.get(position).getProdImageUrls().get(0), R.drawable.logo1);
       // holder.imgView.setImageBitmap(productModels.get(position).getProdImageUrls().get(0));
        holder.prodOwnerName.setText(productModels.get(position).getOwnerName());
        UrlImageViewHelper.setUrlDrawable(holder.prodOwnerImage, productModels.get(position).getOwnerImage(), R.drawable.logo1);
        //holder.prodOwnerImage.setImageBitmap(productModels.get(position).getOwnerImage());
        int postTime= CommonMethods.getDaysBetween(productModels.get(position).getUploadTime());
        if (postTime == 0) {
            holder.prodTime.setText("Today");
        } else {
            holder.prodTime.setText(postTime + " days ago");
        }
        holder.prodName.setText(productModels.get(position).getProdName());
        holder.prodPrice.setText(productModels.get(position).getPrice());
        holder.prodCat.setText(productModels.get(position).getCategory());
        holder.likes.setText(productModels.get(position).getWowCounter()+"");
        holder.comments.setText(productModels.get(position).getCommentsCounter()+"");

        holder.likesLay.setTag(position);
        holder.commentsLay.setTag(position);
        holder.likesLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable likeInactiveBg=activity.getResources().getDrawable(R.drawable.like_inactive);
                Drawable likeactiveBg=activity.getResources().getDrawable(R.drawable.like_active);
                if(holder.likeIcon.getBackground() == likeInactiveBg){
                    holder.likeIcon.setBackgroundResource(R.drawable.like_active);
                }else if(holder.likeIcon.getBackground() == likeactiveBg){
                    holder.likeIcon.setBackgroundResource(R.drawable.like_inactive);
                }
            }
        });
        holder.commentsLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable commentInactiveBg=activity.getResources().getDrawable(R.drawable.comment);
                Drawable commentactiveBg=activity.getResources().getDrawable(R.drawable.comment_option);
                if(holder.likeIcon.getBackground() == commentInactiveBg){
                    holder.likeIcon.setBackgroundResource(R.drawable.comment_option);
                }else if(holder.likeIcon.getBackground() == commentactiveBg){
                    holder.likeIcon.setBackgroundResource(R.drawable.comment);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    class StaggeredGridView extends RecyclerView.ViewHolder implements View.OnClickListener {
        DynamicHeightImageView imgView;
        TextView prodOwnerName;
        ImageView prodOwnerImage;
        TextView prodTime;
        TextView prodName;
        TextView prodPriceUnit;
        TextView prodPrice;
        TextView prodCat;
        TextView likes;
        TextView comments;
        ImageView likeIcon;
        ImageView commentIcon;
        LinearLayout likesLay;
        LinearLayout commentsLay;

        public StaggeredGridView(View itemView) {
            super(itemView);

            imgView = (DynamicHeightImageView) itemView.findViewById(R.id.prodlist_item_prod_iv);
            prodOwnerImage = (ImageView) itemView.findViewById(R.id.prodlist_item_prod_owner_iv);
            prodOwnerName = (TextView) itemView.findViewById(R.id.prodlist_item_prod_ownername_tv);
            prodTime = (TextView) itemView.findViewById(R.id.prodlist_item_prod_time);
            prodName = (TextView) itemView.findViewById(R.id.prodlist_item_prod_name);
            prodPriceUnit = (TextView) itemView.findViewById(R.id.prodlist_item_prod_price_unit);
            prodPrice = (TextView) itemView.findViewById(R.id.prodlist_item_prod_price);
            prodCat = (TextView) itemView.findViewById(R.id.prodlist_item_prod_category);
            likes = (TextView) itemView.findViewById(R.id.prodlist_item_prod_like_text);
            comments = (TextView) itemView.findViewById(R.id.prodlist_item_prod_comment_text);
            likeIcon = (ImageView) itemView.findViewById(R.id.prodlist_item_prod_like_icon);
            commentIcon = (ImageView) itemView.findViewById(R.id.prodlist_item_prod_comment_icon);
            likesLay = (LinearLayout) itemView.findViewById(R.id.prodlist_item_prod_like_lay);
            commentsLay = (LinearLayout) itemView.findViewById(R.id.prodlist_item_prod_comments_lay);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int itemPos = getLayoutPosition();

            Intent prodDetailsIntent = new Intent(activity, ProductDetailsActivity.class);
            prodDetailsIntent.putExtra("item_pos", itemPos);
            Gson gS = new Gson();
            String target = gS.toJson(productModels.get(itemPos));
            prodDetailsIntent.putExtra("prod_model",target);
            activity.startActivity(prodDetailsIntent);
        }
    }

    private double getPositionRatio(final int position) {
        double ratio = sPositionHeightRatios.get(position, 0.0);
        // if not yet done generate and stash the columns height
        // in our real world scenario this will be determined by
        // some match based on the known height and width of the image
        // and maybe a helpful way to get the column height!
        if (ratio == 0) {
            ratio = getRandomHeightRatio();
            sPositionHeightRatios.append(position, ratio);
            Log.d("StaggeredGridAdapter", "getPositionRatio:" + position + " ratio:" + ratio);
        }
        return ratio;
    }

    private double getRandomHeightRatio() {
        return (mRandom.nextDouble() / 2.0) + 1.0; // height will be 1.0 - 1.5
        // the width
    }

    private void addLike(){

    }
    private void removeLike(){
    }


}
