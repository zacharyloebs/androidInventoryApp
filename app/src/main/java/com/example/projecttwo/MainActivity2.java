package com.example.projecttwo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Pattern;

public class MainActivity2 extends AppCompatActivity {

    DatabaseHelper db;
    private Button buttonAdd;
    private ListView listView;
    private EditText editItem, editQuantity;
    private ImageView imageClose;
    int quantity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        buttonAdd = findViewById(R.id.buttonAdd);
        editItem = findViewById(R.id.editTextTextItem);
        editQuantity = findViewById(R.id.editTextTextQuantity);

        db = new DatabaseHelper(this);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    // Save data to database
                    boolean insert = db.createUsers(editItem.getText().toString().trim(), editQuantity.getText().toString().trim());
                    if (insert == true) {
                        Toast.makeText(getApplicationContext(), "Successfully Added Item",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Item Already Exists",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
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
            Toast.makeText(this, "Enter A Valid Number", Toast.LENGTH_SHORT).show();

        } else {
            result = true;
        }
        return result;
    }
}