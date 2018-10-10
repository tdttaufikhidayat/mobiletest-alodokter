package com.test.alodokter.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/*
|---------------------------------------------------------------------------------------------------
| Created by TDT on 10/10/2018.
|---------------------------------------------------------------------------------------------------
*/
public class ExitApplication extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            finishAndRemoveTask();
            System.exit(0);
        } else {
            finish();
            System.exit(0);
        }
    }

    public static void exitApplication(Context context) {
        Intent intent = new Intent(context, ExitApplication.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NO_ANIMATION |
                Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);

        context.startActivity(intent);
    }
}
