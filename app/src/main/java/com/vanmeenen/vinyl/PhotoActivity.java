package com.vanmeenen.vinyl;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.vanmeenen.vinyls.RegistrationActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PhotoActivity extends Activity {

    private static final int REQUEST_IMAGE_CAPTURE = 1888;
    private ImageView imgFromCamera;
    private String mCurrentPhotoPath;

    private static final String KEY_PHOTO_PATH = "KEY_PHOTO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        imgFromCamera = (ImageView) this.findViewById(R.id.img_from_camera);

        // Activity is being recreated after screen orientation change
        if (savedInstanceState != null) {
            mCurrentPhotoPath = savedInstanceState.getString(KEY_PHOTO_PATH);
        }
    }

    public void goBackToRegistration(View view)
    {
        Intent intent = new Intent(this, RegistrationActivity.class);
        intent.putExtra("pictureStatus", "ok");
        intent.putExtra("picturePath", mCurrentPhotoPath);
        startActivity(intent);
    }

    public void takeImageFromCamera(View view) {

        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (camera.resolveActivity(getPackageManager()) != null) {
            /*
                The proper directory for shared photos is provided by getExternalStoragePublicDirectory(),
                with the DIRECTORY_PICTURES argument.
             */

            /*
                if you'd like the photos to remain private to your app only,
                you can instead use the directory provided by getExternalFilesDir()
             */

            // Create the File where the photo should go
            File photoFile = createImageFile();

            // Continue only if the File was successfully created
            if (photoFile != null) {
                camera.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(camera, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //mphoto = (Bitmap) data.getExtras().get("data");
            //imgFromCamera.setImageBitmap(mphoto);

            setPic();
        }
        */
    }

    /**
     * Save information in Bundle in case screen orientation changes when picture is taken.
     * Orientation change causes Activity to be recreated, and any variables lost unless saved.
     * @param saveState
     */
    @Override
    protected void onSaveInstanceState (Bundle saveState) {
        super.onSaveInstanceState(saveState);

        saveState.putString(KEY_PHOTO_PATH, mCurrentPhotoPath);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //savedInstanceState.getString("KEY_PHOTO_PATH");
    }

    private File createImageFile() /*throws IOException*/ {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        //File storageDir = Environment.getExternalStoragePublicDirectory(
        //        Environment.DIRECTORY_PICTURES);

        //FOR PRIVATE TO OUR APPLICATION
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File image = null;
        try {
            image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */
            );
            mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        }
        catch (java.io.IOException ioEx)
        {
            Log.i("ioException", ioEx.getMessage());
            mCurrentPhotoPath = null;
        }

        return image;
    }

    private void setPicture() {
        // Get the dimensions of the View
        int targetW = imgFromCamera.getWidth();
        int targetH = imgFromCamera.getHeight(); //200;

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true; //Check dimension

        if(BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions) == null) {
            try {
                InputStream in = getContentResolver().openInputStream(
                        Uri.parse(mCurrentPhotoPath));
                BitmapFactory.decodeStream(in, null, bmOptions);
            } catch (FileNotFoundException e) {
                // do something useless
                e.getMessage();
            }
            catch (Exception ex)
            {
                ex.getMessage();
            }
        }

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);
        Log.i("SCALE", "" + scaleFactor);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        //bmOptions.inBitmap

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        if(bitmap == null) {
            try {
                InputStream in = getContentResolver().openInputStream(
                        Uri.parse(mCurrentPhotoPath));
                bitmap = BitmapFactory.decodeStream(in, null, bmOptions);
            } catch (FileNotFoundException e) {
                // do something
                e.getMessage();
            }
            catch (Exception ex)
            {
                ex.getMessage();
            }
        }

        imgFromCamera.setImageBitmap(bitmap);
    }

    /**
     * Layout loading is done and visible on the screen.
     * We can get valid size here.
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        // Check if onCreate() has fired as the result of a screen orientation change.
        if (hasFocus && mCurrentPhotoPath != null) {
            setPicture();
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.i("onStart", "onStart ");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i("onResume", "onResume ");
    }

    protected void onPause() {
        super.onPause();
        Log.i("onPause", "onPause ");
    }

    protected void onRestart() {
        super.onRestart();
        //imgFromCamera.setImageBitmap(mphoto);
        Log.i("onRestart", "onRestart ");
    }

    protected void onStop() {
        super.onStop();
        Log.i("onStop", "onStop ");
    }

    /**
     * BackGround thread & long-running resources that could potentially leak memory if not properly closed.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();

        // Stop method tracing that the activity started during onCreate()
        android.os.Debug.stopMethodTracing();
    }
}
