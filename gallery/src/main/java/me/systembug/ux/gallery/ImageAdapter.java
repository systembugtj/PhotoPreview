package me.systembug.ux.gallery;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by systembug on 4/12/16.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHoler> {

    @Override
    public ImageAdapter.ViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ImageAdapter.ViewHoler holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHoler extends RecyclerView.ViewHolder {

        public ViewHoler(View itemView) {
            super(itemView);
        }
    }
}
