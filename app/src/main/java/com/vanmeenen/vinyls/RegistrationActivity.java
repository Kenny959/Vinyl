package com.vanmeenen.vinyls;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vanmeenen.vinyl.R;

public class RegistrationActivity extends Activity {
    MyAdapter adapter;
    MySQLiteOpenHelper mySQLiteOpenHelper;
    EditText singerNameAdd_et, songTitleAdd_et;
    Button submit_btn, reset_btn;

    //Camera
    private static final int CAMERA_REQUEST = 1888;
    ImageView mimageView;
    TextView tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        singerNameAdd_et = (EditText) findViewById(R.id.singerNameAdd_et);
        songTitleAdd_et = (EditText) findViewById(R.id.songTitleAdd_et);
        submit_btn = (Button) findViewById(R.id.submit_btn);
        reset_btn = (Button) findViewById(R.id.reset_btn);
        adapter = new MyAdapter(this);



        submit_btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String singerNameValue = singerNameAdd_et.getText().toString();
                String songTitleValue = songTitleAdd_et.getText().toString();

                Vinyl vinyl = new Vinyl(singerNameValue, songTitleValue);

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
    }


    public void takeImageFromCamera(View view) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            mimageView.setImageBitmap(mphoto);
        }

        Uri uri = data.getData();
        tv.setText(tv.getText()+ uri.toString());
    }
}
