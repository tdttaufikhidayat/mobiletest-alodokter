package com.test.alodokter.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/*
|---------------------------------------------------------------------------------------------------
| Created by TDT on 10/10/2018.
|---------------------------------------------------------------------------------------------------
*/
@DatabaseTable(tableName = "login")
public class TableLogin {
    public static final String TABLE_NAME       = "login";

    public static final String fNEW_ID          = "new_id";
    public static final String fEMAIL           = "email";
    public static final String fPASSWORD        = "password";

    @DatabaseField(generatedId = true)
    private int new_id;

    @DatabaseField
    private String email, password;

    public void setfNEW_ID(int new_id) {
        this.new_id = new_id;
    }
    public int getfNEW_ID() {
        return new_id;
    }

    public void setfEMAIL(String email) {
        this.email = email;
    }
    public String getfEMAIL() {
        return email;
    }

    public void setfPASSWORD(String password) {
        this.password = password;
    }
    public String getfPASSWORD() {
        return password;
    }
}