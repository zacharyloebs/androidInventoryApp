package com.example.projecttwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper db;
    private Button buttonAdd;
    private LinearLayout layoutList;
    private EditText editItem;
    private EditText editQuantity;
    private ImageView imageClose;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        layoutList = findViewById(R.id.layout_list);
        buttonAdd = findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(this);

        addView();

        editItem = findViewById(R.id.editTextTextItem);

    }

    public void onClick(View view) {
        addView();
    }

    private void addView() {

        final View inventoryView = getLayoutInflater().inflate(R.layout.row_add_item, null,false);

        editItem = inventoryView.findViewById(R.id.editTextTextItem);
        editQuantity = inventoryView.findViewById(R.id.editTextTextQuantity);
        imageClose = inventoryView.findViewById(R.id.image_close);

        editItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                boolean check = db.createItem(editItem.getText().toString().trim(), editQuantity.getText().toString().trim());
                if (check == true) {
                    Toast.makeText(getApplicationContext(), "Successfully Added Item", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Item Already Exists",Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeView(inventoryView);
            }
        });

        layoutList.addView(inventoryView);
    }

    private void removeView(View view) {

        layoutList.removeView(view);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        
    }
    private Boolean validate() {
        boolean result = false;

        String name = editItem.getText().toString();
        String pass = editQuantity.getText().toString();

        if(name.isEmpty() && pass.isEmpty()) {
            Toast.makeText(this, "Please Enter All Fields", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        return result;
    }

}