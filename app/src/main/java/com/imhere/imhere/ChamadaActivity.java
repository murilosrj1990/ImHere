package com.imhere.imhere;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.zxing.integration.android.IntentIntegrator;

import java.util.ArrayList;

public class ChamadaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamada);

        AdView adView = (AdView) findViewById(R.id.adViewChamada);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adView.loadAd(adRequest);

        BancoController banco= new BancoController(getBaseContext());
        final Cursor cursor = banco.carregaDadosTurma();
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

        Button iniciarCh = (Button)findViewById(R.id.btnInicioCh);
        iniciarCh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( ChamadaActivity.this , LeitorQR.class);
                Spinner spinner = (Spinner) findViewById(R.id.spinner);
                String codTurma = spinner.getSelectedItem().toString();

                intent.putExtra("codigoTurma", codTurma);
                startActivity(intent);
            }
        });
    }
}
