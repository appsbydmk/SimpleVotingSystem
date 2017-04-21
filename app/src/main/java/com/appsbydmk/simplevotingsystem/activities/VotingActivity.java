package com.appsbydmk.simplevotingsystem.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.appsbydmk.simplevotingsystem.R;
import com.appsbydmk.simplevotingsystem.helpers.CandidatesFileHelper;

public class VotingActivity extends AppCompatActivity {

    private ListView lvCandidates;
    private ArrayAdapter<String> candidatesAdapter;
    private CandidatesFileHelper candidatesFileHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        candidatesFileHelper = new CandidatesFileHelper(this);
        lvCandidates = (ListView) this.findViewById(R.id.lv_all_candidates);
        lvCandidates.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        candidatesAdapter = new ArrayAdapter<String>(getBaseContext(),
                android.R.layout.simple_list_item_1, candidatesFileHelper.getAllCandidates());
        lvCandidates.setAdapter(candidatesAdapter);
    }
}
