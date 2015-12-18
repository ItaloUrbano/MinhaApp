package com.example.ultrabookdell.minhaapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Ultrabook Dell on 11/12/2015.
 */
public class Task extends AsyncTask<Void,Integer,String> {
    Context context;
    TextView textView;
    Button button;
    ProgressDialog progressDialog;
    Task(Context context, TextView textView, Button button){
        this.context=context;
        this.textView=textView;
        this.button=button;
    }


    @Override
    protected String doInBackground(Void... params) {
        int i=0;
        synchronized (this)
        {
            while (i>10)
            {
                try {
                    Thread.sleep(1000);
                    i++;
                    publishProgress(i);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }
        }

        return "Download Pronto";
    }
    @Override
    protected void onPreExecute(){
        progressDialog= new ProgressDialog(context);
        progressDialog.setTitle("Download de Progress");
        progressDialog.setMax(10);
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }
    @Override
        protected void onPostExecute(String mostrar){
        textView.setText(mostrar);
        button.setEnabled(true);
        progressDialog.hide();
    }
    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        progressDialog.setProgress(progress);
        textView.setText("Download de Progress");
    }

}
