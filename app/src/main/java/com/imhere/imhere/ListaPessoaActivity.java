package com.imhere.imhere;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListaPessoaActivity extends Activity {
    private ListView lista;
    String codigoTurma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pessoa);

        codigoTurma = this.getIntent().getStringExtra("codigoTurma");

        BancoController crud = new BancoController(getBaseContext());
        final Cursor cursor = crud.carregaDados(Integer.parseInt(codigoTurma));

        String[] nomeCampos = new String[]{CriaBanco.ID_PESSOA, CriaBanco.NOME_PESSOA};
        int[] idViews = new int[]{R.id.idPessoa, R.id.nomePessoa};

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.layout_nomes, cursor, nomeCampos, idViews, 0);
        lista = (ListView) findViewById(R.id.listView);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.ID_PESSOA));
                Intent intent = new Intent(ListaPessoaActivity.this, AlteraAlunoActivity.class);
                intent.putExtra("codigo", codigo);
                intent.putExtra("codigoTurma",codigoTurma);
                startActivity(intent);
                finish();
            }
        });
        FloatingActionButton addAluno;
        addAluno = findViewById(R.id.faButton);
        addAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( ListaPessoaActivity.this ,PessoaActivity.class);
                intent.putExtra("codigoTurma" , codigoTurma);
                startActivity(intent);
                finish();
            }
        });
    }


}
