# FlowLayout

流式布局

 ## 效果展示 

https://github.com/cjwhaogaoleng/FlowLayout/assets/117556474/74a86338-67de-428f-9897-4fd22bbb2c77

 ## 源码位置
/app/src/main/java/com/example/flowlayout/FlowLayout.java
 ## 代码讲解
  ### JAVA
  #### 使用方法
```
flowLayout.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return dataList != null ? dataList.size() : 0;
            }

            @Override
            public View getView(int position, ViewGroup parent) {
                TextView tagTv = (TextView) LayoutInflater.from(MainActivity.this).inflate(R.layout.tag_item, parent, false);
                tagTv.setText(dataList.get(position));
                tagTv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "点击了" + tagTv.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
                return tagTv;
            }
        });
    }
```
传入adapter实现数据绑定和点击事件
 ## 待完成
 - [x] 自定义view
   - [x] onMeasure 源码和写法基本了解
   - [x] onDraw 源码和写法基本了解
   - [x] onTouch 触碰分发事件正在学习
 - [ ] compose 已经接触，还没有另一种熟练
 - [ ] :disappointed: :blush:


