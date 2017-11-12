package com.example.alexey.bestoflastfm.di.components;

import android.app.Application;

import com.example.alexey.bestoflastfm.App;
import com.example.alexey.bestoflastfm.data.Repository;
import com.example.alexey.bestoflastfm.di.modules.ActivityModule;
import com.example.alexey.bestoflastfm.di.modules.AppModule;
import com.example.alexey.bestoflastfm.di.modules.NavigationModule;
import com.example.alexey.bestoflastfm.ui.NavigationManager;
import com.example.alexey.bestoflastfm.ui.main.MainActivity;
import com.example.alexey.bestoflastfm.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NavigationModule.class, ActivityModule.class})
public interface ApplicationComponent {

    void inject(App app);

    void inject(MainActivity activity);

    Repository getDataSource();

    SchedulerProvider getSchedulerProvider();

    NavigationManager getNavigationManager();

    Application getContext();
}
