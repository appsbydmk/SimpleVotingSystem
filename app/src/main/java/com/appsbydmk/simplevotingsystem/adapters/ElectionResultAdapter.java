package com.appsbydmk.simplevotingsystem.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.appsbydmk.simplevotingsystem.R;

import java.util.ArrayList;

public class ElectionResultAdapter extends BaseAdapter implements ListAdapter {

    ArrayList<String> candidateName = new ArrayList<>();
    ArrayList<String> candidateVotes = new ArrayList<>();
    Context context;

    public ElectionResultAdapter(ArrayList<String> candidateName, ArrayList<String> candidateVotes, Context context) {
        this.candidateName = candidateName;
        this.candidateVotes = candidateVotes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return candidateName.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_listview_elections_result, null);
        }

        TextView tvCandidateName = (TextView) view.findViewById(R.id.tv_rs_candidate_name);
        tvCandidateName.setText(candidateName.get(position));
        tvCandidateName.setTextColor(Color.parseColor("#FFFFFF"));

        TextView tvCandidateResult = (TextView) view.findViewById(R.id.lv_rs_candidate_votes);
        tvCandidateResult.setText(candidateVotes.get(position));
        tvCandidateResult.setTextColor(Color.parseColor("#FFFFFF"));

        return view;
    }
}
