package com.itax.billbuddies.models;

import java.util.ArrayList;

public class CompanyModel {
    private float id;
    private String loginId = null;
    private String company_Id = null;
    private String company_Name = null;
    private String bussiness_Type_Id = null;
    private String bussiness_Type = null;
    private String contact_Person = null;
    private String nature_of_Business_Id = null;
    private String nature_of_Business = null;
    private String ciN_Registration_No = null;
    private String date_of_Incorporate = null;
    private String company_Pan = null;
    private String state_code = null;
    private String state_name = null;
    private String company_Gstin = null;
    private String gstin_Registration_Type = null;
    private String gsteKycVerified = null;
    private String gstAadhaarAuthenticated = null;
    private String company_Email = null;
    private String company_Mobile = null;
    private String company_LandLine = null;
    private String company_Address = null;
    private String haveRentAgreement = null;
    private String validityFromRentAgreement = null;
    private String validityToRentAgreement = null;
    private String tan_no = null;
    private String tan_no_registration_date = null;
    private String pf_no = null;
    private String pf_no_registration_date = null;
    private String esi_no = null;
    private String esi_no_registration_date = null;
    private String iso_no = null;
    private String iso_no_registration_date = null;
    private String iso_no_expiry_date = null;
    private String trademark = null;
    private String trademark_registartion_date = null;
    private String trademark_expiry_date = null;
    private String gstin_Registration_Date = null;
    private float companycapital;
    private float companyauthorizedcapital;
    private String create_by = null;
    private String create_date = null;
    private String update_by = null;
    private String update_date = null;
    private boolean isActive;
    private boolean isDefault;
    private String imagePath = null;
    private String ieC_Code = null;
    private String ieC_Code_Registration_Date = null;
    private String fssI_NO = null;
    private String fssI_NO_Registration_Date = null;
    private String fssI_NO_Expiry_Date = null;
    private String msmE_No = null;
    private String msmE_No_Registration_Date = null;
    private String cr_RefId = null;
    ArrayList<Object> natureofBusinessList = new ArrayList<Object>();
    ArrayList<Object> businessTypeList = new ArrayList<Object>();
    ArrayList<Object> documentList = new ArrayList<Object>();
    ArrayList<Object> companyList = new ArrayList<Object>();
    private String directorList = null;
    private String responseMessage = null;
    private String responseCode = null;
    private String data = null;
    private String tranID = null;


    // Getter Methods

