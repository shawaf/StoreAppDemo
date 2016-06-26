package com.oncoti.Adapters;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.oncoti.Fragments.StartScreenFragment;
import com.oncoti.R;

import java.util.ArrayList;
import java.util.List;

public class ImageSlideAdapter extends PagerAdapter {

    FragmentActivity activity;
    List<String> products;
    Fragment holdFrag;
    ArrayList<String> sliderImages ;
    String[] sliderText ;

    public ImageSlideAdapter(FragmentActivity activity,
                             Fragment holdFrag,String [] sliderText,ArrayList<String> sliderImages) {
        this.activity = activity;
        this.holdFrag = holdFrag;
        this.sliderImages=sliderImages;
        this.sliderText=sliderText;

    }

    @Override
    public int getCount() {
        return sliderImages.size();
    }

    @Override
    public View instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slider_view, container, false);

        ImageView mImageView = (ImageView) view
                .findViewById(R.id.slider_iv);
        TextView mtextView = (TextView) view
                .findViewById(R.id.slider_tv);

        UrlImageViewHelper.setUrlDrawable(mImageView, sliderImages.get(position), R.drawable.logo1);
       //mImageView.setImageResource(sliderImages[position]);
        mtextView.setText(sliderText[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


}