package com.example.dillichalise.driverdai;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements OnSeatSelected {

    private static final int COLUMNS = 5;
    Query reference;
    TextView txtSeatSelected;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference userReference;
    DatabaseReference busReference;
    List<Seat> seats = new ArrayList<>();
    private Integer balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.busseat_layout);
        final List<AbstractItem> items = new ArrayList<>();
        busReference = databaseReference.child("Bus");
        userReference = databaseReference.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        GridLayoutManager manager = new GridLayoutManager(this, COLUMNS);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lst_items);
        recyclerView.setLayoutManager(manager);

        final AirplaneAdapter adapter = new AirplaneAdapter(this, items);
        recyclerView.setAdapter(adapter);
        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                balance = dataSnapshot.child("balance").getValue(Integer.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        txtSeatSelected = (TextView) findViewById(R.id.txt_seat_selected);

        txtSeatSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter.getSelectedItems().size() == 0) {
                    Toast.makeText(MainActivity.this, "Please select empty seats first", Toast.LENGTH_SHORT).show();
                    return;
                }
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.spinner_dialog, null);
                mBuilder.setTitle("Choose entry location");
                final Spinner mSpinner = (Spinner) mView.findViewById(R.id.spinner);
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.LocationPick));
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner.setAdapter(adapter1);

                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (balance == null) {
                            Toast.makeText(MainActivity.this, "Please wait while we load your balance", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (mSpinner.getSelectedItemPosition() == 0) {

                            Toast.makeText(MainActivity.this, "Please select the location", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        for (int i = 0; i < adapter.getSelectedItems().size(); i++) {
                            if (balance < 10) {
                                Toast.makeText(MainActivity.this, "You are out of balance", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            busReference.child(seats.get(adapter.getSelectedItems().get(i)).key).child("is_booked").setValue(true);
                            busReference.child(seats.get(adapter.getSelectedItems().get(i)).key).child("location").setValue(mSpinner.getSelectedItem().toString());
                            busReference.child(seats.get(adapter.getSelectedItems().get(i)).key).child("booked_by").setValue(
                                    FirebaseAuth.getInstance().getCurrentUser().getUid()
                            );
                            balance = balance - 10;
                            userReference.child("balance").setValue(balance);
                        }
                        finish();

                    }
                });
                mBuilder.setNegativeButton("Cancel", null);
                mBuilder.setView(mView);
                AlertDialog alert = mBuilder.create();
                alert.show();


            }
        });

        final boolean isPassenger = getIntent().getBooleanExtra("isPassenger", false);
        if (isPassenger) {
            adapter.setClicksDisabled(true);
            TextView txtseat = (TextView) findViewById(R.id.fare);

            txtseat.setVisibility(View.GONE);
            reference = FirebaseDatabase.getInstance().getReference().child("Bus");
        } else {
            reference = FirebaseDatabase.getInstance().getReference().child("Bus");

        }

        //   myBookingsQuery.addChildEventListener()
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.e("Snapshot", dataSnapshot.getValue().toString());

                Seat seat = dataSnapshot.getValue(Seat.class);
                if (isPassenger) {
                    seat.is_booked = seat.booked_by != null && seat.booked_by.equals(FirebaseAuth.getInstance().getCurrentUser().getUid());
                }
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

