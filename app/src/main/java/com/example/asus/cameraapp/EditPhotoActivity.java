package com.example.asus.cameraapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.graphics.Matrix;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.InputStream;

public class EditPhotoActivity extends AppCompatActivity {

    private ImageView cropText;
    private ImageView image;
    private ImageButton rotateButton;
    private ImageButton cropButton;
    private ImageButton backButton;
    private ImageButton doneButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editphoto);

        initComponents();
    }

    private void initComponents() {

        cropText = (ImageView) findViewById(R.id.txt_crop);
        cropText.setImageResource(R.drawable.txt_crop);

        Intent intent = getIntent();
        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");

        image = (ImageView) findViewById(R.id.img_image);
        image.setImageBitmap(bitmap);

        rotateButton = (ImageButton) findViewById(R.id.btn_rotate);
        rotateButton.setOnClickListener(new View.OnClickListener() {

            Intent intent = getIntent();
            Bitmap bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");
            Matrix matrix = new Matrix();

            public void onClick(View view) {
                matrix.postRotate(-90);
                Bitmap rotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                image.setImageBitmap(rotated);
            }
        });

        cropButton = (ImageButton) findViewById(R.id.btn_crop);
        cropButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent imageDownload = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                imageDownload.putExtra("crop", "true");
                imageDownload.putExtra("aspectX", 1);
                imageDownload.putExtra("aspectY", 1);
                imageDownload.putExtra("outputX", 200);
                imageDownload.putExtra("outputY", 200);
                imageDownload.putExtra("return-data", true);
                startActivityForResult(imageDownload, 2);
            }
        });

        backButton = (ImageButton) findViewById(R.id.btn_crop_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = getIntent();
                Bitmap bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");
                Matrix matrix = new Matrix();
                Bitmap rotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),
                        matrix, true);
                image.setImageBitmap(rotated);
            }
        });

        doneButton = (ImageButton) findViewById(R.id.btn_done);
        doneButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(EditPhotoActivity.this, AddStickerActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2 && resultCode == RESULT_OK && data != null)
        {
            Bundle extras = data.getExtras();
            Bitmap images = extras.getParcelable("data");
            image.setImageBitmap(images);
        }
    }
}
