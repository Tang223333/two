package com.lenovo.manufacture.adpter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.data.Base;

import java.util.ArrayList;
import java.util.List;

public class T10Adapter extends BaseExpandableListAdapter {

    private List<Base> mBaseParentList=new ArrayList<>();
    private List<List<Base>> mBaseChildList=new ArrayList<>();


    @Override
    public int getGroupCount() {
        return mBaseParentList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mBaseChildList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mBaseParentList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mBaseChildList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_t10_p,parent,false);
        Base base = mBaseParentList.get(groupPosition);
        TextView mItemT10PTxt1 = view.findViewById(R.id.item_t10_p_txt1);
        TextView mItemT10PTxt2 = view.findViewById(R.id.item_t10_p_txt2);
        mItemT10PTxt1.setText("生产线:"+base.getB1());
        mItemT10PTxt2.setText(""+base.getB2());
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_t10_c,parent,false);
        Base base = mBaseChildList.get(groupPosition).get(childPosition);
        LinearLayout mItemT10CLL = view.findViewById(R.id.item_t10_c_ll);
        TextView mItemT10CTxt1 = view.findViewById(R.id.item_t10_c_txt1);
        TextView mItemT10CTxt2 = view.findViewById(R.id.item_t10_c_txt2);
        TextView mItemT10CTxt3 = view.findViewById(R.id.item_t10_c_txt3);
        mItemT10CTxt1.setText(""+base.getB1());
        mItemT10CTxt2.setText("数量:"+base.getB2());
        mItemT10CTxt3.setText("占地:"+base.getB3());
        mItemT10CLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClick.OnClick(groupPosition,childPosition);
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void setData(List<Base> baseParentList, List<List<Base>> baseChildList) {
        mBaseParentList.clear();
        mBaseChildList.clear();
        mBaseParentList.addAll(baseParentList);
        mBaseChildList.addAll(baseChildList);
        notifyDataSetChanged();
    }

    public T10Adapter(Click click){
        mClick=click;
    }

    private Click mClick=null;

    public interface Click{
        void OnClick(int parentIndex,int ChildIndex);
    }
}
