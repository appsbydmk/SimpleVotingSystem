package com.appsbydmk.simplevotingsystem.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.appsbydmk.simplevotingsystem.R;
import com.appsbydmk.simplevotingsystem.helpers.VotingFileHelper;

import java.util.ArrayList;

public class ElectionsResultActivity extends AppCompatActivity {
    private VotingFileHelper votingFileHelper;
    private ListView lvElectionsResult;
    private ArrayAdapter<String> electionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elections_result);
        votingFileHelper = new VotingFileHelper(this);
        lvElectionsResult = (ListView) this.findViewById(R.id.lv_elections_result);
        electionsAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, this.setElectionResult());
        lvElectionsResult.setAdapter(electionsAdapter);
    }

    private ArrayList<String> setElectionResult() {
        String[] electionsResult = votingFileHelper.getElectionsArray();
        ArrayList<String> resultList = new ArrayList<>();
        for (int n = 0; n < electionsResult.length; n = n + 2) {
            resultList.add(electionsResult[n] + " \t\t\t " + " Total Votes: " + electionsResult[n + 1]);
        }
        return resultList;
    }
}
