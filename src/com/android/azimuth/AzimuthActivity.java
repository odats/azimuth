package com.android.azimuth;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class AzimuthActivity extends Activity implements AccelerationChangeListener {

    private MapView mapEngine;
    private TextView infoLabel;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private AccelerationEventListener accelerationEventListener;

    LinkedList<Float> movement = new LinkedList<Float>();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mapEngine = (MapView) findViewById(R.id.mapEngine);
        infoLabel = (TextView) findViewById(R.id.infoLabel);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // Sensor.TYPE_LINEAR_ACCELERATION
        // http://developer.android.com/reference/android/hardware/SensorEvent.html#values
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        accelerationEventListener = new AccelerationEventListener(this);
    }

    public void onStartButtonClick(View view) {
        mSensorManager.unregisterListener(accelerationEventListener, mAccelerometer);
        movement = new LinkedList<Float>();
        mSensorManager.registerListener(new AccelerationEventListener(this), mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(accelerationEventListener, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(accelerationEventListener, mAccelerometer);
    }

    public void onAccelerationChange(float x1, float y1, float x2, float y2) {
		infoLabel.setText(String.format("x : %f => %f / y : %f => %f", x1, x2, y1, y2));

       /* movement.addLast(x1);
        movement.addLast(y1);
        movement.addLast(x2);
        movement.addLast(y2);*/

        Collections.addAll(movement, x1, y1, x2, y2);


        mapEngine.setDataSource(movement);
        mapEngine.invalidate();
    }
}
