package com.example.income_expense.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "MYCARTS";

    // Table columns
    public static final String _ID = "_id";
    public static final String PRODUCT_ID = "product_id";
    public static final String NAME = "name";
    public static final String QUANTITY = "quantity";
    public static final String COUNT = "count";
    public static final String VARIENT_ID = "varient_id";
    public static final String REAL_PRICE = "real_price";
    public static final String MODIFIED_PRICE = "modified_price";
    public static final String IMAGES = "images";

    // Database Information
    static final String DB_NAME = "CART.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "("+ _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ PRODUCT_ID + " TEXT, "+ NAME + " TEXT, "+ QUANTITY + " TEXT, "+ VARIENT_ID + " TEXT, "+ COUNT + " TEXT, "+ REAL_PRICE + " TEXT, "+ MODIFIED_PRICE + " TEXT, "+ IMAGES + " TEXT);";

    public DatabaseHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}
