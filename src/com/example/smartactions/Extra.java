package com.example.smartactions;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Extra extends Activity implements OnClickListener {
	public static Extra self;
	Button b;
	EditText cell,cell1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extra);
       b=(Button)findViewById(R.id.learn);
       b.setOnClickListener(this);
       self=this;
    }
	@Override
	public void onClick(View arg0) {
		EditText cel=(EditText)findViewById(R.id.celltext);
		EditText cel1=(EditText)findViewById(R.id.cellnametext);
		TelephonyManager tel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		JSONArray cellList = new JSONArray();
		List<NeighboringCellInfo> neighCells = tel.getNeighboringCellInfo();
		cel.setText("104:70:702");
		cel1.setText("Vidyavihar Campus");
		for (int i = 0; i < neighCells.size(); i++) {
		  try {
		    JSONObject cellObj = new JSONObject();
		    NeighboringCellInfo thisCell = neighCells.get(i);
		    cel.setText(thisCell.toString());
		    //cellList.put(cellObj);
		  } catch (Exception e) {}
		}
		/*if (cellList.length() > 0) {
		  try {
		    //params.put("cellTowers", cellList);
		  } catch (JSONException e) {}
		}*/
			
			    }
	}
