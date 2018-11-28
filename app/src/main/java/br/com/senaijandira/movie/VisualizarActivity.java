package br.com.senaijandira.movie;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.senaijandira.movie.model.Movie;
import br.com.senaijandira.movie.presenter.VisualizarPresenter;
import br.com.senaijandira.movie.services.MovieService;
import br.com.senaijandira.movie.services.ServiceFactory;
import br.com.senaijandira.movie.utils.DateUtil;
import br.com.senaijandira.movie.view.VisualizarView;

public class VisualizarActivity extends AppCompatActivity implements VisualizarView{

    TextView txtTitle, txtDate, txtRating;
    Button btnOverview;
    ImageView poster;
    int idMovie;
    MovieService service;
    VisualizarPresenter presenter;

    //objetos da avaliação
    private ProgressBar progressBar;
    private Handler handler = new Handler();

    //class para formatar data
    SimpleDateFormat df;
    Date date;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);

        //instanciando id
        idMovie = getIntent().getIntExtra("idMovie", 0);

        //instanciando txtviews
        txtTitle = findViewById(R.id.txtTitle);
        txtDate = findViewById(R.id.txtDate);
        txtRating = findViewById(R.id.txt_rating);

        df = new SimpleDateFormat("dd/MM/yyyy");

        btnOverview = findViewById(R.id.btn_overview);


        progressBar = (ProgressBar) findViewById(R.id.circle_progress_bar);

        poster = findViewById(R.id.imgFilm);

        service = ServiceFactory.create();

        presenter = new VisualizarPresenter(this, service);

        presenter.getMovieById(idMovie);



    }

    //encerra a activity
    public void fechar (View v) {
        finish();
    }


    @Override
    public void preencherCampos(final Movie movie) {
        txtTitle.setText(movie.getTitle());

        txtDate.setText(DateUtil.convertToBrFormat(movie.getReleaseDate()));

        //caregando imagem
        Glide.with(this).load(MovieService.IMAGE_URL_BASE +
                movie.getPosterPath()).apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                .into(poster);

        //iniciando thread
        startThread(movie.getRating());

        final View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(btnOverview.getContext());
                builder.setMessage(movie.getOverview());
                //
                builder.create();
                builder.show();

            }
        };

        btnOverview.setOnClickListener(listener);
    }




    //método que utiliza threads para fazer o loading da avaliação acontecer
    public void startThread(final float limit){

        txtRating.setText(limit + "/" + 10);

        //thread para efeito de loading
        new Thread(new Runnable() {
            int i = 0;
            int progressStatus = 0;

            @Override
            public void run() {
                while (progressStatus < limit) {
                    progressStatus += 1;


                    //try e catch
                    try{
                        Thread.sleep(64);


                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    //atualiza a progress bar
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            i++;
                        }
                    });
                }
            }
        }).start();


    }


}
