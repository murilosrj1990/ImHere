package com.imhere.imhere;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
    //Método para inserir dados da pessoa
    public String insereDadoPessoa(String nome, String email, String datanasc, int codigoTurma){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.NOME_PESSOA, nome);
        valores.put(CriaBanco.EMAIL_PESSOA, email);
        valores.put(CriaBanco.DATANASC_PESSOA, datanasc);
        valores.put(CriaBanco.ID_TURMA_FK, codigoTurma);

        resultado = db.insert(CriaBanco.TABELA_PESSOA, null, valores);
        db.close();

        if (resultado ==-1) {
            return "Erro ao inserir registro";
        }else{
            return "Registro Inserido com sucesso" ;
        }

    }
    //Método para ler os dados da pessoa
    public Cursor carregaDados(int idTurma){
        Cursor cursor;
        String[] campos =  {banco.ID_PESSOA,banco.NOME_PESSOA};
        db = banco.getReadableDatabase();
        String where = CriaBanco.ID_TURMA_FK + "=" + idTurma;
        cursor = db.query(banco.TABELA_PESSOA, campos, where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    //Método para ler os dados da pessoa de um id especifico
    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {banco.ID_PESSOA,banco.NOME_PESSOA,banco.EMAIL_PESSOA,banco.DATANASC_PESSOA};
        String where = CriaBanco.ID_PESSOA + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(CriaBanco.TABELA_PESSOA,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }
    //Método para alterar os dados da pessoa
    public void alteraRegistro(int id, String nome, String email, String datanasc){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = CriaBanco.ID_PESSOA + "=" + id;

        valores = new ContentValues();
        valores.put(CriaBanco.NOME_PESSOA, nome);
        valores.put(CriaBanco.EMAIL_PESSOA, email);
        valores.put(CriaBanco.DATANASC_PESSOA, datanasc);

        db.update(CriaBanco.TABELA_PESSOA,valores,where,null);
        db.close();
    }
    //Método para deletar os dados da pessoa
    public void deletaRegistro(int id){
        String where = CriaBanco.ID_PESSOA + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(CriaBanco.TABELA_PESSOA,where,null);
        db.close();
    }

    public String insereDadoTurma(String nome){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.NOME_TURMA, nome);

        resultado = db.insert(CriaBanco.TABELA_TURMA, null, valores);
        db.close();

        if (resultado ==-1) {
            return "Erro ao inserir registro";
        }else{
            return "Registro Inserido com sucesso" ;
        }

    }

    public Cursor carregaDadosTurma(){
        Cursor cursor;
        String[] campos =  {banco.ID_TURMA,banco.NOME_TURMA};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELA_TURMA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public void alteraRegistroTurma(int id, String nome){
        ContentValues valores;
        String where;

        db = banco.getWritableDatabase();

        where = CriaBanco.ID_TURMA + "=" + id;

        valores = new ContentValues();
        valores.put(CriaBanco.NOME_TURMA, nome);

        db.update(CriaBanco.TABELA_TURMA,valores,where,null);
        db.close();
    }

    public void deletaRegistroTurma(int id){
        String where = CriaBanco.ID_TURMA + "=" + id;
        db = banco.getReadableDatabase();
        db.delete(CriaBanco.TABELA_TURMA,where,null);
        db.close();
    }

    public Cursor carregaDadoTurmaById(int id){
        Cursor cursor;
        String[] campos =  {banco.ID_TURMA,banco.NOME_TURMA};
        String where = CriaBanco.ID_TURMA + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(CriaBanco.TABELA_TURMA,campos,where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    //Método para inserir dados da Chamada
    public String insereDadoChamada(String dataChamada, int codigoTurma){
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.DATA_CHAMADA, dataChamada);
        valores.put(CriaBanco.ID_TURMA_FK, codigoTurma);

        resultado = db.insert(CriaBanco.TABELA_PESSOA, null, valores);
        db.close();

        if (resultado ==-1) {
            return "Erro ao inserir registro";
        }else{
            return "Registro Inserido com sucesso" ;
        }

    }

    //Método para ler os dados da chamada
    public Cursor carregaDadosChamada(int idTurma){
        Cursor cursor;
        String[] campos =  {banco.ID_CHAMADA,banco.DATA_CHAMADA};
        db = banco.getReadableDatabase();
        String where = CriaBanco.ID_CHAMADA_FK + "=" + idTurma;
        cursor = db.query(banco.TABELA_CHAMADA, campos, where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadosListaChamada(int idTurma){
        Cursor cursor;
        String[] campos =  {banco.ID_CHAMADA,banco.DATA_CHAMADA};
        db = banco.getReadableDatabase();
        String where = CriaBanco.ID_TURMA_FK + "=" + idTurma;
        cursor = db.query(banco.TABELA_PESSOA, campos, where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

}
