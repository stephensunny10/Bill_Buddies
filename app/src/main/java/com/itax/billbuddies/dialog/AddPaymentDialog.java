package com.itax.billbuddies.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.itax.billbuddies.R;

     public class AddPaymentDialog {   Context context;
           Dialog dialog;
                   boolean isDialogShowing;


        public AddPaymentDialog(Context context) {
        this.context = context;
        init();
        }

        private void init(){
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_add_payment);

        itemClick();
        }
        public void itemClick() {
        TextView submit = dialog.findViewById(R.id.btsubmit);

        submit.setOnClickListener(new View.OnClickListener() {
        @Override

         public void onClick(View v) {
        dialog.dismiss();
        }
        });
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
