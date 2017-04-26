package com.renanrhoden.atividade3;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import static java.lang.Math.abs;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private EditText x;
    private EditText y;
    private EditText z;


    private float lastX;

    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        x = (EditText) findViewById(R.id.x);
        y = (EditText) findViewById(R.id.y);
        z = (EditText) findViewById(R.id.z);

        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), sensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float sensorX = event.values[0];
            float sensorY = event.values[1];
            float sensorZ = event.values[2];
            Log.i("X", String.valueOf(sensorX));
            Log.i("Y", String.valueOf(sensorY));
            Log.i("Z", String.valueOf(sensorZ));

            x.setText(String.valueOf(sensorX));
            y.setText(String.valueOf(sensorY));
            z.setText(String.valueOf(sensorZ));

            if (Math.abs(sensorX - lastX) > 30){
                sensorManager.unregisterListener(this);
                startActivity(new Intent(MainActivity.this, MessageActivity.class));
            }

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {



    }
}
