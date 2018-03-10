package com.imhere.imhere;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sergio on 08/03/2018.
 */

public class CriaBanco extends SQLiteOpenHelper {
    protected static final String NOME_BANCO = "imhere.db";
    protected static final String TABELA = "pessoa";
    protected static final String ID = "_id";
    protected static final String NOME = "nome";
    protected static final String EMAIL = "email";
    protected static final String DATANASC = "datanasc";



    protected static final int VERSAO = 1;

    public CriaBanco(Context context) {
        super(context, NOME_BANCO,null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE "+TABELA+"("
            + ID + " integer primary key autoincrement,"
            + NOME + " text,"
            + EMAIL + " text,"
            + DATANASC + " text"
            + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS";
        db.execSQL( sql + TABELA);
        onCreate(db);
    }
}
