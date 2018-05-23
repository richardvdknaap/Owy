package com.example.richard.owy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

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
        init();

    }
}
