package com.itax.billbuddies.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itax.billbuddies.R;


public class FilterSaleListDialog {
    Context context;
    Dialog dialog;
    boolean isDialogShowing;


    public FilterSaleListDialog(Context context) {
        this.context = context;
        init();
    }

    private void init(){
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_for_sale_filter);

    }
    public void itemClick(){

        TextView txt_sale = dialog.findViewById(R.id.txt_sale_list);
        TextView txt_credit = dialog.findViewById(R.id.txt_credit_note);
        TextView txt_debitor = dialog.findViewById(R.id.txt_manage_customer);
        LinearLayout sale_list = dialog.findViewById(R.id.subLay_sale);
        LinearLayout credit_lay = dialog.findViewById(R.id.Lay_credit);
        LinearLayout debitor = dialog.findViewById(R.id.Lay_customer);

        txt_sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {if(sale_list.isShown()){
            sale_list.setVisibility(View.GONE);}
            else sale_list.setVisibility(View.VISIBLE);
            }
        });
        txt_credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(credit_lay.isShown()){
                    credit_lay.setVisibility(View.GONE);}
                else credit_lay.setVisibility(View.VISIBLE);
            }
        });
        txt_debitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(debitor.isShown()){
                    debitor.setVisibility(View.GONE);}
                else debitor.setVisibility(View.VISIBLE);
            }
        });

    }

    public void showDialog(){
        if (!isDialogShowing){
            dialog.show();
            isDialogShowing = true;
            itemClick();
        }
    }

    public void hideDialog(){
        if (isDialogShowing){
            dialog.hide();
            isDialogShowing = false;
        }
    }


}

