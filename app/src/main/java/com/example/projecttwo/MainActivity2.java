package com.example.projecttwo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.regex.Pattern;

import Adapter.MyAdapter;
import Model.Items;

public class MainActivity2 extends AppCompatActivity {

    DatabaseHelper db;
    private Button buttonAdd;
    private ListView listView;
    private EditText editItem, editQuantity;
    private TextView textItem, textQuantity;
    ArrayList<Items> arrayList;
    MyAdapter myAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        buttonAdd = findViewById(R.id.buttonAdd);
        editItem = findViewById(R.id.editTextTextItem);
        editQuantity = findViewById(R.id.editTextTextQuantity);
        listView = findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        textItem = findViewById(R.id.id_txt);
        textQuantity = findViewById(R.id.name_txt);

        db = new DatabaseHelper(this);

        loadDataInListView();

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    // Save data to database
                    boolean insert = db.createItem(editItem.getText().toString().trim(), editQuantity.getText().toString().trim());
                    if (insert == true) {
                        Toast.makeText(getApplicationContext(), "Successfully Added Item",Toast.LENGTH_SHORT).show();
                        editItem.getText().clear();
                        editQuantity.getText().clear();
                    } else {
                        Toast.makeText(getApplicationContext(), "Item Already Exists",Toast.LENGTH_SHORT).show();
                        editItem.getText().clear();
                        editQuantity.getText().clear();
                    }
                }
            }
        });
    }

    private void loadDataInListView() {

        arrayList = db.getAllData();
        myAdapter = new MyAdapter(this,arrayList);
        listView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }

    private Boolean validate() {
        boolean result = false;

        String item = editItem.getText().toString().trim();
        String quantity = editQuantity.getText().toString().trim();
        boolean isNumber = Pattern.matches("[0-9]+", quantity);

        if (item.isEmpty() && quantity.isEmpty()) {
            Toast.makeText(this, "Both Fields Are Empty", Toast.LENGTH_SHORT).show();
        }
        else if (item.isEmpty()) {
            Toast.makeText(this, "Please Add An Item", Toast.LENGTH_SHORT).show();

        }
        else if (isNumber == false) {
            Toast.makeText(this, "Enter A Number", Toast.LENGTH_SHORT).show();

        } else {
            result = true;
        }
        return result;
    }

    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }
}