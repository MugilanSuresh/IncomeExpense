package com.example.income_expense.Activity;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.income_expense.R;
import com.example.income_expense.Utils.Session_Manager;
import com.example.income_expense.Utils.Webservice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class IncomeActivity extends AppCompatActivity {

    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> category_list = new ArrayList<>();
    ArrayList<String> arrayList1 = new ArrayList<>();
    ArrayList<String> payment_list1 = new ArrayList<>();
    ImageView back;
    EditText note_txt;
    TextView textView_addincome,textView_addexpance,income_amount,payment_text,expense_amount,category_text,income_date,expence_date;
    LinearLayout income_layout,expance_layout,category_butt,pay_method_butt,calender_button,calender1;
    Button income1,expance1,add_income,add_expense;
    CardView income_cardview,expance_cardview;
    ProgressDialog progressDialog;
    SharedPreferences prefs;
    Session_Manager session_manager;
    String dobtextfrom = "";
    String dobtextfrom1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

//        income_date.getText().toString();
        note_txt = findViewById(R.id.note_txt);
        income_date = findViewById(R.id.income_date);
        expence_date =findViewById(R.id.expence_date);
        add_income = findViewById(R.id.add_income);
        add_expense = findViewById(R.id.add_expense);
        payment_text=findViewById(R.id.payment_text);
        category_text=findViewById(R.id.category_text);
        progressDialog = new ProgressDialog(IncomeActivity.this);
        prefs = getSharedPreferences("session", 0);
        session_manager = new Session_Manager(this);
        pay_method_butt = findViewById(R.id.pay_method_butt);
        category_butt = findViewById(R.id.category_butt);
        income_amount = findViewById(R.id.income_amt);
        expense_amount = findViewById(R.id.expense_amt);
        calender1 = findViewById(R.id.calender1);
        calender_button=findViewById(R.id.calender_button);
        back = findViewById(R.id.back1);
        income1 = findViewById(R.id.income1);
        expance1 = findViewById(R.id.expance1);
        textView_addincome = findViewById(R.id.textView_addincome);
        textView_addexpance = findViewById(R.id.textView_addexpance);
        income_layout = findViewById(R.id.income_layout);
        expance_layout = findViewById(R.id.expance_layout);
        income_cardview = findViewById(R.id.income_cardview);
        expance_cardview = findViewById(R.id.expance_cardview);
        String income =income_amount.getText().toString();
        System.out.println("amount+++++++++++++++++" + income);

        expance1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textView_addincome.setVisibility(View.GONE);
                textView_addexpance.setVisibility(View.VISIBLE);
                income_layout.setVisibility(View.GONE);
                expance_layout.setVisibility(View.VISIBLE);
                income_cardview.setVisibility(View.GONE);
                expance_cardview.setVisibility(View.VISIBLE);
//                income_date.setText("");
                income_amount.setText("");
                add_income.setVisibility(View.GONE);
                add_expense.setVisibility(View.VISIBLE);
            }
        });

        income1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView_addincome.setVisibility(View.VISIBLE);
                textView_addexpance.setVisibility(View.GONE);
                income_layout.setVisibility(View.VISIBLE);
                expance_layout.setVisibility(View.GONE);
                income_cardview.setVisibility(View.VISIBLE);
                expance_cardview.setVisibility(View.GONE);
                expence_date.setText("");
                expense_amount.setText("");
                category_text.setText("");
                payment_text.setText("");
                add_expense.setVisibility(View.GONE);
                add_income.setVisibility(View.VISIBLE);
            }
        });

        pay_method_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner_view1();
            }
        });

        category_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner_view();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        calender1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateLayout1();
            }
        });

        calender_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateLayout();

            }
        });

        add_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(expence_date.getText().toString().isEmpty()){
                    Toast.makeText(IncomeActivity.this, "Select Date", Toast.LENGTH_SHORT).show();
                }
                else if (expense_amount.getText().toString().isEmpty()){
                    Toast.makeText(IncomeActivity.this, "Enter Amount", Toast.LENGTH_SHORT).show();
                }
                else if(category_text.getText().toString().isEmpty()){
                    Toast.makeText(IncomeActivity.this, "Select Category", Toast.LENGTH_SHORT).show();
                }
                else if (payment_text.getText().toString().isEmpty()){
                    Toast.makeText(IncomeActivity.this, "Select Payment Method", Toast.LENGTH_SHORT).show();
                }
                else if (note_txt.getText().toString().isEmpty()){
                    Toast.makeText(IncomeActivity.this, "Enter Notes", Toast.LENGTH_SHORT).show();

                }else {
                    addexpencedetails(expence_date.getText().toString(),expense_amount.getText().toString(),category_text.getText().toString(),payment_text.getText().toString(),note_txt.getText().toString());
                }
                }
