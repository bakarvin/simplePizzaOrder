package com.bakarvin.pizzatime.Data.DBsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.bakarvin.pizzatime.Data.DBsqlite.DBContract.*;

import androidx.annotation.Nullable;

public class DataBaseCart extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "shopping_cart.db";
    public static final int DATABASE_VERSION = 1;

    public DataBaseCart(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String CREATE_TABLE_CART = "CREATE TABLE " + CartList.TABLE_Cart +
                " (" + CartList._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                      CartList.COL_ID_ITEM + " TEXT NOT NULL, " +
                      CartList.COL_NAMA_ITEM + " TEXT NOT NULL, " +
                      CartList.COL_TOTAL_HARGA_ITEM + " TEXT NOT NULL, " +
                      CartList.COL_HARGA_ITEM + " TEXT NOT NULL, " +
                      CartList.COL_DESC_ITEM + " TEXT NOT NULL, " +
                      CartList.COL_QTY_ITEM + " TEXT NOT NULL " +
                ");";
        sqLiteDatabase.execSQL(CREATE_TABLE_CART);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ CartList.TABLE_Cart);
        onCreate(sqLiteDatabase);
    }

}
