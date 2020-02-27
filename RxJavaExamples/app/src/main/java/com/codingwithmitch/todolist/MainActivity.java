package com.codingwithmitch.todolist;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import java.io.IOException;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    // UI
    private TextView text;

    // vars
    private CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.makeQuery().observe(this, new androidx.lifecycle.Observer<ResponseBody>() {
            @Override
            public void onChanged(ResponseBody responseBody) {
                Log.d(TAG, "onChanged: this is a live data response!");
                try {
                    Log.d(TAG, "onChanged: " + responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
