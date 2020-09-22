package com.lenovo.manufacture.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lenovo.manufacture.MyApp;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.adpter.BaseAdapter;
import com.lenovo.manufacture.data.Base;
import com.lenovo.manufacture.data.Ghsplb;
import com.lenovo.manufacture.data.Yclxq;
import com.lenovo.manufacture.utils.IntUtils;
import com.lenovo.manufacture.utils.MyOk;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class T3Activity extends AppCompatActivity {

    private TextView mT3Txt;
    private RecyclerView mT3Rv;
    private List<Base> mBaseList=new ArrayList<>();
    private ProgressDialog mProgressDialog;
    private BaseAdapter mBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t3);
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
                Yclxq yclxq=MyOk.getT("Interface/index/getMaterial",new FormBody.Builder(),Yclxq.class);
                MyApp.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        mBaseList.clear();
                        if (yclxq != null) {
                            int all=0;
                            for (int i = 0; i < yclxq.getData().size(); i++) {
                                Base base=new Base();
                                Yclxq.DataBean dataBean = yclxq.getData().get(i);
                                all+=dataBean.getPrice()*dataBean.getNum();
                                base.setB1(""+dataBean.getId());
                                base.setB2(""+dataBean.getMaterialName());
                                base.setB3(""+dataBean.getPrice());
                                base.setB4(""+dataBean.getNum());
                                base.setB5(""+dataBean.getSupplyName());
                                base.setB6(""+dataBean.getSize());
                                base.setColor(3);
                                mBaseList.add(base);
                            }
                            mT3Txt.setText("供货总额:"+ IntUtils.getInt(all));
                            mBaseAdapter.setData(mBaseList,6);
                        }else {
                            Toast.makeText(T3Activity.this, "网络有问题", Toast.LENGTH_SHORT).show();
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
        mT3Txt = (TextView) findViewById(R.id.t3_txt);
        mT3Rv = (RecyclerView) findViewById(R.id.t3_rv);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mT3Rv.setLayoutManager(linearLayoutManager);
        mBaseAdapter = new BaseAdapter();
        mT3Rv.setAdapter(mBaseAdapter);
    }
}