package com.example.alexey.bestoflastfm.ui.main;

import android.support.v7.app.AppCompatActivity;

import com.example.alexey.bestoflastfm.App;
import com.example.alexey.bestoflastfm.data.Repository;
import com.example.alexey.bestoflastfm.ui.NavigationManager;
import com.example.alexey.bestoflastfm.ui.base.BasePresenterImpl;
import com.example.alexey.bestoflastfm.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by alexey
 */

public class MainPresenter<V extends MainMvpContract.View> extends BasePresenterImpl<V>
        implements MainMvpContract.Presenter<V>, NavigationManager.NavigationListener {

    @Inject
    MainPresenter(Repository dataSource, SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable, NavigationManager navigationManager) {
        super(dataSource, schedulerProvider, compositeDisposable, navigationManager);
    }

    @Override
    public void navigateBack(AppCompatActivity activity) {
        App.appComponent.getNavigationManager().navigateBack(activity);
    }

    @Override
    protected void onViewPrepared() {
        App.appComponent.getNavigationManager().init(view.getFragmentManger(), this);
        App.appComponent.getNavigationManager().openListFragment();
    }

    @Override
    public void onBackStackChanged() {}
}
