package Controllers;
import Models.Character;
import lib.ConsoleIO;
import Models.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import static lib.ConsoleIO.promptForInt;
import static lib.ConsoleIO.promptForString;

public class GameManager {
    public String characterName;
    private ArrayList<Weapon> weapons = new ArrayList<>();
    private ArrayList<Item> armors = new ArrayList<>();
    private ArrayList<Potion> potions = new ArrayList<>();
    private ArrayList<Weapon> BossDrops = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Enemy> bosses = new ArrayList<>();
    private ArrayList<Item> GroundItems = new ArrayList<>();
    private ArrayList<Weapon> EquippedWeapon = new ArrayList<>();
    private ArrayList<Item> EquippedArmor = new ArrayList<>();
    private ArrayList<Item> shopItems = new ArrayList<>();
    private String[] InventoryMenu = {"Show Whole Inventory","Remove An Item"};
    private String[] ShowItemMenu = {"Show Weapons","Show Armors","Show Potions","Show Entire Inventory", "Show Equipped Items", "Equip Weapon", "Equip Armor"};
    private String[] RemoveItemMenu = {"Remove a Weapon","Remove a Piece of Armor","Remove a Potion","Remove All Items"};
    private String[] AddRandomItemMenu = {"Add 1 Item","Add Some Items","Add Many Items"};
    private String[] MainMenu = {"New Game","Load Game"};
    private String[] SecondaryMenu ={"Move","Access Inventory","View Stats","Save Game","Exit to Main Menu"};
    private String[] AttributeMenu = {"Strength","Stamina","Agility","Vitality"};
    private String[] Combat = {"Attack","Defend","Use Potion","Run"};
    private String[] BossCombat = {"Attack","Defend","Use Potion"};
    private String[] Shop = {"Buy","Sell"};
    private String[] Sell = {"Potions", "Weapons", "Armor"};

    private int currentBalance;
    Inventory inventory = new Inventory();
    public Character character = new Character(characterName,100,100,10,0,0,0,10,0,1,500);
    Random rng = new Random();
    Scanner scanner = new Scanner(System.in);

    public void run() {
        MainMenu();
    }
    private int MainMenu() {
        {
            int selection;
            do {
                selection = ConsoleIO.promptForMenuSelection(MainMenu, true);
                switch (selection) {
                    case 1:
                        NewGame();
                        break;
                    case 2:
                        loadGame();
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("This SHOULD NEVER HAPPEN TRY AGAIN");
                }
            }
            while (selection > 0);

            return selection;
        }
    }
    public void NewGame()
    {
        System.out.println("Welcome Adventurer I See You Found Yourself In My Tavern");
        characterName = promptForString("Tell Me Adventurer What Is Your Name?");
        System.out.println("Well Glad To Meet You " + characterName);
        character.setCharacterName(characterName);
        character.setCurrentXP(0);
        character.setDamage(10);
        character.setDefense(10);
        character.setLevel(1);
        character.setCurrentBalance(500);
        character.calculateNextXp();
        character.setAttributePoints(5);
        character.setAgility(1);
        character.setStrength(1);
        character.setMaxHealth(100);
        character.setStamina(1);
        character.setCurrentHealth(100);
        weapons.removeAll(weapons);
        armors.removeAll(armors);
        potions.removeAll(potions);
        inventory.removeItem();
        Weapon Stick = new Weapon();
        Stick.setName("Stick");
        Stick.setMinDamage(1);
        Stick.setMaxDamage(1);
        inventory.addItem(Stick);
        weapons.add(Stick);
        Potion weakPotion = new Potion();
        weakPotion.setName("Weak Potion");
        weakPotion.setHealAmount(5);
        inventory.addItem(weakPotion);
        potions.add(weakPotion);

        Weapon boss1 = new Weapon();
        boss1.setName("boss1");
        boss1.setMinDamage(10);
        boss1.setMaxDamage(10);
        inventory.addItem(boss1);
        BossDrops.add(boss1);

        Weapon boss2 = new Weapon();
        boss2.setName("boss2");
        boss2.setMinDamage(20);
        boss2.setMaxDamage(20);
        inventory.addItem(boss2);
        BossDrops.add(boss2);

        Weapon boss3 = new Weapon();
        boss3.setName("boss3");
        boss3.setMinDamage(30);
        boss3.setMaxDamage(30);
        inventory.addItem(boss3);
        BossDrops.add(boss3);

        Weapon boss4 = new Weapon();
        boss4.setName("boss4");
        boss4.setMinDamage(40);
        boss4.setMaxDamage(40);
        inventory.addItem(boss4);
        BossDrops.add(boss4);


        AttributeMenu();
    }
    public void AttributeMenu()
    {
        int Points = character.getAttributePoints();
        int selection;
        do{
            System.out.println("You have " + Points + " Points To Spend");
            selection = ConsoleIO.promptForMenuSelection(AttributeMenu,false);
            switch(selection)
            {
                case 1:
                    //Strength
                    character.setStrength(character.getStrength() +1);
                    character.setDamage(character.getDamage() + character.getStrength());
                    System.out.println(character.getDamage());
                    Points--;
                    break;
                case 2:
                    //Stamina
                    character.setStamina(character.getStamina() + 1);
                    Points--;
                    break;
                case 3:
                    //Agility
                    character.setAgility(character.getAgility() + 1);
                    Points--;
                    break;
                case 4:
                    //Vitality
                    character.setMaxHealth((int) (character.getMaxHealth() + 50));
                    character.setCurrentHealth(character.getMaxHealth());
                    Points--;
                    break;
                case 0:
                    break;
                default:
                    System.out.println("THIS SHOULD NEVER HAPPEN TRY AGAIN");
            }
        }while (Points > 0);
        character.setAttributePoints(0);
        SecondaryMenu();
    }

