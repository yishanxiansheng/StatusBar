package com.noodle.statusbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
}
