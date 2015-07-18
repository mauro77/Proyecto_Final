package com.cazz.proyectofinal;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Cristian on 14/07/2015.
 */
public class FragmentMap extends Fragment implements GoogleApiClient.ConnectionCallbacks, LocationListener {

    MapView mMapView;
    private GoogleMap googleMap;
    Location location;
    private Location mLastLocation;
    private LatLng currentLocation;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private ArrayList<Double> zonesLati = new ArrayList<Double>();
    private ArrayList<Double> zonesLong = new ArrayList<Double>();
    private ArrayList<Double> zonesDist=new ArrayList<Double>();
    static private int minIndexValue;
    private boolean newLocationReady = false;
/*
    private double suroccidentalLat = 6.215793;
    private double suroccidentalLong =-75.590529;
    private double surorientalLat=6.200912;
    private double surorientalLong= -75.563064;
    private double noroccidentalLat= 6.296071;
    private double noroccidentalLong=-75.582804;
    private double nororientalLat=6.287224;
    private double nororientalLong=-75.548663;
*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflat and return the layout
        View v = inflater.inflate(R.layout.fragment_location_info, container,
                false);
        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();// needed to get the map to display immediately
        zonesLati.add(6.215793);
        zonesLati.add(6.200912);
        zonesLati.add(6.296071);
        zonesLati.add(6.287224);

        zonesLong.add(-75.590529);
        zonesLong.add(-75.563064);
        zonesLong.add(-75.582804);
        zonesLong.add(-75.548663);
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        googleMap = mMapView.getMap();
        googleMap.setMyLocationEnabled(true);

        location = googleMap.getMyLocation();
      //  compareDistances(6.296071,-75.582804);


        if (location != null) {


        }

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds

        // Perform any camera updates here
        return v;
    }

    public void compareDistances(Double currentLat, Double currentLong){
            int index;
        for (int i=0; i<4;i++){
            zonesDist.add(i, getDistance(zonesLati.get(i), zonesLong.get(i), currentLat, currentLong));

        }
            minIndexValue=minIndex(zonesDist);
        Toast.makeText(getActivity(),  String.valueOf(minIndexValue) ,
                Toast.LENGTH_SHORT).show();
    }

    public static int getMinIndex(){

         return minIndexValue;

    }


    public double getDistance(Double Lat, Double Long, Double currentLat,Double currentLong){

        double distance;

        Location locationA = new Location("point A");

        locationA.setLatitude(Lat);

        locationA.setLongitude(Long);

        Location locationB = new Location("point B");

        locationB.setLatitude(currentLat);

        locationB.setLongitude(currentLong);
/*
        Toast.makeText(getActivity(),  String.valueOf(locationA.distanceTo(locationB)) ,
                Toast.LENGTH_SHORT).show();*/
        return locationA.distanceTo(locationB);
    }

    public static int minIndex (ArrayList<Double> list) {
        return list.indexOf(Collections.min(list)); }

    @Override
    public void onResume() {
        super.onResume();
        if(!mGoogleApiClient.isConnected()) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onLocationChanged(Location location) {

        // latitude and longitude
        compareDistances(location.getLatitude(),location.getLongitude());
        LatLng lat = new LatLng(location.getLatitude(), location.getLongitude());
        compareDistances(location.getLatitude(),location.getLongitude());
        // create marker

        MarkerOptions marker = new MarkerOptions().position(
                lat).title("Hello Maps");

        // Changing marker icon
        marker.icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

        // adding marker
        googleMap.addMarker(marker);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(lat).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLastLocation=LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation!=null) {
            handleNewLocation(mLastLocation);
            newLocationReady=true;
        }
        else{
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, (com.google.android.gms.location.LocationListener) this);
        }
    }
    private void handleNewLocation(Location location) {

        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();
        currentLocation = new LatLng(currentLatitude, currentLongitude);
        compareDistances(currentLatitude,currentLongitude);
        MarkerOptions options1 = new MarkerOptions()
                .position(currentLocation)
                .title("Ubicacion actual");
        //googleMap.addMarker(options1);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));

    }
    @Override
    public void onConnectionSuspended(int i) {

    }
}