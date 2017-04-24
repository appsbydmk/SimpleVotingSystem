package com.appsbydmk.simplevotingsystem.helpers;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class VotersFileHelper {

    Context context;

    public void updateVoterCount(int voterCount) {
        BufferedWriter voterCountWriter = null;
        try {
            voterCountWriter = new BufferedWriter(new OutputStreamWriter(context.openFileOutput(
                    HelperConstants.VOTER_COUNT_FILE, Context.MODE_PRIVATE)));
            voterCountWriter.write(Integer.toString(voterCount));
            voterCountWriter.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (voterCountWriter != null)
                    voterCountWriter.close();
            } catch (IOException | NullPointerException ex) {
                ex.printStackTrace();
            }
        }
    }

    public VotersFileHelper(Context context) {
        this.context = context;
    }

    public int getVoterCount() {
        BufferedReader voterCountReader = null;
        int voterCount = 0;
        try {
            voterCountReader = new BufferedReader(new InputStreamReader(context.openFileInput(HelperConstants.VOTER_COUNT_FILE)));
            voterCount = Integer.parseInt(voterCountReader.readLine());
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (voterCountReader != null)
                    voterCountReader.close();
            } catch (IOException | NullPointerException ex) {
                ex.printStackTrace();
            }
        }
        return voterCount;

    }
}
