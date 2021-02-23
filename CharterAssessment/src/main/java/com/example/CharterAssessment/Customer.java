package com.example.CharterAssessment;

import java.time.LocalDate;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;

public class Customer {

    private String id;
    private String first_name;
    private String last_name;
    private String price;
    private String date;

    private Customer(){}

    public Customer(String id, String first_name, String last_name, String price, String date){
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.price = price;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getPurchaseTotal() {
        return price;
    }

    public void setPurchaseTotal(String price) {
        this.price = price;
    }

    public String getPurchaseDate() {
        return date;
    }

    public void setPurchaseDate(String date) {
        this.date = date;
    }

    public String toString(){
        return "Customer{" +
                "ID: " + id + '\'' +
                ", First Name: " + first_name + '\'' +
                ", Last Name: " + last_name + '\'' +
                ", Purchase Total: " + price + '\'' +
                ", Purchase Date: " + date + '\'' +
                '}';
    }

    public static Customer fromJson(JSONObject jsonObject) {
        Customer c = new Customer();
        // Deserialize json into object fields
        try {
            c.id = jsonObject.getString("id");
            c.first_name = jsonObject.getString("first_name");
            c.last_name = jsonObject.getString("last_name");
            c.price = jsonObject.getString("price");
            c.date = jsonObject.getString("date");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return c;
    }

    public static ArrayList<Customer> fromJson(JSONArray jsonArray) {
        JSONObject customerJson;
        ArrayList<Customer> customers = new ArrayList<Customer>(jsonArray.length());
        // Process each result in json array, decode and convert to business object
        for (int i=0; i < jsonArray.length(); i++) {
            try {
                customerJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Customer customer = Customer.fromJson(customerJson);
            if (customer != null) {
                customers.add(customer);
            }
        }

        return customers;
    }
}
