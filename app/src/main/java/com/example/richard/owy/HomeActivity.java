package com.example.richard.owy;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import java.lang.Object;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    String UID;
    ListView schuldListView;
    ListView ontvangenListView;
    String[] naamSchuld;
    String[] schuldBedrag;
    String[] naamOntvang;
    String[] ontvangBedrag;
    ArrayList<String> namesSchuld = new ArrayList<String>();
    ArrayList<String> bedragenSchuld = new ArrayList<String>();
    ArrayList<String> namesOntvang = new ArrayList<String>();
    ArrayList<String> bedragenOntvang = new ArrayList<String>();
    ArrayList<String> type = new ArrayList<String>();
    private FirebaseDatabase mDatabase;
    private DatabaseReference userDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        schuldListView = (ListView) findViewById(R.id.schuldListView);
        ontvangenListView = (ListView) findViewById(R.id.ontvangenListView);
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
                    if(String.valueOf(dsp.child("type").getValue()).equals("ontvangst")) {
                        namesOntvang.add(String.valueOf(dsp.child("naam").getValue()));
                        bedragenOntvang.add(String.valueOf(dsp.child("bedrag").getValue()));
                    }
                }

                naamSchuld = namesSchuld.toArray(new String[0]);
                schuldBedrag = bedragenSchuld.toArray(new String[0]);
                itemAdapter itemAdapter = new itemAdapter(HomeActivity.this, naamSchuld, schuldBedrag);
                schuldListView.setAdapter(itemAdapter);

                naamOntvang = namesOntvang.toArray(new String[0]);
                ontvangBedrag = bedragenOntvang.toArray(new String[0]);
                receiveAdapter receiveAdapter = new receiveAdapter(HomeActivity.this, naamOntvang, ontvangBedrag);
                ontvangenListView.setAdapter(receiveAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("The read failed: " ,databaseError.getMessage());

            }
        });



        schuldListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HomeActivity.this, debtInfoPageActivity.class);
                intent.putExtra("varName", position);
                startActivity(intent);
            }
        });



        ontvangenListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HomeActivity.this, receiveInfoPageActivity.class);
                intent.putExtra("varName", position);
                startActivity(intent);
            }
        });

        //Round Floating button on HomeActivity. Links to receiveOrDebt
        FloatingActionButton faButton = findViewById(R.id.faButton);
        faButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), receiveOrDebtActivity.class);
                startActivity(startIntent);
            }
        });

        //te betalen knop
        Button schuldButton = (Button) findViewById(R.id.schuldButton);
        schuldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), DeptActivity.class);
                startActivity(startIntent);
            }
        });

        //te ontvangen knop
        Button ontvangButton = (Button) findViewById(R.id.ontvangButton);
        ontvangButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ReceiveActivity.class);
                startActivity(startIntent);
            }
        });




    }
    @Override
    protected void onRestart() {
        super.onRestart();
        this.recreate();
    }

}
