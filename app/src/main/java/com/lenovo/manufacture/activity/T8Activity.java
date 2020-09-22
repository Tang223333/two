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
import com.lenovo.manufacture.adpter.BaseAdapter;
import com.lenovo.manufacture.data.Base;
import com.lenovo.manufacture.data.Dd;
import com.lenovo.manufacture.utils.CarUtils;
import com.lenovo.manufacture.utils.IntUtils;
import com.lenovo.manufacture.utils.MyOk;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class T8Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView mT8Txt;
    private Button mT8Xd;
    private RecyclerView mT8Rv;
    private ProgressDialog mProgressDialog;
    private List<Base> mBaseList=new ArrayList<>();
    private BaseAdapter mBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t8);
        mProgressDialog=new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setTitle("数据（加载/处理）中...");
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
                Dd dd= MyOk.getT("dataInterface/UserAppointment/getAll",new FormBody.Builder(),Dd.class);
                MyApp.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        if (dd != null) {
                            int all = 0;
                            for (int i = 0; i < dd.getData().size(); i++) {
                                Dd.DataBean dataBean = dd.getData().get(i);
                                Base base=new Base();
                                all+=dataBean.getGold()*dataBean.getNum();
                                base.setB1(""+dataBean.getId());
                                base.setB2(""+dataBean.getUserAppointmentName());
                                base.setB3(""+dataBean.getGold());
                                base.setB4(""+dataBean.getNum());
                                base.setB5(""+ CarUtils.getCarName(dataBean.getCarId()));
                                base.setB6(""+CarUtils.getDdType(dataBean.getType()));
                                base.setColor(CarUtils.getCarColor(dataBean.getColor()));
                                mBaseList.add(base);
                            }
                            mT8Txt.setText("支出总额:"+ IntUtils.getInt(all));
                            mBaseAdapter.setData(mBaseList,6);
                        }else {
                            Toast.makeText(T8Activity.this, "网络有问题", Toast.LENGTH_SHORT).show();
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
        mT8Txt = (TextView) findViewById(R.id.t8_txt);
        mT8Xd = (Button) findViewById(R.id.t8_xd);
        mT8Rv = (RecyclerView) findViewById(R.id.t8_rv);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mT8Rv.setLayoutManager(linearLayoutManager);
        mBaseAdapter=new BaseAdapter();
        mT8Rv.setAdapter(mBaseAdapter);

        mT8Xd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.t8_xd:
                Intent intent=new Intent(T8Activity.this,T9_2Activity.class);
                startActivity(intent);
                break;
        }
    }
}