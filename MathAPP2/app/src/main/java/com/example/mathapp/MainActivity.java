package com.example.mathapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnFeature1).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CompareNumberActivity.class)));
        findViewById(R.id.btnFeature2).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, OrderNumberActivity.class)));
        findViewById(R.id.btnFeature3).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ComposeNumberActivity.class)));
    }

}

