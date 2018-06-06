package com.example.richard.owy;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class debtInfoPageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debt_info_page);
        Intent mIntent = getIntent();
        int position = mIntent.getIntExtra("varName",1);
        TextView tv = (TextView)findViewById(R.id.bedragDebtTextView);
        TextView tv2 = (TextView)findViewById(R.id.showNaamTextView);
        tv.setText(Integer.toString(position));
        tv2.setText(Integer.toString(position));



    }
}
