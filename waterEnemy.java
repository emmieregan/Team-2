
public class waterEnemy extends Enemy {
    public waterEnemy(String name, int health, int baseDamage) {
        super(name, health, baseDamage);
    }

    @Override
    public int attack() {
        // Water enemies heal a little when they attack
        this.health += 1;
        return baseDamage + 1;
    }

    @Override
    public String toString() {
        return name + " (HP: " + health + ", Type: Water)";
    }
}
