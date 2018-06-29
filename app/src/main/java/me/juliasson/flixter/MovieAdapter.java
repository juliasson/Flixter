package me.juliasson.flixter;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import me.juliasson.flixter.models.Config;
import me.juliasson.flixter.models.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    //list of movies
    ArrayList<Movie> movies;
    //config needed for image urls
    Config config;
    //context for rendering
    Context context;

    //initialize with list
    public MovieAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    //implement extended class
    //creates and inflates a new view
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //get the context and create the inflater
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //create the actual view object using the item_movie layout
        View movieView = inflater.inflate(R.layout.item_movie, parent, false);
        //return a new ViewHolder
        return new ViewHolder(movieView);
    }

    //associates an inflated view to a new item
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //get the movie data at the specific position
        Movie movie = movies.get(position);
        //populate the view with the movie data
        holder.tvTitle.setText(movie.getTitle());
        holder.tvOverview.setText(movie.getSynopsis());

        //determine phone's orientation
        boolean isPortrait = context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
        String imageUrl = null;

        //if in portrait mode, load poster image.
        if (isPortrait) {
            //build a url for poster image
            imageUrl = config.getImageUrl(config.getPosterSize(), movie.getPosterPath());
        } else {
            //load the backdrop
            imageUrl = config.getImageUrl(config.getPosterSize(), movie.getBackdropPath());
        }

        //get the correct placeholder and image view for the current orientation
        //using the ternary operator
        int placeholderId = isPortrait ?
                R.drawable.flicks_movie_placeholder :
                R.drawable.flicks_backdrop_placeholder;
        ImageView imageView = isPortrait ?
                holder.ivPosterImage :
                holder.ivBackdropImage;

        //load image using glide
        Glide.with(context)
                .load(imageUrl)
                .apply(
                        RequestOptions.placeholderOf(placeholderId)
                                .error(R.drawable.flicks_movie_placeholder)
                                .fitCenter()
                ).apply(
                        RequestOptions.bitmapTransform(
                                        new RoundedCornersTransformation(25,0))
        ).into(imageView);

    }

    public void setConfig(Config config) {
        this.config = config;
    }

    //returns the size of the entire data set
    @Override
    public int getItemCount() {
        return movies.size();
    }

    //create the viewHolder as a static inner class
    public static class ViewHolder extends RecyclerView.ViewHolder {

        //track view objects
        ImageView ivPosterImage;
        ImageView ivBackdropImage;
        TextView tvTitle;
        TextView tvOverview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //lookup view objects by id
            ivPosterImage = itemView.findViewById(R.id.ivPosterImage);
            ivBackdropImage = itemView.findViewById(R.id.ivBackdropImage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
        }
    }
}
