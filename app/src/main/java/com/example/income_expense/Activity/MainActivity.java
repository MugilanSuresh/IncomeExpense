package com.example.income_expense.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.income_expense.R;
import com.example.income_expense.Utils.CheckInternet;
import com.example.income_expense.Utils.Session_Manager;
import com.example.income_expense.Utils.Webservice;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText mobile_num;
    Button login_button;
    ProgressDialog progressDialog;
    SharedPreferences prefs;
    Session_Manager session_manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(MainActivity.this);
        prefs = getSharedPreferences("session", 0);
        session_manager = new Session_Manager(this);
        mobile_num = findViewById(R.id.mobile);
        login_button = findViewById(R.id.login_button);


        AndroidNetworking.initialize(getApplicationContext());

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mobile_num.getText().toString().isEmpty() || mobile_num.length() != 10) {
                    Toast.makeText(MainActivity.this, "Enter 10 Digit Mobile Number", Toast.LENGTH_SHORT).show();
                } else {
                    login(mobile_num.getText().toString());


//                    Intent i = new Intent(MainActivity.this, VerificationOtp.class);
//                    startActivity(i);
                }
            }
        });
    }

    public void login(String mobile) {
        progressDialog = ProgressDialog.show(MainActivity.this, "Loading", "Please Wait", true);
        progressDialog.setMessage("Verifying");
        progressDialog.setCancelable(false);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.accumulate("mobile", mobile);
            jsonObject.accumulate("fcm_id", "eyurefgrueifrueiwuirerep");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("mugilan login url "+Webservice.getregister_user +" req "+jsonObject);
        AndroidNetworking.post(Webservice.getregister_user)
                .addJSONObjectBody(jsonObject)
                .setOkHttpClient(Webservice.getUnsafeOkHttpClient())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.e("abbas1", response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.e("abbas", "Login Response :" + response);
                            String status = jsonObject.optString("status");
                            String message = jsonObject.optString("message");
                            if (status.equalsIgnoreCase("1") || status.equalsIgnoreCase("2")) {

                                JSONObject id_object = jsonObject.getJSONObject("details");
                                String user_id = id_object.optString("user_id");
                                String name = id_object.optString("name");
                                String email = id_object.optString("email");
                                String city = id_object.optString("city");
                                String step = id_object.optString("step");
                                String phone_number = id_object.optString("mobile");

                                System.out.println("step---------" + step);
                                System.out.println("ajeeth----------- id " + user_id);
                                session_manager.setUserId(user_id);
                                session_manager.setUserEmail(email);
                                session_manager.setSelectedAddress(city);
                                session_manager.setUserName(name);
                                session_manager.setUserMobile(phone_number);

                                mobile_num.setText("");
                                Intent intent = new Intent(MainActivity.this, VerificationOtp.class);
                                intent.putExtra("status", status);
                                intent.putExtra("user_id",user_id);
                                intent.putExtra("name", name);
                                intent.putExtra("email",email);
                                intent.putExtra("step",step);
                                startActivity(intent);
                                finish();
                            }

                            else if(status.equalsIgnoreCase("")){
                                final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this)
                                        .setTitle("Maintenance")
                                        .setMessage("The app is in Maintenance Please come back later")
                                        .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                finishAffinity();

                                            }
                                        })

                                        .setCancelable(false);
                                final AlertDialog dialog = alert.create();
                                dialog.show();
                            }

