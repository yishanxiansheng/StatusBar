package com.noodle.statusbar.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

import com.noodle.statusbar.Point;

/**
 * 利用动画实现球的弹效果
 * @author heshufan
 * @date 2019-11-23
 */
public class WaveCircleView extends View {
    private Paint mPaint;
    private Point mcurPoint;

    public WaveCircleView(Context context) {
        this(context, null);
    }

    public WaveCircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mcurPoint = new Point(20);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPaint != null) {
            canvas.drawCircle(300, 300, mcurPoint.getRadius(), mPaint);
        }
    }

    public void doPointAnim() {
        ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(), new Point(10), new Point(100));
        animator.setDuration(1000);
        animator.setInterpolator(new BounceInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mcurPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }

    /**
     * 利用ObjectAnimation或PropertyValuesHolder实现弹弹动画 这里的"PointRadius"必须和构建ObjectAnimation时第二个参数保持一致
     * @param radius 半径
     */
    public void setPointRadius(int radius) {
        mcurPoint.setRadius(radius);
        invalidate();
    }
}