    public int SecondaryMenu() {
        int selection;
        do {
            selection = ConsoleIO.promptForMenuSelection(SecondaryMenu, false);

            switch (selection) {
                case 1:
                    Move();
                    break;
                case 2:
                    showInventory();
                    break;
                case 3:

                    if (character.getAttributePoints() > 0)
                    {
                        AttributeMenu();
                    }
                    System.out.println("You have " + character.getAttributePoints() + " Points to Spend");
                    System.out.println(character);
                    break;
                case 4:
                    saveGame();
                    break;
                case 5:
                    MainMenu();
                    break;
                default:
                    System.out.println("Invalid option selected");
                    break;
            }
        } while (selection > 0);

        return selection;
    }

    public int Combat(){
        int selection;
        do {
            selection = ConsoleIO.promptForMenuSelection(Combat, true);
            switch (selection) {
                case 1:
                    Attack();
                    break;
                case 2:
                    Defend();
                    break;
                case 3:
                    UsePotion();
                    break;
                case 4:
                    System.out.println("You retreat like the french");
                    break;
                default:
                    System.out.println("Invalid option selected");

            }
        }while (selection > 0);
        return  selection;
    }

    public int bossCombat(){
        int selection;
        do {
            selection = ConsoleIO.promptForMenuSelection(BossCombat, true);
            switch (selection) {
                case 1:
                    BossAttack();
                    break;
                case 2:
                    BossDefend();
                    break;
                case 3:
                    UsePotion();
                    break;
                default:
                    System.out.println("Invalid option selected");

            }
        }while (selection > 0);
        return  selection;
    }


    private void BossAttack()
    {
        boolean enemyDead = false;
        boolean playerDead = false;
        int playerTurn = 1;
        long damageToDo = character.getDamage();
        System.out.println("Character Name: " + character.getCharacterName() + " Character Health: " + character.getCurrentHealth());
        System.out.println(bosses.get(0));
        long enemyHealth = bosses.get(0).getCurrentHealth();
        long currentHealth = enemyHealth - damageToDo;
        bosses.get(0).setCurrentHealth(currentHealth);
        System.out.println("You did: " + damageToDo + " damage");
        playerTurn = 2;
        if (bosses.get(0).getCurrentHealth() <= 0)
        {
            System.out.println("Boss Was Killed! You Gained a level!");
            System.out.println("You have received a unique weapon!");
            bosses.remove(0);
            weapons.add(BossDrops.get(0));
            BossDrops.remove(0);
            enemyDead = true;
            playerTurn = 0;
            character.setCurrentXP(999999999);
            SecondaryMenu();
        }
        if (character.getCurrentHealth() <= 0)
        {
            playerDead = true;
            playerTurn = 0;
            NewGame();
        }
        if (playerTurn == 2)
        {
            int attack = rng.nextInt(5);
            if (attack == 2) {
                long enemiesDamageToDo = (bosses.get(0).getDamage() * 2);
                System.out.println(bosses.get(0));
                long playerHealth = character.getCurrentHealth();
                long playerCurrentHealth = playerHealth - enemiesDamageToDo;
                character.setCurrentHealth(playerCurrentHealth);
                System.out.println("The boss used his special attack dealing: " + enemiesDamageToDo + " damage");
            }
            else {
                long enemiesDamageToDo = bosses.get(0).getDamage();
                System.out.println(bosses.get(0));
                long playerHealth = character.getCurrentHealth();
                long playerCurrentHealth = playerHealth - enemiesDamageToDo;
                character.setCurrentHealth(playerCurrentHealth);
                System.out.println("The boss did: " + enemiesDamageToDo + " damage");
            }
            if (bosses.get(0).getCurrentHealth() <= 0)
            {
                bosses.remove(0);
                enemyDead = true;
                playerTurn = 0;
                if(bosses.isEmpty()) {
                    gameOver();
                }
                else {
                    SecondaryMenu();
                }
            }
            if (character.getCurrentHealth() <= 0)
            {
                playerDead = true;
                playerTurn = 0;
                NewGame();
            }
            playerTurn = 0;
        }
    }


