package br.com.marcogorak.intent;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChamarBrowserActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAbrirBrowser;
    private EditText txtUrl; //edtUrl

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamar_browser);

        btnAbrirBrowser = (Button)findViewById(R.id.btnAbrirBrowser);
        txtUrl          = (EditText)findViewById(R.id.txtUrl); //edtUrl

        btnAbrirBrowser.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        try {
            Uri uri = Uri.parse(txtUrl.getText().toString());
            Intent it = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(it);
        } catch (Exception ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage(getResources().getString(R.string.msg_url_invalida));
            dlg.setNeutralButton(getResources().getString(R.string.ok), null);
            dlg.show();
        }

    }
}
