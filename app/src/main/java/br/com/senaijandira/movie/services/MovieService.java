package br.com.senaijandira.movie.services;

import br.com.senaijandira.movie.model.Movie;
import br.com.senaijandira.movie.model.MovieResult;
    import retrofit2.Call;
    import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {


    String URL_BASE = "https://api.themoviedb.org/3/";
    //String URL_BASE = "http://10.0.2.2:5001/";

    //url das imagens
    String IMAGE_URL_BASE = "http://image.tmdb.org/t/p/w500";

    String LANG = "pt-br"; //idioma de como o response vai aparecer

    //endpoint onde virá os filmes que estão passando no cinema
    @GET("movie/now_playing")
    Call<MovieResult> getNowPlayingMovies(
            //parametros necessarios
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    //endpoint onde virá um filme em individual
    @GET("movie/{id}")
    Call<Movie> getMovieById(
            @Path("id") int id,
            //parametros necessarios
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    //endpoint onde virá filmes pelo gênero
    @GET("discover/movie/")
    Call<MovieResult> getMoviesByGenre(
            //parametros necessarios
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("with_genres") int id
    );

    //endpoint onde virá filmes pelo gênero
    @GET("search/movie/")
    Call<MovieResult> getMoviesByName(

            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("query") String name
    );
}
