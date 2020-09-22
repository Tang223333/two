package com.lenovo.manufacture.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lenovo.manufacture.R;

import java.util.ArrayList;
import java.util.List;

public class T13Adapter extends RecyclerView.Adapter<T13Adapter.T13Holder> {

    private List<Base> mBaseList=new ArrayList<>();

    @NonNull
    @Override
    public T13Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_t13, parent, false);
        return new T13Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull T13Holder holder, int position) {
        Base base = mBaseList.get(position);
        holder.mItemT13Txt1.setText(""+base.getB1());
        holder.mItemT13Txt2.setText(""+base.getB2());
        holder.mItemT13Txt3.setText(""+base.getB3());
        holder.mItemT13Txt4.setText(""+base.getB4());
        holder.mItemT13Btn.setText(""+base.getB5());
        holder.mItemT13Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (base.isTF()) {
                    Toast.makeText(holder.itemView.getContext(), "该人员已被雇佣", Toast.LENGTH_SHORT).show();
                }else {
                    mClick.OnClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBaseList.size();
    }

    public class T13Holder extends RecyclerView.ViewHolder {
        private TextView mItemT13Txt1;
        private TextView mItemT13Txt2;
        private TextView mItemT13Txt3;
        private TextView mItemT13Txt4;
        private Button mItemT13Btn;
        public T13Holder(@NonNull View itemView) {
            super(itemView);
            mItemT13Txt1 = itemView.findViewById(R.id.item_t13_txt1);
            mItemT13Txt2 = itemView.findViewById(R.id.item_t13_txt2);
            mItemT13Txt3 = itemView.findViewById(R.id.item_t13_txt3);
            mItemT13Txt4 = itemView.findViewById(R.id.item_t13_txt4);
            mItemT13Btn = itemView.findViewById(R.id.item_t13_btn);
        }
    }


    public void setData(List<Base> bases){
        mBaseList.clear();
        mBaseList.addAll(bases);
        notifyDataSetChanged();
    }

    public T13Adapter(Click click){
        mClick=click;
    }

    private Click mClick=null;

    public interface Click{
        void OnClick(int index);
    }
}
