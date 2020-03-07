package com.cts.list.util.binder;

import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.cts.list.R;

import androidx.databinding.BindingAdapter;


public class CustomBinders {


    private static final String TAG = "CustomBinders";

    @BindingAdapter({"image_url"})
    public static void loadImageWithOUtProgressBar(ImageView view, String imageUrl) {
        Log.d(TAG, "before loadImageWithOUtProgressBar: " + imageUrl);
        Log.d(TAG, "after loadImageWithOUtProgressBar: " + imageUrl);

        Glide.with(view.getContext())
                .load(imageUrl)
                .error(R.drawable.noimage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(view);

    }


}
