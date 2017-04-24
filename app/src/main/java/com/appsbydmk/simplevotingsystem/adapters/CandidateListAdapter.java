package com.appsbydmk.simplevotingsystem.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.appsbydmk.simplevotingsystem.R;
import com.appsbydmk.simplevotingsystem.helpers.CandidatesFileHelper;

import java.util.ArrayList;

public class CandidateListAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<String> candidates = new ArrayList<>();
    private Context listContext;
    private CandidatesFileHelper candidatesFileHelper;

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
        candidatesFileHelper = new CandidatesFileHelper(listContext);
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) listContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_listview_candidates, null);
        }

        TextView tvCandidateName = (TextView) view.findViewById(R.id.tv_candidate_name);
        tvCandidateName.setText(candidates.get(position));

        final ImageButton deleteCandidate, editCandidate;
        editCandidate = (ImageButton) view.findViewById(R.id.img_btn_edit);
        editCandidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(listContext);
                builder.setTitle("Edit the candidate");
                final EditText etCandidate = new EditText(listContext);
                etCandidate.setText(candidates.get(position));
                etCandidate.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(etCandidate);
                builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String candidateName = etCandidate.getText().toString();
                        candidates.set(position, candidateName);
                        candidatesFileHelper.editCandidate(candidateName, position);
                        notifyDataSetChanged();
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
        deleteCandidate = (ImageButton) view.findViewById(R.id.img_btn_delete);
        deleteCandidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String target = candidates.get(position);
                candidates.remove(position);
                candidatesFileHelper.removeCandidate(target);
                notifyDataSetChanged();
            }
        });
        return view;
    }
}
