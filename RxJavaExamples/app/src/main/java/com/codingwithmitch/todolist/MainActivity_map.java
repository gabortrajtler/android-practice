package com.codingwithmitch.todolist;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.codingwithmitch.todolist.models.Task;
import com.codingwithmitch.todolist.util.DataSource;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity_map extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<Task> extractDescriptionObservable = Observable
                .fromIterable(DataSource.createTaskList())
                .subscribeOn(Schedulers.io())
                .map(completeTask)
                .observeOn(AndroidSchedulers.mainThread());

        extractDescriptionObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(Task task) {
                Log.d(TAG, "onNext: is this task " + task.getDescription() + " complete? " + task.isComplete());
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onComplete() {
            }
        });

    }

    private Function<Task, Task> completeTask = new Function<Task, Task>() {
        @Override
        public Task apply(Task task) throws Exception {
            Log.d(TAG, "apply: doing work on thread: " + Thread.currentThread().getName());
            task.setComplete(true);
            return task;
        }
    };
}
