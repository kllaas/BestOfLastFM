package com.example.alexey.bestoflastfm.data.remote.service;

import com.example.alexey.bestoflastfm.BuildConfig;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class ServiceGenerator {

    private String API_KEY = "api_key";

    private OkHttpClient.Builder okHttpBuilder;
    private Retrofit retrofit;
    private Gson gson;
    private String apiKey;

    private Interceptor headerInterceptor = chain -> {
        Request original = chain.request();
        HttpUrl originalUrl = original.url();

        HttpUrl url = originalUrl.newBuilder()
                .addQueryParameter(API_KEY, apiKey)
                .addQueryParameter("format", "json")
                .build();

        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        Request request = requestBuilder.build();

        return chain.proceed(request);
    };

    @Inject
    ServiceGenerator(Gson gson, String apiKey) {
        this.okHttpBuilder = new OkHttpClient.Builder();
        this.gson = gson;
        this.apiKey = apiKey;

        okHttpBuilder.addInterceptor(headerInterceptor);

        if (BuildConfig.DEBUG)
            okHttpBuilder.addInterceptor(getLogger());
    }

    private HttpLoggingInterceptor getLogger() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS).setLevel
                (HttpLoggingInterceptor.Level.BODY);


        return loggingInterceptor;
    }


    public <S> S createService(Class<S> service, String baseUrl) {
        OkHttpClient client = okHttpBuilder.build();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl).client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(service);
    }
}
