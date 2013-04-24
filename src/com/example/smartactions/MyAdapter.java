package com.example.smartactions;

import java.util.ArrayList;
import java.util.HashMap;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<Object> {
  private final Context context;
  private final ArrayList<HashMap<String, String>> values;

  public MyAdapter(Context context,ArrayList<HashMap<String, String>> values) {
	  super(context, R.layout.list_item);
	  this.context = context;
	  this.values = values;
  }
  public int getCount() {
      return values.size();
  }
  public Object getItem(int position) {
      return position;
  }

  public long getItemId(int position) {
      return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View rowView = inflater.inflate(R.layout.list_item, parent, false);
    //View row = inflater.inflate(R.layout.profile, parent, false);
    TextView textView = (TextView)rowView.findViewById(R.id.label);
   TextView details = (TextView)rowView.findViewById(R.id.activate);
   // TextView currr=(TextView)rowView.findViewById(R.id.curr);
    textView.setText(values.get(position).get("user"));
   // currr.setText(values.get(position).get("chk"));
    details.setText(values.get(position).get("active1"));  
    // Change the icon for Windows and iPhone
    return rowView;
  }
  
  
} 