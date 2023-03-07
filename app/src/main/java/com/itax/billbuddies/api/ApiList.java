package com.itax.billbuddies.api;

public class ApiList {
    public static final String BaseUrl = "https://uat.itaxinfo.com/Api/Service/";
    public static final String Baseurl = "https://sysqbit.com/bill-buddies/";//https://sysqbit.com/";
    public static final String LoginUrl = BaseUrl + "UserLogin";
    public static final String RegisterUrl = BaseUrl + "RegisterUser";
    public static final String ForgotPasswordUrl = BaseUrl + "SentForgotpasswordOTP";
    public static final String ResetPassUrl = BaseUrl + "ResetPassword";
    public static final String ProfileUrl = BaseUrl + "GetProfile";
    public static final String ourServicesUrl = BaseUrl + "get_ServiceMaster_List";
    public static final String ITAX_COMPANY = BaseUrl + "GetCompanyList";
    // APP API BEGIN //https://sysqbit.com/bill-buddies/api/cash-transaction?loginID=ITIC-00005161&company_id=COM00000001
    public static final String BILL_BUDDIES_BASE_URL = "http://192.168.242.64/bill-buddies/";//"http://192.168.242.64/bill-buddies/";
    //public static final String BILL_BUDDIES_BASE_URL = "https://sysqbit.com/bill-buddies/";
    public static final String BILL_BUDDIES_IMAGE_URL = BILL_BUDDIES_BASE_URL + "public/uploads/images/";
    public static final String BILL_BUDDIES_API_URL = BILL_BUDDIES_BASE_URL + "api/";
    public static final String CUSTOMER_URL = BILL_BUDDIES_API_URL + "customer";
    public static final String SUPPLIER_URL = BILL_BUDDIES_API_URL + "supplier";
    public static final String SALES_URL = BILL_BUDDIES_API_URL + "sales";
    public static final String PURCHASE_URL = BILL_BUDDIES_API_URL + "purchase";
    public static final String ITEM_PRODUCT_URL = BILL_BUDDIES_API_URL + "item";
    public static final String CASH_TRANSACTION_URL = BILL_BUDDIES_API_URL + "cash-transaction";
    public static final String PAYMENT_URL = BILL_BUDDIES_API_URL + "payment";
    public static final String CATEGORY_URL = BILL_BUDDIES_API_URL + "item-category";
    public static final String BRAND_URL = BILL_BUDDIES_API_URL + "item-brand";
    public static final String UNIT_URL = BILL_BUDDIES_API_URL + "item-unit";
    public static final String TAX_CLASS_URL = BILL_BUDDIES_API_URL + "item-tax-class";
    public static final String userSettingUrl= BILL_BUDDIES_API_URL+"user-settings.php";
    public static final String PRINT_SETTING_URL= BILL_BUDDIES_API_URL+"print-settings";
}
