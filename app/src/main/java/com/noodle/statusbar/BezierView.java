package com.noodle.statusbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author heshufan
 * @desc :
 * @date 2019/11/18
 */
public class BezierView extends View {
    private Paint mPaint;
    private Path mBezierPath;
    private Path mPointPath;

    /**
     * 起始点
     */
    private Point mStartPoint;

    /**
     * 控制点
     */
    private Point mControlPoint;

    /**
     * 结束点
     */
    private Point mEndPoint;

    public BezierView(Context context) {
        this(context,null);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);


        mBezierPath = new Path();
        mPointPath = new Path();

        //起始点
        mStartPoint = new Point();
        mStartPoint.set(100, 300);

        //控制点
        mControlPoint = new Point();
        mControlPoint.set(300, 100);

        //终点
        mEndPoint = new Point();
        mEndPoint.set(500, 500);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //贝塞尔 path移动到起始点
        mBezierPath.moveTo(mStartPoint.x, mStartPoint.y);
        //设置控制点 终点
        mBezierPath.quadTo(mControlPoint.x, mControlPoint.y, mEndPoint.x, mEndPoint.y);

        //dx1,dy1 是相对于终点的作为控制点  dx2 ,dy2 是相对于终点的位移的点作为终点
        mBezierPath.rQuadTo(200,300,400,-200);

        //连接线
        mPointPath.moveTo(mStartPoint.x, mStartPoint.y);
        mPointPath.lineTo(mControlPoint.x, mControlPoint.y);
        mPointPath.lineTo(mEndPoint.x, mEndPoint.y);

        //绘制起始点、控制点、终点的连线
        canvas.drawPath(mPointPath, mPaint);

        //绘制贝塞尔
        mPaint.setColor(Color.RED);
        canvas.drawPath(mBezierPath, mPaint);


    }
}
