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
import com.itax.billbuddies.models.CartItem;
import com.itax.billbuddies.models.CartItem;
import com.itax.billbuddies.R;
import com.itax.billbuddies.utils.Constants;
import com.itax.billbuddies.utils.Functions;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
    Context context;
    ClickListener listener;
    ArrayList<CartItem> itemList;

    public CartAdapter(Context context, ArrayList<CartItem> itemList, ClickListener listener) {
        this.context = context;
        this.itemList = itemList;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position) {
        CartItem item = itemList.get(position);
        if (item != null){
            Glide.with(context).load(ApiList.BILL_BUDDIES_IMAGE_URL + item.main_image).error(R.drawable.ic_product).into(holder.product_image);
            holder.product_name_txt.setText(item.product_name);
            holder.product_code_txt.setText(item.barcode);
            holder.hsn_txt.setText(item.hsn);
            holder.price_txt.setText(item.actual_price);
            holder.quantity_txt.setText(item.quantity);
            holder.discount_txt.setText(item.discount);
            holder.parent_layout.setOnClickListener(v->{
                listener.onClick(position);
            });
            setData(holder,item,position);
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView product_image,minus_image,plus_image;
        public TextView product_name_txt,product_code_txt,price_txt,hsn_txt,quantity_txt,discount_txt,total_txt;
        public RelativeLayout parent_layout;
        public ViewHolder(View itemView) {
            super(itemView);
            product_image = itemView.findViewById(R.id.img_product);
            minus_image = itemView.findViewById(R.id.img_minus);
            plus_image = itemView.findViewById(R.id.img_plus);
            product_name_txt = itemView.findViewById(R.id.txt_product_name);
            product_code_txt = itemView.findViewById(R.id.txt_product_code);
            hsn_txt = itemView.findViewById(R.id.txt_hsn);
            price_txt = itemView.findViewById(R.id.txt_price);
            quantity_txt = itemView.findViewById(R.id.txt_quantity);
            discount_txt = itemView.findViewById(R.id.txt_discount);
            total_txt = itemView.findViewById(R.id.txt_total);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    private void setData(ViewHolder holder,CartItem item,int position){
        int q = Functions.ParseInteger(item.quantity);
        if (q == 0){
            q = 1;
            holder.quantity_txt.setText(Integer.toString(q));
        }
        // set quantity
        double discount = Functions.ParseDouble(item.discount);
        double unit_price = Functions.ParseDouble(item.actual_price);
        double total = unit_price * q;
        double total_dis = (discount * total)/100;
        holder.total_txt.setText(Double.toString(total-total_dis));

        holder.plus_image.setOnClickListener(view -> {
            int quantity = Functions.ParseInteger(holder.quantity_txt.getText().toString());
            quantity++;
            double total_amount = unit_price * quantity;
            double total_discount = (discount * total_amount)/100;
            holder.quantity_txt.setText(Integer.toString(quantity));
            holder.total_txt.setText(Double.toString(total_amount-total_discount));
            item.quantity = Integer.toString(quantity);
            itemList.set(position,item);
            listener.onClick(Constants.quantity_update);
        });

        holder.minus_image.setOnClickListener(view -> {
            int quantity = Functions.ParseInteger(holder.quantity_txt.getText().toString());
            if (quantity > 1){
                quantity--;
                double total_amount = unit_price * quantity;
                double total_discount = (discount * total_amount)/100;
                holder.quantity_txt.setText(Integer.toString(quantity));
                holder.total_txt.setText(Double.toString(total_amount-total_discount));
                item.quantity = Integer.toString(quantity);
                itemList.set(position,item);
                listener.onClick(Constants.quantity_update);
            }
        });
    }

    public ArrayList<CartItem>getItemList(){
        return itemList;
    }
}
