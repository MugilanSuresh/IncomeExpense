package com.example.income_expense.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Session_Manager {

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "onboard";



    // Constructor
    public Session_Manager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public Session_Manager() {

    }
    public Boolean getPincodeStatus()
    {
        return pref.getBoolean("pincode_status", false);
    }
    public void setPincodeStatus(Boolean pincode_status) {
        editor.putBoolean("pincode_status", pincode_status);
        editor.commit();
    }
    public String getStep(){
        return pref.getString("step","step");
    }
    public void setStep(String step){
        editor.putString("step",step);
        editor.commit();
    }
    public String getPinCode()
    {
        return pref.getString("pin_code", "No Pincode");
    }
    public void setPinCode(String pin_code) {
        editor.putString("pin_code", pin_code);
        editor.commit();
    }
    public String getUserName()
    {
        return pref.getString("name", "Zilfos User");
    }
    public void setUserName(String name) {
        editor.putString("name", name);
        editor.commit();
    }
    public String getUserId()
    {
        return pref.getString("UserId", " ");
    }
    public void setUserId(String UserId) {
        editor.putString("UserId", UserId);
        editor.commit();
    }

    public String getDeliveryId()
    {
        return pref.getString("DeliveryId", "1");
    }
    public void setDeliveryId(String DeliveryId) {
        editor.putString("DeliveryId", DeliveryId);
        editor.commit();
    }
    public String getContact_Name()
    {
        return pref.getString("Contact_Name", " ");
    }
    public void setContact_Name(String Contact_Name) {
        editor.putString("Contact_Name", Contact_Name);
        editor.commit();
    }
    public String getContact_Add1()
    {
        return pref.getString("Contact_Add1", " ");
    }
    public void setContact_Add1(String Contact_Add1) {
        editor.putString("Contact_Add1", Contact_Add1);
        editor.commit();
    }
    public String getContact_Add2()
    {
        return pref.getString("Contact_Add2", " ");
    }
    public void setContact_Add2(String Contact_Add2) {
        editor.putString("Contact_Add2", Contact_Add2);
        editor.commit();
    }
    public String getContact_Add3()
    {
        return pref.getString("Contact_Add3", " ");
    }
    public void setContact_Add3(String Contact_Add3) {
        editor.putString("Contact_Add3", Contact_Add3);
        editor.commit();
    }
    public String getContact_Ph()
    {
        return pref.getString("Contact_Ph", " ");
    }
    public void setContact_Ph(String Contact_Ph) {
        editor.putString("Contact_Ph", Contact_Ph);
        editor.commit();
    }
    public String getContact_Email()
    {
        return pref.getString("Contact_Email", " ");
    }
    public void setContact_Email(String Contact_Email) {
        editor.putString("Contact_Email", Contact_Email);
        editor.commit();
    }

    public String getCouponid()
    {
        return pref.getString("Couponid", " ");
    }
    public void setCouponid(String Couponid) {
        editor.putString("Couponid", Couponid);
        editor.commit();
    }
    public String getCouponName()
    {
        return pref.getString("CouponName", " ");
    }
    public void setCouponName(String CouponName) {
        editor.putString("CouponName", CouponName);
        editor.commit();
    }


    public String  getPinCodeId()
    {
        return pref.getString("pin_code_id", "");
    }
    public void setPinCodeId(String came_from_fp) {
        editor.putString("pin_code_id", came_from_fp);
        editor.commit();
    }
    public Boolean getFirstLogin()
    {
        return pref.getBoolean("first_login", false);
    }
    public void setFirstLogin(Boolean pin_code) {
        editor.putBoolean("first_login", pin_code);
        editor.commit();
    }
public Integer getcategoryId()
{
    return pref.getInt("category",0);
}
    public void setCategoryId(Integer categoryId) {
        editor.putInt("category", categoryId);
        editor.commit();
    }
    public String getPaymentMethod() {
        return pref.getString("payment_method", "");
    }
    public void setPaymentMethod(String payment_method) {
        editor.putString("payment_method", payment_method);
        editor.commit();
    }


//    public Integer getpaymentId()
//    {
//        return pref.getInt("payment_method",0);
//    }
//    public void getpaymentId(Integer getpaymentId) {
//        editor.putInt("payment_method", getpaymentId);
//        editor.commit();
//    }

    public  String getsubcategoryId()
    {
        return pref.getString("sub_category_id", "");
    }
    public void setsubCategoryId(String sub_category_id) {
        editor.putString("sub_category_id", sub_category_id);
        editor.commit();
    }

    public  String getProductId()
    {
        return pref.getString("product_id", "");
    }
    public void setProductId(String productId) {
        editor.putString("product_id", productId);
        editor.commit();
    }

//    public  String getUserId()
//    {
//        return pref.getString("user_id", "");
//    }
//    public void setUserId(String user_id) {
//        editor.putString("user_id", user_id);
//        editor.commit();
//    }

    public  String getUserEmail()
    {
        return pref.getString("email", "");
    }
    public void setUserEmail(String email) {
        editor.putString("email", email);
        editor.commit();
    }

    public  String getUserMobile()
    {
        return pref.getString("mobile", "");
    }
    public void setUserMobile(String mobile) {
        editor.putString("mobile", mobile);
        editor.commit();
    }







    public  String getLoginId()
    {
        return pref.getString("input_id", "");
    }
    public void setLoginId(String input_id) {
        editor.putString("input_id", input_id);
        editor.commit();
    }
    public  String getLoginPassword()
    {
        return pref.getString("input_password", "");
    }
    public void setLoginPassword(String input_password) {
        editor.putString("input_password", input_password);
        editor.commit();
    }

    public  Integer getPincodeId()
    {
        return pref.getInt("pincode_id", 0);
    }
    public void setPicodeId(Integer pincode_id)
    {
        editor.putInt("pincode_id", pincode_id);
        editor.commit();
    }
    public  String getProfileId()
    {
        return pref.getString("profileid", "");
    }
    public void setProfileId(String profileid) {
        editor.putString("profileid", profileid);
        editor.commit();
    }
    public Boolean getLoginStatus() {
        return pref.getBoolean("login_status", false);
    }

    public void setLoginStatus(Boolean b) {
        editor.putBoolean("login_status", b);
        editor.commit();
    }


    public String getSelectedAddress() {
        return pref.getString("selected_address", "");
    }

    public void setSelectedAddress(String selected_address) {
        editor.putString("selected_address", selected_address);
        editor.commit();
    }
    public String getFullTotal()
    {
        return pref.getString("FullTotal", "0");
    }
    public void setFullTotal(String FullTotal) {
        editor.putString("FullTotal", FullTotal);
        editor.commit();
    }
    public Boolean getshow_notification() {
        return pref.getBoolean("show_notification", false);
    }

    public void setshow_notification(Boolean show_notification) {
        editor.putBoolean("show_notification", show_notification);
        editor.commit();
    }

    public int getWalletAmount() {
        return pref.getInt("wallet_amount", 0);
    }

    public void setWalletAmount(int wallet_amount) {
        editor.putInt("wallet_amount", wallet_amount);
        editor.commit();
    }
    public String getDeliveryFeeSafe() {
        return pref.getString("DeliveryFeeSafe", "0");
    }

    public void setDeliveryFeeSafe(String DeliveryFeeSafe) {
        editor.putString("DeliveryFeeSafe", DeliveryFeeSafe);
        editor.commit();
    }
    public int getLocationGiven() {
        return pref.getInt("LocationGiven", 0);
    }

    public void setLocationGiven(int LocationGiven) {
        editor.putInt("LocationGiven", LocationGiven);
        editor.commit();
    }
    public String getPincode_start() {
        return pref.getString("pincode", "");
    }

    public void setPincode_start(String pincode) {
        editor.putString("pincode", pincode);
        editor.commit();
    }

    public String getTimeline() {
        return pref.getString("auth_key", "");
    }

    public void setTimeline(String authkey) {
        editor.putString("Timeline", authkey);
        editor.commit();
    }

//    public void setProfile_Info(ProfileInfo_Objects profileInfo) {
//        Gson gson = new Gson();
//        String json = gson.toJson(profileInfo);
//        editor.putString("profileInfo_object", json);
//        editor.commit();
//    }
//
//    public ProfileInfo_Objects getProfile_Info() {
//        Gson gson = new Gson();
//        String json = pref.getString("profileInfo_object", "");
//        ProfileInfo_Objects user = gson.fromJson(json, ProfileInfo_Objects.class);
//        return user;
//    }


    public void clear_session() {
        editor.clear();
        editor.commit();
    }

    public int getInitialPage() {
        return pref.getInt("initial_page", 0);
    }
    public void setInitialPage(int initial_page) {
        editor.putInt("initial_page", initial_page);
        editor.commit();
    }
}
