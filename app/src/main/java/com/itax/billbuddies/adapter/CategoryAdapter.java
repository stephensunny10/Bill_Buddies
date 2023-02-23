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
import com.itax.billbuddies.models.ItemCategoryModel;
import com.itax.billbuddies.models.SalesItem;
import com.itax.billbuddies.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    Context context;
    ClickListener listener;
    ArrayList<ItemCategoryModel.ItemCategoryData> itemList;

    public CategoryAdapter(Context context, ArrayList<ItemCategoryModel.ItemCategoryData> itemList, ClickListener listener) {
        this.context = context;
        this.itemList = itemList;
        this.listener = listener;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int position) {
        ItemCategoryModel.ItemCategoryData item = itemList.get(position);

        if (item != null){
            holder.category_name_txt.setText(item.category_name);
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon_image;
        public TextView category_name_txt;
        public LinearLayout parent_layout;
        public ViewHolder(View itemView) {
            super(itemView);
            category_name_txt = itemView.findViewById(R.id.txt_category_name);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}

