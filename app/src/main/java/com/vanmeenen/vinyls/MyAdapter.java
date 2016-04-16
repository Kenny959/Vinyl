package com.vanmeenen.vinyls;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MyAdapter {
    SQLiteDatabase sqLiteDatabase_db;
    MySQLiteOpenHelper mySQLiteOpenHelper;
    Context context;

    public MyAdapter(Context c) {
        context = c;
    }

    public MyAdapter opnToRead() {
        mySQLiteOpenHelper = new MySQLiteOpenHelper(context,
                mySQLiteOpenHelper.DATABASE_NAME, null, mySQLiteOpenHelper.VERSION);
        sqLiteDatabase_db = mySQLiteOpenHelper.getReadableDatabase();
        return this;

    }

    public MyAdapter opnToWrite() {
        mySQLiteOpenHelper = new MySQLiteOpenHelper(context,
                mySQLiteOpenHelper.DATABASE_NAME, null, mySQLiteOpenHelper.VERSION);
        sqLiteDatabase_db = mySQLiteOpenHelper.getWritableDatabase();
        return this;

    }

    public void Close() {
        sqLiteDatabase_db.close();
    }

    public long insert(Vinyl vinyl) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(mySQLiteOpenHelper.SINGER_NAME, vinyl.getName());
        contentValues.put(mySQLiteOpenHelper.SONG_TITLE, vinyl.getDescription());
        opnToWrite();
        long val = sqLiteDatabase_db.insert(mySQLiteOpenHelper.TABLE_NAME, null,
                contentValues);
        Close();
        return val;

    }

    public Cursor queryName() {
        String[] cols = { mySQLiteOpenHelper.KEY_ID, mySQLiteOpenHelper.SINGER_NAME,
                mySQLiteOpenHelper.SONG_TITLE};
        opnToWrite();
        Cursor c = sqLiteDatabase_db.query(mySQLiteOpenHelper.TABLE_NAME, cols, null,
                null, null, null, null);

        return c;

    }

    public Cursor queryAll(int nameId) {
        String[] cols = { mySQLiteOpenHelper.KEY_ID, mySQLiteOpenHelper.SINGER_NAME,
                mySQLiteOpenHelper.SONG_TITLE};
        opnToWrite();
        Cursor c = sqLiteDatabase_db.query(mySQLiteOpenHelper.TABLE_NAME, cols,
                mySQLiteOpenHelper.KEY_ID + "=" + nameId, null, null, null, null);

        return c;

    }

    public long update(int rowId, Vinyl vinyl) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(mySQLiteOpenHelper.SINGER_NAME, vinyl.getName());
        contentValues.put(mySQLiteOpenHelper.SONG_TITLE, vinyl.getDescription());
        opnToWrite();
        long val = sqLiteDatabase_db.update(mySQLiteOpenHelper.TABLE_NAME, contentValues,
                mySQLiteOpenHelper.KEY_ID + "=" + rowId, null);
        Close();
        return val;
    }

    public int delete(int rowId) {
        opnToWrite();
        int val = sqLiteDatabase_db.delete(mySQLiteOpenHelper.TABLE_NAME,
                mySQLiteOpenHelper.KEY_ID + "=" + rowId, null);
        Close();
        return val;
    }

}
