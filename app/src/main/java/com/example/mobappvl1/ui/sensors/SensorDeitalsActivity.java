package com.example.mobappvl1.ui.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.mobappvl1.R;

import java.util.Date;
import java.util.List;

public class SensorDeitalsActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = SensorDeitalsActivity.class.getName();
    private Sensor sensor;
    private TextView tvSensorName,tvSensorValues,tvSensorAccuracy,tvSensorTimestamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_deitals);
        Intent intent = getIntent();
        String name = intent.getStringExtra(SensorFragment.SENSOR_NAME);
        SensorManager mSensorManager;
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        sensor = null;
        for (int i = 0; i < deviceSensors.size() ; i++) {
            if (deviceSensors.get(i).getName().equals(name)) {
                sensor = deviceSensors.get(i);
            }
        }

        if (sensor != null) {
            mSensorManager.registerListener(this,sensor,SensorManager.SENSOR_STATUS_ACCURACY_HIGH);
        }

        tvSensorName = findViewById(R.id.sensorName);
        tvSensorValues =findViewById(R.id.tvSensorValue);
        tvSensorAccuracy = findViewById(R.id.tvSensorAccuracy);
        tvSensorTimestamp=findViewById(R.id.tvSensorLastChange);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float[] values = event.values;
        int accuracy = event.accuracy;
        StringBuffer valueString = new StringBuffer();
        for (int i = 0; i <values.length ; i++) {
            valueString.append(values[i]+"\n");
        }
        Date timeStamp = new Date(event.timestamp/1000000);
        tvSensorName.setText(event.sensor.getName());
        tvSensorValues.setText(valueString.toString());
        tvSensorAccuracy.setText(""+accuracy);
        tvSensorTimestamp.setText(timeStamp.toString());
        Log.d(TAG,"Wert geändert!");

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}