package com.lenovo.manufacture.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lenovo.manufacture.MyApp;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.data.Base;
import com.lenovo.manufacture.data.Ry;
import com.lenovo.manufacture.data.T13Adapter;
import com.lenovo.manufacture.data.Xsscx;
import com.lenovo.manufacture.data.Xsyg;
import com.lenovo.manufacture.data.Xsygzmrz;
import com.lenovo.manufacture.utils.MyOk;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.FormBody;

public class T13Activity extends AppCompatActivity implements View.OnClickListener {

    private Spinner mT13Sp;
    private Button mT13Btn;
    private RecyclerView mT7Rv;
    private T13Adapter mT13Adapter;
    private ProgressDialog mProgressDialog;
    private List<Base> mBaseList=new ArrayList<>();
    private List<String> mSpList=new ArrayList<>();
    private Xsscx mXsscx;
    private FormBody.Builder mBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t13);
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
                mXsscx = MyOk.getT("dataInterface/UserProductionLine/getAll",new FormBody.Builder(),Xsscx.class);
                Ry ry=MyOk.getT("dataInterface/People/getAll",new FormBody.Builder(),Ry.class);
                Xsyg xsyg=MyOk.getT("dataInterface/UserPeople/getAll",new FormBody.Builder(),Xsyg.class);
                if (mXsscx ==null){
                    MyApp.getPostError(T13Activity.this);
                }else {
                    mSpList.clear();
                    mBaseList.clear();
                    for (int i = 0; i < mXsscx.getData().size(); i++) {
                        mSpList.add((mXsscx.getData().get(i).getProductionLineId()+1)+"");
                    }
                    for (int i = 0; i < ry.getData().size(); i++) {
                        Ry.DataBean dataBean = ry.getData().get(i);
                        Base base=new Base();
                        base.setB1(dataBean.getId()+"");
                        base.setB2(dataBean.getPeopleName()+"");
                        base.setB3(dataBean.getContent()+"");
                        base.setB4(dataBean.getGold()+"");
                        base.setB5("招聘");
                        base.setTF(false);
                        mBaseList.add(base);
                    }
                    for (int i = 0; i < xsyg.getData().size(); i++) {
                        for (int j = 0; j < mBaseList.size(); j++) {
                            if (xsyg.getData().get(i).getPeopleId()==Integer.valueOf(mBaseList.get(j).getB1())){
                                mBaseList.get(j).setB5("已招娉");
                                mBaseList.get(j).setTF(true);
                                continue;
                            }
                        }
                    }
                }
                MyApp.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        ArrayAdapter arrayAdapter=new ArrayAdapter(T13Activity.this,R.layout.support_simple_spinner_dropdown_item,mSpList);
                        mT13Sp.setAdapter(arrayAdapter);
                        mT13Adapter.setData(mBaseList);
                        if (mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                    }
                });
            }
        }).start();
    }

    private void initView() {
        mT13Sp = (Spinner) findViewById(R.id.t13_sp);
        mT13Btn = (Button) findViewById(R.id.t13_btn);
        mT7Rv = (RecyclerView) findViewById(R.id.t7_rv);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mT7Rv.setLayoutManager(linearLayoutManager);
        mT13Adapter = new T13Adapter(new T13Adapter.Click() {
            @Override
            public void OnClick(int index) {
                ZpPost(index);
            }
        });
        mT7Rv.setAdapter(mT13Adapter);

        mT13Btn.setOnClickListener(this);
    }

    private void ZpPost(int index) {
        if (mProgressDialog != null) {
            mProgressDialog.show();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                mBuilder = new FormBody.Builder().add("userWorkId","1").add("power","100")
                        .add("peopleId",""+mBaseList.get(index).getB1()).add("userProductionLineId",mXsscx.getData().get(mT13Sp.getSelectedItemPosition()).getId()+"");
                Xsyg xsyg=MyOk.getT("dataInterface/UserPeople/create",mBuilder,Xsyg.class);
                long l=new Date().getTime();
                l=l/1000;
                int time= (int) l;
                mBuilder=new FormBody.Builder().add("userWorkId","1").add("userPeopleId",""+mBaseList.get(index).getB1()).add("time",time+"");
                Xsygzmrz xsygzmrz=MyOk.getT("dataInterface/UserPeopleLog/create",mBuilder,Xsygzmrz.class);
                if (xsyg==null || xsygzmrz==null){
                    MyApp.getPostError(T13Activity.this);
                }
                MyApp.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(T13Activity.this, ""+xsygzmrz.getMessage(), Toast.LENGTH_SHORT).show();
                        initPost();
                    }
                });
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.t13_btn:
                Intent intent=new Intent(T13Activity.this,T13_2Activity.class);
                startActivity(intent);
                break;
        }
    }
}