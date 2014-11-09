package edu.mobicom.lifeplus;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter{

	Context context;
	ArrayList<Task> data;
    private static LayoutInflater inflater = null;

    public CustomAdapter(Context context, ArrayList<Task> data) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
	
	@Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Task getItem(int position) {
        // TODO Auto-generated method stub
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        
        if (vi == null)
            vi = inflater.inflate(R.layout.item, null);
        
        TextView name = (TextView) vi.findViewById(R.id.itemName);
        TextView desc = (TextView) vi.findViewById(R.id.itemDesc);
        CheckBox check = (CheckBox) vi.findViewById(R.id.itemCheck);
        
        name.setText(data.get(position).getName());
        desc.setText(data.get(position).getDesc());
        check.setChecked(data.get(position).isChecked());
        
        return vi;
    }	
	
}
