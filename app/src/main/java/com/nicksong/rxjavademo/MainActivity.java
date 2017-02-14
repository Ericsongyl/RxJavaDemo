package com.nicksong.rxjavademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private Subscription mSubscription;
    private SimpleAdapter mSimpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscription != null && mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    private void initView() {
        setContentView(R.layout.activity_main);
        mProgressBar = (ProgressBar)findViewById(R.id.load_pb);
        mRecyclerView = (RecyclerView)findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSimpleAdapter = new SimpleAdapter(this);
        mRecyclerView.setAdapter(mSimpleAdapter);
    }

    private void initData() {
        Observable<List<String>> tvObservable = Observable.fromCallable(new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                return getList();
            }
        });

        mSubscription = tvObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<String> strings) {
                        showListData(strings);
                    }
                });
    }

    private List<String> getList() {
        List<String> mString = new ArrayList<String>();
        mString.add("Android专家");
        mString.add("Android资深开发工程师");
        mString.add("Android高级开发工程师");
        mString.add("Android开发工程师");
        mString.add("iOS专家");
        mString.add("iOS资深开发工程师");
        mString.add("iOS高级开发工程师");
        mString.add("iOS开发工程师");
        mString.add("H5前端开发工程师");
        return mString;
    }

    private void showListData(List<String> strings) {
        mSimpleAdapter.setData(strings);
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }
}
