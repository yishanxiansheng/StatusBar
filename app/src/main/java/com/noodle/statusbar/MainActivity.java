package com.noodle.statusbar;

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
import android.widget.Button;
import android.widget.RelativeLayout;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {
    RelativeLayout titleview ;
    private Button showShareDialogBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusBar(this);
        titleview = findViewById(R.id.titleName);
        showShareDialogBtn = findViewById(R.id.show_dialog);
        showShareDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShareDialog();
            }
        });
        setTitlePadding();
    }

    /**
     * 展示分享dialog
     */
    private void showShareDialog() {
        Dialog dialog  = new Dialog(this,R.style.fullScreenDialog);
        dialog.setContentView(R.layout.share_dialog);
        dialog.setCancelable(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.dialog_anim);
        if (window != null){
            dialog.show();
            window.getDecorView().setPadding(0,0,0,0);
            lp.copyFrom(window.getAttributes());
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.gravity = Gravity.BOTTOM;
            window.setAttributes(lp);
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
    public static void transparentStatusBar(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 显示状态栏
     */
    public static void showStatusBar(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //android6.0以后可以对状态栏文字颜色和图标进行修改
            //自动跟底部颜色进行反差
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
}
