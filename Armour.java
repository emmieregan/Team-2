
/**
 * Write a description of class Armour here.
 *
 * @author Ange Li
 * @version Spring 2025
 */
public class Armour extends Treasure
{
    protected int armourAdded; 
   
    public Armour(String name, String description, int armourAdded){
        super(name, description); 
        this.armourAdded = armourAdded; 
    }
    
    public int getArmourAdded(){
        return armourAdded; 
    }
    
    public void setArmourAdded(int newArmourAdded){
        this.armourAdded = newArmourAdded; 
    }
    
    @Override
    public String toString(){
        return super.toString() + " that gives you " + armourAdded + " when worn.";
    }
    
}
