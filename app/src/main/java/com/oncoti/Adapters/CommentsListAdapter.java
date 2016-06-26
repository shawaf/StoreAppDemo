package com.oncoti.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oncoti.Models.CommentModel;
import com.oncoti.R;

import java.util.List;

/**
 * Created by Shawaf on 9/11/2015.
 */
public class CommentsListAdapter extends RecyclerView.Adapter<CommentsListAdapter.CommentsListView> {

    private Activity activity;
    private List<CommentModel> commentModels;

    public CommentsListAdapter(Activity activity, List<CommentModel> commentModels) {
        this.activity = activity;
        this.commentModels = commentModels;
    }

    @Override
    public CommentsListView onCreateViewHolder(ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comment_list_item, viewGroup, false);
        CommentsListView commentsListView = new CommentsListView(layoutView);
        return commentsListView;
    }

    @Override
    public void onBindViewHolder(CommentsListView holder, int position) {
        holder.userImage.setImageResource(R.drawable.street);
        holder.userName.setText(commentModels.get(position).getUserName());
        holder.commentTime.setText(commentModels.get(position).getCommentTime());
        holder.commentText.setText(commentModels.get(position).getCommentText());
    }

    @Override
    public int getItemCount() {
        return commentModels.size();
    }

    class CommentsListView extends RecyclerView.ViewHolder {
        ImageView userImage;
        TextView userName;
        TextView commentTime;
        TextView commentText;

        public CommentsListView(View itemView) {
            super(itemView);
            userImage = (ImageView) itemView.findViewById(R.id.comment_userimage);
            userName = (TextView) itemView.findViewById(R.id.comment_username);
            commentTime = (TextView) itemView.findViewById(R.id.comment_time);
            commentText = (TextView) itemView.findViewById(R.id.comment_text);
        }
    }
}
