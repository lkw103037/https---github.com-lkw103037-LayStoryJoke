package com.lab.joke.data.datebase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lab.joke.util.common.LogUtil;

public class DBOpenHelper extends SQLiteOpenHelper {
    public static final String TAG = DBOpenHelper.class.getSimpleName();
    public static final String DBNAME = "Sotry.db";
    public static final int VERSION = 1;

    private static DBOpenHelper mDBOpenHelper;

    public static synchronized DBOpenHelper getHelper() {
        return mDBOpenHelper;
    }

    public static synchronized void setContext(Context context) {
        if (mDBOpenHelper == null) {
            mDBOpenHelper = new DBOpenHelper(context);
        }
    }

    private DBOpenHelper(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /** 创建搜索历史表 */
        String search_history_sql = "CREATE TABLE IF NOT EXISTS " +
                SearchHistoryDB.TABLE_NAME + "(" + "_id integer primary key autoincrement, " +
                SearchHistoryDB.COL_KEY + " varchar(32) UNIQUE, " +
                SearchHistoryDB.COL_TYPE + " varchar(32), " +
                SearchHistoryDB.COL_TIME + " varchar(32) " + ")";

        db.execSQL(search_history_sql);

        LogUtil.e(TAG, "onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        LogUtil.e(TAG, "onUpgrade oldVersion is " + oldVersion + " newVersion is " + newVersion);

        if (oldVersion < 0) {

            LogUtil.e(TAG, "onUpgrade oldVersion");
            /** 删除标签表重新建立 */
            String drop_table = "DROP TABLE IF EXISTS " + SearchHistoryDB.TABLE_NAME;
            db.execSQL(drop_table);

            onCreate(db);
        }
    }
}
