
public class fireEnemy extends Enemy {
    public fireEnemy(String name, int health, int baseDamage) {
        super(name, health, baseDamage);
    }

    @Override
    public int attack() {
        // Fire enemies do burn damage
        return baseDamage + 2;
    }

    @Override
    public String toString() {
        return name + " (HP: " + health + ", Type: Fire)";
    }
}
