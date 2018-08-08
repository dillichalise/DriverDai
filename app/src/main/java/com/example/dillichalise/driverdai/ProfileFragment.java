package com.example.dillichalise.driverdai;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileFragment extends Fragment {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_profile, container, false);

        Button button = (Button) view.findViewById(R.id.view_p_seats);
        Button buttonlong = (Button) view.findViewById(R.id.view_pl_seats);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), MainActivity.class);
                i.putExtra("isPassenger",true);
                startActivity(i);

            }
        });

        buttonlong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), LongRouteViewSeats.class);
                i.putExtra("isPassenger", true);
                startActivity(i);
            }
        });
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String email = dataSnapshot.child("email").getValue(String.class);
                Integer balance=dataSnapshot.child("balance").getValue(Integer.class);
                ((TextView) view.findViewById(R.id.username)).setText("Welcome: "+email);
                ((TextView) view.findViewById(R.id.amount)).setText("Balance left: "+balance);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return view;

    }
}
