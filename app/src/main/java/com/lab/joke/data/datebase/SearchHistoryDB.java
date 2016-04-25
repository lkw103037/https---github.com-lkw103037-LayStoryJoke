package com.lab.joke.data.datebase;

/**
 * Created by luokaiwen on 15/6/26.
 * <p/>
 * 搜索历史表
 */
public class SearchHistoryDB extends BaseDB {

    public static final String TABLE_NAME = "t_search_history";

    public static final String COL_KEY = "search_key";
    public static final String COL_TYPE = "search_type";
    public static final String COL_TIME = "search_time";

    private static final int INDEX_KEY = 1;
    private static final int INDEX_TYPE = 2;
    private static final int INDEX_TIME = 3;

//    public void insertHistory(SearchHistory searchHistory) {
//
//        initDb();
//        mDb.beginTransaction();
//        try {
//            ContentValues cv = new ContentValues();
//
//            cv.put(COL_KEY, searchHistory.getKey());
//            cv.put(COL_TYPE, searchHistory.getType());
//            cv.put(COL_TIME, searchHistory.getTime());
//
//            mDb.insertOrThrow(TABLE_NAME, null, cv);
//
//            mDb.setTransactionSuccessful();
//
//        } catch (SQLiteException e) {
//
//        } finally {
//            mDb.endTransaction();
//        }
//    }
//
//    public void insertHistorys(ArrayList<SearchHistory> searchHistorys) {
//        initDb();
//        mDb.beginTransaction();
//        try {
//
//            for (SearchHistory searchHistory : searchHistorys) {
//
//                ContentValues cv = new ContentValues();
//
//                cv.put(COL_KEY, searchHistory.getKey());
//                cv.put(COL_TYPE, searchHistory.getType());
//                cv.put(COL_TIME, searchHistory.getTime());
//
//                mDb.insertOrThrow(TABLE_NAME, null, cv);
//
//            }
//            mDb.setTransactionSuccessful();
//
//        } catch (SQLiteException e) {
//
//        } finally {
//            mDb.endTransaction();
//        }
//    }
//
//    /**
//     * 查询现在历史记录有多少条
//     *
//     * @return
//     */
//    public int selectCount() {
//
//        Cursor cursor = null;
//        int count = 0;
//
//        try {
//            initDb();
//            String sql = "SELECT COUNT(1) FROM " + SearchHistoryDB.TABLE_NAME;
//            cursor = mDb.rawQuery(sql, new String[]{});
//
//            while (cursor.moveToNext()) {
//
//                count = cursor.getInt(0);
//            }
//
//            closeCursor(cursor);
//            return count;
//
//        } catch (Exception e) {
//
//            if (null != cursor) {
//                closeCursor(cursor);
//            }
//        }
//        return count;
//    }
//
//    /**
//     * 查询最近30条搜索历史
//     *
//     * @return
//     */
//    public ArrayList<SearchHistory> selectHistorys() {
//
//        if (selectCount() > 30) {
//            deleteOldSearch();
//        }
//
//        ArrayList<SearchHistory> searchHistorys = new ArrayList<SearchHistory>();
//        Cursor cursor = null;
//        try {
//
//            initDb();
//            searchHistorys = new ArrayList<SearchHistory>();
//
//            String sql = "SELECT * FROM " + SearchHistoryDB.TABLE_NAME + " ORDER BY " + COL_TIME + " desc limit 30";
//            cursor = mDb.rawQuery(sql, new String[]{});
//
//            while (cursor.moveToNext()) {
//                SearchHistory searchHistory = new SearchHistory();
//
//                searchHistory.setType(cursor.getInt(INDEX_TYPE));
//                searchHistory.setTime(cursor.getLong(INDEX_TIME));
//                searchHistory.setKey(cursor.getString(INDEX_KEY));
//
//                searchHistorys.add(searchHistory);
//            }
//
//            closeCursor(cursor);
//            return searchHistorys;
//        } catch (Exception e) {
//
//            if (null != cursor) {
//                closeCursor(cursor);
//            }
//        }
//
//        return searchHistorys;
//    }
//
//    /**
//     * 删除最新30条数据以后的数据
//     */
//    public void deleteOldSearch() {
//
//        Cursor cursor = null;
//        try {
//            initDb();
//            String topTenSql = "SELECT * FROM " + SearchHistoryDB.TABLE_NAME + " ORDER BY " + COL_TIME + " desc limit 30";
//            String sql = "DELETE FROM " + SearchHistoryDB.TABLE_NAME + " WHERE " + COL_TIME + " NOT IN (" + topTenSql + ")";
//            cursor = mDb.rawQuery(sql, new String[]{});
//
//            closeCursor(cursor);
//        } catch (Exception e) {
//
//            if (null != cursor) {
//                closeCursor(cursor);
//            }
//        }
//    }
//
//    /**
//     * 删除所有搜索历史
//     */
//    public void deletes() {
//        initDb();
//        try {
//            mDb.execSQL("DELETE FROM " + TABLE_NAME);
//        } catch (SQLiteException e) {
//
//        }
//    }
}
