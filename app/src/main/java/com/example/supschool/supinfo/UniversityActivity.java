package com.example.supschool.supinfo;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class UniversityActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        // Add a marker in Sydney and move the camera
        LatLng supinfo = new LatLng(14.6702929, -17.4297956);
        mMap.addMarker(new MarkerOptions().position(supinfo).title("SUPINFO").snippet("ecole de formation , contact: 33 889 11 88"));

        LatLng ucad = new LatLng(14.6878005, -17.4640114);
        mMap.addMarker(new MarkerOptions().position(ucad).title("UCAD").snippet("université de dakar , contact: 33 825 05 30"));

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(supinfo,13));
        CircleOptions c = new CircleOptions()
                .center(supinfo).radius(500).fillColor(Color.GREEN).strokeColor(Color.BLUE).strokeColor(5);
        mMap.addCircle(c);


        mMap.moveCamera(CameraUpdateFactory.newLatLng(supinfo));
    }
}
