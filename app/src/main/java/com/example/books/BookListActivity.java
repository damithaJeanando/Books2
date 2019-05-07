package com.example.books;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.net.URL;

public class BookListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        TextView tvResult = (TextView) findViewById(R.id.tvResponse);
        try{
            URL url = ApiUtil.buildUrl("Android");
            String JsonResult = ApiUtil.getJson(url);
            tvResult.setText(JsonResult);
        }catch(Exception e){
            Log.d("Error",e.getMessage());
        }
    }
}
