package com.dreamsdevelopers.statusforyou.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dreamsdevelopers.statusforyou.R;

import java.util.List;

/**
 * Created by prats on 3/10/2017.
 */

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.ViewHolder> {

    private Activity context;
    public List<String> arrayList;
    private String TAG = "AllStatusAdapter";

    public StatusAdapter(Activity activity, List<String> itemList) {
        this.context = activity;
        this.arrayList = itemList;
    }

    @Override
    public StatusAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_all_status, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final StatusAdapter.ViewHolder viewHolder, final int position) {

        viewHolder.txtStatus.setText(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            txtStatus = (TextView) itemView.findViewById(R.id.txtStatus);
        }
    }
}