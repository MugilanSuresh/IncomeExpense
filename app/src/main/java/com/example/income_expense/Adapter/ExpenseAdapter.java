package com.example.income_expense.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.income_expense.Objects.Expense;
import com.example.income_expense.Objects.Income;
import com.example.income_expense.R;
import com.example.income_expense.Utils.Session_Manager;

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.MyViewHolder> {
    Context context;
    private List<Expense> expenseDataList;
    SharedPreferences prefs;
    Session_Manager session_manager;

    public ExpenseAdapter(Context context, List<Expense> expenseDataList) {
        this.context = context;
        this.expenseDataList = expenseDataList;


    }

    @NonNull
    @Override
    public ExpenseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_list, parent, false);
        ExpenseAdapter.MyViewHolder vh = new ExpenseAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseAdapter.MyViewHolder holder, int position) {
        session_manager = new Session_Manager(context);
        prefs = context.getSharedPreferences("session", 0);

        holder.expenseData.setText(expenseDataList.get(position).getExpenseData());

    }

    @Override
    public int getItemCount() {
        return expenseDataList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView expenseData;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            expenseData = (TextView)itemView.findViewById(R.id.expense_data);
        }
    }
}
