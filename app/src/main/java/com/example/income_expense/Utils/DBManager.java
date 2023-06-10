package com.example.income_expense.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public Long insert(String product_id, String name,String quantity,String varient ,String count,String real_price, String modified_price,String images) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.PRODUCT_ID, product_id);
        contentValue.put(DatabaseHelper.NAME, name);
        contentValue.put(DatabaseHelper.QUANTITY, quantity);
        contentValue.put(DatabaseHelper.VARIENT_ID, varient);
        contentValue.put(DatabaseHelper.COUNT, count);
        contentValue.put(DatabaseHelper.REAL_PRICE, real_price);
        contentValue.put(DatabaseHelper.MODIFIED_PRICE, modified_price);
        contentValue.put(DatabaseHelper.IMAGES, images);
      Long id= database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
      return id;
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID,DatabaseHelper.PRODUCT_ID, DatabaseHelper.NAME, DatabaseHelper.QUANTITY, DatabaseHelper.VARIENT_ID, DatabaseHelper.COUNT, DatabaseHelper.REAL_PRICE, DatabaseHelper.MODIFIED_PRICE,DatabaseHelper.IMAGES };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
//    public List<CartData> getdata(){
//
//        List<CartData> data=new ArrayList<>();
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        Cursor cursor = db.rawQuery("select * from "+DatabaseHelper.TABLE_NAME+" ;",null);
//        StringBuilder stringBuffer = new StringBuilder();
//        CartData dataModel = null;
//        while (cursor.moveToNext()) {
//            dataModel= new CartData();
//            dataModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper._ID)));
//            dataModel.setProcuct_id(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.PRODUCT_ID)));
//            dataModel.setName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.NAME)));
//            dataModel.setQuantity(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.QUANTITY)));
//            dataModel.setCount(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COUNT)));
//            dataModel.setModified_price(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.MODIFIED_PRICE)));
//            dataModel.setReal_price(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.REAL_PRICE)));
//            dataModel.setVariant_id(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.VARIENT_ID)));
//            dataModel.setImages(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.IMAGES)));
//            stringBuffer.append(dataModel);
//
//            data.add(dataModel);
//        }
//
//        for (CartData mo:data ) {
//
//            Log.i("abbas",""+mo.getCount());
//        }
//
//
//
//        return data;
//    }




    public int update(long _id,String count,String modified_price) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper._ID, _id);
        contentValues.put(DatabaseHelper.COUNT, count);
        contentValues.put(DatabaseHelper.MODIFIED_PRICE, modified_price);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id ) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID+ "=" +_id, null);
    }
    public void delete_all( ) {
        database.delete(DatabaseHelper.TABLE_NAME, null, null);
    }
}
