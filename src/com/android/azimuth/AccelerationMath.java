package com.android.azimuth;

import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: odats
 * Date: 7/9/11
 * Time: 7:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccelerationMath {

    public static PositionState calculate(float ax, float ay, float azimuth, double timeDif, PositionState prevPositionState) {
        PositionState currentPositionState = new PositionState();

        currentPositionState.setAzimuth(azimuth);

        // calculation of distance, note next values are proections, to calculate real distance use d = sqrt(distanceProectionX*distanceProectionX + accelerationY*accelerationY)
        double distanceProectionX = GetDistanceProection(prevPositionState.getStartingSpeedX(), timeDif, ax);
        double distanceProectionY = GetDistanceProection(prevPositionState.getStartingSpeedY(), timeDif, ay);

        // to get new cordinates we should use direction (angle) to proect distance
        currentPositionState.setCoordinateX(GetXCoordinate(prevPositionState.getCoordinateX(), currentPositionState.getAzimuth(), distanceProectionX));
        currentPositionState.setCoordinateY(GetYCoordinate(prevPositionState.getCoordinateY(), currentPositionState.getAzimuth(), distanceProectionY));

        currentPositionState.setStartingSpeedX(GetEndSpeed(prevPositionState.getStartingSpeedX(), timeDif, ax));
        currentPositionState.setStartingSpeedY(GetEndSpeed(prevPositionState.getStartingSpeedY(), timeDif, ay));

        // System.out.println("X = " + distanceProectionX + " Y =  " + distanceProectionY + " Acceleration = " + Math.sqrt(positionDto.getAx()*positionDto.getAx() + positionDto.getAy()*positionDto.getAy()));
        // System.out.println(prevPositionState.getCoordinateX() + " -> " + currentPositionState.getCoordinateX() + " difference = " +  (currentPositionState.getCoordinateX() -prevPositionState.getCoordinateX()) );
        // System.out.println(prevPositionState.getCoordinateY() + " -> " + currentPositionState.getCoordinateY());

        return currentPositionState;
    }

    public static class PositionState {

        private double startingSpeedX = 0;
        private double startingSpeedY = 0;
        private double coordinateX = 0;
        private double coordinateY = 0;
        private double azimuth = 30;

        public double getStartingSpeedX() {
            return startingSpeedX;
        }

        public void setStartingSpeedX(double startingSpeedX) {
            this.startingSpeedX = startingSpeedX;
        }

        public double getStartingSpeedY() {
            return startingSpeedY;
        }

        public void setStartingSpeedY(double startingSpeedY) {
            this.startingSpeedY = startingSpeedY;
        }

        public double getCoordinateX() {
            return coordinateX;
        }

        public void setCoordinateX(double coordinateX) {
            this.coordinateX = coordinateX;
        }

        public double getCoordinateY() {
            return coordinateY;
        }

        public void setCoordinateY(double coordinateY) {
            this.coordinateY = coordinateY;
        }

        public double getAzimuth() {
            return azimuth;
        }

        public void setAzimuth(double azimuth) {
            this.azimuth = azimuth;
        }
    }

    private static double GetDistanceProection(double startingSpeed, double t, double acceleration) {
        return startingSpeed + 0.5 * acceleration * t * t;
    }

    private static double GetEndSpeed(double startingSpeed, double t, double acceleration) {
        return startingSpeed + acceleration * t;
    }

    private static double GetXCoordinate(double startingXCoordinate, double azimuth, double distanceXProection) {
        double coordinate = 0;
        if (azimuth > 0 && azimuth < 180) {
            coordinate = startingXCoordinate + distanceXProection;
        }

        if (azimuth > 180 && azimuth < 360) {
            coordinate = startingXCoordinate - distanceXProection;
        }
        return coordinate;
    }

    private static double GetYCoordinate(double startingYCoordinate, double azimuth, double distanceYProection) {
        double coordinate = 0;
        if ((azimuth > 0 && azimuth < 90) || (azimuth > 270 && azimuth <
                360)) {
            coordinate = startingYCoordinate + distanceYProection;
        }

        if (azimuth > 90 && azimuth < 270) {
            coordinate = startingYCoordinate - distanceYProection;
        }
        return coordinate;
    }
} 