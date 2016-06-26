package com.oncoti.Adapters;


import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oncoti.Models.HeadlineModel;
import com.oncoti.Models.ShuraModel;
import com.oncoti.R;

import java.util.List;

/**
 * Created by Shawaf on 9/4/2015.
 */
public class ShurasListAdapter extends RecyclerView.Adapter<ShurasListAdapter.ShurasListView> {


    private Activity activity;
    private List<ShuraModel> shuraModels;

    public ShurasListAdapter(Activity activity,List<ShuraModel> shuraModels){
        this.activity=activity;
        this.shuraModels=shuraModels;
    }



    @Override
    public ShurasListView onCreateViewHolder(ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shuras_list_item, viewGroup, false);
        ShurasListView shurasListView = new ShurasListView(layoutView);
        return shurasListView;
    }


    @Override
    public void onBindViewHolder(ShurasListView holder, int position) {
        holder.prodOwnerName.setText(shuraModels.get(position).getProdOwnerName());
        holder.prodDesc.setText(shuraModels.get(position).getProdDesc());
        holder.prodPrice.setText(shuraModels.get(position).getProdPrice()+" DHS");
        holder.prodReviewerNo.setText(shuraModels.get(position).getProdRatePersonNo());
    }

    public int getItemCount() {
        return shuraModels.size();
    }

    class ShurasListView extends RecyclerView.ViewHolder {
        ImageView prodImage;
        TextView prodOwnerName;
        TextView prodDesc;
        TextView prodPrice;
        TextView prodReviewerNo;
        ImageView star1;
        ImageView star2;
        ImageView star3;
        ImageView star4;
        ImageView star5;


        public ShurasListView(View itemView) {
            super(itemView);
            prodImage = (ImageView) itemView.findViewById(R.id.shuras_prod_image);
            prodOwnerName = (TextView) itemView.findViewById(R.id.shuras_prod_owner);
            prodDesc = (TextView) itemView.findViewById(R.id.shuras_prod_desc);
            prodPrice = (TextView) itemView.findViewById(R.id.shuras_prod_price);
            prodReviewerNo = (TextView) itemView.findViewById(R.id.reviewers_counter);
            star1 = (ImageView) itemView.findViewById(R.id.star1);
            star2 = (ImageView) itemView.findViewById(R.id.star2);
            star3 = (ImageView) itemView.findViewById(R.id.star3);
            star4 = (ImageView) itemView.findViewById(R.id.star4);
            star5 = (ImageView) itemView.findViewById(R.id.star5);
        }
    }

}
