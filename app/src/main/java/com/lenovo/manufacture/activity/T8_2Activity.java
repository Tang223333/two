package com.lenovo.manufacture.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lenovo.manufacture.MyApp;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.data.Jbsrzc;
import com.lenovo.manufacture.utils.DateUtils;
import com.lenovo.manufacture.utils.MyOk;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.FormBody;

public class T8_2Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText mT82Et1;
    private EditText mT82Et2;
    private Spinner mT82Sp;
    private TextView mT82Txt;
    private Button mT82Btn1;
    private Button mT82Btn2;
    private Timer mTimer;
    private TimerTask mTimerTask=new TimerTask() {
        @Override
        public void run() {
            long l = System.currentTimeMillis();
            l=l/1000;
            mTime = (int) l;
            mT82Txt.setText("时间:"+DateUtils.getYearToM(mTime));
        }
    };
    private int mTime;
    private FormBody.Builder mBuilder;
    private int mPrice;
    private int mEndPrice;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t8_2);
        mProgressDialog=new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setTitle("数据（加载/处理）中...");
        initView();
    }

    private void initView() {
        mT82Et1 = (EditText) findViewById(R.id.t8_2_et1);
        mT82Et2 = (EditText) findViewById(R.id.t8_2_et2);
        mT82Sp = (Spinner) findViewById(R.id.t8_2_sp);
        mT82Txt = (TextView) findViewById(R.id.t8_2_txt);
        mT82Btn1 = (Button) findViewById(R.id.t8_2_btn1);
        mT82Btn2 = (Button) findViewById(R.id.t8_2_btn2);

        mTimer=new Timer();
        mTimer.schedule(mTimerTask,0,1000);



        mT82Btn1.setOnClickListener(this);
        mT82Btn2.setOnClickListener(this);
        mT82Et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String s1=s+"";
                if (s1.matches("^0")){
                    mT82Et1.setText("");
                }
                if (s1.equals("")){
                    mPrice = 0;
                }else {
                    mPrice= Integer.valueOf(s1);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mT82Et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String s1=s+"";
                if (s1.matches("^0")){
                    mT82Et2.setText("");
                }
                if (s1.equals("")){
                    mEndPrice = 0;
                }else {
                    mEndPrice= Integer.valueOf(s1);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.t8_2_btn1:
                finish();
                break;
            case R.id.t8_2_btn2:
                if (mPrice==0||mEndPrice==0){
                    Toast.makeText(this, "金币与剩余金币不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    int type=mT82Sp.getSelectedItemPosition();
                    mBuilder= new FormBody.Builder().add("userWorkId","1").add("price",""+mPrice)
                            .add("endPrice",""+mEndPrice).add("time",""+mTime)
                            .add("type",""+type);
                    if (type==5) {
                        CreatePost("dataInterface/UserInPriceLog/create");
                    }else{
                        CreatePost("dataInterface/UserOutPriceLog/create");
                    }
                }
                break;
        }
    }

    private void CreatePost(String url) {
        if (mProgressDialog != null) {
            mProgressDialog.show();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                Jbsrzc jbsrzc= MyOk.getT(url,mBuilder,Jbsrzc.class);
                MyApp.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        if (jbsrzc==null){
                            Toast.makeText(T8_2Activity.this, "网络有问题", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(T8_2Activity.this, ""+jbsrzc.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        if (mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                    }
                });
            }
        }).start();
    }

}