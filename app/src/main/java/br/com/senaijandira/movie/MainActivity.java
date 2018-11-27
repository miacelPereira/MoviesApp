package br.com.senaijandira.movie;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuView;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.util.List;

import br.com.senaijandira.movie.adapter.MovieAdapter;
import br.com.senaijandira.movie.model.Movie;
import br.com.senaijandira.movie.presenter.GenrePresenter;
import br.com.senaijandira.movie.presenter.MoviePresenter;
import br.com.senaijandira.movie.services.ServiceFactory;
import br.com.senaijandira.movie.view.MovieView;

public class MainActivity extends AppCompatActivity implements MovieView, AdapterView.OnItemClickListener, NavigationView.OnNavigationItemSelectedListener{

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

        // Menu
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);

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
