package com.example.alexey.bestoflastfm.di.modules;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.alexey.bestoflastfm.BuildConfig;
import com.example.alexey.bestoflastfm.data.entity.mappers.AlbumDeserializer;
import com.example.alexey.bestoflastfm.data.entity.mappers.ArtistDeserializer;
import com.example.alexey.bestoflastfm.data.entity.remote.AlbumRemote;
import com.example.alexey.bestoflastfm.data.entity.remote.ArtistRemote;
import com.example.alexey.bestoflastfm.data.local.AlbumsDAO;
import com.example.alexey.bestoflastfm.data.local.AppDatabase;
import com.example.alexey.bestoflastfm.data.local.ArtistsDAO;
import com.example.alexey.bestoflastfm.utils.network.NetworkUtils;
import com.example.alexey.bestoflastfm.utils.rx.AppSchedulerProvider;
import com.example.alexey.bestoflastfm.utils.rx.SchedulerProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AppModule {

    private Application application;
    private AppDatabase appDatabase;

    public AppModule(Application application) {
        this.application = application;

        appDatabase = Room.databaseBuilder(application, AppDatabase.class, "lastfm-db").build();
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @Singleton
    SchedulerProvider provideAppSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapter(AlbumRemote.class, new AlbumDeserializer())
                .registerTypeAdapter(ArtistRemote.class, new ArtistDeserializer())
                .create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder().build();
    }

    @Singleton
    @Provides
    AppDatabase provideRoomDatabase() {
        return appDatabase;
    }

    @Singleton
    @Provides
    ArtistsDAO provideArtistsDao(AppDatabase db) {
        return db.artistsDAO();
    }

    @Singleton
    @Provides
    AlbumsDAO provideAlbumDao(AppDatabase db) {
        return db.albumsDao();
    }

    @Singleton
    @Provides
    NetworkUtils provideNetworkUtils() {
        return new NetworkUtils(application);
    }

}
