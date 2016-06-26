package com.oncoti.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.oncoti.ActivityClasses.ProductDetailsActivity;
import com.oncoti.Models.ProductModel;
import com.oncoti.R;
import com.oncoti.Utilites.CommonMethods;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Shawaf on 9/11/2015.
 */
public class ProductDetailsFragment extends Fragment implements View.OnClickListener {
    private View v;
    private ProductDetailsActivity productDetailsActivity;
    private ImageButton commentBtn, likeBtn, knockBtn, shareBtn;
    private Button buyBtn;
    private ProductModel productModel;
    private ImageView userImage, prodImage;
    private TextView userName, uploadTime, prodCateg, prodName, prodDesc, prodPriceUnit, prodPrice;
    private LinearLayout tagsLay;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.prod_details_frag, container, false);

        initUI();
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        productDetailsActivity = (ProductDetailsActivity) activity;
    }

    @Override
    public void onResume() {
        super.onResume();
        productDetailsActivity.setTitle("ProdDetails");
        productModel = productDetailsActivity.productModel;
        fillData(productModel);
    }

    private void initUI() {
        commentBtn = (ImageButton) v.findViewById(R.id.proddetails_comment_btn);
        likeBtn = (ImageButton) v.findViewById(R.id.proddetails_likebtn);
        knockBtn = (ImageButton) v.findViewById(R.id.proddetails_knockbtn);
        shareBtn = (ImageButton) v.findViewById(R.id.proddetails_sharebtn);
        buyBtn = (Button) v.findViewById(R.id.proddetails_buy_btn);

        userImage = (ImageView) v.findViewById(R.id.proddetails_userimage);
        prodImage = (ImageView) v.findViewById(R.id.proddetails_prodimage);
        userName = (TextView) v.findViewById(R.id.proddetails_username);
        uploadTime = (TextView) v.findViewById(R.id.proddetails_posttime);
        prodCateg = (TextView) v.findViewById(R.id.proddetails_categ);
        prodName = (TextView) v.findViewById(R.id.proddetails_prodname);
        prodDesc = (TextView) v.findViewById(R.id.proddetails_desc);
        prodPriceUnit = (TextView) v.findViewById(R.id.proddetails_pricunit);
        prodPrice = (TextView) v.findViewById(R.id.proddetails_price);
        tagsLay = (LinearLayout) v.findViewById(R.id.proddetails_tagslay);

        commentBtn.setOnClickListener(this);
        likeBtn.setOnClickListener(this);
        knockBtn.setOnClickListener(this);
        shareBtn.setOnClickListener(this);
        buyBtn.setOnClickListener(this);

    }

    private void fillData(ProductModel productModel) {
        UrlImageViewHelper.setUrlDrawable(userImage, productModel.getOwnerImage(), R.drawable.logo1);
        //userImage.setImageBitmap(productModel.getOwnerImage());
        UrlImageViewHelper.setUrlDrawable(prodImage, productModel.getProdImageUrls().get(0), R.drawable.logo1);
       // prodImage.setImageBitmap(productModel.getProdImageUrls().get(0));
        userName.setText(productModel.getOwnerName());
        int postTime= CommonMethods.getDaysBetween(productModel.getUploadTime());
        if (postTime == 0) {
            uploadTime.setText("Today");
        } else {
            uploadTime.setText(postTime + " days ago");
        }
        prodCateg.setText(productModel.getCategory());
        prodName.setText(productModel.getProdName());
        prodDesc.setText(productModel.getDescription());
        prodPrice.setText(productModel.getPrice());

        if (productModel.getTags() != null) {
            Log.e("ProductDetails", "Tags : " + productModel.getTags().size());
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.proddetails_comment_btn:
                productDetailsActivity.openFragment(0);
                break;
            case R.id.proddetails_likebtn:

                break;
            case R.id.proddetails_knockbtn:

                break;
            case R.id.proddetails_sharebtn:

                break;
            case R.id.proddetails_buy_btn:

                break;
        }
    }

}
