package Models;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Armor extends Item implements Serializable {
    public static final long serialVersionUID = 5;
    private int damageReduction = 5;
    private int agilityModifier = -6;
    private ArrayList<String> armorNames = new ArrayList<>(Arrays.asList("Bronze Chestplate of Healing","Golden Boots of Speed","Silver Leggings of WolfDown","BloodTongue Helmet of Lizards","Diamond Gauntlets of Seething"));
    Random rng = new Random();


    public Armor() {
        super();
        int damagereduction = 10;
        int agilitymodifier = 7;
        int maxValue = 100000;
        name = armorNames.get(rng.nextInt(armorNames.size()));
        damageReduction = rng.nextInt(damagereduction);
        agilityModifier = rng.nextInt(agilitymodifier)-6;
    }

    public Armor(String name, int value, int damageReduction, int agilityModifier) {
        super(name, value);
        setDamageReduction(damageReduction);
        setAgilityModifier(agilityModifier);
    }

    public float getDamageReduction() {
        return damageReduction;
    }

    public void setDamageReduction(int damageReduction) {
        if (damageReduction < 0 || damageReduction > 10){
            throw new IllegalArgumentException("damage reduction cannot be less than 0 or greater than 10 inclusive");
        }
        this.damageReduction = damageReduction;
    }

    public float getAgilityModifier() {
        return agilityModifier;
    }

    public void setAgilityModifier(int agilityModifier) {
        if (agilityModifier < -6 || agilityModifier > 0){
            throw new IllegalArgumentException("agility modifier cannot be less than -6 or greater than 0 inclusive ");
        }
        this.agilityModifier = agilityModifier;
    }

    @Override
    public String toString() {
        String ArmorString = " Armor {" + super.toString() + " Damage Reduction: " + getDamageReduction() + " Agility Modifier: " + getAgilityModifier() + "}";
        return ArmorString;
    }
}
