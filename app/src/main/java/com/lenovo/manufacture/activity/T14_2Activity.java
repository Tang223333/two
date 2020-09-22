package com.lenovo.manufacture.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lenovo.manufacture.MyApp;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.data.Base;
import com.lenovo.manufacture.data.T13Adapter;
import com.lenovo.manufacture.data.Xsmcjl;
import com.lenovo.manufacture.utils.CarUtils;
import com.lenovo.manufacture.utils.DateUtils;
import com.lenovo.manufacture.utils.MyOk;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class T14_2Activity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mT14Rv;
    private Button mT142Fh;
    private T13Adapter mT13Adapter;
    private ProgressDialog mProgressDialog;
    private List<Base> mBaseList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t14_2);
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
                Xsmcjl xsmcjl= MyOk.getT("dataInterface/UserSellOutLog/getAll",new FormBody.Builder(),Xsmcjl.class);
                MyApp.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        if (xsmcjl != null) {
                            mBaseList.clear();
                            for (int i = 0; i < xsmcjl.getData().size(); i++) {
                                Xsmcjl.DataBean dataBean = xsmcjl.getData().get(i);
                                Base base=new Base();
                                base.setB1(""+ CarUtils.getCarNameD(dataBean.getCarId()));
                                base.setB2(""+ dataBean.getGold());
                                base.setB3(""+ dataBean.getNum());
                                base.setB4(""+ DateUtils.getYearToM(dataBean.getTime()));
                                base.setB5("删除");
                                base.setB6(""+dataBean.getId());
                                base.setTF(false);
                                mBaseList.add(base);
                            }
                            mT13Adapter.setData(mBaseList);
                        }else {
                            Toast.makeText(T14_2Activity.this, "网路有问题", Toast.LENGTH_SHORT).show();
                        }
                        if (mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                    }
                });
            }
        }).start();
    }

    private void initView() {
        mT14Rv = (RecyclerView) findViewById(R.id.t14_rv);
        mT142Fh = (Button) findViewById(R.id.t14_2_fh);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mT14Rv.setLayoutManager(linearLayoutManager);
        mT13Adapter = new T13Adapter(new T13Adapter.Click() {
            @Override
            public void OnClick(int index) {
                deletePost(index);
            }
        });
        mT14Rv.setAdapter(mT13Adapter);

        mT142Fh.setOnClickListener(this);
    }

    private void deletePost(int index) {
        if (mProgressDialog != null) {
            mProgressDialog.show();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                Xsmcjl xsmcjl=MyOk.getT("dataInterface/UserSellOutLog/delete",new FormBody.Builder().add("id",mBaseList.get(index).getB6()),Xsmcjl.class);
                MyApp.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        if (xsmcjl != null) {
                            Toast.makeText(T14_2Activity.this, ""+xsmcjl.getMessage(), Toast.LENGTH_SHORT).show();
                            initPost();
                        }else {
                            Toast.makeText(T14_2Activity.this, "网络有问题", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.t14_2_fh:
                finish();
                break;
        }
    }
}