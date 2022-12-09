package com.zgf.otherlibrary.randomlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.zgf.otherlibrary.R;
import com.zgf.otherlibrary.randomlayout.view.CircleRandomLayout;

public class CircleRandomTestActivity extends AppCompatActivity {

    private CircleRandomLayout randomLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_random_test);

        initView();
    }

    private void initView() {
        randomLayout = findViewById(R.id.circle_random_layout);
        for (int i = 0; i < 30; i++) {
            View view = inflateItemView();
            view.setTag(i);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int tag = (int) v.getTag();
                    Log.e("zgf", "=====onClick=====" + tag);
                }
            });
            randomLayout.addView(view);
        }
    }

    private View inflateItemView() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_layout, null);
        return view;
    }
}