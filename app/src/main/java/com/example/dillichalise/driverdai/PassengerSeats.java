package com.example.dillichalise.driverdai;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PassengerSeats extends AppCompatActivity {

    DatabaseReference db;
    FirebaseHelper helper;
    CustomAdapter adapter;
    ListView lv;
    EditText busNoTxt, seatNoTxt, fromTxt, destinationTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_seats);

        lv = (ListView) findViewById(R.id.list_seats);

        db = FirebaseDatabase.getInstance().getReference();
        helper = new FirebaseHelper(db);

        adapter = new CustomAdapter(this, helper.retrieve());
        lv.setAdapter(adapter);



    }
}
