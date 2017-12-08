package com.room.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.room.R;
import com.room.adapter.FavouriteAdapter;
import com.room.databinding.FragmentFavouriteBinding;
import com.room.db.AppDatabase;
import com.room.db.table.Favourite;
import com.room.ui.BaseFragment;
import com.room.ui.activity.HolderActivity;
import com.room.ui.viewmodel.FavouriteViewModel;
import com.room.utility.Constants;
import com.room.utility.SpacesItemDecoration;
import com.room.utility.Utils;

import java.util.List;

/**
 * author by Anuj Sharma on 12/6/2017.
 */

public class FavouriteFragment extends BaseFragment implements FavouriteAdapter.FavouriteItemInterface {
    private FragmentFavouriteBinding binding;
    private FavouriteViewModel viewModel;
    private FavouriteAdapter adapter;
    IntentFilter updateIntentFilter = new IntentFilter(Constants.BroadcastIntent.UPDATE_FOVOURITE);
    BroadcastReceiver updateFavourite = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Constants.BroadcastIntent.UPDATE_FOVOURITE)) {
                updateFavouriteList();
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourite, container, false);
        viewModel = ViewModelProviders.of(this).get(FavouriteViewModel.class);
        getActivity().registerReceiver(updateFavourite, updateIntentFilter);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        getActivity().unregisterReceiver(updateFavourite);
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        Flowable.
        updateFavouriteList();

    }

    private void updateFavouriteList() {
        List<Favourite> list = AppDatabase.getDatabaseInstance(getActivity()).favouriteDao().getAll();
        if (list != null && list.size() > 0) {
            binding.emptyView.setVisibility(View.INVISIBLE);
            int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.margin_4);
            StaggeredGridLayoutManager sm = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
            binding.favouriteRecycler.setLayoutManager(sm);
            binding.favouriteRecycler.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
            adapter = new FavouriteAdapter(getActivity(), list, this);
            binding.favouriteRecycler.setAdapter(adapter);
        } else {
            if (adapter != null)
                adapter.updateList(null);
            binding.emptyView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(Favourite obj, ImageView imageView) {
        if (obj != null) {
            Intent intent = new Intent(getActivity(), HolderActivity.class);
            intent.putExtra(Constants.INTENT_ACTIONS.DESTINATION, Constants.INTENT_ACTIONS.SCREEN_DETAIL);
            intent.putExtra(Constants.INTENT_ACTIONS.data_image_obj, obj);
            if (Utils.getInstance().isEqualLollipop()) {
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(getActivity(), imageView, getString(R.string.transition_image));
                startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        }
    }
}
