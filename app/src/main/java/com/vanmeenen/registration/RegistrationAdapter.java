package com.vanmeenen.registration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RegistrationAdapter {
    SQLiteDatabase database_ob;
    RegistrationOpenHelper openHelper_ob;
    Context context;

    public RegistrationAdapter(Context c) {
        context = c;
    }

    public RegistrationAdapter opnToRead() {
        openHelper_ob = new RegistrationOpenHelper(context,
                openHelper_ob.DATABASE_NAME, null, openHelper_ob.VERSION);
        database_ob = openHelper_ob.getReadableDatabase();
        return this;

    }

    public RegistrationAdapter opnToWrite() {
        openHelper_ob = new RegistrationOpenHelper(context,
                openHelper_ob.DATABASE_NAME, null, openHelper_ob.VERSION);
        database_ob = openHelper_ob.getWritableDatabase();
        return this;

    }

    public void Close() {
        database_ob.close();
    }

    public long insertDetails(String fname, String lname) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(openHelper_ob.FNAME, fname);
        contentValues.put(openHelper_ob.LNAME, lname);
        opnToWrite();
        long val = database_ob.insert(openHelper_ob.TABLE_NAME, null,
                contentValues);
        Close();
        return val;

    }

    public Cursor queryName() {
        String[] cols = { openHelper_ob.KEY_ID, openHelper_ob.FNAME,
                openHelper_ob.LNAME };
        opnToWrite();
        Cursor c = database_ob.query(openHelper_ob.TABLE_NAME, cols, null,
                null, null, null, null);

        return c;

    }

    public Cursor queryAll(int nameId) {
        String[] cols = { openHelper_ob.KEY_ID, openHelper_ob.FNAME,
                openHelper_ob.LNAME };
        opnToWrite();
        Cursor c = database_ob.query(openHelper_ob.TABLE_NAME, cols,
                openHelper_ob.KEY_ID + "=" + nameId, null, null, null, null);

        return c;

    }

    public long updateldetail(int rowId, String fname, String lname) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(openHelper_ob.FNAME, fname);
        contentValues.put(openHelper_ob.LNAME, lname);
        opnToWrite();
        long val = database_ob.update(openHelper_ob.TABLE_NAME, contentValues,
                openHelper_ob.KEY_ID + "=" + rowId, null);
        Close();
        return val;
    }

    public int deletOneRecord(int rowId) {
        // TODO Auto-generated method stub
        opnToWrite();
        int val = database_ob.delete(openHelper_ob.TABLE_NAME,
                openHelper_ob.KEY_ID + "=" + rowId, null);
        Close();
        return val;
    }

}
