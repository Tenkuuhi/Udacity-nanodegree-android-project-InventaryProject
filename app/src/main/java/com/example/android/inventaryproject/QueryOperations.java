package com.example.android.inventaryproject;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

//class that contain write and read operation
public class QueryOperations {

    FeedReaderDbHelper mDbHelper;
    public QueryOperations(FeedReaderDbHelper mDbHelper){

         this.mDbHelper = mDbHelper;
    }
    //method for insert data in database
    public void insertData(String name, float price, int quantity, String supplier_name, String supplier_phone_number){

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_PRODUCT_NAME, name);
        values.put(FeedReaderContract.FeedEntry.COLUMN_PRICE, price);
        values.put(FeedReaderContract.FeedEntry.COLUMN_QUANTITY, quantity);
        values.put(FeedReaderContract.FeedEntry.COLUMN_SUPPLIER_NAME, supplier_name);
        values.put(FeedReaderContract.FeedEntry.COLUMN_SUPPLIER_PHONE_NUMBER, supplier_phone_number);
        // Insert the new row, returning the primary key value of the new row
        db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
    }
    //method for obtain all row from database
    public Cursor queryData(){

        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                FeedReaderContract.FeedEntry.COLUMN_PRODUCT_NAME,
                FeedReaderContract.FeedEntry.COLUMN_PRICE,
                FeedReaderContract.FeedEntry.COLUMN_QUANTITY,
                FeedReaderContract.FeedEntry.COLUMN_SUPPLIER_NAME,
                FeedReaderContract.FeedEntry.COLUMN_SUPPLIER_PHONE_NUMBER
        };
        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order
        return cursor;
    }
    //method for update data of a row find by _id
    public void updateData(int id, String name, float price, int quantity, String supplier_name, String supplier_phone_number){

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_PRODUCT_NAME, name);
        values.put(FeedReaderContract.FeedEntry.COLUMN_PRICE, price);
        values.put(FeedReaderContract.FeedEntry.COLUMN_QUANTITY, quantity);
        values.put(FeedReaderContract.FeedEntry.COLUMN_SUPPLIER_NAME, supplier_name);
        values.put(FeedReaderContract.FeedEntry.COLUMN_SUPPLIER_PHONE_NUMBER, supplier_phone_number);
        // Insert the new row, returning the primary key value of the new row
        db.update(FeedReaderContract.FeedEntry.TABLE_NAME, values, "_id="+id, null);
    }
    //method for delete a row by _id
    public void deleteData(int id){

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.delete(FeedReaderContract.FeedEntry.TABLE_NAME,"_id="+id, null);
    }
    //find quantity of a row by _id
    public int findQuantity(int id){

        int quantity=0;
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {FeedReaderContract.FeedEntry.COLUMN_QUANTITY};
        String selection = FeedReaderContract.FeedEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };
        Cursor cursor = db.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                selection,                  // The columns for the WHERE clause
                selectionArgs,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);              // The sort order
        int quantityColumnIndex = cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_QUANTITY);
        if(cursor.moveToNext()){
            quantity = cursor.getInt(quantityColumnIndex);
        }
        return quantity;
    }
    //update quantity of a row by _id
    public void updateQuantity(int id, int quantity){

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_QUANTITY, quantity);
        // Insert the new row, returning the primary key value of the new row
        db.update(FeedReaderContract.FeedEntry.TABLE_NAME, values, "_id="+id, null);
    }
}
