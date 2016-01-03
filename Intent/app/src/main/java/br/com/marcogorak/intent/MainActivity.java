package br.com.marcogorak.intent;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnChamarBrowser;
    private Button btnFazerLigacao;
    private Button btnExemploParametros;

    private final int EXEMPLO_PARAMETROS_ACTIVITY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnChamarBrowser= (Button)findViewById(R.id.btnChamarBrowser);
        btnChamarBrowser.setOnClickListener(this);

        btnFazerLigacao= (Button)findViewById(R.id.btnFazerLigacao);
        btnFazerLigacao.setOnClickListener(this);

        btnExemploParametros= (Button)findViewById(R.id.btnExemploParametros);
        btnExemploParametros.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v == btnChamarBrowser) {
            Intent it = new Intent(this, ChamarBrowserActivity.class);
            startActivity(it);
        } else if (v == btnFazerLigacao) {
            Intent it = new Intent(this, FazerLigacaoActivity.class);
            startActivity(it);
        } else if (v == btnExemploParametros) {
            Intent it = new Intent(this, ParametrosActivity.class);
            startActivityForResult(it, EXEMPLO_PARAMETROS_ACTIVITY);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Bundle param = null;

        if (data != null) {
            param = data.getExtras();
        }


        if (requestCode == EXEMPLO_PARAMETROS_ACTIVITY) {
            switch (resultCode) {
                case RESULT_OK:

                    if (param != null) {
                        String valor = param.getString("VALOR");
                        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                        dlg.setMessage("O valor do parâmetro é: " + valor);
                        dlg.setNeutralButton("Ok", null);
                        dlg.show();

                    }

                    break;
                case RESULT_CANCELED:
                    AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                    dlg.setMessage("Operação cancelada ");
                    dlg.setNeutralButton("Ok", null);
                    dlg.show();
                    break;
            }
        }
    }
}
