package com.example.smartactions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

public class AddProfile extends Activity implements OnClickListener{
	Button subProf;
	EditText nameProf;
	Spinner s,s1,s2,s3;
	SeekBar sb,sb1;
	ProfileDatabase db;
	AudioManager am;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addprofile);
        subProf=(Button)findViewById(R.id.done);
        nameProf=(EditText)findViewById(R.id.name);
        s=(Spinner)findViewById(R.id.mode);
        s1=(Spinner)findViewById(R.id.special);
        s2=(Spinner)findViewById(R.id.bluspin);
        s3=(Spinner)findViewById(R.id.wispin);
        sb=(SeekBar)findViewById(R.id.volume);
        sb1=(SeekBar)findViewById(R.id.briseek);
        subProf.setOnClickListener(this);
        db = new ProfileDatabase(this);
        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                int maxVolume = am.getStreamMaxVolume(AudioManager.STREAM_RING);
                int curVolume = am.getStreamVolume(AudioManager.STREAM_RING);
                sb.setMax(maxVolume);
                sb.setProgress(curVolume);
        sb1.setMax(254);
    }
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0.getId()==R.id.done){
			subProf=(Button)findViewById(R.id.done);
	        nameProf=(EditText)findViewById(R.id.name);
	        s=(Spinner)findViewById(R.id.mode);
	        s1=(Spinner)findViewById(R.id.special);
	        s2=(Spinner)findViewById(R.id.bluspin);
	        s3=(Spinner)findViewById(R.id.wispin);
	        sb=(SeekBar)findViewById(R.id.volume);
	        sb1=(SeekBar)findViewById(R.id.briseek);
	        String qt,qt1,qt2,qt3;
	     qt2="NC";
	     qt3="NC";
	        if(s.getSelectedItem().toString().equalsIgnoreCase("Normal"))
	        	qt="N";
	        else if(s.getSelectedItem().toString().equalsIgnoreCase("Silent"))
	        	qt="S";
	        else
	        	qt="V";
	        
	        if(s1.getSelectedItem().toString().equalsIgnoreCase("Driving"))
	        	qt1="1";
	        else if(s1.getSelectedItem().toString().equalsIgnoreCase("Meeting"))
	        	qt1="1";
	        else if(s1.getSelectedItem().toString().equalsIgnoreCase("Studying"))
	        	qt1="1";
	        else
	        	qt1="0";
	        if(s2.getSelectedItem().toString().equalsIgnoreCase("No Change"))
	        	qt2="NC";
	        else if(s2.getSelectedItem().toString().equalsIgnoreCase("ON"))
	        	qt2="O";
	        else if(s2.getSelectedItem().toString().equalsIgnoreCase("OFF"))
	        	qt2="N";
	        
	        if(s3.getSelectedItem().toString().equalsIgnoreCase("No Change"))
	        	qt3="NC";
	        else if(s3.getSelectedItem().toString().equalsIgnoreCase("ON"))
	        	qt3="O";
	        else if(s3.getSelectedItem().toString().equalsIgnoreCase("OFF"))
	        	qt3="N";
	        
	        if ((nameProf.getText().toString().equals(""))){
				Toast.makeText(getApplicationContext(), "Please Provide All the details", Toast.LENGTH_SHORT).show();
			
			}
			else{
				Contact c=new Contact(nameProf.getText().toString(),sb.getProgress(),qt,"0",qt1,qt2,qt3,sb1.getProgress());
				db.addContact(c);
				Intent i = new Intent(AddProfile.this, TabView.class);
				startActivity(i);
				}
			
		}
	}
	
}    
