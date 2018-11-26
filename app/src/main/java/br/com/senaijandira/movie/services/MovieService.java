package br.com.senaijandira.movie.services;

import br.com.senaijandira.movie.model.MovieResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {


     String URL_BASE = "https://api.themoviedb.org/3/";
     String LANG = "pt-br"; //idioma de como o response vai aparecer

    //endpoint onde virá os filmes que estão passando no cinema
    @GET("movie/now_playing")
    Call<MovieResult> getNowPlayingMovies(
            //parametros necessarios
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}
