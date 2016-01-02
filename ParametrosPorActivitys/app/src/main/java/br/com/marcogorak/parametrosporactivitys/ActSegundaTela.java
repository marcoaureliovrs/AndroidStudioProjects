package br.com.marcogorak.parametrosporactivitys;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.*;
import android.content.*;
import android.widget.Button;
import android.widget.EditText;


public class ActSegundaTela extends AppCompatActivity implements View.OnClickListener{


    /** @author MarcoGorak
     *
     * Após ter criado os dois objetos no act_main.xml, é necessário cria-los na respectiva
     * classe Java desse activity que no caso é essa ActMain.java, pois o Android não o cria
     * de forma automática
     */

    private EditText edtValor;
    private Button btnFechar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_segunda_tela);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /**
         * O método findViewById retorna uma referência para o objeto desejado da interface
         * gráfica para o objeto da classe Java como pode ser visto abaixo:
         */
        edtValor = (EditText)findViewById(R.id.edtValor);
        btnFechar = (Button)findViewById(R.id.btnFechar);

        //Registrando o evento do botção
        btnFechar.setOnClickListener(this);


        /**
         * Bundlue é um pacote que contem algumas classes que irá conter todos os parâmetros
         * passados de um Activity a outro.
         */
        Bundle bundle = getIntent().getExtras();

        if (bundle.containsKey("VALOR")) {
            String valor = bundle.getString("VALOR");

        }

    }

    public void onClick (View v) {
        // O método para fechar o Activity é o finish()
        finish();
    }

}
