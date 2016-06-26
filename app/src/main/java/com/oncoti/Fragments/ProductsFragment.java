package com.oncoti.Fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.oncoti.Adapters.ProductsStaggGridAdapter;
import com.oncoti.ItemDecoration.SpacesItemDecoration;
import com.oncoti.Models.ProductModel;
import com.oncoti.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Shawaf on 8/25/2015.
 */
public class ProductsFragment extends Fragment {
    private View v;
    private RecyclerView productsGridView;
    private ParseUser parseUser;
    private LinearLayout prodListProgLay;
    private TextView noProdTV;
    private ProgressBar prodlistProgPB;
    private ArrayList<String> iBitmaps;
    private Bitmap userImage;
    private String curKey;
    private int likesCounter;
    private String userUsername;
    private String ownerImage;
    private String category;
    private String price;
    private String prodName;
    private String description;
    private List<String> tags;
    private List<String> likes;
    private ArrayList<ProductModel> productModels;
    private Date uploadTime;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.products_tab, container, false);

        parseUser = ParseUser.getCurrentUser();
        initUI();
        Log.e("ProductFragment", "UserName : " + parseUser.getObjectId());
        loadProducts();
        return v;
    }

    private void initUI() {
        prodListProgLay = (LinearLayout) v.findViewById(R.id.productlist_progress_lay);
        noProdTV = (TextView) v.findViewById(R.id.productlist_noprod_tv);
        prodlistProgPB = (ProgressBar) v.findViewById(R.id.productlist_pb);
        productsGridView = (RecyclerView) v.findViewById(R.id.products_grid);
        productsGridView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));


    }

    private List<ProductModel> loadProducts() {
        productModels = new ArrayList<>();

        List<String> arr = new ArrayList<String>();
        if (parseUser.getList("following") != null) {
            Log.e("ProductsFragment","There's Following peapole");
            arr = parseUser.getList("following");
        }
        arr.add(parseUser.getObjectId());
//        arr.add("7B1ojCQXFD");
        arr.add("XHPFTaWOOI");
        arr.add("5H4MVAGZED");
//        Log.e("ProductsTabFragment", "Array Size : " + parseUser.getList("following").size());
        ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
        query.whereContainedIn("objectId", arr);
        ParseQuery<ParseObject> query_product = ParseQuery.getQuery("Products");
        query_product.whereMatchesQuery("owner", query);
        query_product.include("owner");
        query_product.orderByDescending("createdAt");
        query_product.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> productList, ParseException e) {

                if (e == null) {
                    for (ParseObject product : productList) {
                        String productId = product.getObjectId();
                        ParseObject userProd = product.getParseUser("owner");
                        userUsername = userProd.getString("username");
                        ownerImage = getOwnerBitmap(userProd, "avatar");
                        uploadTime = product.getCreatedAt();
                        category = product.get("category").toString();
                        prodName = product.get("title").toString();
                        description = product.get("description").toString();
                        price = product.get("price").toString();
                        tags = new ArrayList<String>();
                        tags = product.getList("tags");
                        likes = new ArrayList<String>();
                        likes = product.getList("likes");
                        if (likes == null) {
                            likesCounter = 0;
                        } else {
                            likesCounter = likes.size();
                        }
                        Log.e("ProductFragment", "ProductId : " + productId);
                        getBitmaps(product);

                    }
                    fillProdductList(productModels);
                } else {
                    Log.e("Result", e.getMessage());
                    Toast.makeText(getActivity(), "Error : " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });
        return productModels;
    }

    private void fillProdductList(ArrayList<ProductModel> productModels) {

        if (productModels.size() != 0) {
            prodListProgLay.setVisibility(View.GONE);
            ProductsStaggGridAdapter adapter = new ProductsStaggGridAdapter(getActivity(), productModels);
            productsGridView.setAdapter(adapter);
            SpacesItemDecoration decoration = new SpacesItemDecoration(16);
            productsGridView.addItemDecoration(decoration);

        } else {
            prodListProgLay.setVisibility(View.VISIBLE);
            prodlistProgPB.setVisibility(View.GONE);
            noProdTV.setVisibility(View.VISIBLE);
        }
    }

    private void getBitmaps(ParseObject product) {
        iBitmaps = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            curKey = "picture" + i;
            Log.e("ProductsFragment", curKey + " is downloading");
            ParseFile pict1File = (ParseFile) product.get(curKey);
            if (pict1File != null) {

                Log.e("ProductsFragment", curKey + pict1File.getUrl());
                //Bitmap bmp = BitmapFactory.decodeByteArray(pict1File.getData(), 0, pict1File.getData().length);
                iBitmaps.add(pict1File.getUrl());

            }
//            pict1File.getDataInBackground(new GetDataCallback() {
//                public void done(byte[] data, ParseException e) {
//                    if (e == null) {
//                        // data has the bytes for the resume
//                        if (data != null) {
//                            Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
//                            iBitmaps.add(bmp);
//                            Log.e("ProductsFragment", curKey + " is exist");
//                        }
//                    } else {
//                        // something went wrong
//                    }
//                }
//            });
        }
        productModels.add(new ProductModel(iBitmaps, ownerImage, userUsername, uploadTime, category, prodName, description, price, (ArrayList<String>) tags, likesCounter, 12));


    }

    private String getOwnerBitmap(ParseObject userProd, String key) {
        userImage = null;
        ParseFile pict1File = (ParseFile) userProd.get(key);
//        byte[] data = new byte[0];
//        try {
//            data = pict1File.getData();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        if (data != null) {
//            userImage = BitmapFactory.decodeByteArray(data, 0, data.length);
//        } else {
//            userImage = BitmapFactory.decodeResource(getResources(), R.drawable.logo1);
//        }
        if (pict1File != null) {
            return pict1File.getUrl();
        }
        return null;
    }

}
