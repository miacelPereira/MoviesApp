package br.com.senaijandira.movie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    EditText txtBusca;
    ImageView imgLogo;
    int cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtBusca = findViewById(R.id.txtBusca);
        imgLogo = findViewById(R.id.imgLogo);
        cont = 0;
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
}
