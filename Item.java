package Models;
public class Item {
    protected String name = "[UNIDENTIFIED]";
    protected int value = 1;

    public Item() {
        setName(name);
        setValue(value);
    }

    public Item(String name, int value) {
        setName(name);
        setValue(value);
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

    public int getValue() {
        return value;
    }

    protected void setValue(int value) {
        if (value < 0){
            throw new IllegalArgumentException("The value cannot be less than zero");
        }
        if (value > 100000){
            throw new IllegalArgumentException("The value cannot be more than 100000");
        }
        this.value = value;
    }

    @Override
    public String toString() {
        String ItemString = "Name: " + getName() + " Value: " + getValue();
        return ItemString;
    }
}
