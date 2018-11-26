package br.com.senaijandira.movie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import br.com.senaijandira.movie.R;
import br.com.senaijandira.movie.model.Movie;

public class MovieAdapter extends ArrayAdapter<Movie> {

    //construtor
    public MovieAdapter(Context ctx) {
        super(ctx, 0, new ArrayList<Movie>());
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        System.out.println("teste adapter");
        View v = convertView;

        if(v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.cardview_livro, parent, false);

        }

        Movie movie = getItem(position);

       // TextView txtTitle = v.findViewById(R.id.txtTitulo);

       // TextView txtData = v.findViewById(R.id.txtData);
        Log.d("TESTE", "fUNCIONOU");
       // txtTitle.setText(movie.getTitle());
       // txtData.setText(movie.getReleaseDate());


        return v;
    }
}
