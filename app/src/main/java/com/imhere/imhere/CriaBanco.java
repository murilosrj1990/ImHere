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
    protected static final String ID_TURMA = "_id_turma";
    protected static final String NOME_TURMA = "nome";



    protected static final int VERSAO = 3;

    public CriaBanco(Context context) {
        super(context, NOME_BANCO,null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql_turma =
            "CREATE TABLE "+ TABELA_TURMA +"( "
            + ID_TURMA + " integer primary key autoincrement,"
            + NOME_TURMA + " TEXT"
            + ");";

        String sql =
            "CREATE TABLE "+ TABELA_PESSOA +"("
            + ID_PESSOA + " integer primary key autoincrement,"
            + NOME_PESSOA + " text, "
            + EMAIL_PESSOA + " text, "
            + DATANASC_PESSOA + " text, "
            + ID_TURMA_FK + " integer, "
            + "FOREIGN KEY " + ID_TURMA_FK + " REFERENCES "+ TABELA_TURMA + "(" + ID_TURMA + ")"
            + ");";


        db.execSQL(sql_turma);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS";
        db.execSQL( sql +" "+ TABELA_PESSOA);
        onCreate(db);

    }
}
