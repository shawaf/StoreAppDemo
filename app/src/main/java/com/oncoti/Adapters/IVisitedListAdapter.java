package com.oncoti.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.oncoti.ActivityClasses.ProductDetailsActivity;
import com.oncoti.ActivityClasses.VisitDetailsActivity;
import com.oncoti.Models.VisitModel;
import com.oncoti.R;
import com.oncoti.Utilites.CommonMethods;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shawaf on 9/4/2015.
 */
public class IVisitedListAdapter extends RecyclerView.Adapter<IVisitedListAdapter.IVisitListView> {

    private Activity activity;
    private List<VisitModel> visitModels;

    public IVisitedListAdapter(Activity activity, List<VisitModel> visitModels) {
        this.activity = activity;
        this.visitModels = visitModels;
    }

    @Override
    public IVisitListView onCreateViewHolder(ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ivisited_list_item, viewGroup, false);
        IVisitListView headLinesListView = new IVisitListView(layoutView);
        return headLinesListView;
    }

    @Override
    public void onBindViewHolder(IVisitListView holder, int position) {
        UrlImageViewHelper.setUrlDrawable(holder.userImage, visitModels.get(position).getUserImageUrl(), R.drawable.logo1);
        holder.userName.setText(visitModels.get(position).getUserName());
        holder.placeName.setText(visitModels.get(position).getPlaceName());
        holder.placeLocation.setText(visitModels.get(position).getPlaceLocation());
        int postTime=CommonMethods.getDaysBetween(visitModels.get(position).getVisitTime());
        if(postTime==0){
            holder.visitTime.setText("Today");
        }else {
            holder.visitTime.setText(postTime+" days ago");
        }
        holder.visitDesc.setText(visitModels.get(position).getVisitDesc());
        ArrayList<String> visitImages = visitModels.get(position).getPlaceImages();
        if(holder.visitImagesLay.getChildCount() !=visitImages.size()) {
            if (visitImages.size() == 0) {
                holder.visitImagesLay.setVisibility(View.GONE);
            } else if (visitImages.size() == 1) {
                holder.visitImagesLay.setVisibility(View.VISIBLE);
                ImageView imageView = new ImageView(activity);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
//                imageView.setImageResource(R.drawable.street);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                holder.visitImagesLay.addView(imageView);
                UrlImageViewHelper.setUrlDrawable(imageView, visitImages.get(0), R.drawable.logo1);
            } else {
                holder.visitImagesLay.setVisibility(View.VISIBLE);
                String imageSource;
                for (int i = 0; i < visitImages.size(); i++) {
                    if (i == 0) {
                        imageSource =visitImages.get(0);
                    } else if (i == 1) {
                        imageSource = visitImages.get(1);
                    } else {
                        imageSource =visitImages.get(2);
                    }
                    ImageView imageView = new ImageView(activity);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(convertDpToPx(135), convertDpToPx(135));
                    layoutParams.setMargins(0, 0, convertDpToPx(8), 0);
                    imageView.setLayoutParams(layoutParams);
                  //  imageView.setImageResource(imageSource);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    holder.visitImagesLay.addView(imageView);
                    UrlImageViewHelper.setUrlDrawable(imageView, imageSource, R.drawable.logo1);
                }

            }
        }
        holder.visitLikesCount.setText(visitModels.get(position).getLikesNo());
        holder.visitCommentsCount.setText(visitModels.get(position).getCommentsNo());
    }

    public int getItemCount() {
        return visitModels.size();
    }

    class IVisitListView extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView userImage;
        TextView userName;
        TextView visitTime;
        TextView placeName;
        TextView placeLocation;
        LinearLayout placeLocationLay;
        TextView visitDesc;
        LinearLayout visitImagesLay;
        TextView visitLikesCount;
        TextView visitCommentsCount;
        ImageView visitLikeIcon;
        ImageView visitCommentIcon;

        public IVisitListView(View itemView) {
            super(itemView);
            userImage = (ImageView) itemView.findViewById(R.id.ivisited_userimage);
            userName = (TextView) itemView.findViewById(R.id.ivisited_username);
            visitTime = (TextView) itemView.findViewById(R.id.ivisited_time);
            placeName = (TextView) itemView.findViewById(R.id.ivisit_placename);
            placeLocation = (TextView) itemView.findViewById(R.id.ivisit_placelocation);
            placeLocationLay = (LinearLayout) itemView.findViewById(R.id.ivisit_placelocation_lay);
            visitDesc = (TextView) itemView.findViewById(R.id.ivisit_desc);
            visitImagesLay = (LinearLayout) itemView.findViewById(R.id.ivisit_images_lay);
            visitLikesCount = (TextView) itemView.findViewById(R.id.ivisit_likescount);
            visitCommentsCount = (TextView) itemView.findViewById(R.id.ivisit_commentscount);
            visitLikeIcon = (ImageView) itemView.findViewById(R.id.ivisit_likesimage);
            visitCommentIcon = (ImageView) itemView.findViewById(R.id.ivisit_commentimage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int itemPos=getLayoutPosition();

            Intent visitDetailsIntent=new Intent(activity, VisitDetailsActivity.class);
            visitDetailsIntent.putExtra("item_pos",itemPos);
            Gson gS = new Gson();
            String target = gS.toJson(visitModels.get(itemPos));
            visitDetailsIntent.putExtra("visit_model",target);
            activity.startActivity(visitDetailsIntent);
        }
    }

    private int convertDpToPx(int dpValue){
        Resources r = activity.getResources();
        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dpValue,
                r.getDisplayMetrics()
        );
        return px;
    }
}
