package br.com.senaijandira.movie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.senaijandira.movie.adapter.MovieAdapter;
import br.com.senaijandira.movie.model.Movie;
import br.com.senaijandira.movie.presenter.MoviePresenter;
import br.com.senaijandira.movie.services.ServiceFactory;
import br.com.senaijandira.movie.view.MovieView;

public class MainActivity extends AppCompatActivity implements MovieView,
        AdapterView.OnItemClickListener {

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

        //setando onclick no listview para visualizar id do filme
        listView.setOnItemClickListener(this);

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

     @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //selecionando filme através do clique

        Movie sltMovie = adapter.getItem(position);
/*
         AlertDialog.Builder alert = new AlertDialog.Builder(this);

         alert.setMessage("id do filme: " + sltmovie.getId() +
                 "\nTitle: " + sltmovie.getTitle() +
                 "\nLançamento: " + sltmovie.getReleaseDate());
         alert.show();
         alert.create();
*/
    //DialogFragment fragment = new VisualizarActivity();


/*
    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();

    lp.copyFrom(fragment.getWindow().getAttributes());

    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
    lp.height = WindowManager.LayoutParams.MATCH_PARENT;
*/
    //fragment.show(getSupportFragmentManager(), "teste");


    //fragment.getDialog().getWindow().setAttributes(lp);

         Intent intent = new Intent(this, VisualizarActivity.class);

         intent.putExtra("idMovie", sltMovie.getId());

         startActivity(intent);
    }
}
