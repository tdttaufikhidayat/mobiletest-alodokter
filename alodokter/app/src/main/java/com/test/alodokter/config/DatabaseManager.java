package com.test.alodokter.config;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.test.alodokter.database.TableLogin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/*
|---------------------------------------------------------------------------------------------------
| Created by TDT on 10/10/2018.
|---------------------------------------------------------------------------------------------------
*/
public class DatabaseManager extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME   = "alodokter-test";
    private static final int DATABASE_VERSION   = 1;

    @SuppressLint("SdCardPath")
    private static final String DATABASE_PATH   = Config.PATH_DEFAULT + "databases/";

    /*
    |-----------------------------------------------------------------------------------------------
    | The DAO object we use to access the SimpleData table
    |-----------------------------------------------------------------------------------------------
    */
    private Dao<TableLogin, Integer> TableLoginDAO                              = null;

    public DatabaseManager(Context context) {
        super(context, DATABASE_PATH + DATABASE_NAME, null, DATABASE_VERSION);

        boolean dbexist = checkdatabase();
        if (!dbexist) {

            // If database did not exist, try copying existing database from assets folder.
            try {
                File dir = new File(DATABASE_PATH);
                //noinspection ResultOfMethodCallIgnored
                dir.mkdirs();
                InputStream myinput = context.getAssets().open(DATABASE_NAME);
                String outfilename  = DATABASE_PATH + DATABASE_NAME;
                Log.i(DatabaseManager.class.getName(), "DB Path : " + outfilename);

                OutputStream myoutput   = new FileOutputStream(outfilename);
                byte[] buffer           = new byte[1024];
                int length;

                while ((length = myinput.read(buffer)) > 0) {
                    myoutput.write(buffer, 0, length);
                }

                myoutput.flush();
                myoutput.close();
                myinput.close();

                DatabaseManager db = new DatabaseManager(context);
                db.setUpgrade();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /*
    |-----------------------------------------------------------------------------------------------
    | Check whether or not database exist
    |-----------------------------------------------------------------------------------------------
    */
    private boolean checkdatabase() {
        boolean checkdb;

        String myPath   = DATABASE_PATH + DATABASE_NAME;
        File dbfile     = new File(myPath);
        checkdb         = dbfile.exists();

        Log.i(DatabaseManager.class.getName(), "DB Exist : " + checkdb);

        return checkdb;
    }

    private void setUpgrade() {
        SQLiteDatabase db   = getWritableDatabase();
        ConnectionSource cs = getConnectionSource();

        try {
            onUpgrade(db, cs, db.getVersion(), DATABASE_VERSION + 1);
        } catch (SQLiteException e) {
            System.out.println("Database doesn't exist");
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, TableLogin.class);
        } catch (SQLException e) {
            Log.e("this", "Can't create database", e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, TableLogin.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            Log.e("this", "Can't create database", e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Dao<TableLogin, Integer> getTableLoginDAO() {
        if (null == TableLoginDAO) {
            try {
                TableLoginDAO = getDao(TableLogin.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return TableLoginDAO;
    }

    /*
    |-----------------------------------------------------------------------------------------------
    | Close the database connections and clear any cached tableDAO.
    |-----------------------------------------------------------------------------------------------
    */
    @Override
    public void close() {
        super.close();

        TableLoginDAO               = null;
    }
}
