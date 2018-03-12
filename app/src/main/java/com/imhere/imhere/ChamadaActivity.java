package com.imhere.imhere;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class ChamadaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamada);

        BancoController banco= new BancoController(getBaseContext());
        final Cursor cursor = banco.carregaDados();
        ArrayList arrayList = new ArrayList();
        if(cursor != null && cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String nome = cursor.getString(1);
                arrayList.add(id + " " +nome);

            }while(cursor.moveToNext());
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayList);
        Spinner spinner =  findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
    }
}
