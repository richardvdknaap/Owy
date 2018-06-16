package com.example.richard.owy;

import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class debtInfoPageActivity extends AppCompatActivity {
    String[] naamSchuld;
    String[] schuldBedrag;
    String[] usernameSchuld;
    String[] datumSchuld;
    String[] ibanSchuld;
    String[] beschrijvingSchuld;
    String UID;
    ArrayList<String> namesSchuld = new ArrayList<String>();
    ArrayList<String> bedragenSchuld = new ArrayList<String>();
    ArrayList<String> usernamesSchuld = new ArrayList<String>();
    ArrayList<String> datumsSchuld = new ArrayList<String>();
    ArrayList<String> ibansSchuld = new ArrayList<String>();
    ArrayList<String> beschrijvingsSchuld = new ArrayList<String>();
    ArrayList<String> type = new ArrayList<String>();
    private FirebaseDatabase mDatabase;
    private DatabaseReference userDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debt_info_page);
        final TextView tv = (TextView)findViewById(R.id.bedragDebtTextView);
        final TextView tv2 = (TextView)findViewById(R.id.showNaamTextView);
        final TextView tv3 = (TextView)findViewById(R.id.debtUserTextView);
        final TextView tv4 = (TextView)findViewById(R.id.DatumDebtTextView);
        final TextView tv5 = (TextView)findViewById(R.id.ibandebtTextView);
        final TextView tv6 = (TextView)findViewById(R.id.beschrijvingTextView);
        Intent mIntent = getIntent();
        final int position = mIntent.getIntExtra("varName",1);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        if (user != null) {
            UID = user.getUid();
        }
        userDatabase = mDatabase.getReference(UID);
        userDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                Log.e("Count", "" + dataSnapshot.getChildrenCount());
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {

                    if (String.valueOf(dsp.child("type").getValue()).equals("schuld")) {
                        namesSchuld.add(String.valueOf(dsp.child("naam").getValue()));
                        bedragenSchuld.add(String.valueOf(dsp.child("bedrag").getValue()));
                        usernamesSchuld.add(String.valueOf(dsp.child("username").getValue()));
                        datumsSchuld.add(String.valueOf(dsp.child("datum").getValue()));
                        ibansSchuld.add(String.valueOf(dsp.child("IBAN").getValue()));
                        beschrijvingsSchuld.add(String.valueOf(dsp.child("beschrijving").getValue()));

                    }
                }

                naamSchuld = namesSchuld.toArray(new String[0]);
                schuldBedrag = bedragenSchuld.toArray(new String[0]);
                usernameSchuld = usernamesSchuld.toArray(new String[0]);
                datumSchuld = datumsSchuld.toArray(new String[0]);
                ibanSchuld = ibansSchuld.toArray(new String[0]);
                beschrijvingSchuld = beschrijvingsSchuld.toArray(new String[0]);
                tv.setText(schuldBedrag[position]);
                tv2.setText(naamSchuld[position]);
                tv3.setText(usernameSchuld[position]);
                tv4.setText(datumSchuld[position]);
                tv5.setText(ibanSchuld[position]);
                tv6.setText(beschrijvingSchuld[position]);
                tv6.setMovementMethod(new ScrollingMovementMethod());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("The read failed: " ,databaseError.getMessage());

            }
        });

    }
}
