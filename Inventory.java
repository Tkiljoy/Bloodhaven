package Models;
import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items = new ArrayList<>();
    public Inventory() {
    }

    public ArrayList<Item> getItems() {
        return items;
    }
    public void addItem(Item item){
        items.add(item);
    }

    @Override
    public String toString() {
        String inventoryString = "Items " + getItems();
        return inventoryString;
    }
}
