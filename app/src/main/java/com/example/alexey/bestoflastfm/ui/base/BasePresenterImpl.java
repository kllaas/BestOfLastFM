package com.example.alexey.bestoflastfm.ui.base;

import com.example.alexey.bestoflastfm.data.Repository;
import com.example.alexey.bestoflastfm.ui.NavigationManager;
import com.example.alexey.bestoflastfm.utils.rx.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by alexey
 */

public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter<V> {

    private Repository dataSource;
    private SchedulerProvider schedulerProvider;
    private CompositeDisposable compositeDisposable;
    private NavigationManager navigationManager;

    protected V view;

    public BasePresenterImpl(Repository dataSource, SchedulerProvider schedulerProvider,
                             CompositeDisposable compositeDisposable, NavigationManager navigationManager) {
        this.dataSource = dataSource;
        this.schedulerProvider = schedulerProvider;
        this.compositeDisposable = compositeDisposable;
        this.navigationManager = navigationManager;
    }

    @Override
    public void takeView(V view) {
        this.view = view;
        onViewPrepared();
    }

    @Override
    public void dropView() {
        getCompositeDisposable().dispose();
    }

    protected abstract void onViewPrepared();

    public Repository getRepository() {
        return dataSource;
    }

    protected SchedulerProvider getSchedulerProvider() {
        return schedulerProvider;
    }

    protected NavigationManager getNavigationManager() {
        return navigationManager;
    }

    protected CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public V getView() {
        return view;
    }

}
