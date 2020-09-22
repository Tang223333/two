package com.lenovo.manufacture.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lenovo.manufacture.MyApp;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.adpter.BaseAdapter;
import com.lenovo.manufacture.adpter.T11Adapter;
import com.lenovo.manufacture.data.Base;
import com.lenovo.manufacture.data.Schj;
import com.lenovo.manufacture.utils.MyOk;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class T11Activity extends AppCompatActivity {

    private RecyclerView mT11Rv;
    private T11Adapter mT11Adapter;
    private List<Base> mBaseList=new ArrayList<>();
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t11);
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
                Schj schj= MyOk.getT("dataInterface/PLStep/getAll",new FormBody.Builder(),Schj.class);
                MyApp.getHandler().post(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void run() {
                        if (schj==null) {
                            Toast.makeText(T11Activity.this, "", Toast.LENGTH_SHORT).show();
                        }else {
                            schj.getData().sort(((o1, o2) -> o1.getId()-o2.getId()));
                            for (int i = 0; i < schj.getData().size(); i++) {
                                Base base=new Base();
                                base.setB1(schj.getData().get(i).getIcon());
                                base.setB2(schj.getData().get(i).getPlStepName());
                                mBaseList.add(base);
                            }
                            mT11Adapter.setData(mBaseList);
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
        mT11Rv = findViewById(R.id.t11_rv);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,4);
        mT11Rv.setLayoutManager(gridLayoutManager);
        mT11Adapter = new T11Adapter(new BaseAdapter.CLick() {
            @Override
            public void OnClick(int index) {
                Intent intent=new Intent(T11Activity.this,ImgActivity.class);
                intent.putExtra("img",mBaseList.get(index).getB1());
                startActivity(intent);
            }

            @Override
            public void OnLongClick(int index) {

            }
        });
        mT11Rv.setAdapter(mT11Adapter);
    }
}