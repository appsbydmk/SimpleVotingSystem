package com.appsbydmk.simplevotingsystem.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.appsbydmk.simplevotingsystem.R;
import com.appsbydmk.simplevotingsystem.adapters.CandidateListAdapter;
import com.appsbydmk.simplevotingsystem.helpers.CandidatesFileHelper;

import java.util.ArrayList;

public class CandidateCountActivity extends AppCompatActivity {
    private ListView candidatesList;
    private Button btnCandidatesAdd;
    private ArrayList<String> allCandidates;
    private CandidateListAdapter candidateListAdapter;
    private CandidatesFileHelper candidatesFileHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_count);

        candidatesFileHelper = new CandidatesFileHelper(this);
        allCandidates = candidatesFileHelper.getAllCandidates();
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        candidatesList = (ListView) this.findViewById(R.id.lv_all_candidates);
        candidateListAdapter = new CandidateListAdapter(allCandidates, this);
        candidatesList.setAdapter(candidateListAdapter);
        candidatesList.setEmptyView(findViewById(android.R.id.empty));
        btnCandidatesAdd = (Button) this.findViewById(R.id.btn_candidate_add);
        btnCandidatesAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CandidateCountActivity.this);
                builder.setTitle("Add a candidate");
                final EditText etCandidate = new EditText(CandidateCountActivity.this);
                etCandidate.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(etCandidate);
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String candidateName = etCandidate.getText().toString();
                        allCandidates.add(candidateName);
                        candidatesFileHelper.addCandidate(candidateName);
                        candidateListAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });


    }
}
