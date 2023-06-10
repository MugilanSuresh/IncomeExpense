package com.example.income_expense.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.example.income_expense.Utils.Session_Manager;
import com.example.income_expense.Utils.Webservice;

import org.json.JSONException;
import org.json.JSONObject;

public class UpdateProfile extends AppCompatActivity {
    Button update_profile;
    EditText person_name,email_id,location;
    ProgressDialog progressDialog;
    SharedPreferences prefs;
    Session_Manager session_manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        update_profile = findViewById(R.id.update_details);

        person_name = findViewById(R.id.person_name);
        email_id = findViewById(R.id.email_id);
        location = findViewById(R.id.location);
        session_manager = new Session_Manager(this);
        progressDialog = new ProgressDialog(UpdateProfile.this);
        prefs = getSharedPreferences("session", 0);

        update_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailToText = email_id.getText().toString();

                if(person_name.getText().toString().isEmpty()){
                    Toast.makeText(UpdateProfile.this, "Enter Name", Toast.LENGTH_SHORT).show();
                }
                else if (email_id.getText().toString().isEmpty()){
                    Toast.makeText(UpdateProfile.this, "Enter Email Id", Toast.LENGTH_SHORT).show();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()){
                    Toast.makeText(UpdateProfile.this, "Invalid Email Id", Toast.LENGTH_SHORT).show();
                }
                else if (location.getText().toString().isEmpty()){
                    Toast.makeText(UpdateProfile.this, "Enter Location", Toast.LENGTH_SHORT).show();
                }
                else{

                    upadted();
//                    Toast.makeText(UpdateProfile.this, "Updated", Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(UpdateProfile.this, HomeActivity.class);
//                    startActivity(i);
                }

            }

        });
    }

    private void upadted() {
        progressDialog = ProgressDialog.show(UpdateProfile.this, "Loading", "Please Wait", true);
        progressDialog.setMessage("Updating");
        progressDialog.setCancelable(false);

        String name = person_name.getText().toString();
        String email = email_id.getText().toString();
        String location1 = location.getText().toString();

        JSONObject jsonObject = new JSONObject();
        String id=session_manager.getUserId();
        try {
            jsonObject.accumulate("user_id",id);
            jsonObject.accumulate("name",name);
            jsonObject.accumulate("email",email);
            jsonObject.accumulate("city",location1);


        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("profile update url"+ Webservice.getupdate_profile+"request"+jsonObject);
        AndroidNetworking.post(Webservice.getupdate_profile)
                .addJSONObjectBody(jsonObject)
                .setOkHttpClient(Webservice.getUnsafeOkHttpClient())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            System.out.println("mugilan upadte response" + response);

                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.optString("status");
                            if (status.equalsIgnoreCase("1")) {
                                JSONObject id_object = jsonObject.getJSONObject("details");

                                String mobile = id_object.optString("mobile");
                                String name = id_object.optString("name");
                                String email = id_object.optString("email");
                                String city = id_object.optString("city");
                                String id1 = id_object.optString("user_id");
                                String step = id_object.optString("step");

                                session_manager.setUserId(id1);
                                session_manager.setUserMobile(mobile);
                                session_manager.setUserName(name);
                                session_manager.setUserEmail(email);
                                session_manager.setSelectedAddress(city);
                                session_manager.setStep(step);

                                Intent i = new Intent(UpdateProfile.this,HomeActivity.class);
                                startActivity(i);
                                finish();




//                                String step = id_object.optString("step");
//
//                                if (step.equalsIgnoreCase("2")) {
//                                    session_manager.setInitialPage(2);
//                                    Intent intent = new Intent(OtpVerificationActivity.this, UpdateProfileActivity.class);
//                                    startActivity(intent);
//                                    finish();
//                                } else if (step.equalsIgnoreCase("3")) {
//                                    session_manager.setInitialPage(3);
//                                    Intent intent = new Intent(OtpVerificationActivity.this, HomeContentActivity.class);
//                                    startActivity(intent);
//                                    finish();
//                                }
                                Toast.makeText(UpdateProfile.this, jsonObject.optString("message"), Toast.LENGTH_SHORT).show();
                            }  else {
                                Toast.makeText(UpdateProfile.this, jsonObject.optString("message"), Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        Log.e("mugilan",anError.getMessage());

                    }
                });

    }
}