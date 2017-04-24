package com.appsbydmk.simplevotingsystem.helpers;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class VotingFileHelper {
    private Context context;

    public VotingFileHelper(Context context) {
        this.context = context;
    }

    public boolean registerVoterStatus(int voterId, String voterName) {
        String voterDetail = voterId + "-" + voterName;
        boolean registered;
        BufferedWriter voteRegister = null;
        if (this.haveVotedAlready(voterId)) {
            registered = false;
        } else {
            try {
                voteRegister = new BufferedWriter(new OutputStreamWriter(context.openFileOutput(HelperConstants.VOTER_FILE, Context.MODE_APPEND)));
                voteRegister.write(voterDetail + "\n");
                voteRegister.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (voteRegister != null)
                        voteRegister.close();
                } catch (IOException | NullPointerException ex) {
                    ex.printStackTrace();
                }
            }
            registered = true;
        }
        return registered;
    }

    private boolean haveVotedAlready(int voterId) {
        ArrayList<String> allVoters = this.getAllVoters();
        int id;
        boolean hasVoted = false;
        if (allVoters.isEmpty()) {
            hasVoted = false;
        } else {

            for (int n = 0; n < allVoters.size(); n++) {
                id = Integer.parseInt(allVoters.get(n).substring(0, 3));
                if (voterId == id) {
                    hasVoted = true;
                    break;
                }
            }
        }
        return hasVoted;
    }

    private ArrayList<String> getAllVoters() {
        ArrayList<String> allVoters = new ArrayList<>();
        BufferedReader voterValidator = null;
        String voter;
        try {
            voterValidator = new BufferedReader(new InputStreamReader(context.openFileInput(HelperConstants.VOTER_FILE)));
            while ((voter = voterValidator.readLine()) != null) {
                allVoters.add(voter);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (voterValidator != null)
                    voterValidator.close();
            } catch (IOException | NullPointerException ex) {
                ex.printStackTrace();
            }
        }
        return allVoters;
    }
}
