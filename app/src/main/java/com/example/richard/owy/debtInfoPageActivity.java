package com.example.richard.owy;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class debtInfoPageActivity extends AppCompatActivity {
    String[] naamSchuld;
    String[] schuldBedrag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debt_info_page);
        Resources res = getResources();
        Intent mIntent = getIntent();
        int position = mIntent.getIntExtra("varName",1);
        naamSchuld = res.getStringArray(R.array.naamSchuld);
        schuldBedrag = res.getStringArray(R.array.schuldBedrag);
        TextView tv = (TextView)findViewById(R.id.bedragDebtTextView);
        TextView tv2 = (TextView)findViewById(R.id.showNaamTextView);

        tv2.setText(naamSchuld[position]);
        tv.setText(schuldBedrag[position]);

    }
}
