package com.lab.joke.data.datebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import java.io.File;

public class DBManager {

    private final int BUFFER_SIZE = 1024;
    public static final String DB_NAME = "ww_city.db";
    public static final String PACKAGE_NAME = "com.wonderworld.com";
    public static final String DB_PATH = "/data" + Environment.getDataDirectory().getAbsolutePath() + "/" + PACKAGE_NAME;
    private SQLiteDatabase database;
    private Context context;
    private File file = null;

    DBManager(Context context) {
        Log.e("cc", "DBManager");
        this.context = context;
    }

    public void openDatabase() {
        Log.e("cc", "openDatabase()");
        this.database = this.openDatabase(DB_PATH + "/" + DB_NAME);
    }

    public SQLiteDatabase getDatabase() {
        Log.e("cc", "getDatabase()");
        return this.database;
    }

    private SQLiteDatabase openDatabase(String dbfile) {
        try {
            database = SQLiteDatabase.openOrCreateDatabase(dbfile, null);
            return database;
        } catch (Exception e) {
            Log.e("cc", "exception " + e.toString());
        }
        return null;
    }

    public void closeDatabase() {
        Log.e("cc", "closeDatabase()");
        if (this.database != null)
            this.database.close();
    }
}