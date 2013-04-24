package com.example.smartactions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



import android.app.ActivityManager;
import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class  Profiles extends ListActivity implements OnClickListener {
	Button s,s1,s2,s3,all,appkill;
	public static Profiles self;
	TextView t1,t2;
	String activeProfile;
	ProfileDatabase db;
	WifiManager wfm = null;
	BluetoothAdapter adapter = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	TextView err;
    	self=this;
    	ArrayList<Contact> u;
    	db = new ProfileDatabase(this);
        u = new ArrayList<Contact>();
    	ListView lv;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profiles);
        TranslateAnimation as= new TranslateAnimation(0,0,100, 0);
        as.setDuration(1500);
    	as.setFillAfter(true);
        all=(Button)findViewById(R.id.all);
        all.startAnimation(as);
        all.setOnClickListener(this);
        appkill=(Button)findViewById(R.id.appk);
        appkill.startAnimation(as);
        appkill.setOnClickListener(this);
        lv = getListView();
        u = db.getAllProfiles();
        wfm = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        adapter = BluetoothAdapter.getDefaultAdapter();
        if (u.size()==0)
        {
        	err=(TextView)findViewById(R.id.erro);
        	err.setVisibility(View.VISIBLE);
        	
        }
        else
        {	
        
        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
        for (Contact i:u) {
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("user", i.name);
			if(i.act.equalsIgnoreCase("1"))
			{	
				map.put("active1", "Active");
				activeProfile=i.name;
				Contact val=db.getCont(activeProfile);
				chkProfile(val);
			}
			else
				map.put("active1", "");
			data.add(map);
		}	
		lv = getListView();
		lv.setAdapter(new MyAdapter(this, data));
            }
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    super.onListItemClick(l, v, position, id);
    s=(Button)v.findViewById(R.id.delete);
    s1=(Button)v.findViewById(R.id.edit);
    s2=(Button)v.findViewById(R.id.active);
    t1=(TextView)v.findViewById(R.id.label);
    t2=(TextView)v.findViewById(R.id.activate);
    if ((s.getVisibility()==View.VISIBLE)){
    	s.setVisibility(View.GONE);
    	s1.setVisibility(View.GONE);
    	s2.setVisibility(View.GONE);
    }
    else{
    	s.setVisibility(View.VISIBLE);
    	s1.setVisibility(View.VISIBLE);
    	s2.setVisibility(View.VISIBLE);
    	s.setFocusable(false);
    	s1.setFocusable(false);
    	s2.setFocusable(false);
    	l.hasFocus();
    	s2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String st = t1.getText().toString();
				db.deactivateProfile(activeProfile);
				db.activateProfile(st);
				activeProfile=st;
				Contact val=db.getCont(st);
				chkProfile(val);
				
				Intent i = new Intent(Profiles.this, TabView.class);
	    		startActivity(i);
			}
		});
    	s.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String st = t1.getText().toString();
				if(st.equalsIgnoreCase(activeProfile))
					Toast.makeText(getApplicationContext(), "Active profile can't be deleted.", Toast.LENGTH_SHORT).show();
				else
					db.delProfile(st);
				Intent i = new Intent(Profiles.this, TabView.class);
	    		startActivity(i);
			}
		});
    	s1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String st = t1.getText().toString();
				// TODO Auto-generated method stub
				Intent i = new Intent(Profiles.this, EditProfile.class);
				i.putExtra("the text", st);
				startActivity(i);
				
			}
		});
    	}}
    public void chkProfile(Contact val){
    	AudioManager am;
		am= (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
		if(val.mode.equals("V"))
			am.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
		else if(val.mode.equals("N"))
			am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		else if(val.mode.equals("S"))
			am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		am.setStreamVolume(AudioManager.STREAM_RING, val.ringvol, AudioManager.FLAG_ALLOW_RINGER_MODES|AudioManager.FLAG_PLAY_SOUND);
		if(val.auto.equalsIgnoreCase("1")){
		PhoneCallListener phoneListener = new PhoneCallListener();
	    TelephonyManager telephonyManager = (TelephonyManager) this
	            .getSystemService(Context.TELEPHONY_SERVICE);
	    telephonyManager.listen(phoneListener,
	            PhoneStateListener.LISTEN_CALL_STATE);}
		else{
			PhoneCallListener phoneListener = new PhoneCallListener();
		    TelephonyManager telephonyManager = (TelephonyManager) this
		            .getSystemService(Context.TELEPHONY_SERVICE);
		    telephonyManager.listen(phoneListener,
		            PhoneStateListener.LISTEN_NONE);}
		
		android.provider.Settings.System.putInt(this.getContentResolver(),  
				  android.provider.Settings.System.SCREEN_BRIGHTNESS,val.bright);
		if(val.wifi.equalsIgnoreCase("O"))
			wfm.setWifiEnabled(true);
		else if (val.wifi.equalsIgnoreCase("N"))
			wfm.setWifiEnabled(false);
		
		if(adapter != null) {
		    if(val.btooth.equalsIgnoreCase("N")) {
		        adapter.disable();
		    } else if (val.btooth.equalsIgnoreCase("O")){
		        adapter.enable();
		    } 
		    } 
		
    }
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId()==R.id.all){
			Intent i = new Intent(Profiles.this, AddProfile.class);
			startActivity(i);
			
		}
		else if(v.getId()==R.id.appk){
			
			Toast.makeText(getApplicationContext(), "Background Applications Killed.", Toast.LENGTH_SHORT).show();
			
			List<ApplicationInfo> packages;
		    PackageManager pm;
		    pm = getPackageManager();
		    //get a list of installed apps.
		    packages = pm.getInstalledApplications(0);

		    ActivityManager mActivityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);

		   for (ApplicationInfo packageInfo : packages) {
		        if((packageInfo.flags & ApplicationInfo.FLAG_SYSTEM)==1)continue;
		        if(packageInfo.packageName.equals("example.com.smartactions")) continue;
		        mActivityManager.killBackgroundProcesses(packageInfo.packageName);
		   }      
		   }      
		
	}
	}

