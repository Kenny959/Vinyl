package com.vanmeenen.vinyl;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class NewItemActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    ImageView mimageView;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mimageView = (ImageView) this.findViewById(R.id.image_from_camera);
        Button button = (Button) this.findViewById(R.id.take_image_from_camera);
        tv = (TextView) this.findViewById(R.id.camera_url_tv);
    }

    protected void addButton(View view){
        String name =  ((EditText)findViewById(R.id.Name)).getText().toString();
        String name2 =  ((EditText)findViewById(R.id.Name2)).getText().toString();
        String name3 =  ((EditText)findViewById(R.id.Name3)).getText().toString();
    }

    public void takeImageFromCamera(View view) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap mphoto = (Bitmap) data.getExtras().get("data");
            mimageView.setImageBitmap(mphoto);
            Uri uri = data.getData();

            tv.setText(tv.getText()+ uri.toString());
        }
    }
}
