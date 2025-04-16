import java.util.ArrayList;

public class Player {
    private int health;
    private String statusEffect; // e.g., "Poisoned"
    private ArrayList<Item> inventory;

    public Player() {
        this.health = 100;
        this.statusEffect = "None";
        this.inventory = new ArrayList<>();
    }

    public int getHealth() {
        return health;
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
    
    
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public void dropItem(Item item) {
        inventory.remove(item);
    }
}
