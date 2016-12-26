package com.whapprapphck.other.di.view;

import com.whapprapphck.presenter.StartPresenter;
import com.whapprapphck.view.fragments.IStartFragmentView;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewDynamicModule {

    private IStartFragmentView view;

    public ViewDynamicModule(IStartFragmentView view) {
        this.view = view;
    }

    @Provides
    StartPresenter provideStartPresenter() {
        return new StartPresenter(view);
    }

}
