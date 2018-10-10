package com.test.alodokter.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.test.alodokter.R;
import com.test.alodokter.adapter.itemobject.HomeItem;
import com.test.alodokter.main.menu.home.HomeDetail;

import java.util.List;

/*
|---------------------------------------------------------------------------------------------------
| Created by TDT on 10/10/2018.
|---------------------------------------------------------------------------------------------------
*/
public class HomeAdapter extends BaseAdapter {
    private Context context;
    private List<HomeItem> itemData;

    public HomeAdapter(Context context, List<HomeItem> list) {
        this.context    = context;
        this.itemData   = list;
    }

    @Override
    public int getCount() {
        return itemData.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_home_item, parent, false);

        ImageView img_icon  = itemLayoutView.findViewById(R.id.mn_view_item_img_icon);

        HomeItem item       = itemData.get(position);
        final int menu_icon = item.getMENU_ICON();

        img_icon.setBackgroundResource(menu_icon);

        itemLayoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, HomeDetail.class);
                i.putExtra("menu_icon", menu_icon);
                context.startActivity(i);

                ((Activity)context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        return itemLayoutView;
    }
}
