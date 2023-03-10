package com.itax.billbuddies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itax.billbuddies.R;
import com.itax.billbuddies.listener.ClickListener;
import com.itax.billbuddies.models.CustomerModel;

import java.util.ArrayList;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder>{
    Context context;
    ClickListener listener;
    ArrayList<CustomerModel.CustomerItem> itemList;


    public CustomerAdapter(Context context, ArrayList<CustomerModel.CustomerItem> itemList, ClickListener listener) {
        this.context = context;
        this.itemList = itemList;
        this.listener = listener;
    }

    @Override
    public CustomerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer, parent, false);
        return new CustomerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.ViewHolder holder, int position) {
        CustomerModel.CustomerItem item = itemList.get(position);
        if (item.fname != null){
            holder.name.setText(item.fname);
        }
        if (item.pan != null){
            holder.pan.setText(item.pan);
        }
        if (item.address != null){
            holder.address.setText(item.address);
        }
        holder.parent_layout.setOnClickListener(v -> {
            listener.onClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name,pan,address;
        public LinearLayout parent_layout;
        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_name);
            pan = itemView.findViewById(R.id.txt_pan_no);
            address = itemView.findViewById(R.id.txt_address);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}
