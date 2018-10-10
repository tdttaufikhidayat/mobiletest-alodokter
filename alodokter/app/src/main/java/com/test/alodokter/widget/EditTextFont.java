package com.test.alodokter.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.util.Log;

import com.test.alodokter.R;

/*
|---------------------------------------------------------------------------------------------------
| Created by TDT on 09/23/2018.
|---------------------------------------------------------------------------------------------------
*/
public class EditTextFont extends AppCompatEditText {
    private static final String TAG = "EditText";

    public EditTextFont(Context context) {
        super(context);
    }

    public EditTextFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
        init();
    }

    public EditTextFont(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context, attrs);
        init();
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.Font);
        String customFont = a.getString(R.styleable.Font_fontname);
        setCustomFont(ctx, customFont);
        a.recycle();
    }

    public boolean setCustomFont(Context ctx, String asset) {
        Typeface tf = null;
        try {
            tf = Typeface.createFromAsset(ctx.getAssets(), "fonts/"+asset);
        } catch (Exception e) {
            Log.e(TAG, "Could not get typeface: "+e.getMessage());
            return false;
        }

        setTypeface(tf);
        return true;
    }

    private void init() {
        setFilters(new InputFilter[] {
                new EmojiExcludeFilter()
        });
    }
}