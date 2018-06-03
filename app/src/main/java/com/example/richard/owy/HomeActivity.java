package com.example.richard.owy;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class HomeActivity extends AppCompatActivity {

    ListView schuldListView;
    ListView ontvangenListView;
    String[] naamSchuld;
    String[] schuldBedrag;
    String[] naamOntvang;
    String[] ontvangBedrag;


    public Button but1;
    public Button but2;
    public Button but3;
    public Button but4;

    public  void init(){
        but2 = (Button)findViewById(R.id.button_dept);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(HomeActivity.this,DeptActivity.class);
                startActivity(toy);
            }
        });
        but1 = (Button)findViewById(R.id.button_receive);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(HomeActivity.this,ReceiveActivity.class);
                startActivity(toy);
            }
        });
        but3 = (Button)findViewById(R.id.button_info_receive);
        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(HomeActivity.this,InfoReceiveActivity.class);
                startActivity(toy);
            }
        });
        but4 = (Button)findViewById(R.id.button_info_dept);
        but4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(HomeActivity.this,InfoDeptActivity.class);
                startActivity(toy);
            }
        });

    }

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

        ontvangenListView = (ListView) findViewById(R.id.ontvangenListView);
        naamOntvang = res.getStringArray(R.array.naamOntvang);
        ontvangBedrag = res.getStringArray(R.array.ontvangBedrag);
        receiveAdapter receiveAdapter = new receiveAdapter(this, naamOntvang, ontvangBedrag);
        ontvangenListView.setAdapter(receiveAdapter);



        init();



    }
}
