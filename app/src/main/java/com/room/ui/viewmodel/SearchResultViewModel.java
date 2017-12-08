package com.room.ui.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.room.api.APIHandler;
import com.room.model.GetSearchResultResponse;
import com.room.model.ImagesResultResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * author by Anuj Sharma on 12/4/2017.
 */

public class SearchResultViewModel extends ViewModel {
    private List<ImagesResultResponse.ItemsBean> list;
    private MutableLiveData<List<ImagesResultResponse.ItemsBean>> responseResult;
    private MutableLiveData<String> failure;
    private MutableLiveData<String> message;

    public MutableLiveData<List<ImagesResultResponse.ItemsBean>> getResponseResult() {
        if (responseResult == null) responseResult = new MutableLiveData<>();
        return responseResult;
    }

    public MutableLiveData<String> getFailure() {
        if (failure == null) failure = new MutableLiveData<>();
        return failure;
    }

    public MutableLiveData<String> getMessage() {
        if (message == null) message = new MutableLiveData<>();
        return message;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        responseResult = null;
        System.out.println("on cleared called");
    }

    //    Observable<GetSearchResultResponse> observervable;
    public void hitSearchAPI(String type, int count, int page, String query) {
        System.out.println("Hit API ");
        Single<GetSearchResultResponse> observable = APIHandler.getInstance().getAPIMethods().
                getSearchResult(count, page, query);

        observable.flatMapObservable(new Function<GetSearchResultResponse, ObservableSource<ImagesResultResponse.ItemsBean>>() {
            @Override
            public ObservableSource<ImagesResultResponse.ItemsBean> apply(GetSearchResultResponse getSearchResultResponse) throws Exception {
                if (!getSearchResultResponse.getStatus().equals("success")) {
                    return Observable.error(new Exception("Error Occured while fetching data."));
                }
                return Observable.fromIterable(getSearchResultResponse.getData().getResult().getItems());
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ImagesResultResponse.ItemsBean>() {
                    @Override
                    public void onNext(ImagesResultResponse.ItemsBean itemsBean) {
                        if (list == null) list = new ArrayList<>();
                        list.add(itemsBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getFailure().setValue(e.getMessage() + "");
                    }

                    @Override
                    public void onComplete() {
                        //update recyclerview
                        if (list != null && list.size() > 0)
                            getResponseResult().setValue(list);
                    }
                });
    }
}
