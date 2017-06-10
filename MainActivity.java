package com.dreamsdevelopers.statusforyou;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.dreamsdevelopers.statusforyou.adapter.AllStatusAdapter;
import com.dreamsdevelopers.statusforyou.model.AllStatus;
import com.dreamsdevelopers.statusforyou.model.ResultItem;
import com.dreamsdevelopers.statusforyou.utils.ParseJSON;
import com.dreamsdevelopers.statusforyou.webServices.WebService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ResultItem> resultItemArrayList = new ArrayList<>();
    //private ArrayList<StatusModel> resultItemArrayList = new ArrayList<>();
    private AllStatusAdapter statusAdapter;
    private RecyclerView rvAllStatus;
    private LinearLayoutManager layoutManager;
    private String TAG = "MainActivity";

    /*pagination vars start*/
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    int page = 0;
    int total_pages = 10;
    /*pagination vars end*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        getAllStatus(rvAllStatus, true);

        rvAllStatus.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();
                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            if (page < total_pages) {
                                page++;
                                Log.e(TAG, "onScrolled: page : " + page);
                                getAllStatus(rvAllStatus, false);
                            }
                        }
                    }
                }
            }
        });
    }

    private void initViews() {

        rvAllStatus = (RecyclerView) findViewById(R.id.rvAllStatus);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvAllStatus.setLayoutManager(layoutManager);
        statusAdapter = new AllStatusAdapter(this, resultItemArrayList);
        rvAllStatus.setAdapter(statusAdapter);
    }

    private void getAllStatus(final View view, final boolean clearFlag) {

        if (clearFlag) {
            resultItemArrayList.clear();
        }

        ArrayList<String> params1 = new ArrayList<>();
        params1.add("page");

        ArrayList<String> values1 = new ArrayList<>();
        values1.add(Integer.toString(page));

        Log.e(TAG, "getAllStatus: page " + Integer.toString(page));

        ParseJSON parseJSON1 = new ParseJSON(this, WebService.BASE_URL, params1, values1, AllStatus.class, new ParseJSON.OnResultListner() {
            @Override
            public void onResult(boolean status, Object obj) {
                if (status) {
                    AllStatus allStatus = (AllStatus) obj;
                    resultItemArrayList.addAll(allStatus.getResult());
                    statusAdapter.notifyDataSetChanged();
                    loading = true;
                } else {
                    String message = (String) obj;
                    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
}