package com.davidhernandezn.scancode_java;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
//Detectaremos si se selecciona el botón escanear
    public void onClick(View view){
        if(view.getId() == R.id.btnScan){
            Toast.makeText(this, "Activando Scanner..", Toast.LENGTH_LONG).show();
            new IntentIntegrator(this).initiateScan();
        }
    }

    @Override
    protected void  onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String valor = result.getContents();
        Toast.makeText(this, valor, Toast.LENGTH_LONG).show();
    }


}