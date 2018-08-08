package com.example.dillichalise.driverdai;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class FirebaseHelper {

    private DatabaseReference db;
    private ArrayList<ListSeats> listSeats = new ArrayList<>();

    public FirebaseHelper(DatabaseReference db) {
        this.db = db;
    }


    private void fetchData(DataSnapshot dataSnapshot) {
        listSeats.clear();

        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            ListSeats listSeats = ds.getValue(ListSeats.class);
            listSeats.add(listSeats);
        }


    }

    public ArrayList<ListSeats> retrieve() {
        Query myBookingsQuery = FirebaseDatabase.getInstance().getReference().child("bookings")
                .equalTo("booked_by", FirebaseAuth.getInstance().getCurrentUser().getUid());
        myBookingsQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return listSeats;
    }
}
