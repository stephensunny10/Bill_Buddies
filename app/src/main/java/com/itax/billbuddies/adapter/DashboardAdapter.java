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
import com.itax.billbuddies.models.DashboardItem;
import com.itax.billbuddies.R;
import java.util.ArrayList;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder>{
    Context context;
    ClickListener listener;
    ArrayList<DashboardItem> itemList;

    public DashboardAdapter(Context context, ArrayList<DashboardItem> itemList, ClickListener listener) {
        this.context = context;
        this.itemList = itemList;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dashboard, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {
        DashboardItem item = itemList.get(position);

        if (item.amount != null){
            holder.amount_txt.setText( item.amount);
        }
        if (item.title != null){
            holder.title_txt.setText(item.title);
        }
        if (item.icon!=0) {
            holder.icon_image.setImageResource(item.icon);
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon_image;
        public TextView amount_txt,title_txt;
        public LinearLayout parent_layout;
        public ViewHolder(View itemView) {
            super(itemView);
            amount_txt = itemView.findViewById(R.id.txt_amount);
            title_txt = itemView.findViewById(R.id.txt_title);
            icon_image = itemView.findViewById(R.id.image);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}

