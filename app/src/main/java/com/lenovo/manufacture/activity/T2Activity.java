package com.lenovo.manufacture.activity;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.lenovo.manufacture.MyApp;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.data.Hgl;
import com.lenovo.manufacture.utils.CarUtils;
import com.lenovo.manufacture.utils.MyOk;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;

public class T2Activity extends AppCompatActivity {

    private PieChart mT2Pie;
    private BarChart mT2Bar;
    private ProgressDialog mProgressDialog;
    private List<PieEntry> mPieEntryList=new ArrayList<>();
    private List<BarEntry> mBarEntryList=new ArrayList<>();
    private List<String> carName=new ArrayList<>();
    private Hgl mHgl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t2);
        mProgressDialog=new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setTitle("数据（加载/处理）中...");
        carName.clear();
        carName.add("轿车汽车标准型");
        carName.add("MPV汽车标准型");
        carName.add("SUV汽车标准型");
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
                mHgl = MyOk.getT("dataInterface/PassRate/getAll",new FormBody.Builder(),Hgl.class);
                MyApp.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        if (mHgl==null){
                            Toast.makeText(T2Activity.this, "网络有问题", Toast.LENGTH_SHORT).show();
                        }else {
                            mPieEntryList.clear();
                            mBarEntryList.clear();
                            for (int i = 0; i < mHgl.getData().size(); i++) {
                                Hgl.DataBean dataBean = mHgl.getData().get(i);
                                mBarEntryList.add(new BarEntry(i,dataBean.getRate()));
                                mPieEntryList.add(new PieEntry(dataBean.getRate(), CarUtils.getCarName(dataBean.getCarId())));
                            }
                            initPie();
                            initBar();
                        }
                        if (mProgressDialog.isShowing()) {
                            mProgressDialog.dismiss();
                        }
                    }
                });
            }
        }).start();
    }

    private void initBar() {
        Log.d("TAG", "initBar: "+mBarEntryList.size());
//        mT2Bar.setExtraOffsets(50,50,50,50);
        Legend legend = mT2Bar.getLegend();
        legend.setEnabled(false);
        Description description = mT2Bar.getDescription();
        description.setEnabled(false);
//
        XAxis xAxis = mT2Bar.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(3);
        xAxis.setAxisMinimum(-1);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                int carid= (int) value;
                if (carid>=0&&carid<carName.size()){
                    return carName.get(carid);
                }else {
                    return "";
                }
            }
        });

        YAxis yAxisR=mT2Bar.getAxisRight();
        yAxisR.setEnabled(false);

        YAxis yAxisL=mT2Bar.getAxisLeft();
        yAxisL.setAxisMinimum(0);
        yAxisL.setAxisMaximum(100);
        yAxisL.enableGridDashedLine(5,5,5);
        yAxisL.setLabelCount(6);
        yAxisL.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return super.getAxisLabel(value, axis)+"%";
            }
        });
        LimitLine limitLine1=new LimitLine(70,"警戒线");
        LimitLine limitLine2=new LimitLine(70,"70%");
        limitLine1.setTextColor(Color.RED);
        limitLine1.setLineColor(Color.RED);
        limitLine2.setTextColor(Color.RED);
        limitLine2.setLineColor(Color.RED);
        limitLine1.enableDashedLine(5,5,5);
        limitLine2.enableDashedLine(5,5,5);
        limitLine2.setYOffset(-5);
        limitLine2.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_BOTTOM);
        yAxisL.addLimitLine(limitLine1);
        yAxisL.addLimitLine(limitLine2);

        BarDataSet barDataSet=new BarDataSet(mBarEntryList,"");
        barDataSet.setColors(Color.parseColor("#7ECF51"),Color.parseColor("#EECB5F"),Color.parseColor("#E3935D"));
        barDataSet.setValueTextColor(0x00000000);
        BarData barData=new BarData(barDataSet);
        mT2Bar.setData(barData);
        mT2Bar.invalidate();
    }

    private void initPie() {
        mT2Pie.setExtraOffsets(50,50,50,50);
        mT2Pie.setHoleRadius(0);
        mT2Pie.setDrawHoleEnabled(false);
        mT2Pie.setHoleColor(0x00000000);
        mT2Pie.setCenterTextColor(0x00000000);
        mT2Pie.setEntryLabelColor(0x00000000);
        mT2Pie.getDescription().setEnabled(false);
        Legend legend = mT2Pie.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setYOffset(10);
        legend.setXOffset(-50);

        PieDataSet pieDataSet=new PieDataSet(mPieEntryList,"");
        pieDataSet.setColors(Color.parseColor("#7ECF51"),Color.parseColor("#EECB5F"),Color.parseColor("#E3935D"));
        pieDataSet.setValueTextColor(0x00000000);
        PieData pieData=new PieData(pieDataSet);
        mT2Pie.setData(pieData);
        mT2Pie.invalidate();
    }

    private void initView() {
        mT2Pie = findViewById(R.id.t2_pie);
        mT2Bar = findViewById(R.id.t2_bar);
    }
}