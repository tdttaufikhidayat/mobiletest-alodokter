package com.test.alodokter.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.alodokter.R;
import com.test.alodokter.adapter.TabMenuAdapter;
import com.test.alodokter.utils.Utils;

import java.util.Objects;

/*
|---------------------------------------------------------------------------------------------------
| Created by TDT on 10/10/2018.
|---------------------------------------------------------------------------------------------------
*/
public class TabMenu extends Fragment {
    private Context context;

    private TabLayout tablayout;
    private ViewPager viewpager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view   = inflater.inflate(R.layout.menu_tablayout, container, false);
        context     = getActivity();

        loadResources(view);
        loadListener();

        return view;
    }

    private void loadResources(View view) {
        tablayout   = view.findViewById(R.id.mn_tblyt_tablayout);
        viewpager   = view.findViewById(R.id.mn_tblyt_viewpager);
    }

    private void loadListener() {
        tablayout.addTab(tablayout.newTab().setIcon(R.mipmap.home));
        tablayout.addTab(tablayout.newTab().setIcon(R.mipmap.profile));

        //==========================================================================================

        final TabMenuAdapter adapter = new TabMenuAdapter
                (Objects.requireNonNull(getActivity()).getSupportFragmentManager(), tablayout.getTabCount());

        viewpager.setAdapter(adapter);
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        viewpager.setOffscreenPageLimit(8);

        //noinspection deprecation
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //==========================================================================================

        ViewGroup viewGroup = (ViewGroup) tablayout.getChildAt(0);
        int tabsCount       = viewGroup.getChildCount();

        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab     = (ViewGroup) viewGroup.getChildAt(j);
            int tabChildsCount  = vgTab.getChildCount();

            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);

                if (tabViewChild instanceof TextView) {
                    Utils utils = new Utils();
                    ((TextView) tabViewChild).setTypeface(utils.setFontType(getActivity(), utils.CHAMPAGNELIMOUSINES, utils.BOLD));
                }
            }
        }
    }
}
