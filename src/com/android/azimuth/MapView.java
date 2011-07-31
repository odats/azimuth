package com.android.azimuth;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by IntelliJ IDEA.
 * User: odats
 * Date: 7/31/11
 * Time: 7:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class MapView extends View {
    private final int scale = 10;

    private float[] dataSource;

    public void setDataSource(float[] dataSource) {
        this.dataSource = dataSource;
    }

    private Paint mPaint = new Paint();


    /**
     * Construct object, initializing with any attributes we understand from a
     * layout file. These attributes are defined in
     * SDK/assets/res/any/classes.xml.
     *
     * @see android.view.View#View(android.content.Context, android.util.AttributeSet)
     */
    public MapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);

        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeJoin(Paint.Join.BEVEL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GREEN);

        // validate data source
        if (dataSource == null) {
            canvas.drawText("Data source is empty", 0, 10, mPaint);
            return;
        } else if (dataSource.length < 4) {
            canvas.drawText("Data source has less than 4 elements", 0, 10, mPaint);
            return;
        } else if (dataSource.length % 4 != 0) {
            canvas.drawText("Data source has wrong number of elements : " + dataSource.length, 0, 10, mPaint);
            return;
        }

        // position on the centre
        int w = canvas.getWidth();
        int h = canvas.getHeight();
        int cx = w / 2;
        int cy = h / 2;
        canvas.translate(cx, cy);

        // draw path
        for (int n = 2; n < dataSource.length; n += 2) {
            canvas.drawLine(getScaledPoint(dataSource[n - 2]), getScaledPoint(dataSource[n - 1]),
                    getScaledPoint(dataSource[n]), getScaledPoint(dataSource[n + 1]), mPaint);
        }
    }

    protected float getScaledPoint(float input) {
        return input * scale;
    }
}
