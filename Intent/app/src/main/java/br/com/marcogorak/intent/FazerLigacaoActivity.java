package br.com.marcogorak.intent;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FazerLigacaoActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtNumero;
    private Button btnFazerLigacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fazer_ligacao);

        edtNumero = (EditText) findViewById(R.id.edtNumero);
        btnFazerLigacao = (Button) findViewById(R.id.btnFazerLigacao);

        btnFazerLigacao.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        try {
            Uri uri = Uri.parse("tel:" + edtNumero.getText().toString());
            Intent it = new Intent(Intent.ACTION_CALL, uri);
            startActivity(it); // Erro apresentado devido a falha de permissão

        } catch (Exception ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage(getResources().getString(R.string.msg_url_invalida));
            dlg.setNeutralButton(getResources().getString(R.string.ok), null);
            dlg.show();
        }
    }
}
