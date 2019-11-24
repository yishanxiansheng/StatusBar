package com.noodle.statusbar;

import android.animation.Animator;
import android.animation.Keyframe;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewGroup添加删除子view时的动画
 *
 * @author heshufan
 * @date 2019-11-24
 */
public class ViewGropActivity extends AppCompatActivity {
    /**
     * 动态添加view的容器
     */
    private LinearLayout mViewContainer;

    private LayoutTransition transition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);
        mViewContainer = findViewById(R.id.view_container);

        customAnim();
        mViewContainer.setLayoutTransition(transition);
    }

    public void removeView(View view) {
        if (mViewContainer.getChildCount() > 0) {
            mViewContainer.removeViewAt(0);
        }
    }

    public void addView(View view) {
        Button button = new Button(this);
        button.setText("Button");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(40, 0, 0, 0);
        button.setLayoutParams(params);
        mViewContainer.addView(button, 0);
    }

    public void customAnim() {
        transition = new LayoutTransition();
        ObjectAnimator animOut = ObjectAnimator.ofFloat(null, "rotation", 0f, 90f, 0f);

        //子view消失时的动画LayoutTransition.DISAPPEARING
        transition.setAnimator(LayoutTransition.DISAPPEARING, animOut);
        //子view出现时的动画
        transition.setAnimator(LayoutTransition.APPEARING, animOut);

        //构造PropertyValuesHolder必须写
        PropertyValuesHolder pvhLeft = PropertyValuesHolder.ofInt("left",0,100,0);
        PropertyValuesHolder pvhTop = PropertyValuesHolder.ofInt("top",1,1);

        //添加子view已经存在的view的动画
        PropertyValuesHolder pvhScaleX = PropertyValuesHolder.ofFloat("ScaleX",1f,9f,1f);
        Animator changeAppearAnimator = ObjectAnimator.ofPropertyValuesHolder(mViewContainer, pvhLeft,pvhTop,pvhScaleX);
        transition.setAnimator(LayoutTransition.CHANGE_APPEARING,changeAppearAnimator);

        //删除子view已经存在的view的动画
        PropertyValuesHolder outLeft = PropertyValuesHolder.ofInt("left",0,0);
        PropertyValuesHolder outTop = PropertyValuesHolder.ofInt("top",0,0);
        Keyframe frame0 = Keyframe.ofFloat(0f, 0);
        Keyframe frame1 = Keyframe.ofFloat(0.1f, -20f);
        Keyframe frame2 = Keyframe.ofFloat(0.2f, 20f);
        Keyframe frame3 = Keyframe.ofFloat(0.3f, -20f);
        Keyframe frame4 = Keyframe.ofFloat(0.4f, 20f);
        Keyframe frame5 = Keyframe.ofFloat(0.5f, -20f);
        Keyframe frame6 = Keyframe.ofFloat(0.6f, 20f);
        Keyframe frame7 = Keyframe.ofFloat(0.7f, -20f);
        Keyframe frame8 = Keyframe.ofFloat(0.8f, 20f);
        Keyframe frame9 = Keyframe.ofFloat(0.9f, -20f);
        Keyframe frame10 = Keyframe.ofFloat(1, 0);
        PropertyValuesHolder mPropertyValuesHolder = PropertyValuesHolder.ofKeyframe("rotation",frame0,frame1,frame2,frame3,
                frame4,frame5,frame6,frame7,frame8,frame9,frame10);
        ObjectAnimator mObjectAnimatorChangeDisAppearing = ObjectAnimator.ofPropertyValuesHolder(this, outLeft,outTop, mPropertyValuesHolder);
        transition.setAnimator(LayoutTransition.CHANGE_DISAPPEARING, mObjectAnimatorChangeDisAppearing);
    }
}
