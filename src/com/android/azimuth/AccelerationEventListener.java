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

        double timeDiffInSeconds = (double)sensorEvent.timestamp / 1000000000000000.0;

        //System.out.println(String.format("x is: %f / y is: %f / z is: %f / timeDif is: %f", x, y, z, timeDiffInSeconds));


        AccelerationMath.PositionState prevState = positionStates.getLast();
        AccelerationMath.PositionState currentState = AccelerationMath.calculate(x, y, 30, timeDiffInSeconds, prevState);

        positionStates.addLast(currentState);

        eventListener.onAccelerationChange(
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
