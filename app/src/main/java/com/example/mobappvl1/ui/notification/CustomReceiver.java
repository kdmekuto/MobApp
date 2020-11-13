package com.example.mobappvl1.ui.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

    private static final String TAG = CustomReceiver.class.getName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();
        if (intentAction != null) {
            String toastMessage = "unknown intent action";
            switch (intentAction){
                case Intent.ACTION_POWER_CONNECTED:
                    toastMessage = "Power connected!";
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    toastMessage = "Power disconnected!";
                    break;
                case Intent.ACTION_SCREEN_OFF:
                    toastMessage = "Screen turned off!";
                    Log.d(TAG,"Screen wurde ausgeschaltet!");
                    break;
                case Intent.ACTION_SCREEN_ON:
                    toastMessage = "Screen turned on!";
                    Log.d(TAG,"Screen wurde angeschaltet!");
                    break;
            }

            //Display the toast.
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
        }

    }
}