package com.imhere.imhere;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AlteraAlunoActivity extends AppCompatActivity {

    EditText nome;
    EditText email;
    EditText datanasc;
    Button alterar;
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
        email = (EditText)findViewById(R.id.editText5);
        datanasc = (EditText)findViewById(R.id.editText6);

        alterar = (Button)findViewById(R.id.button2);

        cursor = crud.carregaDadoById(Integer.parseInt(codigo));
        nome.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.NOME_PESSOA)));
        email.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.EMAIL_PESSOA)));
        datanasc.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.DATANASC_PESSOA)));

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.alteraRegistro(Integer.parseInt(codigo),
                        nome.getText().toString(),email.getText().toString(),
                        datanasc.getText().toString());
                Intent intent = new Intent(AlteraAlunoActivity.this,ListaPessoaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        deletar = (Button)findViewById(R.id.button3);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.deletaRegistro(Integer.parseInt(codigo));
                Intent intent = new Intent(AlteraAlunoActivity.this,ListaPessoaActivity.class);
            startActivity(intent);
            finish();
            }
            });

    }

}
