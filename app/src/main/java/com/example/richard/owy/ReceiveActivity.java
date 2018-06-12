package com.example.richard.owy;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class ReceiveActivity extends AppCompatActivity {

    ListView receiveListView;
    String[] naamOntvang;
    String[] ontvangBedrag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        Resources res = getResources();

        receiveListView = (ListView) findViewById(R.id.receiveListView);
        naamOntvang = res.getStringArray(R.array.naamOntvang);
        ontvangBedrag = res.getStringArray(R.array.ontvangBedrag);
        receivePageAdapter receivePageAdapter = new receivePageAdapter(this, naamOntvang, ontvangBedrag);
        receiveListView.setAdapter(receivePageAdapter);

        receiveListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent (ReceiveActivity.this, receiveInfoPageActivity.class);
                intent.putExtra("varName",position);
                startActivity(intent);
            }
        });


    }
}