    private void Attack()
    {
        boolean enemyDead = false;
        boolean playerDead = false;
        int minXP = 100;
        int maxXP = 500;
        int xpToGain = rng.nextInt(maxXP - minXP);
        int playerTurn = 1;
        long damageToDo = character.getDamage();
        System.out.println("Character Name: " + character.getCharacterName() + " Character Health: " + character.getCurrentHealth());
        System.out.println(enemies.get(0));
        long enemyHealth = enemies.get(0).getCurrentHealth();
        long currentHealth = enemyHealth - damageToDo;
        enemies.get(0).setCurrentHealth(currentHealth);
        System.out.println("You did: " + damageToDo + " damage");
        playerTurn = 2;
        if (enemies.get(0).getCurrentHealth() <= 0)
        {
            System.out.println("Enemy Was Killed! You Gained " + xpToGain + " XP");
            enemies.remove(0);
            enemyDead = true;
            playerTurn = 0;
            int charactersXp = character.getCurrentXP();
            int expererienceCalculated = charactersXp + xpToGain;
            character.setCurrentXP(character.getCurrentXP() + expererienceCalculated);
            SecondaryMenu();
        }
        if (character.getCurrentHealth() <= 0)
        {
            playerDead = true;
            playerTurn = 0;
            NewGame();
        }
        if (playerTurn == 2)
        {
            long enemiesDamageToDo = enemies.get(0).getDamage();
            System.out.println(enemies.get(0));
            long playerHealth = character.getCurrentHealth();
            long playerCurrentHealth = playerHealth - enemiesDamageToDo;
            character.setCurrentHealth(playerCurrentHealth);
            System.out.println("The Enemy did: " + enemiesDamageToDo + " damage");
            if (enemies.get(0).getCurrentHealth() <= 0)
            {
                enemies.remove(0);
                enemyDead = true;
                playerTurn = 0;
                SecondaryMenu();
            }
            if (character.getCurrentHealth() <= 0)
            {
                playerDead = true;
                playerTurn = 0;
                NewGame();
            }
            playerTurn = 0;
        }
    }
    private void Defend() {
        boolean enemyDead = false;
        boolean playerDead = false;
        int minXP = 100;
        int maxXP = 500;
        int xpToGain = rng.nextInt(maxXP - minXP);
        int playerTurn = 1;
        long damageToDo = character.getDamage();
        System.out.println(character);
        System.out.println("You get into a block stance");
        playerTurn = 2;
        if (enemies.get(0).getCurrentHealth() <= 0) {
            enemies.remove(0);
            enemyDead = true;
            playerTurn = 0;
            SecondaryMenu();
        }
        if (character.getCurrentHealth() <= 0) {
            playerDead = true;
            playerTurn = 0;
            NewGame();
        }
        if (playerTurn == 2) {
            long enemiesDamageToDo = enemies.get(0).getDamage() - character.getDefense();
            if (enemiesDamageToDo <= 0)
            {
                enemiesDamageToDo = 0;
            }
            System.out.println(enemies.get(0));
            long playerHealth = character.getCurrentHealth();
            long playerCurrentHealth = playerHealth - enemiesDamageToDo + character.getDefense();
            character.setCurrentHealth(playerCurrentHealth);
            System.out.println("The Enemy did: " + enemiesDamageToDo + " damage");
            if (enemies.get(0).getCurrentHealth() <= 0) {
                enemies.remove(0);
                enemyDead = true;
                playerTurn = 0;
                SecondaryMenu();
            }
            if (character.getCurrentHealth() <= 0) {
                playerDead = true;
                playerTurn = 0;
                NewGame();
            }
            playerTurn = 0;
        }
    }

    private void BossDefend() {
        boolean enemyDead = false;
        boolean playerDead = false;
        int playerTurn = 1;
        long damageToDo = character.getDamage();
        System.out.println(character);
        System.out.println("You get into a block stance");
        playerTurn = 2;
        if (bosses.get(0).getCurrentHealth() <= 0) {
            bosses.remove(0);
            enemyDead = true;
            playerTurn = 0;
            SecondaryMenu();
        }
        if (character.getCurrentHealth() <= 0) {
            playerDead = true;
            playerTurn = 0;
            NewGame();
        }
        if (playerTurn == 2) {
            long enemiesDamageToDo = bosses.get(0).getDamage() - character.getDefense();
            if (enemiesDamageToDo <= 0)
            {
                enemiesDamageToDo = 0;
            }
            System.out.println(bosses.get(0));
            long playerHealth = character.getCurrentHealth();
            long playerCurrentHealth = playerHealth - enemiesDamageToDo + character.getDefense();
            character.setCurrentHealth(playerCurrentHealth);
            System.out.println("The Enemy did: " + enemiesDamageToDo + " damage");
            if (bosses.get(0).getCurrentHealth() <= 0) {
                bosses.remove(0);
                enemyDead = true;
                playerTurn = 0;
                SecondaryMenu();
            }
            if (character.getCurrentHealth() <= 0) {
                playerDead = true;
                playerTurn = 0;
                NewGame();
            }
            playerTurn = 0;
        }
    }

