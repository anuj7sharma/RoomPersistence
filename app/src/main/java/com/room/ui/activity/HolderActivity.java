package com.room.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.room.R;
import com.room.databinding.ActivityHolderBinding;
import com.room.enums.CurrentScreen;
import com.room.ui.BaseActivity;
import com.room.utility.Constants;

/**
 * author by Anuj Sharma on 12/4/2017.
 */

public class HolderActivity extends BaseActivity {
    private ActivityHolderBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_holder);
        Bundle bundle = new Bundle();
        if (getIntent() != null && getIntent().getStringExtra(Constants.INTENT_ACTIONS.DESTINATION) != null) {
            if (getIntent().getStringExtra(Constants.INTENT_ACTIONS.DESTINATION).equals(Constants.INTENT_ACTIONS.SCREEN_SEARCH)) {
                String searchValue = getIntent().getStringExtra(Constants.INTENT_ACTIONS.data_search_value);
                bundle.putString(Constants.INTENT_ACTIONS.data_search_value, searchValue);
                changeScreen(R.id.container_holder, CurrentScreen.SEARCH_LIST_SCREEN, false, false, bundle);
            } else if (getIntent().getStringExtra(Constants.INTENT_ACTIONS.DESTINATION).equals(Constants.INTENT_ACTIONS.SCREEN_DETAIL)) {
                bundle.putParcelable(Constants.INTENT_ACTIONS.data_image_obj, getIntent().getParcelableExtra(Constants.INTENT_ACTIONS.data_image_obj));
                changeScreen(R.id.container_holder, CurrentScreen.DETAIL_SCREEN, false, false, bundle);
            }
        }
    }
}
