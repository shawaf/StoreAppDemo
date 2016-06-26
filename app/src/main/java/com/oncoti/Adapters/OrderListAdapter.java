package com.oncoti.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oncoti.Models.OrderModel;
import com.oncoti.R;

import java.util.List;

/**
 * Created by Shawaf on 9/5/2015.
 */
public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.OrderListView> {

    private Activity activity;
    private List<OrderModel> orderModels;

    public OrderListAdapter(Activity activity, List<OrderModel> orderModels) {
        this.activity = activity;
        this.orderModels = orderModels;
    }

    @Override
    public OrderListView onCreateViewHolder(ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_list_item, viewGroup, false);
        OrderListView orderListView = new OrderListView(layoutView);
        return orderListView;
    }

    @Override
    public void onBindViewHolder(OrderListView holder, int position) {
        holder.orderImage.setImageResource(R.drawable.street);
        holder.orderDealerName.setText(orderModels.get(position).getOrderDealer());
        holder.orderDesc.setText(orderModels.get(position).getOrderDesc());
        holder.orderLastUpdate.setText(activity.getResources().getString(R.string.lastupdate)+" "+orderModels.get(position).getOrderLastUpdate());
        holder.orderPrice.setText(orderModels.get(position).getOrderPrice()+" AED");
        holder.orderState.setText(orderModels.get(position).getOrderCurState());

    }

    @Override
    public int getItemCount() {
        return orderModels.size();
    }

    class OrderListView extends RecyclerView.ViewHolder {
        private ImageView orderImage;
        private TextView orderDealerName;
        private TextView orderDesc;
        private TextView orderLastUpdate;
        private TextView orderPrice;
        private TextView orderState;

        public OrderListView(View itemView) {
            super(itemView);
            orderImage = (ImageView) itemView.findViewById(R.id.order_image);
            orderDealerName = (TextView) itemView.findViewById(R.id.order_prod_dealer);
            orderDesc = (TextView) itemView.findViewById(R.id.order_prod_desc);
            orderLastUpdate = (TextView) itemView.findViewById(R.id.order_lastupdate);
            orderPrice = (TextView) itemView.findViewById(R.id.order_price);
            orderState = (TextView) itemView.findViewById(R.id.order_state);
        }
    }
}
