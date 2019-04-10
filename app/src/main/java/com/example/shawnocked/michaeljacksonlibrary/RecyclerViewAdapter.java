package com.example.shawnocked.michaeljacksonlibrary;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.ArrayList;

/*
 Class of customized adapter for RecyclerView
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

//    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> images;
    private ArrayList<String> trackNames;
    private ArrayList<String> previewURLList;
    private ArrayList<String> collectionPriceList;
    private ArrayList<String> releaseDateList;

    private Activity act;
    private Bitmap placeholder;

    public RecyclerViewAdapter(ArrayList<String> images, ArrayList<String> trackNames,
                               ArrayList<String> previewURLList, ArrayList<String> collectionPriceList,
                               ArrayList<String> releaseDateList, Activity act) {
        this.images = images;
        this.trackNames = trackNames;
        this.previewURLList = previewURLList;
        this.collectionPriceList = collectionPriceList;
        this.releaseDateList = releaseDateList;
        this.act = act;
        this.placeholder = BitmapFactory.decodeResource(
                this.act.getResources(),
                R.drawable.michaeljackson
        );
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_recycleritem, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        String url = images.get(position);

        // we need to figure out if there is already a task existing
        // if so, we need to cancel it
        if (cancelCurrentTask(url, viewHolder.mjImage)) {
            DownloadBitmapTask task = new DownloadBitmapTask(viewHolder);
            AsyncDrawable asyncDrawable = new AsyncDrawable(
                    this.act.getResources(),
                    this.placeholder,
                    task);
            viewHolder.mjImage.setImageDrawable(asyncDrawable);
            task.execute(url);
        }

        viewHolder.mjImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // jump into detail activity to show details if image is clicked
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("TRACK", trackNames.get(position));
                intent.putExtra("PREVIEW", previewURLList.get(position));
                intent.putExtra("COLLECTION", collectionPriceList.get(position));
                intent.putExtra("DATE", releaseDateList.get(position));
                view.getContext().startActivity(intent);
            }
        });
    }

    // method to see if a CurrentTask should be canceled
    private boolean cancelCurrentTask(
            String url,  ImageView imageView) {

        // get the task for the imageview if it exists.
        DownloadBitmapTask bitmapTask = AsyncDrawable.getDownloadBitmapTaskReference(imageView);

        if (bitmapTask != null) {

            String taskURL = bitmapTask.getUrl();
            if (taskURL == null || !url.equals(taskURL)) {
                bitmapTask.cancel(true);
                return true;
            }
            else {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView mjImage;

        public ViewHolder(View itemView) {
            super(itemView);

            this.mjImage = itemView.findViewById(R.id.mj_item_image);
            this.mjImage.setAdjustViewBounds(true);
        }
    }
}
