# RxJavaDemo
Android:this is a demo for RxJava used on Android

#Get start
**Declare Subscription**

`private Subscription mSubscription;`


**Create Observable**

eg:
`Observable<List<String>> tvObservable = Observable.fromCallable(new Callable<List<String>>() {
     @Override
     public List<String> call() throws Exception {
        return getList();
     }
 });`

**The most important step:**
`mSubscription = tvObservable
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
                        //your next action
                    }
                });`
