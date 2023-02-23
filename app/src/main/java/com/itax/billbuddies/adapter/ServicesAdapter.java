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

import com.bumptech.glide.Glide;
import com.itax.billbuddies.listener.ClickListener;
import com.itax.billbuddies.models.ListItem;
import com.itax.billbuddies.R;
import com.itax.billbuddies.models.ServicesModel;

import java.util.ArrayList;


public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder>{
    Context context;
    ClickListener listener;
    ArrayList<ServicesModel.ServicesItem> itemList;

    public ServicesAdapter(Context context, ArrayList<ServicesModel.ServicesItem> itemList, ClickListener listener) {
        this.context = context;
        this.itemList = itemList;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_our_services, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {
        ServicesModel.ServicesItem item = itemList.get(position);
        if (item != null){
            holder.title_txt.setText(item.serviceName);
        }

        Glide.with(context).load(item.iconCompleteURL).error(R.drawable.img_empty).into(holder.icon_image);
        holder.parent_layout.setOnClickListener(v->{
            listener.onClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon_image;
        public TextView title_txt;
        public LinearLayout parent_layout;
        public ViewHolder(View itemView) {
            super(itemView);
            icon_image = itemView.findViewById(R.id.img_service);
            title_txt = itemView.findViewById(R.id.txt_title);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}


