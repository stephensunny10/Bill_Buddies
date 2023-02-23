package com.itax.billbuddies.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Window;
import android.widget.TextView;

import com.itax.billbuddies.activities.Sale.AddSalesA;
import com.itax.billbuddies.R;

public class SalesTypeDialog {
    Context context;
    Dialog dialog;
    boolean isDialogShowing;


    public SalesTypeDialog(Context context) {
        this.context = context;
        init();
    }

    private void init(){
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_sales_type);
        click();
    }
    private void click(){
        TextView perform_invoice = dialog.findViewById(R.id.perform_invoice);
        TextView rcm = dialog.findViewById(R.id.rcm);
        TextView Stock = dialog.findViewById(R.id.stock_transfer);
        TextView Delivery = dialog.findViewById(R.id.delivery_challan);
        TextView Tax = dialog.findViewById(R.id.tax_invoices);
        TextView fixed_asset = dialog.findViewById(R.id.fixed_asset);


        perform_invoice.setOnClickListener(v-> {
            startActivity();
        });
        rcm.setOnClickListener(v-> {
            startActivity();
        });
        Stock.setOnClickListener(v-> {
            startActivity();
        });
        fixed_asset.setOnClickListener(v-> {
            startActivity();
        });
        Delivery.setOnClickListener(v-> {
            startActivity();
        });
        Tax.setOnClickListener(v-> {
            startActivity();
        });

}

public void startActivity(){
    Intent i = new Intent(context, AddSalesA.class);
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
