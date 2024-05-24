package com.example.lostfound;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.database.Cursor;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListAdvert extends AppCompatActivity {

    private RecyclerView rv;
    private ItemAdapter adapter;
    private List<String[]> dataList;
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_advert);
        dbHelper = new DBHelper(this);
        database = dbHelper.getWritableDatabase();
        rv = findViewById(R.id.rv_list_advert);
        rv.setLayoutManager(new LinearLayoutManager(this));
        dataList = new ArrayList<>();
        adapter = new ItemAdapter(this, dataList);
        getAllDataSortedByNameDesc();
    }

    public void getAllDataSortedByNameDesc() {
        dataList.clear();
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
                String id = String.valueOf(cursor.getLong(cursor.getColumnIndex("id")));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String description = cursor.getString(cursor.getColumnIndex("description"));
                String date = cursor.getString(cursor.getColumnIndex("date"));
                String phone = cursor.getString(cursor.getColumnIndex("phone"));
                String location = cursor.getString(cursor.getColumnIndex("location"));
                String type = cursor.getString(cursor.getColumnIndex("type"));
                dataList.add(new String[]{id, name, description, phone, location, date, type});
            } while (cursor.moveToNext());
            cursor.close();
        }
        rv.setAdapter(adapter);
    }
}