package com.android.azimuth;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class AzimuthActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

     public void onStartButtonClick(View view) {
        final MapView mapEngine = (MapView) findViewById(R.id.mapEngine);

        float[] dataSource = new float[]{1,2,5,8,4,5,3,6,7,5,8,1,3,5,-2,-3};
        mapEngine.setDataSource(dataSource);
        mapEngine.invalidate();
    }
}
