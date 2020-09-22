package com.lenovo.manufacture.activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.data.CandleData;
import com.lenovo.manufacture.MyApp;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.adpter.BaseAdapter;
import com.lenovo.manufacture.data.Base;
import com.lenovo.manufacture.data.Scjl;
import com.lenovo.manufacture.utils.DateUtils;
import com.lenovo.manufacture.utils.IntUtils;
import com.lenovo.manufacture.utils.MyOk;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class T1Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView mT1Txt1;
    private TextView mT1Txt2;
    private Button mT1Btn1;
    private RecyclerView mT1Rv;
    private BaseAdapter mBaseAdapter;
    private String date="3030/09/20";
    private ProgressDialog mProgressDialog;
    private List<Base> mBaseList=new ArrayList<>();
    private List<Base> mBaseList2=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t1);
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
                List<Scjl> scjlList= MyOk.getTList("Interface/index/userSellInfoTEditer",new FormBody.Builder(),Scjl.class);
                MyApp.getHandler().post(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void run() {
                        if (scjlList==null){
                            Toast.makeText(T1Activity.this, "网络有问题", Toast.LENGTH_SHORT).show();
                        }else {
                            scjlList.sort(((o1, o2) -> o2.getTime()-o1.getTime()));
                            int all=0;
                            for (int i = 0; i < scjlList.size(); i++) {
                                Scjl scjl = scjlList.get(i);
                                all+=scjl.getPrice()*scjl.getNum();
                                Base base=new Base();
                                base.setB1(""+scjl.getId());
                                base.setB2(""+scjl.getCarTypeName());
                                base.setB3(""+scjl.getPrice());
                                base.setB4(""+scjl.getNum());
                                base.setB5(""+ DateUtils.getYearToM(scjl.getTime()));
                                base.setB6(""+DateUtils.getYearToD(scjl.getTime()));
                                base.setColor(3);
                                mBaseList.add(base);
                            }
                            mBaseAdapter.setData(mBaseList,5);
                            mT1Txt1.setText("售出总额:"+ IntUtils.getInt(all));
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
        mT1Txt1 = (TextView) findViewById(R.id.t1_txt1);
        mT1Txt2 = (TextView) findViewById(R.id.t1_txt2);
        mT1Btn1 = (Button) findViewById(R.id.t1_btn1);
        mT1Rv = (RecyclerView) findViewById(R.id.t1_rv);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mT1Rv.setLayoutManager(linearLayoutManager);
        mBaseAdapter = new BaseAdapter();
        mT1Rv.setAdapter(mBaseAdapter);

        mT1Txt2.setOnClickListener(this);
        mT1Btn1.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.t1_txt2:
                DatePickerDialog datePickerDialog=new DatePickerDialog(this);
                datePickerDialog.show();
                datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String s=""+year+"/";
                        if ((month+1)<10){
                            s=s+"0"+(month+1)+"/";
                        }else {
                            s=s+""+(month+1)+"/";
                        }
                        if (dayOfMonth<10){
                            s=s+"0"+dayOfMonth;
                        }else {
                            s=s+""+dayOfMonth;
                        }
                        date=s;
                        mT1Txt2.setText(date);
                    }
                });
                break;
            case R.id.t1_btn1:
                mBaseList2.clear();
                for (int i = 0; i < mBaseList.size(); i++) {
                    String timeItem=mBaseList.get(i).getB6();
                    if (timeItem.equals(date)) {
                        mBaseList2.add(mBaseList.get(i));
                    }
                }
                mBaseAdapter.setData(mBaseList2,5);
                break;
        }
    }
}