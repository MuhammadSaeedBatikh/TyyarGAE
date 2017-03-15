package com.example.YourPc.myapplication.backend.helpers;

import java.util.Date;

/**
 * Created by Muhammad Saeed on 2/23/2017.
 */
public class IdHelper {
    public static String generateCustomerKey ( String name , String email)
    {
        int index = email.indexOf('@');
        String subEmail = email.substring(0,index);
        String  time=new Date().toString();
        System.out.println(time);
        String id="Customer "+RandomGenerator.randomString()+name+RandomGenerator.randomString()
                +subEmail+RandomGenerator.randomString()+time;
        return id;
    }
    public static String generateRestaurantKey ( String name , String email)
    {
        int index = email.indexOf('@');
        String subEmail = email.substring(0,index);
        String  time=new Date().toString();
        System.out.println(time);
        String id="Restaurant "+RandomGenerator.randomString()+name+RandomGenerator.randomString()
                +subEmail+RandomGenerator.randomString()+time;
        return id;
    }
    public static String generatePharmacyID(String  name, String email){
        int index = email.indexOf('@');
        String subEmail = email.substring(0,index);
        String  time=new Date().toString();
        System.out.println(time);
        String id="Pharmacy "+RandomGenerator.randomString()+name+RandomGenerator.randomString()
                +subEmail+RandomGenerator.randomString()+time;
        return id;
    }

    public static String generateChoiceKey (String name)
    {
        String  time=new Date().toString();
        System.out.println(time);
        String id="Choice "+RandomGenerator.randomString()+name+RandomGenerator.randomString()
                +RandomGenerator.randomString()+time;
        return id;
    }
    public static String generateDriverKey ( String name , String email)
    {
        int index = email.indexOf('@');
        String subEmail = email.substring(0,index);
        String  time=new Date().toString();
        System.out.println(time);
        String id="Driver "+RandomGenerator.randomString()+name+RandomGenerator.randomString()
                +subEmail+RandomGenerator.randomString()+time;
        return id;
    }
    public static String generateRestaurantCategoryKey (String name)
    {
        String  time=new Date().toString();
        String id="ReCategory "+RandomGenerator.randomString()+name+RandomGenerator.randomString()
                +RandomGenerator.randomString()+time;
        return id;
    }
    public static String generatePharmacyCategoryID (String name)
    {
        String  time=new Date().toString();
        String id="PhCategory "+RandomGenerator.randomString()+name+RandomGenerator.randomString()
                +RandomGenerator.randomString()+time;
        return id;
    }

    public static String generatePharmacyProductID (String name)
    {
        String  time=new Date().toString();
        String id="PhProduct "+RandomGenerator.randomString()+name+RandomGenerator.randomString()
                +RandomGenerator.randomString()+time;
        return id;
    }
    public static String generateOptionKey (String name)
    {
        String  time=new Date().toString();
        System.out.println(time);
        String id="Option "+RandomGenerator.randomString()+name+RandomGenerator.randomString()
                +RandomGenerator.randomString()+time;
        return id;
    }
    public static String generateItemKey (String name)
    {
        String  time=new Date().toString();
        System.out.println(time);
        String id="Item "+RandomGenerator.randomString()+name+RandomGenerator.randomString()
                +RandomGenerator.randomString()+time;
        return id;
    }
    public static String generateListOfOrdersKey(String distinguisher){
        String time=new Date().toString();
        String nanoTime = String.valueOf(System.nanoTime());
        String id="OrdersList "+RandomGenerator.randomString()+" "+time+" "+
                RandomGenerator.randomString()+distinguisher+nanoTime;
        return id;
    }
    public static String generateDeliveryRequestKey(String distinguisher){
        String time=new Date().toString();
        String nanoTime = String.valueOf(System.nanoTime());
        String id="RestRequest "+RandomGenerator.randomString()+" "+time+" "+
                RandomGenerator.randomString()+distinguisher+nanoTime;
        return id;
    }
    public static String generateOrderKey(String distinguisher){
        String time=new Date().toString();
        String nanoTime = String.valueOf(System.nanoTime());
        String id="Order "+RandomGenerator.randomString()+" "+time+" "+
                RandomGenerator.randomString()+ distinguisher +nanoTime;
        return id;
    }
}
