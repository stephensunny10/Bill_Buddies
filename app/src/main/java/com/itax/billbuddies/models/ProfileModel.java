package com.itax.billbuddies.models;

import java.util.ArrayList;

public class ProfileModel {

        private String loginID;
        private String financialYear = null;
        private String company_Name = null;
        private String first_Name;
        private String middle_Name;
        private String last_Name;
        private String dob;
        private String placeofBirth;
        private String mother_Name;
        private String father_Name;
        private String email;
        private String mobile;
        private String landline;
        private String pan;
        private String aadhaarno;
        private String nearest_poicestation;
        private String marital_status;
        private String destinationId;
        private String designation_name;
        private String anniversary;
        private String persent_address;
        private String persent_city;
        private String persent_state;
        private String persent_pincode;
        private String permanent_address;
        private String permanent_city;
        private String permanent_state;
        private String permanent_pincode;
        private String haveDSC;
        private String dscExpieryDate;
        private String haveDIN;
        private String dinNo;
        private String aboutus;
        private String firm_Name;
        private String firm_Reg_No;
        private String firm_Reg_Date;
        private String membershipNo;
        private String membership_Date;
        private String imagePath;
        private String websiteURL;
        private String lenkdinURL;
        private String facebookURL;
        private String twitterURL;
        DirectorModel DirectorModelObject;
        CompanyModel CompanyModelObject;
        ArrayList < Object > familyList = new ArrayList < Object > ();
        ArrayList < Object > educationList = new ArrayList < Object > ();
        ArrayList < Object > documentsList = new ArrayList < Object > ();
        ArrayList < Object > banksList = new ArrayList < Object > ();
        ArrayList < Object > bussinessList = new ArrayList < Object > ();
        ArrayList < Object > workExprienceList = new ArrayList < Object > ();
        private String passwordList = null;
        ArrayList < Object > relationList = new ArrayList < Object > ();
        ArrayList < Object > businessTypeList = new ArrayList < Object > ();
        ArrayList < Object > qualificationList = new ArrayList < Object > ();
        ArrayList < Object > documentList = new ArrayList < Object > ();
        ArrayList < Object > bankList = new ArrayList < Object > ();
        ArrayList < Object > applicationList = new ArrayList < Object > ();
        ArrayList < Object > designationList = new ArrayList < Object > ();
        ArrayList < Object > serviceCategoryList = new ArrayList < Object > ();
        private String responseMessage;
        private String responseCode;
        private String data = null;
        private String tranID = null;
        private String company_Id = null;


        // Getter Methods

        public String getLoginID() {
            return loginID;
        }

        public String getFinancialYear() {
            return financialYear;
        }

        public String getCompany_Name() {
            return company_Name;
        }

        public String getFirst_Name() {
            return first_Name;
        }

        public String getMiddle_Name() {
            return middle_Name;
        }

        public String getLast_Name() {
            return last_Name;
        }

        public String getDob() {
            return dob;
        }

        public String getPlaceofBirth() {
            return placeofBirth;
        }

        public String getMother_Name() {
            return mother_Name;
        }

        public String getFather_Name() {
            return father_Name;
        }

        public String getEmail() {
            return email;
        }

        public String getMobile() {
            return mobile;
        }

        public String getLandline() {
            return landline;
        }

        public String getPan() {
            return pan;
        }

        public String getAadhaarno() {
            return aadhaarno;
        }

        public String getNearest_poicestation() {
            return nearest_poicestation;
        }

        public String getMarital_status() {
            return marital_status;
        }

        public String getDestinationId() {
            return destinationId;
        }

        public String getDesignation_name() {
            return designation_name;
        }

        public String getAnniversary() {
            return anniversary;
        }

        public String getPersent_address() {
            return persent_address;
        }

        public String getPersent_city() {
            return persent_city;
        }

        public String getPersent_state() {
            return persent_state;
        }

        public String getPersent_pincode() {
            return persent_pincode;
        }

        public String getPermanent_address() {
            return permanent_address;
        }

        public String getPermanent_city() {
            return permanent_city;
        }

        public String getPermanent_state() {
            return permanent_state;
        }

        public String getPermanent_pincode() {
            return permanent_pincode;
        }

        public String getHaveDSC() {
            return haveDSC;
        }

        public String getDscExpieryDate() {
            return dscExpieryDate;
        }

        public String getHaveDIN() {
            return haveDIN;
        }

        public String getDinNo() {
            return dinNo;
        }

        public String getAboutus() {
            return aboutus;
        }

        public String getFirm_Name() {
            return firm_Name;
        }

        public String getFirm_Reg_No() {
            return firm_Reg_No;
        }

        public String getFirm_Reg_Date() {
            return firm_Reg_Date;
        }

        public String getMembershipNo() {
            return membershipNo;
        }

        public String getMembership_Date() {
            return membership_Date;
        }

