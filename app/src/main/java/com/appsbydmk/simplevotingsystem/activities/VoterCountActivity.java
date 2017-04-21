package com.appsbydmk.simplevotingsystem.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.appsbydmk.simplevotingsystem.R;
import com.appsbydmk.simplevotingsystem.helpers.VotersFileHelper;

public class VoterCountActivity extends AppCompatActivity {

    private NumberPicker npVoterCount;
    private VotersFileHelper votersFileHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter_count);

        votersFileHelper = new VotersFileHelper(this);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        npVoterCount = (NumberPicker) this.findViewById(R.id.np_voter_count);
        npVoterCount.setMinValue(0);
        npVoterCount.setMaxValue(100);
        npVoterCount.setWrapSelectorWheel(true);
        npVoterCount.setValue(votersFileHelper.getVoterCount());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        votersFileHelper.updateVoterCount(npVoterCount.getValue());
        Toast.makeText(getBaseContext(), "Voter Count changed.", Toast.LENGTH_SHORT).show();
    }
}
