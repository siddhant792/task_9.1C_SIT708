package com.example.lostfound;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ViewItem extends AppCompatActivity {

    TextView textViewName, textViewDescription, textViewPhone, textViewLocation, textViewDate, textViewLostFound;
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_item);
        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();
        textViewName = findViewById(R.id.textViewName);
        textViewDescription = findViewById(R.id.textViewDescription);
        textViewPhone = findViewById(R.id.textViewPhone);
        textViewLocation = findViewById(R.id.textViewLocation);
        textViewDate = findViewById(R.id.textViewDate);
        textViewLostFound = findViewById(R.id.textViewLostFound);

        Intent i = getIntent();
        String[] arr = i.getStringArrayExtra("data");
        textViewName.setText("Name: " + arr[1]);
        textViewDescription.setText("Description: " + arr[2]);
        textViewPhone.setText("Phone: " + arr[3]);
        textViewLocation.setText("Location: " + arr[4]);
        textViewDate.setText("Date: " + arr[5]);
        textViewLostFound.setText("Type: " + arr[6].toUpperCase());

        findViewById(R.id.btn_remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.delete("my_table", "id = ?", new String[]{String.valueOf(arr[0])});
                Toast.makeText(ViewItem.this, "Item Deleted Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}