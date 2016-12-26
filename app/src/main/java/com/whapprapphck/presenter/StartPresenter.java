package com.whapprapphck.presenter;

import android.os.Bundle;

import com.whapprapphck.other.App;
import com.whapprapphck.view.fragments.IStartFragmentView;
import com.whapprapphck.view.fragments.View;

import javax.inject.Inject;


public class StartPresenter extends BasePresenter {

    private IStartFragmentView view;

    // for DI
    @Inject
    public StartPresenter() {
    }

    public StartPresenter(IStartFragmentView view) {
        App.getComponent().inject(this);
        this.view = view;
    }

    @Override
    protected View getView() {
        return view;
    }

    public void onStartButtonClick() {
        view.showFragment();
    }

    public void onCreateView(Bundle savedInstanceState) {
        /*if (savedInstanceState != null) {
            repoList = (List<Repository>) savedInstanceState.getSerializable(BUNDLE_REPO_LIST_KEY);
        }
        if (isRepoListNotEmpty()) {
            view.showRepoList(repoList);
        }*/
    }


    public void onSaveInstanceState(Bundle outState) {
        /*if (isRepoListNotEmpty()) {
            outState.putSerializable(BUNDLE_REPO_LIST_KEY, new ArrayList<>(repoList));
        }*/
    }

    @Override
    public void onStop() {

    }
}
