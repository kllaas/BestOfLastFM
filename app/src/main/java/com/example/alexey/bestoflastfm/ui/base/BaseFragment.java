package com.example.alexey.bestoflastfm.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.alexey.bestoflastfm.App;
import com.example.alexey.bestoflastfm.di.components.DaggerFragmentComponent;
import com.example.alexey.bestoflastfm.di.components.FragmentComponent;
import com.example.alexey.bestoflastfm.di.modules.FragmentModule;

import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements BaseView {

    private FragmentComponent component;
    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        component = DaggerFragmentComponent.builder()
                .applicationComponent(App.appComponent)
                .fragmentModule(new FragmentModule(this))
                .build();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpViews();
    }

    @Override
    public void onDestroy() {
        if (unbinder != null) {
            unbinder.unbind();
        }

        super.onDestroy();
    }

    protected abstract void setUpViews();

    protected FragmentComponent getComponent() {
        return component;
    }

    protected void setUnBinder(Unbinder unbinder) {
        this.unbinder = unbinder;
    }

}
