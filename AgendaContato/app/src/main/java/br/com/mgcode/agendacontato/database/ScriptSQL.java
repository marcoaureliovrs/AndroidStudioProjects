package br.com.mgcode.agendacontato.database;

/**
 * Created by Marco Gorak on 04/11/15.
 *
 * Classe responável por criar a base de dados que será utilizada pela aplicação e também suas
 * respectivas tabelas.
 *
 * Esse processo é feito através da criação de um método estático do tipo String getCreateContato()
 * onde é criado um objeto do tipo StringBuilder onde é setado em formato de Strings o script SQL
 * que irá criar as tabelas do banco de dados a serem retornados pelo método.
 */


public class ScriptSQL {

    public static String getCreateContato() {

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("");

        sqlBuilder.append("CREATE TABLE IF NOT EXISTS CONTATO (" );
        sqlBuilder.append("_id                INTEGER       NOT NULL ");
        sqlBuilder.append("PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("NOME               VARCHAR (200), ");
        sqlBuilder.append("TELEFONE           VARCHAR (14), ");
        sqlBuilder.append("TIPOTELEFONE       VARCHAR (1), ");
        sqlBuilder.append("EMAIL              VARCHAR (255), ");
        sqlBuilder.append("TIPOEMAIL          VARCHAR (1), ");
        sqlBuilder.append("ENDERECO           VARCHAR (255), ");
        sqlBuilder.append("TIPOENDERECO       VARCHAR (1), ");
        sqlBuilder.append("DATASESPECIAIS     DATE, ");
        sqlBuilder.append("TIPODATASESPECIAIS VARCHAR (1), ");
        sqlBuilder.append("GRUPOS             VARCHAR (255) ");
        sqlBuilder.append(");");

        return sqlBuilder.toString();
    }
}
