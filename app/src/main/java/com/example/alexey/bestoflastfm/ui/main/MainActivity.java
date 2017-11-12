package com.example.alexey.bestoflastfm.ui.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import com.example.alexey.bestoflastfm.App;
import com.example.alexey.bestoflastfm.R;
import com.example.alexey.bestoflastfm.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainMvpContract.View {

    @Inject
    MainMvpContract.Presenter<MainMvpContract.View> presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUnbinder(ButterKnife.bind(this));
        App.appComponent.inject(this);

        presenter.takeView(this);
    }

    @Override
    public void onBackPressed() {
        presenter.navigateBack(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            presenter.navigateBack(this);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public FragmentManager getFragmentManger() {
        return getSupportFragmentManager();
    }

}
