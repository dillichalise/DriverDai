package com.example.dillichalise.driverdai;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BusFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, View.OnClickListener {

    private GoogleMap mMap;
    private View view;
    private LinearLayout seat1;
    private DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("locations").child("Bus");
    private bus1 value;
    private Dialog dialog;
    private ImageView ivSeat1;
    private Double latitude,longitude;
    private ValueEventListener valueEventListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bus, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        dialog = new Dialog(getActivity());
        dialog.setTitle("Bus JAS");
        View dialogView = View.inflate(getActivity(), R.layout.busseat_layout, null);
        dialog.setContentView(dialogView);
        final ProgressDialog progressDialog=new ProgressDialog(getActivity());
        progressDialog.setTitle("Available bus");
        progressDialog.setMessage("Please wait while bus location are being fetched");
        progressDialog.show();
         valueEventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("location", dataSnapshot.getValue().toString());
                progressDialog.hide();
                latitude = dataSnapshot.child("latitude").getValue(Double.class);
                longitude = dataSnapshot.child("longitude").getValue(Double.class);
                LatLng aa = new LatLng(latitude, longitude);
                mMap.addMarker(new MarkerOptions().position(aa).title("Bus001").snippet("ringroad yatayat").icon(BitmapDescriptorFactory.fromResource(R.drawable.busicon)));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
      /*  seat1 = (LinearLayout) dialogView.findViewById(R.id.ll_seat_1);
        ivSeat1 = (ImageView) dialogView.findViewById(R.id.iv_seat_1);
        seat1.setOnClickListener(this);*/
/*
        databaseReference = FirebaseDatabase.getInstance().getReference().child("bus1");
*/
   /*     databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Datasnapshot", dataSnapshot.getValue().toString());
                value = dataSnapshot.getValue(bus1.class);

                if (value.seat1Available()) {
                    Log.e("seat1aVailable", String.valueOf(value.seat1Available()));

                    seat1.setBackgroundColor(getResources().getColor(R.color.green));
                    ivSeat1.setBackgroundColor(getResources().getColor(R.color.green));
                } else {
                    seat1.setBackgroundColor(getResources().getColor(R.color.red));
                    ivSeat1.setBackgroundColor(getResources().getColor(R.color.red));
                }
//                    if (value) {
//                        seat1.setBackgroundColor(getResources().getColor(R.color.red));
//                        ivSeat1.setBafckgroundColor(getResources().getColor(R.color.red));
//                    } else {
//                        seat1.setBackgroundColor(getResources().getColor(R.color.green));
//                        ivSeat1.setBackgroundColor(getResources().getColor(R.color.green));
//                    }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("datasnapshot", databaseError.getMessage());
            }
        });*/

        return view;

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMarkerClickListener(this);
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        // Add a marker in ktm and move the camera
     /*   LatLng ktm = new LatLng(27.7172, 85.3240);
        mMap.addMarker(new MarkerOptions().position(ktm).title("Marker in kathmandu"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ktm));*/

/*
        LatLng ktm = new LatLng(27.7172, 85.3240);
     CameraPosition cameraPosition = new CameraPosition.Builder().target(ktm).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));*/



//        LatLng rabibhawan = new LatLng(27.696729, 85.293814);
//        googleMap.addMarker(new MarkerOptions().position(rabibhawan).title("Bus0002").snippet("kalanki-ratnapark").icon(BitmapDescriptorFactory.fromResource(R.drawable.busicon)));
//
//        LatLng kalimati = new LatLng(27.698391, 85.299372);
//        googleMap.addMarker(new MarkerOptions().position(kalimati).title("Bus0003").snippet("kalanki-ratnapark").icon(BitmapDescriptorFactory.fromResource(R.drawable.busicon)));
//
//        LatLng teku = new LatLng(27.696244, 85.307078);
//        googleMap.addMarker(new MarkerOptions().position(teku).title("Bus0004").snippet("kalanki-ratnapark").icon(BitmapDescriptorFactory.fromResource(R.drawable.busicon)));
//
//        LatLng tripureshwor = new LatLng(27.694276, 85.312192);
//        googleMap.addMarker(new MarkerOptions().position(tripureshwor).title("Bus0005").snippet("kalanki-ratnapark").icon(BitmapDescriptorFactory.fromResource(R.drawable.busicon)));
//
//        LatLng chowk = new LatLng(27.694234, 85.314136);
//        googleMap.addMarker(new MarkerOptions().position(chowk).title("Bus0006").snippet("kalanki-ratnapark").icon(BitmapDescriptorFactory.fromResource(R.drawable.busicon)));
//
//        LatLng rnac = new LatLng(27.702432, 85.313615);
//        googleMap.addMarker(new MarkerOptions().position(rnac).title("Bus0007").snippet("kalanki-ratnapark").icon(BitmapDescriptorFactory.fromResource(R.drawable.busicon)));
//
//        LatLng ratnapark = new LatLng(27.706716, 85.315380);
//        googleMap.addMarker(new MarkerOptions().position(ratnapark).title("Bus0008").snippet("kalanki-ratnapark").icon(BitmapDescriptorFactory.fromResource(R.drawable.busicon)));

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        getActivity().startActivity(intent);
        return false;
        /*showDialog();
        return false;*/
    }

    private void showDialog() {

        dialog.show();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}
