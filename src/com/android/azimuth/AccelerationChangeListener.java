package com.android.azimuth;

/**
 * Created by IntelliJ IDEA.
 * User: odats
 * Date: 8/2/11
 * Time: 10:42 PM
 * To change this template use File | Settings | File Templates.
 */
public interface AccelerationChangeListener {

    /**
     * Returns coordinates
     */
    public void onAccelerationChange(float ax, float ay, float x1, float y1, float x2, float y2);
}
