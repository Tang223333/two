package com.lenovo.manufacture.adpter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.data.Base;

import java.util.ArrayList;
import java.util.List;

public class T11Adapter extends RecyclerView.Adapter<T11Adapter.ViewHolder> {

    private List<Base> mBaseList=new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_t11, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).load("file:///android_asset/"+mBaseList.get(position).getB1()+".png").into(holder.mItemT11Img);
        holder.mItemT11Txt.setText(""+mBaseList.get(position).getB2());
        holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCLick.OnClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mBaseList.size();
    }

    public void setData(List<Base> bases){
        mBaseList.clear();
        mBaseList.addAll(bases);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout mLinearLayout;
        private ImageView mItemT11Img;
        private TextView mItemT11Txt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mLinearLayout=itemView.findViewById(R.id.item_t11_ll);
            mItemT11Img = itemView.findViewById(R.id.item_t11_img);
            mItemT11Txt = itemView.findViewById(R.id.item_t11_txt);
        }
    }

    public T11Adapter(BaseAdapter.CLick cLick){
        mCLick=cLick;
    }

    public T11Adapter(){

    }

    private BaseAdapter.CLick mCLick=null;

    public interface CLick{
        void OnClick(int index);
    }
}
