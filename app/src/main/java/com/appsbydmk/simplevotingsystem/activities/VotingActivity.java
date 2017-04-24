package com.appsbydmk.simplevotingsystem.activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.appsbydmk.simplevotingsystem.R;
import com.appsbydmk.simplevotingsystem.helpers.CandidatesFileHelper;
import com.appsbydmk.simplevotingsystem.helpers.VotingFileHelper;

public class VotingActivity extends AppCompatActivity {

    private ListView lvCandidates;
    private ArrayAdapter<String> candidatesAdapter;
    private CandidatesFileHelper candidatesFileHelper;
    private Button btnVote;
    private VotingFileHelper votingFileHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);
        votingFileHelper = new VotingFileHelper(this);
        btnVote = (Button) this.findViewById(R.id.btn_vote);
        btnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lvCandidates.getCheckedItemCount() > 0)
                    VotingActivity.this.displayAlertDialog();
                else
                    Toast.makeText(getBaseContext(), "Please select a candidate first!", Toast.LENGTH_SHORT).show();
            }
        });
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        candidatesFileHelper = new CandidatesFileHelper(this);
        lvCandidates = (ListView) this.findViewById(R.id.lv_all_candidates);
        lvCandidates.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        candidatesAdapter = new ArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_list_item_1, candidatesFileHelper.getAllCandidates());
        lvCandidates.setAdapter(candidatesAdapter);
    }

    private void displayAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(VotingActivity.this);
        builder.setTitle("Enter your ID and Name");
        LinearLayout dialogLayout = new LinearLayout(VotingActivity.this);
        dialogLayout.setOrientation(LinearLayout.VERTICAL);

        final EditText etVoterID = new EditText(VotingActivity.this);
        etVoterID.setInputType(InputType.TYPE_CLASS_NUMBER);
        etVoterID.setHint("Voter ID");
        dialogLayout.addView(etVoterID);

        final EditText etVoterName = new EditText(VotingActivity.this);
        etVoterName.setInputType(InputType.TYPE_CLASS_TEXT);
        etVoterName.setHint("Voter Name");
        dialogLayout.addView(etVoterName);

        builder.setView(dialogLayout);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int voterId = Integer.parseInt(etVoterID.getText().toString());
                String voterName = etVoterName.getText().toString();
                if (votingFileHelper.registerVoterStatus(voterId, voterName)) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("message", "thankYou");
                    VotingActivity.this.setResult(Activity.RESULT_OK, resultIntent);
                    VotingActivity.this.finish();
                } else {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("message", "alreadyVoted");
                    VotingActivity.this.setResult(Activity.RESULT_OK, resultIntent);
                    VotingActivity.this.finish();
                }
            }
        });
        builder.show();
    }
}
