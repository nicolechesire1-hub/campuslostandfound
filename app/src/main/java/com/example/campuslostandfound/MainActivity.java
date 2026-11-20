package com.example.campuslostandfound;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listViewItems;
    Button btnAdd;
    ArrayList<String> lostItems;
    ArrayAdapter<String> adapter;

    @Overrider
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewItems = findViewById(R.id.listViewItems);
        btnAdd = findViewById(R.id.btnAdd);

        // Sample data so app is not empty
        lostItems = new ArrayList<>();
        lostItems.add("Black HP Charger - Library - 0700 000 000");
        lostItems.add("Student ID - John - Cafeteria");
        lostItems.add("Blue Umbrella - Lecture Hall 2");

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, lostItems);
        listViewItems.setAdapter(adapter);

        // When user clicks an item, mark as found
        listViewItems.setOnItemClickListener((parent, view, position, id) -> {
            String item = lostItems.get(position);
            Toast.makeText(this, "Contact owner for: " + item,
                    Toast.LENGTH_SHORT).show();
        });

        // When user clicks Report button
        btnAdd.setOnClickListener(v -> {
            showAddDialog();
        });
    }

    private void showAddDialog() {
        EditText input = new EditText(this);
        input.setHint("e.g. Red Wallet - Hostel B - Your Phone");

        new AlertDialog.Builder(this)
                .setTitle("Report Lost Item")
                .setView(input)
                .setPositiveButton("Add", (dialog, which) -> {
                    String newItem = input.getText().toString();
                    if (!newItem.isEmpty()) {
                        lostItems.add(newItem);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}