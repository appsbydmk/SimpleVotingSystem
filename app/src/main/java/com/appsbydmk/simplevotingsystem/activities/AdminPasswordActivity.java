package com.appsbydmk.simplevotingsystem.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.appsbydmk.simplevotingsystem.R;
import com.appsbydmk.simplevotingsystem.helpers.HelperConstants;

public class AdminPasswordActivity extends AppCompatActivity {

    Button btnSavePassword;
    TextInputEditText tieAdminPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_password);
        btnSavePassword = (Button) this.findViewById(R.id.btn_save);
        tieAdminPassword = (TextInputEditText) this.findViewById(R.id.tie_change_admin_password);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnSavePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences adminSettings = getSharedPreferences(HelperConstants.ADMIN_SETTINGS, MODE_PRIVATE);
                adminSettings.edit().putString("admin_password", tieAdminPassword.getText().toString()).apply();
                Toast.makeText(getBaseContext(), "Admin Password Changed!", Toast.LENGTH_SHORT).show();
                AdminPasswordActivity.this.setResult(HelperConstants.ADMIN_CHANGE_PASSWORD_REQUEST_CODE);
                AdminPasswordActivity.this.finish();
            }
        });
    }
}
