package com.example.income_expense.Utils;

import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

public class Webservice {

//    public static String Baseurl = "http://7amshop.com/mobile/api/";


//    public static String Baseurl = "https://technovanza2k18.in/shopma/mobile/api/";
   // public static String Baseurl = "http://shopma.in/shopma/mobile/api/";
    //public static String Baseurl = "http://3.12.170.234/gfa/api/";
    public static String Baseurl = "https://xplorant.in/smartproject/trakingapp/api/";
   // public static String Baseurl = "https://arasansupermarket.com/arasan/api/";
//    public static String Baseurl = "http://3.6.178.77/arasan/api/";
//    public static String Baseurl = "https://arasansupermarket.in/arasan/api/";

    public static String getregister_user = Baseurl + "register_user.php";
    public static String getcheck_otp = Baseurl + "check_otp.php";
    public static String getupdate_profile = Baseurl + "update_profile.php";
    public static String getcategory = Baseurl + "category.php";
    public static String getpayment_method = Baseurl + "payment_method.php";
    public static String getadd_income = Baseurl + "add_income.php";
    public static String getadd_expense = Baseurl + "add_expence.php";
    public static String getuser_details = Baseurl + "user_details.php";
    public static String getincomedata = Baseurl + "income.php";
    public static String getexpensedata = Baseurl + "expence.php";
















    public static String notification = Baseurl + "home/notifications";
    public static String product_detalis=Baseurl+"product-detalis";


    public static String addwallet = Baseurl + "add/wallet";
    public static String add_wallet= Baseurl+"add/user/wallet";
    public static String home_content= Baseurl+"home_content";
    public static String cancel_order = Baseurl+"cancel-order";
    public static String get_log_out = Baseurl+"logout";
    public static String get_user_info = Baseurl+"user_info";
    public static String get_deliver_type = Baseurl+"user/delivery_type";






    public static String get_sub_category = Baseurl + "categories";
   public static String get_sub_sub_category = Baseurl + "sub/categories";




    public static String get_search = Baseurl+ "search";
    public static String notifications = Baseurl + "notifications";
    public static String user_notifications = Baseurl + "user_notifications";
    public static String verify_promocode = Baseurl + "coupon";
    public static String get_banner = Baseurl+ "banners";
    public static String get_otpverify = Baseurl+ "verifyotp";
    public static String get_forgetpassword = Baseurl + "forgot-pwd";
    public static String get_login = Baseurl + "login";
    public static String userRegistration = Baseurl + "new/user/reg";
    public static String get_updateprofile = Baseurl + "update-profile";
    public static String get_resetpassword = Baseurl + "reset-pwd";
    public static String check_deliveryfee= Baseurl + "delivery-type";
    public static String add_address = Baseurl + "add/address";
    public static String show_address= Baseurl + "show/address";
    public static String getDeleteAddress = Baseurl + "delete/address";
    public static String default_address= Baseurl + "set/default/address";
    public static String update_address= Baseurl + "update/address";
    public static String get_order = Baseurl + "init/order";
    public static String show_orders = Baseurl + "orders";
    public static String show_sub_orders = Baseurl + "order-items";
    public static String re_orders = Baseurl + "reorder";
    public static String get_otpresend = Baseurl+ "resend-otp";
    public static String guestLogin = Baseurl+ "guestlogin";

    public static String add_to_cart = Baseurl + "add/cart";
    public static String show_cart = Baseurl + "view/cart";
    public static String update_cart = Baseurl + "update/cart";
    public static String delete_cart = Baseurl + "delete/cart";

    public static String delivery_slot = Baseurl + "slots";
    public static String wallet_amount = Baseurl + "wallets";
    public static String wallet_amount_reduction = Baseurl + "commission/percentage";
    public static String payment_response=Baseurl+"online/payment/init";
    public static String contact_us=Baseurl+"contact_us";
    public static String check_postalCode=Baseurl+"check-postal-code";

    public static String add_favourites = Baseurl + "add/favourites";
    public static String delete_favourites= Baseurl + "delete/favourites";
    public static String user_favourites = Baseurl + "user/favourites";
    public static String user_favouritescount = Baseurl + "user/favouritescount";
    public static String getdefault_address = Baseurl + "default/address";

    //------------------------------------------------------------------------------------------------------------------
    public static String show_coupons= Baseurl+"show/coupons";



    public static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
