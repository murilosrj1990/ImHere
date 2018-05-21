package com.imhere.imhere;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sergio on 08/03/2018.
 */

public class CriaBanco extends SQLiteOpenHelper {
    protected static final String NOME_BANCO = "imhere.db";
    protected static final String TABELA_PESSOA = "pessoa";
    protected static final String ID_PESSOA = "_id";
    protected static final String NOME_PESSOA = "nome";
    protected static final String EMAIL_PESSOA = "email";
    protected static final String DATANASC_PESSOA = "datanasc";
    protected static final String ID_TURMA_FK = "_id_turma_fk";

    protected static final String TABELA_TURMA = "turma";
    protected static final String ID_TURMA = "_id";
    protected static final String NOME_TURMA = "nome";

    protected static final String TABELA_CHAMADA = "chamada";
    protected static final String ID_CHAMADA = "_id";
    protected static final String DATA_CHAMADA = "data_cham";

    protected static final String TABELA_LISTA_CHAMADA = "listachamada";

    protected static final String ID_LISTA_CHAMADA = "_id";
    protected static final String ID_CHAMADA_FK = "_id_chamada_fk";
    protected static final String ID_PESSOA_FK = "_id_pessoa_fk";
    protected static final String PRESENCA = "presenca";




    protected static final int VERSAO = 4;

    public CriaBanco(Context context) {
        super(context, NOME_BANCO,null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql_turma =
            "CREATE TABLE "+ TABELA_TURMA +" ( "
            + ID_TURMA + " integer primary key autoincrement,"
            + NOME_TURMA + " TEXT"
            + ");";

        String sql =
            "CREATE TABLE "+ TABELA_PESSOA +" ( "
            + ID_PESSOA + " integer primary key autoincrement,"
            + NOME_PESSOA + " text, "
            + EMAIL_PESSOA + " text, "
            + DATANASC_PESSOA + " text, "
            + ID_TURMA_FK + " integer, "
            + "FOREIGN KEY (" + ID_TURMA_FK + ") REFERENCES "+ TABELA_TURMA + "( " + ID_TURMA + " )"
            + ");";

        String sql_chamada =
                "CREATE TABLE "+ TABELA_CHAMADA +" ( "
                        + ID_CHAMADA + " integer primary key autoincrement,"
                        + DATA_CHAMADA + " text, "
                        + ID_TURMA_FK + " integer, "
                        + "FOREIGN KEY (" + ID_TURMA_FK + ") REFERENCES "+ TABELA_TURMA + "( " + ID_TURMA + " )"
                        + ");";

        String sql_lista_chamada =
                "CREATE TABLE "+ TABELA_LISTA_CHAMADA +" ( "
                        + ID_LISTA_CHAMADA + " integer primary key autoincrement,"
                        + PRESENCA + " integer, "
                        + ID_TURMA_FK + " integer, "
                        + ID_PESSOA_FK + " integer, "
                        + "FOREIGN KEY (" + ID_CHAMADA_FK + ") REFERENCES "+ TABELA_CHAMADA + "( " + ID_CHAMADA + " )"
                        + "FOREIGN KEY (" + ID_PESSOA_FK + ") REFERENCES "+ TABELA_PESSOA + "( " + ID_PESSOA + " )"
                        + ");";


        db.execSQL(sql_turma);
        db.execSQL(sql);
        db.execSQL(sql_chamada);
        db.execSQL(sql_lista_chamada);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS";
        db.execSQL( sql +" "+ TABELA_PESSOA);
        db.execSQL( sql +" "+ TABELA_TURMA);
        db.execSQL( sql +" "+ TABELA_CHAMADA);
        db.execSQL( sql +" "+ TABELA_LISTA_CHAMADA);
        onCreate(db);

    }
}
