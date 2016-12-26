package com.whapprapphck.presenter;

import com.whapprapphck.model.Model;
import com.whapprapphck.other.App;
import com.whapprapphck.view.fragments.View;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter implements Presenter {

    @Inject
    protected Model model;

    @Inject
    protected CompositeSubscription compositeSubscription;

    public BasePresenter() {
        App.getComponent().inject(this);
    }

    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    @Override
    public void onStop() {
        compositeSubscription.clear();
    }

    protected abstract View getView();


    protected void showError(Throwable e) {
        getView().showError(e.getMessage());
    }

}
