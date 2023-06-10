package com.example.income_expense.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.income_expense.Adapter.HistoryAdapter;
import com.example.income_expense.Objects.HistoryTransaction;
import com.example.income_expense.R;
import com.example.income_expense.Utils.Session_Manager;
import com.example.income_expense.Utils.Webservice;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class HomeActivity extends AppCompatActivity
         {
    LinearLayout move_income, move_expance,color_change;
    LinearLayout toggle_drawer;
    TextView see_more,income_show,expense_show,balance_show;
    ProgressDialog progressDialog;
    SharedPreferences prefs;
    Session_Manager session_manager;
    RecyclerView recyclerView;
    HistoryAdapter historyAdapter;
    ArrayList<HistoryTransaction> homecontentModelArraylist = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        color_change = findViewById(R.id.color_change);
        recyclerView = findViewById(R.id.recycler_home);
        expense_show =findViewById(R.id.expense_show);
        income_show = findViewById(R.id.income_show);
        balance_show = findViewById(R.id.balance_show);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView=(NavigationView) findViewById(R.id.nav_view);
        see_more = findViewById(R.id.see_more);
        move_income = findViewById(R.id.move_income);
        move_expance = findViewById(R.id.move_expance);
        progressDialog = new ProgressDialog(HomeActivity.this);
        prefs = getSharedPreferences("session", 0);
        session_manager = new Session_Manager(this);
        toggle_drawer =  findViewById(R.id.toggle_drawer);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        homecontentModelArraylist.clear();

        move_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, IncomeActivity.class);
                startActivity(i);
            }
        });
        move_expance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, ExpanceActivity.class);
                startActivity(i);
            }
        });
        toggle_drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
                //setNavigationDrawer();
            }
        });
        see_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, IncomeExpenseView.class);
                startActivity(i);

            }
        });


        View hView = navigationView.getHeaderView(0);
        TextView logout = (TextView) hView.findViewById(R.id.log_out);
        LinearLayout work_layout = (LinearLayout) hView.findViewById(R.id.work_layout);
        TextView text = (TextView) hView.findViewById(R.id.text_change);
        TextView text1 = (TextView) hView.findViewById(R.id.text_change1);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("clickk logout ");
                logout();
            }
        });

    }



    @Override
    protected void onResume() {
        income();
        super.onResume();
    }

    private void income() {
        progressDialog = ProgressDialog.show(HomeActivity.this, "", "Updating", true);
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
                                    HistoryTransaction historyTransaction= new HistoryTransaction();
                                    historyTransaction.setCategory(jsonArray.getJSONObject(i).optString("category"));
                                    historyTransaction.setAmount(jsonArray.getJSONObject(i).optString("amount"));
                                    historyTransaction.setPayment(jsonArray.getJSONObject(i).optString("payment_method"));
                                    historyTransaction.setDate(jsonArray.getJSONObject(i).optString("income_date"));
//                                    historyTransaction.setType(jsonArray.getJSONObject(i).optString("type"));
                                    System.out.println("responceeeeeeeee+++++++++ " + homecontentModelArraylist);
                                    System.out.println("typeeeeeeeeeee+++++++ " + type);
                                    if (type.equalsIgnoreCase("1")){
                                        historyTransaction.setType("Income");


                                    }else {
                                        historyTransaction.setType("Expense");
//                                        historyTransaction.setColor_change("F11818");

                                    }

                                    homecontentModelArraylist.add(historyTransaction);
                                }historyAdapter = new HistoryAdapter(HomeActivity.this, homecontentModelArraylist);
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

    public void onPause() {
        homecontentModelArraylist.clear();
        super.onPause();
    }

    public void onBackPressed() {
        DrawerLayout dr = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (dr.isDrawerOpen(GravityCompat.START)){
            dr.closeDrawer(GravityCompat.START);
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
            builder.setMessage("Do you Want to Exit?");
            builder.setTitle("Exit App");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                finish();
                moveTaskToBack(true);
            });
            builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                dialog.cancel();
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

    }


    private void logout() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HomeActivity.this);
        alertDialogBuilder.setTitle("Alert !!!");
        alertDialogBuilder
                .setMessage("Log Out Your Account?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        session_manager.clear_session();
                        Intent intent = new Intent(HomeActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.activity_drawyer,menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.log_out){
//            logout();
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        return super.onPrepareOptionsMenu(menu);
//    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.log_out) {
//            System.out.println("ajith at logout");
//            String login_title;
//            String login_message;
//             {
//                login_title="Logout Account";
//                login_message="Do you want to Logout?";
//            }
//
//            logout(login_title,login_message);
//        }
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//
//        drawer.closeDrawer(GravityCompat.START);
//        return true;

//    }
//    private void logout(String login_title, String login_message) {
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HomeActivity.this);
//        alertDialogBuilder.setTitle(login_title);
//        alertDialogBuilder
//                .setMessage(login_message)
//                .setCancelable(false)
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        session_manager.clear_session();
//                        Intent intent = new Intent(HomeActivity.this,MainActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                })
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        dialog.cancel();
//                    }
//                });
//        AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.show();
//    }