        public String getImagePath() {
            return imagePath;
        }

        public String getWebsiteURL() {
            return websiteURL;
        }

        public String getLenkdinURL() {
            return lenkdinURL;
        }

        public String getFacebookURL() {
            return facebookURL;
        }

        public String getTwitterURL() {
            return twitterURL;
        }

        public DirectorModel getDirectorModel() {
            return DirectorModelObject;
        }

        public CompanyModel getCompanyModel() {
            return CompanyModelObject;
        }

        public String getPasswordList() {
            return passwordList;
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

        public String getCompany_Id() {
            return company_Id;
        }

        // Setter Methods

        public void setLoginID(String loginID) {
            this.loginID = loginID;
        }

        public void setFinancialYear(String financialYear) {
            this.financialYear = financialYear;
        }

        public void setCompany_Name(String company_Name) {
            this.company_Name = company_Name;
        }

        public void setFirst_Name(String first_Name) {
            this.first_Name = first_Name;
        }

        public void setMiddle_Name(String middle_Name) {
            this.middle_Name = middle_Name;
        }

        public void setLast_Name(String last_Name) {
            this.last_Name = last_Name;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public void setPlaceofBirth(String placeofBirth) {
            this.placeofBirth = placeofBirth;
        }

        public void setMother_Name(String mother_Name) {
            this.mother_Name = mother_Name;
        }

        public void setFather_Name(String father_Name) {
            this.father_Name = father_Name;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public void setLandline(String landline) {
            this.landline = landline;
        }

        public void setPan(String pan) {
            this.pan = pan;
        }

        public void setAadhaarno(String aadhaarno) {
            this.aadhaarno = aadhaarno;
        }

        public void setNearest_poicestation(String nearest_poicestation) {
            this.nearest_poicestation = nearest_poicestation;
        }

        public void setMarital_status(String marital_status) {
            this.marital_status = marital_status;
        }

        public void setDestinationId(String destinationId) {
            this.destinationId = destinationId;
        }

        public void setDesignation_name(String designation_name) {
            this.designation_name = designation_name;
        }

        public void setAnniversary(String anniversary) {
            this.anniversary = anniversary;
        }

        public void setPersent_address(String persent_address) {
            this.persent_address = persent_address;
        }

        public void setPersent_city(String persent_city) {
            this.persent_city = persent_city;
        }

        public void setPersent_state(String persent_state) {
            this.persent_state = persent_state;
        }

        public void setPersent_pincode(String persent_pincode) {
            this.persent_pincode = persent_pincode;
        }

        public void setPermanent_address(String permanent_address) {
            this.permanent_address = permanent_address;
        }

        public void setPermanent_city(String permanent_city) {
            this.permanent_city = permanent_city;
        }

        public void setPermanent_state(String permanent_state) {
            this.permanent_state = permanent_state;
        }

        public void setPermanent_pincode(String permanent_pincode) {
            this.permanent_pincode = permanent_pincode;
        }

        public void setHaveDSC(String haveDSC) {
            this.haveDSC = haveDSC;
        }

        public void setDscExpieryDate(String dscExpieryDate) {
            this.dscExpieryDate = dscExpieryDate;
        }

        public void setHaveDIN(String haveDIN) {
            this.haveDIN = haveDIN;
        }

        public void setDinNo(String dinNo) {
            this.dinNo = dinNo;
        }

        public void setAboutus(String aboutus) {
            this.aboutus = aboutus;
        }

        public void setFirm_Name(String firm_Name) {
            this.firm_Name = firm_Name;
        }

        public void setFirm_Reg_No(String firm_Reg_No) {
            this.firm_Reg_No = firm_Reg_No;
        }

        public void setFirm_Reg_Date(String firm_Reg_Date) {
            this.firm_Reg_Date = firm_Reg_Date;
        }

        public void setMembershipNo(String membershipNo) {
            this.membershipNo = membershipNo;
        }

        public void setMembership_Date(String membership_Date) {
            this.membership_Date = membership_Date;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public void setWebsiteURL(String websiteURL) {
            this.websiteURL = websiteURL;
        }

        public void setLenkdinURL(String lenkdinURL) {
            this.lenkdinURL = lenkdinURL;
        }

        public void setFacebookURL(String facebookURL) {
            this.facebookURL = facebookURL;
        }

        public void setTwitterURL(String twitterURL) {
            this.twitterURL = twitterURL;
        }

        public void setDirectorModel(DirectorModel directorModelObject) {
            this.DirectorModelObject = directorModelObject;
        }

        public void setCompanyModel(CompanyModel companyModelObject) {
            this.CompanyModelObject = companyModelObject;
        }

        public void setPasswordList(String passwordList) {
            this.passwordList = passwordList;
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

        public void setCompany_Id(String company_Id) {
            this.company_Id = company_Id;
        }
    }

