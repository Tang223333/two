package com.lenovo.manufacture.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lenovo.manufacture.MyApp;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.data.Xsgchj;
import com.lenovo.manufacture.data.XsgchjOne;
import com.lenovo.manufacture.utils.CarUtils;
import com.lenovo.manufacture.utils.MyOk;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class T12Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView mT12TxtTime;
    private TextView mT12TxtKt;
    private TextView mT12TxtDg;
    private TextView mT12TxtCjwd;
    private TextView mT12TxtSwwd;
    private TextView mT12TxtGz;
    private TextView mT12TxtDlgy;
    private Switch mT12SwichKt;
    private Switch mT12SwichDg;
    private Spinner mT12SpinnerCjwd;
    private EditText mT12EditTextGz;
    private EditText mT12EditTextDlgy;
    private Button mT12Btn1;
    private Button mT12Btn2;
    private Button mT12Btn3;
    private Button mT12Btn4;
    private ProgressDialog mProgressDialog;
    private int kt=0,dg=0;
    private List<Integer> mList=new ArrayList<>();
    private Xsgchj mXsgchj;
    private FormBody.Builder mBuilder;
    private int mGz;
    private int mDlgy;
    private String mString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t12);
        mProgressDialog=new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setTitle("数据（加载/处理）中...");
        mList.clear();
        for (int i = 16; i < 41; i++) {
            mList.add(i);
        }
        initView();
        initPost();
    }

    private void initPost() {
        if (mProgressDialog != null) {
            mProgressDialog.show();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                mXsgchj = MyOk.getT("dataInterface/UserWorkEnvironmental/getInfo",new FormBody.Builder().add("id","1"),Xsgchj.class);
                MyApp.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        if (mXsgchj ==null){
                            Toast.makeText(T12Activity.this, "网络有问题", Toast.LENGTH_SHORT).show();
                        }else {
                            Update(mXsgchj.getData().get(0));
                        }
                        if (mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                    }
                });
            }
        }).start();
    }

    private void Update(Xsgchj.DataBean dataBean) {
        if (dataBean.getAcOnOff().equals("0")) {
            kt=0;
        }else {
            kt=1;
        }
        mT12TxtKt.setText("空调状态: "+ CarUtils.getKt(dataBean.getAcOnOff()));
        if (dataBean.getLightSwitch()==1) {
            dg=1;
        }else {
            dg=0;
        }
        mT12TxtDg.setText("灯光: "+CarUtils.getGz(dataBean.getLightSwitch()));
        mT12TxtCjwd.setText("车间状态: "+dataBean.getWorkshopTemp());
        mT12TxtSwwd.setText("室外状态: "+dataBean.getOutTemp());
        mT12TxtGz.setText("光照: "+dataBean.getBeam());
        mT12TxtDlgy.setText("电力供应: "+dataBean.getPower());
        mT12TxtTime.setText(""+dataBean.getTime());

        mT12SwichKt.setChecked(kt!=0);
        mT12SwichDg.setChecked(dg!=0);

        ArrayAdapter arrayAdapter=new ArrayAdapter(T12Activity.this,R.layout.support_simple_spinner_dropdown_item,mList);
        mT12SpinnerCjwd.setAdapter(arrayAdapter);
    }

    private void initView() {
        mT12TxtTime = (TextView) findViewById(R.id.t12_txt_time);
        mT12TxtKt = (TextView) findViewById(R.id.t12_txt_kt);
        mT12TxtDg = (TextView) findViewById(R.id.t12_txt_dg);
        mT12TxtCjwd = (TextView) findViewById(R.id.t12_txt_cjwd);
        mT12TxtSwwd = (TextView) findViewById(R.id.t12_txt_swwd);
        mT12TxtGz = (TextView) findViewById(R.id.t12_txt_gz);
        mT12TxtDlgy = (TextView) findViewById(R.id.t12_txt_dlgy);
        mT12SwichKt = (Switch) findViewById(R.id.t12_swich_kt);
        mT12SwichDg = (Switch) findViewById(R.id.t12_swich_dg);
        mT12SpinnerCjwd = (Spinner) findViewById(R.id.t12_spinner_cjwd);
        mT12EditTextGz = (EditText) findViewById(R.id.t12_editText_gz);
        mT12EditTextDlgy = (EditText) findViewById(R.id.t12_editText_dlgy);
        mT12SwichDg.setOnClickListener(this);
        mT12SwichKt.setOnClickListener(this);
        mT12Btn1 = (Button) findViewById(R.id.t12_btn1);
        mT12Btn2 = (Button) findViewById(R.id.t12_btn2);
        mT12Btn3 = (Button) findViewById(R.id.t12_btn3);
        mT12Btn4 = (Button) findViewById(R.id.t12_btn4);

        mT12Btn1.setOnClickListener(this);
        mT12Btn2.setOnClickListener(this);
        mT12Btn3.setOnClickListener(this);
        mT12Btn4.setOnClickListener(this);

        mT12EditTextGz.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String ss=s+"";
                if (ss.equals("")){
                    mGz = 0;
                }else {
                    mGz=Integer.valueOf(ss);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        mT12EditTextDlgy.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String ss=s+"";
                if (ss.equals("")){
                    mDlgy = 0;
                }else {
                    mDlgy=Integer.valueOf(ss);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        mBuilder = new FormBody.Builder().add("id","1");
        switch (v.getId()) {
            case R.id.t12_swich_kt:
                if (kt!=0){
                    kt=0;
                }else {
                    kt=1;
                }
                if (kt!=0){
                    mBuilder.add("acOnOff","1");
                }else {
                    mBuilder.add("acOnOff","0");
                }
                break;
            case R.id.t12_swich_dg:
                if (dg!=0){
                    dg=0;
                }else {
                    dg=1;
                }
                if (dg!=0){
                    mBuilder.add("lightSwitch","1");
                }else {
                    mBuilder.add("lightSwitch","0");
                }
                break;
            case R.id.t12_btn1:
                if (dg==0){
                    Toast.makeText(this, "请先打开空调", Toast.LENGTH_SHORT).show();
                }else if (dg==1){
                    dg=2;
                    mBuilder.add("acOnOff","2");
                }else {
                    dg=1;
                    mBuilder.add("acOnOff","1");
                }
                break;
            case R.id.t12_btn2:
                mBuilder.add("workshopTemp",(mT12SpinnerCjwd.getSelectedItemPosition()+16)+"℃");
                break;
            case R.id.t12_btn3:
                if (mT12EditTextGz.getText().equals("")) {
                    Toast.makeText(this, "你倒是输入啊", Toast.LENGTH_SHORT).show();
                }else {
                    if (dg==0){
                        Toast.makeText(this, "请先开灯", Toast.LENGTH_SHORT).show();
                    }else {
                        if (mGz>3000){
                            Toast.makeText(this, "0-3000", Toast.LENGTH_SHORT).show();
                        }else {
                            mBuilder.add("beam",""+mGz);
                        }
                    }
                }
                break;
            case R.id.t12_btn4:
                if (mT12EditTextDlgy.getText().equals("")) {
                    Toast.makeText(this, "你倒是输入啊", Toast.LENGTH_SHORT).show();
                }else {
                    if (mDlgy>1000){
                        Toast.makeText(this, "0-1000", Toast.LENGTH_SHORT).show();
                    }else {
                        mBuilder.add("power",""+mDlgy);
                    }
                }
                break;
        }
        update();
    }

    private void update() {
        if (mProgressDialog != null) {
            mProgressDialog.show();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                mString = MyOk.getString("dataInterface/UserWorkEnvironmental/update", mBuilder);
                MyApp.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        if (mString ==null) {
                            Toast.makeText(T12Activity.this, "网络有问题", Toast.LENGTH_SHORT).show();
                        }else {
                            try {
                                JSONObject jsonObject=new JSONObject(mString);
                                Toast.makeText(T12Activity.this, ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                mString =jsonObject.getString("data");
                                Xsgchj.DataBean dataBean=MyApp.getGson().fromJson(mString,Xsgchj.DataBean.class);
                                Update(dataBean);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
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