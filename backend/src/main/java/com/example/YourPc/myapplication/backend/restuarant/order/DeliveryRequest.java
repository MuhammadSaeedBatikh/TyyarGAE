package com.example.YourPc.myapplication.backend.restuarant.order;

import com.example.YourPc.myapplication.backend.helpers.IdHelper;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammad Saeed on 3/2/2017.
 */
@Entity
public class DeliveryRequest {
    @Id
    public String id;
    public ListOfOrders listOfOrders;
    public String customerID = "";
    public String restaurantID = "";
    public String customerLocationID;
    public String driverID ="default";
    public boolean confirmPickUP=false;
    public List<String> driversWhoRefusedIDs=new ArrayList<>();
    public DeliveryRequest(ListOfOrders listOfOrders, String customerID,
                           String restaurantID, String customerLocationID) {
        this.listOfOrders = listOfOrders;
        this.customerID = customerID;
        this.restaurantID = restaurantID;
        this.customerLocationID = customerLocationID;
        this.id = IdHelper.generateDeliveryRequestKey(genertaeDistinguisher());
    }

    public DeliveryRequest() {
    }

    private String genertaeDistinguisher(){
        String distinguisher ="" ;
        int length = customerID.length()/2;
        distinguisher+=customerID.substring(length-2,length+2);
        int restLength =restaurantID.length()/2;
        distinguisher+=restaurantID.substring(restLength-2,restLength+2);
        return distinguisher;
    }

}
