package com.lenovo.manufacture;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lenovo.manufacture.activity.T10Activity;
import com.lenovo.manufacture.activity.T11Activity;
import com.lenovo.manufacture.activity.T12Activity;
import com.lenovo.manufacture.activity.T13Activity;
import com.lenovo.manufacture.activity.T14Activity;
import com.lenovo.manufacture.activity.T1Activity;
import com.lenovo.manufacture.activity.T2Activity;
import com.lenovo.manufacture.activity.T3Activity;
import com.lenovo.manufacture.activity.T4Activity;
import com.lenovo.manufacture.activity.T5Activity;
import com.lenovo.manufacture.activity.T6Activity;
import com.lenovo.manufacture.activity.T7Activity;
import com.lenovo.manufacture.activity.T8Activity;
import com.lenovo.manufacture.activity.T9Activity;

/**
 * @author Amoly
 * @date 2019/10/24.
 * GitHub：
 * email：
 * description：
 */
public class MainActivity extends AppCompatActivity {


    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initView();
        initWebView();
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void initWebView() {

        TWebView webView = new TWebView(this, null);
        ViewGroup viewParent = findViewById(R.id.webView1);
        viewParent.addView(webView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));

        webView.loadUrl("file:///android_asset/index.html");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                /* mWebView.showLog("test Log"); */
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setBackgroundColor(0);
        webView.requestFocus();
        webView.addJavascriptInterface(new JavaScriptInterface(this), "nativeMethod");
        WebSettings webSetting = webView.getSettings();
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(false);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setDefaultTextEncodingName("utf-8");
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);


        int screenDensity = getResources().getDisplayMetrics().densityDpi;
        WebSettings.ZoomDensity zoomDensity = WebSettings.ZoomDensity.MEDIUM;
        switch (screenDensity) {
            case DisplayMetrics.DENSITY_LOW:
                zoomDensity = WebSettings.ZoomDensity.CLOSE;
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                zoomDensity = WebSettings.ZoomDensity.MEDIUM;
                break;
            case DisplayMetrics.DENSITY_HIGH:
                zoomDensity = WebSettings.ZoomDensity.FAR;
                break;
        }
        webSetting.setDefaultZoom(zoomDensity);
    }



    public class JavaScriptInterface {
        Activity mActivity;

        JavaScriptInterface(Activity mActivity) {
            this.mActivity = mActivity;
        }

        /**
         * 与js交互时用到的方法，在js里直接调用的
         */
        @JavascriptInterface
        public void startActivity(int i) {
            switch (i){
                case 0:
                    mIntent = new Intent();
                    mIntent.setClass(mActivity, T1Activity.class);
                    mActivity.startActivity(mIntent);
                    break;
                case 1:
                    mIntent = new Intent();
                    mIntent.setClass(mActivity, T2Activity.class);
                    mActivity.startActivity(mIntent);
                    break;
                case 2:
                    mIntent = new Intent();
                    mIntent.setClass(mActivity, T3Activity.class);
                    mActivity.startActivity(mIntent);
                    break;
                case 3:
                    mIntent = new Intent();
                    mIntent.setClass(mActivity, T4Activity.class);
                    mActivity.startActivity(mIntent);
                    break;
                case 4:
                    mIntent = new Intent();
                    mIntent.setClass(mActivity, T5Activity.class);
                    mActivity.startActivity(mIntent);
                    break;
                case 5:
                    mIntent = new Intent();
                    mIntent.setClass(mActivity, T6Activity.class);
                    mActivity.startActivity(mIntent);
                    break;
                case 6:
                    mIntent = new Intent();
                    mIntent.setClass(mActivity, T7Activity.class);
                    mActivity.startActivity(mIntent);
                    break;
                case 7:
                    mIntent = new Intent();
                    mIntent.setClass(mActivity, T8Activity.class);
                    mActivity.startActivity(mIntent);
                    break;
                case 8:
                    mIntent = new Intent();
                    mIntent.setClass(mActivity, T9Activity.class);
                    mActivity.startActivity(mIntent);
                    break;
                case 9:
                    mIntent = new Intent();
                    mIntent.setClass(mActivity, T10Activity.class);
                    mActivity.startActivity(mIntent);
                    break;
                case 10:
                    mIntent = new Intent();
                    mIntent.setClass(mActivity, T11Activity.class);
                    mActivity.startActivity(mIntent);
                    break;
                case 11:
                    mIntent = new Intent();
                    mIntent.setClass(mActivity, T12Activity.class);
                    mActivity.startActivity(mIntent);
                    break;
                case 12:
                    mIntent = new Intent();
                    mIntent.setClass(mActivity, T13Activity.class);
                    mActivity.startActivity(mIntent);
                    break;
                case 13:
                    mIntent = new Intent();
                    mIntent.setClass(mActivity, T14Activity.class);
                    mActivity.startActivity(mIntent);
                    break;
                case 14:
                    mIntent = new Intent();
                    mIntent.setClass(mActivity, TestActivity.class);
                    mActivity.startActivity(mIntent);
                    break;

            }
            Toast.makeText(mActivity, ""+i, Toast.LENGTH_SHORT).show();
        }

    }

}
