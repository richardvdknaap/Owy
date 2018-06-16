package com.example.richard.owy;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class debtInfoPageActivity extends AppCompatActivity {
    String[] naamSchuld;
    String[] schuldBedrag;
    String[] usernameSchuld;
    String[] datumSchuld;
    String[] ibanSchuld;
    String[] beschrijvingSchuld;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debt_info_page);
        Resources res = getResources();
        Intent mIntent = getIntent();
        int position = mIntent.getIntExtra("varName",1);
        naamSchuld = res.getStringArray(R.array.naamSchuld);
        schuldBedrag = res.getStringArray(R.array.schuldBedrag);
        usernameSchuld = res.getStringArray(R.array.usernameSchuld);
        datumSchuld = res.getStringArray(R.array.datumSchuld);
        ibanSchuld = res.getStringArray(R.array.ibanSchuld);
        beschrijvingSchuld = res.getStringArray(R.array.beschrijvingSchuld);

        TextView tv = (TextView)findViewById(R.id.bedragDebtTextView);
        TextView tv2 = (TextView)findViewById(R.id.showNaamTextView);
        TextView tv3 = (TextView)findViewById(R.id.debtUserTextView);
        TextView tv4 = (TextView)findViewById(R.id.DatumDebtTextView);
        TextView tv5 = (TextView)findViewById(R.id.ibandebtTextView);
        TextView tv6 = (TextView)findViewById(R.id.beschrijvingTextView);

        tv.setText(schuldBedrag[position]);
        tv2.setText(naamSchuld[position]);
        tv3.setText(usernameSchuld[position]);
        tv4.setText(datumSchuld[position]);
        tv5.setText(ibanSchuld[position]);
        tv6.setText(beschrijvingSchuld[position]);

        tv6.setMovementMethod(new ScrollingMovementMethod());

    }
}
