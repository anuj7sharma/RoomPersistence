package com.room.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.room.ui.BaseActivity;

/**
 * author by Anuj Sharma on 12/4/2017.
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Move to MainActivity
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
