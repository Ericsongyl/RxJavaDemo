# RxJavaDemo
Android:this is a demo for RxJava used on Android

#Get start
## 1.Declare Subscription
```java
    private Subscription mSubscription;
```

## 2.Create Observable
```java
    Observable<List<String>> tvObservable = Observable.fromCallable(new Callable<List<String>>() {
        @Override
        public List<String> call() throws Exception {
           return getList();
        }
    });
```

## 3.The most important step
```java
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
```
