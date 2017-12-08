package com.room.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Anuj Sharma on 3/30/2017.
 */

public class BaseFragment extends Fragment {
    Bundle mbundle;

    public Bundle getMbundle() {
        return mbundle;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setCustomArgus(Bundle customArgus) {
        mbundle = customArgus;
    }
}
