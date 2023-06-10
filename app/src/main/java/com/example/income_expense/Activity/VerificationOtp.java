package com.example.income_expense.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.income_expense.R;
import com.example.income_expense.Utils.Kprogress_Loader;
import com.example.income_expense.Utils.Session_Manager;
import com.example.income_expense.Utils.Webservice;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class VerificationOtp extends AppCompatActivity {
    EditText otp1, otp2, otp3, otp4;
    Button verify_otp;
    LinearLayout timer;
    ProgressDialog progressDialog;
    SharedPreferences prefs;
    Session_Manager session_manager;
    TextView textView, resend_otp, resend_otphide, change_email;
    Kprogress_Loader progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_otp);

        textView = findViewById(R.id.timer);
        resend_otp = findViewById(R.id.resend_otp);
        resend_otphide = findViewById(R.id.resend_otphide);
        otp1 = findViewById(R.id.otp_1);
        otp2 = findViewById(R.id.otp_2);
        otp3 = findViewById(R.id.otp_3);
        otp4 = findViewById(R.id.otp_4);
        timer = findViewById(R.id.timer_hide);
        change_email = findViewById(R.id.change_mail);
        session_manager = new Session_Manager(this);
        progressDialog = new ProgressDialog(VerificationOtp.this);
        prefs = getSharedPreferences("session", 0);

        progress = new Kprogress_Loader();
        progress.Initial(this);


        Intent intent=getIntent();
        String status=intent.getStringExtra("status");

        //Intent intent = getIntent();
        //String user_id = intent.getStringExtra("user_id");


        change_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VerificationOtp.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        verify_otp = findViewById(R.id.otp_button);

        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {

                NumberFormat f = new DecimalFormat("00");
                long sec = (millisUntilFinished / 1000) % 60;

                textView.setText(f.format(sec));

            }

            public void onFinish() {
                resend_otphide.setVisibility(View.GONE);
                resend_otp.setVisibility(View.VISIBLE);
                timer.setVisibility(View.INVISIBLE);
                textView.setText("00");

            }

        }.start();

        resend_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resend_otphide.setVisibility(View.VISIBLE);
                resend_otp.setVisibility(View.GONE);
                timer.setVisibility(View.VISIBLE);
                new CountDownTimer(30000, 1000) {

                    public void onTick(long millisUntilFinished) {

                        NumberFormat f = new DecimalFormat("00");
                        long sec = (millisUntilFinished / 1000) % 60;

                        textView.setText(f.format(sec));

                    }

                    public void onFinish() {
                        resend_otphide.setVisibility(View.GONE);
                        resend_otp.setVisibility(View.VISIBLE);
                        timer.setVisibility(View.INVISIBLE);
                        textView.setText("00");

                    }

                }.start();


            }
        });


        otp1.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int textlength1 = otp1.getText().length();
                if (textlength1 >= 1) {
                    otp2.requestFocus();
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        });

        otp2.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int textlength2 = otp2.getText().length();
                if (textlength2 >= 1) {
                    otp3.requestFocus();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        });

        otp3.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int textlength3 = otp3.getText().length();
                if (textlength3 >= 1) {
                    otp4.requestFocus();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        });

        verify_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (otp1.getText().toString().isEmpty() ||
                        otp2.getText().toString().isEmpty() ||
                        otp3.getText().toString().isEmpty() ||
                        otp4.getText().toString().isEmpty()) {
                    Toast.makeText(VerificationOtp.this, "Enter otp", Toast.LENGTH_SHORT).show();
                } else {
                    String otp_v = otp1.getText().toString() + "" + otp2.getText().toString() + "" + otp3.getText().toString() + "" + otp4.getText().toString();
                    otp();
//                    Intent i = new Intent(VeriicationOtp.this, UpdateProfile.class);
//                    startActivity(i);
                }
            }
        });

        otp2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_DEL) {
                    //if(i == KeyEvent.KEYCODE_DEL){
                    otp1.requestFocus();
                    System.out.println("1");
                }
                return false;
            }
        });
        otp3.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_DEL) {
                    otp2.requestFocus();
                    System.out.println("2");
                }
                return false;
            }
        });
        otp4.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_DEL) {
                    otp3.requestFocus();
                    System.out.println("3");
                }
                return false;
            }
        });
    }


    private void otp() {
        progressDialog = ProgressDialog.show(VerificationOtp.this, "Loading", "Please Wait", true);
        progressDialog.setMessage("Verifying");
        progressDialog.setCancelable(false);

        String otp_v=otp1.getText().toString()+""+otp2.getText().toString()+""+otp3.getText().toString()+""+otp4.getText().toString();
        JSONObject jsonObject = new JSONObject();

        String id=session_manager.getUserId();
//        final SharedPreferences prefs = getSharedPreferences("session", MODE_PRIVATE);
//        String id=prefs.getString("user_id","");

        System.out.println("mugilan id"+id);

        try {
            jsonObject.accumulate("user_id", id);
            jsonObject.accumulate("otp", otp_v);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("ajeeth OtpPage url "+Webservice.getcheck_otp+" request "+jsonObject);
        AndroidNetworking.post(Webservice.getcheck_otp)
                .addJSONObjectBody(jsonObject)
                .setOkHttpClient(Webservice.getUnsafeOkHttpClient())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            System.out.println("mugil OtpPage response" + response);

                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.optString("status");
                            System.out.println("status num" + status);
                            if (status.equalsIgnoreCase("1")) {
                                JSONObject id_object = jsonObject.getJSONObject("details");

                                String mobile = id_object.optString("mobile");
                                String name = id_object.optString("name");
                                String email = id_object.optString("email");
                                String city = id_object.optString("city");
                                String id1 = id_object.optString("user_id");
                                String step = id_object.optString("step");
                                System.out.println("step77777----------" + step);


                                session_manager.setUserId(id1);
                                session_manager.setUserMobile(mobile);
                                session_manager.setUserName(name);
                                session_manager.setUserEmail(email);
                                session_manager.setSelectedAddress(city);

                                if (status.equalsIgnoreCase("2")){
                                    Intent i = new Intent(VerificationOtp.this,HomeActivity.class);
                                    startActivity(i);
                                    finish();
                                }else if (status.equalsIgnoreCase("1")){
                                    Intent i = new Intent(VerificationOtp.this,UpdateProfile.class);
                                    startActivity(i);
                                    finish();
                                }
                                if (step.equalsIgnoreCase("2")) {
                                    session_manager.setInitialPage(1);
                                    Intent intent = new Intent(VerificationOtp.this, UpdateProfile.class);
                                    startActivity(intent);
                                    finish();
                                } else if (step.equalsIgnoreCase("3")) {
                                    session_manager.setInitialPage(2);
                                    Intent intent = new Intent(VerificationOtp.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
//                                Toast.makeText(VerificationOtp.this, jsonObject.optString("message"), Toast.LENGTH_SHORT).show();
//                                Intent i = new Intent(VerificationOtp.this,UpdateProfile.class);
//                                startActivity(i);
//                                finish();
//                            }else if (status.equalsIgnoreCase("2")){
//                                Toast.makeText(VerificationOtp.this, "Welcome   "+id, Toast.LENGTH_SHORT).show();

                            }else{
                                Toast.makeText(VerificationOtp.this, jsonObject.optString("message"), Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Log.e("ajeeth",anError.getMessage());
                    }
                });


//        JSONObject jsonObject = new JSONObject();
//        String otp_v=otp1.getText().toString()+""+otp2.getText().toString()+""+otp3.getText().toString()+""+otp4.getText().toString();
//        final SharedPreferences prefs = getSharedPreferences("session", MODE_PRIVATE);
//        String id=session_manager.getUserId();
//        try {
//            jsonObject.accumulate("user_id", id);
//            jsonObject.accumulate("otp", otp_v);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("mugilan otp url "+ Webservice.getcheck_otp +" req "+jsonObject);
//        AndroidNetworking.post(Webservice.getcheck_otp)
//                .addJSONObjectBody(jsonObject)
//                .setOkHttpClient(Webservice.getUnsafeOkHttpClient())
//                .setPriority(Priority.MEDIUM)
//                .build()
//                .getAsString(new StringRequestListener() {
//                    @Override
//                    public void onResponse(String response) {
//                        progressDialog.dismiss();
//
//                        try {
//                            JSONObject jsonObject = new JSONObject(response);
//                            String status = jsonObject.optString("status");
//                            String message = jsonObject.optString("message");
//                            System.out.println(status);
//                            if(status.equalsIgnoreCase("1")){
//
//                                String mobile = jsonObject.optString("mobile");
//                                String name = jsonObject.optString("name");
//                                String email = jsonObject.optString("email");
//                                String id = jsonObject.optString("user_id");
//
//                                session_manager.setUserId(id);
//                                session_manager.setUserEmail(email);
//                                session_manager.setUserName(name);
//                                session_manager.setUserMobile(mobile);
//
//
//
//                                Intent i = new Intent(VerificationOtp.this,UpdateProfile.class);
//                                startActivity(i);
//                                finish();
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//
//                    }
//                });
//
//
//
//    }


//    public void onBackPressed() {
//        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(
//                VerificationOtp.this);
//        alertDialogBuilder.setTitle("Exit App");
//        alertDialogBuilder
//                .setMessage("You cant login unless You verify your Mobile Number,Do you want to move back?")
//                .setCancelable(false)
//                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog,int id) {
//                        Intent intent = new Intent(VerificationOtp.this, MainActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
//                    }
//                })
//                .setNegativeButton("No",new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog,int id) {
//                        dialog.cancel();
//                    }
//                });
//        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.show();
//    }

    }
}