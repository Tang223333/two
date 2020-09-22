package com.lenovo.manufacture.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.lenovo.manufacture.R;

public class ImgActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView mImgWeb;
    private Button mImgBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img);
        initView();
    }

    private void initView() {
        mImgWeb = (WebView) findViewById(R.id.img_web);
        mImgBtn = (Button) findViewById(R.id.img_btn);
        String imgUrl="file:///android_asset/"+getIntent().getStringExtra("img")+".png";
        WebSettings settings = mImgWeb.getSettings();
        settings.setSupportZoom(true);
        settings.setDisplayZoomControls(true);
        settings.setBuiltInZoomControls(true);
        mImgWeb.loadUrl(imgUrl);
        mImgBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_btn:
                finish();
                break;
        }
    }
}