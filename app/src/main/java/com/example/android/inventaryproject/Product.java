package com.example.android.inventaryproject;

public class Product {

    private int mId;
    private String mName;
    private float mPrice;
    private int mQuantity;
    private String mSupplierName;
    private String mSupplierPhoneNumber;

    public Product(int id, String name, float price, int quantity, String suppliername, String supplierphonenumber){

        mId = id;
        mName = name;
        mPrice = price;
        mQuantity = quantity;
        mSupplierName = suppliername;
        mSupplierPhoneNumber = supplierphonenumber;
    }
    public int getId() {return mId;}
    public String getName() {return mName;}
    public float getPrice() {return mPrice;}
    public int getQuantity() {return mQuantity;}
    public String getSupplierName() {return mSupplierName;}
    public String getSupplierPhoneNumber() {return mSupplierPhoneNumber;}
}
