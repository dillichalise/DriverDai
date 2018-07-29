package com.example.dillichalise.driverdai;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        //check whether the network provider is enabled
            if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onLocationChanged(Location location) {
                        double latitude = location.getLatitude();  //get the latitude of current location
                        double longitude = location.getLongitude(); //get longitude of current location

                        LatLng latLng = new LatLng(latitude, longitude);
                        Geocoder geocoder = new Geocoder(getApplicationContext());
                        List<Address> addressList = null;
                        try {
                            addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        String str = addressList.get(0).getLocality()+",";
                        str += addressList.get(0).getCountryName();
                        mMap.addMarker(new MarkerOptions().position(latLng).title(str));

                        mMap.setMyLocationEnabled(true);

                        CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(13).build();
                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


                    }

                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {

                    }

                    @Override
                    public void onProviderEnabled(String s) {

                    }

                    @Override
                    public void onProviderDisabled(String s) {

                    }
                });

             }
             else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double latitude = location.getLatitude();  //get the latitude of current location
                    double longitude = location.getLongitude(); //get longitude of current location

                    LatLng latLng = new LatLng(latitude, longitude);
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    List<Address> addressList = null;
                    try {
                        addressList = geocoder.getFromLocation(latitude, longitude, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String str = addressList.get(0).getLocality()+",";
                    str += addressList.get(0).getCountryName();

                    mMap.addMarker(new MarkerOptions().position(latLng).title(str));
                   CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(13).build();
                   mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
            }

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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


        LatLng kalanki = new LatLng(27.6931, 85.2807);
        googleMap.addMarker(new MarkerOptions().position(kalanki).title("Bus0001").snippet("kalanki-ratnapark").icon(BitmapDescriptorFactory.fromResource(R.drawable.busicon)));

        LatLng rabibhawan = new LatLng(27.696729,  85.293814);
        googleMap.addMarker(new MarkerOptions().position(rabibhawan).title("Bus0002").snippet("kalanki-ratnapark").icon(BitmapDescriptorFactory.fromResource(R.drawable.busicon)));

        LatLng kalimati = new LatLng(27.698391,  85.299372);
        googleMap.addMarker(new MarkerOptions().position(kalimati).title("Bus0003").snippet("kalanki-ratnapark").icon(BitmapDescriptorFactory.fromResource(R.drawable.busicon)));

        LatLng teku = new LatLng(27.696244,  85.307078);
        googleMap.addMarker(new MarkerOptions().position(teku).title("Bus0004").snippet("kalanki-ratnapark").icon(BitmapDescriptorFactory.fromResource(R.drawable.busicon)));

        LatLng tripureshwor = new LatLng(27.694276,  85.312192);
        googleMap.addMarker(new MarkerOptions().position(tripureshwor).title("Bus0005").snippet("kalanki-ratnapark").icon(BitmapDescriptorFactory.fromResource(R.drawable.busicon)));

        LatLng chowk = new LatLng(27.694234,  85.314136);
        googleMap.addMarker(new MarkerOptions().position(chowk).title("Bus0006").snippet("kalanki-ratnapark").icon(BitmapDescriptorFactory.fromResource(R.drawable.busicon)));

        LatLng rnac = new LatLng(27.702432,  85.313615);
        googleMap.addMarker(new MarkerOptions().position(rnac).title("Bus0007").snippet("kalanki-ratnapark").icon(BitmapDescriptorFactory.fromResource(R.drawable.busicon)));

        LatLng ratnapark = new LatLng(27.706716,  85.315380);
        googleMap.addMarker(new MarkerOptions().position(ratnapark).title("Bus0008").snippet("kalanki-ratnapark").icon(BitmapDescriptorFactory.fromResource(R.drawable.busicon)));

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
        Intent intent = new Intent(MapActivity.this, PassengerLoginActivity.class);
        startActivity(intent);
    }
}
