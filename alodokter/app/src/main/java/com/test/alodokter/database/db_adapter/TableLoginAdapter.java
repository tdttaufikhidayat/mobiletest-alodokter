package com.test.alodokter.database.db_adapter;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import com.test.alodokter.config.DatabaseManager;
import com.test.alodokter.database.TableLogin;

import java.util.List;

/*
|---------------------------------------------------------------------------------------------------
| Created by TDT on 10/10/2018.
|---------------------------------------------------------------------------------------------------
*/
public class TableLoginAdapter {
    private Dao dao;
    private DatabaseManager helper;

    public TableLoginAdapter(Context context) {
        helper = new DatabaseManager(context);
    }

    private DatabaseManager getHelper() {
        return helper;
    }

    /*
    |-----------------------------------------------------------------------------------------------
    | Get Data by Condition
    |-----------------------------------------------------------------------------------------------
    */
    public List<TableLogin> getDatabyCondition(Context context, String condition, Object param) {
        List<TableLogin> listTable = null;

        try {
            helper  = OpenHelperManager.getHelper(context, DatabaseManager.class);
            dao     = helper.getDao(TableLogin.class);

            QueryBuilder<TableLogin, Integer> queryBuilder = dao.queryBuilder();
            Where<TableLogin, Integer> where = queryBuilder.where();
            where.eq(condition, param);

            listTable = queryBuilder.query();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listTable;
    }

    public List<TableLogin> getAllData(Context context) {
        List<TableLogin> listTable = null;
        try {
            helper  = OpenHelperManager.getHelper(context, DatabaseManager.class);
            dao     = helper.getDao(TableLogin.class);

            QueryBuilder<TableLogin, Integer> queryBuilder = dao.queryBuilder();

            listTable = queryBuilder.query();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listTable;
    }

    /*
    |-----------------------------------------------------------------------------------------------
    | Insert Data
    |-----------------------------------------------------------------------------------------------
    */
    public void insertData(TableLogin table, String email, String password) {
        if (email.equalsIgnoreCase("null")
                || email.equalsIgnoreCase(null)){
            email = "";
        }
        if (password.equalsIgnoreCase("null")
                || password.equalsIgnoreCase(null)){
            password = "";
        }

        try {
            table.setfEMAIL(email);
            table.setfPASSWORD(password);

            getHelper().getTableLoginDAO().create(table);

            if (helper.isOpen()) {
                try {
                    helper.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    |-----------------------------------------------------------------------------------------------
    | Update Data by Condition
    |-----------------------------------------------------------------------------------------------
    */
    public void updateDatabyCondition(Context context, String column, Object value, String condition,
                                      Object param) {
        try {
            helper  = OpenHelperManager.getHelper(context, DatabaseManager.class);
            dao     = helper.getDao(TableLogin.class);

            UpdateBuilder<TableLogin, Integer> updateBuilder = dao.updateBuilder();
            updateBuilder.updateColumnValue(column, value);
            updateBuilder.where().eq(condition, param);
            updateBuilder.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAllData(Context context, String email, String password, String condition, Object param) {
        if (email.equalsIgnoreCase("null")
                || email.equalsIgnoreCase(null)){
            email = "";
        }
        if (password.equalsIgnoreCase("null")
                || password.equalsIgnoreCase(null)){
            password = "";
        }

        try {
            helper  = OpenHelperManager.getHelper(context, DatabaseManager.class);
            dao     = helper.getDao(TableLogin.class);

            UpdateBuilder<TableLogin, Integer> updateBuilder = dao.updateBuilder();
            updateBuilder.where().eq(condition, param);
            updateBuilder.updateColumnValue(TableLogin.fEMAIL, email);
            updateBuilder.updateColumnValue(TableLogin.fPASSWORD, password);
            updateBuilder.update();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    |-----------------------------------------------------------------------------------------------
    | Delete Data by Condition
    |-----------------------------------------------------------------------------------------------
    */
    public void deleteDatabyCondition(Context context, String condition, Object value) {
        try {
            helper  = OpenHelperManager.getHelper(context, DatabaseManager.class);
            dao     = helper.getDao(TableLogin.class);

            DeleteBuilder<TableLogin, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().eq(condition, value);
            deleteBuilder.delete();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllData(Context context) {
        try {
            helper  = OpenHelperManager.getHelper(context, DatabaseManager.class);
            dao     = helper.getDao(TableLogin.class);

            DeleteBuilder<TableLogin, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.delete();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
}
