package com.golducks.sniffleradmin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by DELL on 8/28/2016.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<model> arrayList;

    public MyRecyclerViewAdapter(Context context, ArrayList<model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.item, null);
        return new MyViewHolder(contentView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        model model = arrayList.get(i);
        ((MyViewHolder) viewHolder).tvName.setText(model.getName());
        ((MyViewHolder) viewHolder).tvNumber.setText(model.getNumber());
        ((MyViewHolder) viewHolder).tvMessage.setText(model.getMessage());
        ((MyViewHolder) viewHolder).tvTime.setText(model.getTime());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
