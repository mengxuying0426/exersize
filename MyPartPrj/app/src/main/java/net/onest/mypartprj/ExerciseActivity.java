package net.onest.mypartprj;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import net.onest.mypartprj.beans.QuestionBank;
import net.onest.mypartprj.beans.SingleChoice;
import net.onest.mypartprj.utils.NoScrollViewPager;
import net.onest.mypartprj.utils.ScaleAnimatorUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private ImageView ivMore;
    private ImageView ivOpt1;
    private ImageView ivOpt2;
    private ImageView ivOpt3;
    private ImageView ivOpt4;
    private ImageView ivShoucang;
    private ImageView ivCuoti;
    private LinearLayout llShoucang;
    private LinearLayout llCuoti;
    private List<SingleChoice> singleChoices;
    private List<View> myViewList;
    private CommonPopupWindow window;
    private TextView tvShoucang;
    private TextView tvCuoti;
    private CommonPopupWindow.LayoutGravity layoutGravity;
    private int keyNum = 0;
    private boolean keyNumA = false;
    private boolean keyNumB = false;
    private boolean keyNumC = false;
    private boolean keyNumD = false;
    private Map<Integer,Boolean> collect = new HashMap<>();
    private Map<Integer,Boolean> cuotiset = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_exercise);

        Intent result = getIntent();
        String fenlei = result.getStringExtra("fenlei");
        Log.i("跳转练习",fenlei);
        String[] arr = fenlei.split("&");
        String kemu = arr[1];
        int num = Integer.parseInt(arr[0]);
        collect.put(0,false);
        cuotiset.put(0,false);
        List<SingleChoice> s11 = new ArrayList<>();
        SingleChoice s1 = new SingleChoice("某资本家投资100万元创办企业从事生产，60万元用于固定资本、以购买机器设备等，40万元用于流动资本、以购买原材料和劳动力等(其中购买劳动力支付了10万元),一轮生产结束后，该企业的总资本达到了120万元。那么，该企业的剩余价值率为()。","200%","50%","100%","20%","A",1,"A正确，B、C、D错误。剩余价值率的计算公式是m'=m/v，题干中m的数量是120万与100万的差，即20万，v是用来购买劳动力商品的，题干中v的数量为10万，并不是40万。因此，m'=m/v=20万/10万=200%。题干的混淆项是固定资本和流动资本，但混淆性极弱。故正确答案为A。");
        s11.add(s1);
        SingleChoice s2 = new SingleChoice("邓小平指出:社会主义究竟是个什么样子，苏联搞了很多年，也并没有完全搞清楚。可能列宁的思路比较好，搞了个新经济政策，但是后来苏联的模式僵化了。列宁新经济政策关于社会主义的思维之所以“比较好”是因为()。","提出了比较系统的社会主义建设纲领","根据俄国的实际情况来探索社会主义建设的道路","为俄国找到了一种比较成熟的社会发展模式","按照马克思恩格斯关于未来社会的设想来建设社会主义","B",1,"本题考查的知识点为列宁领导下的苏维埃俄国对社会主义的探索。1921年3月，俄共(布)召开十大，决定从战时共产主义政策过渡到以发展商品经济为主要特征的新经济政策。这一决定，表明列宁的社会主义建设思想发生了重大转变，对俄国社会主义的发展道路有了新的认识，标志着列宁正在找到一条符合俄国情况的建设社会主义的道路。B项正确。A项错误，列宁虽然对建设社会主义提出过许多精辟的论述，但并没有提出比较系统的社会主义建设纲领; C项错误，列宁并没有为俄国找到一种成熟的社会发展模式，新经济政策也是一种探索性质的道路; D项错误,列宁并不是按照马克思主义关于社会主义的设想建设俄国的社会主义，而是将马克思主义与俄国的具体国情相结合，建设俄国的社会主义。故正确答案为B。");
        s11.add(s2);
        SingleChoice s3 = new SingleChoice("有人认为，既然人的意识是对客观外部世界的反映。那么人脑海里的“鬼神”意识就是对外在世界上鬼、神真实存在的反映。这种观念的错误在于()。","夸大了意识的能动作用","把意识看成是物质的产物","认为意识是对存在的直观反映","混淆了人类意识自然演化的阶段","C",1,"A错误。从题干本身来看，不是在夸大意识的能动作用，相反是在否认意识的能动作用。B错误。此选项把意识看成是物质的产物，本身是一种唯物主义的观点，不符合题意C正确。意识是人脑的机能和属性，是客观世界的主观映象。客观世界中并没有鬼、神，鬼、神是人脑对客观世界的歪曲反映，可以从人世间找到它的原型，是人按照自己的形象塑造出来的。意识是对客观事物的反映，但人的意识不是照镜子似的原物映现，即直观反映。意识是物质的产物，但又不是物质本身。D错误。意识作为一种反映形式，它的形成经历一-切物质所具有的反应特性到低等生物的刺激感应性，再到高等动物的感觉和心理，最终发展为人类的意识这样三个发展阶段，题干并没有涉及到此问题。");
        s11.add(s3);
        SingleChoice s4 = new SingleChoice("1914年至1918年的第一次世界大战，是一场空前残酷的大屠杀。它改变了世界政治的格局，也改变了各帝国主义国家在中国的利益格局，对中国产生了巨大的影响。大战使中国的先进分子()。","对中国传统文化产生怀疑","对西方资产阶级民主主义产生怀疑","认识到工人阶级的重要作用","认识到必须优先改造国民性","B",1,"本题考查的知识点为五四运动前后的新文化运动的区别。第一次世界大战及巴黎和会都暴露出资本主义制度固有的不可克服的矛盾以及资产阶级民主的弱点，巴黎和会的外交失败使中国的先进分子对西方资产阶级民主主义产生怀疑。B选项正确。新文化运动一开始就是对中国传统文化的怀疑。A选项错误。五四运动是中国工人阶级登上历史舞台的标志，此前的一战并没有让中国先进分子认识到工人阶级的重要作用。C选项错误。五四运动之前的新文化运动把改造国民性置于优先的地位。D选项错误。故正确答案为B。");
        s11.add(s4);
        SingleChoice s5 = new SingleChoice("1905年11月，在同盟会机关报《民报》发刊词中，孙中山将同盟会的纲领概括为三大主义，后被称为三民主义。在这一时期，孙中山主张()。","建立“民族独立的国家”","扫除“恶劣政治的根本”","实现“耕者有其田”","民权应“为一般平民所共有”","AB",2,"A正确民族主义即民族革命要求建立民族独立的国家; B正确，民权主义即政治革命要扫除“恶劣政治之根本”;C、D错误，CD是新三民主义，相对于旧三民主义的发展。故正确答案为AB。");
        s11.add(s5);
        SingleChoice s6 = new SingleChoice("邓小平指出:社会主义究竟是个什么样子，苏联搞了很多年，也并没有完全搞清楚。可能列宁的思路比较好，搞了个新经济政策，但是后来苏联的模式僵化了。列宁新经济政策关于社会主义的思维之所以“比较好”是因为()。","提出了比较系统的社会主义建设纲领","根据俄国的实际情况来探索社会主义建设的道路","为俄国找到了一种比较成熟的社会发展模式","按照马克思恩格斯关于未来社会的设想来建设社会主义","B",1,"本题考查的知识点为列宁领导下的苏维埃俄国对社会主义的探索。1921年3月，俄共(布)召开十大，决定从战时共产主义政策过渡到以发展商品经济为主要特征的新经济政策。这一决定，表明列宁的社会主义建设思想发生了重大转变，对俄国社会主义的发展道路有了新的认识，标志着列宁正在找到一条符合俄国情况的建设社会主义的道路。B项正确。A项错误，列宁虽然对建设社会主义提出过许多精辟的论述，但并没有提出比较系统的社会主义建设纲领; C项错误，列宁并没有为俄国找到一种成熟的社会发展模式，新经济政策也是一种探索性质的道路; D项错误,列宁并不是按照马克思主义关于社会主义的设想建设俄国的社会主义，而是将马克思主义与俄国的具体国情相结合，建设俄国的社会主义。故正确答案为B。");
        s11.add(s6);
        singleChoices = new ArrayList<>();
        for(int i = 0;i<num;i++){
            SingleChoice s = s11.get(i);
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
        mViewPager.setOffscreenPageLimit(singleChoices.size());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.i("PageChange-Select", "position:" + position);
                Log.i("当前页seclect",""+mPosition);
                mPosition=position;
                for(int i =0;i<singleChoices.size();i++){
                    //初始化
                    if(!collect.containsKey(i)){
                        collect.put(i,false);
                        Log.i("第一次初始化collect","yes"+i);
                    }
                    if(!cuotiset.containsKey(i)){
                        cuotiset.put(i,false);
                        Log.i("第一次初始化cuotiset","yes"+i);

                    }
                }

                if(collect.get(mPosition)==true){
                    Log.i("收藏之前被选中","true");
                    ivShoucang.setImageResource(R.drawable.shoucangok);
                }else if(collect.get(mPosition)==false) {
                    Log.i("收藏之前没被选中","false");
                    ivShoucang.setImageResource(R.drawable.shoucang);
                }
                if(cuotiset.get(mPosition)==true){
                    Log.i("cuoti之前被选中","true");
                    ivCuoti.setImageResource(R.drawable.cuotiok);
                }else if(cuotiset.get(mPosition)==false) {
                    Log.i("错题之前没被选中","false");
                    ivCuoti.setImageResource(R.drawable.cuoti);
                }
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
                            ivShoucang.setImageResource(R.drawable.shoucang);
                            ivCuoti.setImageResource(R.drawable.cuoti);
                            keyNum=0;
                            keyNumA=false;
                            keyNumB=false;
                            keyNumC=false;
                            keyNumD=false;
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

        //更多（包含收藏和加入错题集）
        window = new CommonPopupWindow(this,R.layout.popup_gravity,500, ViewGroup.LayoutParams.WRAP_CONTENT) {
            @Override
            protected void initView() {
                View view=getContentView();
                tvCuoti = view.findViewById(R.id.tv_cuoti);
                tvShoucang = view.findViewById(R.id.tv_shoucang);
                ivShoucang = view.findViewById(R.id.iv_shoucang);
                ivCuoti = view.findViewById(R.id.iv_cuoti);
                llCuoti = view.findViewById(R.id.ll_cuoti);
                llShoucang = view.findViewById(R.id.ll_shoucang);
                ivShoucang.setSelected(false);
                ivCuoti.setSelected(false);
                llShoucang.setOnClickListener(new MyListener());
                llCuoti.setOnClickListener(new MyListener());
            }

            @Override
            protected void initEvent() {

            }
        };

        layoutGravity = new CommonPopupWindow.LayoutGravity(CommonPopupWindow.LayoutGravity.CENTER_HORI);




    }

    private void setViewListener() {
        MyListener listener = new MyListener();
        llOptA.setOnClickListener(listener);
        llOptB.setOnClickListener(listener);
        llOptC.setOnClickListener(listener);
        llOptD.setOnClickListener(listener);
        ivMore.setOnClickListener(listener);
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
        ivMore = findViewById(R.id.iv_more);
        ivOpt1 = view.findViewById(R.id.iv_opt1);
        ivOpt2 = view.findViewById(R.id.iv_opt2);
        ivOpt3 = view.findViewById(R.id.iv_opt3);
        ivOpt4 = view.findViewById(R.id.iv_opt4);
//        ivCuoti = view.findViewById(R.id.iv_cuoti);
//        ivShoucang = view.findViewById(R.id.iv_shoucang);
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

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ll_optA:
                    if(keyNumA==false){
                        keyNumA=true;
                        tvOptA.setTextColor(ExerciseActivity.this.getResources().getColor(R.color.colorSel));
                        keyNum++;
                    }else{
                        keyNumA=false;
                        tvOptA.setTextColor(ExerciseActivity.this.getResources().getColor(R.color.colorNoSel));
                        keyNum--;
                    }
                    if(keyNum==singleChoices.get(mPosition).getKeyNum()){
                        Log.i("mxy","点击完毕A");
                        judgmentOpt();
                    }
                    Log.i("mxy","点击了A");
                    break;
                case R.id.ll_optB:
                    if(keyNumB==false){
                        keyNumB=true;
                        tvOptB.setTextColor(ExerciseActivity.this.getResources().getColor(R.color.colorSel));
                        keyNum++;
                    }else{
                        keyNumB=false;
                        tvOptB.setTextColor(ExerciseActivity.this.getResources().getColor(R.color.colorNoSel));
                        keyNum--;
                    }
                    if(keyNum==singleChoices.get(mPosition).getKeyNum()){
                        Log.i("mxy","点击完毕B");
                        judgmentOpt();
                    }
                    break;
                case R.id.ll_optC:
                    if(keyNumC==false){
                        keyNumC=true;
                        tvOptC.setTextColor(ExerciseActivity.this.getResources().getColor(R.color.colorSel));
                        keyNum++;
                    }else{
                        keyNumC=false;
                        tvOptC.setTextColor(ExerciseActivity.this.getResources().getColor(R.color.colorNoSel));
                        keyNum--;
                    }
                    if(keyNum==singleChoices.get(mPosition).getKeyNum()){
                        Log.i("mxy","点击完毕C");
                        judgmentOpt();
                    }
                    break;
                case R.id.ll_optD:
                    if(keyNumD==false){
                        keyNumD=true;
                        tvOptD.setTextColor(ExerciseActivity.this.getResources().getColor(R.color.colorSel));
                        keyNum++;
                    }else{
                        keyNumD=false;
                        tvOptD.setTextColor(ExerciseActivity.this.getResources().getColor(R.color.colorNoSel));
                        keyNum--;
                    }
                    if(keyNum==singleChoices.get(mPosition).getKeyNum()){
                        Log.i("mxy","点击完毕D");
                        judgmentOpt();
                    }
                    break;
                case R.id.iv_more:
                    window.showBashOfAnchor(ivMore,layoutGravity,0,0);
                    break;
                case R.id.ll_shoucang:
                    if(collect.get(mPosition)==false) {
                        ivShoucang.setImageResource(R.drawable.shoucangok);
                        ivShoucang.setSelected(true);
                        ScaleAnimatorUtils.setScalse(ivShoucang);
                        collect.put(mPosition, true);
                        Toast.makeText(ExerciseActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                    } else {
                        ivShoucang.setImageResource(R.drawable.shoucang);
                        ivShoucang.setSelected(false);
                        collect.put(mPosition, false);
                        Log.i("当前取消收藏页",""+mPosition);
                        Toast.makeText(ExerciseActivity.this, "取消收藏", Toast.LENGTH_SHORT).show();
                    }

                    Log.i("mxy收藏posion",""+mPosition);
                    break;
                case R.id.ll_cuoti:
                    if(cuotiset.get(mPosition)==false) {
                        ivCuoti.setImageResource(R.drawable.cuotiok);
                        ivCuoti.setSelected(true);
                        ScaleAnimatorUtils.setScalse(ivCuoti);
                        cuotiset.put(mPosition, true);
                        Toast.makeText(ExerciseActivity.this, "添加错题成功", Toast.LENGTH_SHORT).show();
                    } else {
                        ivCuoti.setImageResource(R.drawable.cuoti);
                        ivCuoti.setSelected(false);
                        cuotiset.put(mPosition, false);
                        Log.i("当前取消添加页",""+mPosition);
                        Toast.makeText(ExerciseActivity.this, "取消添加错题", Toast.LENGTH_SHORT).show();
                    }
                    break;

            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void judgmentOpt() {
        switch (currentOpt){
            case "A":
                ivOpt1.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes));
                ivOpt2.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no));
                ivOpt3.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no));
                ivOpt4.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
                break;
            case "B":
                ivOpt1.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no));
                ivOpt2.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes));
                ivOpt3.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no));
                ivOpt4.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
                break;
            case "C":
                ivOpt1.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no));
                ivOpt2.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no));
                ivOpt3.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes));
                ivOpt4.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
                break;
            case "D":
                Log.i("mxy","正确答案是D");
                ivOpt1.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt2.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt3.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt4.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
                break;
            case "AB":
                Log.i("mxy","正确答案是AB");
                ivOpt1.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt2.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt3.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt4.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
                break;
            case "AC":
                Log.i("mxy","正确答案是AC");
                ivOpt1.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt2.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt3.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt4.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
                break;
            case "AD":
                Log.i("mxy","正确答案是AD");
                ivOpt1.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt2.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt3.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt4.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
                break;
            case "ABC":
                Log.i("mxy","正确答案是ABC");
                ivOpt1.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt2.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt3.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt4.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
                break;
            case "ABD":
                Log.i("mxy","正确答案是ABD");
                ivOpt1.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt2.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt3.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt4.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
                break;
            case "ABCD":
                Log.i("mxy","正确答案是ABCD");
                ivOpt1.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt2.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt3.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt4.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
                break;
            case "BC":
                Log.i("mxy","正确答案是BC");
                ivOpt1.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt2.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt3.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt4.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
                break;
            case "BD":
                Log.i("mxy","正确答案是BD");
                ivOpt1.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt2.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt3.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt4.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
                break;
            case "BCD":
                Log.i("mxy","正确答案是BCD");
                ivOpt1.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt2.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt3.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt4.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
                break;
            case "CD":
                Log.i("mxy","正确答案是CD");
                ivOpt1.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt2.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
                ivOpt3.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
                ivOpt4.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
                tvAnTitle.setText("解析：");
                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
                break;
        }

    }


}


