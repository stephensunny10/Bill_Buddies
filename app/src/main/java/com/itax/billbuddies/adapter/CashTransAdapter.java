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
import com.itax.billbuddies.models.CashTransModel;
import com.itax.billbuddies.R;

import java.util.ArrayList;

public class CashTransAdapter extends RecyclerView.Adapter<CashTransAdapter.ViewHolder>{
    Context context;
    ClickListener listener;
    ArrayList<CashTransModel.CashTransaction> itemList;

    public CashTransAdapter(Context context, ArrayList<CashTransModel.CashTransaction> itemList, ClickListener listener) {
        this.context = context;
        this.itemList = itemList;
        this.listener = listener;
    }

    @Override
    public CashTransAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cash_transaction, parent, false);
        return new CashTransAdapter.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(CashTransAdapter.ViewHolder holder, int position) {
        CashTransModel.CashTransaction item = itemList.get(position);

        if (item != null){
            holder.date_txt.setText(item.date);
            holder.reference_txt.setText(item.txn_reference);
            holder.debit_txt.setText("");
            holder.credit_txt.setText(item.amount);
            holder.balance_txt.setText(item.amount);
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon_image;
        public TextView date_txt,reference_txt,debit_txt,credit_txt,balance_txt;
        public LinearLayout parent_layout;
        public ViewHolder(View itemView) {
            super(itemView);
            date_txt = itemView.findViewById(R.id.txt_date);
            reference_txt = itemView.findViewById(R.id.txt_reference);
            debit_txt = itemView.findViewById(R.id.txt_debit);
            credit_txt = itemView.findViewById(R.id.txt_credit);
            balance_txt = itemView.findViewById(R.id.txt_balance);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}

