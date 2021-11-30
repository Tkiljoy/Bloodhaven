package Models;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Weapon extends Item{
    private int minDamage = 1;
    private int maxDamage = 99;
    private ArrayList<String> weaponNames = new ArrayList<>(Arrays.asList("Kromwolds Cleaver of Power","Jack's GreatSword of Greatness","Max's Flail of Flailness","Keebie's Axe of Cutting","Swomold's Greatbow of Flying"));
    Random rng = new Random();

    public Weapon() {
        super();
        int mindamage = 10;
        int maxdamage = 100;
        int maxValue = 100000;
        int minValue = 1;
        name = weaponNames.get(rng.nextInt(weaponNames.size()));
        minDamage = rng.nextInt(mindamage);
        maxDamage = rng.nextInt(maxdamage);
    }

    public Weapon(String name, int value, int minDamage, int maxDamage) {
        super(name, value);
        setMinDamage(minDamage);
        setMaxDamage(maxDamage);
    }



    public float getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(int minDamage) {
        if (minDamage < 1) {
            throw new IllegalArgumentException("The Minimum Damage cannot be greater than the Max damage or less than 1");
        }
        this.minDamage = minDamage;
    }

    public float getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        if (maxDamage > 100){
            throw new IllegalArgumentException("The max damage cannot be greater than 100");
        }
        this.maxDamage = maxDamage;
    }

    @Override
    public String toString() {
        String WeaponString = " Weapon {" + super.toString() + " Minimum Damage: " + getMinDamage() + " Maximum Damage: " + getMaxDamage() + "}";
        return WeaponString;
    }
}
