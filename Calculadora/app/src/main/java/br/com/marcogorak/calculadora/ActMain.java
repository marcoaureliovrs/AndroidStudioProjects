package br.com.marcogorak.calculadora;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;


public class ActMain extends AppCompatActivity implements View.OnClickListener{

    private EditText edtValor1;
    private EditText edtValor2;
    private Button btnCalcular;


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

        //Recuperando objetos da interface gráfica
        edtValor1 = (EditText)findViewById(R.id.edtValor1);
        edtValor2 = (EditText)findViewById(R.id.edtValor2);
        btnCalcular = (Button)findViewById(R.id.btnCalcular);

        btnCalcular.setOnClickListener(this);


    }


    //Implementando o ouvinte de eventos
    public void onClick (View v) {
        //Recuperando dados digitados nos campos e passando para variaveis
        double valor1 = Double.parseDouble(edtValor1.getText().toString());
        double valor2 = Double.parseDouble(edtValor2.getText().toString());
        //Efetuando a soma dos dois valores
        double resultado = valor1 + valor2;
       //Apresentando resultado na tela através de um AlertDialog
        AlertDialog.Builder dlg = new AlertDialog.Builder(ActMain.this);
        dlg.setMessage("O resultado da soma é" + resultado);
        dlg.setNeutralButton("Ok", null);
        dlg.show();
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
