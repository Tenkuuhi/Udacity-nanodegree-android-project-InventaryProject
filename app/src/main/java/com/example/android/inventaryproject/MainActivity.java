package com.example.android.inventaryproject;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView productListView = findViewById(R.id.list);
        ArrayList<Product> products = new ArrayList<Product>();
        final ProductAdapter productAdapter = new ProductAdapter(this, products);
        Button insert = (Button) findViewById(R.id.insert_button);
        // Set a click listener to go in insert page
        insert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // Create a new intent to open the {@link InsertActivity}
                Intent insertIntent = new Intent(MainActivity.this, InsertActivity.class);
                // Start the new activity
                startActivity(insertIntent);
            }
        });
        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());
        QueryOperations query = new QueryOperations(mDbHelper);
        Cursor cursor = query.queryData();
        // Figure out the index of each column
        int idColumnIndex = cursor.getColumnIndex(FeedReaderContract.FeedEntry._ID);
        int productnameColumnIndex = cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_PRODUCT_NAME);
        int priceColumnIndex = cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_QUANTITY);
        int suppliernameColumnIndex = cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_SUPPLIER_NAME);
        int supplierphonenumberColumnIndex = cursor.getColumnIndex(FeedReaderContract.FeedEntry.COLUMN_SUPPLIER_PHONE_NUMBER);
        // Iterate through all the returned rows in the cursor
        while (cursor.moveToNext()) {
            // Use that index to extract value of the product
            // at the current row the cursor is on.
            int currentID = cursor.getInt(idColumnIndex);
            String currentProductName = cursor.getString(productnameColumnIndex);
            float currentPrice = cursor.getFloat(priceColumnIndex);
            int currentQuantity = cursor.getInt(quantityColumnIndex);
            String currentSupplierName = cursor.getString(suppliernameColumnIndex);
            String currentSupplierPhoneNumber = cursor.getString(supplierphonenumberColumnIndex);
            // add product at view
            products.add(new Product(currentID, currentProductName, currentPrice, currentQuantity, currentSupplierName, currentSupplierPhoneNumber));
        }
        TextView no_products = (TextView) findViewById(R.id.no_products);
        if (products.size() == 0) no_products.setVisibility(View.VISIBLE);
        productListView.setAdapter(productAdapter);
        cursor.close();
        //Set a click item listener to go in product describe page for modify, delete or call supplier by phone number
        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent updateIntent = new Intent(MainActivity.this, UpdateActivity.class);
                Product sendVariableProduct;
                sendVariableProduct = (Product) adapterView.getItemAtPosition(position);
                updateIntent.putExtra("Product_id", sendVariableProduct.getId());
                updateIntent.putExtra("Product_name", sendVariableProduct.getName());
                updateIntent.putExtra("Product_price", sendVariableProduct.getPrice());
                updateIntent.putExtra("Product_quantity", sendVariableProduct.getQuantity());
                updateIntent.putExtra("Product_supplier_name", sendVariableProduct.getSupplierName());
                updateIntent.putExtra("Product_supplier_phone_number", sendVariableProduct.getSupplierPhoneNumber());
                // Start the new activity
                startActivity(updateIntent);
            }
        });
    }
    //onClick method for decrease quantity in database and textview by 1
    public void decrement(View v){

        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());
        QueryOperations updatequantity = new QueryOperations(mDbHelper);
        int quantity = updatequantity.findQuantity(v.getId());
        if (quantity > 0){
            quantity--;
        }
        updatequantity.updateQuantity(v.getId(), quantity);
        LinearLayout item = (LinearLayout) v.getParent();
        TextView productquantity = (TextView) item.getChildAt(2);
        productquantity.setText(String.valueOf(quantity));
        }
}
