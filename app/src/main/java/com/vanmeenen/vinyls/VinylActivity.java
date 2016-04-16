package com.vanmeenen.vinyls;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import com.vanmeenen.vinyl.R;


public class VinylActivity extends Activity {
    MyAdapter myAdapter;
    MySQLiteOpenHelper mySQLiteOpenHelper;
    // SQLiteDatabase db_ob;
    ListView listView;
    Button add_btn;
    Cursor cursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listView = (ListView) findViewById(R.id.vinylItem_lv);
        add_btn = (Button) findViewById(R.id.btn_register);
        myAdapter = new MyAdapter(this);

        String[] from = { mySQLiteOpenHelper.SINGER_NAME, mySQLiteOpenHelper.SONG_TITLE};
        int[] to = { R.id.singer_name_tv, R.id.song_title_tv};
        cursor = myAdapter.queryName();
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
                R.layout.row, cursor, from, to);
        listView.setAdapter(cursorAdapter);
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2,
                                    long arg3) {
                Bundle passdata = new Bundle();
                Cursor listCursor = (Cursor) arg0.getItemAtPosition(arg2);
                int nameId = listCursor.getInt(listCursor
                        .getColumnIndex(mySQLiteOpenHelper.KEY_ID));
                passdata.putInt("keyid", nameId);
                Intent passIntent = new Intent(VinylActivity.this,
                        EditActivity.class);
                passIntent.putExtras(passdata);
                startActivity(passIntent);
            }
        });
        add_btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent registerIntent = new Intent(VinylActivity.this,
                        RegistrationActivity.class);
                startActivity(registerIntent);
            }
        });



    }

    @Override
    public void onResume() {
        super.onResume();
        cursor.requery();

    }



}
