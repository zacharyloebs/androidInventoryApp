package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

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
            TextView t1_id = (TextView)convertView.findViewById(R.id.id_txt);
            TextView t2_name = (TextView)convertView.findViewById(R.id.name_txt);
            TextView t3_age = (TextView)convertView.findViewById(R.id.age_txt);

            Items items = arrayList.get(position);

            t1_id.setText(items.getItems());
            t2_name.setText(items.getQuantity());

            return convertView;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }
}