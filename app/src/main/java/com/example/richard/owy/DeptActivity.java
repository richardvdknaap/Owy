package com.example.richard.owy;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


public class DeptActivity extends AppCompatActivity {

    ListView debtListView;
    String[] naamSchuld;
    String[] schuldBedrag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept);

        Resources res = getResources();

        debtListView = (ListView) findViewById(R.id.debtListView);
        naamSchuld = res.getStringArray(R.array.naamSchuld);
        schuldBedrag = res.getStringArray(R.array.schuldBedrag);
        debtPageAdapter debtPageAdapter = new debtPageAdapter(this, naamSchuld, schuldBedrag);
        debtListView.setAdapter(debtPageAdapter);

        debtListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent (DeptActivity.this, debtInfoPageActivity.class);
                intent.putExtra("varName",position);
                startActivity(intent);
            }
        });

    }
}
