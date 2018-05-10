package com.imhere.imhere;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AlteraTurmaActivity extends AppCompatActivity {

    //Cria atributos da classe
    EditText nome;
    Button alterar;
    Button cadastraAluno;
    Button deletar;
    Cursor cursor;
    BancoController crud;
    String codigo;

    //Método de criação
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_turma);
        //Pega informação vinda de outra Activity
        codigo = this.getIntent().getStringExtra("codigo");
        //Controi Controller do Banco de Dados
        crud = new BancoController(getBaseContext());

        nome = (EditText)findViewById(R.id.edtNomeAluno);
        alterar = (Button)findViewById(R.id.btnAlterarAluno);
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
        cadastraAluno = (Button)findViewById(R.id.CadastraAluno);
        cadastraAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AlteraTurmaActivity.this,ListaPessoaActivity.class);

                //define variável extra para passar para outra tela
                System.out.println("Escopo 2 "+codigo);

                //Envia informações para outra Activity
                intent.putExtra("codigoTurma", codigo);
                startActivity(intent);
                finish();
            }
        });

    }
}
