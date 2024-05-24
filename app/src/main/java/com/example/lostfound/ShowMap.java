package com.example.lostfound;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.lostfound.databinding.ActivityShowMapBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ShowMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    List<LatLng> coordinates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_map);
        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void getAllDataSortedByNameDesc() {
        coordinates = new ArrayList<>();
        Cursor cursor = database.query(
                "my_table",
                null,
                null,
                null,
                null,
                null,
                "date DESC"
        );

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String location = cursor.getString(cursor.getColumnIndex("location"));
                String[] parts = location.split("\\|");
                double lat = Double.parseDouble(parts[1]);
                double lon = Double.parseDouble(parts[2]);
                System.out.println("Lat: " + lat + " Long: " + lon);
                coordinates.add(new LatLng(lat, lon));
            } while (cursor.moveToNext());
            cursor.close();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getAllDataSortedByNameDesc();

        for (LatLng coordinate : coordinates) {
            mMap.addMarker(new MarkerOptions().position(coordinate).title("Marker").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        }

        if (!coordinates.isEmpty()) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates.get(0), 10));
        }
    }
}