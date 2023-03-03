package com.itax.billbuddies.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.itax.billbuddies.R;
import com.itax.billbuddies.listener.ClickListener;
import com.itax.billbuddies.models.SupplierModel;

import java.util.ArrayList;

public class SupplierAdapter  extends RecyclerView.Adapter<SupplierAdapter.ViewHolder>{
    Context context;
    ClickListener listener;
    ArrayList<SupplierModel.Supplier> itemList  = new ArrayList<>();

    public SupplierAdapter(Context context, ArrayList<SupplierModel.Supplier> itemList, ClickListener listener) {
        this.context = context;
        this.itemList = itemList;
        this.listener = listener;
    }

    @Override
    public SupplierAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer, parent, false);
        return new SupplierAdapter.ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull SupplierAdapter.ViewHolder holder, int position) {
        SupplierModel.Supplier item = itemList.get(position);
        if (item != null){
            holder.name.setText(item.fname);
            holder.pan_no.setText(item.pan);
            holder.address.setText(item.address);
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
        public TextView name,pan_no,address;
        public LinearLayout parent_layout;
        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_name);
            pan_no = itemView.findViewById(R.id.txt_pan_no);
            address = itemView.findViewById(R.id.txt_addres);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}