    public float getId() {
        return id;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getCompany_Id() {
        return company_Id;
    }

    public String getCompany_Name() {
        return company_Name;
    }

    public String getBussiness_Type_Id() {
        return bussiness_Type_Id;
    }

    public String getBussiness_Type() {
        return bussiness_Type;
    }

    public String getContact_Person() {
        return contact_Person;
    }

    public String getNature_of_Business_Id() {
        return nature_of_Business_Id;
    }

    public String getNature_of_Business() {
        return nature_of_Business;
    }

    public String getCiN_Registration_No() {
        return ciN_Registration_No;
    }

    public String getDate_of_Incorporate() {
        return date_of_Incorporate;
    }

    public String getCompany_Pan() {
        return company_Pan;
    }

    public String getState_code() {
        return state_code;
    }

    public String getState_name() {
        return state_name;
    }

    public String getCompany_Gstin() {
        return company_Gstin;
    }

    public String getGstin_Registration_Type() {
        return gstin_Registration_Type;
    }

    public String getGsteKycVerified() {
        return gsteKycVerified;
    }

    public String getGstAadhaarAuthenticated() {
        return gstAadhaarAuthenticated;
    }

    public String getCompany_Email() {
        return company_Email;
    }

    public String getCompany_Mobile() {
        return company_Mobile;
    }

    public String getCompany_LandLine() {
        return company_LandLine;
    }

    public String getCompany_Address() {
        return company_Address;
    }

    public String getHaveRentAgreement() {
        return haveRentAgreement;
    }

    public String getValidityFromRentAgreement() {
        return validityFromRentAgreement;
    }

    public String getValidityToRentAgreement() {
        return validityToRentAgreement;
    }

    public String getTan_no() {
        return tan_no;
    }

    public String getTan_no_registration_date() {
        return tan_no_registration_date;
    }

    public String getPf_no() {
        return pf_no;
    }

    public String getPf_no_registration_date() {
        return pf_no_registration_date;
    }

    public String getEsi_no() {
        return esi_no;
    }

    public String getEsi_no_registration_date() {
        return esi_no_registration_date;
    }

    public String getIso_no() {
        return iso_no;
    }

    public String getIso_no_registration_date() {
        return iso_no_registration_date;
    }

    public String getIso_no_expiry_date() {
        return iso_no_expiry_date;
    }

    public String getTrademark() {
        return trademark;
    }

    public String getTrademark_registartion_date() {
        return trademark_registartion_date;
    }

    public String getTrademark_expiry_date() {
        return trademark_expiry_date;
    }

    public String getGstin_Registration_Date() {
        return gstin_Registration_Date;
    }

    public float getCompanycapital() {
        return companycapital;
    }

    public float getCompanyauthorizedcapital() {
        return companyauthorizedcapital;
    }

    public String getCreate_by() {
        return create_by;
    }

    public String getCreate_date() {
        return create_date;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public boolean getIsDefault() {
        return isDefault;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getIeC_Code() {
        return ieC_Code;
    }

    public String getIeC_Code_Registration_Date() {
        return ieC_Code_Registration_Date;
    }

    public String getFssI_NO() {
        return fssI_NO;
    }

    public String getFssI_NO_Registration_Date() {
        return fssI_NO_Registration_Date;
    }

    public String getFssI_NO_Expiry_Date() {
        return fssI_NO_Expiry_Date;
    }

    public String getMsmE_No() {
        return msmE_No;
    }

    public String getMsmE_No_Registration_Date() {
        return msmE_No_Registration_Date;
    }

    public String getCr_RefId() {
        return cr_RefId;
    }

    public String getDirectorList() {
        return directorList;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getData() {
        return data;
    }

    public String getTranID() {
        return tranID;
    }

    // Setter Methods

    public void setId(float id) {
        this.id = id;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public void setCompany_Id(String company_Id) {
        this.company_Id = company_Id;
    }

    public void setCompany_Name(String company_Name) {
        this.company_Name = company_Name;
    }

    public void setBussiness_Type_Id(String bussiness_Type_Id) {
        this.bussiness_Type_Id = bussiness_Type_Id;
    }

    public void setBussiness_Type(String bussiness_Type) {
        this.bussiness_Type = bussiness_Type;
    }

    public void setContact_Person(String contact_Person) {
        this.contact_Person = contact_Person;
    }

    public void setNature_of_Business_Id(String nature_of_Business_Id) {
        this.nature_of_Business_Id = nature_of_Business_Id;
    }

    public void setNature_of_Business(String nature_of_Business) {
        this.nature_of_Business = nature_of_Business;
    }

    public void setCiN_Registration_No(String ciN_Registration_No) {
        this.ciN_Registration_No = ciN_Registration_No;
    }

    public void setDate_of_Incorporate(String date_of_Incorporate) {
        this.date_of_Incorporate = date_of_Incorporate;
    }

    public void setCompany_Pan(String company_Pan) {
        this.company_Pan = company_Pan;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public void setCompany_Gstin(String company_Gstin) {
        this.company_Gstin = company_Gstin;
    }

    public void setGstin_Registration_Type(String gstin_Registration_Type) {
        this.gstin_Registration_Type = gstin_Registration_Type;
    }

    public void setGsteKycVerified(String gsteKycVerified) {
        this.gsteKycVerified = gsteKycVerified;
    }

    public void setGstAadhaarAuthenticated(String gstAadhaarAuthenticated) {
        this.gstAadhaarAuthenticated = gstAadhaarAuthenticated;
    }

    public void setCompany_Email(String company_Email) {
        this.company_Email = company_Email;
    }

    public void setCompany_Mobile(String company_Mobile) {
        this.company_Mobile = company_Mobile;
    }

    public void setCompany_LandLine(String company_LandLine) {
        this.company_LandLine = company_LandLine;
    }

    public void setCompany_Address(String company_Address) {
        this.company_Address = company_Address;
    }

    public void setHaveRentAgreement(String haveRentAgreement) {
        this.haveRentAgreement = haveRentAgreement;
    }

    public void setValidityFromRentAgreement(String validityFromRentAgreement) {
        this.validityFromRentAgreement = validityFromRentAgreement;
    }

    public void setValidityToRentAgreement(String validityToRentAgreement) {
        this.validityToRentAgreement = validityToRentAgreement;
    }

    public void setTan_no(String tan_no) {
        this.tan_no = tan_no;
    }

    public void setTan_no_registration_date(String tan_no_registration_date) {
        this.tan_no_registration_date = tan_no_registration_date;
    }

    public void setPf_no(String pf_no) {
        this.pf_no = pf_no;
    }

    public void setPf_no_registration_date(String pf_no_registration_date) {
        this.pf_no_registration_date = pf_no_registration_date;
    }

    public void setEsi_no(String esi_no) {
        this.esi_no = esi_no;
    }

    public void setEsi_no_registration_date(String esi_no_registration_date) {
        this.esi_no_registration_date = esi_no_registration_date;
    }

    public void setIso_no(String iso_no) {
        this.iso_no = iso_no;
    }

    public void setIso_no_registration_date(String iso_no_registration_date) {
        this.iso_no_registration_date = iso_no_registration_date;
    }

    public void setIso_no_expiry_date(String iso_no_expiry_date) {
        this.iso_no_expiry_date = iso_no_expiry_date;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    public void setTrademark_registartion_date(String trademark_registartion_date) {
        this.trademark_registartion_date = trademark_registartion_date;
    }

    public void setTrademark_expiry_date(String trademark_expiry_date) {
        this.trademark_expiry_date = trademark_expiry_date;
    }

    public void setGstin_Registration_Date(String gstin_Registration_Date) {
        this.gstin_Registration_Date = gstin_Registration_Date;
    }

    public void setCompanycapital(float companycapital) {
        this.companycapital = companycapital;
    }

    public void setCompanyauthorizedcapital(float companyauthorizedcapital) {
        this.companyauthorizedcapital = companyauthorizedcapital;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setIeC_Code(String ieC_Code) {
        this.ieC_Code = ieC_Code;
    }

    public void setIeC_Code_Registration_Date(String ieC_Code_Registration_Date) {
        this.ieC_Code_Registration_Date = ieC_Code_Registration_Date;
    }

    public void setFssI_NO(String fssI_NO) {
        this.fssI_NO = fssI_NO;
    }

    public void setFssI_NO_Registration_Date(String fssI_NO_Registration_Date) {
        this.fssI_NO_Registration_Date = fssI_NO_Registration_Date;
    }

    public void setFssI_NO_Expiry_Date(String fssI_NO_Expiry_Date) {
        this.fssI_NO_Expiry_Date = fssI_NO_Expiry_Date;
    }

    public void setMsmE_No(String msmE_No) {
        this.msmE_No = msmE_No;
    }

    public void setMsmE_No_Registration_Date(String msmE_No_Registration_Date) {
        this.msmE_No_Registration_Date = msmE_No_Registration_Date;
    }

    public void setCr_RefId(String cr_RefId) {
        this.cr_RefId = cr_RefId;
    }

    public void setDirectorList(String directorList) {
        this.directorList = directorList;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setTranID(String tranID) {
        this.tranID = tranID;
    }
}
