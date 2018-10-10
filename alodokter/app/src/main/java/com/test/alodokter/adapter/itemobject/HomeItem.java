package com.test.alodokter.adapter.itemobject;

/*
|---------------------------------------------------------------------------------------------------
| Created by TDT on 10/10/2018.
|---------------------------------------------------------------------------------------------------
*/
public class HomeItem {
    private int menu_icon;

    public HomeItem(int menu_icon) {
        setMENU_ICON(menu_icon);
    }

    private void setMENU_ICON(int menu_icon) {
        this.menu_icon = menu_icon;
    }
    public int getMENU_ICON() {
        return menu_icon;
    }
}