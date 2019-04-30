package com.example.sameer.roomfinder;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

//import com.squareup.picasso.Picasso;

import java.io.File;

public class UploadImageActivity extends Activity {

    private static final String TAG = "==> UploadImageActivity";

    public static final int SELECT_IMAGE_REQUEST = 558;

    public String selectedImagePath = "";

    Button selectImage, uploadImage;
    ImageView selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

        this.selectedImage = (ImageView) findViewById(R.id.selectedImage);
        this.selectImage = (Button) findViewById(R.id.selectImage);
        this.uploadImage = (Button) findViewById(R.id.uploadImage);

        this.selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImageAction();
            }
        });

        this.uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });

    }


    private void selectImageAction(){
        if (Build.VERSION.SDK_INT <= 19) {
            Intent selectImageIntent = new Intent();
            selectImageIntent.setType("image/*");
            selectImageIntent.setAction(Intent.ACTION_GET_CONTENT);
            selectImageIntent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(selectImageIntent, SELECT_IMAGE_REQUEST);

        } else{
            Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, SELECT_IMAGE_REQUEST);

        }
    }

    /**
     * Upload image to the server
     */
    private void uploadImage(){
        try {
            //uploads the image to the server
            ImageUploader iu1=new ImageUploader();
                    iu1.uploadImage(this, System.currentTimeMillis() + ".jpg", this.selectedImagePath);

        }catch (Exception e){
            Log.e(TAG, "uploadImage: error handled --> "+e.getMessage());
            Toast.makeText(getApplicationContext(),"Error Uploadng image!!!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_IMAGE_REQUEST) {

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String filePath = cursor.getString(columnIndex);

               // setImage(filePath);

                cursor.close();
            }
        }
    }
/*
    private void setImage(String imagePath){
        this.selectImage.setVisibility(View.VISIBLE);
        this.selectedImagePath = imagePath;
        Picasso.with(this).load(new File(imagePath)).into(this.selectedImage);
        this.uploadImage.setVisibility(View.VISIBLE);
    }*/

}
