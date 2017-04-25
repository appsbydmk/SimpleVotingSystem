package com.appsbydmk.simplevotingsystem.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AbsListView;
import android.widget.ListView;

import com.appsbydmk.simplevotingsystem.R;
import com.appsbydmk.simplevotingsystem.adapters.ElectionResultAdapter;
import com.appsbydmk.simplevotingsystem.helpers.VotingFileHelper;

import java.util.ArrayList;

public class ElectionsResultActivity extends AppCompatActivity {
    private VotingFileHelper votingFileHelper;
    private ListView lvElectionsResult;
    private ElectionResultAdapter electionResultAdapter;
    private String[] electionsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elections_result);
        votingFileHelper = new VotingFileHelper(this);
        electionsArray = null;
        lvElectionsResult = (ListView) this.findViewById(R.id.lv_elections_result);
        lvElectionsResult.setChoiceMode(AbsListView.CHOICE_MODE_NONE);
        lvElectionsResult.setEmptyView(this.findViewById(android.R.id.empty));
        if ((electionsArray = votingFileHelper.getElectionsArray()) != null) {
            electionResultAdapter = new ElectionResultAdapter(this.getCandidateNames(electionsArray), this.getCandidatesResult(electionsArray), this);
            lvElectionsResult.setAdapter(electionResultAdapter);
        }
    }

    private ArrayList<String> getCandidateNames(String[] electionsResult) {
        ArrayList<String> candidateNames = new ArrayList<>();
        for (int n = 0; n < electionsResult.length; n = n + 2) {
            candidateNames.add(electionsResult[n]);
        }
        return candidateNames;
    }

    private ArrayList<String> getCandidatesResult(String[] electionsResult) {
        ArrayList<String> candidateResult = new ArrayList<>();
        for (int n = 1; n < electionsResult.length; n = n + 2) {
            candidateResult.add(electionsResult[n]);
        }
        return candidateResult;
    }
}
