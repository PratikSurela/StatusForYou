package com.dreamsdevelopers.statusforyou.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dreamsdevelopers.statusforyou.R;
import com.dreamsdevelopers.statusforyou.model.ResultItem;

import java.util.ArrayList;

/**
 * Created by prats on 3/10/2017.
 */

public class AllStatusAdapter extends RecyclerView.Adapter<AllStatusAdapter.ViewHolder> {

    private Activity context;
    public ArrayList<ResultItem> arrayList;
    private String TAG = "AllStatusAdapter";

    public AllStatusAdapter(Activity activity, ArrayList<ResultItem> itemList) {
        this.context = activity;
        this.arrayList = itemList;
    }

    @Override
    public AllStatusAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_all_status, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final AllStatusAdapter.ViewHolder viewHolder, final int position) {

        viewHolder.txtStatus.setText(arrayList.get(position).getSHindi());
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