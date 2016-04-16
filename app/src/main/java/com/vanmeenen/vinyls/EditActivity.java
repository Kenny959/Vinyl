package com.vanmeenen.vinyls;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.vanmeenen.vinyl.R;

public class EditActivity extends Activity {
    MyAdapter myAdapter;
    MySQLiteOpenHelper mySQLiteOpenHelper;
    int rowId;
    Cursor c;
    EditText singerName, songTitle;
    Button add_btn, delete_btn;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editregister);
        singerName = (EditText) findViewById(R.id.singerNameEdit_et);
        songTitle = (EditText) findViewById(R.id.songTitleAdd_et);
        add_btn = (Button) findViewById(R.id.add_btn);
        delete_btn = (Button) findViewById(R.id.delete_btn);

        Bundle showData = getIntent().getExtras();
        rowId = showData.getInt("keyid");
        // Toast.makeText(getApplicationContext(), Integer.toString(rowId),
        // 500).show();
        myAdapter = new MyAdapter(this);

        c = myAdapter.queryAll(rowId);

        if (c.moveToFirst()) {
            do {
                singerName.setText(c.getString(1));
                songTitle.setText(c.getString(2));

            } while (c.moveToNext());
        }

        add_btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Vinyl vinyl = new Vinyl(singerName.getText().toString(), songTitle.getText().toString());
                myAdapter.update(rowId, vinyl);
                finish();
            }
        });
        delete_btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                myAdapter.delete(rowId);
                finish();
            }
        });
    }
}
