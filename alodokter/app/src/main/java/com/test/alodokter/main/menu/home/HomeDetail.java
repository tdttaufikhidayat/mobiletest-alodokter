package com.test.alodokter.main.menu.home;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.alodokter.R;
import com.test.alodokter.adapter.HomeDetailAdapter;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

/*
|---------------------------------------------------------------------------------------------------
| Created by TDT on 10/10/2018.
|---------------------------------------------------------------------------------------------------
*/
public class HomeDetail extends AppCompatActivity {
    private Context context;

    private ImageView btn_back, btn_logout;
    private TextView txt_title;

    private ViewPager mPager;
    private int currentPage = 0;
    private ArrayList<Integer> IMAGE = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_home_detail);
        context = HomeDetail.this;

        loadResources();
        loadListener();
    }

    private void loadResources() {
        btn_back        = findViewById(R.id.mnhd_btn_back);
        txt_title       = findViewById(R.id.mnhd_txt_title);
        btn_logout      = findViewById(R.id.mnhd_btn_logout);
    }

    private void loadListener() {
        btn_back.setOnClickListener(btn_back_listener);
        btn_logout.setVisibility(View.GONE);

        IMAGE.clear();

        Bundle extras   = getIntent().getExtras();
        if (extras != null) {
            IMAGE.add(extras.getInt("menu_icon"));
        }

        IMAGE.add(R.mipmap.img_1);
        IMAGE.add(R.mipmap.img_2);
        IMAGE.add(R.mipmap.img_3);
        IMAGE.add(R.mipmap.img_4);
        IMAGE.add(R.mipmap.img_5);

        loadSlideImage();
    }

    private View.OnClickListener btn_back_listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    private void loadSlideImage() {
        mPager = findViewById(R.id.pager);
        mPager.setAdapter(new HomeDetailAdapter(context, IMAGE));
        CircleIndicator indicator = findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == IMAGE.size()) {
                    currentPage = 0;
                }

                mPager.setCurrentItem(currentPage++, true);
            }
        };

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);
    }
}
