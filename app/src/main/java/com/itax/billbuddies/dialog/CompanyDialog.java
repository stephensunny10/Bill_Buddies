package com.itax.billbuddies.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.itax.billbuddies.R;
import com.itax.billbuddies.database.PaperDbManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class CompanyDialog {
    Context context;
    Dialog companyDialog;
    ListView lv;
    ArrayAdapter adapter;
    ArrayList<String>itemList = new ArrayList<>();
    ArrayList<HashMap>companyList = new ArrayList<>();
    String TAG = "CompanyDialog";
    PaperDbManager dbManger;
    boolean isDialogShowing = false;
    TextView company_txt;


    public CompanyDialog(Context context) {
        this.context = context;
        init();
    }

    private void init(){
        companyDialog = new Dialog(context );
        companyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        companyDialog.setContentView(R.layout.dialog_company);
        companyDialog.setCancelable(false);
        lv = companyDialog.findViewById(R.id.lv);

        lv.setOnItemClickListener((parent, view, position, id) -> {
            PaperDbManager.setCompany(PaperDbManager.getAllCompanyList().get(position));
            Log.d(TAG, "onItemClick: "+ dbManger.getCompany().getCompany_Gstin());
            hideDialog();
            if (company_txt != null){
                company_txt.setText(dbManger.getAllCompanyList().get(position).getCompany_Name());
            }
        });

        companyDialog.findViewById(R.id.img_close).setOnClickListener(v->{
            hideDialog();
        });
        getCompanyData();
    }

    public void getCompanyData(){
        itemList.clear();
        if (!PaperDbManager.getAllCompanyList().isEmpty()){
            extractCompany();
        }
        else {
            Log.d(TAG, "getCompanyData: "+ "empty company list");
        }
    }

    private void extractCompany(){
        try {
            JSONObject object = new JSONObject();
            for (int i=0; i<PaperDbManager.getAllCompanyList().size(); i++){
                itemList.add(PaperDbManager.getAllCompanyList().get(i).Company_Name);
            }
            adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,itemList);
            lv.setAdapter(adapter);
        }
        catch (Exception e){
            Log.d(TAG, "extractCompany: "+ e.getMessage());
        }
    }

    public void showDialog(){
        if (!isDialogShowing){
            companyDialog.show();
            isDialogShowing = true;
        }
    }

    public void hideDialog(){
        if (isDialogShowing){
            companyDialog.hide();
            isDialogShowing = false;
        }
    }

    public void setTextView(TextView company_txt){
        this.company_txt = company_txt;
    }

}
