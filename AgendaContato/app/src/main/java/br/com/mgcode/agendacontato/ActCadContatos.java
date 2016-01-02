package br.com.mgcode.agendacontato;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.mgcode.agendacontato.app.MessageBox;
import br.com.mgcode.agendacontato.app.ViewHelper;
import br.com.mgcode.agendacontato.database.DataBase;
import br.com.mgcode.agendacontato.dominio.RepositorioContato;
import br.com.mgcode.agendacontato.dominio.entidades.Contato;
import br.com.mgcode.agendacontato.util.DateUtils;


public class ActCadContatos extends AppCompatActivity {

    /** @author MarcoGorak
     *
     * Após ter criado os dois objetos no content_act_cad_contatoscontatos.xml, é necessário cria-los na respectiva
     * classe Java desse activity que no caso é essa ActCadContatos.java, pois o Android não o cria
     * de forma automática.
     */

    private EditText edtNome;
    private EditText edtTelefone;
    private EditText edtEmail;
    private EditText edtEndereco;
    private EditText edtDatasEspeciais;
    private EditText edtGrupos;

    private Spinner spnTipoEmail;
    private Spinner spnTipoTelefone;
    private Spinner spnTipoEndereco;
    private Spinner spnDatasEspeciais;

    private ArrayAdapter<String> adpTipoEmail;
    private ArrayAdapter<String> adpTipoTelefone;
    private ArrayAdapter<String> adpTipoEndereco;
    private ArrayAdapter<String> adpDatasEspeciais;

    //Criando o Objeto da classe DataBase
    private DataBase dataBase;
    private SQLiteDatabase conn;

    //Criando objeto de refencia da classe RepositorioContato
    private RepositorioContato repositorioContato;

