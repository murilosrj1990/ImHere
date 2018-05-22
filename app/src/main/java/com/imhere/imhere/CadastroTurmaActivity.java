package com.imhere.imhere;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroTurmaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_turma);

        Button botao = (Button)findViewById(R.id.button);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BancoController crud = new BancoController(getBaseContext());
                EditText nome = (EditText)findViewById(R.id.etNomeAluno);
                String nomeString = nome.getText().toString();
                String resultado;

                resultado = crud.insereDadoTurma(nomeString);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                Intent intent = new Intent( CadastroTurmaActivity.this ,ListaTurmaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
