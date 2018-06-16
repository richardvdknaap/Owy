package com.example.richard.owy;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class receiveInfoPageActivity extends AppCompatActivity {
    String[] naamOntvang;
    String[] ontvangBedrag;
    String[] usernameOntvang;
    String[] datumOntvang;
    String[] ibanOntvang;
    String[] beschrijvingOntvang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_info_page);

        Resources res = getResources();
        Intent mIntent = getIntent();
        int position = mIntent.getIntExtra("varName",1);
        naamOntvang = res.getStringArray(R.array.naamOntvang);
        ontvangBedrag = res.getStringArray(R.array.ontvangBedrag);
        usernameOntvang = res.getStringArray(R.array.usernameOntvang);
        datumOntvang = res.getStringArray(R.array.datumOntvang);
        ibanOntvang = res.getStringArray(R.array.ibanOntvang);
        beschrijvingOntvang = res.getStringArray(R.array.beschrijvingOntvang);

        TextView tv = (TextView)findViewById(R.id.recieveTextView);
        TextView tv2 = (TextView)findViewById(R.id.showNaamTextView2);
        TextView tv3 = (TextView)findViewById(R.id.userName);
        TextView tv4 = (TextView)findViewById(R.id.datum);
        TextView tv5 = (TextView)findViewById(R.id.textView6);
        TextView tv6 = (TextView)findViewById(R.id.descReceiveTV);

        tv.setText(ontvangBedrag[position]);
        tv2.setText(naamOntvang[position]);
        tv3.setText(usernameOntvang[position]);
        tv4.setText(datumOntvang[position]);
        tv5.setText(ibanOntvang[position]);
        tv6.setText(beschrijvingOntvang[position]);

        tv6.setMovementMethod(new ScrollingMovementMethod());


    }
}
