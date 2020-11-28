package net.onest.mypartprj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import net.onest.mypartprj.beans.QuestionBank;
import net.onest.mypartprj.beans.SingleChoice;
import net.onest.mypartprj.utils.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public class ExerciseActivity extends Activity {
    private ViewPager mViewPager;
    private int mPosition;
    private String currentOpt;
    private TextView tvStem;
    private TextView tvOptA;
    private TextView tvOptB;
    private TextView tvOptC;
    private TextView tvOptD;
    private TextView tvAnTitle;
    private TextView tvAnalysis;
    private LinearLayout llOptA;
    private LinearLayout llOptB;
    private LinearLayout llOptC;
    private LinearLayout llOptD;
//    private DynamicFragmentAdapter dynamicFragmentAdapter;
//    private List<Fragment> mFragments;
    private List<SingleChoice> singleChoices;
    private List<View> myViewList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_exercise);

        Intent result = getIntent();
        String fenlei = result.getStringExtra("fenlei");
        Log.i("跳转练习",fenlei);
        String[] arr = fenlei.split("&");
        int num = Integer.parseInt(arr[1]);
        singleChoices = new ArrayList<>();
        for(int i = 0;i<num;i++){
            SingleChoice s = new SingleChoice("蔡徐坤的应援色是()"+i,"红色"+i,"蓝色"+i,"粉色"+i,"金色"+i,"D",
                    "掉落人间 你像丘比特赐予我的首选 靠在枕边 ah光绕过你天使般的脸 ah这感觉实在太危险 能否再对我温柔一点点 不忍心再带你去冒险,掉落人间 你像丘比特赐予我的首选 靠在枕边 ah光绕过你天使般的脸 ah这感觉实在太危险 能否再对我温柔一点点 不忍心再带你去冒险,掉落人间 你像丘比特赐予我的首选 靠在枕边 ah光绕过你天使般的脸 ah这感觉实在太危险 " +
                            "能否再对我温柔一点点 不忍心再带你去冒险掉落人间 你像丘比特赐予我的首选 靠在枕边 ah光绕过你天使般的脸 ah这感觉实在太危险 能否再对我温柔一点点 不忍心再带你去冒险");
            singleChoices.add(s);
        }
        mViewPager = findViewById(R.id.in_viewpager);
        myViewList = new ArrayList<>();
        LayoutInflater layoutInflater = getLayoutInflater().from(ExerciseActivity.this);
        for(int j = 0;j<singleChoices.size();j++){
            View view1 = layoutInflater.inflate(R.layout.exercise_first, null,false);
            myViewList.add(view1);
        }
        mViewPager.setAdapter(new MyPager(myViewList));
        mPosition = mViewPager.getCurrentItem();

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i("PageChange-Select", "position:" + position);
                mPosition=position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE:
                        Log.i("PageChange-State", "state:SCROLL_STATE_IDLE(滑动闲置或滑动结束)");


                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        Log.i("PageChange-State", "state:SCROLL_STATE_DRAGGING(手势滑动中)");
                        Log.i("当前页",""+mPosition);

                        if(mPosition<singleChoices.size()-1){
                            View view = myViewList.get(mPosition+1);
                            initView(view,singleChoices.get(mPosition+1));
                            setViewListener();
                        }
                        break;
                    case ViewPager.SCROLL_STATE_SETTLING:
                        Log.i("PageChange-State", "state:SCROLL_STATE_SETTLING(代码执行滑动中)");
                        break;
                    default:
                        break;
                }

            }
        });
        View view = myViewList.get(mPosition);

        initView(view,singleChoices.get(mPosition));
        setViewListener();

    }

    private void setViewListener() {
        MyListener listener = new MyListener();
        llOptA.setOnClickListener(listener);
        llOptB.setOnClickListener(listener);
        llOptC.setOnClickListener(listener);
        llOptD.setOnClickListener(listener);
    }

    private void initView(View view,SingleChoice singleChoice) {
        tvStem = view.findViewById(R.id.tv_stem);
        tvOptA = view.findViewById(R.id.tv_optA);
        tvOptB = view.findViewById(R.id.tv_optB);
        tvOptC = view.findViewById(R.id.tv_optC);
        tvOptD = view.findViewById(R.id.tv_optD);
        llOptA = view.findViewById(R.id.ll_optA);
        llOptB = view.findViewById(R.id.ll_optB);
        llOptC = view.findViewById(R.id.ll_optC);
        llOptD = view.findViewById(R.id.ll_optD);
        tvAnTitle = view.findViewById(R.id.tv_antitle);
        tvAnalysis = view.findViewById(R.id.tv_analysis);
        currentOpt = singleChoice.getCorrect();
        tvStem.setText(singleChoice.getStem());
        tvOptA.setText(singleChoice.getOptionA());
        tvOptB.setText(singleChoice.getOptionB());
        tvOptC.setText(singleChoice.getOptionC());
        tvOptD.setText(singleChoice.getOptionD());

    }

    class MyListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ll_optA:
                    Log.i("mxy","点击了A");
                    judgmentOpt();
                    break;
                case R.id.ll_optB:
                    judgmentOpt();
                    break;
                case R.id.ll_optC:
                    judgmentOpt();
                    break;
                case R.id.ll_optD:
                    judgmentOpt();
                    break;

            }
        }
    }

    private void judgmentOpt() {
        switch (currentOpt){
            case "A":
                llOptA.setBackgroundColor(ExerciseActivity.this.getResources().getColor(R.color.colorOk));
                llOptB.setBackgroundColor(ExerciseActivity.this.getResources().getColor(R.color.colorErr));
                llOptC.setBackgroundColor(ExerciseActivity.this.getResources().getColor(R.color.colorErr));
                llOptD.setBackgroundColor(ExerciseActivity.this.getResources().getColor(R.color.colorErr));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
                break;
            case "B":
                llOptA.setBackgroundColor(ExerciseActivity.this.getResources().getColor(R.color.colorErr));
                llOptB.setBackgroundColor(ExerciseActivity.this.getResources().getColor(R.color.colorOk));
                llOptC.setBackgroundColor(ExerciseActivity.this.getResources().getColor(R.color.colorErr));
                llOptD.setBackgroundColor(ExerciseActivity.this.getResources().getColor(R.color.colorErr));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
                break;
            case "C":
                llOptA.setBackgroundColor(ExerciseActivity.this.getResources().getColor(R.color.colorErr));
                llOptB.setBackgroundColor(ExerciseActivity.this.getResources().getColor(R.color.colorErr));
                llOptC.setBackgroundColor(ExerciseActivity.this.getResources().getColor(R.color.colorOk));
                llOptD.setBackgroundColor(ExerciseActivity.this.getResources().getColor(R.color.colorErr));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
                break;
            case "D":
                Log.i("mxy","正确答案是D");
                llOptA.setBackgroundColor(ExerciseActivity.this.getResources().getColor(R.color.colorErr));
                llOptB.setBackgroundColor(ExerciseActivity.this.getResources().getColor(R.color.colorErr));
                llOptC.setBackgroundColor(ExerciseActivity.this.getResources().getColor(R.color.colorErr));
                llOptD.setBackgroundColor(ExerciseActivity.this.getResources().getColor(R.color.colorOk));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
                break;
        }
    }


}
