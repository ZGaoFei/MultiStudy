package com.zgf.multistudy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

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
    }

    void openFlutterPage() {
        startActivity(FlutterActivity.createDefaultIntent(this));
    }
}