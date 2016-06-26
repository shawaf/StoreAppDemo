package com.oncoti.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oncoti.Models.NotificationModel;
import com.oncoti.R;

import java.util.List;

/**
 * Created by Shawaf on 9/5/2015.
 */
public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.NotificationListView> {

    private Activity activity;
    private List<NotificationModel> notificationModels;

    public NotificationListAdapter (Activity activity,List<NotificationModel> notificationModels){
        this.notificationModels=notificationModels;
        this.activity=activity;
    }

    @Override
    public NotificationListView onCreateViewHolder(ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notif_list_item, viewGroup, false);
        NotificationListView notificationListView = new NotificationListView(layoutView);
        return notificationListView;
    }

    @Override
    public void onBindViewHolder(NotificationListView holder, int position) {
        holder.notifImageIV.setImageResource(R.drawable.street);
        holder.notifTimeTV.setText(notificationModels.get(position).getNotifTime());
        String notifUser=notificationModels.get(position).getNotifUser();
        String notifText=notificationModels.get(position).getNotifText();
        holder.notifTextTV.setText(Html.fromHtml("<font color=#f2b705><b>"+notifUser+" "+"</b></font>"+notifText));
    }

    public int getItemCount() {
        return notificationModels.size();
    }


    class NotificationListView extends RecyclerView.ViewHolder {

        private ImageView notifImageIV;
        private TextView notifTextTV;
        private TextView notifTimeTV;

        public NotificationListView(View itemView) {
            super(itemView);
            notifImageIV = (ImageView) itemView.findViewById(R.id.notif_item_image);
            notifTextTV = (TextView) itemView.findViewById(R.id.notif_item_text);
            notifTimeTV = (TextView) itemView.findViewById(R.id.notif_item_time);

        }
    }
}
