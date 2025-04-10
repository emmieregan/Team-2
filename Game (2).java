import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
/**
 * This is the driver class for a text-based adventure game called "The Tower"
 *
 * @author Ange Li
 * @version Spring 2025 
 */
public class Game {
    
    private Player p1; //initialize a new Player object to be able to call 
    private Room r0, r1, r2, r3;
    
    public Game(){
        
        
        //instantiate new animals for each of the four rooms 
        Animal patrol = new Animal("Patrol", 5); 
        Animal towerGuardian = new Animal("Tower Guardian", 10); 
        Animal keyKnight = new Animal("Key Knight", 15); 
        Animal noAnimal = new Animal("nothing" , 0); 
        
        //instantiate new objects for each of the four rooms r0, r1, r2, r3
        Room r0 = new Room("the bottom floor", "Look inside the single treasure chest.", false, patrol); 
        Room r1 = new Room("the second floor", "What new treasure--or challenge--awaits you in this chest?", false, towerGuardian);
        Room r2 = new Room("the third floor", "The key to the final floor is in this chest, but can you defeat the Key Knight?", false, keyKnight); 
        Room r3 = new Room("the top floor", "The treasure is yours! But is it what you expected?", true, noAnimal); 
        
        Armour shield = new Armour("Shield", "a shield", 10); 
        Food bread = new Food("Bread", "a piece of bread", 10); 
        Armour armour = new Armour("Armour", "a suit of armour", 20);
        Food steak = new Food("Steak", "a hearty piece of meat", 20); 
        Treasure key = new Treasure("Key", "the key to the locked room");
        Treasure message = new Treasure("Message", "a mysterious message"); 
        Food fruit = new Food("Fruit", "fresh fruit", 25); 
        Treasure map = new Treasure("Map", "this is the final treasure"); 
        
        r0.addTreasure(shield); 
        r0.addTreasure(bread);
        r1.addTreasure(armour); 
        r1.addTreasure(steak); 
        r2.addTreasure(key); 
        r2.addTreasure(message); 
        r2.addTreasure(fruit); 
        r3.addTreasure(map); 
        
        //for each of the four rooms, add their neighbors using the setNeighbors() method
        r0.setNeighbors(r1,null,null,null);
        r1.setNeighbors(r2,null,null,r0); 
        r2.setNeighbors(r3,null,null,r1); 
        r3.setNeighbors(null,null,null,r2); 

    
        p1 = new Player("treasure seeker", "this is you!", r0);
        System.out.println("You are starting on "+p1.getLocation());
    }
    
    public Player getPlayer(){
        return p1; 
    }
    
    public void parseCommand(String input) {
        
        String[] wordList = input.split("[\s]");
        String verb;
        String noun;
        
        if(wordList.length < 2 || wordList.length > 2) {
            System.out.println("Only 2 word commands allowed!");
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
                    System.out.println(verb + " is not a known verb!");
            }
        }
    }
    
    public void movePlayer(String direction) {
        Room room = p1.getLocation();
        switch (direction) {
            case "North":
                p1.setLocation(room.getN()); // set player location to the room at the north of the current room
                getAttacked(p1.getLocation()); 
                break;
            case "South":
                p1.setLocation(room.getS());
                getAttacked(p1.getLocation());
                break;
            case "East":
                p1.setLocation(room.getE());
                break;
            case "West":
                p1.setLocation(room.getW());
                break;
            default:
                System.out.println("Unknown direction, player did not move.");
        }
        System.out.println("You are now on "+p1.getLocation());
    }
    
    public void getAttacked(Room playerLocation){
        Animal animal = playerLocation.getAnimal(); 
        int animalDamage = animal.getDamage(); 
        int playerHealth = p1.getHealth(); 
        int playerArmour = p1.getArmour(); 
        if (animalDamage >= playerArmour){
            p1.setHealth(playerHealth + playerArmour - animalDamage);
        }
        System.out.println("You were attacked by " + animal.getName() 
        + " and took " + animalDamage + " damage."); 
    }
    
    public void take(String t) {
        Room room = p1.getLocation();
        if(room.containsTreasure(t)){
            p1.addTreasure(t);
            room.removeTreasure(t);
            System.out.println("You added "+t+" to your inventory.");
        }else{
            System.out.println(t+ " is not in the treasure chest.");
        }
    }
    
    public void drop(String t){
        Room room = p1.getLocation(); 
        Treasure treasure = p1.findTreasure(t); 
        ArrayList<Treasure> playerInventory = p1.getInventory(); 
        ArrayList<Treasure> treasureChest = room.getTreasureChest(); 
        int playerArmour = p1.getArmour(); 
        if (p1.hasTreasure(t)){
            playerInventory.remove(treasure); 
            treasureChest.add(treasure); 
            System.out.println("You dropped "+t+" from your inventory into the treasure chest.");
            
            if (treasure instanceof Armour){
            Armour armourAdded = (Armour)treasure; 
            p1.setArmour(playerArmour - armourAdded.getArmourAdded()); 
            }
        }
        else{
            System.out.println(t+ " is not in your inventory.");
        }
    }
    
    public void eat(String t){
        ArrayList<Treasure> playerInventory = p1.getInventory(); 
        Treasure treasure = p1.findTreasure(t);
        int playerHealth = p1.getHealth(); 
        if (p1.hasTreasure(t)){
            if (treasure instanceof Food){
                Food ateFood = (Food)treasure; 
                p1.setHealth(playerHealth + ateFood.getHealthAdded());  
                playerInventory.remove(treasure); 
                System.out.println("You ate " + t + " and gained " 
                + ateFood.getHealthAdded() + " health."); 
            }
        }
        else {
            System.out.println(t+ " is not in your inventory.");
        }
    }
    
    public void check(String t){
        if (t.equals("Inventory")){
            System.out.println(p1.getInventory()); 
        }
        else if (t.equals("Stats")){
            System.out.println("Your health: " + p1.getHealth() +
            "\n Your armour: " + p1.getArmour());
        }
        else if (t.equals("Health")){
            System.out.println("Your health: " + p1.getHealth()); 
        }
        else if (t.equals("Armour")){
            System.out.println("Your armour: " + p1.getArmour()); 
        }
        else{
            System.out.println("There is nothing to check."); 
        }
    }

    
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        String input = "";
        Game gm = new Game();

        // An introduction displayed to the user 
        // Let the user know what they can do and how to quit, the user quits by entering 'q'
        System.out.println("Welcome to the Tower,treasure seeker. Can you make it to the treasure chest on the top floor?"); 
        System.out.println("You can enter 'go North' to move up a floor and 'go South' to move down a floor."); 
        System.out.println("To take a treasure, enter 'take' followed by 'name of the item'"); 
        System.out.println("To quit the game, enter 'q'."); 
        
        while(!input.equals("q")) {
            input = scan.nextLine();
            if(input.equals("q")) {
                System.out.println("Thanks for playing!");
                break;
            }else{
                gm.parseCommand(input);
            }
        }
        
        scan.close();
        
    }
}