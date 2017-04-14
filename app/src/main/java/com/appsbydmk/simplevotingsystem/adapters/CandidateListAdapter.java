package com.appsbydmk.simplevotingsystem.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.appsbydmk.simplevotingsystem.R;

import java.util.ArrayList;

/**
 * Created by Darshan on 14-04-2017.
 */

public class CandidateListAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<String> candidates = new ArrayList<>();
    private Context listContext;

    public CandidateListAdapter(ArrayList<String> candidates, Context listContext) {
        this.candidates = candidates;
        this.listContext = listContext;
    }

    @Override
    public int getCount() {
        return candidates.size();
    }

    @Override
    public Object getItem(int position) {
        return candidates.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) listContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_listview_candidates, null);
        }

        TextView tvCandidateName = (TextView) view.findViewById(R.id.tv_candidate_name);
        tvCandidateName.setText(candidates.get(position));

        ImageButton addCandidate, deleteCandidate, editCandidate;
        addCandidate = (ImageButton) view.findViewById(R.id.img_btn_add);
        addCandidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        editCandidate = (ImageButton) view.findViewById(R.id.img_btn_edit);
        editCandidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        deleteCandidate = (ImageButton) view.findViewById(R.id.img_btn_delete);
        deleteCandidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                candidates.remove(position);
                notifyDataSetChanged();
            }
        });
        return null;
    }
}
