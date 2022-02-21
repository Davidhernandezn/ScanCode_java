package com.davidhernandezn.scancode_java;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    WebView mywebview = (WebView) findViewById(R.id.webview);
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

            WebView mywebview = (WebView) findViewById(R.id.webview);
            assert mywebview != null;
            //Para activar JavaScript
            WebSettings webSettings = mywebview.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setAllowFileAccess(true);

            //Como definimos el minimo de SDK 21 usaremos ESTE TIPO DE LAYER
            webSettings.setMixedContentMode(0);
            mywebview.setLayerType(View.LAYER_TYPE_HARDWARE, null);

            mywebview.setWebViewClient(new WebViewClient());
            mywebview.setWebChromeClient(new WebChromeClient());

            mywebview.setVerticalScrollBarEnabled(false);
        }
    }

    @Override
    protected void  onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String valor = result.getContents();
        mywebview.loadUrl(valor);
        Toast.makeText(this, valor, Toast.LENGTH_LONG).show();
    }

    private class  MyWebViewClient extends  WebViewClient{
        @Override
        public  void  onPageStarted(WebView view, String valor, Bitmap favicon){
            super.onPageStarted(view, valor, favicon);
        }
//        @Override
        public void  onPageFinish(WebView view, String valor){
            findViewById(R.id.webview).setVisibility(view.VISIBLE);
        }
    }

}