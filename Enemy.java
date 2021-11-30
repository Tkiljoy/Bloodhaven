package Models;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Enemy {
    private String enemyName;
    private long currentHealth;
    private long maxHealth;
    private int damage;
    private int defense;

    public Enemy(String enemyName, long currentHealth, long maxHealth, int damage, int defense) {
        this.enemyName = enemyName;
        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;
        this.damage = damage;
        this.defense = defense;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public void setEnemyName(String enemyName) {
        if (enemyName == null || enemyName.trim().isEmpty())
        {
            System.out.println("Enemy Name cannot be null empty or just whitespace");
        }
        this.enemyName = enemyName;
    }

    public long getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(long currentHealth) {
        if (currentHealth > maxHealth)
        {
            currentHealth = maxHealth;
        }
        if (currentHealth < 0)
        {
            currentHealth = 0;
        }
        this.currentHealth = currentHealth;
    }

    public long getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(long maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        if (damage < 0)
        {
            System.out.println("damage cannot be less than 0");
        }
        this.damage = damage;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        if (defense < 0)
        {
            System.out.println("defense cannot be less than 0");
        }
        this.defense = defense;
    }

    @Override
    public String toString() {
        return "Enemy Name: " + enemyName + '\'' +
                ", Current Health: " + currentHealth +
                ", Max Health: " + maxHealth +
                ", Damage: " + damage +
                ", Defense: " + defense;
    }
}
