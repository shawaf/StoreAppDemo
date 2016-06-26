package com.oncoti.Fragments;

import android.app.ProgressDialog;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oncoti.R;
import com.oncoti.Utilites.ConnectionDetector;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Shawaf on 9/11/2015.
 */
public class AddNewVisitFragment extends Fragment implements View.OnClickListener {
    private View v;
    private ParseUser parseUser;
    private ConnectionDetector cd;
    private ImageView visitImage1, visitImage2, visitImage3;
    private EditText shopNameET, doingET;
    private TextView locationTV;
    private int imageNo = 0;
    private String imagePath1, imagePath2, imagePath3;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private File root;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private ProgressDialog pd;
    private int uploadPhotoNo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.add_new_visit_frag, container, false);

        parseUser = ParseUser.getCurrentUser();
        cd = new ConnectionDetector(getActivity());
        initUI();
        makeVisitsDire();
        buildProgressDialog();
        return v;
    }


    private void initUI() {
        visitImage1 = (ImageView) v.findViewById(R.id.newvisit_photo1);
        visitImage2 = (ImageView) v.findViewById(R.id.newvisit_photo2);
        visitImage3 = (ImageView) v.findViewById(R.id.newvisit_photo3);
        shopNameET = (EditText) v.findViewById(R.id.newvisit_shopname);
        doingET = (EditText) v.findViewById(R.id.newvisit_whatdidthere);
        locationTV = (TextView) v.findViewById(R.id.newvisit_locationt_tv);

        visitImage1.setOnClickListener(this);
        visitImage2.setOnClickListener(this);
        visitImage3.setOnClickListener(this);
        locationTV.setOnClickListener(this);
    }


    public void submitVisit() {
        locationTV.setText("address");
        imagePaths = new ArrayList<>();
        if (cd.isConnectingToInternet()) {
            if (shopNameET.getText().toString().matches("") || doingET.getText().toString().matches("") || locationTV.getText().toString().equals(getResources().getString(R.string.identfyloc))) {
                Toast.makeText(getActivity(), "Please fill all visit fields", Toast.LENGTH_LONG).show();
            } else if (imagePath1 == null && imagePath2 == null && imagePath3 == null) {
                Toast.makeText(getActivity(), "You must add at least one image .", Toast.LENGTH_LONG).show();
            } else {
                pd.show();
                if (imagePath1 != null) {
                    imagePaths.add(imagePath1);
                } else {
                    imagePaths.add(null);
                }
                if (imagePath2 != null) {
                    imagePaths.add(imagePath2);
                } else {
                    imagePaths.add(null);
                }
                if (imagePath3 != null) {
                    imagePaths.add(imagePath3);
                } else {
                    imagePaths.add(null);
                }
                String shopName = shopNameET.getText().toString();
                String doing = doingET.getText().toString();
                String address = locationTV.getText().toString();

                ParseObject visitParseObject = new ParseObject("Ivisited");
                visitParseObject.put("store_name", shopName);
                visitParseObject.put("dowing", doing);
                visitParseObject.put("adresss", address);
                if (imagePaths.get(0) != null) {
                    ParseFile pic1 = new ParseFile("vistiimage.jpg", getImageBytes(imagePaths.get(0)));
                    visitParseObject.put("picture1", pic1);
                    pic1.saveInBackground();
                }
                if (imagePaths.get(1) != null) {
                    ParseFile pic2 = new ParseFile("visitimage.jpg", getImageBytes(imagePaths.get(1)));
                    visitParseObject.put("picture2", pic2);
                    pic2.saveInBackground();
                }
                if (imagePaths.get(2) != null) {
                    ParseFile pic3 = new ParseFile("visitimage.jpg", getImageBytes(imagePaths.get(2)));
                    visitParseObject.put("picture3", pic3);
                    pic3.saveInBackground();
                }
                // uploadPhotos(visitParseObject,imagePaths,0);
                postToClode(visitParseObject);

            }
        } else {
            Toast.makeText(getActivity(), "Check Internet Connection", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.newvisit_photo1:
                imageNo = 1;
                openPickPhotoDialog();
                break;
            case R.id.newvisit_photo2:
                imageNo = 2;
                openPickPhotoDialog();
                break;
            case R.id.newvisit_photo3:
                imageNo = 3;
                openPickPhotoDialog();
                break;
            case R.id.newvisit_locationt_tv:

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
                File destination = new File(root, ".newvisit" + imageNo + ".jpg");
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
                String path = root + File.separator + ".newvisit" + imageNo + ".jpg";
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
                visitImage1.setImageBitmap(bm);
                imagePath1 = path;
                break;
            case 2:
                visitImage2.setImageBitmap(bm);
                imagePath2 = path;
                break;
            case 3:
                visitImage3.setImageBitmap(bm);
                imagePath3 = path;
                break;

        }
    }

    private void makeVisitsDire() {
        root = new File(Environment.getExternalStorageDirectory(), "Oncoti" + File.separator + "Visits");

        if (!root.exists()) {
            root.mkdirs();
        }
    }

    private void uploadPhotos(final ParseObject visitParseObject, final ArrayList<String> uploadImagePaths, int no) {
        uploadPhotoNo = no;
        if (uploadPhotoNo != 3) {
            final String picName = "picture" + uploadPhotoNo;
            ParseFile pic1 = new ParseFile("vistiimage.jpg", getImageBytes(imagePaths.get(uploadPhotoNo)));
            visitParseObject.put(picName, pic1);
            pic1.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Log.e("AddNewVisit", picName + " is uploaded");
                        uploadPhotoNo++;
                        if (uploadImagePaths.get(uploadPhotoNo) != null) {
                            uploadPhotos(visitParseObject, uploadImagePaths, uploadPhotoNo);
                        }
                    }
                }
            });
        } else {
            postToClode(visitParseObject);
        }

    }

    private void postToClode(ParseObject visitParseObject) {
        Log.e("AddNewVisit", "post object to cloud");
        visitParseObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                pd.dismiss();
                if (e == null) {
                    Toast.makeText(getActivity(), "Done", Toast.LENGTH_LONG).show();
                    getActivity().finish();
                } else {
                    Toast.makeText(getActivity(), "Error : " + e.getMessage(), Toast.LENGTH_LONG).show();
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
        Log.e("AddVisit", "Bytes :  " + image.toString());
        return image;
    }

    private void buildProgressDialog() {
        pd = new ProgressDialog(getActivity());
        pd.setMessage("Submitting Visit .....");

    }
}
