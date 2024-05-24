package com.example.lostfound;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        findViewById(R.id.newAd).setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, CreateAdvert.class);
            startActivity(i);
        });

        findViewById(R.id.listAds).setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, ListAdvert.class);
            startActivity(i);
        });

        findViewById(R.id.showOnMap).setOnClickListener(v -> {
            Intent i = new Intent(MainActivity.this, ShowMap.class);
            startActivity(i);
        });
    }
}