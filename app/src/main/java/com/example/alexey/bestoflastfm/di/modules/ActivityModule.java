package com.example.alexey.bestoflastfm.di.modules;


import com.example.alexey.bestoflastfm.ui.main.MainMvpContract;
import com.example.alexey.bestoflastfm.ui.main.MainPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
@Singleton
public class ActivityModule {

    @Provides
    MainMvpContract.Presenter<MainMvpContract.View> provideMainPresenter(
            MainPresenter<MainMvpContract.View> presenter) {
        return presenter;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

}
