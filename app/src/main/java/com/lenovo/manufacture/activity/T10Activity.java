package com.lenovo.manufacture.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lenovo.manufacture.MyApp;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.adpter.T10Adapter;
import com.lenovo.manufacture.data.Base;
import com.lenovo.manufacture.data.Xsbl;
import com.lenovo.manufacture.data.Xsscx;
import com.lenovo.manufacture.data.Ycl;
import com.lenovo.manufacture.utils.MyOk;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class T10Activity extends AppCompatActivity implements View.OnClickListener {

    private ExpandableListView mT10Elv;
    private FloatingActionButton mT10Btn;
    private T10Adapter mT10Adapter;
    private List<Base> mBaseParentList=new ArrayList<>();
    private List<List<Base>> mBaseChildList=new ArrayList<>();
    private List<Base> mBaseList=new ArrayList<>();
    private ProgressDialog mProgressDialog;
    private FormBody.Builder mBuilder;
    private List<String> mSp1=new ArrayList<>();
    private List<String> mSp2=new ArrayList<>();
    private Ycl mYcl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t10);
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
                mBaseParentList.clear();
                mBaseChildList.clear();
                mSp1.clear();
                mSp2.clear();
                Xsscx xsscx= MyOk.getT("dataInterface/UserProductionLine/getAll",new FormBody.Builder(),Xsscx.class);
                mYcl = MyOk.getT("dataInterface/Part/getAll",new FormBody.Builder(),Ycl.class);
                if (xsscx==null || mYcl ==null){
                    MyApp.getPostError(T10Activity.this);
                }else {
                    for (int i = 0; i < mYcl.getData().size(); i++) {
                        mSp2.add(mYcl.getData().get(i).getPartName());
                    }
                    for (int i = 0; i < xsscx.getData().size(); i++) {
                        Xsscx.DataBean dataBean = xsscx.getData().get(i);
                        mSp1.add(dataBean.getId()+"");
                        Base base=new Base();
                        base.setB1(""+dataBean.getId());
                        int isAI = dataBean.getIsAI();
                        if (isAI==1){
                            base.setB2("AI生产线");
                        }else {
                            base.setB2("不是AI生产线");
                        }
                        mBaseParentList.add(base);
                        mBuilder = new FormBody.Builder().add("userProductionLineId",dataBean.getId()+"");
                        Xsbl xsbl=MyOk.getT("dataInterface/UserParts/search",mBuilder,Xsbl.class);
                        if (xsbl != null) {
                            mBaseList=new ArrayList<>();
                            for (int j = 0; j < xsbl.getData().size(); j++) {
                                base=new Base();
                                for (int k = 0; k < mYcl.getData().size(); k++) {
                                    if (xsbl.getData().get(j).getPartId()== mYcl.getData().get(k).getId()){
                                        Ycl.DataBean dataBean1 = mYcl.getData().get(k);
                                        base.setB1(""+dataBean1.getPartName());
                                        base.setB2(""+xsbl.getData().get(j).getNum());
                                        base.setB3(""+dataBean1.getArea());
                                        base.setB4(""+dataBean1.getIcon());
                                    }
                                }
                                mBaseList.add(base);
                            }
                            mBaseChildList.add(mBaseList);
                        }else {
                            MyApp.getPostError(T10Activity.this);
                        }
                    }
                }
                MyApp.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        mT10Adapter.setData(mBaseParentList,mBaseChildList);
                        for (int i = 0; i < mBaseParentList.size(); i++) {
                            mT10Elv.expandGroup(i);
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
        mT10Elv = (ExpandableListView) findViewById(R.id.t10_elv);
        mT10Btn = (FloatingActionButton) findViewById(R.id.t10_btn);

        mT10Adapter = new T10Adapter(new T10Adapter.Click() {
            @Override
            public void OnClick(int parentIndex, int ChildIndex) {
                Dialog dialog=new Dialog(T10Activity.this);
                ImageView imageView=new ImageView(T10Activity.this);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(200,200));
                Glide.with(T10Activity.this).load("file:///android_asset/"+mBaseChildList.get(parentIndex).get(ChildIndex).getB4()+".png").into(imageView);
                dialog.setContentView(imageView);
                dialog.show();
            }
        });
        mT10Elv.setAdapter(mT10Adapter);

        mT10Btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.t10_btn:
                Dialog dialog=new Dialog(T10Activity.this);
                dialog.setContentView(R.layout.dialog_t10);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0x00000000));
                Spinner mDialogT10Sp1 = dialog.findViewById(R.id.dialog_t10_sp1);
                Spinner mDialogT10Sp2 = dialog.findViewById(R.id.dialog_t10_sp2);
                Spinner mDialogT10Sp3 = dialog.findViewById(R.id.dialog_t10_sp3);
                Button mDialogT10Btn1 = dialog.findViewById(R.id.dialog_t10_btn1);
                Button mDialogT10Btn2 = dialog.findViewById(R.id.dialog_t10_btn2);
                ArrayAdapter arrayAdapter1=new ArrayAdapter(T10Activity.this,R.layout.support_simple_spinner_dropdown_item,mSp1);
                mDialogT10Sp1.setAdapter(arrayAdapter1);
                ArrayAdapter arrayAdapter2=new ArrayAdapter(T10Activity.this,R.layout.support_simple_spinner_dropdown_item,mSp2);
                mDialogT10Sp2.setAdapter(arrayAdapter2);
                mDialogT10Btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                mDialogT10Btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mBuilder=new FormBody.Builder().add("userWorkId","1").add("userProductionLineId",mSp1.get(mDialogT10Sp1.getSelectedItemPosition()))
                                .add("partId",mYcl.getData().get(mDialogT10Sp2.getSelectedItemPosition()).getId()+"").add("num",(mDialogT10Sp3.getSelectedItemPosition()+1)+"");
                        CreatePost();
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
        }
    }

    private void CreatePost() {
        if (mProgressDialog != null) {
            mProgressDialog.show();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                Xsbl xsbl=MyOk.getT("dataInterface/UserParts/create",mBuilder,Xsbl.class);
                MyApp.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        if (xsbl==null){
                            Toast.makeText(T10Activity.this, "网络有问题", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(T10Activity.this, ""+xsbl.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        initPost();
                    }
                });
            }
        }).start();
    }
}