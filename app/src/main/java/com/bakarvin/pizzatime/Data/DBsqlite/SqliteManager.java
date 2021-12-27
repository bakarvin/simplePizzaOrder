package com.bakarvin.pizzatime.Data.DBsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.bakarvin.pizzatime.Data.DBsqlite.DBContract.*;

import static com.bakarvin.pizzatime.Data.DBsqlite.DBContract.CartList.COL_NAMA_ITEM;
import static com.bakarvin.pizzatime.Data.DBsqlite.DBContract.CartList.TABLE_Cart;

public class SqliteManager {
    private final Context context;
    private SQLiteDatabase database;
    private DataBaseCart dataBaseCart;

    public SqliteManager(Context context) {
        this.context = context;
    }

    public SqliteManager open() throws SQLException{
        this.dataBaseCart = new DataBaseCart(this.context);
        this.database = dataBaseCart.getWritableDatabase();
        return this;
    }

    public void close() {
        this.dataBaseCart.close();
    }

    public void insert(String id, String nama, String desc, int harga, int subTotal, int qty){
        ContentValues cartValues = new ContentValues();
        cartValues.put(CartList.COL_ID_ITEM,id);
        cartValues.put(CartList.COL_NAMA_ITEM,nama);
        cartValues.put(CartList.COL_DESC_ITEM,desc);
        cartValues.put(CartList.COL_HARGA_ITEM,harga);
        cartValues.put(CartList.COL_TOTAL_HARGA_ITEM,subTotal);
        cartValues.put(CartList.COL_QTY_ITEM,qty);
        this.database.insert(CartList.TABLE_Cart, null, cartValues);
    }

    public Cursor readAll(){
        Cursor cursor = this.database.query(CartList.TABLE_Cart, new String[]{CartList._ID,
                CartList.COL_ID_ITEM,
                CartList.COL_NAMA_ITEM,
                CartList.COL_TOTAL_HARGA_ITEM,
                CartList.COL_HARGA_ITEM,
                CartList.COL_DESC_ITEM,
                CartList.COL_QTY_ITEM},
                null, null, null, null, CartList._ID + " ASC");
        return cursor;
    }

    public Cursor checkDup(String nama){
        Cursor cursor = this.database.rawQuery("SELECT * FROM " + TABLE_Cart + " WHERE " + COL_NAMA_ITEM + " like '%" + nama + "%'", null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void delete(String id) {
        this.database.delete(CartList.TABLE_Cart, "_ID=" + id, null);
    }

    public void deleteAll(){
        this.database.execSQL("DELETE FROM "+ DBContract.CartList.TABLE_Cart);
    }

    public int updateQTY(String _id, int qty, int subTotal) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CartList.COL_QTY_ITEM, qty);
        contentValues.put(CartList.COL_TOTAL_HARGA_ITEM, subTotal);
        return this.database.update(CartList.TABLE_Cart, contentValues, "_ID= " + _id, null);
    }

    public Cursor readTotalHarga(){
        String count = "SELECT SUM(total_harga_item) FROM " + TABLE_Cart;
        Cursor mcursor = this.database.rawQuery(count, null);
        if (mcursor != null) {
            mcursor.moveToFirst();
        }
        return mcursor;
    }

}
