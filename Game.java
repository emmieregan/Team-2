import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import javafx.application.Platform;
/**
 * This is the driver class for a text-based adventure game called "The Tower"
 *
 * @author Emmie Regan Ange Li Anisa Rodriguez
 * @version Spring 2025 
 */
public class Game {
    
    private Player p1; //initialize a new Player object to be able to call 
    private Room r0, r1, r2, r3; //references the rooms
    private GameGUIAnisa gui; //references the GUI
    
    public Game(GameGUIAnisa gui){
        this.gui = gui;
        
        //instantiate new animals for each of the four rooms 
        fireEnemy fire = new fireEnemy("Patrol", 5, 2); 
        waterEnemy water = new waterEnemy("Tower Guardian", 10, 5); 
        poisonEnemy poison = new poisonEnemy("Key Knight", 15, 10); 
        Enemy noEnemy = new Enemy("nothing" , 0, 0); 
        
        //instantiate new objects for each of the four rooms r0, r1, r2, r3
        Room r0 = new Room("the bottom floor", "Look inside the single treasure chest.", false, fire, 0); 
        Room r1 = new Room("the second floor", "What new treasure--or challenge--awaits you in this chest?", false, water, 1);
        Room r2 = new Room("the third floor", "The key to the final floor is in this chest, but can you defeat the Key Knight?", false, poison, 2); 
        Room r3 = new Room("the top floor", "The treasure is yours! But is it what you expected?", true, noEnemy, 3); 
        
        Armour shield = new Armour("Shield", "a shield", 10); 
        Food bread = new Food("Bread", "a piece of bread", 10); 
        Armour armour = new Armour("Armour", "a suit of armour", 20);
        Food steak = new Food("Steak", "a hearty piece of meat", 20); 
        Treasure key = new Treasure("Key", "the key to the locked room");
        Treasure message = new Treasure("Message", "a mysterious message"); 
        Food fruit = new Food("Fruit", "fresh fruit", 25); 
        Treasure brainRotCat = new Treasure("Brainrot-Cat", "this is the final treasure"); 
        
        r0.addTreasure(shield); 
        r0.addTreasure(bread);
        r1.addTreasure(armour); 
        r1.addTreasure(steak); 
        r2.addTreasure(key); 
        r2.addTreasure(message); 
        r2.addTreasure(fruit); 
        r3.addTreasure(brainRotCat); 
        
        //for each of the four rooms, add their neighbors using the setNeighbors() method
        r0.setNeighbors(r1,null,null,null);
        r1.setNeighbors(r2,null,null,r0); 
        r2.setNeighbors(r3,null,null,r1); 
        r3.setNeighbors(null,null,null,r2); 

    
        p1 = new Player(100, "none", r0);
        if (gui != null){
            gui.appendToDescription("You are starting on "+p1.getLocation());
        }else{
            System.out.println("GUI is null in Game constructor (this should not happen if set correctly).");
        }
        
    }
    public void setGUI(GameGUIAnisa gui){
        this.gui = gui;
    }
    public Player getPlayer(){
        return p1; 
    }
    
    public void parseCommand(String input) {
        
        String[] wordList = input.split("[\s]");
        String verb;
        String noun;
        
        if(wordList.length < 2 || wordList.length > 2) {
            gui.appendToDescription("Only 2 word commands allowed!");
        } else {
            verb = wordList[0];
            noun = wordList[1];
            switch (verb) {
                case "take":
                    take(noun); 
                    break;
                case "drop":
                    drop(noun); 
                    break;
                case "eat":
                    eat(noun); 
                    break;
                case "go":
                    movePlayer(noun);
                    break;
                case "check": 
                    check(noun);
                    break;
                default:
                    gui.appendToDescription(verb + " is not a known verb!");
            }
        }
    }
    
    public void movePlayer(String direction) {
        Room room = p1.getLocation(); //where the player currently is
        Room nextRoom = null; // where the player is going next
        switch (direction) {
            case "North":
                nextRoom = room.getN();
                break;
            case "South":
                nextRoom = room.getS();
                break;
            case "East":
                nextRoom = room.getE();
                break;
            case "West":
                nextRoom = room.getW();
                break;
            default:
                gui.appendToDescription("Unknown direction, player did not move.");
        }
        if (nextRoom != null) {
            p1.setLocation(nextRoom); //updates the location
            gui.appendToDescription("You are now on " + p1.getLocation());
            if (gui != null) {
                Room actualNextRoom = nextRoom; //wouldn't work without reassigning to a new variable
                Platform.runLater(() -> gui.updateRoomVisual(actualNextRoom.getId())); //gui update
            }
            getAttacked(p1.getLocation()); // attacks
        } else {
            gui.appendToDescription("You cannot go that way.");
        }
    }
    
