package com.example.richard.owy;

import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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


public class DeptActivity extends AppCompatActivity {

    ListView debtListView;
    String[] naamSchuld;
    String[] schuldBedrag;
    String UID;
    ArrayList<String> namesSchuld = new ArrayList<String>();
    ArrayList<String> bedragenSchuld = new ArrayList<String>();
    ArrayList<String> type = new ArrayList<String>();
    private FirebaseDatabase mDatabase;
    private DatabaseReference userDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        debtListView = (ListView) findViewById(R.id.debtListView);
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

                    if(String.valueOf(dsp.child("type").getValue()).equals("schuld")) {
                        namesSchuld.add(String.valueOf(dsp.child("naam").getValue()));
                        bedragenSchuld.add(String.valueOf(dsp.child("bedrag").getValue()));
                    }
                }

                naamSchuld = namesSchuld.toArray(new String[0]);
                schuldBedrag = bedragenSchuld.toArray(new String[0]);
                receiveAdapter receiveAdapter = new receiveAdapter(DeptActivity.this, naamSchuld, schuldBedrag);
                debtListView.setAdapter(receiveAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("The read failed: " ,databaseError.getMessage());

            }
        });

        debtListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent (DeptActivity.this, debtInfoPageActivity.class);
                intent.putExtra("varName",position);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onRestart() {
        super.onRestart();
        this.recreate();
    }
}
