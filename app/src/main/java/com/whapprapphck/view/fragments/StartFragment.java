package com.whapprapphck.view.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.whapprapphck.R;
import com.whapprapphck.other.di.view.DaggerViewComponent;
import com.whapprapphck.other.di.view.ViewComponent;
import com.whapprapphck.other.di.view.ViewDynamicModule;
import com.whapprapphck.presenter.Presenter;
import com.whapprapphck.presenter.StartPresenter;
import com.whapprapphck.view.activities.ActivityCallback;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Tikho on 21.12.2016.
 */

public class StartFragment extends BaseFragment implements IStartFragmentView {

    private static final String BUNDLE_START_FRAGMENT_KEY = "BUNDLE_START_FRAGMENT_KEY";

    @Inject
    protected StartPresenter presenter;

    private ActivityCallback activityCallback;

    private ViewComponent viewComponent;

    @OnClick(R.id.btn_start_hacking_whatsapp)
    public void onClickStartButton(View v) {
        if (presenter != null) {
            presenter.onStartButtonClick();
        }
    }

    /*public static StartFragment newInstance() {
        StartFragment myFragment = new StartFragment();

        Bundle args = new Bundle();
        //args.putSerializable(BUNDLE_START_FRAGMENT_KEY, repository);
        myFragment.setArguments(args);

        return myFragment;
    }*/

    @Override
    protected Presenter getPresenter() {
        return presenter;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (viewComponent == null) {
            viewComponent = DaggerViewComponent.builder()
                    .viewDynamicModule(new ViewDynamicModule(this))
                    .build();
        }
        viewComponent.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);
        ButterKnife.bind(this, view);

        presenter.onCreateView(savedInstanceState);

        return view;
    }

    @Override
    public void showFragment() {
        activityCallback.startFragmentHackWhatsApp();
    }

    @Override
    public void showError(String error) {

    }
}
