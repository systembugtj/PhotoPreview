package me.systembug.ux.gallery;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Animatable;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.view.menu.ListMenuItemView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by systembug on 4/12/16.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHoler>{

    public interface OnImageClickedListener {

        void onClick (String url);
    }

    protected List<String> mImages = new ArrayList<String>();

    protected int mLayout = R.layout.default_image_view;

    private OnImageClickedListener mClick;

    public ImageAdapter() {

    }

    public ImageAdapter(List<String> images) {
        setImages(images);
    }

    public ImageAdapter(List<String> images, int layout) {
        setImages(images);
        mLayout = layout;
    }

    public ImageAdapter click(OnImageClickedListener listener) {
        mClick = listener;
        return this;
    }

    public void setImages(List<String> images) {
        mImages.clear();
        if (images != null) {
            mImages.addAll(images);
        }
        notifyDataSetChanged();
    }

    @Override
    public ImageViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(mLayout, null);
        ImageViewHoler holder = new ImageViewHoler(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(ImageAdapter.ImageViewHoler holder, int position) {
        holder.initView(position);
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public class ImageViewHoler extends RecyclerView.ViewHolder {

        private SimpleDraweeView mImageView;
        private String mUrl;

        public ImageViewHoler(View itemView) {
            super(itemView);
            mImageView = (SimpleDraweeView) itemView.findViewById(R.id.image_view);
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClick != null) {
                        mClick.onClick(mUrl);
                    }
                }
            });
        }

        public void initView(int position) {
            mUrl = mImages.get(position);

            if(mImageView != null) {
                WindowManager wm = (WindowManager) itemView.getContext().getSystemService(Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);

                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(size.x / 6, size.y / 4);
                mImageView.setLayoutParams(params);

                loadImage(mUrl);
            }
        }

        public void loadImage(String url) {
            int width = 960, height = 540;
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                    .setProgressiveRenderingEnabled(true)
                    .setResizeOptions(new ResizeOptions(width, height))
                    .build();
            PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                    .setImageRequest(request)
                    .build();
            mImageView.setController(controller);
        }
    }
}
