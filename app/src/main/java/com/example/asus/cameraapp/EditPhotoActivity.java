package com.example.asus.cameraapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.graphics.Matrix;

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

        image = (ImageView) findViewById(R.id.img_image);
        image.setImageResource(R.drawable.monkey);

        rotateButton = (ImageButton) findViewById(R.id.btn_rotate);
        rotateButton.setOnClickListener(new View.OnClickListener() {
            Bitmap myImg = BitmapFactory.decodeResource(getResources(), R.drawable.monkey);
            Matrix matrix = new Matrix();

            public void onClick(View view) {
                matrix.postRotate(270);
                Bitmap rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.getWidth(), myImg.getHeight(),
                        matrix, true);
                image.setImageBitmap(rotated);
            }
        });

        cropButton = (ImageButton) findViewById(R.id.btn_crop);
        cropButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });

        backButton = (ImageButton) findViewById(R.id.btn_crop_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Bitmap myImg = BitmapFactory.decodeResource(getResources(), R.drawable.monkey);
                Matrix matrix = new Matrix();
                Bitmap rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.getWidth(), myImg.getHeight(),
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
}