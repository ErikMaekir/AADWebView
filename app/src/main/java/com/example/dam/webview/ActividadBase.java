package com.example.dam.webview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ActividadBase extends AppCompatActivity {

    static final String TAG = "MITAG";
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_base);

        webView = findViewById(R.id.WebView);
        webView.getSettings().setJavaScriptEnabled(true);
        InterfaceAplicacionWeb iaw = new InterfaceAplicacionWeb();
        webView.addJavascriptInterface(iaw, "puente");
        webView.loadUrl("http://www.juntadeandalucia.es/averroes/centros-tic/18700098/moodle2/");
        webView.setWebViewClient(new WebViewClient() {
            int contador = 0;
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.v(TAG, url);
                if(contador == 0) {
                    Log.v(TAG, "on page finished");
                    /*final String javaScript = "var boton = document.getElementById('enviar');\n" +
                            "            var nombre = document.getElementById('nombre');\n" +
                            "            var btEnviar = document.getElementById('btEnviar');\n" +
                            "            boton.addEventListener('click', function() {\n" +
                            "                nombre.value = 'pepe';\n" +
                            "                btEnviar.click();\n" +
                            "            });" +
                            "            boton.click();";*/
                    /*final String javaScript =
                            "var nombre = document.getElementById('nombre');" +
                            "var btEnviar = document.getElementById('btEnviar');" +
                            "nombre.value = 'pepito';" +
                            "btEnviar.addEventListener('click', function() {Android: var data = nombre.value})" +
                            "btEnviar.click();";
                    webView.loadUrl("javascript: " + javaScript);*/
                    final String javaScript = "" +
                            "var boton = document.getElementById('btEnviar');" +
                            "boton.addEventListener('click', function() {" +
                            "    var nombre = document.getElementById('nombre').value;" +
                            "    var clave  = document.getElementById('clave').value;" +
                            "    puente.sendData(nombre, clave);" +
                            "});";
                    webView.loadUrl("javascript: " + javaScript);
                    //Log.v(TAG, iaw.getUsuario());
                    //Log.v(TAG, iaw.getClave());
                }
                contador++;
            }
        });
    }
}