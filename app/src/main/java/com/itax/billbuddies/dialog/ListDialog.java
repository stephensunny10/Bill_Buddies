package com.itax.billbuddies.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.itax.billbuddies.R;
import com.itax.billbuddies.adapter.ListAdapter;
import com.itax.billbuddies.fragments.HomeF;
import com.itax.billbuddies.listener.ClickListener;
import com.itax.billbuddies.models.ListItem;

import java.util.ArrayList;

public class ListDialog {
    Context context;
    Dialog dialog;
    RecyclerView recyclerView;
    ProgressBar pb;
    ArrayList<ListItem>itemList = new ArrayList<>();
    ListAdapter adapter;
    boolean isDialogShowing = false;
    ClickListener listener;

    public ListDialog(Context context, ArrayList<ListItem> list, ClickListener listener) {
        this.context = context;
        this.itemList = list;
        this.listener = listener;
        init();
    }


    private void init(){
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_list);
        dialog.setCancelable(true);

        recyclerView = dialog.findViewById(R.id.recycler_view);
        pb = dialog.findViewById(R.id.pb);
        adapter = new ListAdapter(context, itemList, position -> {
            hideDialog();
            listener.onClick(position);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    public void addList(ArrayList<ListItem> list){
        itemList.addAll(list);
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
