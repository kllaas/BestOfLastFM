package com.example.alexey.bestoflastfm.ui;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.alexey.bestoflastfm.R;
import com.example.alexey.bestoflastfm.data.entity.local.Album;
import com.example.alexey.bestoflastfm.data.entity.local.Artist;
import com.example.alexey.bestoflastfm.ui.details_album.DetailsAlbumFragment;
import com.example.alexey.bestoflastfm.ui.details_artist.DetailsArtistFragment;
import com.example.alexey.bestoflastfm.ui.list_artists.ArtistsFragment;

/**
 * Created by alexey
 */

public class NavigationManager {

    public interface NavigationListener {

        void onBackStackChanged();
    }

    private FragmentManager fragmentManager;

    private NavigationListener navigationListener;

    public void init(FragmentManager fragmentManager, NavigationListener navigationListener) {
        this.fragmentManager = fragmentManager;
        this.navigationListener = navigationListener;

        FragmentManager.enableDebugLogging(true);

        fragmentManager.addOnBackStackChangedListener(() -> {

            if (navigationListener != null)
                navigationListener.onBackStackChanged();
        });
    }

    private void open(Fragment fragment) {
        if (fragment == null) return;

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.add(R.id.fragments_container, fragment)
                .addToBackStack(fragment.toString())
                .commit();
    }

    private void openAsRoot(Fragment fragment) {
        popAllFragments();
        open(fragment);
    }

    private void popAllFragments() {
        int backStackCount = fragmentManager.getBackStackEntryCount();
        for (int i = 0; i < backStackCount; i++) {

            int backStackId = fragmentManager.getBackStackEntryAt(i).getId();

            fragmentManager.popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    public void openListFragment() {
        Fragment fragment = ArtistsFragment.newInstance();
        openAsRoot(fragment);
    }

    public void navigateBack(AppCompatActivity activity) {
        if (fragmentManager.getBackStackEntryCount() == 1) {

            activity.finish();
            return;
        }

        fragmentManager.popBackStackImmediate();
    }

    public void openDetailsArtistFragment(Artist artist) {
        DetailsArtistFragment fragment = DetailsArtistFragment.newInstance(artist);
        open(fragment);
    }

    public void openDetailsAlbumFragment(Album album, FragmentManager childFragmentManager) {
        DialogFragment fragment = DetailsAlbumFragment.newInstance(album);
        fragment.show(childFragmentManager, album.getName());
    }

    public boolean isRootFragmentVisible() {
        return fragmentManager.getBackStackEntryCount() <= 1;
    }

}
