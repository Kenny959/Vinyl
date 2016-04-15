package com.vanmeenen.registration;

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
    RegistrationAdapter adapter;
    RegistrationOpenHelper helper;
    EditText fnameEdit, lnameEdit;
    Button submitBtn, resetBtn;

    //Camera
    private static final int CAMERA_REQUEST = 1888;
    ImageView mimageView;
    TextView tv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        fnameEdit = (EditText) findViewById(R.id.et_fname);
        lnameEdit = (EditText) findViewById(R.id.et_lname);
        submitBtn = (Button) findViewById(R.id.btn_submit);
        resetBtn = (Button) findViewById(R.id.btn_reset);
        adapter = new RegistrationAdapter(this);

        submitBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String fnameValue = fnameEdit.getText().toString();
                String lnameValue = lnameEdit.getText().toString();
                long val = adapter.insertDetails(fnameValue, lnameValue);
                // Toast.makeText(getApplicationContext(), Long.toString(val),
                // 300).show();
                finish();
            }
        });
        resetBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                fnameEdit.setText("");
                lnameEdit.setText("");
            }
        });

        //Camera
        mimageView = (ImageView) this.findViewById(R.id.image_from_camera);
        Button button = (Button) this.findViewById(R.id.take_image_from_camera);
        tv = (TextView) this.findViewById(R.id.textView4);
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
