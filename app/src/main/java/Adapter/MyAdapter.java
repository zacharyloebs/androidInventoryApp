package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projecttwo.R;

import java.util.ArrayList;

import Model.Items;

public class MyAdapter extends BaseAdapter {

    Context context;
    ArrayList<Items> arrayList;


    public MyAdapter(Context context, ArrayList<Items> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.mycustomlistview,null);
        final TextView t1_item = convertView.findViewById(R.id.item_txt);
        final EditText t2_quantity = convertView.findViewById(R.id.quantity_txt);
        Button buttonSave = convertView.findViewById(R.id.buttonSave);
        Button buttonX = convertView.findViewById(R.id.buttonX);

        Items items = arrayList.get(position);

        t1_item.setText(items.getItems());
        t2_quantity.setText(items.getQuantity());

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context.getApplicationContext(), "Cool" + t1_item.getText(),Toast.LENGTH_SHORT).show();
            }
        });

        buttonX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context.getApplicationContext(), "Cool" + t2_quantity.getText(),Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;



    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }
}
