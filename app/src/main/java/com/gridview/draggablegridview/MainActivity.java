package com.gridview.draggablegridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final int NUM_COLUMNS = 4;//grideview的列数
    private List<Data> data = new ArrayList<>();
    private MoreColorTypeAdapter mMoreColorTypeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DragGridView dragGridView = findViewById(R.id.dgv);
        dragGridView.setNumColumns(NUM_COLUMNS);
        initData();
        mMoreColorTypeAdapter = new MoreColorTypeAdapter(this, true, data, NUM_COLUMNS);
        dragGridView.setAdapter(mMoreColorTypeAdapter);

        dragGridView.setOnItemClickListener(this);
        dragGridView.setOnItemChangeListener(new DragGridView.OnItemChangeListener() {
            @Override
            public void onChange(int from, int to) {
                Data dataBean = data.get(from);
                //直接交互
                //Collections.swap(dataSourceList,from,to);

                //非直接交互 这里的处理需要注意下 排序交换
                if(from < to){
                    for(int i = from; i < to; i++){
                        Collections.swap(data, i, i + 1);
                    }
                }else if(from > to){
                    for(int i = from; i > to; i--){
                        Collections.swap(data, i, i - 1);
                    }
                }
                data.set(to, dataBean);

                mMoreColorTypeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onStop() {
                //拖动停止
            }
        });
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            Data dataBean = new Data();
            dataBean.setImage(R.drawable.lott_icon_football);
            dataBean.setFooterType(i % 2 == 0 ? 0 : 1);
            dataBean.setName("测试数据" + i);
            dataBean.setTip(i % 2 == 0 ? null : "" + i);
            dataBean.setTag("测试tag" + i);
            data.add(dataBean);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(MainActivity.this, i + "", Toast.LENGTH_SHORT).show();
    }
}
