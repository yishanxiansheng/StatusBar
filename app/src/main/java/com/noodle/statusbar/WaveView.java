package com.noodle.statusbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * @author heshufan
 * @desc : 贝塞尔曲线画波浪
 * @date 2019/11/18
 */
public class WaveView extends View {

    private int mWidth;
    private int mHeight;
    private Paint mPaint;
    private Path mPath;

    /**
     * 波浪的半径
     */
    private int radius = 600;

    /**
     * 起始点X轴移动动画，线性插值器
     */
    private ValueAnimator mAnimator;

    /**
     * X轴偏移量
     */
    private int mOffsetX = 0;

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        int halfItem = radius / 2;
        mPath.moveTo(-radius + mOffsetX, halfItem);
        for (int i = 0; i < radius + getWidth(); i += radius) {
            mPath.rQuadTo(halfItem / 2, -100, halfItem, 0);
            mPath.rQuadTo(halfItem / 2, 100, halfItem, 0);
        }

        //闭合图像，并填充
        mPath.lineTo(mWidth,mHeight);
        mPath.lineTo(0,mHeight);
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(5);
        mPath = new Path();
        mAnimator = ValueAnimator.ofInt(0, radius);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mOffsetX = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.setDuration(1000);
        mAnimator.setRepeatCount(-1);
        mAnimator.start();
    }
}
