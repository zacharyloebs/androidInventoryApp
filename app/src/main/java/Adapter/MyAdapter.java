package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationManagerCompat;

import com.example.projecttwo.DatabaseHelper;
import com.example.projecttwo.MainActivity2;
import com.example.projecttwo.R;

import java.util.ArrayList;

import Model.Items;

public class MyAdapter extends BaseAdapter {

    private LinearLayout layoutList;
    Context context;
    ArrayList<Items> arrayList;
    DatabaseHelper db;
    private Switch notifications;
    private NotificationManagerCompat notificationManager;
    MainActivity2 main2;


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
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.mycustomlistview,null);
        final TextView t1_item = convertView.findViewById(R.id.item_txt);
        final EditText t2_quantity = convertView.findViewById(R.id.quantity_txt);
        Button buttonSave = convertView.findViewById(R.id.buttonSave);
        Button buttonX = convertView.findViewById(R.id.buttonX);
        notifications = convertView.findViewById(R.id.smsSwitch);
        notificationManager = NotificationManagerCompat.from(context);

        db = new DatabaseHelper(context);
        main2 = new MainActivity2();

        layoutList = convertView.findViewById(R.id.linear);

        Items items = arrayList.get(position);

        t1_item.setText(items.getItems());
        t2_quantity.setText(items.getQuantity());

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean update = false;
                boolean checkIfEmpty = t2_quantity.getText().toString().trim().isEmpty();
                String string = t2_quantity.getText().toString().trim();

                if (!checkIfEmpty) {
                    update = db.updateItem(t1_item.getText().toString(), t2_quantity.getText().toString());
                }

                if (update) {
                    Toast.makeText(context.getApplicationContext(), "Quantity of " + t1_item.getText() + " updated",Toast.LENGTH_SHORT).show();
                    arrayList = db.getAllData();
                    new MyAdapter(context, arrayList);
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(context.getApplicationContext(), "Failed to update " + t1_item.getText() + " 's quantity. Please enter a number",Toast.LENGTH_SHORT).show();
                }

            }
        });

        buttonX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean delete = db.deleteItem(t1_item.getText().toString());
                if (delete) {
                    Toast.makeText(context.getApplicationContext(), "Item " + t1_item.getText() + " has been deleted",Toast.LENGTH_SHORT).show();
                    arrayList.remove(position);
                    notifyDataSetChanged();
                } else {
                    Toast.makeText(context.getApplicationContext(), "Could not delete " + t1_item.getText() + " has been deleted",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return convertView;

    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }
}
