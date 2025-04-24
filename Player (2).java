import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

/**
 * Write a description of class Player here.
 *
 * @author Ange Li
 * @version Spring 2025 - February 6, 2025 
 */
public class Player
{
    private String description;
    private String playerName;
    private int health;
    private int armour; 
    private Room location;
    private ArrayList<Treasure> inventory; 
    
    public Player(String playerDesc, String playerName, Room playerLoc) {
        //constructor
        this.description = playerDesc; 
        this.playerName = playerName; 
        this.location = playerLoc;
        this.health = 20; 
        this.armour = 0; 
        this.inventory = new ArrayList<Treasure>();
    }
    
    //getter method for the name of the player
    public String getName(){
        return playerName; 
    }
    
    //getter method for the location description of the player
    public String getDescription(){
        return description; 
    }   
    //getter method for the health of the player
    public int getHealth(){
        return health; 
    }
    
    //a setter method for the health of the player
    public void setHealth(int setHealth){
        health = setHealth; 
    }
    
    public int getArmour(){
        return armour; 
    }
    
    public void setArmour(int newArmour){
        this.armour = newArmour; 
    }
    
    //setter method for the location of the player
    //check if the location is valid (not null) and print a message if it is not valid
    public void setLocation(Room setLocation){
        if (setLocation != null){
           location = setLocation; 
        }
        else{
            System.out.println("That doesn't exist! If there is a floor above, you can enter 'go North' to go up. If there is a floor below, you can enter 'go South' to go down."); 
        }
    }
    
    //a getter method for the location of the player
    public Room getLocation(){
        return location; 
    }
    
    public ArrayList<Treasure> getInventory(){
        return inventory; 
    }
    
    public void addTreasure(String addTreasure){
        Treasure treasure = location.findTreasure(addTreasure);
        if (location.containsTreasure(addTreasure)){
            inventory.add(treasure); 
        }
        if (treasure instanceof Armour){
            Armour armourAdded = (Armour)treasure; 
            setArmour(armour + armourAdded.getArmourAdded()); 
        }
    }
    
    public boolean hasTreasure(String t){
        for (int i = 0; i < inventory.size(); i++){
            if ((inventory.get(i).getName()).equals(t)){
                return true; 
            }
        }
        return false; 
    }
    
    public Treasure findTreasure(String t){
        for (int i = 0; i < inventory.size(); i++){
            if ((inventory.get(i).getName()).equals(t)){
                return inventory.get(i); 
            }
        }
        return null; 
    }
    
    //override the toString() to display the name of the current player
    @Override
    public String toString(){
    return playerName;   
    }
}