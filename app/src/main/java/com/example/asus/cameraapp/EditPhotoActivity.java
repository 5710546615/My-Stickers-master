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
import android.widget.ImageView;

import com.example.asus.cameraapp.R;

public class EditPhotoActivity extends AppCompatActivity{

    private ImageView cropText;
    private ImageView testImage;
    private ImageButton squareButton;
    private ImageButton rotateButton;
    private ImageButton rectangleButton;
    private ImageButton doneButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editphoto);
        initComponents();
    }

    private void initComponents() {
        cropText = (ImageView)findViewById(R.id.txt_crop);
        cropText.setImageResource(R.drawable.txt_crop);

        testImage = (ImageView)findViewById(R.id.img_test);
        testImage.setImageResource(R.drawable.monkey);

        rotateButton = (ImageButton) findViewById(R.id.btn_rotate);
        rotateButton.setOnClickListener(new View.OnClickListener() {
            Bitmap myImg = BitmapFactory.decodeResource(getResources(), R.drawable.monkey);
            Matrix matrix = new Matrix();
            @Override
            public void onClick(View view) {
                matrix.postRotate(270);
                Bitmap rotated = Bitmap.createBitmap(myImg, 0, 0, myImg.getWidth(), myImg.getHeight(),
                        matrix, true);
                testImage.setImageBitmap(rotated);
            }
        });

        squareButton = (ImageButton) findViewById(R.id.btn_square);
        squareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rectangleButton = (ImageButton) findViewById(R.id.btn_rectangle);
        rectangleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        doneButton = (ImageButton) findViewById(R.id.btn_done);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditPhotoActivity.this, AddStickerActivity.class);
                startActivity(intent);
            }
        });
    }
}
