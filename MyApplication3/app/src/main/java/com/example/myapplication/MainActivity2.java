package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {
    private Button btnLoad;
    private ImageView imgAnd;
    private TextView tvMess;
    private ProgressDialog progressDialog;
    private String url = "https://static-images.vnncdn.net/files/publish/2022/9/3/bien-vo-cuc-thai-binh-346.jpeg";
    private Bitmap bitmap= null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnLoad = (Button) findViewById(R.id.button);
        imgAnd = (ImageView) findViewById(R.id.imageView);
        tvMess = (TextView) findViewById(R.id.textView);
        btnLoad.setOnClickListener(this);
    }
    private Handler messageHandler = new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String message = bundle.getString("message");
            tvMess.setText(message);
            imgAnd.setImageBitmap(bitmap);
            progressDialog.dismiss();
        }
    };

    @Override
    public void onClick(View view) {
        progressDialog = ProgressDialog.show(MainActivity2.this, "","Downloading...");
        Runnable aRunnable = new Runnable() {
            @Override
            public void run() {
                bitmap = downloadBitmap(url);
                Message msg = messageHandler.obtainMessage();
                Bundle bundle = new Bundle();
                String threadMessage = "Image downloaded";
                bundle.putString("message", threadMessage);
                msg.setData(bundle);
                messageHandler.sendMessage(msg);
            }
        };
        Thread athread = new Thread(aRunnable);
        athread.start();
    }
    private Bitmap downloadBitmap(String link) {
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}