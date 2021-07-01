package com.rich_it.library.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.airbnb.lottie.L;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.android.clustering.ClusterManager;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.rich_it.library.Model.MyItem;
import com.rich_it.library.R;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;

public class GoogleMapActivity extends AppCompatActivity implements View.OnClickListener {

    String TAG = GoogleMapActivity.class.getName();
    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    FusedLocationProviderClient client;
    Button showPlaceButton;
    Spinner spinner;
    Location myLocation;
    LatLng myLatLng;
    String[] placeTypes = {"atm", "restaurant"};
    String[] places = {"ATM", "Restaurant"};
    String baseUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=";
    String defaultRadius = "5000";

    // clustering
    private ClusterManager<MyItem> clusterManager;

    // +"?location=" + currentLat + "," + currentLong + "&radius=5000" + "&type=" + placeTypeList[i] + "&sensor=true" + "&key=" + getResources().getString(R.string.google_map_key);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);

        initObject(this);
        requiredTask(this);
    }

    private void initObject(GoogleMapActivity context) {
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        client = LocationServices.getFusedLocationProviderClient(context);
        showPlaceButton = findViewById(R.id.showPlaceButton);
        spinner = findViewById(R.id.spinner);
    }

    private void requiredTask(GoogleMapActivity context) {
        Dexter.withContext(context)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Log.d(TAG, "onPermissionGranted: ");
                        getMyLocation();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Log.d(TAG, "onPermissionDenied: ");
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                        Log.d(TAG, "onPermissionRationaleShouldBeShown: ");
                    }
                }).check();
        showPlaceButton.setOnClickListener((View.OnClickListener) context);
        spinner.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, places ));
    }

    private void getMyLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Task<Location> tasks = client.getLastLocation();
        tasks.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location == null){
                    return;
                }
                myLocation = location;
                mapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(@NonNull @NotNull GoogleMap googleMap) {
                        Log.d(TAG, "onMapReady: ");
                        mMap = googleMap;

                        // Add a marker in Sydney and move the camera
                        LatLng currentLatLng = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
                        myLatLng = currentLatLng;
                        mMap.addMarker(new MarkerOptions().position(currentLatLng).title("My Location"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15));
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.showPlaceButton:
                showNearbyPlace();
                break;
        }
    }

    private void showNearbyPlace() {
        int selected = spinner.getSelectedItemPosition();
        String url = baseUrl + myLocation.getLatitude() +","+ myLocation.getLongitude() +"&radius=" + defaultRadius + "&types=" + placeTypes[selected]+ "&sensor=true" + "&key=" + getResources().getString(R.string.google_maps_key);
        Log.d(TAG, "showNearbyPlace: " + url);
        Toast.makeText(this, "Showing Nearby places "+url, Toast.LENGTH_SHORT).show();
        setUpClusterer(myLocation);
    }

    private void setUpClusterer(Location location) {
        // Position the map.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 10));

        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        clusterManager = new ClusterManager<MyItem>(GoogleMapActivity.this, mMap);

        // Point the map's listeners at the listeners implemented by the cluster
        // manager.
        mMap.setOnCameraIdleListener(clusterManager);
        mMap.setOnMarkerClickListener(clusterManager);

        // Add cluster items (markers) to the cluster manager.
        addItems();
    }

    private void addItems() {

        // Set some lat/lng coordinates to start with.
        double lat = myLocation.getLatitude();
        double lng = myLocation.getLongitude();

        // Add ten cluster items in close proximity, for purposes of this example.
        for (int i = 0; i < 100; i++) {
            double offset = i / 100d;
            lat = lat + offset;
            lng = lng + offset;
            LatLng finalLatLng = new LatLng(lat, lng);
            double distance = CalculationByDistance(myLatLng, finalLatLng);
            Log.d(TAG, "addItems: " + distance);
            if(distance<=2.00f){
                MyItem offsetItem = new MyItem(lat, lng, "Title " + i, "Snippet " + i);
                clusterManager.addItem(offsetItem);
            }
        }
    }

    public double CalculationByDistance(LatLng StartP, LatLng EndP) {
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return Radius * c;
    }

}