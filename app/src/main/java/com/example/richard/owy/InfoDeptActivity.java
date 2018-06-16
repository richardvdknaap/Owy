package com.example.richard.owy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
public class InfoDeptActivity extends AppCompatActivity {
    String UID;
    EditText bedragText;
    EditText usernameText;
    EditText naamText;
    EditText IBANText;
    EditText datumText;
    EditText beschrijvingText;
    String bedrag;
    String username;
    String naam;
    String IBAN;
    String datum;
    String beschrijving;
    private FirebaseDatabase mDatabase;
    private DatabaseReference userDatabase;

    private void addItem(DatabaseReference userDatabase){
        bedragText = (EditText) findViewById(R.id.schuldBedragNr);
        usernameText = (EditText) findViewById(R.id.usernameEditText);
        naamText = (EditText) findViewById(R.id.nameEditText);
        IBANText = (EditText) findViewById(R.id.IBANEditText);
        datumText = (EditText) findViewById(R.id.datumEditText);
        beschrijvingText = (EditText) findViewById(R.id.descEditText);
        bedrag = bedragText.getText().toString();
        username = usernameText.getText().toString();
        naam = naamText.getText().toString();
        IBAN = IBANText.getText().toString();
        datum = datumText.getText().toString();
        beschrijving = beschrijvingText.getText().toString();
        String key = userDatabase.push().getKey();
        userDatabase.child(key).child("type").setValue("schuld");
        userDatabase.child(key).child("bedrag").setValue(bedrag);
        userDatabase.child(key).child("username").setValue(username);
        userDatabase.child(key).child("naam").setValue(naam);
        userDatabase.child(key).child("IBAN").setValue(IBAN);
        userDatabase.child(key).child("datum").setValue(datum);
        userDatabase.child(key).child("beschrijving").setValue(beschrijving);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_dept);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        if (user != null) {
            UID = user.getUid();
        }
        userDatabase = mDatabase.getReference(UID);

        //savebutton
        Button saveButton = (Button) findViewById(R.id.saveBtn);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem(userDatabase);
                Intent startIntent = new Intent (getApplicationContext(), HomeActivity.class);
                startActivity(startIntent);
            }
        });

    }

}
