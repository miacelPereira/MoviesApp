package br.com.senaijandira.movie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

import br.com.senaijandira.movie.adapter.MovieAdapter;
import br.com.senaijandira.movie.model.Movie;
import br.com.senaijandira.movie.presenter.MoviePresenter;
import br.com.senaijandira.movie.services.ServiceFactory;
import br.com.senaijandira.movie.view.MovieView;

public class MainActivity extends AppCompatActivity implements MovieView {

    EditText txtBusca;
    ImageView imgLogo;
    int cont;

    ListView listView;
    MovieAdapter adapter;
    MoviePresenter presenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lstFilmes);
        adapter = new MovieAdapter(this);


        listView.setAdapter(adapter);

        txtBusca = findViewById(R.id.txtBusca);
        imgLogo = findViewById(R.id.imgLogo);

        cont = 0;

        //configurando o presenter
        presenter = new MoviePresenter(ServiceFactory.create(), this);

        presenter.getMovies();
    }

    public void abrirEdittext(View view) {

        // Estrutura de decisão para mostrar o Edit text
        if(cont == 0){
            imgLogo.setVisibility(view.GONE);
            txtBusca.setVisibility(view.VISIBLE);
            cont++;
        }else{
            imgLogo.setVisibility(view.VISIBLE);
            txtBusca.setVisibility(view.GONE);
            cont = 0;
        }
    }

    @Override
    public void preencher(List<Movie> movies) {
        //adapter.clear();
        adapter.addAll(movies);
    }
}
