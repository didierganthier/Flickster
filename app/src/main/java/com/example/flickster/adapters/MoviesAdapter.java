package com.example.flickster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.flickster.R;
import com.example.flickster.models.Movie;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>
{

    Context context;
    List<Movie>movies;

    public MoviesAdapter(Context context, List<Movie> movies)
    {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i)
    {
        Movie movie = movies.get(i);
        viewHolder.bind(movie);
    }

    @Override
    public int getItemCount()
    {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView tvTitle, tvOverview;
        ImageView ivPoster;
        RatingBar ratingBar;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }

        public void bind(Movie movie)
        {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            double rate = movie.getVoteAverage();
            float rating = (float)rate;
            rating = rating/2;
            ratingBar.setRating(rating);
            String imageUrl = movie.getPosterPath();
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            {
             imageUrl = movie.getBackdropPath();
            }
            if(movie.getVoteAverage() > 5.0){
                imageUrl = movie.getBackdropPath();
            }
            Glide.with(context).load(imageUrl).apply(new RequestOptions().placeholder(R.drawable.popcorn)).into(ivPoster);
        }
    }
}
