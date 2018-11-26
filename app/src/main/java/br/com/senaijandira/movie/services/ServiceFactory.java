package br.com.senaijandira.movie.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {

    //criando um service para a classe Movies
    public static MovieService create(){
        //invocando retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl(MovieService.URL_BASE).
                addConverterFactory(GsonConverterFactory.create()).build();

        return retrofit.create(MovieService.class);

    }
}
