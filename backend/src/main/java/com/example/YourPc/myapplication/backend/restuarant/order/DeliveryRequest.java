package com.example.YourPc.myapplication.backend.restuarant.order;

import com.example.YourPc.myapplication.backend.helpers.IdHelper;
import com.example.YourPc.myapplication.backend.helpers.ProfileType;
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
    public ProfileType merchantType;
    public ListOfOrders listOfOrders;
    public String customerID = "";
    public String merchantID = "";
    public String customerLocationID="";
    public String driverID ="";
    public boolean driverConfirmPickUP =false;
    public boolean customerConfirmPickUP=false;
    public List<String> driversWhoRefusedIDs=new ArrayList<>();
    public DeliveryRequest(ProfileType merchantType,ListOfOrders listOfOrders, String customerID,
                           String merchantID, String customerLocationID) {
        this.merchantType=merchantType;
        this.listOfOrders = listOfOrders;
        this.customerID = customerID;
        this.merchantID = merchantID;
        this.customerLocationID = customerLocationID;
        this.id = IdHelper.generateDeliveryRequestKey(genertaeDistinguisher());
    }

    public DeliveryRequest() {
    }

    private String genertaeDistinguisher(){
        String distinguisher ="" ;
        int length = customerID.length()/2;
        distinguisher+=customerID.substring(length-2,length+2);
        int restLength =merchantID.length()/2;
        distinguisher+=merchantID.substring(restLength-2,restLength+2);
        return distinguisher;
    }

}
