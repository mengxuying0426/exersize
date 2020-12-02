package net.onest.mypartprj;

import android.app.Activity;
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

public class WrongTiActivity extends Activity {
    private SwipeMenuListView swipeMenuListView;
    private List<QuestionBank> myWList;
    private MyWAdapter myWAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exersize_wrongti);

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
                QuestionBank q = myWList.get(position);
                switch (index){
                    case 0:
//                        delete(q);数据库中删除
                        myWList.remove(position);
                        myWAdapter.notifyDataSetChanged();
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
        //设置一些死数据
        myWList = new ArrayList<>();
        QuestionBank q1 = new QuestionBank("中国近代史",3,1);
        QuestionBank q2 = new QuestionBank("马克思主义理论",2,1);
        QuestionBank q3 = new QuestionBank("毛泽东思想",2,1);
        QuestionBank q4 = new QuestionBank("邓小平理论",6,1);
        QuestionBank q5 = new QuestionBank("新社会主义",2,1);
        myWList.add(q1);
        myWList.add(q2);
        myWList.add(q3);
        myWList.add(q4);
        myWList.add(q5);
        //绑定Adapter
        myWAdapter = new MyWAdapter(getApplicationContext(),myWList);
        swipeMenuListView.setAdapter(myWAdapter);

    }
    public int dp2px(float dipValue) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
