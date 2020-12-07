package net.onest.mypartprj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTabHost;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import net.onest.mypartprj.exersise.SecondFragment;
import net.onest.mypartprj.fragment.Fragment1;
import net.onest.mypartprj.fragment.YuebaoFragment;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Map<String, ImageView> imageViewMap = new HashMap<>();
    private Map<String, TextView> textViewMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取FragmentTabHost的引用
        FragmentTabHost fragmentTabHost = findViewById(android.R.id.tabhost);
        //初始化 //管理多个Fragment对象的管理器
        fragmentTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        //创建内容页面TabSpec对象
        TabHost.TabSpec tab1 = fragmentTabHost.newTabSpec("first_tab").setIndicator(getTabSpecView("first_tab", "首页", R.mipmap.home));
        fragmentTabHost.addTab(tab1, Fragment1.class, null);

        TabHost.TabSpec tab2 = fragmentTabHost.newTabSpec("second_tab").setIndicator(getTabSpecView("second_tab", "院校", R.mipmap.school));
        fragmentTabHost.addTab(tab2, YuebaoFragment.class, null);

        TabHost.TabSpec tab3 = fragmentTabHost.newTabSpec("third_tab").setIndicator(getTabSpecView("third_tab", "计划", R.mipmap.plan));
        fragmentTabHost.addTab(tab3, YuebaoFragment.class, null);
        TabHost.TabSpec tab4 = fragmentTabHost.newTabSpec("four_tab").setIndicator(getTabSpecView("four_tab", "题库", R.drawable.work));
        fragmentTabHost.addTab(tab4, SecondFragment.class, null);
        TabHost.TabSpec tab5 = fragmentTabHost.newTabSpec("five_tab").setIndicator(getTabSpecView("five_tab", "音乐", R.mipmap.music));
        fragmentTabHost.addTab(tab5, YuebaoFragment.class, null);

        //处理fragmentTabHost的选项切换事件
        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                //修改图片和文字颜色
                switch (tabId) {
                    case "first_tab":
                        imageViewMap.get("first_tab").setImageResource(R.mipmap.home_selected);
                        imageViewMap.get("second_tab").setImageResource(R.mipmap.school);
                        imageViewMap.get("third_tab").setImageResource(R.mipmap.plan);
                        imageViewMap.get("four_tab").setImageResource(R.drawable.work_selected);
                        imageViewMap.get("five_tab").setImageResource(R.drawable.music);
                        break;
                    case "second_tab":
                        imageViewMap.get("first_tab").setImageResource(R.mipmap.home);
                        imageViewMap.get("second_tab").setImageResource(R.mipmap.school_selected);
                        imageViewMap.get("third_tab").setImageResource(R.mipmap.plan);
                        imageViewMap.get("four_tab").setImageResource(R.drawable.work_selected);
                        imageViewMap.get("five_tab").setImageResource(R.drawable.music);
                        break;
                    case "third_tab":
                        imageViewMap.get("first_tab").setImageResource(R.mipmap.home);
                        imageViewMap.get("second_tab").setImageResource(R.mipmap.school);
                        imageViewMap.get("third_tab").setImageResource(R.mipmap.plan_selected);
                        imageViewMap.get("four_tab").setImageResource(R.drawable.work_selected);
                        imageViewMap.get("five_tab").setImageResource(R.drawable.music);
                        break;
                    case "four_tab":
                        imageViewMap.get("first_tab").setImageResource(R.mipmap.home);
                        imageViewMap.get("second_tab").setImageResource(R.mipmap.school);
                        imageViewMap.get("third_tab").setImageResource(R.mipmap.plan);
                        imageViewMap.get("four_tab").setImageResource(R.drawable.work);
                        imageViewMap.get("five_tab").setImageResource(R.drawable.music);
                        break;
                    case "five_tab":
                        imageViewMap.get("first_tab").setImageResource(R.mipmap.home);
                        imageViewMap.get("second_tab").setImageResource(R.mipmap.school);
                        imageViewMap.get("third_tab").setImageResource(R.mipmap.plan);
                        imageViewMap.get("four_tab").setImageResource(R.drawable.work_selected);
                        imageViewMap.get("five_tab").setImageResource(R.drawable.music_selected);
                        break;
                }
            }
        });

        //设置默认选项卡
        fragmentTabHost.setCurrentTab(3);
        imageViewMap.get("four_tab").setImageResource(R.drawable.work);
//        textViewMap.get("four_tab").setTextColor(getResources().getColor(R.color.color3));


    }
    public View getTabSpecView(String tag, String title, int drawable){
        View view = getLayoutInflater().inflate(R.layout.tab_spec_layout,null);

        //获取tab_spec_layout布局当中视图控件的引用
        ImageView icon = view.findViewById(R.id.icon);
        icon.setImageResource(drawable);

        //存储ImageView对象
        imageViewMap.put(tag,icon);

        TextView tvtitle = view.findViewById(R.id.title);
        tvtitle.setText(title);
        textViewMap.put(tag,tvtitle);

        return view;
    }
}
