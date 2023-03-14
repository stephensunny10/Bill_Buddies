package com.itax.billbuddies.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.itax.billbuddies.api.ApiList;
import com.itax.billbuddies.listener.ClickListener;
import com.itax.billbuddies.models.ItemModel;
import com.itax.billbuddies.R;

import java.util.ArrayList;

public class ItemProductsAdapter extends RecyclerView.Adapter<ItemProductsAdapter.ViewHolder>{
    Context context;
    ClickListener listener;
    ArrayList<ItemModel.Item> itemList;

    public ItemProductsAdapter(Context context, ArrayList<ItemModel.Item> itemList, ClickListener listener) {
        this.context = context;
        this.itemList = itemList;
        this.listener = listener;
    }

    @Override
    public ItemProductsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ItemProductsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemProductsAdapter.ViewHolder holder, int position) {
        ItemModel.Item item = itemList.get(position);

        if (item != null){
            holder.sku_txt.setText(item.sku);
            holder.hsn_txt.setText(item.hsn);
            holder.item_name_txt.setText(item.product_name);
            holder.brand_txt.setText(item.brand_name);
            holder.category_txt.setText(item.category_name);
            holder.inventory_txt.setText(item.sku);
            holder.unit_txt.setText("");
            holder.price_txt.setText(item.actual_price);
            //holder.purchase_price_txt.setText(item.selling_price);
            holder.selling_price_txt.setText(item.selling_price);

            Glide.with(context).load(ApiList.BILL_BUDDIES_IMAGE_URL + item.main_image).error(R.drawable.img_empty).into(holder.product_image);
            
            holder.parent_layout.setOnClickListener(v->{
                listener.onClick(position);
            });
        }

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView product_image;
        public TextView sku_txt,hsn_txt,item_name_txt,brand_txt,category_txt,inventory_txt,unit_txt,price_txt,purchase_price_txt,selling_price_txt;
        public RelativeLayout parent_layout;
        public ViewHolder(View itemView) {
            super(itemView);
            sku_txt = itemView.findViewById(R.id.txt_sku);
            hsn_txt = itemView.findViewById(R.id.txt_hsn);
            item_name_txt = itemView.findViewById(R.id.txt_item_name);
            brand_txt = itemView.findViewById(R.id.txt_brand);
            category_txt = itemView.findViewById(R.id.txt_category);
            inventory_txt = itemView.findViewById(R.id.txt_inventory);
            unit_txt = itemView.findViewById(R.id.txt_unit);
            price_txt = itemView.findViewById(R.id.txt_price);
            purchase_price_txt = itemView.findViewById(R.id.txt_purchase_price);
            selling_price_txt = itemView.findViewById(R.id.txt_selling_price);
            product_image = itemView.findViewById(R.id.img_product);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    public void updateList(ArrayList<ItemModel.Item> list){
        itemList = list;
        notifyDataSetChanged();
    }
}
