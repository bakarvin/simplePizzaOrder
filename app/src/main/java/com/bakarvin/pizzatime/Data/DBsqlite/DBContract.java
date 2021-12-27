package com.bakarvin.pizzatime.Data.DBsqlite;

import android.net.Uri;
import android.provider.BaseColumns;

import static com.bakarvin.pizzatime.Data.DBsqlite.DBContract.CartList.TABLE_Cart;

public class DBContract {
    private DBContract(){}

    public static final class CartList implements BaseColumns {
        public static final String TABLE_Cart = "itemCart";
        public static final String COL_NAMA_ITEM = "nama_item";
        public static final String COL_ID_ITEM = "id_item";
        public static final String COL_TOTAL_HARGA_ITEM = "total_harga_item";
        public static final String COL_HARGA_ITEM = "harga_satuan_item";
        public static final String COL_DESC_ITEM = "desc_item";
        public static final String COL_QTY_ITEM = "qty_item";
    }
    public static final String AUTHORITY = "com.bakarvin.pizzatime";
    public static final Uri CONTENT_URI = new Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendPath(TABLE_Cart)
            .build();
}
