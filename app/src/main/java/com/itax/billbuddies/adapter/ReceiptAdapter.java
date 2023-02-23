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
import com.itax.billbuddies.models.SalesItem;
import com.itax.billbuddies.R;

import java.util.ArrayList;

public class ReceiptAdapter extends RecyclerView.Adapter<ReceiptAdapter.ViewHolder>{
    Context context;
    ClickListener listener;
    ArrayList<SalesItem> itemList;

public ReceiptAdapter(Context context, ArrayList<SalesItem> itemList, ClickListener listener) {
        this.context = context;
        this.itemList = itemList;
        this.listener = listener;
        }

@Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_for_payment, parent, false);
        return new ViewHolder(view);
        }

@RequiresApi(api = Build.VERSION_CODES.N)
@Override
public void onBindViewHolder(ViewHolder holder,int position) {
        SalesItem item = itemList.get(position);


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
    }
}

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}


