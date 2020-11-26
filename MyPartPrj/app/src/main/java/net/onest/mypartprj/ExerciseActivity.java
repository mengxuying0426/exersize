package net.onest.mypartprj;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class ExerciseActivity extends AppCompatActivity {
    private ViewPager viewPager;

    private List<View> myViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_exercise);
        viewPager = findViewById(R.id.in_viewpager);
        myViewList = new ArrayList<>();
        LayoutInflater layoutInflater = getLayoutInflater().from(ExerciseActivity.this);
        View view1 = layoutInflater.inflate(R.layout.exercise_first, null);
        View view2 = layoutInflater.inflate(R.layout.exercise_second, null);

        myViewList.add(view1);
        myViewList.add(view2);
        viewPager.setAdapter(new MyPager(myViewList));
        viewPager.setPageTransformer(true, new DepthPageTransformer());
    }
}
