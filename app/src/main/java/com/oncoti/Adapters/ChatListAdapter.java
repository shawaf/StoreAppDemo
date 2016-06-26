package com.oncoti.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oncoti.ChatAdapterClasses.MeChatViewHolder;
import com.oncoti.ChatAdapterClasses.UserChatViewHolder;
import com.oncoti.Models.ChatModel;
import com.oncoti.R;

import java.util.List;

/**
 * Created by Shawaf on 9/11/2015.
 */
public class ChatListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity activity;
    private List<ChatModel> chatModels;
    private static final int userItem = 0;
    private static final int meItem = 1;

    public ChatListAdapter(Activity activity, List<ChatModel> chatModels) {
        this.activity = activity;
        this.chatModels = chatModels;
    }


    @Override
    public int getItemViewType(int position) {
        if (chatModels.get(position).isMe()) {
            return meItem;
        } else {
            return userItem;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        RecyclerView.ViewHolder holder;
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());

        switch (i) {
            case userItem:
                View layoutViewMe = (ViewGroup) mInflater.inflate(R.layout.chat_list_item, viewGroup, false);
                holder = new UserChatViewHolder(layoutViewMe);
                return holder;
            case meItem:
                View layoutViewUser = (ViewGroup) mInflater.inflate(R.layout.chat_list_item2, viewGroup, false);
                holder = new MeChatViewHolder(layoutViewUser);
                return holder;
            default:
                View defaultlayout = (ViewGroup) mInflater.inflate(R.layout.chat_list_item, viewGroup, false);
                holder = new MeChatViewHolder(defaultlayout);
                return holder;

        }

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case userItem:

                UserChatViewHolder userChatViewHolderholder = (UserChatViewHolder) viewHolder;
                userChatViewHolderholder.userChatTextTV.setText(chatModels.get(position).getChatText());
                userChatViewHolderholder.userChatTimeTV.setText(chatModels.get(position).getChatTime());
                break;
            case meItem:
                MeChatViewHolder meChatViewHolder = (MeChatViewHolder) viewHolder;
                meChatViewHolder.meChatTextTV.setText(chatModels.get(position).getChatText());
                meChatViewHolder.meChatTimeTV.setText(chatModels.get(position).getChatTime());
                break;
        }


//        if (chatModels.get(position).isMe()) {
//            holder.userChatBoxLay.setVisibility(View.GONE);
//            holder.meChatTextTV.setText(chatModels.get(position).getChatText());
//            holder.meChatTimeTV.setText(chatModels.get(position).getChatTime());
//        } else {
//            holder.meChatBoxLay.setVisibility(View.GONE);
//            holder.userChatTextTV.setText(chatModels.get(position).getChatText());
//            holder.userChatTimeTV.setText(chatModels.get(position).getChatTime());
//        }


    }

    @Override
    public int getItemCount() {
        return chatModels.size();
    }

//    class ChatListView_Me extends RecyclerView.ViewHolder {
//        //LinearLayout userChatBoxLay;
//        LinearLayout meChatBoxLay;
//        //TextView userChatTextTV;
//        TextView meChatTextTV;
//        //TextView userChatTimeTV;
//        TextView meChatTimeTV;
//
//
//        public ChatListView_Me(View itemView) {
//            super(itemView);
//
//            //  userChatBoxLay = (LinearLayout) itemView.findViewById(R.id.user_chat_lay);
//            meChatBoxLay = (LinearLayout) itemView.findViewById(R.id.me_chat_lay);
//            //  userChatTextTV = (TextView) itemView.findViewById(R.id.user_chat_text);
//            meChatTextTV = (TextView) itemView.findViewById(R.id.me_chat_text);
//            //  userChatTimeTV = (TextView) itemView.findViewById(R.id.user_chat_time);
//            meChatTimeTV = (TextView) itemView.findViewById(R.id.me_chat_time);
//        }
//    }
//
//     class ChatListView_User extends RecyclerView.ViewHolder {
//        LinearLayout userChatBoxLay;
//        // LinearLayout meChatBoxLay;
//        TextView userChatTextTV;
//        //TextView meChatTextTV;
//        TextView userChatTimeTV;
//        //TextView meChatTimeTV;
//
//
//        public ChatListView_User(View itemView) {
//            super(itemView);
//
//            userChatBoxLay = (LinearLayout) itemView.findViewById(R.id.user_chat_lay);
//            //  meChatBoxLay = (LinearLayout) itemView.findViewById(R.id.me_chat_lay);
//            userChatTextTV = (TextView) itemView.findViewById(R.id.user_chat_text);
//            //meChatTextTV = (TextView) itemView.findViewById(R.id.me_chat_text);
//            userChatTimeTV = (TextView) itemView.findViewById(R.id.user_chat_time);
//            // meChatTimeTV = (TextView) itemView.findViewById(R.id.me_chat_time);
//        }
//    }
}
