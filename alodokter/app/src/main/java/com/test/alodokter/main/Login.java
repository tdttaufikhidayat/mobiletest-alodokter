package com.test.alodokter.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.test.alodokter.R;
import com.test.alodokter.database.TableLogin;
import com.test.alodokter.database.db_adapter.TableLoginAdapter;
import com.test.alodokter.utils.StringValue;
import com.test.alodokter.utils.Utils;

/*
|---------------------------------------------------------------------------------------------------
| Created by TDT on 10/10/2018.
|---------------------------------------------------------------------------------------------------
*/
public class Login extends AppCompatActivity {
    private Context context;
    private Utils utils;

    private TextInputLayout txtinput_email, txtinput_password;
    private EditText edt_email, edt_password;
    private Button btn_login;
    private Boolean backPressAgain = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login);
        context = Login.this;

        loadResources();
        loadListener();
    }

    private void loadResources() {
        utils               = new Utils();

        txtinput_email      = findViewById(R.id.mainlogin_txtinput_email);
        edt_email           = findViewById(R.id.mainlogin_edt_email);
        txtinput_password   = findViewById(R.id.mainlogin_txtinput_password);
        edt_password        = findViewById(R.id.mainlogin_edt_password);
        btn_login           = findViewById(R.id.mainlogin_btn_login);
    }

    private void loadListener() {
        edt_email.addTextChangedListener(new MyTextWatcher(edt_email));
        edt_password.addTextChangedListener(new MyTextWatcher(edt_password));

        //set inputtype password :: jika di pasang di xml =>> fonttype tidak bekerja.
        edt_password.setTransformationMethod(new PasswordTransformationMethod());
        txtinput_password.setErrorEnabled(false);
        edt_email.requestFocus();

        btn_login.setOnClickListener(btn_login_listener);
    }

    /*
    |-----------------------------------------------------------------------------------------------
    | Edittext Email Listener
    |-----------------------------------------------------------------------------------------------
    */
    private class MyTextWatcher implements TextWatcher {
        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.mainlogin_edt_email:
                    validateEmail();
                    txtinput_password.setErrorEnabled(false);
                    break;

                case R.id.mainlogin_edt_password:
                    validatePassword();
                    txtinput_email.setErrorEnabled(false);
                    break;
            }
        }
    }

    /*
    |-----------------------------------------------------------------------------------------------
    |-----------------------------------------------------------------------------------------------
    */
    private boolean validateEmail() {
        try {
            String email = edt_email.getText().toString().trim();

            if (email.isEmpty()) {
                txtinput_email.setError(StringValue.alert_pleaseenter("email"));
                edt_email.requestFocus();
                return false;

            } else {
                txtinput_email.setErrorEnabled(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    /*
    |-----------------------------------------------------------------------------------------------
    |-----------------------------------------------------------------------------------------------
    */
    private boolean validatePassword() {
        try {
            String password = edt_password.getText().toString().trim();

            if (password.isEmpty()) {
                txtinput_password.setError(StringValue.alert_pleaseenter("password"));
                edt_password.requestFocus();
                return false;

            } else {
                txtinput_password.setErrorEnabled(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    private View.OnClickListener btn_login_listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!validateEmail()) {
                btn_login.startAnimation(utils.animShake(context));

            } else if (!utils.isValidEmail(edt_email.getText().toString())) {
                txtinput_email.setError("email not valid");
                edt_email.setFocusable(true);
                edt_email.requestFocus();

                btn_login.startAnimation(utils.animShake(context));

            } else if (!validatePassword()) {
                btn_login.startAnimation(utils.animShake(context));

            } else if (edt_password.getText().toString().trim().length() < 6) {
                txtinput_password.setError("password min 6 character");
                edt_password.setFocusable(true);
                edt_password.requestFocus();

                btn_login.startAnimation(utils.animShake(context));

            } else {
                openMainMenu();
            }
        }
    };

    private void openMainMenu() {
        TableLoginAdapter dbAdapterLogin;

        dbAdapterLogin = new TableLoginAdapter(context);
        dbAdapterLogin.deleteAllData(context);

        dbAdapterLogin = new TableLoginAdapter(context);
        dbAdapterLogin.insertData(new TableLogin(), edt_email.getText().toString(), edt_password.getText().toString());

        Intent i = new Intent(context, MainMenu.class);
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
}
