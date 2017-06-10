package com.dreamsdevelopers.statusforyou.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;

import com.dreamsdevelopers.statusforyou.R;
import com.dreamsdevelopers.statusforyou.asyncTask.GetAsyncTask;
import com.dreamsdevelopers.statusforyou.asyncTask.OnAsyncResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by pratik on 23-Sep-16.
 * http://www.jsonschema2pojo.org/
 */

public class ParseJSON {

    private GetAsyncTask getAsyncTask;
    private Uri.Builder builder;
    private ProgressDialog prd;
    private Context mContext;

    public String url;
    public ArrayList<String> params;
    public ArrayList<String> values;
    public Object model;

    private OnResultListner onResultListner;
    ConnectionCheck cd;
    boolean isInternetAvailable;

    public ParseJSON() {
    }


    public ParseJSON(Context mContext, String url, ArrayList<String> params, ArrayList<String> values, Object model, OnResultListner onResultListner) {
        this.url = url;
        this.params = params;
        this.values = values;
        this.model = model;
        this.mContext = mContext;
        this.onResultListner = onResultListner;
        cd = new ConnectionCheck();
        isInternetAvailable = cd.isNetworkConnected(mContext);
        if (isInternetAvailable) {
            getData();
        } else {
            new ConnectionCheck().showconnectiondialog(mContext).show();
        }
    }

    public interface OnResultListner {
        void onResult(boolean status, Object obj);
    }

    public void getData() {
        builder = new Uri.Builder();

        //final Object[] resultObj = new Object[1];

        for (int i = 0; i < params.size(); i++) {
            builder.appendQueryParameter(params.get(i), values.get(i));
        }


        //builder.appendQueryParameter("")
        prd = new ProgressDialog(mContext);
        prd.setTitle("Loading....");
        prd.setMessage("Please wait While Data Loading");
        prd.show();

        // Async Result
        OnAsyncResult onAsyncResult = new OnAsyncResult() {
            @Override
            public void OnSuccess(String result) {

                prd.dismiss();
                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.setDateFormat("M/d/yy hh:mm a"); //Format of our JSON dates
                    Gson gson = gsonBuilder
                            .setLenient()
                            .create();
                    //onResultListner.onResult(true, gson.fromJson(result, (Class) model));
                    StringReader stringReader = new StringReader(result);
                    onResultListner.onResult(true, gson.fromJson(new JsonReader(stringReader), (Class) model));

                    //resultObj[0] = gson.fromJson(result, (Class)model);

                   /* if (post.getStatus()) {
                        Log.e("GSON", "Size : " + post.getData().size());
                    }*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void OnFailure(String result) {
                prd.dismiss();
                getAsyncTask = new GetAsyncTask(url, this, builder);
                //Toast.makeText(MainActivity.this,result,Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.AppCompatAlertDialogStyle);
                builder.setTitle("Error");
                builder.setMessage(result);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getAsyncTask.execute();
                    }
                });
                onResultListner.onResult(false, "Error");
                builder.setNegativeButton("No", null);
                builder.show();
            }
        };
        getAsyncTask = new GetAsyncTask(url, onAsyncResult, builder);
        getAsyncTask.execute();

    }


}
