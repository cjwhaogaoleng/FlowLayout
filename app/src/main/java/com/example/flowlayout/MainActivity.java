 package com.example.flowlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

 public class MainActivity extends AppCompatActivity {

     FlowLayout flowLayout;
     List<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        flowLayout = findViewById(R.id.fl);
        flowLayout.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return dataList != null ? dataList.size() : 0;
            }

            @Override
            public View getView(int position, ViewGroup parent) {
                TextView tagTv = (TextView) LayoutInflater.from(MainActivity.this).inflate(R.layout.tag_item, parent, false);
                tagTv.setText(dataList.get(position));
                return tagTv;
            }
        });
    }

     private void initData() {
         dataList = new ArrayList<>();
         dataList.add("111111111111");
         dataList.add("111111111");
         dataList.add("11111111111");
         dataList.add("1111111111");
         dataList.add("11111111111");
         dataList.add("1111111111");
         dataList.add("111111111111");
         dataList.add("1111111111");
         dataList.add("111111111111");
         dataList.add("11111111");
         dataList.add("111111111111");
     }
 }