package com.imhere.imhere;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AlteraTurmaActivity extends AppCompatActivity {

    EditText nome;
    Button alterar;
    Button cadastraAluno;
    Button deletar;
    Cursor cursor;
    BancoController crud;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_aluno);

        codigo = this.getIntent().getStringExtra("codigo");

        crud = new BancoController(getBaseContext());

        nome = (EditText)findViewById(R.id.editText4);

        alterar = (Button)findViewById(R.id.button2);

        cursor = crud.carregaDadoTurmaById(Integer.parseInt(codigo));
        nome.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.NOME_TURMA)));

        //Ação do botão alterar
        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.alteraRegistroTurma(Integer.parseInt(codigo), nome.getText().toString());
                Intent intent = new Intent(AlteraTurmaActivity.this,ListaTurmaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //Ação do botão deletar
        deletar = (Button)findViewById(R.id.button3);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.deletaRegistroTurma(Integer.parseInt(codigo));
                Intent intent = new Intent(AlteraTurmaActivity.this,ListaTurmaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //Ação do botão cadastrar aluno
        cadastraAluno = (Button)findViewById(R.id.button6);
        cadastraAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlteraTurmaActivity.this,ListaPessoaActivity.class);
                //define variável extra para passar para outra tela
                intent.putExtra("codigoTurma", codigo);
                startActivity(intent);
                finish();
            }
        });

    }
}
