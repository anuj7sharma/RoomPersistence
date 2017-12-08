package com.room.ui;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.room.utility.MyImageControlListener;
import com.squareup.leakcanary.LeakCanary;

import java.util.HashSet;
import java.util.Set;

/**
 * author by Anuj Sharma on 12/4/2017.
 */

public class GlobalActivity extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        if(GlobalActivity.context == null){
            GlobalActivity.context = getApplicationContext();
        }
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(this, config);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
    public static synchronized Context getGlobalContext(){
        return GlobalActivity.context;
    }
}
