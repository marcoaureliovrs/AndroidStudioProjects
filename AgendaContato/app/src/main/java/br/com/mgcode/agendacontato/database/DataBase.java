package br.com.mgcode.agendacontato.database;

import android.content.Context;
import android.database.sqlite.*;
/**
 * Created by Marco Gorak on 04/11/15.
 */

//

/**
 *
 * A Classe SQLiteOpenHelper é uma classe abstrata por isso não pode ser implementada
 * diretamente na classe
 *
 */
public class DataBase extends SQLiteOpenHelper {

    //Método construtor
    public DataBase (Context context) {
        super(context, "agenda", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Chamada do método que contém o script de criação das tabelas
        db.execSQL(ScriptSQL.getCreateContato());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