    private void Move(){

        if(character.getLevel() == 5) {
            createBossLVL_5();
            System.out.println("You encounter boss 1");
            bossCombat();
        }
        else if(character.getLevel() == 10) {
            createBossLVL_10();
            System.out.println("You encounter boss 2");
            bossCombat();
        }
        else if(character.getLevel() == 15) {
            createBossLVL_15();
            System.out.println("You encounter boss 3");
            bossCombat();
        }
        else if(character.getLevel() == 20) {
            createBossLVL_20();
            System.out.println("You encounter boss 4");
            bossCombat();
        }
        else {
            int selection;
            int min = 1;
            int max = 28;
            selection = rng.nextInt((max - min) + 1) + min;
            switch (selection) {
                case 1:
                    shopItems.clear();
                    System.out.println("You stumble across a dark forest. You see nothing");
                    break;
                case 2:
                    createEnemyLVL1_5();
                    System.out.println("You spot a village in the distance. While walking towards it you realize its full of goblins. You decide to turn back but then suddenly enemy approaches you!");
                    Combat();
                    break;
                case 3:
                    createEnemyLVL1_5();
                    System.out.println("You find a cave. After walking for a while you hear bones rattle, suddenly enemy runs towards you!");
                    Combat();
                    break;
                case 4:
                    createEnemyLVL1_5();
                    System.out.println("you step into a disgusting looking swamp. Suddenly an enemy approaches you!");
                    Combat();
                    break;
                case 5:
                    createEnemyLVL1_5();
                    System.out.println("You stumble across a weird wooden hut. After snooping around an enemy appears in front of you!");
                    Combat();
                    break;
                case 6:
                    createEnemyLVL1_5();
                    System.out.println("You find a plain grassy area. Suddenly you feel the earth shake and when you turn around you spot a enemy approaching you!");
                    Combat();
                    break;
                case 7:
                    System.out.println("You find the kingdom of Hyrule. Upon entering the kingdom you spot a shop");
                    shopItems.clear();
                    Shop();
                    break;
                case 8:
                    System.out.println("You find an abandoned house. You spot an unlocked chest while searching around");
                    PickupItem();
                    break;
                case 9:
                    System.out.println("You spot a cave with a shimmering light inside. Upon further investigation you find that the shimmer is coming from the ground");
                    PickupItem();
                    break;
                case 10:
                    System.out.println("an item falls from the sky in front of you");
                    PickupItem();
                    break;
                case 11:
                    System.out.println("You find a pirate ship. While rummaging around below deck, you hear something in the corner. Suddenly an enemy approaches you! ");
                    break;
                case 12:
                    System.out.println();
                    break;
                case 13:
                    System.out.println("You find the kingdom of Oryn. Upon entering the kingdom you spot a shop");
                    shopItems.clear();
                    Shop();
                    break;
                case 14:
                    System.out.println("You find the kingdom of Asterin. Upon entering the kingdom you spot a shop");
                    shopItems.clear();
                    Shop();
                    break;
                case 15:
                    System.out.println("You find the kingdom of Caspian. Upon entering the kingdom you spot a shop");
                    shopItems.clear();
                    Shop();
                    break;
                case 16:
                    System.out.println("You find The kingdom of Narnia. Upon entering the kingdom you spot a shop");
                    shopItems.clear();
                    Shop();
                    break;
                case 17:
                    System.out.println("You find the kingdom of Temeria. Upon entering the kingdom you spot a shop");
                    shopItems.clear();
                    Shop();
                    break;
                case 18:
                    createEnemyLVL1_5();
                    System.out.println("You find yourself on a spaceship. After going through the oxygen chamber, you are greeted with an unhappy enemy.");
                    Combat();
                    break;
                case 19:
                    createEnemyLVL1_5();
                    System.out.println("You fall into a hole. Your fall is broken thanks to an enemy. It does not look happy.");
                    Combat();
                    break;
                case 20:
                    createEnemyLVL1_5();
                    System.out.println("You go to your mothers house. You approach the door and notice it is slightly ajar. Upon entering, you spot a shadowy figure. You realize that this is most certainly not your mother.");
                    Combat();
                    break;
                case 21:
                    createEnemyLVL1_5();
                    System.out.println("You find a tree house and decide to climb it. Bad idea as an enemy was waiting for you.");
                    Combat();
                    break;
                case 22:
                    createEnemyLVL1_5();
                    System.out.println("You are walking about when suddenly an enemy appears out of thin air");
                    Combat();
                    break;
                case 23:
                    createEnemyLVL1_5();
                    System.out.println("You discover a large house that appears to to resemble a bucket. It has a giant unnerving fist above it. Upon entering you are greeted by a very tiny enemy. ");
                    Combat();
                    break;
                case 24:
                    createEnemyLVL1_5();
                    System.out.println("You are walking about when suddenly a giant rock lands on you. You wake up in the gulag, with an enemy right in front of you.");
                    Combat();
                    break;
                case 25:
                    createEnemyLVL1_5();
                    System.out.println("You find what looks like an abandoned school. Upon entering you spot an enemy inside of the cafeteria. Looks like you won't be getting any lunch.");
                    Combat();
                    break;
                case 26:
                    createEnemyLVL1_5();
                    System.out.println("You discover a large sewer pipe and decide to explore. You start hearing echos around you and suddenly an enemy appears.");
                    Combat();
                    break;
                case 27:
                    createEnemyLVL1_5();
                    System.out.println("You get captured by 2 large goons. You wake up in a colosseum with an angry enemy in front of you!");
                    Combat();
                    break;
                case 28:
                    createEnemyLVL1_5();
                    System.out.println("You encounter a fort named the Alamo. You vaguely remember this name. Upon entering an enemy approaches you!");
                    Combat();
                    break;
                case 29:
                    createEnemyLVL1_5();
                    System.out.println("You walk into a pyramid. Upon entering the catacombs, an enemy approaches you!");
                    Combat();
                    break;
                case 30:
                    createEnemyLVL1_5();
                    System.out.println("You spot an enemy hiding behind a comically large pole!");
                    Combat();
                    break;
                case 31:
                    createEnemyLVL1_5();
                    System.out.println("You spot an enemy hiding behind a comically large pole!");
                    PickupItem();
                    break;
                case 32:
                    createEnemyLVL1_5();
                    System.out.println("You find yourself in front of death mountain. You decide to climb it forever reason. Upon reaching the top you find a chest");
                    PickupItem();
                    break;
                case 33:
                    createEnemyLVL1_5();
                    System.out.println("You spot a scary looking brick house. Next to the house is a large pile of sticks and straw. Upon looking into the pile, you find an item");
                    PickupItem();
                    break;
                case 34:
                    createEnemyLVL1_5();
                    System.out.println("You find an item stuck in a tree.");
                    PickupItem();
                    break;
                case 35:
                    createEnemyLVL1_5();
                    System.out.println("You approach a wall with 2 levers, one on the left and one on the right. You decide to pull the right lever and to your surprise, an item drops in front of you!");
                    PickupItem();
                    break;
                case 36:
                    createEnemyLVL1_5();
                    System.out.println("You meet a wandering traveler. He feels generous and gives you an item.");
                    PickupItem();
                    break;
                case 37:
                    createEnemyLVL1_5();
                    System.out.println("You approach an abandoned spacecraft and find a very nice and shiny item inside.");
                    PickupItem();
                    break;
                case 38:
                    createEnemyLVL1_5();
                    System.out.println("You go to the restroom, and to your surprise, find an item inside of the toilet.");
                    PickupItem();
                    break;
                case 39:
                    createEnemyLVL1_5();
                    System.out.println("You find a dumpster and decide to go diving. Inside you find an item.");
                    PickupItem();
                    break;
                case 40:
                    createEnemyLVL1_5();
                    System.out.println("You are walking in a taiga when you spot an igloo. Inside of the igloo you find an item.");
                    PickupItem();
                    break;

                default:
                    System.out.println("THIS SHOULD NEVER HAPPEN TRY AGAIN");
            }
        }

    }
    private void Shop(){
           shopItems.add(addRandomPotion());
           shopItems.add(addRandomPotion());
           shopItems.add(addRandomWeapon());
           shopItems.add(addRandomWeapon());
           shopItems.add(addRandomArmor());
        System.out.println("Welcome to the shop! You're current balance is: " + character.getCurrentBalance());
        int selection;
        do {
            selection = ConsoleIO.promptForMenuSelection(Shop, true);

            switch (selection) {
                case 1:
                    Buy();
                    break;
                case 2:
                    Sell();
                    break;
                default:
                    System.out.println("Invalid option selected");
                    break;
            }
        }
        while (selection > 0);

    }

