package com.nisaefendioglu.google_maps;

import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ZoomControls;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nisaefendioglu.google_maps.databinding.ActivityMapsBinding;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ZoomControls zoom = (ZoomControls) findViewById(R.id.zoom);
        zoom.setOnZoomOutClickListener(view -> mMap.animateCamera(CameraUpdateFactory.zoomOut()));
        zoom.setOnZoomInClickListener(view -> mMap.animateCamera(CameraUpdateFactory.zoomIn()));

        final Button btn_MapType = (Button) findViewById(R.id.btn_Sat);
        btn_MapType.setOnClickListener(view -> {
            if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL) {
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                btn_MapType.setText("Normal View");
            } else {
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                btn_MapType.setText("Satellite View");
            }
        });

        Button btnGo = (Button) findViewById(R.id.btn_Go);

        btnGo.setOnClickListener(view -> {
            EditText etLocation = (EditText) findViewById(R.id.et_location);
            String location = etLocation.getText().toString();
            if (location != null && !location.equals("")) {
                List<Address> adressList = null;
                Geocoder geocoder = new Geocoder(MapsActivity.this);
                try {
                    adressList = geocoder.getFromLocationName(location, 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Address address = adressList.get(0);
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng).title("Location " + location));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng turkey = new LatLng(41.015137	, 28.979530);
        mMap.addMarker(new MarkerOptions().position(turkey).title("Marker in Turkey"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(turkey));
    }
}