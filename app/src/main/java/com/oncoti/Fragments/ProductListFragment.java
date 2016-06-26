package com.oncoti.Fragments;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.oncoti.ActivityClasses.AddNewProductActivity;
import com.oncoti.Adapters.UploadProductsAdapter;
import com.oncoti.Models.ProductModel;
import com.oncoti.R;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shawaf on 9/11/2015.
 */
public class ProductListFragment extends Fragment {
    private View v;
    private AddNewProductActivity addNewProductActivity;
    private TextView noteTV, uploadProdTV;
    private LinearLayout uploadProdLay;
    private ProgressBar uploadProdPB;
    private RecyclerView productsLV;
    private FloatingActionButton addProdFAB;
    private List<ProductModel> productModels;
    private ParseUser parseUser;
    private int prodNo = 0, totalProductSize;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.prodlist_frag, container, false);
        parseUser = ParseUser.getCurrentUser();
        initUI();
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        addNewProductActivity = (AddNewProductActivity) activity;
    }

    @Override
    public void onResume() {
        super.onResume();
        addNewProductActivity.setTitle(getResources().getString(R.string.add_products), getResources().getString(R.string.submit_all));

        if (addNewProductActivity.getProducts() != null) {
            productModels = addNewProductActivity.getProducts();
            fillProdsList(productModels);
        } else {
            productModels = new ArrayList<>();
        }

    }

    private void initUI() {
        noteTV = (TextView) v.findViewById(R.id.prodlist_note);
        productsLV = (RecyclerView) v.findViewById(R.id.prodlist_productlv);
        addProdFAB = (FloatingActionButton) v.findViewById(R.id.prodlist_fabbtn);
        uploadProdTV = (TextView) v.findViewById(R.id.upload_prod_tv);
        uploadProdLay = (LinearLayout) v.findViewById(R.id.uploadlay);
        uploadProdPB = (ProgressBar) v.findViewById(R.id.upload_prod_pb);
        productsLV.setLayoutManager(new LinearLayoutManager(getActivity()));


        addProdFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("ProdList", "ProdList Size : " + productModels.size());
                if (productModels.size() < 5) {
                    addNewProductActivity.OpenNewProdFragment();
                } else {
                    Toast.makeText(getActivity(), "You can't add more than 5 products at onetime", Toast.LENGTH_LONG).show();
                }
            }

        });
    }

    private void fillProdsList(List<ProductModel> productModelList) {
        Log.e("ProdList", "ProdList Size : " + productModelList.size());
        Log.e("ProdList", "ProdList first image : " + productModelList.get(0).getProdImageUrls().get(0));
        productsLV.setVisibility(View.VISIBLE);
        productsLV.setAdapter(new UploadProductsAdapter(getActivity(), productModelList));
    }

    public void uploadProducts() {
        if (productModels.size() != 0) {
            addProdFAB.hide();
            uploadProdLay.setVisibility(View.VISIBLE);
            totalProductSize = productModels.size();
            // uploadProdTV.setText("Upload 1 of "+productModels.size()+" Products"+" ....." );
            uploadProduct();
        }
    }

    private void uploadProduct() {
        int curProd=prodNo+1;
        uploadProdTV.setText("Upload " + curProd + " of " + productModels.size() + " Products" + " .....");

        ProductModel productModel = productModels.get(prodNo);
        //Product ParseObject
        ParseObject productProject = new ParseObject("Products");
        productProject.put("category", productModel.getCategory());
        productProject.put("description", productModel.getDescription());
        productProject.put("owner", parseUser);
        ArrayList<String> imageUrls = productModel.getProdImageUrls();
        if (imageUrls.get(0) != null) {
            ParseFile pic1 = new ParseFile("productimage.jpg", getImageBytes(imageUrls.get(0)));
            productProject.put("picture1", pic1);
            pic1.saveInBackground();
        }
        if (imageUrls.get(1) != null) {
            ParseFile pic2 = new ParseFile("productimage.jpg", getImageBytes(imageUrls.get(1)));
            productProject.put("picture2", pic2);
            pic2.saveInBackground();
        }
        if (imageUrls.get(2) != null) {
            ParseFile pic3 = new ParseFile("productimage.jpg", getImageBytes(imageUrls.get(2)));
            productProject.put("picture3", pic3);
            pic3.saveInBackground();
        }
        productProject.put("price", productModel.getPrice());
        productProject.put("tags", productModel.getTags());
        productProject.put("title", productModel.getProdName());
        Log.e("ProdList", "Start Uploading Product : " + prodNo+1);
        productProject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Toast.makeText(getActivity(), "Error : " + e.getMessage(), Toast.LENGTH_LONG).show();
                    addProdFAB.show();
                    uploadProdLay.setVisibility(View.GONE);
                } else {
                    Log.e("ProdList", "Product uploaded Successfuly : " + prodNo+1);
                    prodNo++;
                    if (prodNo < totalProductSize) {

                        uploadProduct();
                    } else {
                        addProdFAB.show();
                        uploadProdLay.setVisibility(View.GONE);
                    }
                }
            }
        });

    }

    private byte[] getImageBytes(String path) {
        File imgFile = new File(path);
        Bitmap myBitmap = null;
        if (imgFile.exists()) {
            myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        }
        // Convert it to byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        // Compress image to lower quality scale 1 - 100
        myBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] image = stream.toByteArray();
        Log.e("ProdList", "Bytes :  " + image.toString());
        return image;
    }
}
