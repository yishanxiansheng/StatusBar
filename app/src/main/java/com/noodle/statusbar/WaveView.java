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

    private Paint mPaint;
    private Path mPath;

    /**
     * 波浪的半径
     */
    private int mWidth = 600;

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
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        int halfItem = mWidth / 2;
        mPath.moveTo(-mWidth + mOffsetX, halfItem);
        for (int i = 0; i < mWidth + getWidth(); i += mWidth) {
            mPath.rQuadTo(halfItem / 2, -100, halfItem, 0);
            mPath.rQuadTo(halfItem / 2, 100, halfItem, 0);
        }
        canvas.drawPath(mPath, mPaint);
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPath = new Path();
        mAnimator = ValueAnimator.ofInt(0, mWidth);
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
