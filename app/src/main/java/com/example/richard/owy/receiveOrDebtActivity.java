package com.example.richard.owy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class receiveOrDebtActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_or_debt);

        Button schuldKnop = (Button) findViewById(R.id.schuldKnop);
        schuldKnop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), InfoDeptActivity.class);
                startActivity(startIntent);
            }
        });

        Button ontvangKnop = (Button) findViewById(R.id.ontvangKnop);
        ontvangKnop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent StartIntent = new Intent(getApplicationContext(), InfoReceiveActivity.class);
                startActivity(StartIntent);
            }
        });

    }
}
