package com.noodle.statusbar.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.CpuUsageInfo;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * 二阶动态贝塞尔曲线
 *
 * @author heshufan
 * @date 2019-11-19
 */
public class DynamicBezierView extends View {
    private Path mPath;
    private Paint mPaint;
    private PointF mStartPoint;
    private PointF mControlPoint;
    private PointF mEndPoint;

    public DynamicBezierView(Context context) {
        this(context, null);
    }

    public DynamicBezierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DynamicBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(20);

        mStartPoint = new PointF(100, 400);
        mEndPoint = new PointF(400, 400);
        mControlPoint = new PointF(20, 20);

        mPath = new Path();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(20);
        canvas.drawPoint(mStartPoint.x, mStartPoint.y, mPaint);
        canvas.drawPoint(mEndPoint.x, mEndPoint.y, mPaint);
        canvas.drawPoint(mControlPoint.x, mControlPoint.y, mPaint);

        mPaint.setStrokeWidth(5);
        canvas.drawLine(mStartPoint.x, mStartPoint.y, mControlPoint.x, mControlPoint.y, mPaint);
        canvas.drawLine(mControlPoint.x, mControlPoint.y, mEndPoint.x, mEndPoint.y, mPaint);

        mPath.moveTo(mStartPoint.x, mStartPoint.y);
        mPath.quadTo(mControlPoint.x, mControlPoint.y, mEndPoint.x, mEndPoint.y);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5);
        canvas.drawPath(mPath, mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mControlPoint.x = event.getX() - 200;
        mControlPoint.y = event.getY() + 200;
        invalidate();
        return true;
    }
}
