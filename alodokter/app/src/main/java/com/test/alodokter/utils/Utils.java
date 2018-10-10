package com.test.alodokter.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.test.alodokter.R;

/*
|---------------------------------------------------------------------------------------------------
| Created by TDT on 10/10/2018.
|---------------------------------------------------------------------------------------------------
*/
public class Utils {
    public static String TAG(String value) {
        return "TDTmobileapps = " + value;
    }

    /*
    |-----------------------------------------------------------------------------------------------
    | Check Permision
    |-----------------------------------------------------------------------------------------------
    */
    public boolean checkPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }

        return true;
    }

    /*
    |-----------------------------------------------------------------------------------------------
    | Cek Validasi Email
    |-----------------------------------------------------------------------------------------------
    */
    public boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /*
    |-----------------------------------------------------------------------------------------------
    | Load Animation
    |-----------------------------------------------------------------------------------------------
    */
    public Animation animShake(Context context) {
        return AnimationUtils.loadAnimation(context, R.anim.shake);
    }

    /*
    |-----------------------------------------------------------------------------------------------
    | Load Font
    |-----------------------------------------------------------------------------------------------
    */
    public String STEELFISH             = "Steelfish";
    public String CHAMPAGNELIMOUSINES   = "ChampagneLimousines";
    public String NORMAL    = "NORMAL";
    public String BOLD      = "BOLD";

    public Typeface setFontType(Context context, String fontName, String fontType) {
        if (fontName.equalsIgnoreCase(STEELFISH)) {
            if (fontType.equalsIgnoreCase(NORMAL)) {
                return Typeface.createFromAsset(context.getAssets(), "fonts/Steelfish.ttf");
            } else {
                return null;
            }
        } else if (fontName.equalsIgnoreCase(CHAMPAGNELIMOUSINES)) {
            if (fontType.equalsIgnoreCase(NORMAL)) {
                return Typeface.createFromAsset(context.getAssets(), "fonts/Champagne Limousines.ttf");
            } else if (fontType.equalsIgnoreCase(BOLD)) {
                return Typeface.createFromAsset(context.getAssets(), "fonts/Champagne Limousines Bold.ttf");
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
