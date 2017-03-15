package com.example.YourPc.myapplication.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * User: YourPc
 * Date: 3/14/2017
 */

@Entity
public class Review {
    @Id String id;
    String customerID;
    String body;
    int rating;
    String restraintID;

    public Review(String id, String customerID, String body, int rating, String restraintID) {
        this.id = id;
        this.customerID = customerID;
        this.body = body;
        this.rating = rating;
        this.restraintID = restraintID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getRestraintID() {
        return restraintID;
    }

    public void setRestraintID(String restraintID) {
        this.restraintID = restraintID;
    }
}
