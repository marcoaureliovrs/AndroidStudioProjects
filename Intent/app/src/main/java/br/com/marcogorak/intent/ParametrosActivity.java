package br.com.marcogorak.intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ParametrosActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnConfirmar;
    private Button btnCancelar;
    private EditText edtValor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametros);

        btnConfirmar = (Button) findViewById(R.id.btnConfirmar);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        edtValor = (EditText) findViewById(R.id.edtValor);

        btnConfirmar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v == btnConfirmar) {

            Intent it = new Intent();
            it.putExtra("VALOR", edtValor.getText().toString());
            setResult(RESULT_OK, it);
            finish();


        } else if (v == btnCancelar) {
            setResult(RESULT_CANCELED);
            finish();
        }
    }

}
