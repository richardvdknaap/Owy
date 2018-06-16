package com.example.richard.owy;

import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class receiveInfoPageActivity extends AppCompatActivity {
    String[] naamOntvang;
    String[] ontvangBedrag;
    String[] usernameOntvang;
    String[] datumOntvang;
    String[] ibanOntvang;
    String[] beschrijvingOntvang;
    String UID;
    ArrayList<String> namesOntvang = new ArrayList<String>();
    ArrayList<String> bedragenOntvang = new ArrayList<String>();
    ArrayList<String> usernamesOntvang = new ArrayList<String>();
    ArrayList<String> datumsOntvang = new ArrayList<String>();
    ArrayList<String> ibansOntvang = new ArrayList<String>();
    ArrayList<String> beschrijvingsOntvang = new ArrayList<String>();
    ArrayList<String> type = new ArrayList<String>();
    private FirebaseDatabase mDatabase;
    private DatabaseReference userDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_info_page);
        final TextView tv = (TextView)findViewById(R.id.recieveTextView);
        final TextView tv2 = (TextView)findViewById(R.id.showNaamTextView2);
        final TextView tv3 = (TextView)findViewById(R.id.userName);
        final TextView tv4 = (TextView)findViewById(R.id.datum);
        final TextView tv5 = (TextView)findViewById(R.id.textView6);
        final TextView tv6 = (TextView)findViewById(R.id.descReceiveTV);

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


                Log.e("Count", ""+dataSnapshot.getChildrenCount());
                for ( DataSnapshot dsp : dataSnapshot.getChildren()) {

                    if(String.valueOf(dsp.child("type").getValue()).equals("ontvangst")) {
                        namesOntvang.add(String.valueOf(dsp.child("naam").getValue()));
                        bedragenOntvang.add(String.valueOf(dsp.child("bedrag").getValue()));
                        usernamesOntvang.add(String.valueOf(dsp.child("username").getValue()));
                        datumsOntvang.add(String.valueOf(dsp.child("datum").getValue()));
                        ibansOntvang.add(String.valueOf(dsp.child("IBAN").getValue()));
                        beschrijvingsOntvang.add(String.valueOf(dsp.child("beschrijving").getValue()));

                    }
                }

                naamOntvang = namesOntvang.toArray(new String[0]);
                ontvangBedrag = bedragenOntvang.toArray(new String[0]);
                usernameOntvang = usernamesOntvang.toArray(new String[0]);
                datumOntvang = datumsOntvang.toArray(new String[0]);
                ibanOntvang = ibansOntvang.toArray(new String[0]);
                beschrijvingOntvang = beschrijvingsOntvang.toArray(new String[0]);
                tv.setText(ontvangBedrag[position]);
                tv2.setText(naamOntvang[position]);
                tv3.setText(usernameOntvang[position]);
                tv4.setText(datumOntvang[position]);
                tv5.setText(ibanOntvang[position]);
                tv6.setText(beschrijvingOntvang[position]);
                tv6.setMovementMethod(new ScrollingMovementMethod());
                Button receiveDeleteBtn = (Button) findViewById(R.id.receiveDeleteBtn);
                receiveDeleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        userDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                            {
                                long i = -1;
                                for (DataSnapshot dsp : dataSnapshot.getChildren())
                                {
                                    if(String.valueOf(dsp.child("type").getValue()).equals("ontvangst")) {
                                        i = i + 1;
                                        if(i == position)
                                            dsp.getRef().removeValue();
                                    }

                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        Intent startIntent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(startIntent);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("The read failed: " ,databaseError.getMessage());

            }
        });








    }
}
