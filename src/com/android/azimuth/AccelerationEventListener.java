package com.android.azimuth;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: odats
 * Date: 8/2/11
 * Time: 10:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccelerationEventListener implements SensorEventListener {

    private long timestamp = 0;

    private AccelerationChangeListener eventListener;

    private LinkedList<AccelerationMath.PositionState> positionStates = new LinkedList<AccelerationMath.PositionState>();

    public AccelerationEventListener(AccelerationChangeListener eventListener) {
        this.eventListener = eventListener;
    }

    public AccelerationEventListener() {
    }

    {
        positionStates.add(new AccelerationMath.PositionState());
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];


        // alpha is calculated as t / (t + dT)
        // with t, the low-pass filter's time-constant
        // and dT, the event delivery rate
      /*  double alpha = 0.8;

        double[] gravity = new double[]{0, 0, 0};
        double[] linear_acceleration = new double[]{0, 0, 0};

        gravity[0] = alpha * gravity[0] + (1 - alpha) * sensorEvent.values[0];
        gravity[1] = alpha * gravity[1] + (1 - alpha) * sensorEvent.values[1];
        gravity[2] = alpha * gravity[2] + (1 - alpha) * sensorEvent.values[2];

        linear_acceleration[0] = sensorEvent.values[0] - gravity[0];
        linear_acceleration[1] = sensorEvent.values[1] - gravity[1];
        linear_acceleration[2] = sensorEvent.values[2] - gravity[2];

        float x = (float) linear_acceleration[0];
        float y = (float) linear_acceleration[1];
        float z = (float) linear_acceleration[2];

        */
        // calculate time difference
        double timeDiffInSeconds;
        if(timestamp == 0){
            timeDiffInSeconds = 0.2;
        } else {
            timeDiffInSeconds = (sensorEvent.timestamp - timestamp) / 1000000000.0;
        }
        timestamp = sensorEvent.timestamp;

        AccelerationMath.PositionState prevState = positionStates.getLast();
        AccelerationMath.PositionState currentState = AccelerationMath.calculate(x, y, 30, timeDiffInSeconds, prevState);

        positionStates.addLast(currentState);

        eventListener.onAccelerationChange(x, y,
                (float) prevState.getCoordinateX(), (float) prevState.getCoordinateY(),
                (float) currentState.getCoordinateX(), (float) currentState.getCoordinateY());

        System.out.println(String.format("x : %1$f => %2$f / y : %3$f => %4$f",
                (float) prevState.getCoordinateX(), (float) currentState.getCoordinateX(),
                (float) prevState.getCoordinateY(), currentState.getCoordinateY()));

        positionStates.remove(0);
    }

    public void onAccuracyChanged(Sensor sensor, int i) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
