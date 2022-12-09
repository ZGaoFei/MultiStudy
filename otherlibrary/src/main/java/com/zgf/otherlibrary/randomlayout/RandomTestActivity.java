package com.zgf.otherlibrary.randomlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.zgf.otherlibrary.R;
import com.zgf.otherlibrary.randomlayout.view.RandomTestLayout;

public class RandomTestActivity extends AppCompatActivity {

    private RandomTestLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_test);

        initView();
    }

    private void initView() {
        layout = findViewById(R.id.random_test_layout);
        for (int i = 0; i < 30; i++) {
            View view = inflateItemView();
            layout.addViewToRandomList(view);
            layout.addViewAtRandomXY(view, i);
        }
    }

    private View inflateItemView() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_layout, null);
        return view;
    }
}