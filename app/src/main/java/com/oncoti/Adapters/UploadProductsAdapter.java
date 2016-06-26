package com.oncoti.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.oncoti.Models.ProductModel;
import com.oncoti.R;

import java.io.File;
import java.util.List;

/**
 * Created by Shawaf on 10/7/2015.
 */
public class UploadProductsAdapter extends RecyclerView.Adapter<UploadProductsAdapter.ProductsListView> {
    private Activity activity;
    private List<ProductModel> productModels;
    private int curposition;

    public UploadProductsAdapter(Activity activity, List<ProductModel> productModels) {
        this.activity = activity;
        this.productModels = productModels;
    }


    @Override
    public ProductsListView onCreateViewHolder(ViewGroup viewGroup, int i) {
        View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.uploadproducts_list_item, viewGroup, false);
        ProductsListView productsListView = new ProductsListView(layoutView);
        return productsListView;
    }

    @Override
    public void onBindViewHolder(ProductsListView holder, final int position) {
        holder.prodIV.setTag(position);
        setProdImage(holder.prodIV,position);
        holder.prodTV.setText(productModels.get(position).getProdName());
        holder.deleteLay.setTag(position);
        final Context wrapper = new ContextThemeWrapper(activity.getApplicationContext(), R.style.MyPopupMenu);
        holder.deleteLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                curposition = (int)view.getTag();
                PopupMenu popupMenu = new PopupMenu(wrapper, view);
                popupMenu.getMenu().add("Delete");
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        removeAt(curposition);
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    class ProductsListView extends RecyclerView.ViewHolder {

        private ImageView prodIV;
        private TextView prodTV;
        private ImageButton deletBtn;
        private LinearLayout deleteLay;

        public ProductsListView(View itemView) {
            super(itemView);
            prodIV = (ImageView) itemView.findViewById(R.id.uploadprod_item_iv);
            prodTV = (TextView) itemView.findViewById(R.id.uploadprod_item_tv);
            deletBtn = (ImageButton) itemView.findViewById(R.id.uploadprod_deletebtn);
            deleteLay=(LinearLayout)itemView.findViewById(R.id.uploadprod_deletebtn_lay);
        }
    }

    private void setProdImage(ImageView prodImage,int pos){
        int curPos = pos;
        File imgFile = new File(productModels.get(pos).getProdImageUrls().get(0));
        if (imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            prodImage.setImageBitmap(myBitmap);
        }
       // prodImage.setImageBitmap(productModels.get(curPos).getProdImageUrls().get(0));
    }
    public void removeAt(int position) {
        productModels.remove(position);
        notifyItemRemoved(position);
    }
}
