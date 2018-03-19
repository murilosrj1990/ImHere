package com.imhere.imhere;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PessoaActivity extends AppCompatActivity {
    String codigoTurma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa);

        Button botao = (Button)findViewById(R.id.button);
        codigoTurma = this.getIntent().getStringExtra("codigoTurma");
        System.out.println(codigoTurma);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BancoController crud = new BancoController(getBaseContext());
                EditText nome = (EditText)findViewById(R.id.editText);
                EditText email = (EditText)findViewById((R.id.editText2));
                EditText datanasc = (EditText)findViewById(R.id.editText3);
                String nomeString = nome.getText().toString();
                String emailString = email.getText().toString();
                String datanascString = datanasc.getText().toString();
                String resultado;
                System.out.println(codigoTurma);
                resultado = crud.insereDadoPessoa(nomeString,emailString,datanascString,Integer.parseInt(codigoTurma));

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                Intent intent = new Intent( PessoaActivity.this ,ListaPessoaActivity.class);
                intent.putExtra("codigoTurma",codigoTurma);
                startActivity(intent);
                finish();
            }
        });
    }
}
