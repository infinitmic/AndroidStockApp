package com.rx.stock.stockapp;

import android.util.Log;

import io.reactivex.Observable;

public class JavaObservableTest {

    public static void main(String[] args) {
        JavaObservableTest javaObservableTest = new JavaObservableTest();
        javaObservableTest.demo1();
    }

    private void log(String stage) {
        Log.d("MainActivity->", stage+":"+Thread.currentThread().getName());
    }

    private void log(String stage, String item) {
        Log.d("MainActivity->", stage+":"+Thread.currentThread().getName() +":"+ item);
    }

    private void demo1() {
        Observable.just("test1", "test2")
                .doOnSubscribe(e -> log("onComplelete"))
                .subscribe(e -> log("subscribe", e));
    }
}
