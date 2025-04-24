import java.util.ArrayList;

public class Player {
    private String description;
    private String playerName;
    private int health;
    private int maxHealth;
    private String statusEffect; // e.g., "Poisoned"
    private int armour; 
    private Room location;
    private ArrayList<Treasure> inventory;

    public Player(int health, String statusEffect, Room location) {
        this.health = health;
        this.maxHealth = health;
        this.statusEffect = "None";
        this.inventory = new ArrayList<Treasure>();
        this.location = location;
        this.armour = armour;
        
    }
    
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
    public int getHealth() {
        return health;
    }
    public int getMaxHealth(){
        return maxHealth;
    }
    public void changeHealth(int amount) {
        health += amount;
        if (health > 100) health = 100;
        if (health < 0) health = 0;
    }
    
    
    //example of poison status used 
    public void applyStatusEffect() {
        if (statusEffect.equals("Poisoned")) {
            System.out.println("You are poisoned!");
            changeHealth(-2);
        }
    }

    public String getStatusEffect() {
        return statusEffect;
    }

    public void setStatusEffect(String effect) {
        this.statusEffect = effect;
        System.out.println("You are now " + effect.toLowerCase() + "!");
    }

    public void clearStatusEffect() {
        this.statusEffect = "None";
        System.out.println("Your status effect has been cleared.");
    }
    // ^^^^ please add this to our player class 
    
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
    
    public ArrayList<Treasure> getInventory() {
        return inventory;
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
    
    //public void addTreasure(Treasure item) {
        //inventory.add(item);
    //}

    public void dropItem(Treasure item) {
        inventory.remove(item);
    }
}
