package com.example.smartactions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

public class UserpageActivity extends Activity {
	Button xpence,conti;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TranslateAnimation as= new TranslateAnimation(0,0,100, 0);
    	AlphaAnimation al = new AlphaAnimation(0, 1);
    	al.setDuration(3000);
    	al.setFillAfter(true);
    	as.setDuration(1500);
    	as.setFillAfter(true);
        setContentView(R.layout.userpage);
        xpence = (Button)findViewById(R.id.Xpence);
        xpence.startAnimation(al);
        conti = (Button)findViewById(R.id.conti);
        conti.startAnimation(as);
        conti.setFocusable(true);
        conti.setFocusableInTouchMode(true);
        conti.requestFocus();
    }
    public void onClick(View v)
    {
    	if(v.getId()==R.id.conti)
    	{
    		Intent i = new Intent(UserpageActivity.this,TabView.class);
    		startActivity(i);
    	}
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.userpage, menu);
        return true;
    }
}
