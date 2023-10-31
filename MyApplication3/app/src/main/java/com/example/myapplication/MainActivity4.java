package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity4 extends Activity implements View.OnClickListener {
 private EditText editTime;
 private Button btnRun;
 private TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        editTime = (EditText) findViewById(R.id.edText);
        btnRun = (Button) findViewById(R.id.button2);
        tvResult = (TextView) findViewById(R.id.textView3);
        btnRun.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner( tvResult, editTime, this);
        String sleepTime = editTime.getText().toString();
        asyncTaskRunner.execute(sleepTime);
    }
}