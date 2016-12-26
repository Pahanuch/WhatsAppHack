package com.whapprapphck.other.di.view;

import com.whapprapphck.view.fragments.StartFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ViewDynamicModule.class})
public interface ViewComponent {

    void inject(StartFragment startFragment);

}
