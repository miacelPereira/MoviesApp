package br.com.senaijandira.movie.presenter;

import br.com.senaijandira.movie.model.MovieResult;
import br.com.senaijandira.movie.services.MovieService;
import br.com.senaijandira.movie.utils.Utils;
import br.com.senaijandira.movie.view.MovieView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindPresenter {

         Utils utils = new Utils();
        private MovieService service;
        private MovieView movieView;

        public FindPresenter(MovieService service, MovieView view) {
            this.movieView = view;
            this.service = service;
        }

        public void getMoviesByName(String name){
            name = utils.convertName(name);
            Call<MovieResult> call =  service.getMoviesByName("8dd702446c98959288bf81386e153c8c",
                    MovieService.LANG, name);

            call.enqueue(new Callback<MovieResult>() {
                @Override
                public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                    if(response.isSuccessful()) {
                        MovieResult result = response.body();
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
