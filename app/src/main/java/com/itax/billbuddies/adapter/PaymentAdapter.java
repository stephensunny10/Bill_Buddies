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

import com.itax.billbuddies.models.PaymentModel;
import com.itax.billbuddies.models.SalesItem;
import com.itax.billbuddies.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PaymentAdapter  extends RecyclerView.Adapter<PaymentAdapter.ViewHolder>{
    Context context;
    ClickListener listener;
    ArrayList<PaymentModel.PaymentItem>itemList = new ArrayList<>();

    public PaymentAdapter(Context context, ArrayList<PaymentModel.PaymentItem> itemList, ClickListener listener) {
        this.context = context;
        this.itemList = itemList;
        this.listener = listener;
    }

    @Override
    public PaymentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_for_payment, parent, false);
        return new PaymentAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(PaymentAdapter.ViewHolder holder, int position) {
        PaymentModel.PaymentItem item = itemList.get(position);
        if (item != null){
            holder.date_txt.setText(item.payment_date);
            holder.invoice_id_txt.setText(item.invoice_id);
            holder.name_txt.setText("");
            holder.mobile_txt.setText("");
            holder.paid_amount_txt.setText(item.amount_paid);
            holder.payment_method_txt.setText(item.payment_method);
            holder.notes_txt.setText(item.notes);
            holder.payment_on_txt.setText(item.payment_method);
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView date_txt;
        public TextView invoice_id_txt,name_txt,mobile_txt,email_txt,paid_amount_txt,payment_method_txt,notes_txt,payment_on_txt;
        public LinearLayout parent_layout;
        public ViewHolder(View itemView) {
            super(itemView);
            date_txt = itemView.findViewById(R.id.txt_date);
            invoice_id_txt = itemView.findViewById(R.id.txt_invoice_id);
            name_txt = itemView.findViewById(R.id.txt_name);
            mobile_txt = itemView.findViewById(R.id.txt_mobile);
            email_txt = itemView.findViewById(R.id.txt_email);
            paid_amount_txt = itemView.findViewById(R.id.txt_paid_amount);
            payment_method_txt = itemView.findViewById(R.id.txt_payment_method);
            notes_txt = itemView.findViewById(R.id.txt_notes);
            payment_on_txt = itemView.findViewById(R.id.txt_payment_on);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

}

