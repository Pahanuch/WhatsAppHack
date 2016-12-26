package com.whapprapphck.model;

import com.whapprapphck.other.App;
import com.whapprapphck.other.Const;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;

public class ModelImpl implements Model {

    private final Observable.Transformer schedulersTransformer;

    @Inject
    @Named(Const.UI_THREAD)
    Scheduler uiThread;

    @Inject
    @Named(Const.IO_THREAD)
    Scheduler ioThread;

    public ModelImpl() {
        App.getComponent().inject(this);
        schedulersTransformer = o -> ((Observable) o).subscribeOn(ioThread)
                .observeOn(uiThread)
                .unsubscribeOn(ioThread); // TODO: remove when https://github.com/square/okhttp/issues/1592 is fixed
    }


    @Override
    public Observable<Integer> getTimerObservable() {
        final Observable<Integer> timerObservable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {

                int mSickTimer = 0;

                subscriber.onStart();
                while (mSickTimer <= 100) {
                    subscriber.onNext(mSickTimer ++);
                    try {
                        Thread.sleep(Const.SLEEP_TIME);
                    } catch (InterruptedException e) {
                        subscriber.onCompleted();
                    }
                }
                subscriber.onCompleted();
            }
        });
        return timerObservable
                .compose(applySchedulers());
    }

    @SuppressWarnings("unchecked")
    private <T> Observable.Transformer<T, T> applySchedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }

}
