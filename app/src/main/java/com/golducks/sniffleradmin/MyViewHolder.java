package com.golducks.sniffleradmin;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by DELL on 8/28/2016.
 */
public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView tvName,tvNumber,tvMessage,tvTime;

    public MyViewHolder(View itemView) {
        super(itemView);
        tvName = (TextView) itemView.findViewById(R.id.tvName);
        tvNumber = (TextView) itemView.findViewById(R.id.tvNumber);
        tvMessage = (TextView) itemView.findViewById(R.id.tvMessage);
        tvTime = (TextView) itemView.findViewById(R.id.tvTime);
    }
}
