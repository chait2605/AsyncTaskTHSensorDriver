package com.android.thsensordriver;

/**
 * Created by SRIKUCHAITU on 9/30/2017.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.Random;


public class THLongTask extends AsyncTask<Integer, Integer, Integer> {
    SyncUpdateCallBack mUICallBack = null;

    public THLongTask(SyncUpdateCallBack callBack) {
        mUICallBack = callBack;
    }

    @Override
    protected void onPreExecute() {
        MainActivity  ctx = (MainActivity) mUICallBack;
        ctx.getGenerate.setClickable(false);
        Toast.makeText(ctx, "Ready to run Async Task!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Integer doInBackground(Integer... params) {
        int count = params[0];
        Random r = new Random();
        for (int i = 1; i <= count; i++) {
            int tempVal = r.nextInt(101);
            int humiVal = r.nextInt(101);
            int actId = r.nextInt(1000);
            publishProgress(tempVal, humiVal, actId, i);
            Utils.sleepForInSecs(5);
            if (isCancelled()) break;
        }
        return 1;
    }

    @Override
    protected void onProgressUpdate(Integer... params) {
        this.mUICallBack.UpdateUI(params[0], params[1], params[2], params[3]);
        Toast.makeText((Context) mUICallBack, "Output " + params[3], Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(Integer params) {
        MainActivity  ctx = (MainActivity) mUICallBack;
        ctx.getGenerate.setClickable(true);
        Toast.makeText(ctx, "Task Completed.", Toast.LENGTH_SHORT).show();
    }
}
