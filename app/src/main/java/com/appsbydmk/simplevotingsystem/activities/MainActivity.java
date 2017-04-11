package com.appsbydmk.simplevotingsystem.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.appsbydmk.simplevotingsystem.R;

public class MainActivity extends AppCompatActivity {

    TextView tvAdmin;
    Intent adminIntent, voterIntent;
    Button btnVoter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnVoter = (Button) this.findViewById(R.id.btn_voter);
        tvAdmin = (TextView) this.findViewById(R.id.tv_admin);

        tvAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminIntent = new Intent(MainActivity.this, AdminActivity.class);
                MainActivity.this.startActivity(adminIntent);
            }
        });

        btnVoter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voterIntent = new Intent(MainActivity.this, VotingActivity.class);
                MainActivity.this.startActivity(voterIntent);
                MainActivity.this.finish();
            }
        });
    }
}
