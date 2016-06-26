package com.oncoti.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.oncoti.Models.NavDrawerItem_Model;
import com.oncoti.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Shawaf on 8/28/2015.
 */
public class NavDrawerAdapter extends ArrayAdapter<NavDrawerItem_Model> {

    private Activity activity;
    private List<NavDrawerItem_Model> menuItems;

    public NavDrawerAdapter(Activity activity, List<NavDrawerItem_Model> menuItems) {
        super(activity, 0, menuItems);

        this.menuItems = menuItems;
        this.activity = activity;
    }

    static class ViewHolder{
        ImageView icon;
        TextView title ;
        TextView count;
    }

    @Override
    public int getViewTypeCount() {
        return 2;   //any number what you need.
    }

    @Override
    public int getItemViewType(int position) {
        //view type is managed as zero-based index.
        if (position == 5 || position == 9)
            return 1;
        else
            return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView=convertView;
        ViewHolder holder;
        if (rowView == null) {
            // View Recycling is managed separately based on its view type,
            // so you don't need to worry about view corruptions.

            LayoutInflater inflater = activity.getLayoutInflater();
            holder=new ViewHolder();
            int type = getItemViewType(position);
            switch (type) {
                case 0:
                    //inflate imageview here.
                    rowView = inflater.inflate(R.layout.nav_drawer_item, null, false);
                    holder.icon=(ImageView)rowView.findViewById(R.id.list_item_icon);
                    holder.title=(TextView)rowView.findViewById(R.id.list_item_text);
                    holder.count=(TextView)rowView.findViewById(R.id.list_item_count);

                    holder.icon.setImageResource(menuItems.get(position).getIcon());
                    holder.title.setText(menuItems.get(position).getName());
                    if(menuItems.get(position).getCount() != 0) {
                        holder.count.setVisibility(View.VISIBLE);
                        holder.count.setText(""+menuItems.get(position).getCount());
                    }else {
                        holder.count.setVisibility(View.INVISIBLE);
                    }

                    break;
                case 1:
                    //inflate webview here.
                    rowView = inflater.inflate(R.layout.nav_drawer_line, null, false);
            }

            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }
        return rowView;
    }
}//end of inner class

