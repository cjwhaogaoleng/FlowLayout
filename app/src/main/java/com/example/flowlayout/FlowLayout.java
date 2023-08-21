package com.example.flowlayout;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends ViewGroup {

    private List<List<View>> mChildViews;
    private List<Integer> heightList;

    private int horizontalMargin;
    private int verticalMargin;

    private BaseAdapter mAdapter;


    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量子view
        int count = getChildCount();


        //View当前的最大宽度
        int width = MeasureSpec.getSize(widthMeasureSpec);

        //已经摆放的高度
        int height = getPaddingTop();
        //一行中那个最大的高度
        int maxLineHeight = 0;
        //已经摆放的宽度
        int lineWidth = getPaddingLeft();


        List<View> oneLineChildView = new ArrayList<>();
        mChildViews = new ArrayList<>();
        heightList = new ArrayList<>();


        //根据子view计算和指定自己的布局
        //计算子view的宽高，从而获取自己的宽高
        for (int i = 0; i < count; i++) {

            View childView = getChildAt(i);

            //判断是否需要绘制
            if (childView.getVisibility()==GONE) {
                continue;
            }

            //记得考虑margin
            ViewGroup.MarginLayoutParams params = (MarginLayoutParams) childView.getLayoutParams();
            horizontalMargin = params.leftMargin + params.rightMargin;
            verticalMargin = params.topMargin + params.bottomMargin;

            //这句话执行后就可以获取到子view的宽高spec，调用了子view的onMeasure
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);

            //什么时候需要换行
            if (lineWidth + childView.getMeasuredWidth() + horizontalMargin > width) {

                //换行

                //将一行保存并重置
                mChildViews.add(oneLineChildView);
                oneLineChildView = new ArrayList<>();

                height += maxLineHeight;
                heightList.add(maxLineHeight);

                maxLineHeight = 0;
                maxLineHeight = Math.max(maxLineHeight, childView.getMeasuredHeight() + verticalMargin);
                lineWidth = childView.getMeasuredWidth();
            } else {
                lineWidth += childView.getMeasuredWidth() + horizontalMargin;
                maxLineHeight = Math.max(maxLineHeight, childView.getMeasuredHeight() + verticalMargin);
            }

            oneLineChildView.add(childView);

            if (i == count - 1) {
                mChildViews.add(oneLineChildView);
                height += maxLineHeight + getPaddingBottom();
                heightList.add(maxLineHeight);
            }
        }


        //将最大的宽和高传入
//        Log.d(TAG, "width: " + width+"  height: " + height);
        setMeasuredDimension(width, height);

    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int left, top = getPaddingTop(), right, bottom, topNow;
        for (int i = 0; i < mChildViews.size(); i++) {
            List<View> oneLineView = mChildViews.get(i);

            left = getPaddingLeft();
            for (View view : oneLineView) {
                //判断是否需要绘制
                if (view.getVisibility()==GONE) {
                    continue;
                }
                ViewGroup.MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();
                //topNow用于高度不一致时的拜访高度
                //top是每行的top，不包括每个margin
//                topNow = top + (heightList.get(i) - view.getMeasuredHeight()) / 2 + params.topMargin;
                topNow = top + params.topMargin;
                left += params.leftMargin;
                right = left + view.getMeasuredWidth();
                bottom = topNow + view.getMeasuredHeight();

                view.layout(left, topNow, right, bottom);
                left = right + params.rightMargin;
            }
            top += heightList.get(i);

        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    /**
     * 设置我们的adapter
     *
     * @param adapter
     */
    public void setAdapter(BaseAdapter adapter) {
        if (adapter == null) {
            //抛空指针异常
        } else {
            removeAllViews();

            mAdapter = adapter;
            int childCount = mAdapter.getCount();
            for (int i = 0; i < childCount; i++) {
                //通过位置获取view
                View childView = mAdapter.getView(i, this);
                addView(childView);
            }
        }
    }
}