    private void Buy(){
        System.out.println(" You're current balance is: " + character.getCurrentBalance() + "\n");
        int x = 0;
        int item = 1;
        while (x < shopItems.size()) {
            System.out.println();
            System.out.println(Integer.toString(item) + ")" + shopItems.get(x));
            item++;
            x++;
        }
        int selection2;
        selection2 = ConsoleIO.promptForInt("\n" + "Which Item would you like to buy? (You will only be able to buy 1 item)" + "\n",1,5);

        switch (selection2) {
            case 1:
                    int item1Value = shopItems.get(0).getItemValue();
                    if (item1Value > character.getCurrentBalance())
                        System.out.println("This item is too expensive!");
                    else{

                        currentBalance = character.getCurrentBalance() - item1Value;
                        character.setCurrentBalance(currentBalance);
                        potions.add((Potion) shopItems.get(0));
                        shopItems.remove(0);
                        System.out.println("Your new balance is " + character.getCurrentBalance());
                        SecondaryMenu();
                    }
                    break;

            case 2:
                    int item2Value = shopItems.get(1).getItemValue();
                    if (item2Value > character.getCurrentBalance())
                        System.out.println("This item is too expensive!");
                    else{
                        currentBalance = character.getCurrentBalance() - item2Value;
                        character.setCurrentBalance(currentBalance);
                        potions.add((Potion) shopItems.get(1));
                        shopItems.remove(1);
                        System.out.println("Your new balance is " + character.getCurrentBalance());
                        SecondaryMenu();
                    }
                    break;

            case 3:
                    int item3Value = shopItems.get(2).getItemValue();
                    if (item3Value > character.getCurrentBalance())
                        System.out.println("This item is too expensive!");
                    else{
                        currentBalance = character.getCurrentBalance() - item3Value;
                        character.setCurrentBalance(currentBalance);
                        weapons.add((Weapon) shopItems.get(2));
                        shopItems.remove(2);
                        System.out.println("Your new balance is " + character.getCurrentBalance());
                        SecondaryMenu();
                    }
                    break;
            case 4:
                    int item4Value = shopItems.get(3).getItemValue();
                    if (item4Value > character.getCurrentBalance())
                        System.out.println("This item is too expensive!");
                    else{
                        currentBalance = character.getCurrentBalance() - item4Value;
                        character.setCurrentBalance(currentBalance);
                        weapons.add((Weapon) shopItems.get(3));
                        shopItems.remove(3);
                        System.out.println("Your new balance is " + character.getCurrentBalance());
                        SecondaryMenu();
                    }
                    break;
            case 5:
                    int item5Value = shopItems.get(4).getItemValue();
                    if (item5Value > character.getCurrentBalance())
                        System.out.println("This item is too expensive!");
                    else{
                        currentBalance = character.getCurrentBalance() - item5Value;
                        character.setCurrentBalance(currentBalance);
                        armors.add((Armor) shopItems.get(4));
                        shopItems.remove(4);
                        System.out.println("Your new balance is " + character.getCurrentBalance());
                        SecondaryMenu();
                    }
                    break;
            default:
                System.out.println("Invalid option selected");
                break;
        }
    }
    private void Sell() {

        int selection2;
        selection2 = ConsoleIO.promptForMenuSelection(Sell, true);
        switch (selection2) {
            case 1:
                SellPotions();
                break;
            case 2:
                SellWeapons();
                break;
            case 3:
                SellArmor();
                break;
            default:
                System.out.println("Invalid option selected");
                break;
        }
    }
    private void SellPotions(){
        if (potions.isEmpty()){
            System.out.println("There are no potions in your inventory.");
        }
        else {
            for (Item potion : potions) {
                System.out.println(potion);
            }
            int index;
            index = ConsoleIO.promptForInt("Choose a potion to sell",1, potions.size());
            int item1Value = potions.get(index - 1).getItemValue();
            currentBalance = character.getCurrentBalance() + item1Value;
            character.setCurrentBalance(currentBalance);
            potions.remove(index - 1);
            System.out.println("Your new balance is " + character.getCurrentBalance());

        }
    }

