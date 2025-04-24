
/**
 * This is the Food class that describes consumable healing items for the Game
 *
 * @author Ange Emmie Anisa
 * @version Spring 2025
 */
public class Food extends Treasure
{
    protected int healthAdded; 
    
    public Food(String name, String description, int healthAdded){
        super(name, description); 
        this.healthAdded = healthAdded; 
    }
    
    public int getHealthAdded(){
        return healthAdded; 
    }
    
    public void setHealthAdded(int newHealthAdded){
        this.healthAdded = newHealthAdded; 
    }
    
    @Override
    public String toString(){
        return super.toString() + " that gives you " + healthAdded + " when eaten.";
    }
}
