package com.sh.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bite.R;
import com.sh.entities.RestaurantMenu;

/**
 * Created by himanshu on 2/21/15.
 */
public class MenuAdapter extends BaseAdapter {
    private final Context context;
    private final List<RestaurantMenu> items;
    private final Map<View, Map<Integer, View>> cache = new HashMap<View, Map<Integer, View>>();
    public MenuAdapter(Context context, List<RestaurantMenu> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        TextView tv;
        TextView tvPrice;
        TextView tvTags;
        final ImageView iv1;
        final ImageView iv2;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.menu_item, parent, false);
        }
        Map<Integer, View> itemMap = cache.get(v);
        if (itemMap == null) {
            itemMap = new HashMap<Integer, View>();
            tv = (TextView) v.findViewById(android.R.id.text1);
            tvPrice = (TextView) v.findViewById(R.id.textView6);
            tvTags = (TextView) v.findViewById(R.id.textView8);
//            iv1 = (ImageView) v.findViewById(R.id.imageView1);
//            iv2 = (ImageView) v.findViewById(R.id.imageView2);
            itemMap.put(android.R.id.text1, tv);
//            itemMap.put(R.id.imageView1, iv1);
//            itemMap.put(R.id.imageView2, iv2);
            cache.put(v, itemMap);
        } else {
            tv = (TextView) itemMap.get(android.R.id.text1);
            tvPrice = (TextView) v.findViewById(R.id.textView6);
            tvTags = (TextView) v.findViewById(R.id.textView8);
//            iv1 = (ImageView) itemMap.get(R.id.imageView1);
//            iv2 = (ImageView) itemMap.get(R.id.imageView2);
        }
        RestaurantMenu restaurantMenuItem = (RestaurantMenu) getItem(position);

        final String item = restaurantMenuItem.getDishName();
        tv.setText(item);
        final String price = "$" + String.valueOf(restaurantMenuItem.getPrice());
        tvPrice.setText(price);
        final String tags =  restaurantMenuItem.getDishTags();
        tvTags.setText(tags);
//        iv1.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,
//                        String.format("Image 1 clicked: %s", item),
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        iv2.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,
//                        String.format("Image 2 clicked: %s", item),
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
        return v;
    }
}