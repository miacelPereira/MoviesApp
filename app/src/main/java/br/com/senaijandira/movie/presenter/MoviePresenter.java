package br.com.senaijandira.movie.presenter;

import br.com.senaijandira.movie.model.MovieResult;
import br.com.senaijandira.movie.services.MovieService;
import br.com.senaijandira.movie.view.MovieView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePresenter {

    private MovieService service;
    private MovieView  movieView;



    //construtor
    public MoviePresenter(MovieService service, MovieView view) {
        this.movieView = view;
        this.service = service;
    }


    //método para retornar movies
    public void getMovies(){
         service.getNowPlayingMovies("8dd702446c98959288bf81386e153c8c",
                MovieService.LANG).enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                if(response.isSuccessful()) {
                    //Log.e("TESTE", "funciona");
                    MovieResult result = response.body();
                    //System.out.println("oeeeeeeeee");
                    movieView.preencher(result.getMovies());

                }
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                    t.getLocalizedMessage();
            }
        });
    }
}
