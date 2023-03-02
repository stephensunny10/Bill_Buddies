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
import com.itax.billbuddies.models.PurchaseItem;
import com.itax.billbuddies.R;

import java.util.ArrayList;


public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.ViewHolder>{
    Context context;
    ClickListener listener;
    ArrayList<PurchaseItem> itemList;

    public PurchaseAdapter(Context context, ArrayList<PurchaseItem> itemList, ClickListener listener) {
        this.context = context;
        this.itemList = itemList;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_purchase, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {
        PurchaseItem item = itemList.get(position);
        if (item != null){
            holder.purchase_date_txt.setText(item.purchase_date);
            holder.purchase_invoice_txt.setText(item.invoice_id);
            holder.purchase_status_txt.setText(item.status);
            holder.ref_no_txt.setText(item.reference_no);
            holder.total_txt.setText(item.total);
            holder.paid_txt.setText(item.total_paid_amount);
            holder.dues_txt.setText(item.total_due_amount);
            holder.payment_status_txt.setText(item.status);
            holder.parent_layout.setOnClickListener(v -> {
                listener.onClick(position);
            });
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon_image;
        public TextView purchase_date_txt,purchase_invoice_txt,purchase_status_txt,ref_no_txt,total_txt,paid_txt,dues_txt,payment_status_txt;
        public LinearLayout parent_layout;
        public ViewHolder(View itemView) {
            super(itemView);
            purchase_date_txt = itemView.findViewById(R.id.txt_purchase_date);
            purchase_invoice_txt = itemView.findViewById(R.id.txt_purchase_invoice);
            purchase_status_txt = itemView.findViewById(R.id.txt_purchase_status);
            ref_no_txt = itemView.findViewById(R.id.txt_ref_no);
            total_txt = itemView.findViewById(R.id.txt_total);
            paid_txt = itemView.findViewById(R.id.txt_paid);
            dues_txt = itemView.findViewById(R.id.txt_dues);
            payment_status_txt = itemView.findViewById(R.id.txt_payment_status);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}
