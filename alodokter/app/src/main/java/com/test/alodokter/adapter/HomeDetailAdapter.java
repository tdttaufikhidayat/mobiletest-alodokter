package com.test.alodokter.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.test.alodokter.R;

import java.util.ArrayList;

/*
|---------------------------------------------------------------------------------------------------
| Created by TDT on 10/10/2018.
|---------------------------------------------------------------------------------------------------
*/
public class HomeDetailAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<Integer> itemData;
    private LayoutInflater itemLayoutView;

    public HomeDetailAdapter(Context context, ArrayList<Integer> list) {
        this.context    = context;
        this.itemData   = list;
        itemLayoutView  = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return itemData.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup parent, int position) {
        View layoutView     = itemLayoutView.inflate(R.layout.menu_slide, parent, false);
        ImageView img_view  = layoutView.findViewById(R.id.mn_slide_img_view);

        img_view.setImageResource(itemData.get(position));

        parent.addView(layoutView, 0);
        return layoutView;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}
