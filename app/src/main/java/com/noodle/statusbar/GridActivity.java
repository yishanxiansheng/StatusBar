package com.noodle.statusbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.LayoutAnimationController;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * layoutanimation
 * @author heshufan
 * @date 2019-11-24
 */
public class GridActivity extends AppCompatActivity {
    private GridAdapter mGrideAdapter;
    private List<String> mDatas = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        //填充 gridview
        GridView grid = findViewById(R.id.grid);
        mDatas.addAll(getData());
        mGrideAdapter = new GridAdapter();
        grid.setAdapter(mGrideAdapter);
        //按钮点击响应
        Button addData =  findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });
    }

    private List<String> getData() {
        List<String> data = new ArrayList<String>();
        for (int i = 1; i < 35; i++) {
            data.add("DATA " + i);
        }
        return data;
    }

    public void addData() {
        mDatas.addAll(mDatas);
        mGrideAdapter.notifyDataSetChanged();
    }

    public class GridAdapter extends BaseAdapter {
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView i = new TextView(GridActivity.this);
            i.setText(mDatas.get(position));
            i.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.WRAP_CONTENT, GridView.LayoutParams.WRAP_CONTENT));
            return i;
        }

        @Override
        public final int getCount() {
            return mDatas.size();
        }

        @Override
        public final Object getItem(int position) {
            return null;
        }

        @Override
        public final long getItemId(int position) {
            return position;
        }
    }
}


