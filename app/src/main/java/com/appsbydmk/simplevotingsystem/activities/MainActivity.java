package com.appsbydmk.simplevotingsystem.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.appsbydmk.simplevotingsystem.R;
import com.appsbydmk.simplevotingsystem.helpers.HelperConstants;

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
                MainActivity.this.startActivityForResult(voterIntent, HelperConstants.VOTING_STATUS_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == HelperConstants.VOTING_STATUS_CODE) {
            if (resultCode == RESULT_OK) {
                String message = data.getStringExtra("message");
                if (message.equals("thankYou")) {
                    Toast.makeText(getBaseContext(), "Thank you for voting!", Toast.LENGTH_SHORT).show();
                } else if (message.equals("alreadyVoted")) {
                    Toast.makeText(getBaseContext(), "You have already voted!", Toast.LENGTH_SHORT).show();
                } else if (message.equals("allVoted")) {
                    Toast.makeText(getBaseContext(), "All voters have already voted!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
