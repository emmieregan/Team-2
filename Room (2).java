import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
/**
 * Write a description of class Room here.
 *
 * @author Ange Li
 * @version Spring 2025 - February 6, 202
 */
public class Room
{
    // Room has these attributes
    private String name;
    private String description;
    private boolean locked;
    private Room n, e, s, w; //variables that store pointer to the doors in this room
    private Enemy enemy; 
    private ArrayList<Treasure> treasureChest; 
    
    //a new Room is created with this constructor
    public Room (String name, String description, boolean locked, Enemy enemy) {
        this.name = name;
        this.description = description;
        this.locked = locked;
        this.enemy = enemy; 
        this.treasureChest = new ArrayList<Treasure>(); 
    }
    
    public void setNeighbors(Room n, Room e, Room w, Room s){
        this.n = n;
        this.e = e;
        this.s = s;
        this.w = w;
    }
    
    public Room getN() {
        return n;
    }
    
    public Room getE() {
        return e;
    }
    
    public Room getS() {
        return s;
    }
    
    public Room getW() {
        return w;
    }
    
    public Enemy getEnemy(){
        return animal; 
    }
    
    public void setEnemy(Enemy newEnemy){
        this.enemy = newEnemy; 
    }
    
    public ArrayList<Treasure> getTreasureChest(){
        return treasureChest; 
    }
    
    public void addTreasure(Treasure addTreasure){
        treasureChest.add(addTreasure); 
    }
    
    public void removeTreasure (String t){
        for (int i = 0; i < treasureChest.size(); i++){
            if ((treasureChest.get(i).getName()).equals(t)){
                treasureChest.remove(i); 
            }
        }
    }
    
    public boolean containsTreasure (String t){
        for (int i = 0; i < treasureChest.size(); i++){
            if ((treasureChest.get(i).getName()).equals(t)){
                return true;  
            }
        }
        return false; 
    }
    
    public Treasure findTreasure(String t){
        for (int i = 0; i < treasureChest.size(); i++){
            if ((treasureChest.get(i).getName()).equals(t)){
                return treasureChest.get(i);  
            }
        }
        return null; 
    }
    
    //override the toString() to display the name and description of the room
    @Override
    public String toString(){
    return name +"." + " " + description
    + "\n It is patrolled by " + animal +"." 
    + " The treasure chest contains: " + treasureChest;  
    }
}
