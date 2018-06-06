package com.example.richard.owy;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    ListView schuldListView;
    ListView ontvangenListView;
    String[] naamSchuld;
    String[] schuldBedrag;
    String[] naamOntvang;
    String[] ontvangBedrag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Resources res = getResources();

        schuldListView = (ListView) findViewById(R.id.schuldListView);
        naamSchuld = res.getStringArray(R.array.naamSchuld);
        schuldBedrag = res.getStringArray(R.array.schuldBedrag);
        itemAdapter itemAdapter = new itemAdapter(this, naamSchuld, schuldBedrag);
        schuldListView.setAdapter(itemAdapter);

        schuldListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent (HomeActivity.this, debtInfoPageActivity.class);
                startActivity(intent);
            }
        });






        ontvangenListView = (ListView) findViewById(R.id.ontvangenListView);
        naamOntvang = res.getStringArray(R.array.naamOntvang);
        ontvangBedrag = res.getStringArray(R.array.ontvangBedrag);
        receiveAdapter receiveAdapter = new receiveAdapter(this, naamOntvang, ontvangBedrag);
        ontvangenListView.setAdapter(receiveAdapter);

        //Round Floating button on HomeActivity. Links to receiveOrDebt
        FloatingActionButton faButton = findViewById(R.id.faButton);
        faButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), receiveOrDebtActivity.class);
                startActivity(startIntent);
            }
        });

        //te betalen knop
        Button schuldButton = (Button) findViewById(R.id.schuldButton);
        schuldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent (getApplicationContext(), DeptActivity.class);
                startActivity(startIntent);
            }
        });

        //te ontvangen knop
        Button ontvangButton = (Button) findViewById(R.id.ontvangButton);
        ontvangButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent (getApplicationContext(), ReceiveActivity.class);
                startActivity(startIntent);
            }
        });


    }
}
