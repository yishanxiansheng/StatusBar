package com.noodle.statusbar.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * 三阶贝塞尔曲线画圆
 *
 * @author heshufan
 * @date 2019-11-19
 */
public class CircleView extends View {

    private static final String TAG = "CircleView";
    /**
     * 圆心
     */
    private PointF mCenter = new PointF(300, 300);
    /**
     * 半径
     */
    private int radius = 200;
    /**
     * 画圆的比例
     */
    private float rate = 0.55f;

    /**
     * 完整的路径
     */
    private Path mPath;

    private Paint mPaint;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.RED);

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //弧线1
        mPath.moveTo(mCenter.x, mCenter.y - radius);
        mPath.cubicTo(rate * radius + mCenter.x, mCenter.y - radius, mCenter.x + radius,
                mCenter.y - radius * rate, mCenter.x + radius, mCenter.y);

        //弧线2
        mPath.moveTo(mCenter.x + radius, mCenter.y);
        mPath.cubicTo(mCenter.x + radius, mCenter.y + radius * rate,
                mCenter.x + radius * rate, mCenter.y + radius,
                mCenter.x, mCenter.y + radius);

        //弧线3
        mPath.moveTo(mCenter.x, mCenter.y + radius);
        mPath.cubicTo(mCenter.x - radius * rate, mCenter.y + radius,
                mCenter.x - radius, mCenter.y + radius * rate,
                mCenter.x - radius, mCenter.y);

        //弧线4
        mPath.moveTo(mCenter.x - radius, mCenter.y);
        mPath.cubicTo(mCenter.x - radius, mCenter.y - rate * radius,
                mCenter.x - radius * rate, mCenter.y - radius,
                mCenter.x, mCenter.y - radius);

        canvas.drawPath(mPath, mPaint);
    }
}
