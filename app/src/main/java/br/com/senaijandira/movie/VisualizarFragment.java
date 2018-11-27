package br.com.senaijandira.movie;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

public class VisualizarFragment extends DialogFragment {

    //método para criar o dialogo


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_visualizar, container, false);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        /*
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //invocando o layout
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.activity_visualizar, null));

        //builder.show();

        return builder.create();
        */

        Dialog d = super.onCreateDialog(savedInstanceState);


        d.requestWindowFeature(Window.FEATURE_NO_TITLE);

        return d;


    }
}
