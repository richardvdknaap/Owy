package com.example.richard.owy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ReceiveActivity extends AppCompatActivity {

    private static final String TAG = "ReceiveActivity";

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class ReceiveActivity extends AppCompatActivity {

    ListView receiveListView;
    String[] naamOntvang;
    String[] ontvangBedrag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        Log.d(TAG,"onCreate: Started.");

        Resources res = getResources();

        receiveListView = (ListView) findViewById(R.id.receiveListView);
        naamOntvang = res.getStringArray(R.array.naamOntvang);
        ontvangBedrag = res.getStringArray(R.array.ontvangBedrag);
        receivePageAdapter receivePageAdapter = new receivePageAdapter(this, naamOntvang, ontvangBedrag);
        receiveListView.setAdapter(receivePageAdapter);


    }
}
