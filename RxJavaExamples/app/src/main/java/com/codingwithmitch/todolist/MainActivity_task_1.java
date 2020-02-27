package com.codingwithmitch.todolist;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.codingwithmitch.todolist.models.Task;
import com.codingwithmitch.todolist.util.DataSource;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity_task_1 extends AppCompatActivity {
    
    private static final String TAG = "MainActivity";
    
    // UI
    private TextView text;

    // vars
    private CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable<Task> taskObservable = Observable            // create new observable
                .fromIterable(DataSource.createTaskList())      // apply operator
                .subscribeOn(Schedulers.io())                   // designate worker thread (BG)
                .filter(new Predicate<Task>() {
                    @Override
                    public boolean test(Task task) throws Exception {                  // filter elements
                        Log.d(TAG, "test: -- " + Thread.currentThread().getName()  + " BG thread --");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return task.isComplete();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());     // designate observer thread (main)
        
        taskObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: called.");
                disposables.add(d);
            }

            @Override
            public void onNext(Task task) {
                Log.d(TAG, "onNext: -- " + Thread.currentThread().getName() + " FG thread --");
                Log.d(TAG, "onNext: " + task.getDescription());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ", e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: called.");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }
}
