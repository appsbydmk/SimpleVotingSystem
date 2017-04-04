package com.appsbydmk.simplevotingsystem.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;

import com.appsbydmk.simplevotingsystem.R;

public class VotingActivity extends AppCompatActivity {

    ListView lvCandidates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lvCandidates = (ListView) this.findViewById(R.id.lv_all_candidates);
        lvCandidates.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
    }
}
