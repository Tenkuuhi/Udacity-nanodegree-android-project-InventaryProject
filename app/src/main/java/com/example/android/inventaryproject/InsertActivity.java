package com.example.android.inventaryproject;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {

    private EditText mProductName;
    private EditText mPrice;
    private EditText mQuantity;
    private EditText mSupplierName;
    private EditText mSupplierPhoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        //find all edit text by id
        mProductName = (EditText) findViewById(R.id.edit_product_name);
        mPrice = (EditText) findViewById(R.id.edit_price);
        mQuantity = (EditText) findViewById(R.id.edit_quantity);
        mSupplierName = (EditText) findViewById(R.id.edit_supplier_name);
        mSupplierPhoneNumber = (EditText) findViewById(R.id.edit_supplier_phone_number);
        Button insert = (Button) findViewById(R.id.insert_button);
        // Set a click listener for insert data
        insert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String product_name = mProductName.getText().toString();
                if (product_name.equals("")){
                    Context context = getApplicationContext();
                    CharSequence text = "Product name is required";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                }
                String price_verify = mPrice.getText().toString();
                if (price_verify.equals("")){
                    Context context = getApplicationContext();
                    CharSequence text = "Product price is required";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                }
                float price = Float.valueOf(price_verify);
                String quantity_verify = mQuantity.getText().toString();
                if (quantity_verify.equals("")){
                    Context context = getApplicationContext();
                    CharSequence text = "Product quantity is required";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                }
                int quantity = Integer.valueOf(mQuantity.getText().toString());
                String supplier_name = mSupplierName.getText().toString();
                if (supplier_name.equals("")){
                    Context context = getApplicationContext();
                    CharSequence text = "Supplier name is required";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                }
                String supplier_phone_number = mSupplierPhoneNumber.getText().toString();
                if (supplier_phone_number.equals("")){
                    Context context = getApplicationContext();
                    CharSequence text = "Supplier phone number is required";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                }
                FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());
                QueryOperations insertdata = new QueryOperations(mDbHelper);
                insertdata.insertData(product_name, price, quantity, supplier_name, supplier_phone_number);
                // Create a new intent to open the {@link MainActivity}
                Intent homeIntent = new Intent(InsertActivity.this, MainActivity.class);
                // Start the new activity
                startActivity(homeIntent);
            }
        });
        //find button for decrease quantity by id
        Button minium = (Button) findViewById(R.id.minimum_button);
        minium.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String quantity = mQuantity.getText().toString();
                int change = Integer.valueOf(quantity);
                if (change > 0){
                    change--;
                    quantity = String.valueOf(change);
                    mQuantity.setText(quantity);
                }

            }
        });
        //find button for increase quantity by id
        Button plus = (Button) findViewById(R.id.plus_button);
        plus.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String quantity = mQuantity.getText().toString();
                int change = Integer.valueOf(quantity);
                change++;
                quantity = String.valueOf(change);
                mQuantity.setText(quantity);
            }
        });
    }
}
