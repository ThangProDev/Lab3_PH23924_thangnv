package com.example.lab3_ph23924.Bai3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.lab3_ph23924.R;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<AndroidVersion> android;
    public DataAdapter(ArrayList<AndroidVersion> android) {
        this.android = android;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( DataAdapter.ViewHolder holder, int i) {
        holder.tv_name.setText(android.get(i).getName());
        holder.tv_version.setText(android.get(i).getVer());
        holder.tv_api_level.setText(android.get(i).getApi());

    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name,tv_version,tv_api_level;
        public ViewHolder( View itemView) {
            super(itemView);
            tv_name = (TextView)itemView.findViewById(R.id.tv_name);
            tv_version = (TextView)itemView.findViewById(R.id.tv_version);
            tv_api_level = (TextView)itemView.findViewById(R.id.tv_api_level);
        }
    }
}
