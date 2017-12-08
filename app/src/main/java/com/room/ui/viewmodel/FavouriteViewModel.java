package com.room.ui.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.room.db.table.Favourite;

import java.util.List;

/**
 * author by Anuj Sharma on 12/6/2017.
 */

public class FavouriteViewModel extends ViewModel {
    private MutableLiveData<Favourite> response;
    private MutableLiveData<String> message;

    public MutableLiveData<Favourite> getResponse() {
        if (response == null) response = new MutableLiveData<>();
        return response;
    }

    public MutableLiveData<String> getMessage() {
        if (message == null) message = new MutableLiveData<>();
        return message;
    }

    public List<Favourite> getFavouriteList() {

        return null;
    }

}
