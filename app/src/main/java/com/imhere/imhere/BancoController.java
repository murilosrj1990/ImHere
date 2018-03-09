package com.imhere.imhere;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Sergio on 08/03/2018.
 */

public class BancoController {

    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context){
        banco = new CriaBanco(context);
    }

    public String insereDadoPessoa(String nome, String email, String datanasc){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.NOME, nome);
        valores.put(CriaBanco.EMAIL, email);
        valores.put(CriaBanco.DATANASC, datanasc);

        resultado = db.insert(CriaBanco.TABELA, null, valores);
        db.close();

        if (resultado ==-1) {
            return "Erro ao inserir registro";
        }else{
            return "Registro Inserido com sucesso" ;        }

    }
}