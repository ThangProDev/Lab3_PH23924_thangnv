package com.example.lab3_ph23924.Bai1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.lab3_ph23924.R;

import java.util.ArrayList;

public class ContractAdapter extends BaseAdapter {
    Context context;
    ArrayList<Contract> contractArrayList;

    public ContractAdapter(Context context, ArrayList<Contract> contractArrayList) {
        this.context = context;
        this.contractArrayList = contractArrayList;
    }

    @Override
    public int getCount() {
        return contractArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return contractArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        ViewHolder viewHolder;

        if (view == null){
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.list_item, null);


            viewHolder.txtName = (TextView) view.findViewById(R.id.tv_name);
            viewHolder.txtEmail = (TextView) view.findViewById(R.id.tv_email);
            viewHolder.txtMobile = (TextView) view.findViewById(R.id.tv_mobile);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Contract contract = contractArrayList.get(i);
        viewHolder.txtName.setText(contract.getName());
        viewHolder.txtMobile.setText(contract.getMobile());
        viewHolder.txtEmail.setText(contract.getEmail());
        return view;
    }
    static class ViewHolder {
        TextView txtName;
        TextView txtEmail;
        TextView txtMobile;
    }
}
