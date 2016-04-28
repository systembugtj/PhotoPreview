package me.systembug.ux.gallery;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import net.ganin.darv.DpadAwareRecyclerView;
import net.ganin.darv.ExtGridLayoutManager;

import java.util.List;

/**
 * Created by systembug on 4/12/16.
 */
public class GalleryView extends FrameLayout {

    protected SimpleDraweeView mImageView;
    protected DpadAwareRecyclerView mRecyclerView;

    private ImageAdapter mAdapter;
    private int mLayout = R.layout.gallery_view;

    public GalleryView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GalleryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        View v = LayoutInflater.from(context).inflate(mLayout, null);
        mImageView = (SimpleDraweeView) v.findViewById(R.id.image_view);
        mRecyclerView = (DpadAwareRecyclerView) v.findViewById(R.id.image_gallery);
        mRecyclerView.setLayoutManager(new ExtGridLayoutManager(getContext(), 1, LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new ImageAdapter();
        mAdapter.click(new ImageAdapter.OnImageClickedListener() {
            @Override
            public void onClick(String url) {
                loadImage(url);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        addView(v);
    }

    public GalleryView images(List<String> images) {
        mAdapter.setImages(images);

        if (images.size() > 0) {
            loadImage(images.get(0));
        }
        return this;
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


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        mRecyclerView.requestFocus();
        return mRecyclerView.onKeyUp(keyCode, event) || super.onKeyUp(keyCode, event);
    }

}
