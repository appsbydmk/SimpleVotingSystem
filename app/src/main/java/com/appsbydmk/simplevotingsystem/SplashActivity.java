package com.appsbydmk.simplevotingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.appsbydmk.simplevotingsystem.activities.MainActivity;

public class SplashActivity extends AppCompatActivity {
    Intent mainIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainIntent = new Intent(SplashActivity.this, MainActivity.class);
        this.startActivity(mainIntent);
        finish();
    }
}