//package net.onest.mypartprj;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Looper;
//import android.os.Message;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import androidx.viewpager.widget.ViewPager;
//
//import net.onest.mypartprj.beans.ChoiceInfo;
//import net.onest.mypartprj.beans.QuestionBank;
//import net.onest.mypartprj.beans.SingleChoice;
//import net.onest.mypartprj.utils.NoScrollViewPager;
//import net.onest.mypartprj.utils.ScaleAnimatorUtils;
//import net.onest.mypartprj.utils.ServerConfig;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.ProtocolException;
//import java.net.URL;
//import java.security.AlgorithmConstraints;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class ExerciseActivity extends Activity {
//    private ViewPager mViewPager;
//    private int mPosition;
//    private String currentOpt;
//    private TextView tvStem;
//    private TextView tvOptA;
//    private TextView tvOptB;
//    private TextView tvOptC;
//    private TextView tvOptD;
//    private TextView tvAnTitle;
//    private TextView tvAnalysis;
//    private LinearLayout llOptA;
//    private LinearLayout llOptB;
//    private LinearLayout llOptC;
//    private LinearLayout llOptD;
//    private ImageView ivMore;
//    private ImageView ivOpt1;
//    private ImageView ivOpt2;
//    private ImageView ivOpt3;
//    private ImageView ivOpt4;
//    private ImageView ivShoucang;
//    private ImageView ivCuoti;
//    private LinearLayout llShoucang;
//    private LinearLayout llCuoti;
//    private List<SingleChoice> singleChoices;
//    private List<View> myViewList;
//    private CommonPopupWindow window;
//    private TextView tvShoucang;
//    private TextView tvCuoti;
//    private CommonPopupWindow.LayoutGravity layoutGravity;
//    private int keyNum = 0;
//    private boolean keyNumA = false;
//    private boolean keyNumB = false;
//    private boolean keyNumC = false;
//    private boolean keyNumD = false;
//    private Handler myHandler;
//    private ChoiceInfo choiceInfo;
//    private boolean haveDone = false;
//    private Map<Integer,Boolean> collect = new HashMap<>();
//    private Map<Integer,Boolean> cuotiset = new HashMap<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_exercise);
//        myHandler = new Handler(Looper.getMainLooper()) {
//            @Override
//            public void handleMessage(@NonNull Message msg) {
//                switch (msg.what) {
//                    case 1:
//                        choiceInfo = (ChoiceInfo) msg.obj;
//                        if (null != choiceInfo) {
//                            singleChoices = choiceInfo.getSingleChoices();
//                            Log.i("handle", "" + singleChoices.size());
//                            mViewPager = findViewById(R.id.in_viewpager);
//                            myViewList = new ArrayList<>();
//                            LayoutInflater layoutInflater = getLayoutInflater().from(ExerciseActivity.this);
//                            Log.i("handle",""+singleChoices.size());
//                            for(int j = 0;j<singleChoices.size();j++){
//                                View view1 = layoutInflater.inflate(R.layout.exercise_first, null,false);
//                                myViewList.add(view1);
//                            }
//                            mViewPager.setAdapter(new MyPager(myViewList));
//                            mViewPager.setOffscreenPageLimit(singleChoices.size());
//                            mPosition = mViewPager.getCurrentItem();
//                            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                                @Override
//                                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//                                }
//
//                                @Override
//                                public void onPageSelected(int position) {
//                                    Log.i("PageChange-Select", "position:" + position);
//                                    Log.i("当前页seclect",""+mPosition);
//                                    mPosition=position;
//                                    for(int i =0;i<singleChoices.size();i++){
//                                        //初始化
//                                        if(!collect.containsKey(i)){
//                                            collect.put(i,false);
//                                            Log.i("第一次初始化collect","yes"+i);
//                                        }
//                                        if(!cuotiset.containsKey(i)){
//                                            cuotiset.put(i,false);
//                                            Log.i("第一次初始化cuotiset","yes"+i);
//
//                                        }
//                                    }
//
//                                    if(collect.get(mPosition)==true){
//                                        Log.i("收藏之前被选中","true");
//                                        ivShoucang.setImageResource(R.drawable.shoucangok);
//                                    }else if(collect.get(mPosition)==false) {
//                                        Log.i("收藏之前没被选中","false");
//                                        ivShoucang.setImageResource(R.drawable.shoucang);
//                                    }
//                                    if(cuotiset.get(mPosition)==true){
//                                        Log.i("cuoti之前被选中","true");
//                                        ivCuoti.setImageResource(R.drawable.cuotiok);
//                                    }else if(cuotiset.get(mPosition)==false) {
//                                        Log.i("错题之前没被选中","false");
//                                        ivCuoti.setImageResource(R.drawable.cuoti);
//                                    }
//                                }
//
//                                @Override
//                                public void onPageScrollStateChanged(int state) {
//                                    switch (state) {
//                                        case ViewPager.SCROLL_STATE_IDLE:
//                                            Log.i("PageChange-State", "state:SCROLL_STATE_IDLE(滑动闲置或滑动结束)");
//
//
//                                            break;
//                                        case ViewPager.SCROLL_STATE_DRAGGING:
//                                            Log.i("PageChange-State", "state:SCROLL_STATE_DRAGGING(手势滑动中)");
//                                            Log.i("当前页",""+mPosition);
//
//                                            if(mPosition<singleChoices.size()-1){
//                                                View view = myViewList.get(mPosition+1);
//                                                initView(view,singleChoices.get(mPosition+1));
//                                                keyNum=0;
//                                                keyNumA=false;
//                                                keyNumB=false;
//                                                keyNumC=false;
//                                                keyNumD=false;
//                                                setViewListener();
//                                            }
//                                            break;
//                                        case ViewPager.SCROLL_STATE_SETTLING:
//                                            Log.i("PageChange-State", "state:SCROLL_STATE_SETTLING(代码执行滑动中)");
//                                            break;
//                                        default:
//                                            break;
//                                    }
//
//                                }
//                            });
//                            View view = myViewList.get(mPosition);
//                            initView(view,singleChoices.get(mPosition));
//                            setViewListener();
//                            //更多（包含收藏和加入错题集）
//                            window = new CommonPopupWindow(ExerciseActivity.this,R.layout.popup_gravity,500, ViewGroup.LayoutParams.WRAP_CONTENT) {
//                                @Override
//                                protected void initView() {
//                                    Log.i("执行",""+mPosition);
//                                    View view=getContentView();
//                                    tvCuoti = view.findViewById(R.id.tv_cuoti);
//                                    tvShoucang = view.findViewById(R.id.tv_shoucang);
//                                    ivShoucang = view.findViewById(R.id.iv_shoucang);
//                                    ivCuoti = view.findViewById(R.id.iv_cuoti);
//                                    llCuoti = view.findViewById(R.id.ll_cuoti);
//                                    llShoucang = view.findViewById(R.id.ll_shoucang);
//                                    ivShoucang.setSelected(false);
//                                    ivCuoti.setSelected(false);
//                                    llShoucang.setOnClickListener(new MyListener());
//                                    llCuoti.setOnClickListener(new MyListener());
//                                }
//
//                                @Override
//                                protected void initEvent() {
//
//                                }
//                            };
//
//                            layoutGravity = new CommonPopupWindow.LayoutGravity(CommonPopupWindow.LayoutGravity.CENTER_HORI);
//                        }
//                        break;
//                }
//            }
//        };
//
//        Intent result = getIntent();
//        String fenlei = result.getStringExtra("fenlei");
//        Log.i("跳转练习", fenlei);
//        String[] arr = fenlei.split("&");
//        String kemu = arr[0];
//        int num = Integer.parseInt(arr[1]);
//        int kemusta = Integer.parseInt(arr[2]);
//        collect.put(0,false);
//        cuotiset.put(0,false);
//        if (kemusta == 1) {
//            String url1 = ServerConfig.SEVER_ADDR + "/"
//                    + ServerConfig.NET_HOME + "/downloadsMoChoices";
//            Log.i("mxy", "默认的" + kemu);
//            downloadMoChoice(url1, kemu);
//        } else {
//            String url2 = ServerConfig.SEVER_ADDR + "/"
//                    + ServerConfig.NET_HOME + "/downloadsZiChoices";
//            Log.i("mxy", "自定义的");
//            downloadZiChoice(url2, kemu);
//        }
//    }
//
//    /**
//     * 在自定义题库中加载题目
//     * @param url2
//     */
//    private void downloadZiChoice(String url2,String kemu) {
//        new Thread(){
//            @Override
//            public void run() {
//
//            }
//        }.start();
//    }
//
//    /**
//     * 在默认题库中加载题目
//     * @param url1
//     */
//    private void downloadMoChoice(final String url1, final String kemu) {
//        new Thread(){
//            @Override
//            public void run() {
//                try {
//                    URL url = new URL(url1);
//                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//                    connection.setRequestMethod("POST");
//                    connection.setDoOutput(true);
//                    OutputStream out = connection.getOutputStream();
//                    String keyValue = ""+kemu;
//                    out.write(keyValue.getBytes());
//                    connection.getInputStream();
//                    out.close();
//
//                    //获取服务器返回数据判断是否登陆成功
//                    InputStream in = connection.getInputStream();
//                    //读数据（Json串),循环读写方式
//                    byte[] bytes = new byte[131072];
//                    StringBuffer buffer = new StringBuffer();
//                    int len = -1;
//                    while((len = in.read(bytes,0,bytes.length))!=-1){
//                        buffer.append(new String(bytes, 0, len));
//                    }
//                    String result = buffer.toString();
//                    Log.i("mxy","json串+Exersize："+result);
//                    in.close();
//
//                    //先将Json串解析成外部ChoiceInfo对象
//                    //创建ChoiceInfo对象和SingleChoice集合对象
//                    ChoiceInfo choiceInfo = new ChoiceInfo();
//                    List<SingleChoice> singleChoices = new ArrayList<>();
//                    //创建外层JSONObject
//                    JSONObject JUsers = new JSONObject(result);
//                    JSONArray jArray = JUsers.getJSONArray("choices");
//                    //遍历JSONArray对象,解析其中的每个元素
//                    for(int i = 0;i<jArray.length();i++){
//                        //获取当前的JSONObject对象
//                        JSONObject JChoice = jArray.getJSONObject(i);
//                        //获取当前元素中的属性信息
//                        String username = JChoice.getString("username");
//                        String kemu = JChoice.getString("kemu");
//                        int tiid = JChoice.getInt("tiid");
//                        String stem = JChoice.getString("stem");
//                        String opta = JChoice.getString("opta");
//                        String optb = JChoice.getString("optb");
//                        String optc = JChoice.getString("optc");
//                        String optd = JChoice.getString("optd");
//                        String correct = JChoice.getString("correct");
//                        int keynum = JChoice.getInt("keynum");
//                        String analysis = JChoice.getString("analysis");
//                        int motista = JChoice.getInt("motista");
//                        SingleChoice singleChoice = new SingleChoice(username,kemu,tiid,stem,opta,optb,optc,optd,correct,keynum,analysis,motista);
//                        singleChoices.add(singleChoice);
//                    }
//                    choiceInfo.setSingleChoices(singleChoices);
//                    //2. 通过发送Message对象，将数据发布出去
//                    //获取Message对象
//                    Message msg = myHandler.obtainMessage();
//                    //设置Message对象的属性（what、obj）
//                    msg.what = 1;
//                    msg.obj = choiceInfo;
//                    //发送Message对象
//                    myHandler.sendMessage(msg);
//
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//    }
//
//    private void setViewListener() {
//        MyListener listener = new MyListener();
//        llOptA.setOnClickListener(listener);
//        llOptB.setOnClickListener(listener);
//        llOptC.setOnClickListener(listener);
//        llOptD.setOnClickListener(listener);
//        ivMore.setOnClickListener(listener);
//    }
//
//    private void initView(View view,SingleChoice singleChoice) {
//        tvStem = view.findViewById(R.id.tv_stem);
//        tvOptA = view.findViewById(R.id.tv_optA);
//        tvOptB = view.findViewById(R.id.tv_optB);
//        tvOptC = view.findViewById(R.id.tv_optC);
//        tvOptD = view.findViewById(R.id.tv_optD);
//        llOptA = view.findViewById(R.id.ll_optA);
//        llOptB = view.findViewById(R.id.ll_optB);
//        llOptC = view.findViewById(R.id.ll_optC);
//        llOptD = view.findViewById(R.id.ll_optD);
//        ivMore = findViewById(R.id.iv_more);
//        ivOpt1 = view.findViewById(R.id.iv_opt1);
//        ivOpt2 = view.findViewById(R.id.iv_opt2);
//        ivOpt3 = view.findViewById(R.id.iv_opt3);
//        ivOpt4 = view.findViewById(R.id.iv_opt4);
////        ivCuoti = view.findViewById(R.id.iv_cuoti);
////        ivShoucang = view.findViewById(R.id.iv_shoucang);
//        tvAnTitle = view.findViewById(R.id.tv_antitle);
//        tvAnalysis = view.findViewById(R.id.tv_analysis);
//        currentOpt = singleChoice.getCorrect();
//        Log.i("cu",currentOpt);
//        tvStem.setText(singleChoice.getStem());
//        tvOptA.setText(singleChoice.getOptionA());
//        tvOptB.setText(singleChoice.getOptionB());
//        tvOptC.setText(singleChoice.getOptionC());
//        tvOptD.setText(singleChoice.getOptionD());
//
//    }
//
//    class MyListener implements View.OnClickListener{
//
//        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//        @Override
//        public void onClick(View v) {
//            String urlShoucang = ServerConfig.SEVER_ADDR + "/"
//                    + ServerConfig.NET_HOME + "/shoucangcaozuo";
//            String urlCuoti = ServerConfig.SEVER_ADDR + "/"
//                    + ServerConfig.NET_HOME + "/cuoticaozuo";
//            switch (v.getId()){
//                case R.id.ll_optA:
//                    if(keyNumA==false){
//                        keyNumA=true;
//                        tvOptA.setTextColor(ExerciseActivity.this.getResources().getColor(R.color.colorSel));
//                        keyNum++;
//                    }else{
//                        keyNumA=false;
//                        tvOptA.setTextColor(ExerciseActivity.this.getResources().getColor(R.color.colorNoSel));
//                        keyNum--;
//                    }
//                    if(keyNum==singleChoices.get(mPosition).getKeyNum()){
//                        Log.i("mxy","点击完毕A");
//                        judgmentOpt();
//                    }
//                    Log.i("mxy","点击了A");
//                    break;
//                case R.id.ll_optB:
//                    if(keyNumB==false){
//                        keyNumB=true;
//                        tvOptB.setTextColor(ExerciseActivity.this.getResources().getColor(R.color.colorSel));
//                        keyNum++;
//                    }else{
//                        keyNumB=false;
//                        tvOptB.setTextColor(ExerciseActivity.this.getResources().getColor(R.color.colorNoSel));
//                        keyNum--;
//                    }
//                    if(keyNum==singleChoices.get(mPosition).getKeyNum()){
//                        Log.i("mxy","点击完毕B");
//                        judgmentOpt();
//                    }
//                    break;
//                case R.id.ll_optC:
//                    if(keyNumC==false){
//                        keyNumC=true;
//                        tvOptC.setTextColor(ExerciseActivity.this.getResources().getColor(R.color.colorSel));
//                        keyNum++;
//                    }else{
//                        keyNumC=false;
//                        tvOptC.setTextColor(ExerciseActivity.this.getResources().getColor(R.color.colorNoSel));
//                        keyNum--;
//                    }
//                    if(keyNum==singleChoices.get(mPosition).getKeyNum()){
//                        Log.i("mxy","点击完毕C");
//                        judgmentOpt();
//                    }
//                    break;
//                case R.id.ll_optD:
//                    if(keyNumD==false){
//                        keyNumD=true;
//                        tvOptD.setTextColor(ExerciseActivity.this.getResources().getColor(R.color.colorSel));
//                        keyNum++;
//                    }else{
//                        keyNumD=false;
//                        tvOptD.setTextColor(ExerciseActivity.this.getResources().getColor(R.color.colorNoSel));
//                        keyNum--;
//                    }
//                    if(keyNum==singleChoices.get(mPosition).getKeyNum()){
//                        Log.i("mxy","点击完毕D");
//                        judgmentOpt();
//                    }
//                    break;
//                case R.id.iv_more:
//                    window.showBashOfAnchor(ivMore,layoutGravity,0,0);
//                    break;
//                case R.id.ll_shoucang:
//                    if(collect.get(mPosition)==false) {
//                        ivShoucang.setImageResource(R.drawable.shoucangok);
//                        ivShoucang.setSelected(true);
//                        ScaleAnimatorUtils.setScalse(ivShoucang);
//                        collect.put(mPosition, true);
//                        shoucangCaozuo(1, urlShoucang);
//                        Toast.makeText(ExerciseActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
//                    } else {
//                        ivShoucang.setImageResource(R.drawable.shoucang);
//                        ivShoucang.setSelected(false);
//                        collect.put(mPosition, false);
//                        Log.i("当前取消收藏页",""+mPosition);
//                        shoucangCaozuo(2, urlShoucang);
//                        Toast.makeText(ExerciseActivity.this, "取消收藏", Toast.LENGTH_SHORT).show();
//                    }
//
//                    Log.i("mxy收藏posion",""+mPosition);
//                    break;
//                case R.id.ll_cuoti:
//                    if(cuotiset.get(mPosition)==false) {
//                        ivCuoti.setImageResource(R.drawable.cuotiok);
//                        ivCuoti.setSelected(true);
//                        ScaleAnimatorUtils.setScalse(ivCuoti);
//                        cuotiset.put(mPosition, true);
//                        cuotiCaozuo(1, urlCuoti);
//                        Toast.makeText(ExerciseActivity.this, "添加错题成功", Toast.LENGTH_SHORT).show();
//                    } else {
//                        ivCuoti.setImageResource(R.drawable.cuoti);
//                        ivCuoti.setSelected(false);
//                        cuotiset.put(mPosition, false);
//                        Log.i("当前取消添加页",""+mPosition);
//                        cuotiCaozuo(2, urlCuoti);
//                        Toast.makeText(ExerciseActivity.this, "取消添加错题", Toast.LENGTH_SHORT).show();
//                    }
//                    break;
//
//            }
//        }
//    }
//
//    /**
//     * 错题操作
//     * @param i
//     * @param urlCuoti
//     */
//    private void cuotiCaozuo(final int i, final String urlCuoti) {
//        new Thread(){
//            @Override
//            public void run() {
//                try {
//                    URL url = new URL(urlCuoti);
//                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//                    //设置网络请求的方式为POST
//                    connection.setRequestMethod("POST");
//                    OutputStream out = connection.getOutputStream();
//                    SingleChoice singleChoice = singleChoices.get(mPosition);
//                    String keyValue = i+"&"+singleChoice.getTiid()+"&"+singleChoice.getMotista()+"&"+singleChoice.getKemu()+"&"+singleChoice.getStem()+"&"+singleChoice.getOptionA()
//                            +"&"+singleChoice.getOptionB()+"&"+singleChoice.getOptionC()+"&"+singleChoice.getOptionD()+"&"+singleChoice.getCorrect()+"&"+singleChoice.getAnalysis()+"&"+
//                            singleChoice.getKeyNum();
//                    Log.i("keyvalue:",keyValue);
//                    out.write(keyValue.getBytes());
//                    connection.getInputStream();
//                    out.close();
//
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (ProtocolException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//    }
//
//    /**
//     * 收藏操作
//     * @param i
//     * @param urlShoucang
//     */
//    private void shoucangCaozuo(final int i, final String urlShoucang) {
//        new Thread(){
//            @Override
//            public void run() {
//                try {
//                    URL url = new URL(urlShoucang);
//                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//                    //设置网络请求的方式为POST
//                    connection.setRequestMethod("POST");
//                    OutputStream out = connection.getOutputStream();
//                    String keyValue = i+"&"+singleChoices.get(mPosition).getTiid()+"&"+singleChoices.get(mPosition).getMotista();
//                    Log.i("keyvalue:",keyValue);
//                    out.write(keyValue.getBytes());
//                    connection.getInputStream();
//                    out.close();
//
//
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (ProtocolException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    private void judgmentOpt() {
//        Log.i("judgment",""+currentOpt);
//        switch (currentOpt){
//            case "A":
//                ivOpt1.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes));
//                ivOpt2.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no));
//                ivOpt3.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no));
//                ivOpt4.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no));
//                tvAnTitle.setText("解析：");
//                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
//                break;
//            case "B":
//                ivOpt1.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no));
//                ivOpt2.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes));
//                ivOpt3.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no));
//                ivOpt4.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no));
//                tvAnTitle.setText("解析：");
//                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
//                break;
//            case "C":
//                ivOpt1.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no));
//                ivOpt2.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no));
//                ivOpt3.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes));
//                ivOpt4.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no));
//                tvAnTitle.setText("解析：");
//                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
//                break;
//            case "D":
//                Log.i("mxy","正确答案是D");
//                ivOpt1.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
//                ivOpt2.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
//                ivOpt3.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
//                ivOpt4.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
//                tvAnTitle.setText("解析：");
//                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
//                break;
//            case "AB":
//                Log.i("mxy","正确答案是AB");
//                ivOpt1.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
//                ivOpt2.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
//                ivOpt3.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
//                ivOpt4.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
//                tvAnTitle.setText("解析：");
//                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
//                break;
//            case "AC":
//                Log.i("mxy","正确答案是AC");
//                ivOpt1.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
//                ivOpt2.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
//                ivOpt3.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
//                ivOpt4.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
//                tvAnTitle.setText("解析：");
//                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
//                break;
//            case "AD":
//                Log.i("mxy","正确答案是AD");
//                ivOpt1.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
//                ivOpt2.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
//                ivOpt3.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
//                ivOpt4.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
//                tvAnTitle.setText("解析：");
//                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
//                break;
//            case "ABC":
//                Log.i("mxy","正确答案是ABC");
//                ivOpt1.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
//                ivOpt2.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
//                ivOpt3.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
//                ivOpt4.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
//                tvAnTitle.setText("解析：");
//                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
//                break;
//            case "ABD":
//                Log.i("mxy","正确答案是ABD");
//                ivOpt1.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
//                ivOpt2.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
//                ivOpt3.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
//                ivOpt4.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
//                tvAnTitle.setText("解析：");
//                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
//                break;
//            case "ABCD":
//                Log.i("mxy","正确答案是ABCD");
//                ivOpt1.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
//                ivOpt2.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
//                ivOpt3.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
//                ivOpt4.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
//                tvAnTitle.setText("解析：");
//                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
//                break;
//            case "BC":
//                Log.i("mxy","正确答案是BC");
//                ivOpt1.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
//                ivOpt2.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
//                ivOpt3.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
//                ivOpt4.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
//                tvAnTitle.setText("解析：");
//                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
//                break;
//            case "BD":
//                Log.i("mxy","正确答案是BD");
//                ivOpt1.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
//                ivOpt2.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
//                ivOpt3.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
//                ivOpt4.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
//                tvAnTitle.setText("解析：");
//                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
//                break;
//            case "BCD":
//                Log.i("mxy","正确答案是BCD");
//                ivOpt1.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
//                ivOpt2.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
//                ivOpt3.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
//                ivOpt4.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
//                tvAnTitle.setText("解析：");
//                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
//                break;
//            case "CD":
//                Log.i("mxy","正确答案是CD");
//                ivOpt1.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
//                ivOpt2.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.no,null));
//                ivOpt3.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
//                ivOpt4.setImageDrawable(ExerciseActivity.this.getResources().getDrawable(R.drawable.yes,null));
//                tvAnTitle.setText("解析：");
//                tvAnalysis.setText(singleChoices.get(mPosition).getAnalysis());
//                break;
//        }
//
//    }
//
//
//}
