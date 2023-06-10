package com.example.income_expense.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.income_expense.R;
import com.example.income_expense.Utils.Session_Manager;

public class SplashScreen extends AppCompatActivity {

    SharedPreferences prefs;
    ProgressDialog progressDialog;
    Session_Manager session_manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        progressDialog = new ProgressDialog(SplashScreen.this);
        session_manager=new Session_Manager(this);
        prefs = getSharedPreferences("session", 0);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(session_manager.getInitialPage() == 1){
                    Intent i = new Intent(SplashScreen.this, HomeActivity.class);
                    startActivity(i);
                    finish();
                }else {
                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
//                Intent intent=new Intent(SplashScreen.this, MainActivity.class);
//                startActivity(intent);
//                finish();

            }
        },2000);
    }
}
