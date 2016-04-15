package com.vanmeenen.registration;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class RegistrationOpenHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "REGISTRATION_DB";
    public static final String TABLE_NAME = "REGISTRATION_TABLE";
    public static final int VERSION = 1;
    public static final String KEY_ID = "_id";
    public static final String FNAME = "F_NAME";
    public static final String LNAME = "L_NAME";
    public static final String SCRIPT = "create table " + TABLE_NAME + " ("
            + KEY_ID + " integer primary key autoincrement, " + FNAME
            + " text not null, " + LNAME + " text not null );";

    public RegistrationOpenHelper(Context context, String name,
                                  CursorFactory factory, int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("drop table " + TABLE_NAME);
        onCreate(db);
    }

}
