package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener, LoadImageTask.Listener {
    private Button btnLoad;
    private ImageView imgAnd;
    private TextView tvMess;
    public static final String Image_URL =  "https://static-images.vnncdn.net/files/publish/2022/9/3/bien-vo-cuc-thai-binh-346.jpeg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        btnLoad = (Button) findViewById(R.id.button);
        imgAnd = (ImageView) findViewById(R.id.imageView);
        tvMess = (TextView) findViewById(R.id.textView);
        btnLoad.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        new LoadImageTask(this, this).execute(Image_URL);

    }

    @Override
    public void onImageLoaded(Bitmap bitmap) {
        imgAnd.setImageBitmap(bitmap);
        tvMess.setText("Image Downloaded");
    }

    @Override
    public void onError() {
        tvMess.setText("Error download image");

    }
}