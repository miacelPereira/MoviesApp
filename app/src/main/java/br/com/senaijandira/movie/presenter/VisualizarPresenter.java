package br.com.senaijandira.movie.presenter;

import android.util.Log;

import br.com.senaijandira.movie.model.Movie;
import br.com.senaijandira.movie.services.MovieService;
import br.com.senaijandira.movie.view.VisualizarView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisualizarPresenter {

    VisualizarView view;
    MovieService service;

    //construtor
    public VisualizarPresenter(VisualizarView view, MovieService service){
        this.view = view;
        this.service = service;
    }

    //método que vai fazer o request de um filme só
    public void getMovieById(int id){
        Call<Movie> call = service.getMovieById(id, "8dd702446c98959288bf81386e153c8c",
                MovieService.LANG);

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                Movie movie = response.body();

                view.preencherCampos(movie);

            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });
    }
}
