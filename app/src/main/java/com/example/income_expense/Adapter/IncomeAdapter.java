package com.example.income_expense.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.income_expense.Objects.HistoryTransaction;
import com.example.income_expense.Objects.Income;
import com.example.income_expense.R;
import com.example.income_expense.Utils.Session_Manager;

import java.util.List;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.MyViewHolder> {
    Context context;
    private List<Income> incomeDataList;
    SharedPreferences prefs;

    public IncomeAdapter(Context context, List<Income> incomeDataList) {
        this.context = context;
        this.incomeDataList = incomeDataList;
    }

    Session_Manager session_manager;

    @NonNull
    @Override
    public IncomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.income_list, parent, false);
        IncomeAdapter.MyViewHolder vh = new IncomeAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeAdapter.MyViewHolder holder, int position) {
        session_manager = new Session_Manager(context);
        prefs = context.getSharedPreferences("session", 0);

        holder.incomeData.setText(incomeDataList.get(position).getIncomeData());


    }

    @Override
    public int getItemCount() {
        return incomeDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView incomeData;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            incomeData = (TextView)itemView.findViewById(R.id.income_data);
        }
    }
}
