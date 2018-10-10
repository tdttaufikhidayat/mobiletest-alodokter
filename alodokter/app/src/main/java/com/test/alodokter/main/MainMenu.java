package com.test.alodokter.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.test.alodokter.R;
import com.test.alodokter.database.db_adapter.TableLoginAdapter;
import com.test.alodokter.widget.DialogView;

/*
|---------------------------------------------------------------------------------------------------
| Created by TDT on 10/10/2018.
|---------------------------------------------------------------------------------------------------
*/
public class MainMenu extends AppCompatActivity {
    private Context context;
    private DialogView dialogview;

    private ImageView btn_back, btn_logout;

    private Boolean backPressAgain = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu);
        context = MainMenu.this;

        loadResources();
        loadListener();
        loadTabMenu();
    }

    private void loadResources() {
        dialogview      = new DialogView();

        btn_back        = findViewById(R.id.mnhd_btn_back);
        btn_logout      = findViewById(R.id.mnhd_btn_logout);
    }

    private void loadListener() {
        btn_back.setVisibility(View.GONE);
        btn_logout.setOnClickListener(btn_logout_listener);
    }

    private View.OnClickListener btn_logout_listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            loadDialogLogout();
        }
    };

    private void loadDialogLogout() {
        dialogview.showDialogAlertTwoButton(context,
                getString(R.string.message_logout), getString(R.string.no), getString(R.string.yes));

        dialogview.dialogalert_btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View paramAnonymousView) {
                dialogview.dialogalert.dismiss();
            }
        });
        dialogview.dialogalert_btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View paramAnonymousView) {
                dialogview.dialogalert.dismiss();

                logout();
            }
        });
    }

    private void logout() {
        TableLoginAdapter dbAdapterLogin = new TableLoginAdapter(context);
        dbAdapterLogin.deleteAllData(context);

        Intent i = new Intent(context, Login.class);
        startActivity(i);
        finish();

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void onBackPressed() {
        if (backPressAgain){
            ExitApplication.exitApplication(context);
        }else{
            Toast.makeText(context, getString(R.string.message_exit), Toast.LENGTH_SHORT).show();
            backPressAgain = true;
        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                backPressAgain = false;
            }
        }, 2000);
    }

    private void loadTabMenu() {
        Fragment fragment = new TabMenu();
        loadResourceFrag(fragment);

    }

    private void loadResourceFrag(Fragment fragment) {
        FragmentManager fragmentManager;
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                .replace(R.id.mainmenu_framelayout, fragment).commit();
    }
}
