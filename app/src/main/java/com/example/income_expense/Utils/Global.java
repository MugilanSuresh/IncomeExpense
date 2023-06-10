package com.example.income_expense.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.income_expense.Activity.MainActivity;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Global {

public static void logOut(Context context){
    SharedPreferences prefs = context.getSharedPreferences("session", 0);
    SharedPreferences.Editor editor = prefs.edit();
    editor.clear();
    editor.apply();
   // session_manager.clear_session();
    Log.e("abbas","Language"+prefs.getString("language",""));
    Realm.init(context);
    RealmConfiguration main_data_config = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().name("update.realm").build();
    Realm cart_value = Realm.getInstance(main_data_config);
    cart_value.beginTransaction();
    cart_value.deleteAll();
    cart_value.commitTransaction();
    Intent logout = new Intent(context, MainActivity.class);
    logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    context.startActivity(logout);
    //context.finish();
}


}
