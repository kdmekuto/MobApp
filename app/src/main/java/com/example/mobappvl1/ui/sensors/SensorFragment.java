package com.example.mobappvl1.ui.sensors;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mobappvl1.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class SensorFragment extends Fragment {

    public static final String SENSOR_POSITION ="SENSOR_POSITION" ;
    public static final String SENSOR_NAME = "SENSOR_NAME";
    private SensorViewModel sensorViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sensorViewModel =
                new ViewModelProvider(this).get(SensorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_sensorliste, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        sensorViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        ListView listView = root.findViewById(R.id.listview);
        String[] senesorList = getSensorList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, senesorList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                String item = (String) parent.getItemAtPosition(i);
                Snackbar snackbar = Snackbar.make(view,
                        item + " an Position " + (i+1) + " wurde gedrückt. ",
                        Snackbar.LENGTH_LONG);
                snackbar.show();
                Intent intent = new Intent(getContext(), SensorDeitalsActivity.class);
                intent.putExtra(SENSOR_NAME,item);
                intent.putExtra(SENSOR_POSITION,i);
                startActivity(intent);

            }
        });
        return root;
    }

    private String[] getSensorList() {
        SensorManager mSensorManager;
        mSensorManager = (SensorManager) getContext().getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuffer stringBuffer = new StringBuffer();
        String[] sensors = new String[deviceSensors.size()];
        for (int i = 0; i < deviceSensors.size(); i++) {
            sensors[i] = deviceSensors.get(i).getName();
        }
        return sensors;
    }
}