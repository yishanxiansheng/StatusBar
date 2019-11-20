package com.noodle.statusbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
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
    //绘制圆
    private Paint mCirclePaint;

    private Path mCirclePath;
    private PathMeasure mCirclePathMeasure;

    //绘制线段
    private Paint mTrickPaint;
    private Path mTrickPath;
    private PathMeasure mTrickPathMeasure;
    private PathEffect mPathEffect;

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
        mCirclePaint = new Paint();
        mCirclePaint.setColor(Color.RED);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(5);
        mCirclePaint.setAntiAlias(true);

        mTrickPaint = new Paint();
        mTrickPaint.setColor(Color.RED);
        mTrickPaint.setStyle(Paint.Style.STROKE);
        mTrickPaint.setStrokeWidth(5);
        mTrickPaint.setAntiAlias(true);

        mCirclePath = new Path();
        mCirclePath.addCircle(400, 400, 100, Path.Direction.CW);
        mDesPath = new Path();

        mTrickPath = new Path();
        mTrickPath.reset();
        mTrickPath.moveTo(100, 100);
        mTrickPath.lineTo(100, 500);
        mTrickPath.lineTo(400, 300);
        mTrickPath.close();


        mCirclePathMeasure = new PathMeasure();
        mCirclePathMeasure.setPath(mCirclePath, false);

        mTrickPathMeasure = new PathMeasure();
        mTrickPathMeasure.setPath(mTrickPath, false);

        mAnimator = ValueAnimator.ofFloat(0, 1);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float factor = 0.4f;
                float x = animation.getAnimatedFraction();
                mLength = (float) (pow(2, -10 * x) * sin((x - factor / 4) * (2 * PI) / factor) + 1);

                float fraction = animation.getAnimatedFraction();
                mPathEffect = new DashPathEffect(new float[]{mTrickPathMeasure.getLength(), mTrickPathMeasure.getLength()},
                        fraction * mTrickPathMeasure.getLength());

                invalidate();
            }
        });
        mAnimator.setDuration(3000);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mDesPath.reset();
        mDesPath.lineTo(0, 0);
        mCirclePathMeasure.getSegment(0, mLength * mCirclePathMeasure.getLength(), mDesPath, true);
        canvas.drawPath(mDesPath, mCirclePaint);

        //绘制三角形
        mTrickPaint.reset();
        mTrickPaint.setColor(Color.BLACK);
        mTrickPaint.setStyle(Paint.Style.STROKE);
        mTrickPaint.setStrokeWidth(5);
        mTrickPaint.setAntiAlias(true);
        canvas.drawPath(mTrickPath, mTrickPaint);

        //动态三角形
        mTrickPaint.setColor(Color.RED);
        mTrickPaint.setPathEffect(mPathEffect);
        canvas.drawPath(mTrickPath, mTrickPaint);
    }
}
