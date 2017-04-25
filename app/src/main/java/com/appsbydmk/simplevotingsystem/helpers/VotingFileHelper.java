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

    public void writeAllCandidates() {
        CandidatesFileHelper candidatesFileHelper = new CandidatesFileHelper(context);
        ArrayList<String> allCandidates = candidatesFileHelper.getAllCandidates();
        BufferedWriter electionWriter = null;
        String electionLine = "";
        if (allCandidates.size() > 0) {
            try {
                electionWriter = new BufferedWriter(new OutputStreamWriter(context.openFileOutput(HelperConstants.ELECTIONS_FILE, Context.MODE_PRIVATE)));
                for (String candidate : allCandidates) {
                    electionLine += candidate + "," + 0 + ",";
                }
                electionLine = electionLine.substring(0, electionLine.lastIndexOf(","));
                System.out.println(electionLine);
                electionWriter.write(electionLine);
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (electionWriter != null)
                        electionWriter.close();
                } catch (IOException | NullPointerException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            try {
                electionWriter = new BufferedWriter(new OutputStreamWriter(context.openFileOutput(HelperConstants.ELECTIONS_FILE, Context.MODE_PRIVATE)));
                electionWriter.write(electionLine);
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (electionWriter != null)
                        electionWriter.close();
                } catch (IOException | NullPointerException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public String[] getElectionsArray() {
        String[] allCandidates = null;
        BufferedReader electionReader = null;
        String electionLine;
        try {
            electionReader = new BufferedReader(new InputStreamReader(context.openFileInput(HelperConstants.ELECTIONS_FILE)));
            if ((electionLine = electionReader.readLine()) != null) {
                allCandidates = electionLine.split(",");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (electionReader != null)
                    electionReader.close();
            } catch (IOException | NullPointerException ex) {
                ex.printStackTrace();
            }
        }
        return allCandidates;
    }

    private int getElectionVotesCount() {
        String[] allCandidates = this.getElectionsArray();
        int electionVotesCount = 0;
        for (int n = 1; n < allCandidates.length; n += 2) {
            electionVotesCount += Integer.parseInt(allCandidates[n]);
        }
        System.out.println("ElectionVotersCount" + electionVotesCount);
        return electionVotesCount;
    }

    public boolean registerVote(String candidate) {
        VotersFileHelper votersFileHelper = new VotersFileHelper(context);
        int electionVotesCount = this.getElectionVotesCount();
        int voterCount = votersFileHelper.getVoterCount();
        String[] allCandidates = this.getElectionsArray();
        BufferedWriter electionWriter = null;
        boolean voteRegistered = false;
        String electionLine;
        if (electionVotesCount < voterCount) {
            try {
                electionWriter = new BufferedWriter(new OutputStreamWriter(context.openFileOutput(HelperConstants.ELECTIONS_FILE, Context.MODE_PRIVATE)));
                for (int n = 0; n < allCandidates.length; n++) {
                    int add;
                    if (allCandidates[n].equals(candidate)) {
                        add = Integer.parseInt(allCandidates[n + 1]);
                        ++add;
                        allCandidates[n + 1] = Integer.toString(add);
                        break;
                    }
                }
                StringBuilder builder = new StringBuilder();
                for (String s : allCandidates) {
                    builder.append(s).append(",");
                }
                electionLine = builder.toString();
                electionLine = electionLine.substring(0, electionLine.lastIndexOf(","));
                electionWriter.write(electionLine);
                electionWriter.flush();
                voteRegistered = true;
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (electionWriter != null)
                        electionWriter.close();
                } catch (IOException | NullPointerException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            voteRegistered = false;
        }
        return voteRegistered;
    }
}
