package com.imhere.imhere;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AlteraAlunoActivity extends AppCompatActivity {


    //Declaração de variáveis
    EditText nome;
    EditText email;
    EditText datanasc;
    Button alterar;
    Button deletar;
    Cursor cursor;
    BancoController crud;
    String codigo;
    String codigoTurma;

    //Método de criação da Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_aluno);
        //Carrega StringExtra vindo de outra Activity
        codigo = this.getIntent().getStringExtra("codigo");
        codigoTurma=this.getIntent().getStringExtra("codigoTurma");


        //Constroi Controller do Banco de Dados
        crud = new BancoController(getBaseContext());

        //Carrega Objetos java com os componentes do Layout
        nome = (EditText)findViewById(R.id.edtNomeAluno);
        email = (EditText)findViewById(R.id.edtEmailAluno);
        datanasc = (EditText)findViewById(R.id.edtDataNascAluno);
        alterar = (Button)findViewById(R.id.btnAlterarAluno);

        //Carrega dados do Aluno pelo ID
        cursor = crud.carregaDadoById(Integer.parseInt(codigo));
        nome.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.NOME_PESSOA)));
        email.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.EMAIL_PESSOA)));
        datanasc.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.DATANASC_PESSOA)));

        //Ação do Botão alterar
        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.alteraRegistro(Integer.parseInt(codigo),
                        nome.getText().toString(),
                        email.getText().toString(),
                        datanasc.getText().toString());
                Intent intent = new Intent(AlteraAlunoActivity.this,ListaPessoaActivity.class);
                //Envia informação para outra tela
                intent.putExtra("codigoTurma",codigoTurma);
                startActivity(intent);
                finish();
            }
        });

        //Ação do Botão Deletar
        deletar = (Button)findViewById(R.id.btnDeletarAluno);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.deletaRegistro(Integer.parseInt(codigo));
                Intent intent = new Intent(AlteraAlunoActivity.this,ListaPessoaActivity.class);
                //Envia informação para outra tela
                intent.putExtra("codigoTurma",codigoTurma);
                startActivity(intent);
            finish();
            }
            });

    }

}
