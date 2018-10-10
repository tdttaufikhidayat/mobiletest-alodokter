package com.test.alodokter.main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.test.alodokter.R;
import com.test.alodokter.database.TableLogin;
import com.test.alodokter.database.db_adapter.TableLoginAdapter;
import com.test.alodokter.utils.Utils;
import com.test.alodokter.widget.DialogView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
|---------------------------------------------------------------------------------------------------
| Created by TDT on 10/10/2018.
|---------------------------------------------------------------------------------------------------
*/
public class SplashScreen extends AppCompatActivity {
    private Context context;
    private Utils utils;
    private DialogView dialogview;

    private ImageView img_loadone, img_loadtwo, img_loadthree;
    private Handler handler;
    private int running = 0, setimg = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_splashscreen);
        context = SplashScreen.this;

        loadResources();
        loadListener();
    }

    private void loadResources() {
        utils           = new Utils();
        dialogview      = new DialogView();
        handler         = new Handler();

        img_loadone     = findViewById(R.id.splashscreen_img_loadone);
        img_loadtwo     = findViewById(R.id.splashscreen_img_loadtwo);
        img_loadthree   = findViewById(R.id.splashscreen_img_loadthree);
    }

    private void loadListener() {
        setPermissionForm();
    }

    private void setPermissionForm() {
        String[] PERMISSIONS = {
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        if (!utils.checkPermissions(context, PERMISSIONS)) {
            int PERMISSIONS_ALL = 1;
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSIONS_ALL);
        }else {
            startMainMenu();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Map<String, Integer> perms = new HashMap<>();

        // Initial
        perms.put(Manifest.permission.READ_PHONE_STATE, PackageManager.PERMISSION_GRANTED);
        perms.put(Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
        perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);

        for (int i = 0; i < permissions.length; i++)
            perms.put(permissions[i], grantResults[i]);

        if (perms.get(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED
                && perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                && perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

            // All Permissions Granted
            startMainMenu();
        } else {
            showDialogAlertPermissions();
        }
    }

    private void showDialogAlertPermissions() {
        dialogview.showDialogAlertTwoButton(context,
                getString(R.string.message_denied), getString(R.string.exit), getString(R.string.try_again));

        //EXIT
        dialogview.dialogalert_btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View paramAnonymousView) {
                dialogview.dialogalert.dismiss();
                ExitApplication.exitApplication(context);
            }
        });

        //TRY AGAIN
        dialogview.dialogalert_btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View paramAnonymousView) {
                dialogview.dialogalert.dismiss();
                setPermissionForm();
            }
        });
    }

    @Override
    public void onBackPressed() {
    }

    private void startMainMenu() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (running <= 6000) {
                    running = running + 500;
                    Log.i(Utils.TAG("SplashScreen"), "running = " + String.valueOf(running));

                    try {
                        Thread.sleep(500); // Waits for 1 second (1000 milliseconds)
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (setimg == 0) {
                                img_loadone.setImageResource(R.color.blue_9);
                                img_loadtwo.setImageResource(R.color.white);
                                img_loadthree.setImageResource(R.color.white);
                                setimg = 1;

                            } else if (setimg == 1) {
                                img_loadone.setImageResource(R.color.white);
                                img_loadtwo.setImageResource(R.color.blue_9);
                                img_loadthree.setImageResource(R.color.white);
                                setimg = 2;

                            } else if (setimg == 2) {
                                img_loadone.setImageResource(R.color.white);
                                img_loadtwo.setImageResource(R.color.white);
                                img_loadthree.setImageResource(R.color.blue_9);
                                setimg = 0;
                            }
                        }
                    });
                }
            }
        }).start();

        int SPLASH_TIME_OUT = 3000;
        handler.postDelayed(new Runnable() {
            public void run() {
                TableLoginAdapter dbAdapterLogin    = new TableLoginAdapter(context);
                List<TableLogin> listLogin          = dbAdapterLogin.getAllData(context);

                if (listLogin != null && listLogin.size() > 0) {
                    Intent i = new Intent(context, MainMenu.class);
                    startActivity(i);
                    finish();

                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                } else {
                    Intent i = new Intent(context, Login.class);
                    startActivity(i);
                    finish();

                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            }
        }, SPLASH_TIME_OUT);
    }
}
