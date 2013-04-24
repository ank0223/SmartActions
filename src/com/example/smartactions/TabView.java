package com.example.smartactions;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
 
@SuppressWarnings("deprecation")
public class TabView extends TabActivity {
    // TabSpec Names
    private static final String PROFILES = "Profiles";
    private static final String EXTRA = "Extra";
    //private static final String PROFILE_SPEC= "Settings";
    //private static final String SPEND_SPEC= "Summary";
    Bundle b;
    TabHost tabHost;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabview);
 
        tabHost = getTabHost();
 
        TabSpec profileSpec = tabHost.newTabSpec(PROFILES);
        profileSpec.setIndicator(PROFILES, getResources().getDrawable(R.drawable.recent));
        Intent inboxIntent = new Intent(TabView.this,Profiles.class);
        profileSpec.setContent(inboxIntent);
 
       
        TabHost.TabSpec extraSpec = tabHost.newTabSpec(EXTRA);
        extraSpec.setIndicator(EXTRA, getResources().getDrawable(R.drawable.location));
        Intent profileIntent = new Intent(TabView.this, Extra.class);
        extraSpec.setContent(profileIntent);
 
        /*
        TabHost.TabSpec profileSpec = tabHost.newTabSpec(PROFILE_SPEC);
        profileSpec.setIndicator(PROFILE_SPEC, getResources().getDrawable(R.drawable.settings));
        Intent chkIntent = new Intent(TabView.this, profile.class);
        chkIntent.putExtra("the text", name);
        profileSpec.setContent(chkIntent);
        
        
        TabHost.TabSpec p = tabHost.newTabSpec(SPEND_SPEC);
        p.setIndicator(SPEND_SPEC, getResources().getDrawable(R.drawable.summary));
        Intent c= new Intent(TabView.this, Intro.class);
        c.putExtra("the text", name);
        p.setContent(c);
        */
        // Adding all TabSpec to TabHost
        tabHost.addTab(profileSpec); 
        tabHost.addTab(extraSpec);
        //tabHost.addTab(profileSpec);
        setTabColor(tabHost);
        tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				// TODO Auto-generated method stub
				 if(tabId.equals(PROFILES))
	                {
	                	Profiles.self.onCreate(b);
	                	setTabColor(tabHost);
	                }
	                else if(tabId.equals(EXTRA)){
	                	Extra.self.onCreate(b);
	                	setTabColor(tabHost);
	                }
			}
        });


    }
        
    public static void setTabColor(TabHost tabhost) {
        for(int i=0;i<tabhost.getTabWidget().getChildCount();i++)
        {
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#08963a")); //unselected
        }
        tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#178332")); // selected
    }
    }


