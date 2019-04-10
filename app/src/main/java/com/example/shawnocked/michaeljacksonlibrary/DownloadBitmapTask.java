package com.example.shawnocked.michaeljacksonlibrary;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

/*
 Download Bitmap using AsyncTask so it won't block UI
 */

public class DownloadBitmapTask extends AsyncTask<String, Void, Bitmap> {

    // The task will fill in the views in the view holder
    private RecyclerViewAdapter.ViewHolder viewHolder;

    private String url;

    public DownloadBitmapTask(RecyclerViewAdapter.ViewHolder viewHolder) {
        this.viewHolder = viewHolder;
    }

    public String getUrl() {
        return url;
    }

    @Override
    protected Bitmap doInBackground(String... url) {
        // we need this here since doInBackground runs in a separate thread
        Bitmap bm = Utility.downloadBitmap(
                url[0],
                viewHolder.mjImage.getMaxWidth(),
                viewHolder.mjImage.getMaxHeight()
        );
        this.url = url[0];
        return bm;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        if (this.isCancelled()) { return; }

        ImageView iv = viewHolder.mjImage;
        DownloadBitmapTask iv_task = AsyncDrawable.getDownloadBitmapTaskReference(iv);

        if (this == iv_task && iv != null) {
            this.viewHolder.mjImage.setImageBitmap(bitmap);
        }
    }


}
