package com.appsbydmk.simplevotingsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.appsbydmk.simplevotingsystem.activities.MainActivity;
import com.appsbydmk.simplevotingsystem.helpers.HelperConstants;

public class SplashActivity extends AppCompatActivity {
    private Intent mainIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences firstLaunch = getSharedPreferences(HelperConstants.FIRST_LAUNCH, MODE_PRIVATE);

        SharedPreferences adminSettings = getSharedPreferences(HelperConstants.ADMIN_SETTINGS, MODE_PRIVATE);

        if (firstLaunch.getBoolean("first_launch", true)) {
            Log.d("Comments", "First Launch");
            firstLaunch.edit().putBoolean("first_launch", false).apply();
            adminSettings.edit().putString("admin_username", "admin").apply();
            adminSettings.edit().putString("admin_password", "admin12345").apply();
            Toast.makeText(getBaseContext(), "First Launch", Toast.LENGTH_SHORT).show();
        }
        mainIntent = new Intent(SplashActivity.this, MainActivity.class);
        this.startActivity(mainIntent);
        finish();
    }
}
