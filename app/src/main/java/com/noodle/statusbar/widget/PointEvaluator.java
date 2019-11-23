package com.noodle.statusbar.widget;

import android.animation.TypeEvaluator;

import com.noodle.statusbar.Point;

/**
 * @author heshufan
 * @date 2019-11-23
 */
class PointEvaluator implements TypeEvaluator<Point> {
    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        int radius = (int) (startValue.getRadius() + fraction*(endValue.getRadius()- startValue.getRadius()));
        return new Point(radius);
    }
}
