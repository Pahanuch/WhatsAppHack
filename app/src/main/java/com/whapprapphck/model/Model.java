package com.whapprapphck.model;

import rx.Observable;

public interface Model {

    Observable<Integer> getTimerObservable();

}
