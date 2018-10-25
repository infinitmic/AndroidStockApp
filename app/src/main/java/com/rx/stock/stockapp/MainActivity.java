package com.rx.stock.stockapp;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Method to test Observable flow on Same Thread
        observableFlowTestSingleThread();

        //Method to test Observable flow on multiple thread
        //observableFlowTestWorkerThread();
    }

    private void log(String stage) {
        Log.d("MainActivity->", stage + ":" + Thread.currentThread().getName());
    }

    private void log(String stage, String item) {
        Log.d("MainActivity->", stage + ":" + Thread.currentThread().getName() + ":" + item);
    }

    private void observableFlowTestSingleThread() {
        Observable.just("Data1", "Data2")
                .doOnDispose(() -> log("doOnDispose"))
                .doOnComplete(() -> log("doOnComplete"))
                .doOnNext(e -> log("doOnNext", e))
                .doOnEach(e -> log("doOnEach"))
                .doOnSubscribe(e -> log("doOnSubscribe"))
                .doOnTerminate(() -> log("doOnTerminate"))
                .doFinally(() -> log("doFinally"))
                .subscribe(e -> log("subscribe", e));
    }

    private void observableFlowTestWorkerThread() {
        Observable.just("Data1", "Data2")
                .doOnDispose(() -> log("doOnDispose"))
                .doOnComplete(() -> log("doOnComplete"))
                .doOnNext(e -> log("doOnNext", e))
                .doOnEach(e -> log("doOnEach"))
                .doOnSubscribe(e -> log("doOnSubscribe"))
                .doOnTerminate(() -> log("doOnTerminate"))
                .doFinally(() -> log("doFinally"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(e -> log("subscribe", e));
    }
}
