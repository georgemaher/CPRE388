package com.example.mapruler;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.mapruler.databinding.ActivityMapsBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private FusedLocationProviderClient fusedLocationClient;
    private double lat, lon = 0;
    private Geocoder geocoder;
    private List<Address> list;
    private float[] dist = new float[3];
    EditText text;
    String city = "";
    Button but;
    Marker marker;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        geocoder = new Geocoder(this);
        text = findViewById(R.id.city);
        but = (Button) findViewById(R.id.button);
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
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.mapstyle_mine));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            // Logic to handle location object
                            marker = mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("hidden"));
                            marker.setVisible(false);
                            lat = location.getLatitude();
                            lon = location.getLongitude();
                            LatLng current = new LatLng(lat, lon);
                            mMap.addMarker(new MarkerOptions().position(current).title("Current Location"));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
                            but.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    marker.remove();
                                    city = text.getText().toString();
                                    try {

                                        list = geocoder.getFromLocationName(city, 1);
                                        while (list.size() == 0) {
                                            list = geocoder.getFromLocationName(city, 1);
                                        }
                                        LatLng canada = new LatLng(list.get(0).getLatitude(), list.get(0).getLongitude());

                                        marker = mMap.addMarker(new MarkerOptions().position(canada).title("Selected"));
                                        marker.setVisible(true);
                                        mMap.moveCamera(CameraUpdateFactory.newLatLng(canada));
                                        if (list.size() > 0) {
                                            Location.distanceBetween(lat, lon, list.get(0).getLatitude(), list.get(0).getLongitude(), dist);
                                            toast.makeText(getApplicationContext(), "distance is " + (int) dist[0] / 1000 + "KM", Toast.LENGTH_SHORT).show();

                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }
                });
    }
}