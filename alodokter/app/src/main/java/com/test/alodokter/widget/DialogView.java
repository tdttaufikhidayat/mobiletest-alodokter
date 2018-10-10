package com.test.alodokter.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.alodokter.R;

/*
|---------------------------------------------------------------------------------------------------
| Created by TDT on 10/10/2018.
|---------------------------------------------------------------------------------------------------
*/
public class DialogView {
    public Dialog dialogalert;
    public Button dialogalert_btn_center, dialogalert_btn_left, dialogalert_btn_right;

    /*
    |-----------------------------------------------------------------------------------------------
    | Implemet Dialog Alert
    |-----------------------------------------------------------------------------------------------
    */
    private void loadDialogAlert(Context context, Boolean onebtn, String message,
                                 String onebtn_center, String twobtn_left, String twobtn_right) {
        dialogalert = new Dialog(context);
        dialogalert.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogalert.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogalert.setContentView(R.layout.dialog_alert);

        //==========================================================================================

        TextView txt_message        = dialogalert.findViewById(R.id.dialogalert_txt_message);

        LinearLayout lyt_onebtn     = dialogalert.findViewById(R.id.dialogalert_lyt_onebtn);
        LinearLayout lyt_twobtn     = dialogalert.findViewById(R.id.dialogalert_lyt_twobtn);
        dialogalert_btn_center      = dialogalert.findViewById(R.id.dialogalert_btn_center);
        dialogalert_btn_left        = dialogalert.findViewById(R.id.dialogalert_btn_left);
        dialogalert_btn_right       = dialogalert.findViewById(R.id.dialogalert_btn_right);

        txt_message.setText(message);
        dialogalert_btn_center.setText(onebtn_center);
        dialogalert_btn_left.setText(twobtn_left);
        dialogalert_btn_right.setText(twobtn_right);

        //==========================================================================================

        //dialogalert.getWindow().getAttributes().windowAnimations = R.style.AnimationScale;
        dialogalert.setCancelable(false);
        dialogalert.show();

        //==========================================================================================

        if (onebtn) {
            lyt_twobtn.setVisibility(View.GONE);
        } else {
            lyt_onebtn.setVisibility(View.GONE);
        }
    }

    /*
    |-----------------------------------------------------------------------------------------------
    |-----------------------------------------------------------------------------------------------
    */
    public void showDialogAlertOneButtonDismiss(Context context, String message) {
        loadDialogAlert(context, true, message, "OK", "", "");

        dialogalert_btn_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View paramAnonymousView) {
                dialogalert.dismiss();
            }
        });
    }

    public void showDialogAlertOneButton(Context context, String message) {
        loadDialogAlert(context, true, message, "OK", "", "");
    }

    public void showDialogAlertTwoButton(Context context, String message, String leftbutton, String rightbutton) {
        loadDialogAlert(context, false, message, "", leftbutton, rightbutton);
    }
}
