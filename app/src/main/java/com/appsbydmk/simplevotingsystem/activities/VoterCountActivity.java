package com.appsbydmk.simplevotingsystem.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.NumberPicker;

import com.appsbydmk.simplevotingsystem.R;

public class VoterCountActivity extends AppCompatActivity {

    NumberPicker npVoterCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter_count);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        npVoterCount = (NumberPicker) this.findViewById(R.id.np_voter_count);
        npVoterCount.setMinValue(0);
        npVoterCount.setMaxValue(100);
        npVoterCount.setWrapSelectorWheel(true);
        npVoterCount.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            }
        });
    }
}
