package com.room.ui.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.transition.ChangeImageTransform;
import android.support.transition.TransitionManager;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.room.R;
import com.room.databinding.FragmentDetailBinding;
import com.room.db.AppDatabase;
import com.room.db.table.Favourite;
import com.room.ui.BaseFragment;
import com.room.ui.activity.HolderActivity;
import com.room.ui.viewmodel.DetailViewModel;
import com.room.utility.Constants;
import com.room.utility.Utils;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author by Anuj Sharma on 12/5/2017.
 */

public class DetailFragment extends BaseFragment implements View.OnClickListener {

    private FragmentDetailBinding binding;
    private DetailViewModel viewModel;
    private Favourite obj;
    private boolean isFavourite;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);
        viewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() instanceof HolderActivity) {
            ((HolderActivity) getActivity()).setSupportActionBar(binding.includeToolbar.toolbar);
            ((HolderActivity) getActivity()).getSupportActionBar().setTitle("Detail");
            ((HolderActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            binding.includeToolbar.toolbar.setNavigationOnClickListener(view1 -> {
                ((HolderActivity) getActivity()).oneStepBack();
            });
        }
        binding.btnFavourite.setOnClickListener(this);
        if (getArguments() != null && getArguments().getParcelable(Constants.INTENT_ACTIONS.data_image_obj) != null) {
            obj = getArguments().getParcelable(Constants.INTENT_ACTIONS.data_image_obj);
            if (!TextUtils.isEmpty(obj.getTitle()))
                ((HolderActivity) getActivity()).getSupportActionBar().setTitle(obj.getTitle());

            Glide.with(getActivity()).load(obj.getMedia()).crossFade().listener(new RequestListener<String, GlideDrawable>() {
                @Override
                public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                    binding.progressBar.setVisibility(View.GONE);
                    return false;
                }

                @Override
                public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    binding.progressBar.setVisibility(View.GONE);
                    return false;
                }
            }).into(binding.detailImage);
            /*Uri uri = Uri.parse(obj.getMedia());

            GenericDraweeHierarchy hierarchy =
                    GenericDraweeHierarchyBuilder.newInstance(getResources())
//                            .setActualImageColorFilter(colorFilter)
//                            .setActualImageFocusPoint(focusPoint)
                            .setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP)
//                            .setBackground(background)
//                            .setDesiredAspectRatio(desiredAspectRatio)
                            .setFadeDuration(300)
                            .setFailureImage(ContextCompat.getDrawable(getActivity(), R.drawable.ic_no_media))
                            .setFailureImageScaleType(ScalingUtils.ScaleType.CENTER)
//                            .setOverlays(overlays)
                            .setPlaceholderImage(ContextCompat.getDrawable(getActivity(), R.drawable.ic_no_media))
                            .setPlaceholderImageScaleType(ScalingUtils.ScaleType.CENTER)
//                            .setPressedStateOverlay(overlay)
//                            .setProgressBarImage(progressBarImage)
//                            .setProgressBarImageScaleType(scaleType)
//                            .setRetryImage(retryImage)
//                            .setRetryImageScaleType(scaleType)
//                            .setRoundingParams(roundingParams)
                            .build();
            binding.detailImage.setHierarchy(hierarchy);

            ImageRequest imageRequest = ImageRequestBuilder
                    .newBuilderWithSource(uri)
                    .setResizeOptions(new ResizeOptions(300, 300))
                    .setProgressiveRenderingEnabled(true)
                    .build();

            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(imageRequest)
                    .setOldController(binding.detailImage.getController())
                    .setAutoPlayAnimations(true)
                    .build();
            binding.detailImage.setController(controller);*/



            /*Fresco.getImagePipeline().prefetchToBitmapCache(
                    ImageRequest.fromUri(uri), getActivity());

            ImageRequest imageRequest = ImageRequestBuilder
                    .newBuilderWithSource(uri)
                    .setProgressiveRenderingEnabled(true)
                    .setLocalThumbnailPreviewsEnabled(true)
                    .build();
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(imageRequest)
                    .setOldController(binding.detailImage.getController())
                    .setAutoPlayAnimations(true)
                    .setControllerListener(new BaseControllerListener<ImageInfo>(

                    ))
                    .setControllerListener(new ControllerListener<ImageInfo>() {
                        @Override
                        public void onSubmit(String id, Object callerContext) {
                            System.out.println("Submit");
                            onFinalImageSet("", null, null);
                        }

                        @Override
                        public void onFinalImageSet(String id, @javax.annotation.Nullable ImageInfo imageInfo, @javax.annotation.Nullable Animatable animatable) {
                            binding.progressBar.setVisibility(View.GONE);
                            if (imageInfo != null) {
                                QualityInfo qualityInfo = imageInfo.getQualityInfo();
                            }
                        }

                        @Override
                        public void onIntermediateImageSet(String id, @javax.annotation.Nullable ImageInfo imageInfo) {
                            if (imageInfo != null) {
                                QualityInfo qualityInfo = imageInfo.getQualityInfo();
                            }
                        }

                        @Override
                        public void onIntermediateImageFailed(String id, Throwable throwable) {
                            System.out.println("Intermediate Failed image loading");
                        }

                        @Override
                        public void onFailure(String id, Throwable throwable) {
                            binding.progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onRelease(String id) {
                            System.out.println("Release called");
                        }
                    })
                    .build();

            binding.detailImage.setController(controller);*/
            //check if media item is already in favourite list
            isFavourite = AppDatabase.getDatabaseInstance(getActivity()).favouriteDao().isFavourite(obj.getMedia());
            manageFavourite();

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_favourite:
                if (isFavourite) {
                    //Mark it unfavourite
                    isFavourite = false;
                } else {
                    //mark it favourite
                    isFavourite = true;
                }
                //Add/Remove Item to Database
                Single.create((SingleOnSubscribe<Favourite>) e -> {
                    if (isFavourite)
                        AppDatabase.getDatabaseInstance(getActivity()).favouriteDao().insert(obj);
                    else
                        AppDatabase.getDatabaseInstance(getActivity()).favouriteDao().delete(obj.getMediaId());

                    AppDatabase.getDatabaseInstance(getActivity()).destroyInstance();
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe();
                manageFavourite();

                Intent updateFavourite = new Intent(Constants.BroadcastIntent.UPDATE_FOVOURITE);
                getActivity().sendBroadcast(updateFavourite);
                break;
        }
    }

    private void manageFavourite() {
        if (Utils.getInstance().isEqualLollipop()) {
            TransitionManager.beginDelayedTransition((ViewGroup) binding.getRoot(), new ChangeImageTransform());
        }
        if (isFavourite) {
            binding.btnFavourite.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_heart_red));
        } else {
            binding.btnFavourite.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_heart_outline));
        }
    }
}
