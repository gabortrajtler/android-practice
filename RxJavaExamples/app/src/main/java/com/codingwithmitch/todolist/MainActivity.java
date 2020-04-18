package com.codingwithmitch.todolist;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.rxbinding3.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import kotlin.Unit;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //ui
    private Button button;

    // vars
    private CompositeDisposable disposables = new CompositeDisposable();
    private long timeSinceLastRequest; // for log printouts only. Not part of logic.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button_click_counter);

        timeSinceLastRequest = System.currentTimeMillis();

        // Set a click listener to the button with RxBinding Library
        RxView.clicks(button)
                .throttleFirst(500, TimeUnit.MILLISECONDS) // Throttle the clicks so 500 ms must pass before registering a new click
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Unit>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }
                    @Override
                    public void onNext(Unit unit) {
                        Log.d(TAG, "onNext: time since last clicked: " + (System.currentTimeMillis() - timeSinceLastRequest));
                        someMethod(); // Execute some method when a click is registered
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void someMethod(){
        timeSinceLastRequest = System.currentTimeMillis();
        // do something
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear(); // clear disposables
    }

}
