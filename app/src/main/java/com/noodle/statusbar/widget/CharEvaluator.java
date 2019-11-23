package com.noodle.statusbar.widget;

import android.animation.TypeEvaluator;

/**
 * @author heshufan
 * @date 2019-11-23
 */
public class CharEvaluator implements TypeEvaluator<Character> {

    @Override
    public Character evaluate(float fraction, Character startValue, Character endValue) {
        int startInt = (int) startValue;
        int endInt = (int) endValue;
        int curInt = (int) (startInt+fraction*(endInt-startInt));
        return (char)curInt;
    }
}
