package com.lenovo.manufacture.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lenovo.manufacture.MyApp;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.adpter.BaseAdapter;
import com.lenovo.manufacture.data.Base;
import com.lenovo.manufacture.data.Ry;
import com.lenovo.manufacture.data.Xsscx;
import com.lenovo.manufacture.data.Xsyg;
import com.lenovo.manufacture.utils.CarUtils;
import com.lenovo.manufacture.utils.MyOk;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class T5Activity extends AppCompatActivity {

    private RecyclerView mT3Rv;
    private ProgressDialog mProgressDialog;
    private List<Base> mBaseList=new ArrayList<>();
    private BaseAdapter mBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t5);
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
                Xsyg xsyg= MyOk.getT("dataInterface/UserPeople/getAll",new FormBody.Builder(),Xsyg.class);
                Xsscx xsscx=MyOk.getT("dataInterface/UserProductionLine/getAll",new FormBody.Builder(),Xsscx.class);
                Ry ry=MyOk.getT("dataInterface/People/getAll",new FormBody.Builder(),Ry.class);
                MyApp.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        mBaseList.clear();
                        if (xsscx==null || xsyg==null){
                            Toast.makeText(T5Activity.this, "网络有问题", Toast.LENGTH_SHORT).show();
                        }else {
                            for (int i = 0; i < xsyg.getData().size(); i++) {
                                Base base=new Base();
                                for (int j = 0; j < xsscx.getData().size(); j++) {
                                    if (xsyg.getData().get(i).getUserProductionLineId()==xsscx.getData().get(j).getId()){
                                        base.setB4(CarUtils.getUserLineName(xsscx.getData().get(j).getProductionLineId()));
                                    }
                                }
                                for (int j = 0; j < ry.getData().size(); j++) {
                                    if (xsyg.getData().get(i).getPeopleId()==ry.getData().get(j).getId()){
                                        base.setB1(ry.getData().get(j).getPeopleName());
                                        base.setB2(ry.getData().get(j).getGold()+"");
                                        String workPostId = xsyg.getData().get(i).getWorkPostId();
                                        if ("".equals(workPostId+"")){
                                            base.setB5("未工作");
                                        }else {
                                            base.setB5(""+ry.getData().get(j).getContent());
                                        }
                                    }
                                }
                                int powver=xsyg.getData().get(i).getPower();
                                base.setB3(powver+"");
                                if (powver<30){
                                    base.setColor(0);
                                }else{
                                    base.setColor(3);
                                }
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
        mT3Rv = (RecyclerView) findViewById(R.id.t3_rv);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mT3Rv.setLayoutManager(linearLayoutManager);
        mBaseAdapter = new BaseAdapter();
        mT3Rv.setAdapter(mBaseAdapter);
    }
}