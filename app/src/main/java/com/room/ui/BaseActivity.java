package com.room.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.room.enums.CurrentScreen;
import com.room.ui.fragment.DetailFragment;
import com.room.ui.fragment.FavouriteFragment;
import com.room.ui.fragment.SearchResultFragment;

/**
 * @author Anuj Sharma on 3/30/2017.
 */

public class BaseActivity extends AppCompatActivity {
    /*public void navigateTo(int container, Fragment fragment, boolean isBackStack) {
        try {
            FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
            fts.replace(container, fragment, fragment.getClass().getSimpleName());
            if (isBackStack)
                fts.addToBackStack(fragment.getClass().getSimpleName());
            fts.commit();

        } catch (Exception e) {
            Utils.e(e);
        }

    }*/

    public void navigateToWithBundle(int container, Fragment fragment, boolean isBackStack, Bundle bundle) {
        fragment.setArguments(bundle);
        FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
        fts.replace(container, fragment, fragment.getClass().getSimpleName());
        if (isBackStack)
            fts.addToBackStack(fragment.getClass().getSimpleName());
        fts.commit();
    }



   /* public void navigateReplacingCurrentWithBundle(int container, Fragment currentFragment, Fragment fragmentToNavigate, Bundle bundle) {
        fragmentToNavigate.setArguments(bundle);
        FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
        getSupportFragmentManager().popBackStack();
        fts.replace(container, fragmentToNavigate);
        fts.addToBackStack(fragmentToNavigate.getClass().getSimpleName());
        fts.remove(currentFragment).commit();
    }*/

    /*public void addFragment(int container, Fragment fragment, boolean isBackStack) {
        FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
        fts.add(container, fragment, fragment.getClass().getSimpleName());
        if (isBackStack)
            fts.addToBackStack(fragment.getClass().getSimpleName());
        fts.commit();
    }*/

    public void addFragmentWithBundle(int container, Fragment fragment, boolean isBackStack, Bundle bundle) {
        FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
        fragment.setArguments(bundle);
        fts.add(container, fragment, fragment.getClass().getSimpleName());
        if (isBackStack)
            fts.addToBackStack(fragment.getClass().getSimpleName());
        fts.commit();
    }

    public void oneStepBack() {
        FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() >= 1) {
            fragmentManager.popBackStackImmediate();
            fts.commit();
        } else {
            supportFinishAfterTransition();
        }
    }

    /**
     * Generic method to change fragment
     */
    public void changeScreen(int container, CurrentScreen currentScreen, boolean isAddFragment, boolean isBackStack, Bundle bundle) {
        Fragment currentFragment = null;
        switch (currentScreen) {
            case SEARCH_LIST_SCREEN:
                currentFragment = new SearchResultFragment();
                currentFragment.setArguments(bundle);
                break;
            case FAVOURITE_SCREEN:
                currentFragment = new FavouriteFragment();
                currentFragment.setArguments(bundle);
                break;
            case DETAIL_SCREEN:
                currentFragment = new DetailFragment();
                currentFragment.setArguments(bundle);
                break;

        }
        if (currentFragment == null) {
            return;
        }
        if (isAddFragment) {
            //Add Fragment
            if (isBackStack)
                addFragmentWithBundle(container, currentFragment, true, bundle);
            else
                addFragmentWithBundle(container, currentFragment, false, bundle);
        } else {
            //Replace Fragment
            if (isBackStack)
                navigateToWithBundle(container, currentFragment, true, bundle);
            else
                navigateToWithBundle(container, currentFragment, false, bundle);
        }
    }
}
