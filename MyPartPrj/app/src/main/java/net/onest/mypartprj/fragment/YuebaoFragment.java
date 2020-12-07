package net.onest.mypartprj.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.onest.mypartprj.R;
import net.onest.mypartprj.VIPYueBaoActivity;


public class YuebaoFragment extends Fragment {
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //加载内容页面的布局文件（将内容页面的XML布局文件转成View类型的对象）
        view = inflater.inflate(R.layout.fragment_layout,container,false);

        //获取内容页面当中空间的引用
        TextView tvTitle = view.findViewById(R.id.tv_title);
        tvTitle.setText("组合贷款");
        Button btnReg = view.findViewById(R.id.btn_count);
        btnReg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                Intent intent = new Intent();
                intent.setClass(view.getContext(), VIPYueBaoActivity.class);
                view.getContext().startActivity(intent);

            }
        });

        return view;
    }
}
