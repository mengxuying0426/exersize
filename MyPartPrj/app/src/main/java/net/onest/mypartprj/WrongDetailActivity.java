package net.onest.mypartprj;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import net.onest.mypartprj.beans.SingleChoice;
import net.onest.mypartprj.utils.ScaleAnimatorUtils;

import java.util.ArrayList;
import java.util.List;

public class WrongDetailActivity extends Activity {
    private String currentOpt;
    private TextView tvStem;
    private TextView tvOpt;
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
    private ImageView ivOpt1;
    private ImageView ivOpt2;
    private ImageView ivOpt3;
    private ImageView ivOpt4;
    private ImageView ivShoucang1;
    private int keyNum = 0;
    private boolean keyNumA = false;
    private boolean keyNumB = false;
    private boolean keyNumC = false;
    private boolean keyNumD = false;
    private SingleChoice singleChoice;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wrong_detail);

        Intent result = getIntent();
        String fenlei = result.getStringExtra("fenlei");
        Log.i("跳转练习",fenlei);
        String[] arr = fenlei.split("&");
        int keynum = Integer.parseInt(arr[1]);
        singleChoice = new SingleChoice("蔡徐坤的应援色是()"+keynum,"红色","蓝色","粉色","金色","D",
                "掉落人间 你像丘比特赐予我的首选 靠在枕边 ah光绕过你天使般的脸 ah这感觉实在太危险 能否再对我温柔一点点 不忍心再带你去冒险,掉落人间 你像丘比特赐予我的首选 靠在枕边 ah光绕过你天使般的脸 ah这感觉实在太危险 能否再对我温柔一点点 不忍心再带你去冒险,掉落人间 你像丘比特赐予我的首选 靠在枕边 ah光绕过你天使般的脸 ah这感觉实在太危险 " +
                        "能否再对我温柔一点点 不忍心再带你去冒险掉落人间 你像丘比特赐予我的首选 靠在枕边 ah光绕过你天使般的脸 ah这感觉实在太危险 能否再对我温柔一点点 不忍心再带你去冒险",2);

        findViews();
        setListener();
    }

    private void setListener() {
        MyListener listener = new MyListener();
        llOptA.setOnClickListener(listener);
        llOptB.setOnClickListener(listener);
        llOptC.setOnClickListener(listener);
        llOptD.setOnClickListener(listener);
        ivShoucang1.setOnClickListener(listener);
    }

    private void findViews() {
        tvStem = findViewById(R.id.tv_stem);
        tvOptA = findViewById(R.id.tv_optA);
        tvOptB = findViewById(R.id.tv_optB);
        tvOptC = findViewById(R.id.tv_optC);
        tvOptD = findViewById(R.id.tv_optD);
        tvOpt = findViewById(R.id.tv_opt);
        llOptA = findViewById(R.id.ll_optA);
        llOptB = findViewById(R.id.ll_optB);
        llOptC = findViewById(R.id.ll_optC);
        llOptD = findViewById(R.id.ll_optD);
        tvAnalysis = findViewById(R.id.tv_analysis);
        tvAnTitle = findViewById(R.id.tv_antitle);
        ivShoucang1 = findViewById(R.id.iv_shoucang1);
        ivShoucang1.setSelected(false);
        ivOpt1 = findViewById(R.id.iv_opt1);
        ivOpt2 = findViewById(R.id.iv_opt2);
        ivOpt3 = findViewById(R.id.iv_opt3);
        ivOpt4 = findViewById(R.id.iv_opt4);
        tvOptA.setText(singleChoice.getOptionA());
        tvOptB.setText(singleChoice.getOptionB());
        tvOptC.setText(singleChoice.getOptionC());
        tvOptD.setText(singleChoice.getOptionD());
        currentOpt = singleChoice.getCorrect();
        tvStem.setText(singleChoice.getStem());
        if(singleChoice.getKeyNum()>1){
            tvOpt.setText("(多选题)");
        }else {
            tvOpt.setText("(单选题)");
        }
    }

    class MyListener implements View.OnClickListener{

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_shoucang1:
                    if(ivShoucang1.isSelected()==false){
                        ivShoucang1.setImageResource(R.drawable.shoucang1);
                        ivShoucang1.setSelected(true);
                        ScaleAnimatorUtils.setScalse(ivShoucang1);
                        Toast.makeText(WrongDetailActivity.this,"收藏成功",Toast.LENGTH_SHORT).show();
                    }else {
                        ivShoucang1.setImageResource(R.drawable.shoucang1no);
                        ivShoucang1.setSelected(false);
                        Toast.makeText(WrongDetailActivity.this,"取消收藏",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.ll_optA:
                    if(keyNumA==false){
                        keyNumA=true;
                        tvOptA.setTextColor(WrongDetailActivity.this.getResources().getColor(R.color.colorSel));
                        keyNum++;
                    }else{
                        keyNumA=false;
                        tvOptA.setTextColor(WrongDetailActivity.this.getResources().getColor(R.color.colorNoSel));
                        keyNum--;
                    }
                    if(keyNum==singleChoice.getKeyNum()){
                        Log.i("mxy","点击完毕A");
                        judgmentOpt();
                    }
                    Log.i("mxy","点击了A");
                    break;
                case R.id.ll_optB:
                    if(keyNumB==false){
                        keyNumB=true;
                        tvOptB.setTextColor(WrongDetailActivity.this.getResources().getColor(R.color.colorSel));
                        keyNum++;
                    }else{
                        keyNumB=false;
                        tvOptB.setTextColor(WrongDetailActivity.this.getResources().getColor(R.color.colorNoSel));
                        keyNum--;
                    }
                    if(keyNum==singleChoice.getKeyNum()){
                        Log.i("mxy","点击完毕B");
                        judgmentOpt();
                    }
                    break;
                case R.id.ll_optC:
                    if(keyNumC==false){
                        keyNumC=true;
                        tvOptC.setTextColor(WrongDetailActivity.this.getResources().getColor(R.color.colorSel));
                        keyNum++;
                    }else{
                        keyNumC=false;
                        tvOptC.setTextColor(WrongDetailActivity.this.getResources().getColor(R.color.colorNoSel));
                        keyNum--;
                    }
                    if(keyNum==singleChoice.getKeyNum()){
                        Log.i("mxy","点击完毕C");
                        judgmentOpt();
                    }
                    break;
                case R.id.ll_optD:
                    if(keyNumD==false){
                        keyNumD=true;
                        tvOptD.setTextColor(WrongDetailActivity.this.getResources().getColor(R.color.colorSel));
                        keyNum++;
                    }else{
                        keyNumD=false;
                        tvOptD.setTextColor(WrongDetailActivity.this.getResources().getColor(R.color.colorNoSel));
                        keyNum--;
                    }
                    if(keyNum==singleChoice.getKeyNum()){
                        Log.i("mxy","点击完毕D");
                        judgmentOpt();
                    }
                    break;
            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void judgmentOpt() {
        switch (currentOpt){
            case "A":
                ivOpt1.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt2.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt3.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt4.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.no,null));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoice.getAnalysis());
                break;
            case "B":
                ivOpt1.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt2.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt3.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt4.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.no,null));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoice.getAnalysis());
                break;
            case "C":
                ivOpt1.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt2.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt3.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt4.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.no,null));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoice.getAnalysis());
                break;
            case "D":
                Log.i("mxy","正确答案是D");
                ivOpt1.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt2.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt3.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt4.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.yes,null));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoice.getAnalysis());
                break;
            case "AB":
                Log.i("mxy","正确答案是AB");
                ivOpt1.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt2.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt3.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt4.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.no,null));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoice.getAnalysis());
                break;
            case "AC":
                Log.i("mxy","正确答案是AC");
                ivOpt1.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt2.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt3.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt4.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.no,null));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoice.getAnalysis());
                break;
            case "AD":
                Log.i("mxy","正确答案是AD");
                ivOpt1.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt2.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt3.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt4.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.yes,null));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoice.getAnalysis());
                break;
            case "ABC":
                Log.i("mxy","正确答案是ABC");
                ivOpt1.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt2.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt3.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt4.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.no,null));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoice.getAnalysis());
                break;
            case "ABD":
                Log.i("mxy","正确答案是ABD");
                ivOpt1.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt2.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt3.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt4.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.yes,null));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoice.getAnalysis());
                break;
            case "ABCD":
                Log.i("mxy","正确答案是ABCD");
                ivOpt1.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt2.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt3.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt4.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.yes,null));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoice.getAnalysis());
                break;
            case "BC":
                Log.i("mxy","正确答案是BC");
                ivOpt1.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt2.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt3.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt4.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.no,null));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoice.getAnalysis());
                break;
            case "BD":
                Log.i("mxy","正确答案是BD");
                ivOpt1.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt2.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt3.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt4.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.yes,null));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoice.getAnalysis());
                break;
            case "BCD":
                Log.i("mxy","正确答案是BCD");
                ivOpt1.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt2.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt3.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt4.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.yes,null));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoice.getAnalysis());
                break;
            case "CD":
                Log.i("mxy","正确答案是CD");
                ivOpt1.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt2.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt3.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt4.setImageDrawable(WrongDetailActivity.this.getResources().getDrawable(R.drawable.yes,null));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoice.getAnalysis());
                break;
        }

    }
}
