package com.test.alodokter.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.test.alodokter.main.menu.home.Home;
import com.test.alodokter.main.menu.profile.Profile;

/*
|---------------------------------------------------------------------------------------------------
| Created by TDT on 10/10/2018.
|---------------------------------------------------------------------------------------------------
*/
public class TabMenuAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;

    public TabMenuAdapter(FragmentManager manager, int NumOfTabs) {
        super(manager);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new Home();

            case 1:
                return new Profile();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
