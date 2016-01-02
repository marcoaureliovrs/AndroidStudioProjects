package br.com.mgcode.agendacontato.dominio;
import android.content.ContentValues;
import android.content.Context;
import android.database.*;
import android.database.sqlite.*;
import android.widget.*;

import java.util.Date;

import br.com.mgcode.agendacontato.ContatoArrayAdapter;
import br.com.mgcode.agendacontato.dominio.entidades.Contato;
import br.com.mgcode.agendacontato.R;

/** Classe RepositorioContato
 * Created by Marco Gorak on 04/11/15.
 *
 * Classe responsável pelas operações que serão realizadas no banco de dados
 * inserir
 * Consultar
 * Excluir
 */
public class RepositorioContato {

    private SQLiteDatabase conn;

    private ContentValues preencheContentValues(Contato contato) {

        ContentValues values = new ContentValues();

        values.put(Contato.NOME, contato.getNome() );
        values.put(Contato.TELEFONE, contato.getTelefone() );
        values.put(Contato.TIPOTELEFONE, contato.getTipoTelefone() );
        values.put(Contato.EMAIL, contato.getEmail() );
        values.put(Contato.TIPOEMAIL, contato.getTipoEmail() );
        values.put(Contato.ENDERECO, contato.getEndereco() );
        values.put(Contato.TIPOENDERECO, contato.getTipoEndereco() );
        values.put(contato.DATASESPECIAIS, contato.getDatasEspeciais().getTime() );
        values.put(Contato.TIPODATASESPECIAIS, contato.getTipoDatasEspeciais() );
        values.put(Contato.GRUPOS, contato.getGrupos() );

        return values;
    }

    public void inserir(Contato contato) {

        ContentValues values = preencheContentValues(contato);

        conn.insertOrThrow(Contato.TABELA, null, values);

    }

    public void alterar (Contato contato) {

        ContentValues values = preencheContentValues(contato);

        conn.update(Contato.TABELA, values, "_id = ? ", new String[]{String.valueOf(contato.getId())});

    }

    public void excluir(long id) {
    conn.delete(Contato.TABELA, "_id = ? ", new String[] { String.valueOf(id)});
    }



    //Método construtor que recebe a conexão com o BD como parâmetro
    public RepositorioContato(SQLiteDatabase conn) {

        this.conn = conn;

    }

    // Método responsável pela consulta dos dados
    public ContatoArrayAdapter buscaContatos(Context context){

        ContatoArrayAdapter adpContatos = new ContatoArrayAdapter(context, R.layout.item_contato);
        //ArrayAdapter<Contato> adpContatos = new ArrayAdapter<Contato>(context, android.R.layout.simple_list_item_1);

        //Faz a consulta na tabela CONTATO e armazena no objeto cursor da classe Cursor
        Cursor cursor = conn.query(Contato.TABELA, null, null, null, null, null, null);

        //Verificando se a consulta retornou algum dado
        if (cursor.getCount() > 0 ){

            cursor.moveToFirst();

            //Indexa toda a consulta no campo telefone que é adicionado ao ArrayAdapter(adpContatos)
            do {

                Contato contato = new Contato();
                contato.setId(cursor.getLong(cursor.getColumnIndex(contato.ID)));
                contato.setNome(cursor.getString(cursor.getColumnIndex(contato.NOME)));
                contato.setTelefone(cursor.getString(cursor.getColumnIndex(contato.TELEFONE)));
                contato.setTipoTelefone(cursor.getString(cursor.getColumnIndex(contato.TIPOTELEFONE)));
                contato.setEmail(cursor.getString(cursor.getColumnIndex(contato.EMAIL)));
                contato.setTipoEmail(cursor.getString(cursor.getColumnIndex(contato.TIPOEMAIL)));
                contato.setEndereco(cursor.getString(cursor.getColumnIndex(contato.ENDERECO)));
                contato.setTipoEndereco(cursor.getString(cursor.getColumnIndex(contato.TIPOENDERECO)));
                contato.setDatasEspeciais(new Date(cursor.getLong(cursor.getColumnIndex(contato.DATASESPECIAIS))) );
                contato.setTipoDatasEspeciais(cursor.getString(cursor.getColumnIndex(contato.TIPODATASESPECIAIS)));
                contato.setGrupos(cursor.getString(cursor.getColumnIndex(contato.GRUPOS)));



                adpContatos.add(contato);

            }while(cursor.moveToNext());

        }

        return adpContatos;
    }

}
