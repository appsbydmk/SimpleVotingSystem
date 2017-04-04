package com.appsbydmk.simplevotingsystem.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.appsbydmk.simplevotingsystem.R;

public class MainActivity extends AppCompatActivity {

    TextView tvAdmin;
    Intent adminIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAdmin = (TextView) this.findViewById(R.id.tv_admin);
        tvAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminIntent = new Intent(MainActivity.this, AdminActivity.class);
                MainActivity.this.startActivity(adminIntent);
            }
        });
    }
}
