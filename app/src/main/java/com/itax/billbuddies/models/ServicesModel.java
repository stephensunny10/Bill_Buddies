package com.itax.billbuddies.models;

import java.util.ArrayList;

public class ServicesModel {
    public String responseMessage;
    public String responseCode;
    public ArrayList<ServicesItem>data;

    public static class ServicesItem{
        public String id;
        public String serviceCode;
        public String serviceProviderCode;
        public String serviceProviderName;
        public String serviceName;
        public String minBalAmount;
        public String remarks;
        public String isActive;
        public String productionURL;
        public String reqFrom;
        public String iconURL;
        public String iconCompleteURL;
        public String icon;
        public String title;
        public String srno;
        public String status;
        public String loginID = null;
        public String app_constring;
        public String app_usertablename;
        public String responseMessage = null;
        public String responseCode = null;
        public String data = null;
        public String tranID = null;
        public String company_Id = null;
    }
}
