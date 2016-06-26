package com.oncoti.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.oncoti.Models.HeadlineModel;
import com.oncoti.R;
import com.oncoti.Utilites.CommonMethods;

import java.util.List;

/**
 * Created by Shawaf on 9/3/2015.
 */
public class HeadlinesListAdapter extends RecyclerView.Adapter<HeadlinesListAdapter.HeadLinesListView> implements View.OnClickListener {
    private Activity activity;
    private List<HeadlineModel> headlineModels;

    public HeadlinesListAdapter(Activity activity, List<HeadlineModel> headlineModels) {
        this.activity = activity;
        this.headlineModels = headlineModels;
    }

    @Override
    public HeadLinesListView onCreateViewHolder(ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.headlines_list_item, viewGroup, false);
        HeadLinesListView headLinesListView = new HeadLinesListView(layoutView);
        return headLinesListView;
    }

    @Override
    public void onBindViewHolder(final HeadLinesListView holder, int position) {
        holder.postText.setMovementMethod(new ScrollingMovementMethod());
        UrlImageViewHelper.setUrlDrawable(holder.userImage, headlineModels.get(position).getUserImageUrl(), R.drawable.logo1);
        holder.userImage.setImageResource(R.drawable.logo1);
        holder.userName.setText(headlineModels.get(position).getUserName());
        int days = CommonMethods.getDaysBetween(headlineModels.get(position).getPostTime());
        if (days == 0) {
            holder.postTime.setText("Today");
        } else {
            holder.postTime.setText(days + " days ago");
        }
        holder.postText.setText(headlineModels.get(position).getPostText());
        if (headlineModels.get(position).getPostImageUrl() != null) {
            UrlImageViewHelper.setUrlDrawable(holder.postImage, headlineModels.get(position).getPostImageUrl(), R.drawable.logo1);
        } else {
            holder.postImage.setVisibility(View.GONE);
        }

        holder.postText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.postText.getMaxLines() == 3) {
                    holder.postText.setMaxLines(100);
                } else {
                    holder.postText.setMaxLines(3);
                }
            }
        });
    }


    public int getItemCount() {
        return headlineModels.size();
    }

    @Override
    public void onClick(View view) {

    }


    class HeadLinesListView extends RecyclerView.ViewHolder {
        ImageView userImage;
        TextView userName;
        TextView postTime;
        TextView postText;
        ImageView postImage;

        public HeadLinesListView(View itemView) {
            super(itemView);
            userImage = (ImageView) itemView
                    .findViewById(R.id.userimage);
            userName = (TextView) itemView
                    .findViewById(R.id.username);
            postTime = (TextView) itemView
                    .findViewById(R.id.posttime);
            postText = (TextView) itemView.findViewById(R.id.posttext);
            postImage = (ImageView) itemView.findViewById(R.id.postimage);
        }
    }

}
