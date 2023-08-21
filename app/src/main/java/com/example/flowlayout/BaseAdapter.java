package com.example.flowlayout;

import android.view.View;
import android.view.ViewGroup;

public abstract class BaseAdapter {

    //有多少个条目
    public abstract int getCount();

    //通过position获取view
    public abstract View getView(int position, ViewGroup parent);

    //观察者模式及时通知更新


}
