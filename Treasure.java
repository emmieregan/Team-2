
/**
 * Write a description of class Treasure here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Treasure
{
    protected String name;
    protected String description; 
    
    public Treasure(String name, String description)
    {
        this.name = name; 
        this.description = description; 
    }
    
    public Treasure(String name){
        this.name = name; 
        this.description = "";  
    }
    
    public String getName(){
        return name; 
    }
    
    public void setName(String newName){
        this.name = name; 
    }
    
    public String getDescription(){
        return description; 
    }
    
    public void setDescription(String newDescription){
        this.description = newDescription; 
    }
    
    @Override
    public boolean equals(Object object){
        if (!(object instanceof Treasure)){
            return false; 
        }
        else {
            Treasure treasure = (Treasure)object; //cast to convert pointer 
            //at reference object to something of type Treasure (this is the casted object/input)
            if ( treasure.getName().equals(this.getName()) ){
                return true; 
            }
            else {
                return false; 
            }

        }
        
    }

    @Override
    public String toString(){
        return name + ", " + description;
    }


}
