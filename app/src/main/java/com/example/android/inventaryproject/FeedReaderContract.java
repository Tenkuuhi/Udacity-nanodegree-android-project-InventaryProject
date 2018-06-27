package com.example.android.inventaryproject;

import android.provider.BaseColumns;

public final class FeedReaderContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {

        public static final String TABLE_NAME = "invetory"; //constant that contain name of table
        public static final String COLUMN_PRODUCT_NAME = "product_name"; //constant that contain product name attribute
        public static final String COLUMN_PRICE = "price"; //constant that contain price attribute
        public static final String COLUMN_QUANTITY = "quantity"; //constant that contain quantity attribute
        public static final String COLUMN_SUPPLIER_NAME = "supplier_name"; //constant that contain supplier name attribute
        public static final String COLUMN_SUPPLIER_PHONE_NUMBER = "supplier_phone_number"; //constant that contain supplier phone number attribute
    }
}
