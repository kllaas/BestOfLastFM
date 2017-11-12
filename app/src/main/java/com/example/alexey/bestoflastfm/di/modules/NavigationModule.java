package com.example.alexey.bestoflastfm.di.modules;


import com.example.alexey.bestoflastfm.ui.NavigationManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NavigationModule {

    @Provides
    @Singleton
    NavigationManager provideNavigationManager() {
        return new NavigationManager();
    }

}
