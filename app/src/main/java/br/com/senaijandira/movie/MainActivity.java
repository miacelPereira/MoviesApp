package br.com.senaijandira.movie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    EditText txtBusca;
    ImageView imgLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtBusca = findViewById(R.id.txtBusca);
        imgLogo = findViewById(R.id.imgLogo);
    }

    public void abrirEdittext(View view) {
        int cont = 0;
        /* Esquema para o logo sumir */
        if(cont == 0){
            txtBusca.setMinimumWidth(250);
            imgLogo.setMaxWidth(0);


        }else{

            cont = 0;
        }
    }
}
