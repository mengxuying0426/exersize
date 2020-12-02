package net.onest.mypartprj;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import net.onest.mypartprj.beans.QuestionBank;
import net.onest.mypartprj.beans.SingleChoice;

import java.util.ArrayList;
import java.util.List;

public class WrongTiListActivity extends Activity {
    private List<SingleChoice> singleChoices;
    private SwipeMenuListView swipeMenuListView;
    private MyWListAdapter myWListAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exersize_wrongti);

        Intent result = getIntent();
        String fenlei = result.getStringExtra("fenlei");
        Log.i("跳转练习",fenlei);
        String[] arr = fenlei.split("&");
        int num = Integer.parseInt(arr[1]);
        singleChoices = new ArrayList<>();
        for(int i = 0;i<num;i++){
            SingleChoice s = new SingleChoice("蔡徐坤的应援色是()"+i,"红色"+i,"蓝色"+i,"粉色"+i,"金色"+i,"D",
                    "掉落人间 你像丘比特赐予我的首选 靠在枕边 ah光绕过你天使般的脸 ah这感觉实在太危险 能否再对我温柔一点点 不忍心再带你去冒险,掉落人间 你像丘比特赐予我的首选 靠在枕边 ah光绕过你天使般的脸 ah这感觉实在太危险 能否再对我温柔一点点 不忍心再带你去冒险,掉落人间 你像丘比特赐予我的首选 靠在枕边 ah光绕过你天使般的脸 ah这感觉实在太危险 " +
                            "能否再对我温柔一点点 不忍心再带你去冒险掉落人间 你像丘比特赐予我的首选 靠在枕边 ah光绕过你天使般的脸 ah这感觉实在太危险 能否再对我温柔一点点 不忍心再带你去冒险",2);
            singleChoices.add(s);
        }

        //获取控件
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                deleteItem.setWidth(dp2px(90));
                deleteItem.setIcon(R.drawable.ic_delete);
                menu.addMenuItem(deleteItem);
            }
        };
        swipeMenuListView = findViewById(R.id.swipelistView);
        swipeMenuListView.setMenuCreator(creator);

         /*
        设置监听
         */
        swipeMenuListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                SingleChoice s = singleChoices.get(position);
                switch (index){
                    case 0:
//                        delete(q);数据库中删除
                        singleChoices.remove(position);
                        myWListAdapter.notifyDataSetChanged();
                        break;
                }
                return true;
            }
        });
        // 监测用户在ListView的SwipeMenu侧滑事件
        swipeMenuListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {
            @Override
            public void onSwipeStart(int position) {
                Log.d("位置:" + position, "开始侧滑...");
            }

            @Override
            public void onSwipeEnd(int position) {

            }
        });
        //绑定Adapter
        myWListAdapter = new MyWListAdapter(getApplicationContext(),singleChoices);
        swipeMenuListView.setAdapter(myWListAdapter);

    }
    public int dp2px(float dipValue) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
