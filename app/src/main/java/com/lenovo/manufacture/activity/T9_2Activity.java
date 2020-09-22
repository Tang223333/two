package com.lenovo.manufacture.activity;

import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lenovo.manufacture.R;

public class T9_2Activity extends AppCompatActivity {

    private WebView mT92Web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t9_2);
        initView();
    }

    private void initView() {
        mT92Web = (WebView) findViewById(R.id.t9_2_web);
        WebSettings settings = mT92Web.getSettings();
        settings.setJavaScriptEnabled(true);
        mT92Web.addJavascriptInterface(new Client(),"Client");
        mT92Web.setWebChromeClient(mWebChromeClient);
        mT92Web.setWebViewClient(new WebViewClient());
        mT92Web.loadUrl("file:///android_asset/t9_2.html");
    }

    public WebChromeClient mWebChromeClient=new WebChromeClient(){
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Toast.makeText(T9_2Activity.this, ""+message, Toast.LENGTH_SHORT).show();
            result.confirm();
            return true;
        }
    };

    public class Client{

        @JavascriptInterface
        public void OnFinish(){
            finish();
        }
    }
}