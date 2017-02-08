package com.example.a.a12_webview;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    ProgressDialog dialog;
    WebView webView;

    //webviewclient를 상속받아서, 내가 필요한것을 재정의 한다.
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

        webView.setWebViewClient(new MyWebViewClient()); // 필수 적으로 사용한다.
        webView.setWebChromeClient(new WebChromeClient()); //추가적인 내용을 사용할때 추가해서 쓴다

        webView.loadUrl("http://www.daum.net");

        /*
         참고 : setjavascriptcanopenwindowsautomatically 로 찾아서 아래것을 참조해 봐라
         참고 : http://ymson.tistory.com/entry/WebView-windowopen-%EC%B2%98%EB%A6%AC

         */
    }

    //backkey를 눌렀을때
    @Override
    public void onBackPressed() {
        //super.onBackPressed();  back key 수행을 막기위해서 막아야 함.
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            finish(); // 더이상 돌아갈 페이지가 없으면 activity를 종료한다.
        }
    }

    public void onBtnClick(View v){
        EditText editURL = (EditText) findViewById(R.id.editURL);
        String str = editURL.getText().toString(); //getText하면 editable이 나롬
        webView.loadUrl(str);
    }
}
