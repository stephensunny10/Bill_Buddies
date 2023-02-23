package com.itax.billbuddies.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.itax.billbuddies.activities.bank.ManageBankDetailA;
import com.itax.billbuddies.activities.bank.ManageCashTransactionA;
import com.itax.billbuddies.R;

public class BankTypeDialog  {
    Context context;
    Dialog dialog;
    boolean isDialogShowing;

    public BankTypeDialog(Context context) {
        this.context = context;
        init();
    }
    private void init(){
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_bank);
        itemClick();
    }
    public void itemClick() {
        TextView manage_cash = dialog.findViewById(R.id.manage_cash);
        TextView manage_bank = dialog.findViewById(R.id.manage_bank);

        manage_cash.setOnClickListener(v->{
            startActivity(ManageCashTransactionA.class);
        });

        manage_bank.setOnClickListener(v -> startActivity(ManageBankDetailA.class));
    }

    public void startActivity(Class c) {
        Intent i = new Intent(context, c);
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