    private void SellWeapons(){
        if (weapons.isEmpty()){
            System.out.println("There are no weapons in your inventory.");
        }
        else {
            for (Item weapons : weapons) {
                System.out.println(weapons);
            }
            int index;
            index = ConsoleIO.promptForInt("Choose a weapon to sell",1, weapons.size());
            int item1Value = weapons.get(index - 1).getItemValue();
            currentBalance = character.getCurrentBalance() + item1Value;
            character.setCurrentBalance(currentBalance);
            weapons.remove(index - 1);
            System.out.println("Your new balance is " + character.getCurrentBalance());
        }
    }

    private void SellArmor(){
        if (armors.isEmpty()){
            System.out.println("There is no armor in your inventory.");
        }
        else {
            for (Item armor : armors) {
                System.out.println(armor);
            }
            int index;
            index = ConsoleIO.promptForInt("Choose which armor to sell",1, armors.size());
            int item1Value = armors.get(index - 1).getItemValue();
            currentBalance = character.getCurrentBalance() + item1Value;
            character.setCurrentBalance(currentBalance);
            armors.remove(index - 1);
            System.out.println("Your new balance is " + character.getCurrentBalance());
        }
    }

    private void PickupItem(){
        MakeGroundItem(1);
    }
    private void ShowEquippedItems(){
        if(EquippedWeapon.isEmpty()) {
            System.out.println("No weapon equipped");
        }
        else {
            System.out.println(EquippedWeapon.get(0));
        }
        if(EquippedArmor.isEmpty()) {
            System.out.println("No armor equipped");
        }
        else {
            System.out.println(EquippedArmor.get(0));
        }
    }

    private void EquipWeapon(){
        for (int i = 0; i<weapons.size(); i++) {
            System.out.print(i + 1 + ") ");
            System.out.println(weapons.get(i));
        }

        System.out.print("What weapon would you like to equip? ");
        String input = scanner.nextLine();
        if(Integer.parseInt(input) < 0 || Integer.parseInt(input) > weapons.size()) {
            System.out.println("Invalid input please try again");
        }
        else {
            if (EquippedWeapon.isEmpty()) {
                EquippedWeapon.add(weapons.get(Integer.parseInt(input) - 1));
                weapons.remove(Integer.parseInt(input) - 1);
                int damageCalculated = (int) (character.getDamage() + EquippedWeapon.get(0).getMinDamage());
                System.out.println(EquippedWeapon.get(0).getMinDamage());
                System.out.println(character.getDamage());
                character.setDamage(damageCalculated);
            } else {
                weapons.add((Weapon) EquippedWeapon.get(0));
                EquippedWeapon.remove(0);
                EquippedWeapon.add(weapons.get(Integer.parseInt(input) - 1));
                weapons.remove(Integer.parseInt(input) - 1);
                int damageCalculated = (int) (character.getDamage() + EquippedWeapon.get(0).getMaxDamage());
                character.setDamage(damageCalculated);
            }
        }
    }

    private void EquipArmor(){
        for (int i = 0; i<armors.size(); i++) {
            System.out.print(i + 1 + ") ");
            System.out.println(armors.get(i));
        }

        System.out.print("What armor would you like to equip? ");
        String input = scanner.nextLine();
        if(Integer.parseInt(input) < 0 || Integer.parseInt(input) > armors.size()) {
            System.out.println("Invalid input please try again");
        }
        else {
            if (EquippedArmor.isEmpty()) {
                EquippedArmor.add(armors.get(Integer.parseInt(input) - 1));
                armors.remove(Integer.parseInt(input) - 1);
            } else {
                armors.add(EquippedArmor.get(0));
                EquippedArmor.remove(0);
                EquippedArmor.add(armors.get(Integer.parseInt(input) - 1));
                armors.remove(Integer.parseInt(input) - 1);
            }
        }
    }

    private void UsePotion() {
        for (int i = 0; i<potions.size(); i++) {
            System.out.print(i + 1 + ") ");
            System.out.println(potions.get(i));
        }
        System.out.println("Which Potion would you like to use? ");
        String input = scanner.nextLine();
        if(Integer.parseInt(input) < 0 || Integer.parseInt(input) > potions.size()) {
            System.out.println("Invalid input please try again");
        }
        else {
            character.setCurrentHealth((long) (character.getCurrentHealth() + potions.get(Integer.parseInt(input) - 1).getHealAmount()));
            potions.remove(Integer.parseInt(input) - 1);
            if (character.getCurrentHealth() > character.getMaxHealth()) {
                character.setCurrentHealth(character.getMaxHealth());
            }
        }
    }

