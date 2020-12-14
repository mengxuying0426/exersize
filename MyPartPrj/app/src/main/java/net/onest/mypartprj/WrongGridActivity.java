package net.onest.mypartprj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class WrongGridActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent result = getIntent();
        String xinxi = result.getStringExtra("tikufenlei");
        String[] arr = xinxi.split("&");
        String kemu = arr[0];
        int kemusta = Integer.parseInt(arr[1]);
    }
}
