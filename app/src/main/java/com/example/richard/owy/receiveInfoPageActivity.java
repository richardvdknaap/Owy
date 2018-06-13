package com.example.richard.owy;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class receiveInfoPageActivity extends AppCompatActivity {
    String[] naamOntvang;
    String[] ontvangBedrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_info_page);
        Resources res = getResources();
        Intent mIntent = getIntent();
        int position = mIntent.getIntExtra("varName",1);
        naamOntvang = res.getStringArray(R.array.naamOntvang);
        ontvangBedrag = res.getStringArray(R.array.ontvangBedrag);
        TextView tv = (TextView)findViewById(R.id.recieveTextView);
        TextView tv2 = (TextView)findViewById(R.id.showNaamTextView2);

        tv2.setText(naamOntvang[position]);
        tv.setText(ontvangBedrag[position]);
    }
}
