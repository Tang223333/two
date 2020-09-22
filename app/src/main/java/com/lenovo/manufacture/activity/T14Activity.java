package com.lenovo.manufacture.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lenovo.manufacture.MyApp;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.adpter.T14Adapter;
import com.lenovo.manufacture.data.Base;
import com.lenovo.manufacture.data.Cpck;
import com.lenovo.manufacture.data.Xsgchj;
import com.lenovo.manufacture.data.Xsgcxx;
import com.lenovo.manufacture.utils.CarUtils;
import com.lenovo.manufacture.utils.IntUtils;
import com.lenovo.manufacture.utils.MyOk;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class T14Activity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mT7Rv;
    private TextView mT14Txt1;
    private Button mT14Btn1;
    private Button mT14Btn2;
    private T14Adapter mT14Adapter;
    private ProgressDialog mProgressDialog;
    private List<Base> mBaseList=new ArrayList<>();
    private int mZl;
    private Cpck mCpck1;
    private Cpck mCpck2;
    private int mAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t14);
        mProgressDialog=new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setTitle("数据（加载/处理）中...");
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
                Xsgcxx xsgcxx=MyOk.getT("dataInterface/UserWorkInfo/getAll",new FormBody.Builder(),Xsgcxx.class);
                Cpck cpck1= MyOk.getT("dataInterface/UserNormalCarStore/getAll",new FormBody.Builder(),Cpck.class);
                Cpck cpck2= MyOk.getT("dataInterface/UserRepairCarStore/getAll",new FormBody.Builder(),Cpck.class);
                MyApp.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        if (cpck1==null || cpck2==null){
                            Toast.makeText(T14Activity.this, "网络有问题", Toast.LENGTH_SHORT).show();
                        }else {
                            mBaseList.clear();
                            mAll = 0;
                            for (int i = 0; i < cpck1.getData().size(); i++) {
                                Cpck.DataBean dataBean = cpck1.getData().get(i);
                                Base base=new Base();
                                base.setB1(""+ CarUtils.getCarNameD(dataBean.getCarId()));
                                base.setB2(""+CarUtils.getCarPrice(dataBean.getCarId()));
                                base.setB3(""+dataBean.getNum());
                                base.setB4("成品");
                                base.setB5(""+dataBean.getId());
                                base.setTF(false);
                                mBaseList.add(base);
                            }
                            for (int i = 0; i < cpck2.getData().size(); i++) {
                                Cpck.DataBean dataBean = cpck2.getData().get(i);
                                Base base=new Base();
                                base.setB1(""+ CarUtils.getCarNameD(dataBean.getCarId()));
                                base.setB2(""+CarUtils.getCarPrice(dataBean.getCarId()));
                                base.setB3(""+dataBean.getNum());
                                base.setB4("维修");
                                base.setB5(""+dataBean.getId());
                                base.setTF(false);
                                mBaseList.add(base);
                            }
                        }
                        mT14Txt1.setText("工厂资金:"+ IntUtils.getInt(xsgcxx.getData().get(0).getPrice()));
                        mT14Adapter.setData(mBaseList);
                        if (mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                    }
                });
            }
        }).start();
    }

    private void initView() {
        mT7Rv = (RecyclerView) findViewById(R.id.t7_rv);
        mT14Txt1 = (TextView) findViewById(R.id.t14_txt1);
        mT14Btn1 = (Button) findViewById(R.id.t14_btn1);
        mT14Btn2 = (Button) findViewById(R.id.t14_btn2);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mT7Rv.setLayoutManager(linearLayoutManager);
        mT14Adapter = new T14Adapter(new T14Adapter.Click() {
            @Override
            public void OnClick(int index) {
                mBaseList.get(index).setTF(!mBaseList.get(index).isTF());
                mT14Adapter.setData(mBaseList);
            }
        });
        mT7Rv.setAdapter(mT14Adapter);

        mT14Btn1.setOnClickListener(this);
        mT14Btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.t14_btn1:
                Intent intent=new Intent(T14Activity.this,T14_2Activity.class);
                startActivity(intent);
                break;
            case R.id.t14_btn2:
                deletePost();
                break;
        }
    }

    private void deletePost() {
        if (mProgressDialog != null) {
            mProgressDialog.show();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                mZl = 0;
                for (int i = 0; i < mBaseList.size(); i++) {
                    if (mBaseList.get(i).isTF()){
                        mZl =1;
                        mCpck1 = MyOk.getT("dataInterface/UserNormalCarStore/delete",new FormBody.Builder().add("id",mBaseList.get(i).getB5()),Cpck.class);
                        mCpck2 = MyOk.getT("dataInterface/UserRepairCarStore/delete",new FormBody.Builder().add("id",mBaseList.get(i).getB5()),Cpck.class);
                    }
                }
                MyApp.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        if (mCpck1==null || mCpck2==null){
                            Toast.makeText(T14Activity.this, "网络有问题", Toast.LENGTH_SHORT).show();
                        }else {
                            if (mZl ==0) {
                                Toast.makeText(T14Activity.this, "请选中后删除", Toast.LENGTH_SHORT).show();
                            }else {
                                if (mCpck1.getMessage().equals("SUCCESS") || mCpck2.getMessage().equals("SUCCESS")){
                                    Toast.makeText(T14Activity.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                                }
                                initPost();
                            }
                        }
                    }
                });
            }
        }).start();
    }
}