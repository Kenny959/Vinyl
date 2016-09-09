package com.vanmeenen.vinyls;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vanmeenen.vinyl.PhotoActivity;
import com.vanmeenen.vinyl.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class RegistrationActivity extends Activity {
    private static final String KEY_PHOTO_PATH = "KEY_PHOTO";
    MyAdapter adapter;
    MySQLiteOpenHelper mySQLiteOpenHelper;
    EditText singerNameAdd_et, songTitleAdd_et;
    Button submit_btn, reset_btn;
    private String photoPath;

    //Camera
    //private static final int CAMERA_REQUEST = 1888;
    ImageView mimageView;
    TextView tv;
    private final int MY_REQUEST_CODE= 777;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        singerNameAdd_et = (EditText) findViewById(R.id.singerNameAdd_et);
        songTitleAdd_et = (EditText) findViewById(R.id.songTitleAdd_et);
        submit_btn = (Button) findViewById(R.id.submit_btn);
        reset_btn = (Button) findViewById(R.id.reset_btn);
        adapter = new MyAdapter(this);

        // Activity is being recreated after screen orientation change
        if (savedInstanceState != null) {
            photoPath = savedInstanceState.getString(KEY_PHOTO_PATH);
        }

        submit_btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String singerNameValue = singerNameAdd_et.getText().toString();
                String songTitleValue = songTitleAdd_et.getText().toString();
                Vinyl vinyl = new Vinyl(singerNameValue, songTitleValue, photoPath);

                long val = adapter.insert(vinyl);
                finish();
            }
        });
        reset_btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                singerNameAdd_et.setText("");
                songTitleAdd_et.setText("");
            }
        });

        //Camera
        mimageView = (ImageView) this.findViewById(R.id.image_from_camera);
        Button button = (Button) this.findViewById(R.id.take_image_from_camera);

        tv = (TextView) this.findViewById(R.id.camera_url_tv);
        tv.setText(photoPath);
    }


    public void takeImageFromCamera(View view) {
        Intent intent = new Intent(this, PhotoActivity.class);
        startActivityForResult(intent, MY_REQUEST_CODE);
}


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            mimageView.setImageBitmap(mphoto);
        }
        */

        if (requestCode == MY_REQUEST_CODE && resultCode == RESULT_OK) {
            photoPath = data.getStringExtra("picturePath");
            tv.setText(photoPath);
        }

        //Uri uri = data.getData();
        //tv.setText(tv.getText()+ uri.toString());
    }

    /**
     * Save information in Bundle in case screen orientation changes when picture is taken.
     * Orientation change causes Activity to be recreated, and any variables lost unless saved.
     * @param saveState
     */
    @Override
    protected void onSaveInstanceState (Bundle saveState) {
        super.onSaveInstanceState(saveState);
        saveState.putString(KEY_PHOTO_PATH, photoPath);
    }
}
