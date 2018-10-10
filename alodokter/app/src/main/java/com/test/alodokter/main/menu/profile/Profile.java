package com.test.alodokter.main.menu.profile;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.alodokter.R;
import com.test.alodokter.database.TableLogin;
import com.test.alodokter.database.db_adapter.TableLoginAdapter;

import java.util.List;

public class Profile extends Fragment {
    private Context context;
    private TextView txt_email;

    private String email = "";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view   = inflater.inflate(R.layout.menu_profile, container, false);
        context     = getActivity();

        loadTableLogin();
        loadResources(view);
        loadListener();

        return view;
    }

    private void loadTableLogin() {
        TableLoginAdapter dbAdapterLogin = new TableLoginAdapter(context);
        List<TableLogin> listLogin          = dbAdapterLogin.getAllData(context);

        if (listLogin != null && listLogin.size() > 0) {
            TableLogin db_item = listLogin.get(listLogin.size() - 1);

            email = db_item.getfEMAIL();
        }
    }

    private void loadResources(View view) {
        txt_email = view.findViewById(R.id.mn_profile_txt_email);
    }

    private void loadListener() {
        txt_email.setText(email);
    }
}
