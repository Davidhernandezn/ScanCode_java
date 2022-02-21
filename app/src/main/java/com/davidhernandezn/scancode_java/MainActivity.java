package com.davidhernandezn.scancode_java;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
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
    SwipeRefreshLayout mySwipeRefreshLayout;
    WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //color para statusBar
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.black));
        //color para actionBar
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bgapp)));

        myWebView = (WebView) findViewById(R.id.webview);
        assert myWebView != null;

        //Para activar JavaScript
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);

        //Como definimos el minimo de SDK 21 usaremos ESTE TIPO DE LAYER
        webSettings.setMixedContentMode(0);
        myWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        myWebView.setWebViewClient(new WebViewClient());
        myWebView.setWebChromeClient(new WebChromeClient());
        myWebView.setWebViewClient(new MyWebViewClient());

        myWebView.setVerticalScrollBarEnabled(false);
    }

    //Detectamos si se selecciona el botón escanear
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
        myWebView.loadUrl(valor);
        Toast.makeText(this, valor, Toast.LENGTH_LONG).show();
    }

    private class  MyWebViewClient extends  WebViewClient{
        @Override
        public void onPageStarted(WebView view, String valor, Bitmap favicon){
            super.onPageStarted(view, valor, favicon);
        }
        //@Override
        public void onPageFinished(WebView view, String valor){
            findViewById(R.id.webview).setVisibility(View.VISIBLE);
        }
    }

}


