    public void getAttacked(Room playerLocation){
        Enemy enemy = playerLocation.getEnemy(); 
        int enemyDamage = enemy.getDamage(); 
        int playerHealth = p1.getHealth(); 
        int playerArmour = p1.getArmour(); 
        int damageTaken = Math.max(0, enemyDamage - playerArmour);
        if (damageTaken > 0){
            p1.setHealth(playerHealth - damageTaken);
            gui.appendToDescription("You were attacked by " + enemy.getName() 
                + " and took " + enemy.getDamage() + " damage."); 
            if (gui != null){
                Platform.runLater(() ->gui.updateHealthBar());
            }
        } else {
            gui.appendToDescription("You blocked all damage!");
        }
        
    }
    
    public void take(String t) {
        Room room = p1.getLocation();
        Treasure treasure = room.findTreasure(t);
        if(room.containsTreasure(t)){
            p1.addTreasure(treasure);
            room.removeTreasure(t);
            gui.appendToDescription("You added "+t+" to your inventory.");
        }else{
            gui.appendToDescription(t+ " is not in the treasure chest.");
        }
    }
    
    public void drop(String t){
        Room room = p1.getLocation(); 
        Treasure treasure = room.findTreasure(t); 
        ArrayList<Treasure> playerInventory = p1.getInventory(); 
        ArrayList<Treasure> treasureChest = room.getTreasureChest(); 
        int playerArmour = p1.getArmour(); 
        if (p1.hasTreasure(t)){
            playerInventory.remove(treasure); 
            treasureChest.add(treasure); 
            gui.appendToDescription("You dropped "+t+" from your inventory into the treasure chest.");
            
            if (treasure instanceof Armour){
            Armour armourAdded = (Armour)treasure; 
            p1.setArmour(playerArmour - armourAdded.getArmourAdded()); 
            }
        }
        else{
            gui.appendToDescription(t+ " is not in your inventory.");
        }
    }
    
    public void eat(String t){
        ArrayList<Treasure> playerInventory = p1.getInventory(); 
        Room room = p1.getLocation();
        Treasure treasure = room.findTreasure(t);
        int playerHealth = p1.getHealth(); 
        if (p1.hasTreasure(t)){
            if (treasure instanceof Food){
                Food ateFood = (Food)treasure; 
                p1.setHealth(playerHealth + ateFood.getHealthAdded());  
                playerInventory.remove(treasure); 
                gui.appendToDescription("You ate " + t + " and gained " 
                + ateFood.getHealthAdded() + " health."); 
                if (gui != null){
                    Platform.runLater(() ->gui.updateHealthBar());
                }
            }
        }
        else {
            gui.appendToDescription(t+ " is not in your inventory.");
        }
    }
    
    public void check(String t){
        if (t.equals("Inventory")){
            ArrayList<Treasure> inventory = p1.getInventory();
            for (Treasure item : inventory){
                gui.appendToDescription(item.getName() + item.getDescription());
            }
            
        }
        else if (t.equals("Stats")){
            gui.appendToDescription("Your health: " + p1.getHealth() +
            "\n Your armour: " + p1.getArmour());
        }
        else if (t.equals("Health")){
            gui.appendToDescription("Your health: " + p1.getHealth()); 
        }
        else if (t.equals("Armour")){
            gui.appendToDescription("Your armour: " + p1.getArmour()); 
        }
        else{
            gui.appendToDescription("There is nothing to check."); 
        }
    }

    
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String input = "";
        GameGUIAnisa guigui = new GameGUIAnisa();
        Game gm = new Game(guigui);
        

        // An introduction displayed to the user 
        // Let the user know what they can do and how to quit, the user quits by entering 'q'
        guigui.appendToDescription("Welcome to the Tower,treasure seeker. Can you make it to the treasure chest on the top floor?"); 
        guigui.appendToDescription("You can enter 'go North' to move up a floor and 'go South' to move down a floor."); 
        guigui.appendToDescription("To take a treasure, enter 'take' followed by 'name of the item'"); 
        guigui.appendToDescription("To quit the game, enter 'q'."); 
        
        while(!input.equals("q")) {
            input = scan.nextLine();
            if(input.equals("q")) {
                guigui.appendToDescription("Thanks for playing!");
                break;
            }else{
                gm.parseCommand(input);
            }
        }
        
        scan.close();
        
    }
}