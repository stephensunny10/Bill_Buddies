package com.itax.billbuddies.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.itax.billbuddies.activities.Purchase.AddPurchaseA;
import com.itax.billbuddies.R;

public class PurchaseTypeDialog {
    Context context;
    Dialog dialog;
    boolean isDialogShowing;


    public PurchaseTypeDialog(Context context) {
        this.context = context;
        init();
    }

    private void init(){
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_sales_type);
        itemClick();
    }
    public void itemClick(){
        TextView perform_invoice = dialog.findViewById(R.id.perform_invoice);
        TextView Import = dialog.findViewById(R.id.Import);
        TextView delivery_challan = dialog.findViewById(R.id.delivery_challan);
        TextView rcm = dialog.findViewById(R.id.rcm);
        TextView stock_transfer = dialog.findViewById(R.id.stock_transfer);
        TextView fixed_asset = dialog.findViewById(R.id.fixed_asset);
        Import.setVisibility(View.VISIBLE);
        perform_invoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActiity(AddPurchaseA.class);

            }
        });
        Import.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActiity(AddPurchaseA.class);
            }
        });
        delivery_challan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActiity(AddPurchaseA.class);
            }
        });
        rcm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActiity(AddPurchaseA.class);
            }
        });
        stock_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActiity(AddPurchaseA.class);
            }
        });
        fixed_asset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActiity(AddPurchaseA.class);
            }
        });

    }
    public void startActiity(Class c) {
        Intent i = new Intent(context, c);
        context.startActivity(i);
        dialog.dismiss();
    }

    public void showDialog(){
        if (!isDialogShowing){
            dialog.show();
            isDialogShowing = true;

        }
    }

    public void hideDialog(){
        if (isDialogShowing){
            dialog.hide();
            isDialogShowing = false;
        }
    }

}

