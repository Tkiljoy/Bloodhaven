package Models;
import java.io.Serializable;
import java.util.Random;

public class Potion extends Item implements Serializable {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final long serialVersionUID = 3;
    private int healAmount = 10;
    Random rng = new Random();


    public Potion() {
        super();
        int healValue = 100;
        name = "Healing Potion";
        healAmount = rng.nextInt(healValue);
    }

    public Potion(String name, int value, int healAmount) {
        super(name, value);
        setHealAmount(healAmount);
    }


    public float getHealAmount() {
        return healAmount;
    }

    public void setHealAmount(int healAmount) {
        if (healAmount < 0){
            throw new IllegalArgumentException("The Heal Amount cannot be less than zero");
        }
        this.healAmount = healAmount;
    }

    @Override
    public String toString() {
        String PotionString = " Potion {" + super.toString() + ANSI_RED + " Heal Amount: " + getHealAmount() + ANSI_RESET + "}";
        return PotionString;
    }
}
