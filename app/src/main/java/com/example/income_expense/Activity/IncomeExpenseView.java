package com.example.income_expense.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.income_expense.Adapter.ExpenseAdapter;
import com.example.income_expense.Adapter.HistoryAdapter;
import com.example.income_expense.Adapter.HistroryAdapter1;
import com.example.income_expense.Adapter.IncomeAdapter;
import com.example.income_expense.Objects.Expense;
import com.example.income_expense.Objects.HistoryTransaction;
import com.example.income_expense.Objects.HistoryTransaction1;
import com.example.income_expense.Objects.Income;
import com.example.income_expense.R;
import com.example.income_expense.Utils.Session_Manager;
import com.example.income_expense.Utils.Webservice;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class IncomeExpenseView extends AppCompatActivity {
    LinearLayout income_historylayout,income_history1,expense_history1,
                    view1,expense_historylayout,income_history2,expense_history2,view2;
    ImageView back;
    RecyclerView recyclerView_income,recyclerView_expense;
    ProgressDialog progressDialog;
    SharedPreferences prefs;
    TextView income_show,expense_show,balance_show;
    Session_Manager session_manager;
    RecyclerView recyclerView;
    HistroryAdapter1 historyAdapter;
    IncomeAdapter incomeAdapter;
    ExpenseAdapter expenseAdapter;
    ArrayList<HistoryTransaction1> homecontentModelArraylist = new ArrayList<>();

    ArrayList<Income> homecontentIncomeArraylist = new ArrayList<>();
    ArrayList<Expense> homecontentExpenseArraylist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_expense_view);

        back = findViewById(R.id.back);

        progressDialog = new ProgressDialog(IncomeExpenseView.this);
        prefs = getSharedPreferences("session", 0);
        session_manager = new Session_Manager(this);
        recyclerView = findViewById(R.id.recycler_home1);
        expense_show =findViewById(R.id.expense_show);
        income_show = findViewById(R.id.income_show);
        balance_show = findViewById(R.id.balance_show);
        recyclerView.setLayoutManager(new LinearLayoutManager(IncomeExpenseView.this));
        income();


//        recyclerView_income = findViewById(R.id.recyclerView_income);
//        recyclerView_expense =findViewById(R.id.recyclerView_expense);
//        income_historylayout = findViewById(R.id.income_historylayout);
//        income_history1 = findViewById(R.id.income_history1);
//        expense_history1 = findViewById(R.id.expense_history1);
//        view1 = findViewById(R.id.view1);
//        expense_historylayout = findViewById(R.id.expense_historylayout);
//        income_history2 = findViewById(R.id.income_history2);
//        expense_history2 = findViewById(R.id.expense_history2);
//        view2 = findViewById(R.id.view2);
//        recyclerView_income.setLayoutManager(new LinearLayoutManager(IncomeExpenseView.this));
//        recyclerView_expense.setLayoutManager(new LinearLayoutManager(IncomeExpenseView.this));

