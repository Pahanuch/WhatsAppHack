package com.whapprapphck.other.di;

import com.whapprapphck.presenter.HackWhatsAppPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    @Provides
    HackWhatsAppPresenter hackWhatsAppPresenter() {
        return new HackWhatsAppPresenter();
    }

}
