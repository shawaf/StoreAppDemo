package com.oncoti.ItemDecoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Shawaf on 9/3/2015.
 */
public class ListItemDecoration extends RecyclerView.ItemDecoration {
    private final int mSpace;

    public ListItemDecoration(int space) {
        this.mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.bottom = mSpace;

        // Add top margin only for the first item to avoid double space between items
//        if (parent.getChildAdapterPosition(view) == 0)
//            outRect.top = mSpace;
    }
}