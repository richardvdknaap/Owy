package com.example.richard.owy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InfoReceiveActivity extends AppCompatActivity {
    String UID;
    EditText bedragText;
    EditText usernameText;
    EditText naamText;
    EditText IBANText;
    EditText datumText;
    EditText beschrijvingText;
    double bedrag;
    String username;
    String naam;
    String IBAN;
    String datum;
    String beschrijving;
    private FirebaseDatabase mDatabase;
    private DatabaseReference userDatabase;
    private void addItem(DatabaseReference userDatabase){
        bedragText = (EditText) findViewById(R.id.schuldBedragNr2);
        usernameText = (EditText) findViewById(R.id.usernameEditText2);
        naamText = (EditText) findViewById(R.id.nameEditText2);
        IBANText = (EditText) findViewById(R.id.IBANEditText2);
        datumText = (EditText) findViewById(R.id.datumEditText2);
        beschrijvingText = (EditText) findViewById(R.id.descEditText2);
        bedrag = Double.parseDouble(bedragText.getText().toString());
        username = usernameText.getText().toString();
        naam = naamText.getText().toString();
        IBAN = IBANText.getText().toString();
        datum = datumText.getText().toString();
        beschrijving = beschrijvingText.getText().toString();
        String key = userDatabase.push().getKey();
        userDatabase.child(key).child("type").setValue("ontvangst");
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
        setContentView(R.layout.activity_info_receive);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance();
        if (user != null) {
            UID = user.getUid();
        }
        userDatabase = mDatabase.getReference(UID);

        //savebutton
        Button saveButton = (Button) findViewById(R.id.saveBtn2);
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
