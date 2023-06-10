package com.example.income_expense.Adapter;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.income_expense.Activity.HomeActivity;
import com.example.income_expense.Objects.HistoryTransaction;
import com.example.income_expense.R;
import com.example.income_expense.Utils.Session_Manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {
    Context context;
    private List<HistoryTransaction> historyTransactionList;
    SharedPreferences prefs;
    Session_Manager session_manager;

    public HistoryAdapter(Context context, List<HistoryTransaction> historyTransactionList) {
        this.context = context;
        this.historyTransactionList = historyTransactionList;
    }




    @NonNull
    @Override
    public HistoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fullhistotry_list, parent, false);
        HistoryAdapter.MyViewHolder vh = new HistoryAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        session_manager = new Session_Manager(context);
        prefs = context.getSharedPreferences("session", 0);

        holder.category.setText(historyTransactionList.get(position).getCategory());
        holder.payment.setText(historyTransactionList.get(position).getPayment());
        holder.amount.setText(historyTransactionList.get(position).getAmount());
        holder.type.setText(historyTransactionList.get(position).getType());
        holder.date.setText(historyTransactionList.get(position).getDate());

        String strCurrentDate =historyTransactionList.get(position).getDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = null;
        try
        {
            newDate = format.parse(strCurrentDate);
        }
         catch (ParseException e) {
            e.printStackTrace();
        }

        format = new SimpleDateFormat("dd-MM-yyyy");
        String date = format.format(newDate);
        holder.date.setText(date);

        holder.color_change.setTag(position);
        if (historyTransactionList.get(position).getType().equalsIgnoreCase("Income")){
            holder.color_change.setBackgroundColor(Color.parseColor("#4DA469"));

        }
        else if (historyTransactionList.get(position).getType().equalsIgnoreCase("Expense")) {
            holder.color_change.setBackgroundColor(Color.parseColor("#C75450"));

        }
    }
    @Override
    public int getItemCount() {
        return historyTransactionList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView category;
        TextView payment;
        TextView amount;
        TextView type;
        TextView date;
        LinearLayout color_change;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            color_change = (LinearLayout)itemView.findViewById(R.id.color_change);
            category = (TextView)itemView.findViewById(R.id.category);
            payment = (TextView)itemView.findViewById(R.id.payment);
            amount = (TextView)itemView.findViewById(R.id.amount);
            type = (TextView)itemView.findViewById(R.id.type);
            date = (TextView)itemView.findViewById(R.id.date);


        }
    }
}
