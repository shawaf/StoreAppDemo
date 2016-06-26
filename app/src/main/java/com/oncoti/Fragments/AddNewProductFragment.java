package com.oncoti.Fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.oncoti.ActivityClasses.AddNewProductActivity;
import com.oncoti.Models.ProductModel;
import com.oncoti.R;
import com.oncoti.Utilites.CommonMethods;
import com.parse.ParseUser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shawaf on 9/11/2015.
 */
public class AddNewProductFragment extends Fragment implements View.OnClickListener {

    private View v;
    private AddNewProductActivity addNewProductActivity;
    private Spinner categSpinner;
    private EditText prodNameET, prodCatET, prodPriceET, prodTagsET, prodDescET;
    private ImageView prodImage1, prodImage2, prodImage3, prodImage4, prodImage5;
    private LinearLayout tagsLay;
    private int imageNo = 0, prodCount = 0,prodNo=1;
    private String imagePath1, imagePath2, imagePath3, imagePath4, imagePath5;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private File root;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private ArrayList<String> tags = new ArrayList<>();
    private boolean categChoosed = false;
    private ProductModel productModel;
    private ParseUser parseUser;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.newprod_frag, container, false);
        parseUser = ParseUser.getCurrentUser();

        initUI();
        makeProdsDire();
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
        addNewProductActivity.setTitle(getResources().getString(R.string.new_product), getResources().getString(R.string.submit));
    }

    private void initUI() {
        prodNameET = (EditText) v.findViewById(R.id.newprod_name);
        prodCatET = (EditText) v.findViewById(R.id.newprod_category);
        prodPriceET = (EditText) v.findViewById(R.id.newprod_price);
        prodTagsET = (EditText) v.findViewById(R.id.newprod_tags);
        prodDescET = (EditText) v.findViewById(R.id.newprod_desc);
        categSpinner = (Spinner) v.findViewById(R.id.categ_spinner);
        tagsLay = (LinearLayout) v.findViewById(R.id.tags_lay);
        prodImage1 = (ImageView) v.findViewById(R.id.newprod_imag1);
        prodImage2 = (ImageView) v.findViewById(R.id.newprod_imag2);
        prodImage3 = (ImageView) v.findViewById(R.id.newprod_imag3);


        prodImage1.setOnClickListener(this);
        prodImage2.setOnClickListener(this);
        prodImage3.setOnClickListener(this);

        categSpinner.setPrompt(getResources().getString(R.string.choose_cat));
        categSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                categChoosed = true;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//        prodTagsET.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                if(keyEvent.getAction()==KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_SPACE){
//                    if(prodTagsET.getText().toString() != null){
//
//                    }
//
//                    return true;
//                }
//                return false;
//            }
//        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.newprod_imag1:
                imageNo = 1;
                openPickPhotoDialog();
                break;
            case R.id.newprod_imag2:
                imageNo = 2;
                openPickPhotoDialog();
                break;
            case R.id.newprod_imag3:
                imageNo = 3;
                openPickPhotoDialog();
                break;

        }
    }

    private void openPickPhotoDialog() {
        final CharSequence[] items = {"Take Photo", "From Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Change Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                switch (item) {
                    case 0:
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, REQUEST_CAMERA);
                        break;
                    case 1:
                        Intent galleyintent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        galleyintent.setType("image/*");
                        startActivityForResult(
                                Intent.createChooser(galleyintent, "Select File"),
                                SELECT_FILE);
                        break;
                }
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                File destination = new File(root, ".ProdImage" + prodNo+imageNo + ".jpg");
                FileOutputStream fo;
                try {
                    destination.createNewFile();
                    fo = new FileOutputStream(destination);
                    fo.write(bytes.toByteArray());
                    fo.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //changePhotoIV.setImageBitmap(thumbnail);
                String path = root + File.separator + ".ProdImage" + prodNo+imageNo + ".jpg";
                Log.e("NewProduct", "Path : " + path);
                showPic(thumbnail, path);
            } else if (requestCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();
                String[] projection = {MediaStore.MediaColumns.DATA};
                Cursor cursor = getActivity().managedQuery(selectedImageUri, projection, null, null,
                        null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                cursor.moveToFirst();
                String selectedImagePath = cursor.getString(column_index);
                Bitmap bm;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(selectedImagePath, options);
                final int REQUIRED_SIZE = 200;
                int scale = 1;
                while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                        && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                    scale *= 2;
                options.inSampleSize = scale;
                options.inJustDecodeBounds = false;
                bm = BitmapFactory.decodeFile(selectedImagePath, options);
                // changePhotoIV.setImageBitmap(bm);
                Log.e("NewProduct", "Path : " + selectedImagePath);
                showPic(bm, selectedImagePath);
            }
        }
    }

    private void showPic(Bitmap bm, String path) {
        switch (imageNo) {
            case 1:
                prodImage1.setImageBitmap(bm);
                imagePath1 = path;
                break;
            case 2:
                prodImage2.setImageBitmap(bm);
                imagePath2 = path;
                break;
            case 3:
                prodImage3.setImageBitmap(bm);
                imagePath3 = path;
                break;

        }
    }


    public void submitProduct() {
        imagePaths = new ArrayList<>();
        tags = new ArrayList<>();
        if (addNewProductActivity.getProducts() != null) {
            prodCount = addNewProductActivity.getProducts().size();
        }

        if (prodCount < 5) {
            if (prodNameET.getText().toString().matches("") || prodPriceET.getText().toString().matches("") || prodDescET.getText().toString().matches("") || categChoosed == false) {
                Toast.makeText(getActivity(), "Please fill all fields", Toast.LENGTH_LONG).show();
                return;
            } else if (imagePath1 == null && imagePath2 == null && imagePath3 == null && imagePath4 == null && imagePath5 == null) {
                Toast.makeText(getActivity(), "You must add at least one image .", Toast.LENGTH_LONG).show();
            } else {
                if (imagePath1 != null) {
                    imagePaths.add(imagePath1);
                }
                if (imagePath2 != null) {
                    imagePaths.add(imagePath2);
                }
                if (imagePath3 != null) {
                    imagePaths.add(imagePath3);
                }

                if (!prodTagsET.getText().toString().matches("")) {
                    String tagstring = prodTagsET.getText().toString();
                    String[] tagsArr = tagstring.split(" ");
                    for (int i = 0; i < tagsArr.length; i++) {
                        tags.add(tagsArr[i]);
                        Log.e("NewProductSubmit", "Tag" + i + " : " + tagsArr[i]);
                    }
                }
                String name = prodNameET.getText().toString();
                String categ = categSpinner.getSelectedItem().toString();
                String price = prodPriceET.getText().toString();
                String desc = prodDescET.getText().toString();

                productModel = new ProductModel(imagePaths, null, parseUser.getUsername(), null, categ, name, desc, price, tags, 0, 0);
                int prodListSize = addNewProductActivity.addToProducts(productModel);
                prodNo++;
                clearFields();
                //getActivity().getSupportFragmentManager().popBackStack();
                if (prodListSize == 5) {
                    getActivity().getSupportFragmentManager().popBackStack();
                    Toast.makeText(getActivity(), "You added 5 Products", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            Toast.makeText(getActivity(), "You can't add more than 5 products at onetime", Toast.LENGTH_LONG).show();
        }
    }

    private void clearFields() {
        prodTagsET.setText(null);
        prodNameET.setText(null);
        prodPriceET.setText(null);
        prodDescET.setText(null);
        prodImage1.setImageResource(R.drawable.add_photo_bg);
        prodImage2.setImageResource(R.drawable.add_photo_bg);
        prodImage3.setImageResource(R.drawable.add_photo_bg);


        CommonMethods.closeKeyboard(getActivity());
        Toast.makeText(getActivity(), "Submit Done", Toast.LENGTH_LONG).show();

    }

    private void makeProdsDire() {
        root = new File(Environment.getExternalStorageDirectory(), "Oncoti" + File.separator + "Products");

        if (!root.exists()) {
            root.mkdirs();
        }
    }


}
