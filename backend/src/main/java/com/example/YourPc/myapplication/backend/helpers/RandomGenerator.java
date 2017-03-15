package com.example.YourPc.myapplication.backend.helpers;

/**
 * Created by Muhammad Saeed on 2/10/2017.
 */
public class RandomGenerator {
    public static String randomString(){
        String s ="";
        int length = 1+(int) (15*Math.random());
        for (int i=0 ;i<length;i++){
            s+= randomCharacter();
        }
        return s;
    }

    public static char randomCharacter(){
        char c = (char)(1+(int)(120*Math.random()));
        return c;
    }

}
