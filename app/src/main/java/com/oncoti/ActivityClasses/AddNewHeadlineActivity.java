package com.oncoti.ActivityClasses;

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
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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

public class AddNewHeadlineActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView headlineIV;
    private EditText headlineTV;
    private ImageButton addImageBtn;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private Bitmap headlineImage = null;
    private String headlineText = null;
    private ConnectionDetector cd;
    private ProgressDialog pd;
    private Toolbar mToolbar;
    private ImageButton backBtn;
    private Button submitBtn;
    private File root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_headline);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        cd = new ConnectionDetector(this);

        initUI();
        initToolbar();
        makeHeadlineDire();
    }

    @Override
    protected void onResume() {
        super.onResume();
        buildProgressDialog();
    }

    private void initUI() {
        headlineIV = (ImageView) findViewById(R.id.newheadline_iv);
        headlineTV = (EditText) findViewById(R.id.newheadline_message_et);
        addImageBtn = (ImageButton) findViewById(R.id.newheadline_addimage_btn);

        addImageBtn.setOnClickListener(this);
    }
    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.newheadline_toolbar);
        backBtn = (ImageButton) mToolbar.findViewById(R.id.back_toolbar_menubtn);
        submitBtn=(Button)mToolbar.findViewById(R.id.back_toolbar_publishbtn);
        setSupportActionBar(mToolbar);


        backBtn.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.newheadline_addimage_btn:
                openPickPhotoDialog();
                break;
            case R.id.back_toolbar_publishbtn:
                submitHeadline();
                break;
            case R.id.back_toolbar_menubtn:
                finish();
                break;
        }
    }

    private void openPickPhotoDialog() {
        final CharSequence[] items = {"Take Photo", "From Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.PNG, 100, bytes);
                File destination = new File(root, ".newHeadlineImage.jpg");
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
                headlineIV.setImageBitmap(thumbnail);
                headlineImage = thumbnail;
            } else if (requestCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();
                String[] projection = {MediaStore.MediaColumns.DATA};
                Cursor cursor = managedQuery(selectedImageUri, projection, null, null,
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
                headlineIV.setImageBitmap(bm);
                headlineImage = bm;
            }
        }
    }

    private void uploadheadlinePic(ParseObject headlineObject) {

        // Convert it to byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        // Compress image to lower quality scale 1 - 100
        headlineImage.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] image = stream.toByteArray();

        // Create the ParseFile
        ParseFile file = new ParseFile("newheadline.jpg", image);
        headlineObject.put("picture", file);

        // Upload the image into Parse Cloud
        headlineObject.saveInBackground();

    }

    public void submitHeadline() {
        if(cd.isConnectingToInternet()) {
            if (headlineTV.getText().toString().matches("")) {
                Toast.makeText(AddNewHeadlineActivity.this, "Please Add your headline first", Toast.LENGTH_LONG).show();
            } else {
                pd.show();
                ParseObject headlineParseObject = new ParseObject("Headlines");

                //Add Message To headline ParseObject
                String headlineText = headlineTV.getText().toString();
                headlineParseObject.put("message", headlineText);

                //Add Picture To headline ParseObject
                if (headlineImage != null) {
                    uploadheadlinePic(headlineParseObject);
                }

                //Add Owner Object
                ParseUser parseUser=ParseUser.getCurrentUser();
                headlineParseObject.put("owner", parseUser);

                headlineParseObject.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        pd.dismiss();
                        if(e==null){
                            Toast.makeText(AddNewHeadlineActivity.this, "Done", Toast.LENGTH_LONG).show();
                            finish();
                        }else {
                            Toast.makeText(AddNewHeadlineActivity.this, "Error : "+e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });


            }
        }else {
            Toast.makeText(AddNewHeadlineActivity.this, "Check Internet Connection", Toast.LENGTH_LONG).show();
        }
    }

    private void buildProgressDialog(){
        pd=new ProgressDialog(this);
        pd.setMessage("Submitting Headline .....");

    }

    private void makeHeadlineDire() {
        root = new File(Environment.getExternalStorageDirectory(), "Oncoti" + File.separator + "Headline");

        if (!root.exists()) {
            root.mkdirs();
        }
    }
}
