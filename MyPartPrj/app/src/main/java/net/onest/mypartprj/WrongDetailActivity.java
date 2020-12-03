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
        int p = Integer.parseInt(arr[2]);
        SingleChoice s1 = new SingleChoice("某资本家投资100万元创办企业从事生产，60万元用于固定资本、以购买机器设备等，40万元用于流动资本、以购买原材料和劳动力等(其中购买劳动力支付了10万元),一轮生产结束后，该企业的总资本达到了120万元。那么，该企业的剩余价值率为()。","200%","50%","100%","20%","A",1,"A正确，B、C、D错误。剩余价值率的计算公式是m'=m/v，题干中m的数量是120万与100万的差，即20万，v是用来购买劳动力商品的，题干中v的数量为10万，并不是40万。因此，m'=m/v=20万/10万=200%。题干的混淆项是固定资本和流动资本，但混淆性极弱。故正确答案为A。");
        SingleChoice s2 = new SingleChoice("邓小平指出:社会主义究竟是个什么样子，苏联搞了很多年，也并没有完全搞清楚。可能列宁的思路比较好，搞了个新经济政策，但是后来苏联的模式僵化了。列宁新经济政策关于社会主义的思维之所以“比较好”是因为()。","提出了比较系统的社会主义建设纲领","根据俄国的实际情况来探索社会主义建设的道路","为俄国找到了一种比较成熟的社会发展模式","按照马克思恩格斯关于未来社会的设想来建设社会主义","B",1,"本题考查的知识点为列宁领导下的苏维埃俄国对社会主义的探索。1921年3月，俄共(布)召开十大，决定从战时共产主义政策过渡到以发展商品经济为主要特征的新经济政策。这一决定，表明列宁的社会主义建设思想发生了重大转变，对俄国社会主义的发展道路有了新的认识，标志着列宁正在找到一条符合俄国情况的建设社会主义的道路。B项正确。A项错误，列宁虽然对建设社会主义提出过许多精辟的论述，但并没有提出比较系统的社会主义建设纲领; C项错误，列宁并没有为俄国找到一种成熟的社会发展模式，新经济政策也是一种探索性质的道路; D项错误,列宁并不是按照马克思主义关于社会主义的设想建设俄国的社会主义，而是将马克思主义与俄国的具体国情相结合，建设俄国的社会主义。故正确答案为B。");
        SingleChoice s3 = new SingleChoice("有人认为，既然人的意识是对客观外部世界的反映。那么人脑海里的“鬼神”意识就是对外在世界上鬼、神真实存在的反映。这种观念的错误在于()。","夸大了意识的能动作用","把意识看成是物质的产物","认为意识是对存在的直观反映","混淆了人类意识自然演化的阶段","C",1,"A错误。从题干本身来看，不是在夸大意识的能动作用，相反是在否认意识的能动作用。B错误。此选项把意识看成是物质的产物，本身是一种唯物主义的观点，不符合题意C正确。意识是人脑的机能和属性，是客观世界的主观映象。客观世界中并没有鬼、神，鬼、神是人脑对客观世界的歪曲反映，可以从人世间找到它的原型，是人按照自己的形象塑造出来的。意识是对客观事物的反映，但人的意识不是照镜子似的原物映现，即直观反映。意识是物质的产物，但又不是物质本身。D错误。意识作为一种反映形式，它的形成经历一-切物质所具有的反应特性到低等生物的刺激感应性，再到高等动物的感觉和心理，最终发展为人类的意识这样三个发展阶段，题干并没有涉及到此问题。");
        switch (p){
            case 0:
                singleChoice=s1;
            case 1:
                singleChoice=s2;
            case 2:
                singleChoice=s3;
        }
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
