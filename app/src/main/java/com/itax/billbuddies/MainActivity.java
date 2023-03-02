package com.itax.billbuddies;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.itax.billbuddies.activities.Auth.LoginA;
import com.itax.billbuddies.activities.Customer.AddCustomerA;
import com.itax.billbuddies.activities.Customer.CustomerA;
import com.itax.billbuddies.activities.Item.ItemActivity;
import com.itax.billbuddies.activities.ListA;
import com.itax.billbuddies.activities.Purchase.PurchaseA;
import com.itax.billbuddies.activities.Sale.SaleA;

import com.itax.billbuddies.activities.category.CategoryA;
import com.itax.billbuddies.activities.payment.PaymentA;
import com.itax.billbuddies.activities.reciept.ReceiptA;
import com.itax.billbuddies.activities.setting.UserSettingA;
import com.itax.billbuddies.activities.setting.settingF;
import com.itax.billbuddies.activities.supplier.AddSupplierA;
import com.itax.billbuddies.activities.wallet.WalletA;
import com.itax.billbuddies.controller.ITaxCompanyList;
import com.itax.billbuddies.controller.NavMenu;
import com.itax.billbuddies.database.PaperDbManager;
import com.itax.billbuddies.databinding.MainBinding;
import com.itax.billbuddies.dialog.BankTypeDialog;
import com.itax.billbuddies.dialog.CompanyDialog;
import com.itax.billbuddies.dialog.ReportTypeDialog;
import com.itax.billbuddies.fragments.HomeF;

import com.itax.billbuddies.utils.Constants;
import com.itax.billbuddies.utils.SessionManager;

public class MainActivity extends AppCompatActivity {
    MainBinding binding;
    BankTypeDialog bankTypeDialog;
    ReportTypeDialog reportTypeDialog;
    View headerLayout;
    private Bundle bundle = new Bundle();
    SessionManager sessionManager;
    CompanyDialog companyDialog;
    TextView company_txt;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = MainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appMain.activityMain.toolbar);

        sessionManager = new SessionManager(this);
        companyDialog = new CompanyDialog(this);
        initView();
    }

    private void initView(){
        binding.appMain.activityMain.toolbarIconImg.setOnClickListener(v -> binding.drawerLayout.openDrawer(Gravity.LEFT));
        ExpandableListView expandableListView = binding.navView.findViewById(R.id.expandable_list);
        new NavMenu(this,expandableListView);
        headerLayout = binding.navView.getHeaderView(0);
        addNavigationCLick();
        loadFragment(new HomeF());

        setNavHeaderData();
    }

    private void setNavHeaderData(){
       TextView username =  headerLayout.findViewById(R.id.txt_username);
       TextView loginId =  headerLayout.findViewById(R.id.txt_login_id);

       if (PaperDbManager.getLoginData() != null){
           username.setText(PaperDbManager.getLoginData().userName);
           loginId.setText(PaperDbManager.getLoginData().loginID);
       }

       company_txt = headerLayout.findViewById(R.id.txt_company);
        headerLayout.findViewById(R.id.layout_company).setOnClickListener(v->{
           companyDialog.getCompanyData();
           companyDialog.showDialog();
       });
        if (!PaperDbManager.getAllCompanyList().isEmpty()){
            company_txt.setText(PaperDbManager.getCompany().getCompany_Name());
        }
        companyDialog.setTextView(company_txt);
        // get company list
        ITaxCompanyList companyList = new ITaxCompanyList(this);
        if (PaperDbManager.getAllCompanyList().isEmpty()){
            companyList.getCompanyList();
        }
    }

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment); // replace a Fragment with Frame Layout
        transaction.commit(); // commit the changes
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    private void addNavigationCLick(){
        //loadFragment(new BasicDetailF());
        binding.navView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.dashboard:
                    loadFragment(new HomeF());
                    binding.drawerLayout.closeDrawer(Gravity.LEFT);
                    break;
                case R.id.sale:
                    moveToSale();
                    break;
                case R.id.purchase:
                    moveToPurchase();
                    break;
                case R.id.customer:
                    startActivity(new Intent(this, CustomerA.class));
                    break;
                case R.id.supplier:
                    startActivity(new Intent(this, AddSupplierA.class));
                    break;
                case R.id.item:
                    moveToItem();
                    break;
                case R.id.bank:
                    moveToBank();
                    break;
                case R.id.payment:
                    moveToPayment();
                    break;
                case R.id.receipt:
                    moveToReceipt();
                    break;
                case R.id.wallet:
                    moveToWallet();
                    break;
                case R.id.category:
                    moveToCategory();
                    break;
                case R.id.reports:
                    moveToReport();
                    break;
                case R.id.settings:
                    startActivity(new Intent(this, UserSettingA.class));
                    binding.drawerLayout.closeDrawer(Gravity.LEFT);
                    break;
                case R.id.askQuery:

                    break;
                case R.id.ourService:
                    moveToOurServices();
                    break;
                case R.id.rateApp:

                    break;
                case R.id.suggestion:

                    break;
                case R.id.logout:
                    confirmLogout();
                    break;
            }
            return true;
        });

    }


    private void moveToReport() {
        reportTypeDialog = new ReportTypeDialog(MainActivity.this);
        reportTypeDialog.showDialog();

    }

    private void moveToReceipt() {
        startActivity(new Intent(this, ReceiptA.class));
    }

    private void moveToSale(){
        startActivity(new Intent(this, SaleA.class));
    }

    private void moveToPurchase(){
        startActivity(new Intent(this, PurchaseA.class));
    }
    private void moveToItem(){
        startActivity(new Intent(this, ItemActivity.class));
    }
    private void moveToBank(){
      bankTypeDialog = new BankTypeDialog(MainActivity.this);
      bankTypeDialog.showDialog();
      binding.drawerLayout.close();
    }
    private void moveToPayment(){
        startActivity(new Intent(this, PaymentA.class));
    }
    private void moveToWallet(){
        startActivity(new Intent(this, WalletA.class));
    }
    private void moveToCategory(){
        startActivity(new Intent(this, CategoryA.class));
    }

    private void moveToOurServices(){
        startActivity(new Intent(this, ListA.class).putExtra(Constants.page_name,Constants.our_services));
    }

    // warn user before leaving exam
    private void confirmLogout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.app_name);
        builder.setMessage("Are you sure want to logout ?");
        builder.setIcon(R.drawable.app_icon);
        builder.setPositiveButton("Yes", (dialog, id) -> {
            dialog.dismiss();
            sessionManager.setLoggedIn(false);
            moveToLogin();
        });
        builder.setNegativeButton("No", (dialog, id) -> dialog.dismiss());
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void moveToLogin(){
        startActivity(new Intent(this,LoginA.class));
        finish();
    }
}