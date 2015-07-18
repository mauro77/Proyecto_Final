package com.cazz.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Cristian on 14/07/2015.
 */
public class FragmentRoutes extends Fragment {

    Button button1, button2, button3,button4;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_routes, container, false);
        button1 = (Button) view.findViewById(R.id.zona1);
        button2 = (Button) view.findViewById(R.id.zona2);
        button3 = (Button) view.findViewById(R.id.zona3);
        button4 = (Button) view.findViewById(R.id.zona3);

        // Inflate the layout for this fragment
        return view;
    }


}