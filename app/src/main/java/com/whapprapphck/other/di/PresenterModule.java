package com.whapprapphck.other.di;

import com.whapprapphck.model.Model;
import com.whapprapphck.model.ModelImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

@Module
public class PresenterModule {

    @Provides
    @Singleton
    Model provideHackDataTimer() {
        return new ModelImpl();
    }

    @Provides
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }

}
