package com.den.bockreder;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @param tableName
     * @param columnIndex
     * @return a List of quotes
     */
    public List<String> getQuotes(final String tableName, int columnIndex) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT   *  FROM " + tableName, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(columnIndex));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<String> getDataByEntryId(final String entryId, int columnIndex) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT title, description FROM items WHERE  entry_id = " + entryId, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(columnIndex));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}