package com.dreamsdevelopers.statusforyou.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dreamsdevelopers.statusforyou.R;
import com.dreamsdevelopers.statusforyou.adapter.StatusAdapter;
import com.dreamsdevelopers.statusforyou.model.ResultItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatusActivity extends AppCompatActivity {

    private ArrayList<ResultItem> resultItemArrayList = new ArrayList<>();
    private RecyclerView rvAllStatus;
    private LinearLayoutManager layoutManager;
    private String TAG = "StatusActivity";

    private String[] dataArray;
    private List<String> arrayList = new ArrayList<>();
    private StatusAdapter statusAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        initViews();
        statusAdapter.notifyDataSetChanged();
    }

    private void initViews() {

        rvAllStatus = (RecyclerView) findViewById(R.id.rvAllStatus);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvAllStatus.setLayoutManager(layoutManager);

        dataArray = getResources().getStringArray(R.array.AnniversaryStatus);
        arrayList = Arrays.asList(dataArray);
        statusAdapter = new StatusAdapter(this, arrayList);
        rvAllStatus.setAdapter(statusAdapter);
        Log.e(TAG, "initViews: arrayList size : " + arrayList.size());
    }
}