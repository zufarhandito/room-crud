package com.shop.bestshop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

public class aboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    public void btn_fb(View view) {
        Uri webpage = Uri.parse("https://www.facebook.com/wikara.humasta");
        Intent fbIntent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(fbIntent);
    }

    public void btn_tw(View view) {
        Uri webpage = Uri.parse("https://www.twitter.com");
        Intent twIntent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(twIntent);
    }

    public void btn_ig(View view) {
        Uri webpage = Uri.parse("https://www.instagram.com/wikarahumasta/");
        Intent igIntent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(igIntent);
    }
}