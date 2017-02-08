package com.example.a.a12_webview;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    ProgressDialog dialog;
    WebView webView;

    class MyWebViewClient extends WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            dialog.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            dialog.dismiss();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(this);

        webView = (WebView) findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true); //javascript활성화

        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl("http://www.daum.net");
    }

    public void onBtnClick(View v){
        EditText editURL = (EditText) findViewById(R.id.editURL);
        String str = editURL.getText().toString(); //getText하면 editable이 나롬
        webView.loadUrl(str);
    }
}
