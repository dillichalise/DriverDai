package com.example.dillichalise.driverdai;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class OperatorViewSeatsActivity extends AppCompatActivity implements OnSeatSelected {

    private static final int COLUMNS = 5;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Bus");
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    List<Seat> seats = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_seat_layout);
        final List<AbstractItem> items = new ArrayList<>();
//        for (int i = 0; i < seats.size(); i++) {
//            items.add(getItem(i));
//
//        }
        GridLayoutManager manager = new GridLayoutManager(this, COLUMNS);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lst_items);
        recyclerView.setLayoutManager(manager);

        final OperatorAirplaneAdapter adapter = new OperatorAirplaneAdapter(this, items);
        recyclerView.setAdapter(adapter);

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Seat seat = dataSnapshot.getValue(Seat.class);
                seat.key = dataSnapshot.getKey();
                seats.add(seat);
                items.add(getItem(seat.id, seat));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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

        Button txtSeatSelected = (Button) findViewById(R.id.txt_seat_selected);
        Button txtSeatCancelled = (Button) findViewById(R.id.txt_seat_cancel);
        txtSeatSelected.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                for (int i = 0; i < adapter.getSelectedItems().size(); i++) {
                    reference.child(seats.get(adapter.getSelectedItems().get(i)).key).child("is_booked").setValue(true);
                }
                finish();
            }
        });

        txtSeatCancelled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < adapter.getSelectedItems().size(); i++) {
                    reference.child(seats.get(adapter.getSelectedItems().get(i)).key).child("is_booked").setValue(false);
                    reference.child(seats.get(adapter.getSelectedItems().get(i)).key).child("booked_by").setValue(null);
                    reference.child(seats.get(adapter.getSelectedItems().get(i)).key).child("location").setValue(null);
                }
                finish();

            }
        });

    }

    private AbstractItem getItem(int i, Seat seat) {
        if (i % COLUMNS == 0 || i % COLUMNS == 4) {
            return new EdgeItem(String.valueOf(i), seat.is_booked, seat.id, seat.booked_by, seat.location);
        } else if (i % COLUMNS == 1 || i % COLUMNS == 3) {
            return new CenterItem(String.valueOf(i), seat.is_booked, seat.id, seat.booked_by, seat.location);
        } else {
            return new EmptyItem(String.valueOf(i), seat.is_booked, seat.id, seat.booked_by, seat.location);
        }
    }

    @Override
    public void onSeatSelected(List<Integer> selectedItems, int count) {

    }
}

