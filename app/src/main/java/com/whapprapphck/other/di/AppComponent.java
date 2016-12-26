package com.whapprapphck.other.di;

import com.whapprapphck.model.ModelImpl;
import com.whapprapphck.presenter.BasePresenter;
import com.whapprapphck.presenter.HackWhatsAppPresenter;
import com.whapprapphck.presenter.StartPresenter;
import com.whapprapphck.view.fragments.HackWhatsAppFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ModelModule.class, PresenterModule.class, ViewModule.class})
public interface AppComponent {

    void inject(ModelImpl dataTimer);

    void inject(BasePresenter basePresenter);

    void inject(StartPresenter startPresenter);

    void inject(HackWhatsAppPresenter hackWhatsAppPresenter);

    void inject(HackWhatsAppFragment hackWhatsAppFragment);

}
