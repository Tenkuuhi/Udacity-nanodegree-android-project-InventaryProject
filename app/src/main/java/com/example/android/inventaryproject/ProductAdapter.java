package com.example.android.inventaryproject;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product>  {

    public ProductAdapter(Context context, ArrayList<Product> products) {

        super(context, 0, products);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        // Get the {@link Product} object located at this position in the list
        Product currentProduct = getItem(position);
        // Find the TextView in the list_item.xml layout with the ID news_section and set with currentNews item.
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.product_name);
        nameTextView.setText(currentProduct.getName());
        // Find the TextView in the list_item.xml layout with the ID news_date and set with currentNews item.
        TextView priceTextView = (TextView) listItemView.findViewById(R.id.product_price);
        priceTextView.setText(String.valueOf(currentProduct.getPrice()));
        // Find the TextView in the list_item.xml layout with the ID news_author and set with currentNews item.
        TextView quantityTextView = (TextView) listItemView.findViewById(R.id.product_quantity);
        quantityTextView.setText(String.valueOf(currentProduct.getQuantity()));
        //set id of button for dynamic system
        Button saleButton = (Button) listItemView.findViewById(R.id.sale_button);
        int button_id = Integer.valueOf(currentProduct.getId());
        saleButton.setId(button_id);
        return listItemView;
    }
}
