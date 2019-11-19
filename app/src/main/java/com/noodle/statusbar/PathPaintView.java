package com.noodle.statusbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import static java.lang.Math.PI;
import static java.lang.Math.pow;
import static java.lang.Math.sin;

/**
 * @author heshufan
 * @date 2019-11-19
 */
public class PathPaintView extends View {
    private Paint mPaint;
    private Path mPath;
    private PathMeasure mPathMeasure;
    private ValueAnimator mAnimator;
    private float mLength;
    private Path mDesPath;

    public PathPaintView(Context context) {
        this(context, null);
    }

    public PathPaintView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathPaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

        mPath = new Path();
        mPath.addCircle(400, 400, 100, Path.Direction.CW);
        mDesPath = new Path();

        mPathMeasure = new PathMeasure();
        mPathMeasure.setPath(mPath, false);

        mAnimator = ValueAnimator.ofFloat(0, 1);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float factor = 0.4f;
                float x = animation.getAnimatedFraction();
                mLength = (float) (pow(2, -10 * x) * sin((x - factor / 4) * (2 * PI) / factor) + 1);
                //mLength = animation.getAnimatedFraction();
                invalidate();
            }
        });
        mAnimator.setDuration(2000);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mDesPath.reset();
        mDesPath.lineTo(0, 0);
        mPathMeasure.getSegment(0, mLength * mPathMeasure.getLength(), mDesPath, true);
        canvas.drawPath(mDesPath, mPaint);
    }
}
