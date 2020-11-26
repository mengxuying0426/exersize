package net.onest.mypartprj.exersise;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.onest.mypartprj.ExerciseActivity;
import net.onest.mypartprj.R;

public class FragmentSecond extends Fragment {
    private View view;
    private Button btnEW1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            return view;
        }
        view = inflater.inflate(R.layout.fragment2,container,false);

        //获取控件
        btnEW1 = view.findViewById(R.id.btn_ew1);
        btnEW1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(view.getContext(), ExerciseActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        return view;
    }
}
