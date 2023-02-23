package com.itax.billbuddies.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.itax.billbuddies.R;
import com.itax.billbuddies.models.HsnItem;

import java.util.List;

public class HsnAdapter extends RecyclerView.Adapter<HsnAdapter.ViewHolder>{
    private static int ME=1;
    List<HsnItem> itemList;
    String testImage = "https://t4.ftcdn.net/jpg/02/08/93/47/360_F_208934723_tv3JlZKwlOhF1QiQdBruyaetwLRxTQCD.jpg";

    public HsnAdapter(List<HsnItem> itemList) {
        this.itemList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.list_item_hsn, parent, false);
        // ViewHolder viewHolder = new ViewHolder(listItem);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final HsnItem item = itemList.get(position);
        holder.product_txt.setText(item.getProductName());
        holder.rate_txt.setText(item.getRate());
        holder.hsn_code_txt.setText(item.getHsnCode());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView learn_img;
        public TextView product_txt,rate_txt,hsn_code_txt;
        public LinearLayout parent_layout;
        public ViewHolder(View itemView) {
            super(itemView);
            product_txt = itemView.findViewById(R.id.txt_product);
            rate_txt = itemView.findViewById(R.id.txt_rate);
            hsn_code_txt = itemView.findViewById(R.id.txt_hsn);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    // call this method to to update item
    public void updateList(List<HsnItem> list){
        itemList = list;
        notifyDataSetChanged();
    }
}
