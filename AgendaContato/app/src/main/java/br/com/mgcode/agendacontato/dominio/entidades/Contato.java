package br.com.mgcode.agendacontato.dominio.entidades;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Marco Gorak on 12/11/15.
 *
 * Classe destinada para transportar os dados da ACTCadContatos para a o Banco de Dados
 *
 * Mapear todos os campos ta tabela contato e criar atributos da classe contato para inserir esses
 * no banco de dados.
 *
 * Mapeamento objeto relacional - Sempre quando tenho uma tabela no banco de dados e uma aplicação
 * orientada a objeto podemos criar classes com os mesmos campos da tabela para fazer a organização
 * da inserção desses dados a respectiva tabela conforme o exemplo dessa classe contato que irá
 * inserir informações na tabela contato do banco de dados do aplicativo.
 *
 */

public class Contato implements Serializable{


    //Implementando constantes para uso de outras classes
    public static String TABELA = "CONTATO";
    public static String ID = "_id";
    public static String NOME = "NOME";
    public static String TELEFONE = "TELEFONE";
    public static String TIPOTELEFONE = "TIPOTELEFONE";
    public static String EMAIL = "EMAIL";
    public static String TIPOEMAIL = "TIPOEMAIL";
    public static String ENDERECO = "ENDERECO";
    public static String TIPOENDERECO = "TIPOENDERECO";
    public static String DATASESPECIAIS = "DATASESPECIAIS";
    public static String TIPODATASESPECIAIS = "TIPODATASESPECIAIS";
    public static String GRUPOS = "GRUPOS";


    private long id;
    private String nome;
    private String telefone;
    private String tipoTelefone;
    private String email;
    private String tipoEmail;
    private String endereco;
    private String tipoEndereco;
    private Date datasEspeciais;
    private String tipoDatasEspeciais;
    private String grupos;

    public Contato() {
        id = 0;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoEmail() {
        return tipoEmail;
    }

    public void setTipoEmail(String tipoEmail) {
        this.tipoEmail = tipoEmail;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(String tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public Date getDatasEspeciais() {
        return datasEspeciais;
    }

    public void setDatasEspeciais(Date datasEspeciais) {
        this.datasEspeciais = datasEspeciais;
    }

    public String getTipoDatasEspeciais() {
        return tipoDatasEspeciais;
    }

    public void setTipoDatasEspeciais(String tipoDatasEspeciais) {
        this.tipoDatasEspeciais = tipoDatasEspeciais;
    }

    public String getGrupos() {
        return grupos;
    }

    public void setGrupos(String grupos) {
        this.grupos = grupos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(String tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    /**
     * O método toString é um método já pertencente a classe Java, como nesse caso necessitamos de
     * uma adaptação desta para apresentação do nome e do telefone do contato foi necessário
     * reescreve-la nessa classe.
     *
     * @override = Sobreescrever um método da classe pai da respectiva classe.
     *
     */
    @Override
    public String toString () {
        return nome + " " + telefone;
    }
}
