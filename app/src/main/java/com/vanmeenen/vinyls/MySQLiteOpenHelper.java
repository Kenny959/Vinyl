package com.vanmeenen.vinyls;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.vanmeenen.vinyl.db.MySQLiteHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "VINYL_DB";
    public static final String TABLE_NAME = "VINYL_TABLE";
    public static final int VERSION = 2;
    public static final String KEY_ID = "_id";
    public static final String SINGER_NAME = "SINGER_NAME";
    public static final String SONG_TITLE = "SONG_TITLE";
    public static final String PHOTO_URL = "PHOTO_URL";

    public static final String NEW_TABLE = "create table " + TABLE_NAME + " ("
            + KEY_ID + " integer primary key autoincrement, " + SINGER_NAME
            + " text not null, " + SONG_TITLE + " text not null, " + PHOTO_URL + "text not null)";

    public MySQLiteOpenHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NEW_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(), "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        //String upgradeQuery = " ALTER TABLE " + TABLE_NAME + "ADD COLUMN " + PHOTO_URL
        onCreate(db);
    }

}
