package Models;

import java.io.Serializable;

public class Character implements Serializable {
    public static final long serialVersionUID = 1;
    private String characterName;
    private long currentHealth;
    private long maxHealth = 9999;
    private int damage = 10;
    private int defense;
    private int strength;
    private int agility;
    private int stamina;
    private int currentXP;
    private long nextLevelXp = calculateNextXp();
    private int level;

    public Character(String characterName, int currentHealth, int maxHealth ,int damage, int strength, int agility, int stamina,int defense,int currentXP,int level) {
        this.characterName = characterName;
        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;
        this.damage = damage;
        this.defense = defense;
        this.currentXP = currentXP;
        this.level = level;
        this.strength = strength;
        this.agility = agility;
        this.stamina = stamina;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        if (characterName == null || characterName.trim().isEmpty())
        {
            System.out.println("Name cannot be null empty or just whitespace");
        }
        this.characterName = characterName;
    }

    public long getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(long health) {
        if (health < 0)
        {
            System.out.println("Current Health cannot be less than 0");
        }
        this.currentHealth = health;
    }
    public int getCurrentXP() {
        return currentXP;
    }

    public int setCurrentXP(int currentXP) {
        if (currentXP >= nextLevelXp)
        {
            calculateNextXp();
            calculateMaxHP();
            currentHealth = maxHealth;
            level++;
            currentXP = 0;
        }
        this.currentXP = currentXP;
        return currentXP;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (level < 0)
        {
            System.out.println("Level cannot be less than 0");
        }
        this.level = level;
    }

    public long calculateNextXp()
    {
        for (int level = 0; level <= getLevel(); level++)
        {
            nextLevelXp += level * 1000;
        }
        return  nextLevelXp;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        if (strength < 0)
        {
            System.out.println("Strength cannot be less than 0");
        }
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        if (agility < 0)
        {
            System.out.println("Agility cannot be less than 0");
        }
        this.agility =  agility;
    }
    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        if (stamina < 0)
        {
            System.out.println("Stamina cannot be less than 0");
        }
        this.stamina =  stamina;
    }
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        if (damage < 0)
        {
            System.out.println("Damage cannot be less than 0");
        }
        this.damage = damage;
    }
    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        if (defense < 0)
        {
            System.out.println("Defense cannot be less than 0");
        }
        this.defense = defense;
    }

    public long getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        if (maxHealth < currentHealth)
        {
            maxHealth = (int) currentHealth;
        }
        this.maxHealth = maxHealth;
    }
    public long calculateMaxHP()
    {
        for (int level = 0; level <= getLevel(); level++)
        {
            maxHealth += level * 100;
        }
        return  maxHealth;
    }

    @Override
    public String toString() {
        String CharacterString = " Character Name: " + getCharacterName()
                + "\n" + " Current Health: " + getCurrentHealth()
                + "\n" + " Max Health: " + getMaxHealth()
                + "\n" + " CurrentExperience: " + getCurrentXP()
                + "\n" + " Level: " + getLevel()
                + "\n" + " Damage: " + getDamage()
                + "\n" + " Defense: " + getDefense()
                + "\n" +" Strength: "+ getStrength()
                + "\n" + "Agility: "+ getAgility()
                + "\n" +" Stamina: " + getStamina()
                + "\n";
        return CharacterString;
    }
}
