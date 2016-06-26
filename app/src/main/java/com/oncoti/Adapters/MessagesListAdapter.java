package com.oncoti.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oncoti.ActivityClasses.ChatActivity;
import com.oncoti.Models.MessagesModel;
import com.oncoti.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Shawaf on 9/5/2015.
 */
public class MessagesListAdapter extends RecyclerView.Adapter<MessagesListAdapter.MessageListView>{
    private Activity activity;
    private List<MessagesModel> messagesModels;

    public MessagesListAdapter(Activity activity,List<MessagesModel> messagesModels){
        this.activity=activity;
        this.messagesModels=messagesModels;
    }
    @Override
    public MessageListView onCreateViewHolder(ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.msg_list_item, viewGroup, false);
        MessageListView messageListView = new MessageListView(layoutView);
        return messageListView;
    }

    @Override
    public void onBindViewHolder(MessageListView holder, int position) {
        holder.msgUserImage.setImageResource(R.drawable.forest);
        holder.msgUserName.setText(messagesModels.get(position).getMsgUserName());
        holder.msgLastText.setText(messagesModels.get(position).getMsgLastText());
        holder.msgTime.setText(messagesModels.get(position).getMsgTime());

    }

    @Override
    public int getItemCount() {
        return messagesModels.size();
    }

    class MessageListView extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView msgUserImage;
        private TextView msgUserName;
        private TextView msgLastText;
        private TextView msgTime;

        public MessageListView(View itemView) {
            super(itemView);
            msgUserImage=(ImageView)itemView.findViewById(R.id.message_item_image);
            msgUserName=(TextView)itemView.findViewById(R.id.message_item_username);
            msgLastText=(TextView)itemView.findViewById(R.id.message_item_text);
            msgTime=(TextView)itemView.findViewById(R.id.message_item_time);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Intent chatIntent=new Intent(activity, ChatActivity.class);
            chatIntent.putExtra("chat_user",messagesModels.get(getLayoutPosition()).getMsgUserName());
            activity.startActivity(chatIntent);
        }
    }
}
