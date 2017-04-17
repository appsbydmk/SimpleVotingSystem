package com.appsbydmk.simplevotingsystem.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.appsbydmk.simplevotingsystem.R;
import com.appsbydmk.simplevotingsystem.helpers.HelperConstants;

public class AdminPanelActivity extends AppCompatActivity {

    private Intent candidateIntent, voterCountIntent, adminPasswordIntent;
    private Button btnViewUpdCandidate, btnSetVoterCount, btnAdminPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        btnViewUpdCandidate = (Button) this.findViewById(R.id.btn_ap_upd_view_candidates);
        btnSetVoterCount = (Button) this.findViewById(R.id.btn_ap_set_upd_voter_count);
        btnAdminPassword = (Button) this.findViewById(R.id.btn_ap_change_admin_password);
        btnViewUpdCandidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                candidateIntent = new Intent(AdminPanelActivity.this, CandidateCountActivity.class);
                AdminPanelActivity.this.startActivity(candidateIntent);
            }
        });
        btnSetVoterCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voterCountIntent = new Intent(AdminPanelActivity.this, VoterCountActivity.class);
                AdminPanelActivity.this.startActivity(voterCountIntent);
            }
        });
        btnAdminPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminPasswordIntent = new Intent(AdminPanelActivity.this, AdminPasswordActivity.class);
                AdminPanelActivity.this.startActivityForResult(adminPasswordIntent, HelperConstants.ADMIN_CHANGE_PASSWORD_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == HelperConstants.ADMIN_CHANGE_PASSWORD_REQUEST_CODE) {
            this.finish();
        }
    }
}
