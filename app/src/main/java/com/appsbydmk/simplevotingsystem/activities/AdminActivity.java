package com.appsbydmk.simplevotingsystem.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.appsbydmk.simplevotingsystem.R;

public class AdminActivity extends AppCompatActivity {
    Intent adminPanelIntent;
    TextInputEditText tieAdminUsername, tieAdminPassword;
    TextInputLayout tilAdminUsername, tilAdminPassword;
    Button btnAdminLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tieAdminUsername = (TextInputEditText) this.findViewById(R.id.tie_admin_username);
        tieAdminPassword = (TextInputEditText) this.findViewById(R.id.tie_admin_password);
        tilAdminUsername = (TextInputLayout) this.findViewById(R.id.til_admin_username);
        tilAdminPassword = (TextInputLayout) this.findViewById(R.id.til_admin_password);
        btnAdminLogin = (Button) this.findViewById(R.id.btn_admin_login);
        btnAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tieAdminUsername.getText().toString().equals("admin") && tieAdminPassword.getText().toString().equals("admin12345")) {
                    adminPanelIntent = new Intent(AdminActivity.this, AdminPanelActivity.class);
                    AdminActivity.this.startActivity(adminPanelIntent);
                    AdminActivity.this.finish();
                }
            }
        });
    }
}