    private int showInventory() {
        {
            int selection;
            do {
                selection = ConsoleIO.promptForMenuSelection(InventoryMenu, true);
                switch (selection) {
                    case 1:
                        showItem();
                        break;
                    case 3:
                        RemoveItem();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("This SHOULD NEVER HAPPEN TRY AGAIN");
                }
            }
            while (selection > 0);

            return selection;
        }
    }
    private int showItem() {
        {
            int selection;
            do {
                selection = ConsoleIO.promptForMenuSelection(ShowItemMenu, true);
                switch (selection) {
                    case 1:
                        seeWeapons();
                        break;
                    case 2:
                        seeArmors();
                        break;
                    case 3:
                        seePotions();
                        break;
                    case 4:
                        seeInventory();
                        break;
                    case 5:
                        ShowEquippedItems();
                        break;
                    case 6:
                        EquipWeapon();
                        break;
                    case 7:
                        EquipArmor();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("This SHOULD NEVER HAPPEN TRY AGAIN");
                }
            }
            while (selection > 0);

            return selection;
        }
    }
    private int RemoveItem() {
        {
            int selection;
            do {
                selection = ConsoleIO.promptForMenuSelection(RemoveItemMenu, true);
                switch (selection) {
                    case 1:
                        removeWeapon();
                        break;
                    case 2:
                        removeArmor();
                        break;
                    case 3:
                        removePotion();
                        break;
                    case 4:
                        removeAll();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("This SHOULD NEVER HAPPEN TRY AGAIN");
                }
            }
            while (selection > 0);

            return selection;
        }
    }
    private Weapon addRandomWeapon(){
        Weapon weapon = new Weapon();
        return weapon;
    }
    private Armor addRandomArmor(){
        Armor armor = new Armor();
        return armor;
    }
    private Potion addRandomPotion(){
        Potion potion = new Potion();
        return potion;
    }
    private void seeWeapons(){
        if (weapons.isEmpty()){
            System.out.println("There are no weapons please add some");
        }
        else {
            for(Item weapon : weapons)
                System.out.println(weapon);
        }
    }
    private void seeArmors(){
        if (armors.isEmpty()){
            System.out.println("There are no armors please add some");
        }
        else {
            for (Item armor : armors) {
                System.out.println(armor);
            }
        }
    }
    private void seePotions(){
        if (potions.isEmpty()){
            System.out.println("There are no potions please add some");
        }
        else {
            for (Item potion : potions) {
                System.out.println(potion);
            }
        }
    }
    private void seeInventory(){
        System.out.println("Your inventory contains ");
        System.out.println(inventory.getItems().size());
        System.out.println("Items");
        if (potions.isEmpty()){
            System.out.println("There are no potions please add some");
        }
        if (armors.isEmpty()){
            System.out.println("There are no armors please add some");
        }
        if (weapons.isEmpty()){
            System.out.println("There are no weapons please add some");
        }
        else {
            for (Item weapon : weapons){
                System.out.println(weapon);
            }
            for (Item armor : armors){
                System.out.println(armor);
            }
            for (Item potion : potions){
                System.out.println(potion);
            }
        }
    }
    private void removeWeapon(){
        if (weapons.isEmpty()){
            System.out.println("There are no weapons please add some before you can remove them");
        }
        else {
            for (Item weapon : weapons) {
                System.out.println(weapon);
            }
            int index = promptForInt("Enter the Number you would like to delete the first in the list is number 0", 0, 999999999);
            weapons.remove(index);
        }
    }
    private void removeArmor(){
        if (armors.isEmpty()){
            System.out.println("There are no armors please add some before you can remove them ");
        }
        else {
            for (Item armor : armors) {
                System.out.println(armor);
            }
            int index = promptForInt("Enter the Number you would like to delete the first in the list is number 0", 0, 999999999);

            armors.remove(index);
        }
    }
    private void removePotion(){
        if (potions.isEmpty()){
            System.out.println("There are no potions please add some before you can remove them");
        }
        else {
            for (Item potion : potions) {
                System.out.println(potion);
            }
            int index = promptForInt("Enter the Number you would like to delete the first in the list is number 0", 0, 999999999);
            potions.remove(index);
        }
    }
    private void removeAll(){
        weapons.clear();
        armors.clear();
        potions.clear();
        System.out.println("Removed all items successfully");
    }
    private void MakeItem(int amount){
        for (int count = 0;count < amount;count++){

            int maxChoice = 3;
            int minChoice = 1;
            int randomchoice = rng.nextInt(maxChoice - minChoice + 1) + minChoice;
            switch (randomchoice){
                case 1:
                    addRandomWeapon();
                    break;
                case 2:
                    addRandomArmor();
                    break;
                case 3:
                    addRandomPotion();
                    break;
            }
        }

    }

