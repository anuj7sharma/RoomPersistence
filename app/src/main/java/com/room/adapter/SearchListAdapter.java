package com.room.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.room.R;
import com.room.databinding.ViewImagesBinding;
import com.room.db.table.Favourite;

import java.util.List;

/**
 * author by Anuj Sharma on 12/4/2017.
 */

public class SearchListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ViewImagesBinding binding;
    private List<Favourite> list;
    private SearchItemInterface listener;

    public interface SearchItemInterface {
        void onItemClick(Favourite obj, ImageView imageView);
    }

    public SearchListAdapter(Context context, List<Favourite> list, SearchItemInterface listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    public void updateList(List<Favourite> getSearchResultResponse) {
        if (list == null) {
            list = getSearchResultResponse;
            notifyDataSetChanged();
        } else {
            int lastItem = list.size() - 1;
            list.addAll(getSearchResultResponse);
            notifyItemRangeInserted(lastItem, list.size() - 1);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = ViewImagesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SearchListHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SearchListHolder) {
            SearchListHolder vh = (SearchListHolder) holder;
            Favourite obj = list.get(position);

//            System.out.println("Media-> " + obj.getMedia());
            vh.bindData(obj);
        }

    }

    @Override
    public int getItemCount() {
        return (list == null) ? 0 : list.size();
    }


    class SearchListHolder extends RecyclerView.ViewHolder {
        public SearchListHolder(ViewImagesBinding binding) {
            super(binding.getRoot());
            binding.itemImage.setOnClickListener(view -> {
                if (listener != null && list != null && list.get(getAdapterPosition()) != null && getAdapterPosition() != -1)
                    listener.onItemClick(list.get(getAdapterPosition()), binding.itemImage);
            });
        }

        void bindData(Favourite obj) {
            binding.setImageModel(obj);
            binding.executePendingBindings();

            GenericDraweeHierarchy hierarchy =
                    GenericDraweeHierarchyBuilder.newInstance(context.getResources())
//                            .setActualImageColorFilter(colorFilter)
//                            .setActualImageFocusPoint(focusPoint)
                            .setActualImageScaleType(ScalingUtils.ScaleType.CENTER_CROP)
//                            .setBackground(background)
//                            .setDesiredAspectRatio(desiredAspectRatio)
                            .setFadeDuration(300)
                            .setFailureImage(ContextCompat.getDrawable(context, R.drawable.ic_no_media))
                            .setFailureImageScaleType(ScalingUtils.ScaleType.CENTER)
//                            .setOverlays(overlays)
                            .setPlaceholderImage(ContextCompat.getDrawable(context, R.drawable.ic_no_media))
                            .setPlaceholderImageScaleType(ScalingUtils.ScaleType.CENTER)
//                            .setPressedStateOverlay(overlay)
//                            .setProgressBarImage(progressBarImage)
//                            .setProgressBarImageScaleType(scaleType)
//                            .setRetryImage(retryImage)
//                            .setRetryImageScaleType(scaleType)
//                            .setRoundingParams(roundingParams)
                            .build();
            binding.itemImage.setHierarchy(hierarchy);

            Uri uri = Uri.parse(obj.getMedia());
            Fresco.getImagePipeline().prefetchToBitmapCache(
                    ImageRequest.fromUri(uri), context);
            ImageRequest imageRequest = ImageRequestBuilder
                    .newBuilderWithSource(uri)
                    .setResizeOptions(new ResizeOptions(400, 400))
                    .setProgressiveRenderingEnabled(true)
                    .setLocalThumbnailPreviewsEnabled(true)
                    .build();

            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(imageRequest)
                    .setOldController(binding.itemImage.getController())
                    .setAutoPlayAnimations(true)
                    .build();
            binding.itemImage.setController(controller);
        }
    }
}
