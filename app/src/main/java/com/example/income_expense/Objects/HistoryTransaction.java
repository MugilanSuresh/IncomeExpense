package com.example.income_expense.Objects;

public class HistoryTransaction {
    String category;
    String payment;
    String amount;
    String type;
    String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
//    public String getColor_change() {
//        return color_change;
//    }

//    public void setColor_change(String color_change) {
//
//        this.color_change = color_change;
//    }

    String color_change;

//    public HistoryTransaction(String category, String payment, String amount, String type) {
//        this.category = category;
//        this.payment = payment;
//        this.amount = amount;
//        this.type = type;
//    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
