package com.example.alexey.bestoflastfm;

import android.app.Application;

import com.example.alexey.bestoflastfm.di.components.ApplicationComponent;
import com.example.alexey.bestoflastfm.di.components.DaggerApplicationComponent;
import com.example.alexey.bestoflastfm.di.modules.AppModule;


public class App extends Application {

    public static ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }

}
