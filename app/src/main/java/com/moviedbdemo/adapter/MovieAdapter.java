package com.moviedbdemo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.moviedbdemo.R;
import com.moviedbdemo.databinding.ItemMovieAdapterBinding;
import com.moviedbdemo.model.Images;
import com.moviedbdemo.model.MovieModel;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private ArrayList<MovieModel> arrayList;
    private Context context;
    private Images images;

    public MovieAdapter(Context ctx, ArrayList<MovieModel> arrayList, Images images) {
        this.arrayList = arrayList;
        this.context = ctx;
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieAdapterBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_movie_adapter, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieModel dataModel = arrayList.get(position);
        holder.bind(dataModel, images);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ItemMovieAdapterBinding itemRowBinding;

        public ViewHolder(ItemMovieAdapterBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

        public void bind(MovieModel model, Images images) {
            model.setImages(images);
            itemRowBinding.setModel(model);
            itemRowBinding.executePendingBindings();

            String imagePath=model.getBannerUrl();
            Log.e("ImagePath", imagePath);
            Glide.with(context)
                    .load(imagePath)
                    .into(itemRowBinding.imageView);

           /* Glide.with(context)
                    .load(model.getContentThumbnailUrl())
                    .apply(new RequestOptions().placeholder(R.drawable.placeholder2).error(R.drawable.placeholder2))
                    .into(itemRowBinding.imgItem);*/

        }
    }
}
