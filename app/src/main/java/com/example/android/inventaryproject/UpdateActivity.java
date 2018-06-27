package com.example.android.inventaryproject;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    private EditText mProductName;
    private EditText mPrice;
    private EditText mQuantity;
    private EditText mSupplierName;
    private EditText mSupplierPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        mProductName = (EditText) findViewById(R.id.edit_product_name);
        mPrice = (EditText) findViewById(R.id.edit_price);
        mQuantity = (EditText) findViewById(R.id.edit_quantity);
        mSupplierName = (EditText) findViewById(R.id.edit_supplier_name);
        mSupplierPhoneNumber = (EditText) findViewById(R.id.edit_supplier_phone_number);
        Button update = (Button) findViewById(R.id.update_button);
        final Button delete = (Button) findViewById(R.id.delete_button);
        Button call = (Button) findViewById(R.id.call_supplier);
        Bundle data = getIntent().getExtras();
        final int id = data.getInt("Product_id");
        String name = data.getString("Product_name");
        float price = data.getFloat("Product_price");
        int quantity = data.getInt("Product_quantity");
        String supplier_name = data.getString("Product_supplier_name");
        String supplier_phone_number = data.getString("Product_supplier_phone_number");
        mProductName.setText(name);
        mPrice.setText(String.valueOf(price));
        mQuantity.setText(String.valueOf(quantity));
        mSupplierName.setText(String.valueOf(supplier_name));
        mSupplierPhoneNumber.setText(String.valueOf(supplier_phone_number));
        //set listener on update button for update product
        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String product_name = mProductName.getText().toString();
                if (product_name.equals("")) {
                    Context context = getApplicationContext();
                    CharSequence text = "Product name is required";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                }
                String price_verify = mPrice.getText().toString();
                if (price_verify.equals("")) {
                    Context context = getApplicationContext();
                    CharSequence text = "Product price is required";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                }
                float price = Float.valueOf(price_verify);
                String quantity_verify = mQuantity.getText().toString();
                if (quantity_verify.equals("")) {
                    Context context = getApplicationContext();
                    CharSequence text = "Product quantity is required";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                }
                int quantity = Integer.valueOf(mQuantity.getText().toString());
                String supplier_name = mSupplierName.getText().toString();
                if (supplier_name.equals("")) {
                    Context context = getApplicationContext();
                    CharSequence text = "Supplier name is required";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                }
                String supplier_phone_number = mSupplierPhoneNumber.getText().toString();
                if (supplier_phone_number.equals("")) {
                    Context context = getApplicationContext();
                    CharSequence text = "Supplier phone number is required";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                }
                FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());
                QueryOperations updatedata = new QueryOperations(mDbHelper);
                updatedata.updateData(id, product_name, price, quantity, supplier_name, supplier_phone_number);
                // Create a new intent to open the {@link MainActivity}
                Intent numbersIntent = new Intent(UpdateActivity.this, MainActivity.class);
                // Start the new activity
                startActivity(numbersIntent);
            }
        });
        //set listener on delete button for delete product
        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                LinearLayout secure = (LinearLayout) findViewById(R.id.secure_delete);
                delete.setVisibility(View.INVISIBLE);
                secure.setVisibility(View.VISIBLE);
                Button yes = (Button) findViewById(R.id.yes_button);
                Button no = (Button) findViewById(R.id.no_button);
                //set listener for yes button for complete delete operation
                yes.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {

                        FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getApplicationContext());
                        QueryOperations deletedata = new QueryOperations(mDbHelper);
                        deletedata.deleteData(id);
                        // Create a new intent to open the {@link NumbersActivity}
                        Intent numbersIntent = new Intent(UpdateActivity.this, MainActivity.class);
                        // Start the new activity
                        startActivity(numbersIntent);
                    }
                });
                //set listener for no button for abort delete operation
                no.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        LinearLayout secure = (LinearLayout) findViewById(R.id.secure_delete);
                        secure.setVisibility(View.INVISIBLE);
                        delete.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
        //set listener for call supplier by his phone number
        call.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
                phoneIntent.setData(Uri.parse("tel:"+mSupplierPhoneNumber.getText().toString()));
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(phoneIntent);
            }
        });
        Button minium = (Button) findViewById(R.id.minimum_button);
        //set listener for minium button and decrease quantity by 1
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

        Button plus = (Button) findViewById(R.id.plus_button);
        //set listener for plus button and increase quantity by 1
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
