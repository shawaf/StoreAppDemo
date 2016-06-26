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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.oncoti.ActivityClasses.StartActivity;
import com.oncoti.Dialogs.ChangePass_Dialog;
import com.oncoti.Dialogs.ContactsInfo_Dialog;
import com.oncoti.R;
import com.oncoti.Utilites.RoundedImageView;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Shawaf on 9/11/2015.
 */
public class SettingsFragment extends Fragment implements View.OnClickListener {
    private View v;
    private ListView settingsLV;
    private RoundedImageView changePhotoIV;
    private View infoPart, editPart;
    private LinearLayout logoutLay;
    private TextView firstNameTV, lastNameTV, userNameTV, emailTV, addressTV, countryTV, phoneTV, changePhotoTV;
    private LinearLayout changePassLay, contactInfoLay, notifLay, confidLay, payMethodsLay, inviteFriendsLay;
    private ProgressBar changePhotoPB;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private ProgressDialog pd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.settings_frag, container, false);

        initUI();
        fillData();
        return v;
    }

    private void initUI() {
        infoPart = v.findViewById(R.id.info_part);
        editPart = v.findViewById(R.id.edit_part);
        logoutLay = (LinearLayout) v.findViewById(R.id.logout_lay);
        changePhotoIV = (RoundedImageView) v.findViewById(R.id.changephoto_iv);
        changePhotoTV = (TextView) v.findViewById(R.id.changephoto_tv);
        changePhotoPB = (ProgressBar) v.findViewById(R.id.changephoto_pb);

        firstNameTV = (TextView) infoPart.findViewById(R.id.item_firstname_value);
        lastNameTV = (TextView) infoPart.findViewById(R.id.item_lastname_value);
        userNameTV = (TextView) infoPart.findViewById(R.id.item_username_value);
        emailTV = (TextView) infoPart.findViewById(R.id.item_email_value);
        addressTV = (TextView) infoPart.findViewById(R.id.item_address_value);
        countryTV = (TextView) infoPart.findViewById(R.id.item_country_value);
        phoneTV = (TextView) infoPart.findViewById(R.id.item_phone_value);

        changePassLay = (LinearLayout) editPart.findViewById(R.id.settedit_changepass);
        contactInfoLay = (LinearLayout) editPart.findViewById(R.id.settedit_contactsinfo);
        notifLay = (LinearLayout) editPart.findViewById(R.id.settedit_notifications);
        confidLay = (LinearLayout) editPart.findViewById(R.id.settedit_confidentility);
        payMethodsLay = (LinearLayout) editPart.findViewById(R.id.settedit_paymethods);
        inviteFriendsLay = (LinearLayout) editPart.findViewById(R.id.settedit_invitefriends);

        changePassLay.setOnClickListener(this);
        contactInfoLay.setOnClickListener(this);
        notifLay.setOnClickListener(this);
        confidLay.setOnClickListener(this);
        payMethodsLay.setOnClickListener(this);
        inviteFriendsLay.setOnClickListener(this);
        changePhotoIV.setOnClickListener(this);
        logoutLay.setOnClickListener(this);


    }

    private void fillData() {
        ParseUser user = ParseUser.getCurrentUser();

        userNameTV.setText(user.getUsername());
        emailTV.setText(user.getEmail());
        if (user.get("first_name") != null) {
            firstNameTV.setText(user.get("first_name").toString());
        } else {
            firstNameTV.setText("null");
        }
        if (user.get("last_name") != null) {
            lastNameTV.setText(user.get("last_name").toString());
        } else {
            lastNameTV.setText("null");
        }
        if (user.get("adress") != null) {
            addressTV.setText(user.get("adress").toString());
        } else {
            addressTV.setText("null");
        }
        if (user.get("country") != null) {
            countryTV.setText(user.get("country").toString());
        } else {
            countryTV.setText("null");
        }
        if (user.get("phone") != null) {
            phoneTV.setText(user.get("phone").toString());
        } else {
            phoneTV.setText("null");
        }

        setProfilPic();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.changephoto_iv:
                openPickPhotoDialog();
                break;
            case R.id.settedit_changepass:
                ChangePass_Dialog changePass_dialog = new ChangePass_Dialog(getActivity());
                changePass_dialog.show();
                break;
            case R.id.settedit_contactsinfo:
                ContactsInfo_Dialog contactsInfo_dialog = new ContactsInfo_Dialog(getActivity());
                contactsInfo_dialog.show();
                break;
            case R.id.settedit_confidentility:

                break;
            case R.id.settedit_notifications:

                break;
            case R.id.settedit_paymethods:

                break;
            case R.id.settedit_invitefriends:
                inviteFriends();
                break;
            case R.id.logout_lay:
                showLogoutDialog();
                break;
        }
    }

    private void inviteFriends() {

//        String appLinkUrl = "https://fb.me/431581130384161";
//        String previewImageUrl = "http://www.playfresno.org/images/events/Moon.jpg";
//
//        if (AppInviteDialog.canShow()) {
//            AppInviteContent content = new AppInviteContent.Builder()
//                    .setApplinkUrl(appLinkUrl)
//                    .setPreviewImageUrl(previewImageUrl)
//                    .build();
//            AppInviteDialog.show(this, content);
//        }

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

    private void showLogoutDialog() {
        final AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());
        builder.setTitle(null);
        builder.setMessage("Are you sure you want to LogOut ?");
        builder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ParseUser.logOut();
                Intent startIntent=new Intent(getActivity(), StartActivity.class);
                startActivity(startIntent);
                getActivity().finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.PNG, 100, bytes);
                File destination = new File(Environment.getExternalStorageDirectory(),
                        System.currentTimeMillis() + ".jpg");
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
                uploadProfilPic(thumbnail);
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
                uploadProfilPic(bm);
            }
        }
    }

    private void uploadProfilPic(final Bitmap bitmap) {
        changePhotoTV.setVisibility(View.GONE);
        changePhotoPB.setVisibility(View.VISIBLE);
        // Convert it to byte
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        // Compress image to lower quality scale 1 - 100
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] image = stream.toByteArray();

        // Create the ParseFile
        ParseFile file = new ParseFile("pp.png", image);

        ParseUser user = ParseUser.getCurrentUser();
        user.put("avatar", file);

        // Upload the image into Parse Cloud
        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                changePhotoTV.setVisibility(View.VISIBLE);
                changePhotoPB.setVisibility(View.GONE);
                if (e == null) {
                    changePhotoIV.setImageBitmap(bitmap);
                    Toast.makeText(getActivity(), "Profile Pic Updated .", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void setProfilPic() {
        ParseUser user = ParseUser.getCurrentUser();
        ParseFile applicantResume = (ParseFile) user.get("avatar");
        applicantResume.getDataInBackground(new GetDataCallback() {
            public void done(byte[] data, ParseException e) {
                if (e == null) {
                    // data has the bytes for the resume
                    Bitmap bmp = BitmapFactory.decodeByteArray(data, 0, data.length);
                    changePhotoIV.setImageBitmap(bmp);
                } else {
                    // something went wrong
                }
            }
        });
    }
}
