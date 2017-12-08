package com.room.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;

import com.room.R;
import com.room.databinding.ActivityMainBinding;
import com.room.enums.CurrentScreen;
import com.room.ui.BaseActivity;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initViews();
        manageProgress(false);
        //load initial screen
        changeScreen(R.id.container_dashboard, CurrentScreen.SEARCH_LIST_SCREEN, false, false, null);
    }

    private void initViews() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        changeScreen(R.id.container_dashboard, CurrentScreen.SEARCH_LIST_SCREEN, false, false, null);
                        break;
                    case R.id.action_favourite:
                        changeScreen(R.id.container_dashboard, CurrentScreen.FAVOURITE_SCREEN, false, false, null);
                        break;
                }
                return true;
            }
        });
    }

    public void manageProgress(boolean isVisible) {
        if (isVisible) binding.progressBar.setVisibility(View.VISIBLE);
        else binding.progressBar.setVisibility(View.GONE);
    }

    /*private void moveToSearchActivity() {
        //Move to SearchActivity
        Intent intent = new Intent(this, HolderActivity.class);
        intent.putExtra(Constants.INTENT_ACTIONS.DESTINATION, Constants.INTENT_ACTIONS.SCREEN_SEARCH);
        intent.putExtra(Constants.INTENT_ACTIONS.data_search_value, etSearch.getText().toString());
        if (Utils.getInstance().isEqualLollipop()) {
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(this, etSearch, getString(R.string.transition_search));
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }*/


}
