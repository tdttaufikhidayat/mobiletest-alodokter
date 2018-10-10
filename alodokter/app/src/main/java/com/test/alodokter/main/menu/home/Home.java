package com.test.alodokter.main.menu.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.test.alodokter.R;
import com.test.alodokter.adapter.HomeAdapter;
import com.test.alodokter.adapter.itemobject.HomeItem;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {
    private Context context;
    private GridView gridview;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view   = inflater.inflate(R.layout.menu_home, container, false);
        context     = getActivity();

        loadResources(view);
        loadListener();
        loadListGridview();

        return view;
    }

    private void loadResources(View view) {
        // note bisa pake gridview, listview, recyclerview
        // berhubung soal test nya cuma munculin image jadinya pake gridview aja biar bisa set columns > 1.
        // heheehehe... TDTmobileapps

        gridview = view.findViewById(R.id.mn_home_gridview);
    }

    private void loadListener() {
    }

    private void loadListGridview() {
        List<HomeItem> listHomeItem = new ArrayList<>();
        listHomeItem.clear();

        listHomeItem.add(new HomeItem(R.mipmap.icm_cat_1));
        listHomeItem.add(new HomeItem(R.mipmap.icm_cat_2));
        listHomeItem.add(new HomeItem(R.mipmap.icm_cat_3));
        listHomeItem.add(new HomeItem(R.mipmap.icm_cat_4));
        listHomeItem.add(new HomeItem(R.mipmap.icm_cat_5));
        listHomeItem.add(new HomeItem(R.mipmap.icm_cat_6));

        HomeAdapter adapter = new HomeAdapter(context, listHomeItem);
        gridview.setAdapter(adapter);

    }
}