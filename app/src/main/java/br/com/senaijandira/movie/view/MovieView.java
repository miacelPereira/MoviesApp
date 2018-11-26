package br.com.senaijandira.movie.view;

import java.util.List;

import br.com.senaijandira.movie.model.Movie;

public interface MovieView {

    void preencher(List<Movie> movies);
}
