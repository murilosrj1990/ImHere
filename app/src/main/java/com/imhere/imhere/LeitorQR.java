package com.imhere.imhere;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class LeitorQR extends AppCompatActivity {

    ImageButton btnIniciaChamada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitor_qr);
        btnIniciaChamada = (ImageButton) findViewById(R.id.btnIniciarChamada);

        btnIniciaChamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator= new IntentIntegrator(LeitorQR.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scaneando Alunos...");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();

            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent dados){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,dados);
        if(result != null){
            if(result.getContents() == null){
                Toast.makeText(this, "Você cancelou o scan!", Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, result.getContents(),Toast.LENGTH_LONG).show();
            }
        }else{
            super.onActivityResult(requestCode,resultCode,dados);
        }


    };

}
