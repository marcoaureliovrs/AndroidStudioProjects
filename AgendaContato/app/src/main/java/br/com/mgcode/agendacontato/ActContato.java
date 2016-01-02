package br.com.mgcode.agendacontato;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import android.database.sqlite.*;
import android.database.*;

import br.com.mgcode.agendacontato.app.MessageBox;
import br.com.mgcode.agendacontato.database.DataBase;
import br.com.mgcode.agendacontato.dominio.RepositorioContato;
import br.com.mgcode.agendacontato.dominio.entidades.Contato;

public class ActContato extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{


    /** @author MarcoGorak
     *
     * Após ter criado os dois objetos no act_contatos.xml, é necessário cria-los na respectiva
     * classe Java desse activity que no caso é essa ActContatos.java, pois o Android não o cria
     * de forma automática.
     */

    //Criando objetos de referencia da classe ActContato
    private ImageButton btnAdicionar;
    private EditText edtPesquisa;
    private ListView lstContatos;


    private ArrayAdapter<Contato> adpContatos;

    //Criando o Objeto da classe DataBase
    private DataBase dataBase;
    private SQLiteDatabase conn;

    //Criando objeto de refencia da classe RepositorioContato
    private RepositorioContato repositorioContato;

    public static final String PAR_CONTATO = "CONTATO";


    private FiltraDados filtraDados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_contato);
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

        btnAdicionar = (ImageButton)findViewById(R.id.btnAdicionar);
        btnAdicionar.setOnClickListener(this);

        edtPesquisa = (EditText)findViewById(R.id.edtPesquisa);
        lstContatos = (ListView)findViewById(R.id.lstContatos);

        lstContatos.setOnItemClickListener(this);



        //Retorna ao objeto criado a referencia ao metodo construtor da respectiva classe DataBase
        try {
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            repositorioContato = new RepositorioContato(conn);

            adpContatos = repositorioContato.buscaContatos(this);


            lstContatos.setAdapter(adpContatos);

            filtraDados = new FiltraDados(adpContatos);

            edtPesquisa.addTextChangedListener(filtraDados);

            /*
            //Cria uma mensagem informando que a base de dados foi criada com sucesso
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("Conexão criada com sucesso");
            dlg.setNeutralButton("Ok", null);
            dlg.show();
            */

        } catch (SQLException ex) {

            MessageBox.show(this, "Erro", "Erro ao criar o banco: " + ex.getMessage());

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (conn != null) {
            conn.close();
        }
    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(this, ActCadContatos.class);
        startActivityForResult(it, 0);

    }

    //Para fazer a chamada completa de um método sem precisar ficar digitando tecle Control + Espaço
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        adpContatos = repositorioContato.buscaContatos(this);
        filtraDados.setArrayAdapter(adpContatos);
        lstContatos.setAdapter(adpContatos);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        /*
            O objeto contato recebe um item do adpContatos
         */
        Contato contato = adpContatos.getItem(position);

        Intent it = new Intent(this, ActCadContatos.class);
        // Passando parâmetros de um Activity a outro
        it.putExtra(PAR_CONTATO, contato);
        startActivityForResult(it, 0);

    }

    private class FiltraDados implements TextWatcher {
        private ArrayAdapter<Contato> arrayAdapter;

        private FiltraDados (ArrayAdapter<Contato> arrayAdapter) {
            this.arrayAdapter = arrayAdapter;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void setArrayAdapter (ArrayAdapter<Contato> arrayAdapter) {
            this.arrayAdapter = arrayAdapter;
        }

        /**
         * Método responsável por capturar os dados quando quando o usuário esta digitando os dados
         * na caixa de texto edtPesquisa
         * @param s
         * @param start
         * @param before
         * @param count
         */
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            this.arrayAdapter.getFilter().filter(s);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
