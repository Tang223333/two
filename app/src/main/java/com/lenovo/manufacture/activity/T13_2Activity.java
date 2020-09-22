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
import com.lenovo.manufacture.data.Ry;
import com.lenovo.manufacture.data.T13Adapter;
import com.lenovo.manufacture.data.Xsyg;
import com.lenovo.manufacture.data.Xsygzmrz;
import com.lenovo.manufacture.utils.DateUtils;
import com.lenovo.manufacture.utils.MyOk;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class T13_2Activity extends AppCompatActivity implements View.OnClickListener {

    private ProgressDialog mProgressDialog;
    private List<Base> mBaseList = new ArrayList<>();
    private RecyclerView mT7Rv;
    private Button mT132Fh;
    private T13Adapter mT13Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t13_2);
        initView();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setTitle("数据（加载/处理）中...");
        initPost();
    }

    private void initPost() {
        if (mProgressDialog != null) {
            mProgressDialog.show();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                Xsygzmrz xsygzmrz= MyOk.getT("dataInterface/UserPeopleLog/getAll",new FormBody.Builder(),Xsygzmrz.class);
                Ry ry=MyOk.getT("dataInterface/People/getAll",new FormBody.Builder(),Ry.class);
                if (xsygzmrz==null || ry==null){
                    Toast.makeText(T13_2Activity.this, "网络有问题", Toast.LENGTH_SHORT).show();
                }else {
                    mBaseList.clear();
                    for (int i = 0; i < xsygzmrz.getData().size(); i++) {
                        Base base=new Base();
                        base.setB6(""+xsygzmrz.getData().get(i).getId());
                        base.setTF(false);
                        for (int j = 0; j < ry.getData().size(); j++) {
                            String userPeopleId = xsygzmrz.getData().get(i).getUserPeopleId();
                            if ((userPeopleId+"").equals("null")){
                                base.setB1("null");
                                base.setB2("null");
                                base.setB3("null");
                                base.setB4("null");
                                base.setB5("删除");
                                continue;
                            }
                            if (Integer.valueOf(userPeopleId)==ry.getData().get(j).getId()){
                                base.setB1(""+ DateUtils.getYearToM(Integer.valueOf(xsygzmrz.getData().get(i).getTime())));
                                base.setB2(""+ry.getData().get(j).getPeopleName());
                                base.setB3(""+ry.getData().get(j).getContent());
                                base.setB4(""+ry.getData().get(j).getGold());
                                base.setB5("删除");
                                continue;
                            }
                        }
                        mBaseList.add(base);
                    }
                }
                MyApp.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
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
        mT7Rv = (RecyclerView) findViewById(R.id.t7_rv);
        mT132Fh = (Button) findViewById(R.id.t13_2_fh);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mT7Rv.setLayoutManager(linearLayoutManager);
        mT13Adapter = new T13Adapter(new T13Adapter.Click() {
            @Override
            public void OnClick(int index) {
                DeletePost(index);
            }
        });
        mT7Rv.setAdapter(mT13Adapter);

        mT132Fh.setOnClickListener(this);
    }

    private void DeletePost(int index) {
        if (mProgressDialog != null) {
            mProgressDialog.show();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                String id = MyOk.getString("dataInterface/UserPeopleLog/delete", new FormBody.Builder().add("id", mBaseList.get(index).getB6()));
                MyApp.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        if (id==null){
                            Toast.makeText(T13_2Activity.this, "网络有问题", Toast.LENGTH_SHORT).show();
                        }else {
                            try {
                                JSONObject jsonObject=new JSONObject(id);
                                Toast.makeText(T13_2Activity.this, ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                initPost();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.t13_2_fh:
                finish();
                break;
        }
    }
}