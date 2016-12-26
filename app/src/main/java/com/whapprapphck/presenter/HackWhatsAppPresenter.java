package com.whapprapphck.presenter;

import android.os.Bundle;


import com.whapprapphck.other.App;
import com.whapprapphck.view.fragments.IHackWhatsappView;
import com.whapprapphck.view.fragments.View;


import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;


public class HackWhatsAppPresenter extends BasePresenter {

    private IHackWhatsappView view;

    private Subscription subscriptionTimer;

    // for DI
    @Inject
    public HackWhatsAppPresenter() {
    }

    public HackWhatsAppPresenter(IHackWhatsappView view) {
        App.getComponent().inject(this);
        this.view = view;
    }

    @Override
    protected View getView() {
        return view;
    }

    private void showPopUpDialog() {
        view.showPopUpDialog();
    }

    public void onCreate(IHackWhatsappView view) {
        App.getComponent().inject(this);
        this.view = view;
    }

    public void onCreateView(Bundle savedInstanceState) {

        subscriptionTimer = model.getTimerObservable()
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onCompleted() {
                        view.showPopUpDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showError(e);
                    }

                    @Override
                    public void onNext(Integer progress) {
                        view.setProgress(progress);
                    }
                });

        addSubscription(subscriptionTimer);

    }

    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onStop() {
        subscriptionTimer.unsubscribe();
    }
}
