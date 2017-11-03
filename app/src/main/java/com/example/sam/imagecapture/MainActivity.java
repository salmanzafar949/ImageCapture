package com.example.sam.imagecapture;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


     static  final int REQUEST_IMAGE_CAPTURE=1;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Type Casting
        Button photobutton =(Button) findViewById(R.id.button2);
        imageView = (ImageView) findViewById(R.id.imageView);
        //Disable the button if user don't have camera
        if(!hascamera())
        {
            photobutton.setEnabled(false);
        }
    }

    // Function to Check user has a camera

    private boolean hascamera()
    {
        // Andorid built in class that Checks for camera
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    // click method for launching the camera

    public void Launch_Camera(View view)
    {
        // Intent for camera
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Take image and pass result along to activity result
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

    }

    // if you want to return the image taken

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {
            // get the photo
            Bundle extras = data.getExtras();
            // converting image to bitmap
            Bitmap photo = (Bitmap) extras.get("data");
            imageView.setImageBitmap(photo);
        }
    }
}
