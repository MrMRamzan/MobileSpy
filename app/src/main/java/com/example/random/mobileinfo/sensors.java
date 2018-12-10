package com.example.random.mobileinfo;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class sensors extends AppCompatActivity {

    SensorManager sensorManager;
    List<Sensor> sensorList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        listView = (ListView) findViewById(R.id.listViewSensors);
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        listView.setAdapter(new ArrayAdapter<Sensor>(this, android.R.layout.simple_list_item_1, sensorList));
    }
}
