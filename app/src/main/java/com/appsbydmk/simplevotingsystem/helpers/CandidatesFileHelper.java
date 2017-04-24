package com.appsbydmk.simplevotingsystem.helpers;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;


public class CandidatesFileHelper {

    private Context context;

    public CandidatesFileHelper(Context context) {
        this.context = context;
    }

    public void addCandidate(String candidate) {
        BufferedWriter candidateWriter = null;
        try {
            candidateWriter = new BufferedWriter(new OutputStreamWriter(
                    context.openFileOutput(HelperConstants.FILE_CANDIDATES, Context.MODE_APPEND)));
            candidateWriter.write(candidate + "\n");
            candidateWriter.flush();
            candidateWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (candidateWriter != null)
                    candidateWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public ArrayList<String> getAllCandidates() {
        BufferedReader candidateReader = null;
        ArrayList<String> allCandidates = new ArrayList<>();
        String candidate;
        try {
            candidateReader = new BufferedReader(new InputStreamReader(
                    context.openFileInput(HelperConstants.FILE_CANDIDATES)));
            while ((candidate = candidateReader.readLine()) != null) {
                allCandidates.add(candidate);
            }
            candidateReader.close();
        } catch (IOException | NullPointerException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (candidateReader != null)
                    candidateReader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return allCandidates;
    }

    public void removeCandidate(String target) {
        ArrayList<String> allCandidates = this.getAllCandidates();

        for (Iterator<String> it = allCandidates.iterator(); it.hasNext(); ) {
            String candidate = it.next();
            if (candidate.equals(target))
                it.remove();
        }
        BufferedWriter candidateWriter = null;
        try {
            candidateWriter = new BufferedWriter(new OutputStreamWriter(
                    context.openFileOutput(HelperConstants.FILE_CANDIDATES, Context.MODE_PRIVATE)));
            for (String candidate : allCandidates) {
                candidateWriter.write(candidate + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (candidateWriter != null)
                    candidateWriter.close();
            } catch (IOException | NullPointerException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void editCandidate(String newCandidateName, int position) {
        ArrayList<String> allCandidates = this.getAllCandidates();
        allCandidates.set(position, newCandidateName);

        BufferedWriter candidateWriter = null;
        try {
            candidateWriter = new BufferedWriter(new OutputStreamWriter(
                    context.openFileOutput(HelperConstants.FILE_CANDIDATES, Context.MODE_PRIVATE)));
            for (String candidate : allCandidates) {
                candidateWriter.write(candidate + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (candidateWriter != null)
                    candidateWriter.close();
            } catch (IOException | NullPointerException ex) {
                ex.printStackTrace();
            }
        }
    }
}
