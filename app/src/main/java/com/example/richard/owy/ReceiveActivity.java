package com.example.richard.owy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ReceiveActivity extends AppCompatActivity {

    private static final String TAG = "ReceiveActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        Log.d(TAG,"onCreate: Started.");
    }
}
