package Models;

import java.io.Serializable;
import java.util.Random;

public class Item implements Serializable {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final long serialVersionUID = 6;
    protected String name = "[UNIDENTIFIED]";
    Random rng = new Random();
    protected int itemValue = 10;

    public Item() {
        int itemCost = 1000;
        setName(name);
        itemValue = rng.nextInt(itemCost);
    }

    public Item(String name, int itemValue) {
        setName(name);
        setItemValue(itemValue);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("The name cannot be null empty or just whitespace.");
        }
        this.name = name;
    }

    public int getItemValue() {
        return itemValue;
    }

    public void setItemValue(int itemValue) {
        if (itemValue < 0){
            throw new IllegalArgumentException("The value cannot be less than zero");
        }
        if (itemValue > 100000){
            throw new IllegalArgumentException("The value cannot be more than 100000");
        }
        this.itemValue = itemValue;
    }

    @Override
    public String toString() {
        String ItemString = "Name: " + getName() + ANSI_GREEN + " Value: " + getItemValue() + ANSI_RESET;
        return ItemString;
    }
}
