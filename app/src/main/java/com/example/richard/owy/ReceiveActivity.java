package com.example.richard.owy;

import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReceiveActivity extends AppCompatActivity {

    ListView receiveListView;
    String UID;
    String[] naamOntvang;
    String[] ontvangBedrag;
    ArrayList<String> namesOntvang = new ArrayList<String>();
    ArrayList<String> bedragenOntvang = new ArrayList<String>();
    ArrayList<String> type = new ArrayList<String>();
    private FirebaseDatabase mDatabase;
    private DatabaseReference userDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        receiveListView = (ListView) findViewById(R.id.receiveListView);
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
                    }
                }

                naamOntvang = namesOntvang.toArray(new String[0]);
                ontvangBedrag = bedragenOntvang.toArray(new String[0]);
                receiveAdapter receiveAdapter = new receiveAdapter(ReceiveActivity.this, naamOntvang, ontvangBedrag);
                receiveListView.setAdapter(receiveAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("The read failed: " ,databaseError.getMessage());

            }
        });


        receiveListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent (ReceiveActivity.this, receiveInfoPageActivity.class);
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
