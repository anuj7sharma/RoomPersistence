package com.room.ui.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;
import com.room.R;
import com.room.adapter.SearchListAdapter;
import com.room.databinding.FragmentResultListBinding;
import com.room.model.MediaModel;
import com.room.db.table.Favourite;
import com.room.ui.BaseFragment;
import com.room.ui.activity.HolderActivity;
import com.room.ui.viewmodel.SearchResultViewModel;
import com.room.utility.Constants;
import com.room.utility.SpacesItemDecoration;
import com.room.utility.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * author by Anuj Sharma on 12/4/2017.
 */

public class SearchResultFragment extends BaseFragment implements SearchListAdapter.SearchItemInterface {
    private FragmentResultListBinding binder;
    private SearchResultViewModel viewModel;
    private String search_type = "images";
    private int page = 1, count = 10;
    private SearchListAdapter adapter;

    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binder = DataBindingUtil.inflate(inflater, R.layout.fragment_result_list, container, false);
        viewModel = ViewModelProviders.of(this).get(SearchResultViewModel.class);
        binder.setIsProgress(false);
        binder.executePendingBindings();
        return binder.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String query = "beaches";
//        String query = getArguments().getString(Constants.INTENT_ACTIONS.data_search_value, "nature");
//        System.out.println("Search Value-> " + query);

        if (getActivity() instanceof HolderActivity) {
            ((HolderActivity) getActivity()).setSupportActionBar(binder.toolbar);
            ((HolderActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            binder.toolbar.setNavigationOnClickListener(view1 -> ((HolderActivity) getActivity()).oneStepBack());
        }
        initSearch();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//        binder.addressSearch.setQuery(query, true);
        //initialize RecyclerView
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.margin_4);
        StaggeredGridLayoutManager sm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        binder.searchRecycler.setLayoutManager(sm);
        binder.searchRecycler.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
        adapter = new SearchListAdapter(getActivity(), null, this);
        binder.searchRecycler.setAdapter(adapter);

        binder.searchRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = sm.getChildCount();
                    totalItemCount = sm.getItemCount();
                    pastVisiblesItems = sm.findFirstVisibleItemPositions(null)[0];

                    if (loading) {
                        /*if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            Log.v("...", "Last Item Wow !");
                            //Do pagination.. i.e. fetch new data
                            page++;
                            viewModel.hitSearchAPI(search_type, count, page, query);

                        }*/
                    }
                }

            }
        });
        initializeObservers();

        //Static Data coming from Json stored in assets folder
        Gson gson = new Gson();
        MediaModel mediaModel = gson.fromJson(getStringFromLocalJson("media_list.json", getActivity()), MediaModel.class);
        adapter.updateList(mediaModel.getData().getMediaList());
    }

    private void initSearch() {
        EditText searchEditText = binder.addressSearch.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.app_textcolor));
        searchEditText.setHintTextColor(getResources().getColor(R.color.dark_gray));

        RxSearchView.queryTextChanges(binder.addressSearch).debounce(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(charSequence -> {
                    // perform necessary operation with `charSequence`
                    if (charSequence.toString().length() > 0) {
                        /*if (Utils.isNetworkAvailable(getActivity())) {
                            page = 1;
                            viewModel.hitSearchAPI(search_type, count, page, charSequence.toString());
                        } else {
                            Utils.getInstance().showSnakBar(binder.getRoot(), "Internet not working");
                        }*/
                    }
                });
    }

    private void initializeObservers() {
        viewModel.getMessage().observe(getActivity(), s -> Utils.getInstance().showToast(s));

        viewModel.getResponseResult().observe(getActivity(), getSearchResultResponse -> {
            loading = true;
            binder.setIsProgress(false);
            binder.executePendingBindings();
//            if (page == 1) {
//                adapter.updateList(null);
//            }
//            adapter.updateList(getSearchResultResponse);
        });

        viewModel.getFailure().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                binder.setIsProgress(false);
                binder.executePendingBindings();
                Utils.getInstance().showToast(s);
            }
        });
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

    public String getStringFromLocalJson(String assetsFileName, Context context) {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(context.getAssets().open(assetsFileName)));
            String temp;
            while ((temp = br.readLine()) != null)
                sb.append(temp);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close(); // stop reading
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}
