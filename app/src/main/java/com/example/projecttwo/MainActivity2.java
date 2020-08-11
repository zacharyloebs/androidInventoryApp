package com.example.projecttwo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private Button buttonAdd;
    private LinearLayout layoutList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        layoutList = findViewById(R.id.layout_list);
        buttonAdd = findViewById(R.id.button_add);

        buttonAdd.setOnClickListener(this);
    }

    public void onClick(View view) {
        addView();
    }

    private void addView() {

        final View inventoryView = getLayoutInflater().inflate(R.layout.row_add_item, null,false);

        EditText editItem = (EditText)inventoryView.findViewById(R.id.editTextTextItem);
        EditText editQuantity = (EditText)inventoryView.findViewById(R.id.editTextTextQuantity);
        ImageView imageClose = (ImageView)inventoryView.findViewById(R.id.image_close);

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
}