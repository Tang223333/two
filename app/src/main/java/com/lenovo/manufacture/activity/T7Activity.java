package com.lenovo.manufacture.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lenovo.manufacture.MyApp;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.adpter.BaseAdapter;
import com.lenovo.manufacture.data.Base;
import com.lenovo.manufacture.data.Schj;
import com.lenovo.manufacture.data.Schjylxh;
import com.lenovo.manufacture.data.Ycl;
import com.lenovo.manufacture.utils.MyOk;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class T7Activity extends AppCompatActivity {

    private RecyclerView mT7Rv;
    private ProgressDialog mProgressDialog;
    private List<Base> mBaseList=new ArrayList<>();
    private BaseAdapter mBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t7);
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
                mBaseList.clear();
                Ycl ycl=MyOk.getT("dataInterface/Part/getAll",new FormBody.Builder(),Ycl.class);
                Schj schj=MyOk.getT("dataInterface/PLStep/getAll",new FormBody.Builder(),Schj.class);
                Schjylxh schjylxh= MyOk.getT("dataInterface/plStepCost/getAll",new FormBody.Builder(),Schjylxh.class);
                MyApp.getHandler().post(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void run() {
                        if (ycl==null || schj==null || schjylxh==null){
                            Toast.makeText(T7Activity.this, "网路有问题", Toast.LENGTH_SHORT).show();
                        }else {
                            schj.getData().sort(((o1, o2) -> o1.getId()-o2.getId()));
                            schjylxh.getData().sort(((o1, o2) -> o1.getPlStepId()-o2.getPlStepId()));
                            for (int i = 0; i < schjylxh.getData().size(); i++) {
                                Base base=new Base();
                                for (int j = 0; j < ycl.getData().size(); j++) {
                                    if (schjylxh.getData().get(i).getPartId()==ycl.getData().get(j).getId()){
                                        base.setB4(ycl.getData().get(j).getPartName());
                                        continue;
                                    }
                                }
                                base.setB1(schjylxh.getData().get(i).getId()+"");
                                base.setB2(schj.getData().get(i).getPlStepName()+"");
                                base.setB3(schj.getData().get(i).getConsume()+"");
                                base.setB5(schjylxh.getData().get(i).getNum()+"");
                                base.setColor(3);
                                mBaseList.add(base);
                            }
                            mBaseAdapter.setData(mBaseList,5);
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
        mT7Rv = (RecyclerView) findViewById(R.id.t7_rv);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mT7Rv.setLayoutManager(linearLayoutManager);
        mBaseAdapter = new BaseAdapter();
        mT7Rv.setAdapter(mBaseAdapter);
    }
}