package com.lenovo.manufacture.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.data.Base;

import java.util.ArrayList;
import java.util.List;

public class T14Adapter extends RecyclerView.Adapter<T14Adapter.T14Holder> {

    private List<Base> mBaseList=new ArrayList<>();

    @NonNull
    @Override
    public T14Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_t14, parent, false);
        return new T14Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull T14Holder holder, int position) {
        Base base = mBaseList.get(position);
        holder.mItemT14Txt1.setText(""+base.getB1());
        holder.mItemT14Txt2.setText(""+base.getB2());
        holder.mItemT14Txt3.setText(""+base.getB3());
        holder.mItemT14Txt4.setText(""+base.getB4());
        holder.mItemT14Cb.setChecked(base.isTF());
        holder.mItemT14Cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClick.OnClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBaseList.size();
    }

    public void setData(List<Base> bases) {
        mBaseList.clear();
        mBaseList.addAll(bases);
        notifyDataSetChanged();
    }

    public class T14Holder extends RecyclerView.ViewHolder {
        private TextView mItemT14Txt1;
        private TextView mItemT14Txt2;
        private TextView mItemT14Txt3;
        private TextView mItemT14Txt4;
        private CheckBox mItemT14Cb;
        public T14Holder(@NonNull View itemView) {
            super(itemView);
            mItemT14Txt1 = itemView.findViewById(R.id.item_t14_txt1);
            mItemT14Txt2 = itemView.findViewById(R.id.item_t14_txt2);
            mItemT14Txt3 = itemView.findViewById(R.id.item_t14_txt3);
            mItemT14Txt4 = itemView.findViewById(R.id.item_t14_txt4);
            mItemT14Cb = itemView.findViewById(R.id.item_t14_cb);
        }
    }

    public T14Adapter(Click click){
        mClick=click;
    }

    private Click mClick=null;

    public interface Click{
        void OnClick(int index);
    }
}