//                            else {
//                                if (status.equalsIgnoreCase("0")) {
//                                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
//                                } else {
//                                    JSONObject id_object = jsonObject.getJSONObject("data");
//                                    String otp_verify = id_object.optString("otp_verify");
//                                    if (otp_verify.equals("NOT_VERIFIED")) {
//                                        SharedPreferences.Editor editor = prefs.edit();
//                                        editor.putString("otp_register_id", id_object.optString("id"));
//                                        editor.apply();
////                                        resend(id_object.optString("id"));
//                                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                        Log.e("abbas", error.toString());
                        if (error.getErrorCode() != 0) {

                            Log.e("abbas", "onError errorCode : " + error.getErrorCode());
                            Log.e("abbas", "onError errorBody : " + error.getErrorBody());
                            Log.e("abbas", "onError errorDetail : " + error.getErrorDetail());

                        } else {

                            Log.e("abbas", "onError errorDetail : " + error.getErrorDetail());
                        }
                        if (CheckInternet.isConnected(MainActivity.this)) {
                            Toast.makeText(MainActivity.this, "Server Error please try again later", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "You are not connected to Internet please check your internet connection", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


}

//    public void login(String mobile) {
//        progressDialog = ProgressDialog.show(MainActivity.this, "Loading", "Please Wait", true);
////        progressDialog.setMessage("Loging in to your account");
//        progressDialog.setCancelable(false);
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.accumulate("mobile", mobile);
//            jsonObject.accumulate("fcm_token", "pzrltt45545445465451vfdv1df32vfv");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        System.out.println("mugilan login url " + Webservice.getregister_user + " req " + jsonObject);
//        AndroidNetworking.post(Webservice.getregister_user)
//                .addJSONObjectBody(jsonObject)
//                .setOkHttpClient(Webservice.getUnsafeOkHttpClient())
//                .setPriority(Priority.MEDIUM)
//                .build()
//                .getAsString(new StringRequestListener() {
//                    @Override
//                    public void onResponse(String response) {
//                        progressDialog.dismiss();
//                        try {
//                            JSONObject jsonObject1 = null;
//                            try {
//                                jsonObject1 = new JSONObject(response);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                            String status = jsonObject1.optString("status");
//                            String message = jsonObject1.optString("message");
//                            System.out.println(status);
//                            if(status.equalsIgnoreCase("1")){
//                                JSONObject id_object = jsonObject1.getJSONObject("data");
//                                String id = id_object.optString("id");
//                                session_manager.setUserId(id);
//
//                                String user_id = id_object.optString("user_id");
//                                String name = id_object.optString("name");
//                                String mobile = id_object.optString("mobile");
//                                String city = id_object.optString("city");
//                                String email = id_object.optString("email");
//                                String step = id_object.optString("step");
//
//                                session_manager.setUserName(name);
//                                session_manager.setUserMobile(mobile);
//                                session_manager.setUserEmail(email);
//
//                                Intent i = new Intent(MainActivity.this, VerificationOtp.class);
//                                    i.putExtra("message",message);
//                                    i.putExtra("user_id",user_id);
//                                    i.putExtra("mobile",mobile);
//                                    i.putExtra("name",name);
//                                    i.putExtra("email",email);
//
//                                    startActivity(i);
//                                }
//                                else{
//                                    Toast.makeText(MainActivity.this, "Somthing went wrong", Toast.LENGTH_SHORT).show();
//                                }
////                        } catch (JSONException e) {
////                            e.printStackTrace();
////                        }
//
//
////                    @Override
////                    public void onError(ANError anError) {
////
////                    }
////                });
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
////    public void emailValitator(EditText email_id){
////        String emailToText = email_id.getText().toString();
////
////        if (!emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()){
////            Intent i =new  Intent(MainActivity.this, VerificationOtp.class);
////            startActivity(i);
////        }else {
////            Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
////
////        }
////    }
//
//                    }
//
//                    @Override
//                    public void onError(ANError error) {
//                        Log.e("abbas", error.toString());
//                        progressDialog.dismiss();
//                        if (error.getErrorCode() != 0) {
//
//                            Log.e("abbas", "onError errorCode : " + error.getErrorCode());
//                            Log.e("abbas", "onError errorBody : " + error.getErrorBody());
//                            Log.e("abbas", "onError errorDetail : " + error.getErrorDetail());
//
//                        } else {
//
//                            Log.e("abbas", "onError errorDetail : " + error.getErrorDetail());
//                        }
//                        if (CheckInternet.isConnected(MainActivity.this)) {
//                            Toast.makeText(MainActivity.this, "Server Error please try again later", Toast.LENGTH_LONG).show();
//                        } else {
//                            Toast.makeText(MainActivity.this, "You are not connected to Internet please check your internet connection", Toast.LENGTH_LONG).show();
//                        }
//                    }
//
//                });
//    }
//
//    }