//                progressDialog = ProgressDialog.show(IncomeActivity.this, "", "Uploading", true);
//                progressDialog.setCancelable(true);
//
//                JSONObject jsonObject = new JSONObject();
//                String id = session_manager.getUserId();
//                String note = note_txt.getText().toString();
//                try {
//                    jsonObject.accumulate("user_id",id);
//                    jsonObject.accumulate("income_date", dobtextfrom);
//                    jsonObject.accumulate("amount",income);
//                    jsonObject.accumulate("category",category_text);
//                    jsonObject.accumulate("payment_method", payment_text);
//                    jsonObject.accumulate("note", note);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                System.out.println("mugil addincome URL "+ Webservice.getadd_income+" request "+jsonObject);
//                AndroidNetworking.post(Webservice.getadd_income)
//                        .addJSONObjectBody(jsonObject)
//                        .setOkHttpClient(Webservice.getUnsafeOkHttpClient())
//                        .setPriority(Priority.MEDIUM)
//                        .build()
//                        .getAsString(new StringRequestListener() {
//                            @Override
//                            public void onResponse(String response) {
//                                try {
//                                    JSONObject jsonObject1 = new JSONObject(response);
//                                    String status = jsonObject1.optString("status");
//                                    if (status.equalsIgnoreCase("1")){
//                                        Toast.makeText(IncomeActivity.this, jsonObject1.optString("message"), Toast.LENGTH_SHORT).show();
//                                        onBackPressed();
//                                    }else {
//                                        Toast.makeText(IncomeActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
//                                    }
//
//
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                            @Override
//                            public void onError(ANError anError) {
//                            }
//                        });
            });

        add_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(income_date.getText().toString().isEmpty()){
                    Toast.makeText(IncomeActivity.this, "Select Date", Toast.LENGTH_SHORT).show();
                }
                else if (income_amount.getText().toString().isEmpty()){
                    Toast.makeText(IncomeActivity.this, "Enter Amount", Toast.LENGTH_SHORT).show();
                }
                else if(category_text.getText().toString().isEmpty()){
                    Toast.makeText(IncomeActivity.this, "Select Category", Toast.LENGTH_SHORT).show();
                }
                else if (payment_text.getText().toString().isEmpty()){
                    Toast.makeText(IncomeActivity.this, "Select Payment Method", Toast.LENGTH_SHORT).show();
                }
                else if (note_txt.getText().toString().isEmpty()){
                    Toast.makeText(IncomeActivity.this, "Enter Notes", Toast.LENGTH_SHORT).show();

                }else{
                    addincomedetails(income_date.getText().toString(),income_amount.getText().toString(),
                            category_text.getText().toString(),payment_text.getText().toString(),note_txt.getText().toString());

                }

            }
        });
    }

    private void addexpencedetails(String toString, String expenceAmount, String categoryText, String paymentMethod, String noteText) {

        progressDialog = ProgressDialog.show(IncomeActivity.this, "", "Uploading", true);
        progressDialog.setCancelable(true);

        JSONObject jsonObject = new JSONObject();
        String id = session_manager.getUserId();
        try {
            jsonObject.accumulate("user_id",id);
            jsonObject.accumulate("income_date", dobtextfrom1);
            jsonObject.accumulate("amount",expenceAmount);
            jsonObject.accumulate("category",categoryText);
            jsonObject.accumulate("payment_method", paymentMethod);
            jsonObject.accumulate("note", noteText);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("mugil addexpence URL "+ Webservice.getadd_expense+" request "+jsonObject);
        AndroidNetworking.post(Webservice.getadd_expense)
                .addJSONObjectBody(jsonObject)
                .setOkHttpClient(Webservice.getUnsafeOkHttpClient())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject1 = new JSONObject(response);
                            String status = jsonObject1.optString("status");
                            if (status.equalsIgnoreCase("1")){
                                Toast.makeText(IncomeActivity.this, jsonObject1.optString("message"), Toast.LENGTH_SHORT).show();
                                onBackPressed();
                            }else {
                                Toast.makeText(IncomeActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });



    }

    private void addincomedetails(String incomeDate, String incomeAmount, String categoryText, String paymentMethod, String noteText) {
                progressDialog = ProgressDialog.show(IncomeActivity.this, "", "Uploading", true);
                progressDialog.setCancelable(true);

                JSONObject jsonObject = new JSONObject();
                String id = session_manager.getUserId();
                String note = note_txt.getText().toString();
                try {
                    jsonObject.accumulate("user_id",id);
                    jsonObject.accumulate("income_date", dobtextfrom);
                    jsonObject.accumulate("amount",incomeAmount);
                    jsonObject.accumulate("category",categoryText);
                    jsonObject.accumulate("payment_method", paymentMethod);
                    jsonObject.accumulate("note", noteText);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println("mugil addincome URL "+ Webservice.getadd_income+" request "+jsonObject);
                AndroidNetworking.post(Webservice.getadd_income)
                        .addJSONObjectBody(jsonObject)
                        .setOkHttpClient(Webservice.getUnsafeOkHttpClient())
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsString(new StringRequestListener() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                   JSONObject jsonObject1 = new JSONObject(response);
                                    String status = jsonObject1.optString("status");
                                    if (status.equalsIgnoreCase("1")){
                                        Toast.makeText(IncomeActivity.this, jsonObject1.optString("message"), Toast.LENGTH_SHORT).show();
                                        onBackPressed();
                                    }else {
                                        Toast.makeText(IncomeActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }

                            @Override
                            public void onError(ANError anError) {

                            }
                        });


    }

    private void spinner_view1() {
        progressDialog = ProgressDialog.show(IncomeActivity.this, "", "Loading", true);
        progressDialog.setCancelable(true);

        JSONObject jsonObject = new JSONObject();
        String id = session_manager.getUserId();

        try {
            jsonObject.accumulate("user_id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("mugil paymentlist URL "+ Webservice.getpayment_method+" request "+jsonObject);

        AndroidNetworking.post(Webservice.getpayment_method)
                .addJSONObjectBody(jsonObject)
                .setOkHttpClient(Webservice.getUnsafeOkHttpClient())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("details");
                            for(int i = 0; i< array.length(); i++){
                                JSONObject jsonObject1 = array.getJSONObject(i);
                                String name = jsonObject1.getString("payment_method");
                                arrayList1.add(name);
                                payment_list1.add(jsonObject1.getString("payment_method"));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        AlertDialog.Builder alert = new AlertDialog.Builder(IncomeActivity.this);
                        alert.setTitle("Choose Payment Method");
                        alert.setCancelable(false);
                        alert.setSingleChoiceItems(arrayList1.toArray(new String[arrayList1.size()]),0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                session_manager.setPaymentMethod(arrayList1.get(which));
                                payment_text.setText((session_manager.getPaymentMethod()));
                                arrayList1.clear();
                                dialog.dismiss();
                            }
                        });
//                        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                category_text.setText(category_list.get(session_manager.getcategoryId()));
//                                arrayList.clear();
//                                dialog.dismiss();
//                            }
//                        });
                        alert.show();
                    }
                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                    }
                });
    }
    private void spinner_view() {
        progressDialog = ProgressDialog.show(IncomeActivity.this, "", "Loading", true);
        progressDialog.setCancelable(true);

        JSONObject jsonObject = new JSONObject();
        String id = session_manager.getUserId();
        try {
            jsonObject.accumulate("user_id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("mugil categorylist URL "+ Webservice.getcategory+" request "+jsonObject);

        AndroidNetworking.post(Webservice.getcategory)
                .addJSONObjectBody(jsonObject)
                .setOkHttpClient(Webservice.getUnsafeOkHttpClient())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray array = jsonObject.getJSONArray("details");
                            for(int i = 0; i< array.length(); i++){

                                JSONObject jsonObject1 = array.getJSONObject(i);
                                String name = jsonObject1.getString("id")+ "-" +jsonObject1.getString("category");
                                arrayList.add(name);
                                category_list.add(jsonObject1.getString("id")+". "+jsonObject1.getString("category"));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        AlertDialog.Builder alert = new AlertDialog.Builder(IncomeActivity.this);
                        alert.setTitle("Choose Category");
                        alert.setCancelable(false);
                        alert.setSingleChoiceItems(arrayList.toArray(new String[arrayList.size()]), session_manager.getcategoryId(), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                session_manager.setCategoryId(which);
                                category_text.setText(category_list.get(session_manager.getcategoryId()));
                                arrayList.clear();
                                dialog.dismiss();
                            }
                        });
//                        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                category_text.setText(category_list.get(session_manager.getcategoryId()));
//                                arrayList.clear();
//                                dialog.dismiss();
//                            }
//                        });
                        alert.show();

                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                    }
                });


    }

    public void DateLayout() {
        final Calendar c = Calendar.getInstance();
        int Year = c.get(Calendar.YEAR);
        int Month = c.get(Calendar.MONTH);
        int Day = c.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(IncomeActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        String current;
                        if (monthOfYear + 1 > 9) {
                            if (dayOfMonth > 9) {
                                current = (dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            } else {
                                current = ("0" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }

                        } else {
                            if (dayOfMonth > 9) {
                                current = (dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            } else {
                                current = ("0" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }if (monthOfYear + 1<10){
                            dobtextfrom = (year + "-0" + (monthOfYear + 1) + "-" + dayOfMonth);

                        }else {
                            dobtextfrom = (year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);

                        }income_date.setText(current);

                    }
                },
                Year, Month, Day);
        datePickerDialog.show();

}
    public void DateLayout1() {
        final Calendar c = Calendar.getInstance();
        int Year = c.get(Calendar.YEAR);
        int Month = c.get(Calendar.MONTH);
        int Day = c.get(Calendar.DAY_OF_MONTH);

        final DatePickerDialog datePickerDialog = new DatePickerDialog(IncomeActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        String current;
                        if (monthOfYear + 1 > 9) {
                            if (dayOfMonth > 9) {
                                current = (dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            } else {
                                current = ("0" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }

                        } else {
                            if (dayOfMonth > 9) {
                                current = (dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            } else {
                                current = ("0" + dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }if (monthOfYear + 1<10){
                        dobtextfrom1 = (year + "-0" + (monthOfYear + 1) + "-" + dayOfMonth);

                    }
                        else {
                            dobtextfrom1 = (year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        }

                        expence_date.setText(current);

                    }
                },
                Year, Month, Day);
        datePickerDialog.show();

    }


}