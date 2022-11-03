package com.zgf.multistudy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zgf.ktlibrary.KtMainActivity;

import io.flutter.embedding.android.FlutterActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    void initView() {
        findViewById(R.id.tv_open_flutter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFlutterPage();
            }
        });

        findViewById(R.id.tv_open_kotlin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, KtMainActivity.class));
            }
        });
    }

    void openFlutterPage() {
        startActivity(FlutterActivity.createDefaultIntent(this));
    }
}