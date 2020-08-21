package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView webview = (WebView) findViewById(R.id.webview);

        String unencodedHtml = "<html>" +
                "    <head>" +
                "        <title>Webview Test</title>" +
                "    </head>" +
                "    <body>" +
                "        <input type='button' value='Say hello' onClick=\"showAndroidToast('Hello Android!')\" />" +
                "        <script type='text/javascript'>" +
                "            function showAndroidToast(toast) { Android.showToast(toast); }" +
                "        </script>" +
                "    </body>" +
                "</html>";
        String encodedHtml = Base64.encodeToString(unencodedHtml.getBytes(), Base64.NO_PADDING);
        webview.addJavascriptInterface(new WebAppInterface( getBaseContext() ), "Android");
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.loadData(encodedHtml, "text/html", "base64");

    }
}