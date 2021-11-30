package Models;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Weapon extends Item implements Serializable {
    public static final long serialVersionUID = 4;
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
        this.minDamage = minDamage;
    }

    public float getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    @Override
    public String toString() {
        String WeaponString = " Weapon {" + super.toString() + " Minimum Damage: " + getMinDamage() + " Maximum Damage: " + getMaxDamage() + "}";
        return WeaponString;
    }
}
