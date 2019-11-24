package com.noodle.statusbar;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.noodle.statusbar.widget.CharEvaluator;
import com.noodle.statusbar.widget.WaveCircleView;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout titleview;
    /**
     * 分享按钮
     */
    private Button showShareDialogBtn;
    /**
     * 字母A到Z的变化
     */
    private TextView mTv;
    /**
     * 弹弹效果的圆形
     */
    private WaveCircleView mWaveCircleView;
    /**
     * 电话的震动的动画
     */
    private ImageView mTelephoneView;

    /**
     * 按钮弹出的动画
     */
    private Button mMenuButton;
    private Button mItemButton1;
    private Button mItemButton2;
    private Button mItemButton3;
    private Button mItemButton4;
    private Button mItemButton5;
    private boolean mIsMenuOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusBar(this);

        initView();
        showShareDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShareDialog();
                showObjectAnimation();
                //mWaveCircleView.doPointAnim();
                doPointObjectAnim();
                doTelephoneAnim();
            }
        });
        setTitlePadding();
    }

    private void initView() {
        titleview = findViewById(R.id.titleName);
        mTv = findViewById(R.id.animation_object);
        mWaveCircleView = findViewById(R.id.wave_circle_view);
        showShareDialogBtn = findViewById(R.id.show_dialog);
        mTelephoneView = findViewById(R.id.telephone);

        mMenuButton = findViewById(R.id.btn_main);
        mMenuButton.setOnClickListener(this);
        mItemButton1 = findViewById(R.id.btn_1);
        mItemButton1.setOnClickListener(this);
        mItemButton2 = findViewById(R.id.btn_2);
        mItemButton2.setOnClickListener(this);
        mItemButton3 = findViewById(R.id.btn_3);
        mItemButton3.setOnClickListener(this);
        mItemButton4 = findViewById(R.id.btn_4);
        mItemButton4.setOnClickListener(this);
        mItemButton5 = findViewById(R.id.btn_5);
        mItemButton5.setOnClickListener(this);
    }

    /**
     * 利用ObjectAnimation与PropertyValuesHolder实现弹弹动画
     */
    private void doPointObjectAnim() {
//        ObjectAnimator animator = ObjectAnimator.ofInt(mWaveCircleView,"PointRadius",0,300,100);
//        animator.setDuration(2000);
//        animator.setInterpolator(new BounceInterpolator());
//        animator.start();

        //利用PropertyValuesHolder实现弹弹动画
        PropertyValuesHolder holder = PropertyValuesHolder.ofInt("PointRadius", 0, 300, 100);
        //这里可以传入多个holder，同时实现多种动画效果
        ObjectAnimator animator1 = ObjectAnimator.ofPropertyValuesHolder(mWaveCircleView, holder);
        animator1.setDuration(2000);
        animator1.setInterpolator(new BounceInterpolator());
        animator1.start();
    }

    /**
     * 利用关键帧KeyFrame实现电话震动的效果
     */
    private void doTelephoneAnim() {
        //左右摇晃的效果
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
        PropertyValuesHolder frameHolder = PropertyValuesHolder.ofKeyframe("rotation", frame0, frame1, frame2,
                frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10);
        //scaleX 放大 1.1 倍
        Keyframe scaleXframe0 = Keyframe.ofFloat(0f, 1);
        Keyframe scaleXframe1 = Keyframe.ofFloat(0.1f, 1.1f);
        Keyframe scaleXframe2 = Keyframe.ofFloat(0.2f, 1.1f);
        Keyframe scaleXframe3 = Keyframe.ofFloat(0.3f, 1.1f);
        Keyframe scaleXframe4 = Keyframe.ofFloat(0.4f, 1.1f);
        Keyframe scaleXframe5 = Keyframe.ofFloat(0.5f, 1.1f);
        Keyframe scaleXframe6 = Keyframe.ofFloat(0.6f, 1.1f);
        Keyframe scaleXframe7 = Keyframe.ofFloat(0.7f, 1.1f);
        Keyframe scaleXframe8 = Keyframe.ofFloat(0.8f, 1.1f);
        Keyframe scaleXframe9 = Keyframe.ofFloat(0.9f, 1.1f);
        Keyframe scaleXframe10 = Keyframe.ofFloat(1, 1);
        PropertyValuesHolder frameHolder2 = PropertyValuesHolder.ofKeyframe("ScaleX", scaleXframe0, scaleXframe1, scaleXframe2,
                scaleXframe3, scaleXframe4, scaleXframe5, scaleXframe6, scaleXframe7, scaleXframe8, scaleXframe9, scaleXframe10);

        //scaleY 放大 1.1 倍
        Keyframe scaleYframe0 = Keyframe.ofFloat(0f, 1);
        Keyframe scaleYframe1 = Keyframe.ofFloat(0.1f, 1.1f);
        Keyframe scaleYframe2 = Keyframe.ofFloat(0.2f, 1.1f);
        Keyframe scaleYframe3 = Keyframe.ofFloat(0.3f, 1.1f);
        Keyframe scaleYframe4 = Keyframe.ofFloat(0.4f, 1.1f);
        Keyframe scaleYframe5 = Keyframe.ofFloat(0.5f, 1.1f);
        Keyframe scaleYframe6 = Keyframe.ofFloat(0.6f, 1.1f);
        Keyframe scaleYframe7 = Keyframe.ofFloat(0.7f, 1.1f);
        Keyframe scaleYframe8 = Keyframe.ofFloat(0.8f, 1.1f);
        Keyframe scaleYframe9 = Keyframe.ofFloat(0.9f, 1.1f);
        Keyframe scaleYframe10 = Keyframe.ofFloat(1, 1);
        PropertyValuesHolder frameHolder3 = PropertyValuesHolder.ofKeyframe("ScaleY", scaleYframe0, scaleYframe1,
                scaleYframe2, scaleYframe3, scaleYframe4, scaleYframe5, scaleYframe6, scaleYframe7, scaleYframe8, scaleYframe9, scaleYframe10);
        Animator animator = ObjectAnimator.ofPropertyValuesHolder(mTelephoneView, frameHolder, frameHolder2, frameHolder3);
        animator.setDuration(1000);
        animator.start();
    }

    /**
     * 自定义Evaluator 不再局限于int 与float 可以是任意Object
     */
    private void showObjectAnimation() {
        ValueAnimator animator = ValueAnimator.ofObject(new CharEvaluator(), new Character('A'), new Character('Z'));
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                char curText = (char) animation.getAnimatedValue();
                mTv.setText(String.valueOf(curText));
            }
        });
        animator.setDuration(1000);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.start();
    }

    /**
     * 展示分享dialog
     */
    private void showShareDialog() {
        Dialog dialog = new Dialog(this, R.style.fullScreenDialog);
        dialog.setContentView(R.layout.share_dialog);
        dialog.setCancelable(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        if (window != null) {
            dialog.show();
            window.getDecorView().setPadding(0, 0, 0, 0);
            lp.copyFrom(window.getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.BOTTOM;
            window.setAttributes(lp);
            //通过xml文件实现动画
            window.setWindowAnimations(R.style.dialog_anim);
            window.findViewById(R.id.dialog_layout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "hha", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * 设置title距离顶部的距离
     */
    protected void setTitlePadding() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //实现状态栏图标和文字颜色为暗色
            int padding = getStatusBarHeight(this);
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) titleview.getLayoutParams();
            params.setMargins(0, padding, 0, 0);
            titleview.setLayoutParams(params);
        }
    }

    /**
     * 获取状态栏的高度
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }

    /**
     * 设置状态栏为沉浸式状态栏
     */
    public static void setStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0及以上
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //根据上面设置是否对状态栏单独设置颜色
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //android6.0以后可以对状态栏文字颜色和图标进行修改
            //自动跟底部颜色进行反差
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    /**
     * 透明状态栏，并且与状态栏重叠
     * 如果不想重叠，通过paddingTop的方式实现
     */
    public static void transparentStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 显示状态栏
     */
    public static void showStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //android6.0以后可以对状态栏文字颜色和图标进行修改
            //自动跟底部颜色进行反差
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == mMenuButton) {
            if (!mIsMenuOpen) {
                mIsMenuOpen = true;
                doAnimateOpen(mItemButton1, 0, 5, 300);
                doAnimateOpen(mItemButton2, 1, 5, 300);
                doAnimateOpen(mItemButton3, 2, 5, 300);
                doAnimateOpen(mItemButton4, 3, 5, 300);
                doAnimateOpen(mItemButton5, 4, 5, 300);
            } else {
                mIsMenuOpen = false;
                doAnimateClose(mItemButton1, 0, 5, 300);
                doAnimateClose(mItemButton2, 1, 5, 300);
                doAnimateClose(mItemButton3, 2, 5, 300);
                doAnimateClose(mItemButton4, 3, 5, 300);
                doAnimateClose(mItemButton5, 4, 5, 300);

            }
        } else {
            //属性动画的移动，点击响应区域才会变化
            Toast.makeText(this, "你点击了" + v, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 按钮弹出的动画
     * @param view 按钮
     * @param index 按钮编号
     * @param total 按钮总数
     * @param radius 弹出半径
     */
    private void doAnimateOpen(View view, int index, int total, int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        double degree = Math.toRadians(90) / (total - 1) * index;
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));
        AnimatorSet set = new AnimatorSet();
        //包含平移、缩放和透明度动画
        set.playTogether(ObjectAnimator.ofFloat(view, "translationX", 0, translationX),
                ObjectAnimator.ofFloat(view, "translationY", 0, translationY),
                ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f),
                ObjectAnimator.ofFloat(view, "alpha", 0f, 1));
        //动画周期为 500ms
        set.setDuration(1 * 500).start();
    }

    /**
     * 按钮收回的动画
     * @param view 按钮
     * @param index 按钮编号
     * @param total 按钮总数
     * @param radius 半径
     */
    private void doAnimateClose(final View view, int index, int total,
                                int radius) {
        if (view.getVisibility() != View.VISIBLE) {
            view.setVisibility(View.VISIBLE);
        }
        double degree = Math.PI * index / ((total - 1) * 2);
        int translationX = -(int) (radius * Math.sin(degree));
        int translationY = -(int) (radius * Math.cos(degree));
        AnimatorSet set = new AnimatorSet();
        //包含平移、缩放和透明度动画
        set.playTogether(
                ObjectAnimator.ofFloat(view, "translationX", translationX, 0),
                ObjectAnimator.ofFloat(view, "translationY", translationY, 0),
                ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f),
                ObjectAnimator.ofFloat(view, "scaleY", 1f, 0f),
                ObjectAnimator.ofFloat(view, "alpha", 1f, 0f));
        set.setDuration(1 * 500).start();

        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //隐藏按钮
                mItemButton1.setVisibility(View.GONE);
                mItemButton2.setVisibility(View.GONE);
                mItemButton3.setVisibility(View.GONE);
                mItemButton4.setVisibility(View.GONE);
                mItemButton5.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
