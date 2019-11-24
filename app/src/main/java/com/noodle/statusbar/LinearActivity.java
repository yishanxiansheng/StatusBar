package com.noodle.statusbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * layoutanimation 在ConstrainLayout中不适用
 * @author heshufan
 * @date 2019-11-24
 */
public class LinearActivity extends AppCompatActivity {
    /**
     * layoutAnimation
     */
    private ListView mListView;
    private ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);
        mListView = findViewById(R.id.listview);
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,getData());
        mListView.setAdapter(arrayAdapter);

        //代码添加动画
        loadAnimationByCode();

    }
    /**
     * listview的数据
     * @return 数据
     */
    private List<String> getData() {
        List<String> data = new ArrayList<String>(); data.add("测试数据 1");
        data.add("测试数据 2");
        data.add("测试数据 3");
        data.add("测试数据 4");
        return data;
    }

    public void reflash(View view){
        arrayAdapter.addAll(getData());

    }

    /**
     * 通过代码添加动画
     */
    private void loadAnimationByCode(){
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.slide_in_left);
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        //设置间隔时间
        controller.setDelay(1);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        controller.setInterpolator(new BounceInterpolator());
        mListView.setLayoutAnimation(controller);
        mListView.startLayoutAnimation();
    }
}
