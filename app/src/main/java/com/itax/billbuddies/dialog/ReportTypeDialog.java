package com.itax.billbuddies.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.itax.billbuddies.activities.report.SaleReportA;
import com.itax.billbuddies.R;

public class ReportTypeDialog {
    Context context;
    Dialog dialog;
    boolean isDialogShowing;


    public ReportTypeDialog(Context context) {
        this.context = context;
        init();
    }

    private void init(){
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_add_report);

        itemClick();
    }
    public void itemClick() {
        TextView Sale_report = dialog.findViewById(R.id.sale_report);
        TextView Purchase_report = dialog.findViewById(R.id.purchase_report);
        TextView Ledger_report = dialog.findViewById(R.id.ledger_report);
        TextView Debitor_report = dialog.findViewById(R.id.debitor_report);
        TextView Creditor_report = dialog.findViewById(R.id.creditor_report);

        Creditor_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SaleReportA.class,"Creditor");
            }
        });
        Debitor_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SaleReportA.class,"Debitor");
            }
        });
        Ledger_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SaleReportA.class,"Ledger");
            }
        });
        Purchase_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SaleReportA.class,"Purchase");
            }
        });

        Sale_report.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
               startActivity(SaleReportA.class, "Sale");
            }
        });
    }
    public void startActivity(Class c, String value) {
        Intent i = new Intent(context, c);
        i.putExtra("fragment", value);
        context.startActivity(i);
        dialog.dismiss();
    }


    public void showDialog() {
        if (!isDialogShowing) {
            dialog.show();
            isDialogShowing = true;

        }
    }

    public void hideDialog() {
        if (isDialogShowing) {
            dialog.hide();
            isDialogShowing = false;
        }
    }
}

