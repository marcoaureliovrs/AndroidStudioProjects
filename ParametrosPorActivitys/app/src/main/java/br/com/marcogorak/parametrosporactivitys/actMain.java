package br.com.marcogorak.parametrosporactivitys;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.view.*;
import android.content.*;


public class actMain extends AppCompatActivity implements View.OnClickListener{

    /** @author MarcoGorak
     *
     * Após ter criado os dois objetos no act_main.xml, é necessário cria-los na respectiva
     * classe Java desse activity que no caso é essa ActMain.java, pois o Android não o cria
     * de forma automática
     */

    private EditText edtValor;
    private Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
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
        btnOk = (Button)findViewById(R.id.btnOk);

        //Registrando o evento do botão
        btnOk.setOnClickListener(this);

    }

    public void onClick (View v) {

        /**
         * Criando o Objeto it do tipo Intent que recebe no método construtor o Activity atual
         * e a Activity futuro ou seja, o Activity que o usuário irá estar quando chamar um
         * novo Activity.
         */
        Intent it = new Intent(this, ActSegundaTela.class);
        //Passando parâmetros de um Activity para outro através do objeto it.
        it.putExtra("VALOR", edtValor.getText().toString());
        // Fazendo o start da chamada.
        startActivity(it);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
