package com.room.utility;

import android.graphics.drawable.Animatable;

import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;

import javax.annotation.Nullable;

/**
 * author by Anuj Sharma on 12/8/2017.
 */

public class MyImageControlListener extends BaseControllerListener<ImageInfo> {
    @Override
    public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
        super.onIntermediateImageSet(id, imageInfo);
    }

    @Override
    public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
        super.onFinalImageSet(id, imageInfo, animatable);
    }

    @Override
    public void onFailure(String id, Throwable throwable) {
        super.onFailure(id, throwable);
    }

    @Override
    public void onIntermediateImageFailed(String id, Throwable throwable) {
        super.onIntermediateImageFailed(id, throwable);
    }

    @Override
    public void onRelease(String id) {
        super.onRelease(id);
    }

    @Override
    public void onSubmit(String id, Object callerContext) {
        super.onSubmit(id, callerContext);
    }
}