    private void MakeGroundItem(int amount){
        for (int count = 0;count < amount;count++){

            int maxChoice = 3;
            int minChoice = 1;
            int randomchoice = rng.nextInt(maxChoice - minChoice + 1) + minChoice;
            switch (randomchoice){
                case 1:
                    GroundItems.add(addRandomWeapon());
                    PickUpWeapon();
                    break;
                case 2:
                    GroundItems.add(addRandomArmor());
                    PickUpArmor();
                    break;
                case 3:
                    GroundItems.add(addRandomPotion());
                    PickUpPotion();
                    break;
            }
        }
    }
    private int PickUpWeapon()
    {
        int selection = ConsoleIO.promptForInt("You find:" + GroundItems + "Would you like to take it? Yes = 1, No = 2", 0, 2);
        switch (selection) {
            case 1:
                weapons.add((Weapon) GroundItems.get(0));
                GroundItems.remove(0);
                break;
            case 2:
                GroundItems.remove(0);
                SecondaryMenu();
                break;
            case 0:
                break;
            default:
                System.out.println("This SHOULD NEVER HAPPEN TRY AGAIN");
        }

        return selection;

    }
    private int PickUpArmor()
    {
        int selection = ConsoleIO.promptForInt("You find:" + GroundItems + "Would you like to take it? Yes = 1, No = 2", 0, 2);
        switch (selection) {
            case 1:
                armors.add(GroundItems.get(0));
                GroundItems.remove(0);
                break;
            case 2:
                GroundItems.remove(0);
                SecondaryMenu();
                break;
            case 0:
                break;
            default:
                System.out.println("This SHOULD NEVER HAPPEN TRY AGAIN");
        }

        return selection;

    }
    private int PickUpPotion()
    {
        int selection = ConsoleIO.promptForInt("You find:" + GroundItems + "Would you like to take it? Yes = 1, No = 2", 0, 2);
        switch (selection) {
            case 1:
                potions.add((Potion) GroundItems.get(0));
                GroundItems.remove(0);
                break;
            case 2:
                GroundItems.remove(0);
                SecondaryMenu();
                break;
            case 0:
                break;
            default:
                System.out.println("This SHOULD NEVER HAPPEN TRY AGAIN");
        }

        return selection;

    }
    private Enemy createEnemyLVL1_5()
    {
        int max = 50;
        int min = 10;
        //Enemy Level 1-5;
        ArrayList<String> enemyPrefixes = new ArrayList<>(Arrays.asList("Ambassador","Arena Master","Gladiator","Vanquisher","Battlemaster","Blood Guard","Admiral","Brewmaster","Centurion"));
        ArrayList<String> enemyNames = new ArrayList<>(Arrays.asList("Kromwold ","Jack ","Max ","Keebie ","Swomold ","Tiknold ","Digner ","Veemstid ","Hamininki","Hacheek","Maddisonskelis","Cheekhamilton","Fornson","Maniar","Rusaladdison","Madilaf","Onoson",
                "Bolguf","Orzka","Gizka","Nazbad","Zungluk","Nacklik"));
        ArrayList<String> enemySuffixes = new ArrayList<>(Arrays.asList("The Great","The Grand","The Murderous","The Deadly","The Killer","The DragonSlayer","The Crazy","The Psycho","The Exalted","Bane of the Fallen King","The Dragon's Bane","The Brutal",
                "The Nobody"));
        String enemyName = enemyPrefixes.get(rng.nextInt(enemyPrefixes.size())) +" "+ enemyNames.get(rng.nextInt(enemyNames.size())) +" "+ enemySuffixes.get(rng.nextInt(enemySuffixes.size()));
        long maxEnemyHealth = rng.nextInt(max - min);
        long currentEnemyHealth = maxEnemyHealth;
        int enemyDamage = rng.nextInt(5);
        int enemyDefense = rng.nextInt(5);
        Enemy enemy = new Enemy(enemyName,currentEnemyHealth,maxEnemyHealth,enemyDamage,enemyDefense);
        enemies.add(enemy);
        return  enemy;

    }

    private Enemy createBossLVL_5() {
        String bossName = "Boss One";
        long maxHealth = 50;
        long currentEnemyHealth = maxHealth;
        int enemyDamage = 10;
        int enemyDefense = 5;
        Enemy enemy = new Enemy(bossName, currentEnemyHealth, maxHealth, enemyDamage, enemyDefense);
        bosses.add(enemy);
        return enemy;
    }

    private Enemy createBossLVL_10() {
        String bossName = "Boss Two";
        long maxHealth = 100;
        long currentEnemyHealth = maxHealth;
        int enemyDamage = 15;
        int enemyDefense = 10;
        Enemy enemy = new Enemy(bossName, currentEnemyHealth, maxHealth, enemyDamage, enemyDefense);
        bosses.add(enemy);
        return enemy;
    }

    private Enemy createBossLVL_15() {
        String bossName = "Boss Three";
        long maxHealth = 150;
        long currentEnemyHealth = maxHealth;
        int enemyDamage = 15;
        int enemyDefense = 10;
        Enemy enemy = new Enemy(bossName, currentEnemyHealth, maxHealth, enemyDamage, enemyDefense);
        bosses.add(enemy);
        return enemy;
    }

    private Enemy createBossLVL_20() {
        String bossName = "Boss Four";
        long maxHealth = 200;
        long currentEnemyHealth = maxHealth;
        int enemyDamage = 20;
        int enemyDefense = 15;
        Enemy enemy = new Enemy(bossName, currentEnemyHealth, maxHealth, enemyDamage, enemyDefense);
        bosses.add(enemy);
        return enemy;
    }

    private void gameOver() {
        System.out.println("Congratulations all bosses have been slain you win!");
    }

    private void saveGame()
    {
        String filePath = promptForString("What is the filepath and .extension: ");
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(character);
            objectOutputStream.writeObject(inventory);
            objectOutputStream.writeObject(weapons);
            objectOutputStream.writeObject(potions);
            objectOutputStream.writeObject(armors);
        } catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
    private void loadGame()
    {
        String filePath = promptForString("What is the filepath and .extension: ");
        try{
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            character = (Character) objectInputStream.readObject();
            inventory = (Inventory) objectInputStream.readObject();
            weapons = (ArrayList<Weapon>) objectInputStream.readObject();
            potions = (ArrayList<Potion>) objectInputStream.readObject();
            armors = (ArrayList<Item>) objectInputStream.readObject();
        } catch(IOException | ClassNotFoundException ioe)
        {
            ioe.printStackTrace();
        }
        SecondaryMenu();
    }


}
