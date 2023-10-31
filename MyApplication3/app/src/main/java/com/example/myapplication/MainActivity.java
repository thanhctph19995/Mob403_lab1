package com.example.myapplication;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLoad;
    private ImageView imgAnd;
    private TextView tvMess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLoad = (Button) findViewById(R.id.button);
        imgAnd = (ImageView) findViewById(R.id.imageView);
        tvMess = (TextView) findViewById(R.id.textView);
        btnLoad.setOnClickListener(this);

    }



    private Bitmap loadImageFromNetWork(String link) {
        URL url;
        Bitmap bmp = null;
        try {
            url = new URL(link);
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmp;
    }

    @Override
    public void onClick(View v) {
        final Thread myThear = new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = loadImageFromNetWork("https://static-images.vnncdn.net/files/publish/2022/9/3/bien-vo-cuc-thai-binh-346.jpeg");
                imgAnd.post(new Runnable() {
                    @Override
                    public void run() {
                        tvMess.setText("Img DowLoaded");
                        imgAnd.setImageBitmap(bitmap);
                    }
                });
            }
        });
        myThear.start();
    }
}