    private Contato contato;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cad_contatos);
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

        //Recuperando os objetos da classe ActCadContatos
        edtNome = (EditText)findViewById(R.id.edtNome);
        edtTelefone = (EditText)findViewById(R.id.edtTelefone);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtEndereco = (EditText)findViewById(R.id.edtEndereco);
        edtDatasEspeciais = (EditText)findViewById(R.id.edtDatasEspeciais);
        edtGrupos = (EditText)findViewById(R.id.edtGrupos);

        /**
         * Spinner: Uma lista de opções onde o usuario opta por uma a qual deseja.
         * 
         */

        spnTipoEmail = (Spinner)findViewById(R.id.spnTipoEmail);
        spnTipoTelefone = (Spinner)findViewById(R.id.spnTipoTelefone);
        spnTipoEndereco = (Spinner)findViewById(R.id.spnTipoEndereco);
        spnDatasEspeciais = (Spinner)findViewById(R.id.spnDatasEspeciais);


        adpTipoEmail = ViewHelper.createArrayAdapter(this, spnTipoEmail);
        adpTipoTelefone = ViewHelper.createArrayAdapter(this, spnTipoTelefone);
        adpTipoEndereco = ViewHelper.createArrayAdapter(this, spnTipoEndereco);
        adpDatasEspeciais = ViewHelper.createArrayAdapter(this, spnDatasEspeciais);


        /*adpTipoEmail = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpTipoEmail.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adpTipoTelefone = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpTipoTelefone.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adpTipoEndereco = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpTipoEndereco.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        adpDatasEspeciais = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpDatasEspeciais.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Ligar os objetos Spinners aos objetos do tipo ArrayAdapter
        spnTipoEmail.setAdapter(adpTipoEmail);
        spnTipoTelefone.setAdapter(adpTipoTelefone);
        spnTipoEndereco.setAdapter(adpTipoEndereco);
        spnDatasEspeciais.setAdapter(adpDatasEspeciais);
        */

        //Adicionando itens nas Spinners através dos objetos do tipo ArrayAdapter
        //Spinner de E-mail:
        adpTipoEmail.add("Casa");
        adpTipoEmail.add("Trabalho");
        adpTipoEmail.add("Outros");



        adpTipoTelefone.add("Casa");
        adpTipoTelefone.add("Trabalho");
        adpTipoTelefone.add("Celular");
        adpTipoTelefone.add("Fax Trabalho");
        adpTipoTelefone.add("Fax Casa");
        adpTipoTelefone.add("Principal");
        adpTipoTelefone.add("Outros");


        adpTipoEndereco.add("Casa");
        adpTipoEndereco.add("Trabalho");
        adpTipoEndereco.add("Outros");


        adpDatasEspeciais.add("Aniversário");
        adpDatasEspeciais.add("Data Comemorativa");
        adpDatasEspeciais.add("Outros");

        ExibeDataListener listener = new ExibeDataListener();

        edtDatasEspeciais.setOnClickListener(listener);
        edtDatasEspeciais.setOnFocusChangeListener(listener);
        edtDatasEspeciais.setKeyListener(null);


        //Preparando a classe para receber parâmetros da ActContato caso queira editar um registro.
        Bundle bundle = getIntent().getExtras();

        if ((bundle != null) && (bundle.containsKey(ActContato.PAR_CONTATO))) {
            contato = (Contato)bundle.getSerializable(ActContato.PAR_CONTATO);
            preencheDados();

        } else {
            contato = new Contato();
        }


        //Retorna ao objeto criado a referencia ao metodo construtor da respectiva classe DataBase
        try {
            //Objetos que criam a conexão com o Banco
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            repositorioContato = new RepositorioContato(conn);

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

    /**
     *
     *
     * Método Responsavel pela criação do Menu no Activity
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Classe responsável por ligar o Activity com o xml de seu respectivo menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.act_cad_contatos, menu);



        if (contato.getId() != 0 ){
            menu.getItem(1).setVisible(true);
        }


        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Método responsável pela chamada do indice do Menu no Activity
     *
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_acao1:

                salvar();
                finish();


                break;
            case R.id.action_acao2:

                excluir();
                finish();

                break;

        }


        return super.onOptionsItemSelected(item);
    }


    private void preencheDados() {
        edtNome.setText(contato.getNome());
        edtTelefone.setText(contato.getTelefone());
        spnTipoTelefone.setSelection(Integer.parseInt(contato.getTipoTelefone()));
        edtEmail.setText(contato.getEmail());
        spnTipoEmail.setSelection(Integer.parseInt(contato.getTipoEmail()));
        edtEndereco.setText(contato.getEndereco());
        spnTipoEndereco.setSelection(Integer.parseInt(contato.getTipoEndereco()));

        //Implementando um formato de data ao método getDatasEspeciais e armazenando em dt
        DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String dt = format.format(contato.getDatasEspeciais());
        //Passando a data formatada e adicionada em dt ao edtDatasEspeciais
        edtDatasEspeciais.setText(dt);
        spnTipoEndereco.setSelection(Integer.parseInt(contato.getTipoDatasEspeciais()));
        edtGrupos.setText(contato.getGrupos());

    }


    private void excluir() {
        try {

            repositorioContato.excluir(contato.getId());

        } catch (Exception ex) {
            MessageBox.show(this, "Erro", "Erro ao excluir os dados: " + ex.getMessage());
        }
    }

    /**
     * Método responsável por recuperar os dados do Activity content_act_cad_contatoscontatos.xml e armazena-los
     * no objeto contato que por sua vez irá fazer a chamada de um método para armazenar esses
     * dados no banco de dados
     */
    private void salvar() {

        /**
         * Por estarmos inserindo dados em um banco de dados é prudente utilizar o bloco Try Catch
         * para tratamento de possíveis erros no momento da insersão.
         */

        try {


        contato.setNome(edtNome.getText().toString());
        contato.setTelefone(edtTelefone.getText().toString());
        contato.setTipoTelefone(String.valueOf(spnTipoTelefone.getSelectedItemPosition()));
        contato.setEndereco(edtEndereco.getText().toString());
        contato.setTipoEndereco(String.valueOf(spnTipoEndereco.getSelectedItemPosition()));
        contato.setEmail(edtEmail.getText().toString());
        contato.setTipoEmail(String.valueOf(spnTipoEmail.getSelectedItemPosition()));

        Date date = new Date();
        contato.setDatasEspeciais(date);

        contato.setTipoDatasEspeciais(String.valueOf(spnDatasEspeciais.getSelectedItemPosition()));
        contato.setGrupos(edtGrupos.getText().toString());

        if (contato.getId() == 0) {
            repositorioContato.inserir(contato);
        } else {
            repositorioContato.alterar(contato);
        }


        } catch (Exception ex) {
            MessageBox.show(this, "Erro", "Erro ao salvar os dados: " + ex.getMessage());
        }
    }


    /**
     * O método a seguir e as 2 sub-classes são necessários para a seleção correta de uma data
     * em um campo do aplicatio Android. Esse processo requer diversas conversões e ações na
     * classe responável.
     *
     * Método Exibe Data(): Método do tipo void onde armazena a data atual em uma caixa de dialogo
     * especifica para apresentação de datas, criada atravês da classe DatePickerDialog.
     *
     */
    private void exibeData() {

        Calendar calendar = Calendar.getInstance();

        int ano = calendar.get(calendar.YEAR);
        int mes = calendar.get(calendar.MONTH);
        int dia = calendar.get(calendar.DAY_OF_MONTH);

        /*
        Instanciação do objeto dlg a partir da classe DatePickerDialog passando para o método
        construtor o Activity content_act_cad_contatos.xmlatos.xml, a instanciação direta de objeto da classe
        SelecionaDataListener, as variáveis ano, mes e dia que recebem seus dados diretamente do
        objeto Calendar.
         */
        DatePickerDialog dlg = new DatePickerDialog(this, new SelecionaDataListener() , ano, mes, dia);
        dlg.show(); //Apresentando o DatePickerDialog na tela do usuário
    }


    private class ExibeDataListener  implements View.OnClickListener, View.OnFocusChangeListener {

        @Override
        public void onClick(View v) {
            exibeData();
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                exibeData();
            }
        }
    }


    private class SelecionaDataListener implements DatePickerDialog.OnDateSetListener {




        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            String dt = DateUtils.dateToString(year, monthOfYear, dayOfMonth);
            Date data = DateUtils.getDate(year, monthOfYear, dayOfMonth);

            edtDatasEspeciais.setText(dt);

            contato.setDatasEspeciais(data);



        }
    }


}
