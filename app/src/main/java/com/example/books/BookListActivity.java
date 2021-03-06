package com.example.books;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URL;

public class BookListActivity extends AppCompatActivity {
    private ProgressBar mLoadingProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        mLoadingProgress = (ProgressBar) findViewById(R.id.pb_loading);
        try{
            URL url = ApiUtil.buildUrl("Android");
            new BooksQueryTask().execute(url);
        }catch(Exception e){
            Log.d("Error",e.getMessage());
        }
        TextView tvResult = (TextView) findViewById(R.id.tvResponse);
//        try{
//            URL url = ApiUtil.buildUrl("Android");
////            String JsonResult = ApiUtil.getJson(url);
////            tvResult.setText(JsonResult);
//        }catch(Exception e){
//            Log.d("Error",e.getMessage());
//        }
    }

    public class BooksQueryTask extends AsyncTask<URL,Void,String>{

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            mLoadingProgress.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(URL... urls){
            URL searchUrl = urls[0];
            String result = null;
            try{
                result = ApiUtil.getJson(searchUrl);
            }catch (Exception e){
                Log.d("Error",e.getMessage());
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result){
        super.onPostExecute(result);
        TextView tvResult = (TextView) findViewById(R.id.tvResponse);
        TextView tvError = (TextView) findViewById(R.id.tv_error);
        mLoadingProgress.setVisibility(View.INVISIBLE);
        if(result==null){
            tvResult.setVisibility(View.INVISIBLE);
            tvError.setVisibility(View.VISIBLE);
        }else{
            tvResult.setVisibility(View.VISIBLE);
            tvError.setVisibility(View.INVISIBLE);
        }
        tvResult.setText(result);
        }
    }


}
