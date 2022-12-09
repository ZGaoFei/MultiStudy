package com.zgf.otherlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zgf.otherlibrary.randomlayout.CircleRandomTestActivity;
import com.zgf.otherlibrary.randomlayout.RandomLayoutActivity;
import com.zgf.otherlibrary.randomlayout.RandomTestActivity;
import com.zgf.otherlibrary.vlayout.DelegateActivity;
import com.zgf.otherlibrary.vlayout.OnePlusNLayoutActivity;
import com.zgf.otherlibrary.vlayout.StaggeredGridActivity;
import com.zgf.otherlibrary.vlayout.VLayout2Activity;
import com.zgf.otherlibrary.vlayout.VLayoutActivity;

public class OtherMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_main);

        initView();
    }

    private void initView() {
        findViewById(R.id.other_tv_vlayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OtherMainActivity.this, VLayoutActivity.class));
            }
        });

        findViewById(R.id.other_tv_delegate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OtherMainActivity.this, DelegateActivity.class));
            }
        });

        findViewById(R.id.other_tv_one_plus_n).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OtherMainActivity.this, OnePlusNLayoutActivity.class));
            }
        });

        findViewById(R.id.other_tv_staggered_grid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OtherMainActivity.this, StaggeredGridActivity.class));
            }
        });

        findViewById(R.id.other_tv_vlayout2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OtherMainActivity.this, VLayout2Activity.class));
            }
        });

        findViewById(R.id.other_tv_random_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OtherMainActivity.this, RandomLayoutActivity.class));
            }
        });

        findViewById(R.id.other_tv_random_test_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OtherMainActivity.this, RandomTestActivity.class));
            }
        });

        findViewById(R.id.other_tv_circle_random_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OtherMainActivity.this, CircleRandomTestActivity.class));
            }
        });
    }
}