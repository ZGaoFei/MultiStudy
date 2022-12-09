package com.zgf.otherlibrary.randomlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.zgf.otherlibrary.R;
import com.zgf.otherlibrary.randomlayout.view.RandomLayout;

public class RandomLayoutActivity extends AppCompatActivity {

    private RandomLayout randomLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_layout);

        initView();
    }

    private void initView() {
        randomLayout = findViewById(R.id.random_layout);
        for (int i = 0; i < 30; i++) {
            View view = inflateItemView();
            randomLayout.addView(view);
        }
    }

    private View inflateItemView() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_layout, null);
        return view;
    }
}