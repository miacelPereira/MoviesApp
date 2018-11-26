package br.com.senaijandira.movie.presenter;

import br.com.senaijandira.movie.model.MovieResult;
import br.com.senaijandira.movie.services.MovieService;
import br.com.senaijandira.movie.view.MovieView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePresenter {

    private MovieService service;



    //construtor
    public MoviePresenter(MovieService service) {
        this.service = service;
    }


    //método para retornar movies
    public void getMovies(final MovieView view){
         service.getNowPlayingMovies("8dd702446c98959288bf81386e153c8c",
                MovieService.LANG).enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                if(response.isSuccessful()) {

                    MovieResult result = response.body();

                    view.preencher(result.getMovies());

                }
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                    t.getLocalizedMessage();
            }
        });
    }
}
