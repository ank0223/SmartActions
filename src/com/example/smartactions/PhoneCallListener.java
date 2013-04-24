package com.example.smartactions;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.provider.CallLog.Calls;
import android.telephony.PhoneStateListener;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.webkit.WebView.FindListener;
import android.widget.EditText;
import android.widget.Toast;

public class PhoneCallListener extends PhoneStateListener{
	private boolean isPhoneCalling = false;
	Context context;
	EditText ed;
	String phone,msg;
    @Override
    public void onCallStateChanged(int state, String incomingNumber) {
    	
    	if (TelephonyManager.CALL_STATE_RINGING == state) {
            // active
            isPhoneCalling = true;
            Log.d(null, incomingNumber+"Ring");
        }
    	
    	else if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
            // active
           // isPhoneCalling = true;
            Log.d(null, incomingNumber+"Hook");
        }
    	else if (TelephonyManager.CALL_STATE_IDLE == state) {
        	if(isPhoneCalling){
        	Log.d(null, incomingNumber+"free");
        	 SmsManager sm = SmsManager.getDefault();
             sm.sendTextMessage(incomingNumber, null, "Sorry, I am busy. I will call you back once free!", null, null);
           isPhoneCalling=false;
           }
             // run when class initial and phone call ended, need detect flag
            // from CALL_STATE_OFFHOOK
/*
            if (isPhoneCalling) {

                Handler handler = new Handler();
                //Put in delay because call log is not updated immediately when state changed
                // The dialer takes a little bit of time to write to it 500ms seems to be enough
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // get start of cursor
                          Log.i("CallLogDetailsActivity", "Getting Log activity...");
                            String[] projection = new String[]{Calls.NUMBER};
                            Cursor cur = getContentResolver().query(Calls.CONTENT_URI, projection, null, null, Calls.DATE +" desc");
                            cur.moveToFirst();
                            phone = cur.getString(0);
                            msg="I might be driving or in a meeting. I will call when I get free.";
                            
                          SmsManager sm = SmsManager.getDefault();
                            sm.sendTextMessage(phone, null, msg, null, null);
						
                    }
                },500);
                isPhoneCalling = false;
            }
*/
        }
    }
}
