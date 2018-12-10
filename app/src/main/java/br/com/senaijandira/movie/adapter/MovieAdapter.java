package br.com.senaijandira.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import br.com.senaijandira.movie.R;
import br.com.senaijandira.movie.model.Movie;
import br.com.senaijandira.movie.services.MovieService;

public class MovieAdapter extends ArrayAdapter<Movie> {

    //construtor
    public MovieAdapter(Context ctx) {
        super(ctx, 0, new ArrayList<Movie>());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if(v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.cardview_livro, parent, false);

        }

        Movie movie = getItem(position);

        TextView txtTitleMovie = v.findViewById(R.id.txtTitleMovie);

        TextView txtTitleMovieOrigin = v.findViewById(R.id.txtTitleMovieOrigin);

        TextView popularityNumb = v.findViewById(R.id.popularityNumb);

        ImageView imageView = v.findViewById(R.id.imgMovie);

        //usando a lib glide para loading de imagens
        Glide.with(v).load(MovieService.IMAGE_URL_BASE + movie.getPosterPath())
                .apply(RequestOptions.placeholderOf(R.drawable.claquete)).into(imageView);

        txtTitleMovie.setText(movie.getTitle());
        txtTitleMovieOrigin.setText(movie.getOriginalTitle());
        popularityNumb.setText(movie.getPopularity()+"");
        return v;
    }
}
