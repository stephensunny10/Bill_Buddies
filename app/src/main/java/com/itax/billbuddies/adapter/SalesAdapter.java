package com.itax.billbuddies.adapter;


import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.itax.billbuddies.listener.ClickListener;
import com.itax.billbuddies.models.SalesItem;
import com.itax.billbuddies.R;

import java.util.ArrayList;


public class SalesAdapter extends RecyclerView.Adapter<SalesAdapter.ViewHolder>{
    Context context;
    ClickListener listener;
    ArrayList<SalesItem> itemList;

    public SalesAdapter(Context context, ArrayList<SalesItem> itemList, ClickListener listener) {
        this.context = context;
        this.itemList = itemList;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sales, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {
        SalesItem item = itemList.get(position);
        if (item != null){
            holder.sales_date_txt.setText(item.sales_date);
            holder.sales_invoice_txt.setText(item.invoice_id);
            holder.sales_status_txt.setText(item.status);
            holder.ref_no_txt.setText(item.reference_no);
            holder.total_txt.setText(item.total);
            holder.paid_txt.setText(item.total_paid_amount);
            holder.payment_status_txt.setText(item.status);
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon_image;
        public TextView sales_date_txt,sales_invoice_txt,sales_status_txt,ref_no_txt,total_txt,paid_txt,payment_status_txt;
        public LinearLayout parent_layout;
        public ViewHolder(View itemView) {
            super(itemView);
            sales_date_txt = itemView.findViewById(R.id.txt_sales_date);
            sales_invoice_txt = itemView.findViewById(R.id.txt_sales_invoice);
            sales_status_txt = itemView.findViewById(R.id.txt_sales_status);
            ref_no_txt = itemView.findViewById(R.id.txt_ref_no);
            total_txt = itemView.findViewById(R.id.txt_total);
            paid_txt = itemView.findViewById(R.id.txt_paid);
            payment_status_txt = itemView.findViewById(R.id.txt_payment_status);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}

