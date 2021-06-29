package com.shop.bestshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {
    public static final int TEXT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void aboutme(View view) {
        Intent intent = new Intent(this,aboutActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    public void manage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }
}