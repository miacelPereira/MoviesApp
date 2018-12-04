package br.com.senaijandira.movie;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

import br.com.senaijandira.movie.adapter.MovieAdapter;
import br.com.senaijandira.movie.model.Movie;
import br.com.senaijandira.movie.presenter.FindPresenter;
import br.com.senaijandira.movie.presenter.GenrePresenter;
import br.com.senaijandira.movie.presenter.MoviePresenter;
import br.com.senaijandira.movie.services.ServiceFactory;
import br.com.senaijandira.movie.view.MovieView;

public class MainActivity extends AppCompatActivity implements MovieView, AdapterView.OnItemClickListener, NavigationView.OnNavigationItemSelectedListener{

    // essa class representa a MainActivity, ou seja, representa o conteúdo que será visualizado pelo usuário
    // por motivo de recursividade, essa MainActivity é a página que mostra os últimos lançamentos e também os filmes por gênero, assim economizando várias activity
    // usamos o método MVP nesse projeto, assim toda a busca na API está em outros arquivos

    // esses objetos são do próprio android e que usamos no arquivo activity_main.xml, para assim conseguir manipular eles
    EditText txtBusca;
    ImageView imgLogo;
    int cont; // váriavel de controle na abertura da barra de pesquisa

    ListView listView;
    MovieAdapter adapter;
    MoviePresenter presenter;
    DrawerLayout drawer;
    ImageView imagemMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lstFilmes);
        adapter = new MovieAdapter(this);


        listView.setAdapter(adapter);

        // setando onclick no listview para visualizar id do filme
        listView.setOnItemClickListener(this);

        txtBusca = findViewById(R.id.txtBusca);
        imgLogo = findViewById(R.id.imgLogo);

        // drawerLayout e navigationView é usado para o menu lateral que temos no app
        imagemMenu = findViewById(R.id.imagemMenu);
        drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);

        imagemMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!drawer.isDrawerOpen(GravityCompat.START)) drawer.openDrawer(Gravity.START);
                else drawer.closeDrawer(Gravity.START);
            }
        });

        cont = 0;

        //configurando o presenter
        presenter = new MoviePresenter(ServiceFactory.create(), this);

        presenter.getMovies();


    }

    @Override
    public void preencher(List<Movie> movies) {
        adapter.clear();
        adapter.addAll(movies);
    }

    public void abrirEdittext(View view) {

        // Estrutura de decisão para mostrar o Edit text
        if(cont == 0){
            imgLogo.setVisibility(view.GONE);
            txtBusca.setVisibility(view.VISIBLE);
            cont++;
        }else{
            FindPresenter find = new FindPresenter(ServiceFactory.create(), this);
            find.getMoviesByName(txtBusca.getText().toString());
            imgLogo.setVisibility(view.VISIBLE);
            txtBusca.setVisibility(view.GONE);
            cont = 0;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //selecionando filme através do clique

        Movie sltMovie = adapter.getItem(position);

         Intent intent = new Intent(this, VisualizarActivity.class);

         intent.putExtra("idMovie", sltMovie.getId());

         startActivity(intent);
    }

    View.OnClickListener item = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            v.getId();
        }
    };

    //Menu
    public void openMovieByGenre(int id){
        GenrePresenter genrePresenter = new GenrePresenter(ServiceFactory.create(), this);
        genrePresenter.getMoviesByGenre(id);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuAcao) {
            openMovieByGenre(28);
        } else if (id == R.id.menuAnimacao) {
            openMovieByGenre(16);
        } else if (id == R.id.menuComedia) {
            openMovieByGenre(35);
        } else if (id == R.id.menuDocumentario) {
            openMovieByGenre(99);
        } else if (id == R.id.menuDrama) {
            openMovieByGenre(18);
        } else if (id == R.id.menuFaroeste) {
            openMovieByGenre(37);
        } else if (id == R.id.menuFiccao) {
            openMovieByGenre(878);
        } else if (id == R.id.menuGuerra) {
            openMovieByGenre(10752);
        } else if (id == R.id.menuRomance) {
            openMovieByGenre(10749);
        } else if (id == R.id.menuTerror) {
            openMovieByGenre(27);
        } else if (id == R.id.menuThriller) {
            openMovieByGenre(53);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
