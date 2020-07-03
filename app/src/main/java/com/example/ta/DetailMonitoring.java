package com.example.ta;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailMonitoring extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    TextView idkapal2,idlatitude2,idlongitude2,idstatus2,idtime2;
    String id_kapal2,id_latitude2,id_longitude2,id_status2,id_time2;
    DataMonitoring data;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mot_detail);
        idkapal2 = (TextView) findViewById(R.id.idkapal2);
        idlatitude2 = (TextView) findViewById(R.id.idlatitude2);
        idlongitude2 = (TextView) findViewById(R.id.idlongitude2);
        idstatus2 = (TextView) findViewById(R.id.idstatus2);
        idtime2 = (TextView) findViewById(R.id.idtime2);

        Intent intent =getIntent();
        data = (DataMonitoring) intent.getParcelableExtra("data");
        id_kapal2 = data.getIdkapal();
        id_latitude2 = data.getIdlatitude();
        id_longitude2 = data.getIdlongitude();
        id_status2 = data.getIdstatus();
        id_time2 = data.getIdtime();

        idkapal2.setText(id_kapal2);
        idlatitude2.setText(id_latitude2);
        idlongitude2.setText(id_longitude2);
        idstatus2.setText(id_status2);
        idtime2.setText(id_time2);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        id_latitude2 = data.getIdlatitude();
        id_longitude2 = data.getIdlongitude();
        Double lati = Double.parseDouble(id_latitude2);
        Double longi = Double.parseDouble(id_longitude2);

        id_status2 = data.getIdstatus();

        // Add a marker in Sydney and move the camera
        LatLng lokasi = new LatLng(lati, longi);
        mMap.addMarker(new MarkerOptions().position(lokasi).title("Location: "+ lati + "," + longi ));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lokasi,15));

        //mMap.animateCamera( CameraUpdateFactory.zoomTo( 10.0f ) );
        //mMap.animateCamera( CameraUpdateFactory.zoomTo( 17) );
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}