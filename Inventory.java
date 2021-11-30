package Models;
import java.io.Serializable;
import java.util.ArrayList;

public class Inventory implements Serializable {
    public static final long serialVersionUID = 2;
    private ArrayList<Item> items = new ArrayList<>();
    public Inventory() {
    }

    public ArrayList<Item> getItems() {
        return items;
    }
    public void addItem(Item item){
        items.add(item);
    }
    public void removeItem()
    {
        items.removeAll(items);
    }

    @Override
    public String toString() {
        String inventoryString = "Items " + getItems();
        return inventoryString;
    }
}
