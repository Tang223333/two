package com.lenovo.manufacture.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
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
import com.lenovo.manufacture.data.Jbsrzc;
import com.lenovo.manufacture.utils.CarUtils;
import com.lenovo.manufacture.utils.DateUtils;
import com.lenovo.manufacture.utils.IntUtils;
import com.lenovo.manufacture.utils.MyOk;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class T9Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView mT9Txt1;
    private TextView mT9Txt2;
    private Button mT9Btn;
    private RecyclerView mT9Rv;
    private BaseAdapter mBaseAdapter;
    private ProgressDialog mProgressDialog;
    private List<Base> mBaseList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t9);
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
                Jbsrzc jbsr= MyOk.getT("dataInterface/UserInPriceLog/getAll",new FormBody.Builder(),Jbsrzc.class);
                Jbsrzc jbzc=MyOk.getT("dataInterface/UserOutPriceLog/getAll",new FormBody.Builder(),Jbsrzc.class);
                MyApp.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        if (jbsr==null || jbzc==null){
                            Toast.makeText(T9Activity.this, "网络请求", Toast.LENGTH_SHORT).show();
                        }else {
                            mBaseList.clear();
                            int srAll=0;
                            for (int i = 0; i < jbsr.getData().size(); i++) {
                                Jbsrzc.DataBean dataBean = jbsr.getData().get(i);
                                Base base=new Base();
                                srAll+=dataBean.getPrice();
                                base.setB1(""+dataBean.getId());
                                base.setB2(""+dataBean.getPrice());
                                base.setB3(""+dataBean.getEndPrice());
                                String s=dataBean.getType()+"";
                                if (s.equals("null")){
                                    base.setB4("null");
                                }else {
                                    s=s.substring(0,1);
                                    base.setB4(""+CarUtils.getInOutType(Integer.valueOf(s)));
                                }
                                base.setB5(""+ DateUtils.getYearToM(dataBean.getTime()));
                                base.setColor(3);
                                mBaseList.add(base);
                            }
                            int zcAll=0;
                            for (int i = 0; i < jbzc.getData().size(); i++) {
                                Jbsrzc.DataBean dataBean = jbzc.getData().get(i);
                                Base base=new Base();
                                zcAll+=dataBean.getPrice();
                                base.setB1(""+dataBean.getId());
                                base.setB2(""+dataBean.getPrice());
                                base.setB3(""+dataBean.getEndPrice());
                                String s=dataBean.getType()+"";
                                if (s.equals("null")){
                                    base.setB4("null");
                                }else {
                                    s=s.substring(0,1);
                                    base.setB4(""+CarUtils.getInOutType(Integer.valueOf(s)));
                                }
                                base.setB5(""+ DateUtils.getYearToM(dataBean.getTime()));
                                base.setColor(3);
                                mBaseList.add(base);
                            }
                            mT9Txt1.setText("支出总额:"+ IntUtils.getInt(zcAll));
                            mT9Txt2.setText("收入总额:"+ IntUtils.getInt(srAll));
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
        mT9Txt1 = (TextView) findViewById(R.id.t9_txt1);
        mT9Txt2 = (TextView) findViewById(R.id.t9_txt2);
        mT9Btn = (Button) findViewById(R.id.t9_btn);
        mT9Rv = (RecyclerView) findViewById(R.id.t9_rv);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mT9Rv.setLayoutManager(linearLayoutManager);
        mBaseAdapter = new BaseAdapter(new BaseAdapter.CLick() {
            @Override
            public void OnClick(int index) {

            }

            @Override
            public void OnLongClick(int index) {
                DialogShow(index);
            }
        });
        mT9Rv.setAdapter(mBaseAdapter);

        mT9Btn.setOnClickListener(this);
    }

    private void DialogShow(int index) {
        Dialog dialog=new Dialog(T9Activity.this);
        dialog.show();
        dialog.setContentView(R.layout.dialog_t9);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
        TextView mDialogT9Txt = dialog.findViewById(R.id.dialog_t9_txt);
        Button mDialogT9Btn1 = dialog.findViewById(R.id.dialog_t9_btn1);
        Button mDialogT9Btn2 = dialog.findViewById(R.id.dialog_t9_btn2);
        mDialogT9Txt.setText("确认删除ID为:"+mBaseList.get(index).getB1()+"的金币日志记录吗?");
        mDialogT9Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        mDialogT9Btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePost(mBaseList.get(index).getB1());
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void deletePost(String b1) {
        if (mProgressDialog != null) {
            mProgressDialog.show();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                FormBody.Builder builder=new FormBody.Builder().add("id",b1);
                Jbsrzc jbsrzc=MyOk.getT("dataInterface/UserOutPriceLog/delete",builder,Jbsrzc.class);
                MyApp.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        if (jbsrzc==null) {
                            Toast.makeText(T9Activity.this, "网络有问题", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(T9Activity.this, ""+jbsrzc.getMessage(), Toast.LENGTH_SHORT).show();
                            initPost();
                        }
                    }
                });
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.t9_btn:
                Intent intent=new Intent(T9Activity.this,T8_2Activity.class);
                startActivity(intent);
                break;
        }
    }
}