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
    private ArrayList<Item> weapons = new ArrayList<>();
    private ArrayList<Item> armors = new ArrayList<>();
    private ArrayList<Potion> potions = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Item> GroundItems = new ArrayList<>();
    private ArrayList<Item> EquippedWeapon = new ArrayList<>();
    private ArrayList<Item> EquippedArmor = new ArrayList<>();
    private ArrayList<Item> EquippedPotion = new ArrayList<>();
    private  String[] InventoryMenu = {"Show Whole Inventory","Remove An Item"};
    private String[] ShowItemMenu = {"Show Weapons","Show Armors","Show Potions","Show Entire Inventory", "Show Equipped Items", "Equip Weapon", "Equip Armor"};
    private String[] RemoveItemMenu = {"Remove a Weapon","Remove a Piece of Armor","Remove a Potion","Remove All Items"};
    private String[] AddRandomItemMenu = {"Add 1 Item","Add Some Items","Add Many Items"};
    private String[] MainMenu = {"New Game","Load Game"};
    private String[] SecondaryMenu ={"Move","Access Inventory","View Stats","Save Game"};
    private String[] AttributeMenu = {"Strength","Stamina","Agility","Vitality"};
    private String[] Combat = {"Attack","Defend","Use Potion","Run"};
    Inventory inventory = new Inventory();
    public Character character = new Character(characterName,100,100,10,1,1,1,10,0,1);
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
        AttributeMenu();



    }
    public void AttributeMenu()
    {
        int Points = 5;
        int selection;
        do{
            System.out.println("You have " + Points + " Points To Spend");
            selection = ConsoleIO.promptForMenuSelection(AttributeMenu,false);
            switch(selection)
            {
                case 1:
                    //Strength
                    character.setStrength(character.getStrength() +1);
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
        SecondaryMenu();
    }

    public int SecondaryMenu() {
        int selection;
        do {
            selection = ConsoleIO.promptForMenuSelection(SecondaryMenu, true);

            switch (selection) {
                case 1:
                    Move();
                    break;
                case 2:
                    showInventory();
                    break;
                case 3:
                    System.out.println(character);
                    break;
                case 4:
                    saveGame();
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
            character.setCurrentXP(xpToGain);
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

    private void Move(){

        int selection;
        int min = 1;
        int max = 10;
        selection = rng.nextInt((max - min) + 1) + min;
        switch (selection){
            case 1:
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
                System.out.println("You find a a kingdom. Upon entering the kingdom you spot a shop");
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
            default:
                System.out.println("THIS SHOULD NEVER HAPPEN TRY AGAIN");
        }

    }
    private void Shop(){
        System.out.println("The shop system has not been implemented yet!");
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
            } else {
                weapons.add(EquippedWeapon.get(0));
                EquippedWeapon.remove(0);
                EquippedWeapon.add(weapons.get(Integer.parseInt(input) - 1));
                weapons.remove(Integer.parseInt(input) - 1);
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
                weapons.add(GroundItems.get(0));
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
    private void saveGame()
    {
        String filePath = promptForString("What is the filepath and .extension: ");
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(character);
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
        } catch(IOException | ClassNotFoundException ioe)
        {
            ioe.printStackTrace();
        }
        SecondaryMenu();
    }


}