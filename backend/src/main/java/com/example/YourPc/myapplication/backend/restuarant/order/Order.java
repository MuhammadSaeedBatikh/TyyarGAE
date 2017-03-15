package com.example.YourPc.myapplication.backend.restuarant.order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Muhammad Saeed on 2/11/2017.
 */
public class Order {
    public String itemID;
    public String instructions;
    public int itemsNumber;
    public List<String> choicesIDs = new ArrayList<>();

    public Order(String itemID, String instructions, List<String> choicesIDs, int itemsNumber) {
        this.itemID = itemID;
        this.instructions = instructions;
        this.choicesIDs = choicesIDs;
        this.itemsNumber = itemsNumber;
        String ditinguisher = String.valueOf(itemID.hashCode());
    }


    public String getItemID() {
        return itemID;
    }

    public String getInstructions() {
        return instructions;
    }

    public List<String> getChoicesIDs() {
        return choicesIDs;
    }

    @Override
    public String toString() {
        return "Order{" +
                "itemID='" + itemID + '\'' +
                ", instructions='" + instructions + '\'' +
                ", itemsNumber=" + itemsNumber +
                ", choicesIDs=" + choicesIDs +
                '}';
    }
}
