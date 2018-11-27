package br.com.senaijandira.movie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import br.com.senaijandira.movie.model.Movie;
import br.com.senaijandira.movie.presenter.VisualizarPresenter;
import br.com.senaijandira.movie.services.MovieService;
import br.com.senaijandira.movie.services.ServiceFactory;
import br.com.senaijandira.movie.view.VisualizarView;

public class VisualizarActivity extends AppCompatActivity implements VisualizarView{

    TextView txtTitle, txtDate, txtRating;
    ImageView poster;
    int idMovie;
    MovieService service;
    VisualizarPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);

        //instanciando id
        idMovie = getIntent().getIntExtra("idMovie", 0);

        //instanciando txtviews
        txtTitle = findViewById(R.id.txtTitle);
        txtDate = findViewById(R.id.txtDate);
        txtRating = findViewById(R.id.txtRating);

        poster = findViewById(R.id.imgFilm);

        service = ServiceFactory.create();

        presenter = new VisualizarPresenter(this, service);

        presenter.getMovieById(idMovie);



    }

    //encerra a activity
    public void fechar (View v) {
        finish();
    }

    @Override
    public void preencherCampos(Movie movie) {
        txtTitle.setText(movie.getTitle());
        txtRating.setText(String.valueOf(movie.getRating()));
        txtDate.setText(movie.getReleaseDate());

        //caregando imagem
        Glide.with(this).load(MovieService.IMAGE_URL_BASE +
                movie.getPosterPath()).apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                .into(poster);
    }
}
