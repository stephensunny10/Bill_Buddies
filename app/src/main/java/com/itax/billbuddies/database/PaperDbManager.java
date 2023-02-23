package com.itax.billbuddies.database;

import com.itax.billbuddies.models.ITaxCompanyItem;
import com.itax.billbuddies.models.LoginModel;

import java.util.ArrayList;

import io.paperdb.Paper;

public class PaperDbManager {

    public static void setLoginData(LoginModel loginData){
        Paper.book().write("loginData",loginData);
    }

    public static LoginModel getLoginData(){
        return Paper.book().read("loginData",null);
    }

    public static ArrayList<ITaxCompanyItem> getAllCompanyList() {
        return Paper.book("ITaxInfo").read("iTaxCompanyItemArrayList",new ArrayList<ITaxCompanyItem>());
    }

    public static void setAllCompanyList(ArrayList<ITaxCompanyItem> iTaxCompanyItemArrayList) {
        Paper.book("ITaxInfo").write("iTaxCompanyItemArrayList",iTaxCompanyItemArrayList);
    }

    public static ITaxCompanyItem getCompany() {
        return Paper.book("ITaxInfo").read("iTaxCompany",new ITaxCompanyItem());
    }

    public static void setCompany(ITaxCompanyItem iTaxCompany) {
        Paper.book("ITaxInfo").write("iTaxCompany",iTaxCompany);
    }
}
