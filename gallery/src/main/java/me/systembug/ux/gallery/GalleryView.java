package me.systembug.ux.gallery;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by systembug on 4/12/16.
 */
public class GalleryView extends FrameLayout {

    protected SimpleDraweeView mImageView;

    public GalleryView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GalleryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        mImageView = new SimpleDraweeView(context);

        LayoutParams param =  new LayoutParams(context, )
        addView(mImageView, param);


    }


}
