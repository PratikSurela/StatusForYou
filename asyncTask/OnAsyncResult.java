package com.dreamsdevelopers.statusforyou.asyncTask;

/**
 * Created by prats on 3/3/2015.
 */
public interface OnAsyncResult {
    public void OnSuccess(String result);

    public void OnFailure(String result);
}