package com.whapprapphck.view.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.whapprapphck.presenter.Presenter;
import com.whapprapphck.view.activities.ActivityCallback;

public abstract class BaseFragment extends Fragment implements View {

    protected ActivityCallback activityCallback;

    protected abstract Presenter getPresenter();

    @Override
    public void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            activityCallback = (ActivityCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement activityCallback");
        }
    }
}

