package com.oncoti.ChatAdapterClasses;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oncoti.R;

/**
 * Created by Shawaf on 9/11/2015.
 */
public class UserChatViewHolder extends ChatMainViewHolder {

    public LinearLayout userChatBoxLay;
    // LinearLayout meChatBoxLay;
    public TextView userChatTextTV;
    //TextView meChatTextTV;
    public TextView userChatTimeTV;
    //TextView meChatTimeTV;


    public UserChatViewHolder(View itemView) {
        super(itemView);

        userChatBoxLay = (LinearLayout) itemView.findViewById(R.id.user_chat_lay);
        //  meChatBoxLay = (LinearLayout) itemView.findViewById(R.id.me_chat_lay);
        userChatTextTV = (TextView) itemView.findViewById(R.id.user_chat_text);
        //meChatTextTV = (TextView) itemView.findViewById(R.id.me_chat_text);
        userChatTimeTV = (TextView) itemView.findViewById(R.id.user_chat_time);
        // meChatTimeTV = (TextView) itemView.findViewById(R.id.me_chat_time);
    }

}