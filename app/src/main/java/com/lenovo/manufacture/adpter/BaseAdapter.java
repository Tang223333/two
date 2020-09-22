package com.lenovo.manufacture.adpter;

import android.app.NotificationChannel;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.data.Base;

import java.util.ArrayList;
import java.util.List;

public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.BaseHolder> {

    private List<Base> mBaseList=new ArrayList<>();
    private int mSize;

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_base, parent, false);
        return new BaseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder holder, int position) {
        Base base = mBaseList.get(position);
        holder.mItemBaseT1.setText(""+base.getB1());
        holder.mItemBaseT2.setText(""+base.getB2());
        holder.mItemBaseT3.setText(""+base.getB3());
        holder.mItemBaseT4.setText(""+base.getB4());
        holder.mItemBaseT5.setText(""+base.getB5());
        holder.mItemBaseT6.setText(""+base.getB6());
        holder.mItemBaseT7.setText(""+base.getB7());
        holder.mItemBaseT8.setText(""+base.getB8());
        holder.mItemBaseT9.setText(""+base.getB9());
        holder.mItemBaseT10.setText(""+base.getB10());
        switch (base.getColor()){
            case 3:
                holder.mLinearLayout.setBackgroundColor(Color.WHITE);
                break;
            case 0:
                holder.mLinearLayout.setBackgroundColor(Color.RED);
                break;
            case 1:
                holder.mLinearLayout.setBackgroundColor(Color.BLUE);
                break;
            case 2:
                holder.mLinearLayout.setBackgroundColor(Color.GREEN);
                break;
        }
        if (mCLick != null) {
            holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCLick.OnClick(position);
                }
            });
            holder.mLinearLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mCLick.OnLongClick(position);
                    return true;
                }
            });
        }
        if (mSize<10){
            holder.mItemBaseT10.setVisibility(View.GONE);
        }
        if (mSize<9){
            holder.mItemBaseT9.setVisibility(View.GONE);
        }
        if (mSize<8){
            holder.mItemBaseT8.setVisibility(View.GONE);
        }
        if (mSize<7){
            holder.mItemBaseT7.setVisibility(View.GONE);
        }
        if (mSize<6){
            holder.mItemBaseT6.setVisibility(View.GONE);
        }
        if (mSize<5){
            holder.mItemBaseT5.setVisibility(View.GONE);
        }
        if (mSize<4){
            holder.mItemBaseT4.setVisibility(View.GONE);
        }
        if (mSize<3){
            holder.mItemBaseT3.setVisibility(View.GONE);
        }
        if (mSize<2){
            holder.mItemBaseT2.setVisibility(View.GONE);
        }
        if (mSize<1){
            holder.mItemBaseT1.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mBaseList.size();
    }


    public class BaseHolder extends RecyclerView.ViewHolder {
        private LinearLayout mLinearLayout;
        private TextView mItemBaseT1;
        private TextView mItemBaseT2;
        private TextView mItemBaseT3;
        private TextView mItemBaseT4;
        private TextView mItemBaseT5;
        private TextView mItemBaseT6;
        private TextView mItemBaseT7;
        private TextView mItemBaseT8;
        private TextView mItemBaseT9;
        private TextView mItemBaseT10;
        public BaseHolder(@NonNull View itemView) {
            super(itemView);
            mLinearLayout=itemView.findViewById(R.id.item_base_ll);
            mItemBaseT1 = itemView.findViewById(R.id.item_base_t1);
            mItemBaseT2 = itemView.findViewById(R.id.item_base_t2);
            mItemBaseT3 = itemView.findViewById(R.id.item_base_t3);
            mItemBaseT4 = itemView.findViewById(R.id.item_base_t4);
            mItemBaseT5 = itemView.findViewById(R.id.item_base_t5);
            mItemBaseT6 = itemView.findViewById(R.id.item_base_t6);
            mItemBaseT7 = itemView.findViewById(R.id.item_base_t7);
            mItemBaseT8 = itemView.findViewById(R.id.item_base_t8);
            mItemBaseT9 = itemView.findViewById(R.id.item_base_t9);
            mItemBaseT10 = itemView.findViewById(R.id.item_base_t10);
        }

    }

    public void setData(List<Base> bases,int size){
        mSize=size;
        mBaseList.clear();
        mBaseList.addAll(bases);
        Log.d("TAG", "setData: "+bases.size());
        notifyDataSetChanged();
    }

    public BaseAdapter(CLick cLick){
        mCLick=cLick;
    }

    public BaseAdapter(){

    }

    private CLick mCLick=null;

    public interface CLick{
        void OnClick(int index);
        void OnLongClick(int index);
    }
}
