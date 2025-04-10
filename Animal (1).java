
/**
 * Write a description of class Animal here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Animal
{
    private String name; 
    private int damage; 
    private boolean isKilled; 
    private boolean isTriggered;  
    

    /**
     * Constructor for objects of class Animal
     */
    public Animal(String name, int damage)
    {
        this.name = name; 
        this.damage = damage;
        this.isKilled = false; 
        this.isTriggered = true;
    }
    
    public String getName(){
        return name; 
    }
    
    public void setName(String newName){
        this.name = newName; 
    }
    
    public int getDamage(){
        return damage; 
    }
    
    public void setDamage(int newDamage){
        this.damage = newDamage; 
    }
    
    public boolean getIsKilled(){
        return isKilled;
    }
    
    public void setIsKilled(boolean newIsKilled){
        this.isKilled = newIsKilled; 
    }
    
    public boolean getIsTriggered(){
        return isTriggered;
    }
    
    public void setIsTriggered(boolean newIsTriggered){
        this.isKilled = newIsTriggered; 
    }
    //override the toString() to display the name of the animal
    @Override
    public String toString(){
    return name;   
    }
    
}
