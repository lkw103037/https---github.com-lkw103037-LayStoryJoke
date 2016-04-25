package com.lab.joke.data.datebase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lab.joke.util.common.LogUtil;

public class BaseDB {

    protected String TAG = getClass().getSimpleName();
    protected static DBOpenHelper mOpenHelper;
    protected static SQLiteDatabase mDb;

    static {
        mOpenHelper = DBOpenHelper.getHelper();
        mDb = mOpenHelper.getWritableDatabase();
    }

    public static void closeDb() {
        if (mDb != null) {
            mDb.close();
        }
    }

    protected void initDb() {
        
        int version = mDb.getVersion();
        LogUtil.e(TAG, "DB version is " + version);
        // mDb = mOpenHelper.getWritableDatabase();
    }

    protected void closeCursor(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }
}
