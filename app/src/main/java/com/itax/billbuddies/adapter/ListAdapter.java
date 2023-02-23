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
import com.itax.billbuddies.models.ListItem;
import com.itax.billbuddies.R;

import java.util.ArrayList;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{
    Context context;
    ClickListener listener;
    ArrayList<ListItem> itemList;

    public ListAdapter(Context context, ArrayList<ListItem> itemList, ClickListener listener) {
        this.context = context;
        this.itemList = itemList;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {
        ListItem item = itemList.get(position);
        if (item != null){
            holder.title_txt.setText(item.title);
        }

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
            title_txt = itemView.findViewById(R.id.txt_title);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}