//        expense_history1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                income_historylayout.setVisibility(View.GONE);
//                view1.setVisibility(View.GONE);
//                expense_historylayout.setVisibility(View.VISIBLE);
//                view2.setVisibility(View.VISIBLE);
//                recyclerView_income.setVisibility(View.GONE);
//                recyclerView_expense.setVisibility(View.VISIBLE);
////                getexpenseData();
//                homecontentIncomeArraylist.clear();
//            }
//        });
//        income_history2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                income_historylayout.setVisibility(View.VISIBLE);
//                view1.setVisibility(View.VISIBLE);
//                expense_historylayout.setVisibility(View.GONE);
//                view2.setVisibility(View.GONE);
//                recyclerView_expense.setVisibility(View.GONE);
//                recyclerView_income.setVisibility(View.VISIBLE);
////                getincomeData();
//                homecontentExpenseArraylist.clear();
//            }
//        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
//        getincomeData();


    }

    private void income() {
        progressDialog = ProgressDialog.show(IncomeExpenseView.this, "", "Updating", true);
        progressDialog.setCancelable(true);
        JSONObject jsonObject = new JSONObject();
        String id = session_manager.getUserId();
        try {
            jsonObject.accumulate("user_id",id);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("mugil addincome URL "+ Webservice.getuser_details+" request "+jsonObject);
        AndroidNetworking.post(Webservice.getuser_details)
                .addJSONObjectBody(jsonObject)
                .setOkHttpClient(Webservice.getUnsafeOkHttpClient())
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        System.out.println("income-----" + response);
                        try {
                            JSONObject jsonObject1 =new JSONObject(response);
                            String status = jsonObject1.optString("status");
                            if (status.equalsIgnoreCase("1")){
                                JSONArray jsonArray =jsonObject1.getJSONArray("details");
                                System.out.println("details++++ " + jsonArray.length());
                                for(int i = 0; i< jsonArray.length(); i++){
                                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                                    String type = jsonObject2.getString("type");
                                    HistoryTransaction1 historyTransaction= new HistoryTransaction1();
                                    historyTransaction.setCategory1(jsonArray.getJSONObject(i).optString("category"));
                                    historyTransaction.setAmount1(jsonArray.getJSONObject(i).optString("amount"));
                                    historyTransaction.setPayment1(jsonArray.getJSONObject(i).optString("payment_method"));
                                    historyTransaction.setDate1(jsonArray.getJSONObject(i).optString("income_date"));
//                                    historyTransaction.setType(jsonArray.getJSONObject(i).optString("type"));
                                    System.out.println("responceeeeeeeee+++++++++ " + homecontentModelArraylist);
                                    System.out.println("typeeeeeeeeeee+++++++ " + type);
                                    if (type.equalsIgnoreCase("1")){
                                        historyTransaction.setType1("Income");


                                    }else {
                                        historyTransaction.setType1("Expense");
//                                        historyTransaction.setColor_change("F11818");

                                    }

                                    homecontentModelArraylist.add(historyTransaction);
                                }historyAdapter = new HistroryAdapter1(IncomeExpenseView.this, homecontentModelArraylist);
                                recyclerView.setAdapter(historyAdapter);

                                income_show.setText(jsonObject1.optString("income"));
                                expense_show.setText(jsonObject1.optString("expence"));
                                balance_show.setText(jsonObject1.optString("balance"));
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
    }

//    private void getincomeData() {
//        progressDialog = ProgressDialog.show(IncomeExpenseView.this, "", "Updating", true);
//        progressDialog.setCancelable(true);
//
//        JSONObject jsonObject = new JSONObject();
//        String id = session_manager.getUserId();
//        try {
//            jsonObject.accumulate("user_id",id);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        System.out.println("mugil addincome URL "+ Webservice.getincomedata+" request "+jsonObject);
//        AndroidNetworking.post(Webservice.getincomedata)
//                .addJSONObjectBody(jsonObject)
//                .setOkHttpClient(Webservice.getUnsafeOkHttpClient())
//                .setPriority(Priority.MEDIUM)
//                .build()
//                .getAsString(new StringRequestListener() {
//                    @Override
//                    public void onResponse(String response) {
//                        progressDialog.dismiss();
//                        System.out.println("income-----" + response);
//                        try {
//                            JSONObject jsonObject1 = new JSONObject(response);
//                            String status = jsonObject1.optString("status");
//                            if(status.equalsIgnoreCase("1")){
//                                JSONArray jsonArray =jsonObject1.getJSONArray("details");
//                                for(int i = 0; i< jsonArray.length(); i++){
//                                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);
//                                    String type = jsonObject2.getString("type");
//                                    Income historyTransaction= new Income();
//                                    historyTransaction.setIncomeData(jsonArray.getJSONObject(i).optString("amount"));
//                                    homecontentIncomeArraylist.add(historyTransaction);
//
//                                }incomeAdapter = new IncomeAdapter(IncomeExpenseView.this,homecontentIncomeArraylist);
//                                recyclerView_income.setAdapter(incomeAdapter);
//
//
//                                }
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//
//                    }
//                });
//    }
//    public void onPause() {
//        super.onPause();
//    }
//    private void getexpenseData() {
//        progressDialog = ProgressDialog.show(IncomeExpenseView.this, "", "Updating", true);
//        progressDialog.setCancelable(true);
//
//        JSONObject jsonObject = new JSONObject();
//        String id = session_manager.getUserId();
//        try {
//            jsonObject.accumulate("user_id",id);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        System.out.println("mugil addincome URL "+ Webservice.getexpensedata+" request "+jsonObject);
//        AndroidNetworking.post(Webservice.getexpensedata)
//                .addJSONObjectBody(jsonObject)
//                .setOkHttpClient(Webservice.getUnsafeOkHttpClient())
//                .setPriority(Priority.MEDIUM)
//                .build()
//                .getAsString(new StringRequestListener() {
//                    @Override
//                    public void onResponse(String response) {
//                        progressDialog.dismiss();
//                        System.out.println("income-----" + response);
//                        try {
//                            JSONObject jsonObject1 = new JSONObject(response);
//                            String status = jsonObject1.optString("status");
//                            if(status.equalsIgnoreCase("1")){
//                                JSONArray jsonArray =jsonObject1.getJSONArray("details");
//                                for(int i = 0; i< jsonArray.length(); i++){
//                                    JSONObject jsonObject2 = jsonArray.getJSONObject(i);
//                                    String type = jsonObject2.getString("type");
//                                    Expense historyTransaction = new Expense();
//                                    historyTransaction.setExpenseData(jsonArray.getJSONObject(i).optString("amount"));
//                                    homecontentExpenseArraylist.add(historyTransaction);
//
//                                }expenseAdapter = new ExpenseAdapter(IncomeExpenseView.this,homecontentExpenseArraylist);
//                                recyclerView_expense.setAdapter(expenseAdapter);
//
//
//                            }
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//
//                    }
//                });
//    }
