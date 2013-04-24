package com.example.smartactions;

import java.util.Set;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Events extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events);
        CalendarContentResolver c=new CalendarContentResolver(this);
        Set<String> cal=c.getCalendars();
        Log.d(null,cal.toArray().toString());
    }
}    