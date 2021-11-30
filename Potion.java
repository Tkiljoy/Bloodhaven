package Models;
import java.util.Random;

public class Potion extends Item{
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
        String PotionString = " Potion {" + super.toString() + " Heal Amount: " + getHealAmount() + "}";
        return PotionString;
    }
}