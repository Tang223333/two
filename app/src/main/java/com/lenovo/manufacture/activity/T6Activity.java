package com.lenovo.manufacture.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.lenovo.manufacture.MyApp;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.adpter.BaseAdapter;
import com.lenovo.manufacture.data.Base;
import com.lenovo.manufacture.data.Scgx;
import com.lenovo.manufacture.data.Schj;
import com.lenovo.manufacture.data.Xsscx;
import com.lenovo.manufacture.data.Xsyg;
import com.lenovo.manufacture.utils.MyOk;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class T6Activity extends AppCompatActivity {
    private RecyclerView mT3Rv;
    private ProgressDialog mProgressDialog;
    private List<Base> mBaseList=new ArrayList<>();
    private BaseAdapter mBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t6);
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
                Xsscx xsscx=MyOk.getT("dataInterface/UserProductionLine/getAll",new FormBody.Builder(),Xsscx.class);
                Scgx scgx=MyOk.getT("dataInterface/Stage/getAll",new FormBody.Builder(),Scgx.class);
                Schj schj=MyOk.getT("dataInterface/PLStep/getAll",new FormBody.Builder(),Schj.class);
                MyApp.getHandler().post(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void run() {
                        if (scgx==null || schj==null || xsscx==null){
                            Toast.makeText(T6Activity.this, "网络有问题", Toast.LENGTH_SHORT).show();
                        }else {
                            scgx.getData().sort(((o1, o2) -> o1.getId()-o2.getId()));
                            schj.getData().sort(((o1, o2) -> o1.getId()-o2.getId()));
                            for (int i = 0; i < schj.getData().size(); i++) {
                                Base base=new Base();
                                base.setB1(""+schj.getData().get(i).getId());
                                base.setB2(""+scgx.getData().get(i).getStageName());
                                base.setB3(""+scgx.getData().get(i).getContent());
                                base.setB4(""+schj.getData().get(i).getPlStepName());
                                Integer nextStageId = scgx.getData().get(i).getNextStageId();
                                if ((nextStageId+"").equals("0")){
                                    base.setB5("");
                                }else {
                                    base.setB5(""+scgx.getData().get(i+1).getStageName());
                                }
                                base.setB6(""+schj.getData().get(i).getIcon());
                                base.setColor(3);
                                mBaseList.add(base);
                            }
                            for (int i = 0; i < xsscx.getData().size(); i++) {
                                for (int j = 0; j <schj.getData().size(); j++) {
                                    if (xsscx.getData().get(i).getStageId()==schj.getData().get(j).getId()){
                                        mBaseList.get(j).setColor(0);
                                    }
                                }
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
        mT3Rv = (RecyclerView) findViewById(R.id.t6_rv);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mT3Rv.setLayoutManager(linearLayoutManager);
        mBaseAdapter = new BaseAdapter(new BaseAdapter.CLick() {
            @Override
            public void OnClick(int index) {
                Intent intent=new Intent(T6Activity.this,ImgActivity.class);
                intent.putExtra("img",mBaseList.get(index).getB6());
                startActivity(intent);
            }

            @Override
            public void OnLongClick(int index) {

            }
        });
        mT3Rv.setAdapter(mBaseAdapter);
    }
}